package com.yl.study.example1.spring.transaction;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author DELL
 * @date 2017/10/31
 */
public class SingleThreadConnectionHolder {

    private static ThreadLocal<ConnectionHolder> threadLocal = new ThreadLocal<>();


    private static ConnectionHolder getConnectionHolder() {
        ConnectionHolder connectionHolder = threadLocal.get();
        if (connectionHolder == null) {
            connectionHolder = new ConnectionHolder();
            threadLocal.set(connectionHolder);
        }
        return connectionHolder;
    }

    public static Connection getConnection(DataSource dataSource) throws SQLException {
        ConnectionHolder connectionHolder = getConnectionHolder();
        return connectionHolder.getConnectionByDataSource(dataSource);
    }


}
