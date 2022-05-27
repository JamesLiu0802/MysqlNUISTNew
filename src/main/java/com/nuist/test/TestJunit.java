package com.nuist.test;

import com.nuist.bean.DepartmentResponse;
import com.nuist.bean.Response;
import com.nuist.service.impl.DepartmentServiceImpl;
import com.nuist.service.impl.UserServiceImpl;
import org.testng.annotations.Test;

public class TestJunit {

    public static String corpId = "wwe591ec819d17f6a6";
    public static String corpSecret = "pU0pXNLM-Ax9DGB0T8jmdpCpc2SJBMFyulYnrl2EMIo";

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
}
