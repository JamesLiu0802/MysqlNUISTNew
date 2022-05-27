package com.nuist.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UpdateUtils {

    //通用的增、删、改操作（体现一：增、删、改 ； 体现二：针对于不同的表）
    public static void update(String properties, String sql, Object ... args){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //1.获取数据库的连接
            conn = JDBCUtils.getConnection(properties);
            //2.获取PreparedStatement的实例 (或：预编译sql语句)
            ps = conn.prepareStatement(sql);
            //3.填充占位符
            for(int i = 0;i < args.length;i++){
                ps.setObject(i + 1, args[i]);
            }
            //4.执行sql语句
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            //5.关闭资源
            JDBCUtils.closeResource(conn, ps);
        }
    }
}
