package com.nuist.test;

import com.mysql.jdbc.ConnectionFeatureNotAvailableException;
import com.nuist.bean.ConvertDepartment;
import com.nuist.bean.Department;
import com.nuist.bean.DepartmentResponse;
import com.nuist.bean.Response;
import com.nuist.service.impl.DepartmentServiceImpl;
import com.nuist.service.impl.UserServiceImpl;
import com.nuist.utils.Dept.InsertDept;
import com.nuist.utils.JDBCUtils;
import com.nuist.utils.LogUtil;
import com.nuist.utils.SelectListUtils;
import com.nuist.utils.UpdateUtils;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static com.nuist.utils.Dept.InsertDept.conn;

public class TestJunit {

    public static String corpId = "wwcb74a7ccfe39ba43";

    public static String corpSecret = "nFmuUSCFI4I6NJf1PCdkS-U9H-bimRv6DcX9vxsWjLY";

    //    public static String corpId = "wwe591ec819d17f6a6";
//    public static String corpSecret = "pU0pXNLM-Ax9DGB0T8jmdpCpc2SJBMFyulYnrl2EMIo";

    public static UserServiceImpl userService = new UserServiceImpl();
    public static DepartmentServiceImpl departmentService = new DepartmentServiceImpl();

    public static String access_token = userService.getAccess_tokenService(corpId,corpSecret).getAccess_token();

    @Test
    public void testAPI(){

        //读取成员
        /*Response response = userService.selectService(access_token, "201813410001");
        if (response.getErrcode().equals("0")){
            System.out.println(response.toString());
        }*/

        //读取部门
        /*DepartmentResponse departmentResponse = departmentService.selectService(access_token, "");
        for (int i = 0;i < departmentResponse.getDepartment().size();i++){
            System.out.println(departmentResponse.getDepartment().get(i).toString());
        }*/

        //
    }

    @Test
    public void testDeptInsert() throws Exception {

        InsertDept.insert(access_token,"druidnew.properties");
//        DepartmentResponse departmentResponse = departmentService.selectService(access_token, "150020609");
//        List<ConvertDepartment> department = departmentResponse.getDepartment();
//        for (int i = 1;i < department.size();i++){
//            if ((department.get(i).getParentid().equals("150020609"))){
//                Response response = departmentService.deleteService(access_token, department.get(i).getId());
//                LogUtil.Log("删除部门" + response.toString());
//            }
//        }

        /*DepartmentResponse departmentResponse = departmentService.selectService(access_token, "5");
        List<ConvertDepartment> department = departmentResponse.getDepartment();
        for (int i = 0; i < department.size();i++){
            System.out.println(department.get(i).toString());
        }*/
    }

    @Test
    public void testMysql(){
        UpdateUtils.update("druid.properties","insert into party values (?,?,?,?,?,?,?,?)","ceshi","ceshi","ceshi","ceshi","ceshi","ceshi","ceshi","ceshi");
    }

    @Test
    public void testMysql2() throws Exception {
        String sqlDepartment = "select * from party";
        conn = JDBCUtils.getConnection("druidnew.properties");
        Connection conn1 = JDBCUtils.getConnection("druid.properties");
        List<Department> departments = SelectListUtils.getList(conn, sqlDepartment, Department.class);

        String sql1 = "insert into party values \n";
        PreparedStatement prepareStatement = null;
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
            if (( (i + 1) % 50 ) == 0){
                prepareStatement = conn1.prepareStatement(sql1);
                prepareStatement.execute();

                System.out.println(sql1);
                sql1 = "insert into party values \n";
            }
            if (i == (departments.size() - 1)){
                prepareStatement.close();
                conn.close();
                conn1.close();
            }
        }




    }
}
