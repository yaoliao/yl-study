package com.yl.study.event.rocketmq.config;

import javax.sql.DataSource;

/**
 * @author DELL
 * @date 2017/10/30
 */
public class EventManagementConfig {

    /**
     * 服务名
     */
    public static String APPLICATION_NAME;

    /**
     * rocketMQ配置  注入
     */
    public static String ROCKETMQ_NAMESVR_ADDRESS;

    /**
     * 数据源配置  注入
     */
    public static DataSource DATASOURCE;

    /**
     * 允许的消息重试次数
     */
    public static String RECONSUMETIMES;

    public String getRocketMQNameSvrAddress() {
        return ROCKETMQ_NAMESVR_ADDRESS;
    }

    public void setRocketMQNameSvrAddress(String rocketMQNameSvrAddress) {
        ROCKETMQ_NAMESVR_ADDRESS = rocketMQNameSvrAddress;
    }

    public DataSource getDataSource() {
        return DATASOURCE;
    }

    public void setDataSource(DataSource dataSource) {
        DATASOURCE = dataSource;
    }

    public String getApplicationName() {
        return APPLICATION_NAME;
    }

    public void setApplicationName(String applicationName) {
        APPLICATION_NAME = applicationName;
    }

    public String getRECONSUMETIMES() {
        return RECONSUMETIMES;
    }

    public void setReconsumeTimes(String RECONSUMETIMES) {
        EventManagementConfig.RECONSUMETIMES = RECONSUMETIMES;
    }
}
