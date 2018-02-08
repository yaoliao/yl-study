package com.yl.study.example1.spring.transaction;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author DELL
 * @date 2017/10/31
 */
public class UserService {

    private UserAccountDao userAccountDao;

    private UserOrderDao userOrderDao;

    private TransactionManager transactionManager;

    public UserService(DataSource dataSource) {
        this.userAccountDao = new UserAccountDao(dataSource);
        this.userOrderDao = new UserOrderDao(dataSource);
        this.transactionManager = new TransactionManager(dataSource);
    }

    public void action() {

        try {

            transactionManager.start();
            userAccountDao.buy();
            userOrderDao.order();
            transactionManager.close();

        } catch (SQLException e) {
            e.printStackTrace();
            transactionManager.rollback();
        }

    }

}
