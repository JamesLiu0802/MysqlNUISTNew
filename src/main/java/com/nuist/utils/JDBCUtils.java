package com.nuist.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.nuist.test.TestJunit;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 功能1：从数据源获取数据库连接
 * 功能2：从数据库获取到数据库连接后，绑定到本地线程（借助 ThreadLocal）
 * 功能3：释放线程时和本地线程解除绑定
 */
public class JDBCUtils {


    public static Connection getConnection(String properties) throws Exception {

        Properties pro = new Properties();
        pro.load(TestJunit.class.getClassLoader().getResourceAsStream(properties));
        DataSource ds = DruidDataSourceFactory.createDataSource(pro);
        return ds.getConnection();
    }

    /**
     *
     * @Description 关闭连接和PreparedStatement的操作
     * @param conn
     * @param ps
     */
    public static void closeResource(Connection conn, PreparedStatement ps){
        try {
            if(ps != null)
                ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
