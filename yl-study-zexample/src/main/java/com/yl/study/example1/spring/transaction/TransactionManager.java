package com.yl.study.example1.spring.transaction;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author DELL
 * @date 2017/10/31
 */
public class TransactionManager {

    private DataSource dataSource;

    public TransactionManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Connection getConnection() throws SQLException {
        return SingleThreadConnectionHolder.getConnection(dataSource);
    }

    /**
     * 开启事务
     *
     * @throws SQLException
     */
    public void start() throws SQLException {
        Connection connection = getConnection();
        connection.setAutoCommit(false);
    }

    /**
     * 回滚事务
     *
     * @throws SQLException
     */
    public void rollback() {
        try {
            Connection connection = getConnection();
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭事务
     *
     * @throws SQLException
     */
    public void close() throws SQLException {
        Connection connection = getConnection();
        connection.setAutoCommit(false);
        connection.close();
    }

}
