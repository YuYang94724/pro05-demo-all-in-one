package com.atguigu.imperial.court.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {

    private static DataSource dataSource;

    //保證唯一性
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

    static {
        try {
            ClassLoader classLoader = JDBCUtils.class.getClassLoader();

            InputStream s = classLoader.getResourceAsStream("jdbc.properties");

            Properties properties = new Properties();
            properties.load(s);

            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //獲取connection
    public static Connection getConnection() {
        //嘗試檢查當前thread是否存在已綁定的connection
        Connection connection = threadLocal.get();
        try {

            //檢查
            if (connection == null) {
                connection = dataSource.getConnection();
                //將新的connection放入
                threadLocal.set(connection);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    //釋放關閉connection
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                //關閉數據庫
                connection.close();
                //將當咭數據庫連接從當前thread移除
                threadLocal.remove();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


}

