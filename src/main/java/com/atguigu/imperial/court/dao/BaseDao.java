package com.atguigu.imperial.court.dao;

import com.atguigu.imperial.court.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BaseDao<T> {

    private QueryRunner runner = new QueryRunner();

    /***
     * @param sql:
     * @param entityClass:
     * @param parameters:
     * @return List<T>
     * @description 查詢多個物件的方法
     */
    public List<T> getBeanList(String sql, Class<T> entityClass, Object ... parameters) {
        try {
            // 获取数据库连接
            Connection connection = JDBCUtils.getConnection();

            return runner.query(connection, sql, new BeanListHandler<>(entityClass), parameters);

        } catch (SQLException e) {
            new RuntimeException(e);
        }
        return null;
    }
    /***
     * @param sql:
     * @param entityClass:
     * @param parameters:
     * @return T
     * @description 查詢單一物件方法
     */
    public T getSingleBean(String sql, Class<T> entityClass, Object... parameters) {
        try {
            // 获取数据库连接
            Connection connection = JDBCUtils.getConnection();

            return runner.query(connection, sql, new BeanHandler<>(entityClass), parameters);

        } catch (SQLException e) {
            new RuntimeException(e);
        }

        return null;
    }

    /***
     * @param sql:
     * @param parameters:
     * @return int
     * @description c u d方法
     */
    public int update(String sql, Object ... parameters) {

        try {
            Connection connection = JDBCUtils.getConnection();

            int affectedRowNumbers = runner.update(connection, sql, parameters);

            return affectedRowNumbers;
        } catch (SQLException e) {
            new RuntimeException(e);

            return 0;
        }

    }

}
