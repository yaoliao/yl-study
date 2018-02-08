package com.yl.study.mongo.repository;

import org.bson.conversions.Bson;

/**
 * Created by DELL on 2018/2/6.
 */
public interface MongoRepository<T> {

    /**
     * 保存/创建一个对象
     *
     * @param var1
     * @param <S>
     * @return
     */
    <S extends T> S save(S var1);

    /**
     * 更新对象
     *
     * @param var1
     * @param <S>
     * @return
     */
    <S extends T> S update(S var1);

    /**
     * 统计数量
     *
     * @param bson
     * @return
     */
    Long count(Bson bson);


}
