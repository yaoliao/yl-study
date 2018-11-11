package com.yl.study.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author Administrator
 * @date 2018/9/4 0004
 * @time 下午 14:04
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public class ProtobufTest {

    public static void main(String[] args) throws InvalidProtocolBufferException {

        BaseRequestProto.RequestProtocol protocol = BaseRequestProto.RequestProtocol.newBuilder()
                .setRequestId(22).setReqMsg("这是protobuf的测试。。。。").build();

        byte[] bytes = protocol.toByteArray();

        BaseRequestProto.RequestProtocol requestProtocol = BaseRequestProto.RequestProtocol.parseFrom(bytes);

        String string = requestProtocol.toString();

        String reqMsg = requestProtocol.getReqMsg();
        System.out.println(reqMsg);

        System.out.println(protocol.toString().equals(string));

    }

}
