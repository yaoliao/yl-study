package com.yl.controller;

import com.yl.cache.CacheHolder;
import com.yl.utils.BaseResponse;
import com.yl.zk.ZookeeperClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @date 2018/8/29 0029
 * @time 下午 18:54
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@RestController
@RequestMapping("/zk")
public class ZkController {

    private static Logger logger = LoggerFactory.getLogger(ZkController.class);

    /**
     * 获取所有节点
     *
     * @return
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public BaseResponse<List<String>> getZkDate() {
        Map<String, String> all = CacheHolder.getAll();
        List<String> list = new ArrayList<>();
        all.forEach((k, v) -> list.add(k + ":" + v));
        return BaseResponse.createSuccess(list.size() > 0 ? list : null);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public BaseResponse createZk(String host, Integer port) {
        ZookeeperClient client = ZookeeperClient.getClient();
        client.create(host + ":" + port, true);
        return BaseResponse.createSuccess();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public BaseResponse deleteZK(String host, Integer port) {
        ZookeeperClient client = ZookeeperClient.getClient();
        client.delete(host + ":" + port);
        return BaseResponse.createSuccess();
    }

}
