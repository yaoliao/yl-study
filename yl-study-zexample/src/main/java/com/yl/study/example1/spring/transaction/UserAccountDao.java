package com.yl.study.example1.spring.transaction;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author DELL
 * @date 2017/10/31
 */
public class UserAccountDao {

    private DataSource dataSource;

    public UserAccountDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void buy() throws SQLException {

        Connection connection = SingleThreadConnectionHolder.getConnection(dataSource);

        //进行业务操作
        //.......

        System.out.println("thread : " + Thread.currentThread().getName() + "  使用管道(hashCode) : " + connection.hashCode());

    }

}
