package com.yl.common.mongo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.Block;
import com.mongodb.DBRef;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexModel;
import com.mongodb.client.model.InsertManyOptions;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author DELL
 * @date 2018/2/2
 */
public enum MongoKit {

    /*
    *枚举实现单例模式
    */
    INSTANCE;
    private static MongoClient client;
    private static MongoDatabase defaultDb;
    private static Logger logger = LoggerFactory.getLogger(MongoKit.class.getName());

    public MongoClient getClient() {
        return client;
    }

    public void init(MongoClient client, String database) {
        MongoKit.client = client;
        MongoKit.defaultDb = client.getDatabase(database);
    }

    public MongoCollection<Document> getCollection(String collectionName) {
        return defaultDb.getCollection(collectionName);
    }

    /**
     * 新增多条
     *
     * @param collectionName
     * @param docs
     * @param ops
     */
    public void insert(String collectionName, List<Document> docs, InsertManyOptions ops) {
        getCollection(collectionName).insertMany(uniding(docs), ops);
    }

    /**
     * 新增一条
     *
     * @param collectionName
     * @param doc
     */
    public void insert(String collectionName, Document doc) {
        getCollection(collectionName).insertOne(uniding(doc));
    }

    /**
     * 聚合 , 返回JSON对象
     *
     * @param collectionName
     * @param query
     * @param allowDiskUse
     * @return
     */
    public List<JSONObject> aggregate(String collectionName, List<Bson> query, boolean allowDiskUse) {

        final List<JSONObject> list = new ArrayList<JSONObject>();

        Block<Document> block = new Block<Document>() {

            @Override
            public void apply(Document document) {
                //document = (MyDocument)iding(document);
                list.add(parseObject(document.toJson()));
            }
        };

        getCollection(collectionName).aggregate(query).allowDiskUse(allowDiskUse).forEach(block);

        return list;
    }

    /**
     * 聚合
     *
     * @param collectionName
     * @param query
     * @param allowDiskUse
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> List<T> aggregate(String collectionName, List<Bson> query, boolean allowDiskUse, final Class<T> clazz) {

        final List list = new ArrayList();

        Block<Document> block = new Block<Document>() {

            @Override
            public void apply(Document document) {
                document = iding(document);
                list.add(parseObject(document, clazz));
            }
        };

        getCollection(collectionName).aggregate(query).allowDiskUse(allowDiskUse).forEach(block);

        return list;
    }

    /**
     * 聚合 , 返回JSON对象 不处理id toString
     *
     * @param collectionName
     * @param query
     * @param allowDiskUse
     * @return
     */
    public List<JSONObject> aggregateDoNotDealID(String collectionName, List<Bson> query, boolean allowDiskUse) {

        final List<JSONObject> list = new ArrayList<JSONObject>();

        Block<Document> block = new Block<Document>() {

            public void apply(Document document) {
                list.add(parseObject(document.toJson()));
            }
        };

        getCollection(collectionName).aggregate(query).allowDiskUse(allowDiskUse).forEach(block);

        return list;
    }


    /**
     * 聚合 不处理id toString
     *
     * @param collectionName
     * @param query
     * @param allowDiskUse
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> List<T> aggregateDoNotDealID(String collectionName, List<Bson> query, boolean allowDiskUse, final Class<T> clazz) {

        final List list = new ArrayList();

        Block<Document> block = new Block<Document>() {

            public void apply(Document document) {
                list.add(parseObject(document, clazz));
            }
        };

        getCollection(collectionName).aggregate(query).allowDiskUse(allowDiskUse).forEach(block);

        return list;
    }

    /**
     * 按条件查询  返回JSON对象
     *
     * @param collectionName
     * @param projection
     * @return
     */
    public List<JSONObject> find(String collectionName, Bson projection) {
        return find(collectionName, new BsonDocument(), new BsonDocument(), projection, 0, 0, "");
    }

    /**
     * 整个集合 排序，取指定数量， 返回JSON对象
     *
     * @param collectionName
     * @param limit
     * @param sort
     * @param projection
     * @return
     */
    public List<JSONObject> find(String collectionName, int limit, Bson sort, Bson projection) {
        return find(collectionName, new BsonDocument(), sort, projection, limit, 0, "");
    }

    /**
     * 整个集合 排序，取指定数量，
     *
     * @param collectionName
     * @param limit
     * @param sort
     * @param projection
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> List<T> find(String collectionName, int limit, Bson sort, Bson projection, Class<T> clazz) {
        return find(collectionName, new BsonDocument(), sort, projection, limit, 0, "", clazz);
    }


    /**
     * 按条件查询 排序，取指定数量， 返回JSON对象
     *
     * @param collectionName
     * @param query
     * @param sort
     * @param projection
     * @return
     */
    public List<JSONObject> find(String collectionName, Bson query, Bson sort, Bson projection) {
        return find(collectionName, query, sort, projection, 0, 0, "");
    }

    /**
     * 排序 分页查询， 返回JSON对象
     *
     * @param collectionName
     * @param query
     * @param sort
     * @param limit
     * @param skip
     * @param projection
     * @return
     */
    public List<JSONObject> find(String collectionName, Bson query, Bson sort, int limit, int skip, Bson projection) {
        return find(collectionName, query, sort, projection, limit, skip, "");
    }

    /**
     * 排序 分页查询
     *
     * @param collectionName
     * @param query
     * @param sort
     * @param limit
     * @param skip
     * @param projection
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> List<T> find(String collectionName, Bson query, Bson sort, int limit, int skip, Bson projection, Class<T> clazz) {
        return find(collectionName, query, sort, projection, limit, skip, "", clazz);
    }


    /**
     * 集合关联  返回JSON对象
     *
     * @param collectionName
     * @param limit
     * @param skip
     * @param sort
     * @param projection
     * @param join
     * @return
     */
    public List<JSONObject> find(String collectionName, int limit, int skip, Bson sort, Bson projection, String join) {
        return find(collectionName, new BsonDocument(), sort, projection, limit, skip, join);
    }


    /**
     * 集合关联
     *
     * @param collectionName
     * @param limit
     * @param skip
     * @param sort
     * @param projection
     * @param join
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> List<T> find(String collectionName, int limit, int skip, Bson sort, Bson projection, String join, Class<T> clazz) {
        return find(collectionName, new BsonDocument(), sort, projection, limit, skip, join, clazz);
    }

    /**
     * 按条件查询 排序
     *
     * @param collectionName
     * @param query
     * @param sort
     * @param projection
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> List<T> find(String collectionName, Bson query, Bson sort, Bson projection, Class<T> clazz) {
        return find(collectionName, query, sort, projection, 0, 0, "", clazz);
    }

    /**
     * 排序,条件查询，取前n条数据
     *
     * @param collectionName
     * @param query
     * @param sort
     * @param projection
     * @param limit
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> List<T> find(String collectionName, Bson query, Bson sort, Bson projection, int limit, Class<T> clazz) {
        return find(collectionName, query, sort, projection, limit, 0, "", clazz);
    }

    /**
     * 条件查询，返回JSON对象
     *
     * @param collectionName
     * @param query
     * @param projection
     * @return
     */
    public List<JSONObject> find(String collectionName, Bson query, Bson projection) {
        return find(collectionName, query, new BsonDocument(), projection, 0, 0, "");
    }

    /**
     * 按条件查询数量
     *
     * @param collectionName
     * @param query
     * @return
     */
    public long count(String collectionName, Bson query) {
        return getCollection(collectionName).count(query);
    }

    /**
     * 整个集合的大小
     *
     * @param collectionName
     * @return
     */
    public long count(String collectionName) {
        return getCollection(collectionName).count();
    }

    /**
     * 查询一条记录，返回JSON对象
     *
     * @param collectionName
     * @param query
     * @param sort
     * @param join
     * @return
     */
    public JSONObject findOne(String collectionName, Bson query, Bson sort, String join) {
        return toJSON(
                iding(jointing(getCollection(collectionName).find(query).sort(sort).first(), join))
        );
    }

    /**
     * 查询一条记录
     *
     * @param collectionName
     * @param query
     * @param sort
     * @param join
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T findOne(String collectionName, Bson query, Bson sort, String join, Class<T> clazz) {
        return parseObject(
                iding(jointing(getCollection(collectionName).find(query).sort(sort).first(), join))
                , clazz);
    }

    /**
     * 查询的具体实现
     *
     * @param collectionName
     * @param query
     * @param sort
     * @param projection
     * @param limit
     * @param skip
     * @param join
     * @return
     */
    public List<JSONObject> find(String collectionName, Bson query, Bson sort, Bson projection, int limit,
                                 int skip, final String join) {

        final List<JSONObject> list = new ArrayList<JSONObject>();

        Block<Document> block = new Block<Document>() {

            @Override
            public void apply(Document document) {
                document = iding(document);
                document = jointing(document, join);
                list.add(toJSON(document));
            }
        };
        getCollection(collectionName).find(query).projection(projection).sort(sort).limit(limit).skip(skip).forEach(block);

        return list;

    }

    /**
     * 查询的具体实现
     *
     * @param collectionName
     * @param query
     * @param sort
     * @param projection
     * @param limit
     * @param skip
     * @param join
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> List<T> find(String collectionName, Bson query, Bson sort, Bson projection, int limit, int skip,
                            final String join, final Class<T> clazz) {

        final List list = new ArrayList();

        Block<Document> block = new Block<Document>() {

            public void apply(Document document) {
                document = iding(document);
                document = jointing(document, join);
                list.add(parseObject(document, clazz));
            }
        };
        getCollection(collectionName).find(query).projection(projection).sort(sort).limit(limit).skip(skip).forEach(block);
        return list;
    }

    /**
     * 更新
     *
     * @param collectionName
     * @param queue
     * @param data
     * @return
     */
    public long update(String collectionName, Bson queue, Bson data) {
        UpdateResult updateResult = getCollection(collectionName).updateMany(queue, data, new UpdateOptions());
        return updateResult.getModifiedCount();
    }

    /**
     * 更新 多条
     *
     * @param collectionName
     * @param queue
     * @param data
     * @return
     */
    public long update(String collectionName, Bson queue, Bson data, boolean isMutil) {
        if (isMutil) {
            UpdateResult updateResult = getCollection(collectionName).updateMany(queue, data, new UpdateOptions());
            return updateResult.getModifiedCount();
        }
        UpdateResult updateResult = getCollection(collectionName).updateMany(queue, data, new UpdateOptions());
        return updateResult.getModifiedCount();
    }

    /**
     * 更新一条
     *
     * @param collectionName
     * @param queue
     * @param data
     * @return
     */
    public long updateOne(String collectionName, Bson queue, Bson data) {
        UpdateResult updateResult = getCollection(collectionName).updateOne(queue, data);
        return updateResult.getModifiedCount();
    }

    /**
     * 替换一条
     *
     * @param collectionName
     * @param queue
     * @param document
     * @return
     */
    public long replaceOne(String collectionName, Bson queue, Document document) {
        UpdateResult updateResult = getCollection(collectionName).replaceOne(queue, document);
        return updateResult.getModifiedCount();
    }

    /**
     * 删除所有
     *
     * @param collectionName
     * @param queue
     * @return
     */
    public long delete(String collectionName, Bson queue) {
        DeleteResult deleteResult = getCollection(collectionName).deleteMany(queue);
        return deleteResult.getDeletedCount();
    }

    /**
     * 删除一条
     *
     * @param collectionName
     * @param queue
     * @return
     */
    public long deleteOne(String collectionName, Bson queue) {
        MongoCollection<Document> collections = getCollection(collectionName);
        if (collections != null) {
            DeleteResult deleteResult = collections.deleteOne(queue);
            return deleteResult.getDeletedCount();
        }
        return 0L;
    }

    /**
     * 校验
     *
     * @param obj
     * @return
     */
    public String validation(Object obj) {

        StringBuffer buffer = new StringBuffer(64);//用于存储验证后的错误信息

        Validator validator = Validation.buildDefaultValidatorFactory()
                .getValidator();

        Set<ConstraintViolation<Object>> constraintViolations = validator
                .validate(obj);//验证某个对象,其实也可以只验证其中的某一个属性的

        Iterator iter = constraintViolations.iterator();
        while (iter.hasNext()) {
            ConstraintViolation c = (ConstraintViolation) iter.next();
            buffer.append(c.getMessage());
        }

        return buffer.toString();
    }

    //校验单个属性
    public String validation(Object obj, String[] keys) {

        StringBuffer buffer = new StringBuffer(64);//用于存储验证后的错误信息

        Validator validator = Validation.buildDefaultValidatorFactory()
                .getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = new HashSet<ConstraintViolation<Object>>();

        for (String key : keys) {
            Iterator<ConstraintViolation<Object>> it = validator.validateProperty(obj, key).iterator();
            if (it.hasNext()) {
                constraintViolations.add(it.next());
            }
        }

        Iterator iter = constraintViolations.iterator();
        while (iter.hasNext()) {
            ConstraintViolation c = (ConstraintViolation) iter.next();
            buffer.append(c.getMessage());
        }

        return buffer.toString();
    }

    /**
     * 创建索引
     *
     * @param collectionName
     * @param bson
     * @return
     */
    public String setIndex(String collectionName, Bson bson) {
        return getCollection(collectionName).createIndex(bson);
    }

    /**
     * 创建多个索引
     *
     * @param collectionName
     * @param list
     * @return
     */
    public List<String> setIndex(String collectionName, List<IndexModel> list) {
        return getCollection(collectionName).createIndexes(list);
    }

    /**
     * 获取索引
     *
     * @param collectionName
     * @return
     */
    public List<JSONObject> getIndex(String collectionName) {

        final List list = new ArrayList();

        Block<Document> block = new Block<Document>() {

            public void apply(final Document document) {
                list.add(parseObject(document.toJson()));
            }
        };

        getCollection(collectionName).listIndexes().forEach(block);

        return list;
    }

    /**
     * 删除索引
     *
     * @param collectionName
     * @param bson
     */
    public void deleteIndex(String collectionName, Bson bson) {

        getCollection(collectionName).dropIndex(bson);

    }

    /**
     * 删除集合的所有索引
     *
     * @param collectionName
     */
    public void deleteIndex(String collectionName) {
        getCollection(collectionName).dropIndexes();
    }

    /**
     * 对Document的_id属性进行处理，ObjectId 转为String
     *
     * @param document
     * @return
     */
    private Document iding(Document document) {
        try {
            if (document == null || document.get("_id") == null) {
                return document;
            } else {
                document.put("_id", document.get("_id").toString());
            }
        } catch (ClassCastException e) {
                /*如果转换出错直接返回原本的值,不做任何处理*/

        }
        return document;
    }

    /**
     * 对_id进行处理
     *
     * @param list
     * @return
     */
    private List<Document> uniding(List<Document> list) {
        List<Document> newList = new ArrayList<Document>();
        for (Document doc : list) {
            newList.add(uniding(doc));
        }
        return newList;
    }

    /**
     * 删除_id属性
     *
     * @param document
     * @return
     */
    private Document uniding(Document document) {
        try {
            if (document == null || document.get("_id") == null) {
                return document;
            } else {
                document.remove("_id");
            }
        } catch (ClassCastException e) {
                /*如果转换出错直接返回原本的值,不做任何处理*/

        }
        return document;
    }

    /**
     * 关联的处理
     *
     * @param document
     * @param join
     * @return
     */
    private Document jointing(Document document, String join) {
        if (join != null && !join.trim().isEmpty()) {
            try {
                DBRef dbRef = document.get(join, DBRef.class);
                Document joinDoc = getCollection(dbRef.getCollectionName())
                        .find(new Document("_id", dbRef.getId())).first();

                joinDoc = iding(joinDoc);
                joinDoc.put("id", joinDoc.getString("_id"));
                joinDoc.remove("_id");
                document.put(join, joinDoc);
            } catch (ClassCastException e) {
            }
        }
        return document;

    }

    /*由于fastjson转换空对象时就会直接抛出异常,而在实际查询中查不到东西是很正常的
    * ,所以为了避免会有空异常,特别做了异常处理*/

    private JSONObject parseObject(String json) {
        try {
            if (json != null && !json.isEmpty()) {
                return JSON.parseObject(json);
            }
            return new JSONObject();
        } catch (NullPointerException e) {
            error("parseObject", json);
            return new JSONObject();
        }


    }

    private <T> T parseObject(Document doc, Class<T> clazz) {
        try {
            if (doc == null) {
                return JSON.parseObject(new JSONObject().toJSONString(), clazz);
            }
            return JSON.parseObject(JSON.toJSONString(doc), clazz);
        } catch (NullPointerException e) {
            error("parseObject", clazz.getName());
            return JSON.parseObject(new JSONObject().toJSONString(), clazz);
        }
    }

    private JSONObject toJSON(Object obj) {
        try {
            return (JSONObject) JSON.toJSON(obj);
        } catch (NullPointerException e) {
            error("toJSON", obj.getClass().getName());
            return new JSONObject();
        }
    }

    protected void error(String funName, String text) {
        logger.error("MongKit tips: funName " + funName + " is error ! " + text);
    }

    public Map<String, Object> toMap(Object obj) {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {

            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                /*过滤class属性  */
                if (!key.equals("class")) {
                    //*得到property对应的getter方法*/
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);

                    map.put(key, value);
                }
            }
        } catch (Exception e) {
            MongoKit.INSTANCE.error("MongKit.class", "toMap is error " + e.getMessage());
        }
        return map;
    }

    public void setDebug(boolean debug) {
        ch.qos.logback.classic.Level level;
        if (debug) {
            level = ch.qos.logback.classic.Level.DEBUG;
        } else {
            level = ch.qos.logback.classic.Level.WARN;
        }
        ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        root.setLevel(level);
    }

}
