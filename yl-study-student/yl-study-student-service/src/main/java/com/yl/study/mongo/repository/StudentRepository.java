package com.yl.study.mongo.repository;

import com.mongodb.client.model.Filters;
import com.yl.common.mongo.MongoUtil;
import com.yl.study.mongo.model.AggregationBean;
import com.yl.study.mongo.model.MongoStudent;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by DELL on 2018/2/6.
 */
public class StudentRepository implements MongoRepository<MongoStudent> {

    /**
     * 集合名字
     */
    private static final String STUDENT_COLLECTION = "student";

    @Resource
    private MongoUtil mongoUtil;

    /**
     * 保存/创建一个对象
     *
     * @param var1
     * @return
     */
    @Override
    public MongoStudent save(MongoStudent var1) {
        if (var1 != null) {
            mongoUtil.insert(STUDENT_COLLECTION, var1);
        }
        return var1;
    }

    /**
     * 更新对象
     *
     * @param var1
     * @return
     */
    @Override
    public MongoStudent update(MongoStudent var1) {
        if (var1 != null) {
            mongoUtil.insert(STUDENT_COLLECTION, var1);
        }
        //更新条件
        Bson bson = Filters.and(Filters.eq("name", var1.getName()),
                Filters.eq("age", var1.getAge()));

        //更新内容
        Document doc = new Document("lastModifyTime", new Date()).append("name", "This is new Name");

        mongoUtil.updateField(STUDENT_COLLECTION, bson, doc);

        return var1;
    }

    /**
     * 统计数量
     *
     * @param bson
     * @return
     */
    @Override
    public Long count(Bson bson) {
        return mongoUtil.count(STUDENT_COLLECTION, bson);
    }

    /**
     * 测试 mongo 的聚合
     * http://www.runoob.com/mongodb/mongodb-aggregate.html
     *
     * @return
     */
    public List<AggregationBean> find() {

        // $match 管道符
        Document match = new Document("$match", new Document("age", 22));
        Document group = new Document("$group", new Document("_id",
                //已 name 和 age分组
                new Document("name", "$name")
                        .append("age", "$age"))
                .append("count", new Document("$sum", 1)));
        List<Bson> queryList = new ArrayList<>();
        queryList.add(match);
        queryList.add(group);

        List<AggregationBean> aggregate = mongoUtil.aggregate(STUDENT_COLLECTION, queryList, AggregationBean.class);
        return aggregate;
    }
}
