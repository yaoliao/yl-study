package com.yl.study;

import com.yl.common.utils.serialize.Serializer;
import com.yl.common.utils.serialize.SerializerFactory;
import com.yl.common.utils.serialize.fastjson.FastJsonSerializer;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DELL_yaol on 2017/10/27.
 */
public class SerializeTest {


    @Test
    public void fastjson() {
        FastJsonSerializer fastjson = new FastJsonSerializer();
        Map<String, String> map = new HashMap<>();
        map.put("name", "Rose");
        map.put("age", "22");
        byte[] bytes = fastjson.serialize(map);
        System.out.println(Arrays.toString(bytes));
        Map m = fastjson.deserialize(bytes, Map.class);
        System.out.println(m.toString());
    }

    @Test
    public void getSerializer(){
        Serializer load = SerializerFactory.load();
        Map<String, String> map = new HashMap<>();
        map.put("name", "Rose");
        map.put("age", "22");
        byte[] bytes = load.serialize(map);
        load.deserialize(bytes,HashMap.class);
    }
}
