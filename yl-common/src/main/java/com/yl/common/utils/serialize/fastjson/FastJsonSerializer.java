package com.yl.common.utils.serialize.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yl.common.utils.serialize.Serializer;

/**
 *
 * @author DELL
 * @date 2017/9/18
 */
public class FastJsonSerializer implements Serializer {

    @Override
    public byte[] serialize(Object object) {
        byte[] bytes = JSON.toJSONBytes(object, SerializerFeature.SortField);
        return bytes;
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        T object = JSON.parseObject(bytes, clazz, Feature.SortFeidFastMatch);
        return object;
    }
}
