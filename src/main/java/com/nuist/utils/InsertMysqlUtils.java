package com.nuist.utils;

import com.nuist.bean.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static com.nuist.utils.Dept.InsertDept.conn;

public class InsertMysqlUtils {

    public static void insertDepts(List<Department> departments) throws Exception {

        String sqlDepartment = "select * from party";
        conn = JDBCUtils.getConnection("druidnew.properties");
        Connection conn1 = JDBCUtils.getConnection("druid.properties");

        String sql1 = "insert into party values \n";
        PreparedStatement prepareStatement = null;
        if (departments.size()!=0){
            for (int i = 0;i < departments.size();i++){
                System.out.println(departments.size());
                if (i == (departments.size() - 1) || ( (i + 1) % 50 ) == 0 ){
                    sql1 += "('" + departments.get(i).getDm() + "','"+ departments.get(i).getMc()
                            + "','"+ departments.get(i).getFjjg() + "','"+ departments.get(i).getJglx()
                            +"','"+ departments.get(i).getQyzt() + "','" + departments.get(i).getCC()
                            + "','" + departments.get(i).getPX() + "','" + departments.get(i).getJC() + "');";
                }else {
                    sql1 += "('" + departments.get(i).getDm() + "','"+ departments.get(i).getMc()
                            + "','"+ departments.get(i).getFjjg() + "','"+ departments.get(i).getJglx()
                            +"','"+ departments.get(i).getQyzt() + "','" + departments.get(i).getCC()
                            + "','" + departments.get(i).getPX() + "','" + departments.get(i).getJC() + "'),\n";
                }
                boolean execute = false;
                if (( (i + 1) % 50 ) == 0){
                    prepareStatement = conn1.prepareStatement(sql1);
                    execute = prepareStatement.execute();

                    System.out.println(sql1);
                    sql1 = "insert into party values \n";
                }
                if (i == (departments.size() - 1)){
                    if (execute){
                        prepareStatement.close();
                    }

                    conn.close();
                    conn1.close();
                }
            }
        }

    }
}
