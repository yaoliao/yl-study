package com.yl.study.nio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @date 2018/9/26 0026
 * @time 下午 13:58
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public class NioClient {

    private SocketChannel clientChannel;
    private Selector selector;

    private final List<String> response = new ArrayList<>();

    private CountDownLatch latch = new CountDownLatch(1);

    public NioClient() throws IOException {

        clientChannel = SocketChannel.open();
        clientChannel.configureBlocking(false);

        selector = Selector.open();
        clientChannel.register(selector, SelectionKey.OP_CONNECT, response);

        // 连接服务
        clientChannel.connect(new InetSocketAddress(8080));

        new Thread(() -> {
            try {
                handleKeys();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("客户端启动成功.......................");

    }

    private void handleKeys() throws IOException {
        while (true) {
            int select = selector.select(10 * 1000);
            if (select == 0) continue;
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = keys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey next = keyIterator.next();
                keyIterator.remove();
                if (!next.isValid()) {
                    continue;
                }
                toHandleKey(next);
            }
        }
    }

    private void toHandleKey(SelectionKey selectionKey) throws IOException {

        if (selectionKey.isConnectable()) {
            handleConnectable(selectionKey);
        }

        if (selectionKey.isReadable()) {
            handleReadable(selectionKey);
        }

        if (selectionKey.isWritable()) {
            handleWritable(selectionKey);
        }

    }


    private void handleConnectable(SelectionKey key) throws IOException {
        if (!clientChannel.isConnectionPending()) {
            return;
        }
        boolean finishConnect = clientChannel.finishConnect();
        System.out.println("接受新的 Channel");
        clientChannel.register(selector, SelectionKey.OP_READ, response);

        latch.countDown();
    }

    private void handleReadable(SelectionKey key) throws UnsupportedEncodingException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer reader = CodecUtil.reader(channel);
        if (reader == null) return;
        if (reader.position() > 0) {
            String string = CodecUtil.newString(reader);
            System.out.println("client 接收到数据 : " + string);
        }
    }

    @SuppressWarnings("unchecked")
    private void handleWritable(SelectionKey key) throws ClosedChannelException {
        SocketChannel channel = (SocketChannel) key.channel();
        List<String> attachment = (List<String>) key.attachment();
        attachment.forEach(e -> {
            try {
                CodecUtil.write(channel, e);
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
        });
        channel.register(selector, SelectionKey.OP_READ, key.attachment());
    }

    public void send(String msg) throws IOException {
        response.add(msg);
        clientChannel.register(selector, SelectionKey.OP_WRITE, response);
        selector.wakeup();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        NioClient client = new NioClient();
        for (int i = 0; i < 100; i++) {
            client.send("Hello Word " + i);
            TimeUnit.SECONDS.sleep(2);
        }
    }

}
