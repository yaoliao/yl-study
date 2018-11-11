package com.yl.study.nio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @author Administrator
 * @date 2018/9/26 0026
 * @time 上午 11:06
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public class CodecUtil {

    public static ByteBuffer reader(SocketChannel channel) {

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            //将channel中的数据写入ByteBuffer
            int read = channel.read(buffer);
            if (read < 0) {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }


    public static String newString(ByteBuffer byteBuffer) throws UnsupportedEncodingException {
        // 写模式 -> 读模式
        byteBuffer.flip();

        int remaining = byteBuffer.remaining();
        byte[] bytes = new byte[remaining];
        System.arraycopy(byteBuffer.array(), byteBuffer.position(), bytes, 0, remaining);
        String s = new String(bytes, "UTF-8");
        return s;
    }


    public static void write(SocketChannel channel, String response) throws UnsupportedEncodingException {
        byte[] bytes = response.getBytes("UTF-8");
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put(bytes);
        buffer.flip();
        try {
            channel.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
