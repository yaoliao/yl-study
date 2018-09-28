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

/**
 * @author Administrator
 * @date 2018/9/26 0026
 * @time 上午 10:44
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public class NioServer {

    private ServerSocketChannel serverSocketChannel;
    private Selector selector;

    public NioServer() throws IOException {

        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        //启动服务端
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));

        selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("Server 启动成功............");

        handleKeys();
    }

    private void handleKeys() throws IOException {

        while (true) {
            int select = selector.select(30 * 1000);
            if (select == 0) continue;
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = keys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                keyIterator.remove();
                if (!key.isValid()) {
                    continue;
                }
                handleKey(key);
            }
        }
    }

    private void handleKey(SelectionKey key) throws IOException {
        if (key.isAcceptable()) {
            handleAcceptable(key);
        }

        if (key.isReadable()) {
            handleReadable(key);
        }

        if (key.isWritable()) {
            handleWritable(key);
        }
    }


    private void handleAcceptable(SelectionKey key) throws IOException {
        SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
        clientChannel.configureBlocking(false);
        System.out.println("--------  Accept --------  接受到新的ClientSocket");
        clientChannel.register(selector, SelectionKey.OP_READ, new ArrayList<>());
    }


    @SuppressWarnings("unchecked")
    private void handleReadable(SelectionKey key) throws ClosedChannelException, UnsupportedEncodingException {
        SocketChannel clientChanel = (SocketChannel) key.channel();
        ByteBuffer buffer = CodecUtil.reader(clientChanel);
        if (buffer == null) {
            //读不到数据，连接已断开??????
            clientChanel.register(selector, 0);
            return;
        }

        if (buffer.position() > 0) {
            ArrayList<String> response = (ArrayList<String>) key.attachment();
            String string = CodecUtil.newString(buffer);
            response.add("响应: " + string);
            System.out.println("--------  Readable --------  接受到新的Readable");
            clientChanel.register(selector, SelectionKey.OP_WRITE, key.attachment());
        }
    }

    @SuppressWarnings("unchecked")
    private void handleWritable(SelectionKey key) throws ClosedChannelException {

        SocketChannel channel = (SocketChannel) key.channel();
        List<String> responses = (List<String>) key.attachment();
        responses.forEach(e -> {
            try {
                CodecUtil.write(channel, e);
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
        });
        channel.register(selector, SelectionKey.OP_READ, key.attachment());
        responses.clear();
    }

    public static void main(String[] args) throws IOException {
        NioServer nioServer = new NioServer();
    }

}
