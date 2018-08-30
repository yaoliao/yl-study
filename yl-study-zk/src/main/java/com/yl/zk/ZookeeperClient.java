package com.yl.zk;

import com.yl.cache.CacheHolder;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Administrator
 * @date 2018/8/29 0029
 * @time 下午 16:49
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public class ZookeeperClient implements Client {

    private static Logger logger = LoggerFactory.getLogger(ZookeeperClient.class);

    private static final String ROOT_PATH = "/MYROOT";

    private static final String address = "116.196.100.123:2181";

    private static ZkClient client;

    private volatile KeeperState state = KeeperState.SyncConnected;

    private ZookeeperClient() {
        client = new ZkClient(ZookeeperClient.address, Integer.MAX_VALUE);
        client.subscribeStateChanges(new IZkStateListener() {
            @Override
            public void handleStateChanged(KeeperState keeperState) throws Exception {
                ZookeeperClient.this.state = keeperState;
                if (KeeperState.Disconnected == keeperState) {
                    logger.info("------------- ZK断开连接 --------------");
                } else if (KeeperState.SyncConnected == keeperState) {
                    logger.info("------------- ZK连接成功 --------------");
                }
            }

            @Override
            public void handleNewSession() throws Exception {
                logger.info("------------- ZK重连成功 --------------");
            }
        });
        if (!client.exists(ROOT_PATH)) {
            client.createPersistent(ROOT_PATH);
        }
        addListern(ROOT_PATH);
    }

    public static ZookeeperClient getClient() {
        return SingletonHandler.singleton;
    }

    private static class SingletonHandler {
        private static ZookeeperClient singleton = new ZookeeperClient();
    }

    @Override
    public void create(String path, boolean ephemeral) {
        if (!client.exists(ROOT_PATH)) {
            client.createPersistent(ROOT_PATH);
        }
        String newPath = ROOT_PATH + "/" + path;
        if (client.exists(newPath)) return;
        if (ephemeral) {
            client.createEphemeral(newPath);
        } else {
            client.createPersistent(newPath);
        }
    }


    @Override
    public void delete(String path) {
        try {
            client.delete(ROOT_PATH + "/" + path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isConnect() {
        return state == KeeperState.SyncConnected;
    }

    @Override
    public void doClose() {
        client.close();
    }

    public void addListern(String path) {
        client.subscribeChildChanges(path, (s, list) -> {
            logger.info("清除/更新本地缓存 parentPath=【{}】,currentChilds=【{}】", s, list.toString());
            CacheHolder.clear();
            list.forEach(e -> CacheHolder.put(e.split(":")[0], e.split(":")[1]));
        });
    }
}
