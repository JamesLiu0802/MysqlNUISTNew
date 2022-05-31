package com.nuist.utils;

import com.nuist.bean.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

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

    public static void updateAll(String properties, List<Department> departments){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //1.获取数据库的连接
            conn = JDBCUtils.getConnection(properties);
            String sql = "insert into party values (?,?,?,?,?,?,?,?)";
            //2.获取PreparedStatement的实例 (或：预编译sql语句)
            ps = conn.prepareStatement(sql);
            //3.填充占位符
            for(int i = 0;i < departments.size();i++){
                ps.setString(1, departments.get(i).getDm());
                ps.setString(2, departments.get(i).getMc());
                ps.setString(3, departments.get(i).getFjjg());
                ps.setString(4, departments.get(i).getJglx());
                ps.setString(5, departments.get(i).getQyzt());
                ps.setString(6, departments.get(i).getCC());
                ps.setString(7, departments.get(i).getPX());
                ps.setString(8, departments.get(i).getJC());
                //每满500条数据执行一次更新
                ps.addBatch();
                if (i % 50 == 0) {
                    // 执行批量更新
                    ps.executeBatch();
                    // 清空执行过的sql
                    ps.clearBatch();
                }
            }
//            //4.执行sql语句
//            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            //5.关闭资源
            JDBCUtils.closeResource(conn, ps);
        }
    }
}
