package com.nuist.utils;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SelectListUtils {

    public  static <T> List<T> getList(Connection conn, String sql, Class<T> clazz) throws SQLException {

        QueryRunner runner = new QueryRunner();
        BeanListHandler<T> handler = new BeanListHandler<>(clazz);
        List<T> query = runner.query(conn, sql, handler);
        return query;
    }
}
