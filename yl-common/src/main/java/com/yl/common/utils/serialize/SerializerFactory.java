package com.yl.common.utils.serialize;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author DELL
 * @date 2017/9/18
 */
public class SerializerFactory {

    /**
     * SPI
     *
     * @return
     */
    public static Serializer load() {

        Iterator iterator = ServiceLoader.load(Serializer.class).iterator();
        Serializer next = null;
        while (iterator.hasNext()) {
            next = (Serializer) iterator.next();
        }
        return next;

    }

}
