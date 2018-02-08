package com.yl.common.mongo;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertManyOptions;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by DELL on 2018/2/2.
 */
public class MongoUtil {

    private static Logger logger = LoggerFactory.getLogger(MongoUtil.class);

    private static MongoClient mongoClient = null;

    private String domains;
    private String user;
    private String password;
    private String database;
    private int connectionsPerHost;
    private int connectTimeout;
    private int maxWaitTime;
    private int socketTimeout;
    private int threadsAllowedToBlockForConnectionMultiplier;


    //初始化客户端
    private MongoClient getClient() {
        try {
            if (null != mongoClient) {
                return mongoClient;
            }

            //组装mongo服务端地址
            final List<ServerAddress> addressLists = new ArrayList<>();
            for (String domain : domains.split(";")) {
                String[] hostAndPort = domain.split(":");
                String host = hostAndPort[0];
                String port = hostAndPort[1];
                ServerAddress serverAddress = new ServerAddress(host, Integer.parseInt(port));
                addressLists.add(serverAddress);
            }

            MongoClientOptions.Builder options = new MongoClientOptions.Builder();

            options.connectionsPerHost(connectionsPerHost);// 连接池设置为300个连接,默认为100
            options.connectTimeout(connectTimeout);// 连接超时，推荐>3000毫秒
            options.maxWaitTime(maxWaitTime); //
            options.socketTimeout(socketTimeout);// 套接字超时时间，0无限制
            options.threadsAllowedToBlockForConnectionMultiplier(threadsAllowedToBlockForConnectionMultiplier);// 线程队列数，如果连接线程排满了队列就会抛出“Out of semaphores to get db”错误。
            options.writeConcern(WriteConcern.SAFE);//

            final MongoClientOptions op = options.build();
            //组装权限对象
            final List<MongoCredential> credentialsLists = new ArrayList<>();
            if (StringUtils.isNotBlank(user) && StringUtils.isNotBlank(password)) {
                MongoCredential credential = MongoCredential.createCredential(user, database, password.toCharArray());
                credentialsLists.add(credential);
            }

            if (CollectionUtils.isEmpty(credentialsLists)) {
                //创建客户端
                mongoClient = new MongoClient(addressLists, op);
            } else {
                //创建客户端
                mongoClient = new MongoClient(addressLists, credentialsLists, op);
            }


        } catch (NumberFormatException e) {
            logger.error("MongoDB init error", e);
        }
        return mongoClient;
    }

    public void init() {
        MongoKit.INSTANCE.init(getClient(), database);
    }

    /**
     * 暴露mongoClient
     */
    public MongoClient getMongoClient() {
        return mongoClient;
    }

    /**----------------------------------------------------------CRUD-------------------------------------------------------------*/
    /**-------------------------新增-----------------------------*/

    /**
     * 新增一条记录
     *
     * @param collName
     * @param bean
     */
    public void insert(String collName, Object bean) {
        MongoKit.INSTANCE.insert(collName, MongoDocumentKit.toDocument(bean));
    }

    /**
     * 新增多条记录
     *
     * @param collName
     * @param list
     */
    public void insert(String collName, List<Object> list) {
        if (com.alibaba.dubbo.common.utils.CollectionUtils.isNotEmpty(list) && StringUtils.isNotEmpty(collName)) {
            List<Document> docList = new ArrayList<>();
            for (Object bean : list) {
                docList.add(MongoDocumentKit.toDocument(bean));
            }
            MongoKit.INSTANCE.insert(collName, docList, new InsertManyOptions());
        }
    }

    /**-------------------------查询-----------------------------*/
    /**
     * 查询集合所有文档
     *
     * @param collName
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> List<T> findAll(String collName, Class<T> clazz) {
        return MongoKit.INSTANCE.find(collName, 0, new BsonDocument(), new BsonDocument(), clazz);
    }

    /**
     * 根据条件查询
     *
     * @param
     * @return
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     */
    public <T> List<T> find(String collName, Bson filter, Class<T> clazz) {
        Bson query = filter;
        if (filter == null) {
            query = new BsonDocument();
        }
        return MongoKit.INSTANCE.find(collName, query, new BsonDocument(), new BsonDocument(), clazz);
    }

    /**
     * 条件查询
     * 返回JSON对象
     * @param collName
     * @param filter
     * @return
     */
    public List<JSONObject> find(String collName, Bson filter) {
        Bson query = filter;
        if (filter == null) {
            query = new BsonDocument();
        }
        return MongoKit.INSTANCE.find(collName, query, new BsonDocument());
    }

    /**
     * 条件排序查询 ,返回JSON对象
     * @param collName
     * @param filter
     * @param sortColumn
     * @param isDesc
     * @return
     */
    public List<JSONObject> find(String collName, Bson filter, String sortColumn, boolean isDesc) {
        Bson query = filter;
        int desc = isDesc ? -1 : 1;
        if (filter == null) {
            query = new BsonDocument();
        }
        return MongoKit.INSTANCE.find(collName, query, new BasicDBObject(sortColumn, desc), new BsonDocument());


    }

    /**
     * 条件排序查询
     * @param collName
     * @param filter
     * @param sortColumn
     * @param isDesc true 降序 ， false 升序
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> List<T> find(String collName, Bson filter, String sortColumn, boolean isDesc, Class<T> clazz) {
        Bson query = filter;
        int desc = isDesc ? -1 : 1;
        if (filter == null) {
            query = new BsonDocument();
        }
        return MongoKit.INSTANCE.find(collName, query, new BasicDBObject(sortColumn, desc), new BsonDocument(), clazz);
    }


    /**
     * 条件排序查询，取前n条数据
     * @param collName
     * @param filter
     * @param sortColumn
     * @param isDesc
     * @param limit
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> List<T> find(String collName, Bson filter, String sortColumn, boolean isDesc, Integer limit,  Class<T> clazz) {
        Bson query = filter;
        int desc = isDesc ? -1 : 1;
        if (filter == null) {
            query = new BsonDocument();
        }
        int limitNum = (limit==null ? 0 : limit);
        return MongoKit.INSTANCE.find(collName , query, new BasicDBObject(sortColumn, desc), new BsonDocument() , limitNum,  clazz);
    }


    /**
     * 分页查询 pageNo 从 1 开始
     * @param collName
     * @param filter
     * @param pageNo
     * @param pageSize
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> List<T> findByPage(String collName, Bson filter, int pageNo, int pageSize , Class<T> clazz) {
        Bson orderBy = new BasicDBObject("_id", 1);
        int skip = (pageNo - 1) * pageSize;
        int limit = pageSize;
        Bson query = filter;
        if (filter == null) {
            query = new BsonDocument();
        }
        return MongoKit.INSTANCE.find(collName, query, orderBy, limit, skip, new BsonDocument() , clazz);
    }

    /**
     *  查询，跳过skip 查询limit条数据
     * @param collName
     * @param filter
     * @param skip
     * @param limit
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> List<T> findBySkipAndLimit(String collName, Bson filter, int skip, int limit , Class<T> clazz) {
        Bson orderBy = new BasicDBObject("_id", 1);
        Bson query = filter;
        if (filter == null) {
            query = new BsonDocument();
        }
        return MongoKit.INSTANCE.find(collName, query, orderBy, limit, skip, new BsonDocument() , clazz);
    }

    /**
     * 分页查询 , 按字段排序 , pageNo 从 1 开始
     * @param collName
     * @param filter
     * @param sortColumn
     * @param isDesc
     * @param pageNo
     * @param pageSize
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> List<T> findByPage(String collName, Bson filter, String sortColumn, boolean isDesc ,
                                  int pageNo, int pageSize , Class<T> clazz) {
        int desc = isDesc ? -1 : 1;
        int skip = (pageNo - 1) * pageSize;
        int limit = pageSize;
        Bson query = filter;
        if (filter == null) {
            query = new BsonDocument();
        }
        return MongoKit.INSTANCE.find(collName, query, new BasicDBObject(sortColumn, desc),
                limit, skip, new BsonDocument() , clazz);
    }


    /**
     * 万金油查询
     * @param collName
     * @param filter
     * @param sortColumn
     * @param isDesc
     * @param pageNo
     * @param pageSize
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> List<T> find(String collName, Bson filter,Bson project ,  String sortColumn, boolean isDesc ,
                            int pageNo, int pageSize , String join , Class<T> clazz) {
        int desc = isDesc ? -1 : 1;
        int skip = (pageNo - 1) * pageSize;
        int limit = pageSize;
        Bson query = filter;
        if (filter == null) {
            query = new BsonDocument();
        }
        return MongoKit.INSTANCE.find(collName, query, new BasicDBObject(sortColumn, desc),project,
                limit, skip,  join,  clazz);
    }

    /**
     * 按条件查询数量
     * @param collName
     * @param filter
     * @return
     */
    public Long count(String collName, Bson filter){
        return MongoKit.INSTANCE.count(collName , filter);
    }

    /**
     * 更新一条记录(全部覆盖)
     *
     * @param collName 表名
     * @param bson     条件
     * @param doc      更新内容
     */
    public long updateData(String collName, Bson bson, Document doc) {
        return MongoKit.INSTANCE.replaceOne(collName, bson, doc);
    }

    /**
     * 更新一条记录(更新对应列)
     *
     * @param collName
     * @param bson
     * @param doc
     * @return
     */
    public long updateField(String collName, Bson bson, Document doc) {
        return MongoKit.INSTANCE.update(collName, bson, new Document("$set", doc));
    }

    /**
     * 更新多条条记录(更新对应列)
     *
     * @param collName
     * @param bson
     * @param doc
     * @return
     */
    public long updateFieldMutil(String collName, Bson bson, Document doc) {
        return MongoKit.INSTANCE.update(collName, bson, new Document("$set", doc) , true);
    }



    /**
     * 删除一条数据
     * @param collName
     * @param bson
     * @return
     */
    public long deleteOne(String collName, Bson bson) {
        return MongoKit.INSTANCE.deleteOne(collName, bson);
    }

    /**
     * 删除多条
     * @param collName
     * @param bson
     * @return
     */
    public long deleteMany(String collName, Bson bson) {
        return MongoKit.INSTANCE.delete(collName, bson);
    }


    public  <T> List<T> aggregate(String collName, List<Bson> query ,  Class<T> document) {
        return MongoKit.INSTANCE.aggregateDoNotDealID(collName, query,true,document);
    }

    public  List<JSONObject>  aggregate(String collName, List<Bson> query ) {
        return MongoKit.INSTANCE.aggregateDoNotDealID(collName, query,true);
    }




    public static void main(String[] args) {

        MongoUtil db = new MongoUtil();
        db.setDomains("116.196.100.123:27017");
//        db.setUser("root");
//        db.setPassword("root");
        db.setDatabase("my_test");
        db.setConnectionsPerHost(100);
        db.setConnectTimeout(15000);
        db.setMaxWaitTime(10000);
        db.setSocketTimeout(0);
        db.setThreadsAllowedToBlockForConnectionMultiplier(5);

        try {
            db.init();


        } catch (Exception e) {
            e.printStackTrace();
        }

        //插入 一条记录
        List<String> valueList = new ArrayList<String>();
        valueList.add("asdf1");
        valueList.add("asdf2");
        valueList.add("asdf3");
        valueList.add("asdf4");
        valueList.add("asdf5");
        valueList.add("asdf6");
        valueList.add("asdf7");
        db.insert("testDB", new TestBean(23131, new Date(), valueList));


        List<TestBean> testDB = db.findAll("testDB", TestBean.class);

        List<String> values = testDB.get(0).getValues();
        for (String s : values){
            System.out.println(s);
        }

        System.out.println("=======================");

        testDB.forEach(e -> {
            System.out.println(e.getMem());
            e.getValues().forEach(System.out::println);
        });

        //=====================================================================================
        /*MongoClient mongoClient = new MongoClient( "116.196.100.123" , 27017 );

        // 连接到数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("my_test");
        System.out.println("Connect to database successfully");*/

        //==================================================================================


    }

    public static class TestBean extends MongoBean implements Serializable {

        private static final long serialVersionUID = 7923432049819775450L;

        private int mem;
        private Date date;
        private List<String> values;

        public TestBean() {
        }

        public TestBean(int mem, Date date, List<String> values) {
            this.mem = mem;
            this.date = date;
            this.values = values;
        }

        public int getMem() {
            return mem;
        }

        public void setMem(int mem) {
            this.mem = mem;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public List<String> getValues() {
            return values;
        }

        public void setValues(List<String> values) {
            this.values = values;
        }
    }


    public String getDomains() {
        return domains;
    }

    public void setDomains(String domains) {
        this.domains = domains;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public int getConnectionsPerHost() {
        return connectionsPerHost;
    }

    public void setConnectionsPerHost(int connectionsPerHost) {
        this.connectionsPerHost = connectionsPerHost;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getMaxWaitTime() {
        return maxWaitTime;
    }

    public void setMaxWaitTime(int maxWaitTime) {
        this.maxWaitTime = maxWaitTime;
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public int getThreadsAllowedToBlockForConnectionMultiplier() {
        return threadsAllowedToBlockForConnectionMultiplier;
    }

    public void setThreadsAllowedToBlockForConnectionMultiplier(int threadsAllowedToBlockForConnectionMultiplier) {
        this.threadsAllowedToBlockForConnectionMultiplier = threadsAllowedToBlockForConnectionMultiplier;
    }
}
