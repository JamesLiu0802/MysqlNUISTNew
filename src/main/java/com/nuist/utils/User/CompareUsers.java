package com.nuist.utils.User;

import com.nuist.bean.*;
import com.nuist.utils.ConvertUtils;

import java.util.ArrayList;
import java.util.List;

import static com.nuist.test.TestJunit.*;

public class CompareUsers {

    public static void compare(List<User> users) throws Exception {

        DepartmentResponse departmentResponse = departmentService.selectService(access_token, "");
        List<User> userlist = new ArrayList<>();

        //初步转换后的ConvertUser类型的List（需要与企业微信后台的部门id同步）
        List<ConvertUser> convertUsers = ConvertUtils.convertUsers("druidnew.properties", users);
        //南京信息工程大学下的所有部门
        List<ConvertDepartment> convertDepartments;
        //从企业微信后台获取南京信息工程大学下的所有用户
        for (int i = 0;i < departmentResponse.getDepartment().size();i++){
            if (departmentResponse.getDepartment().get(i).getName().equals("南京信息工程大学")){

                Response response = userService.selectDeptUsers(access_token, departmentResponse.getDepartment().get(i).getId());
                convertDepartments = departmentService.selectService(
                        access_token, departmentResponse.getDepartment().get(i).getId()).getDepartment();

                for (int j = 0;j < response.getUserlist().size();j++){
                    userlist.add(response.getUserlist().get(j));
                }
            }
        }

        boolean flagUser;
        for (int i = 0;i < users.size();i++){
            flagUser = false;
            for (int j = 0;j < userlist.size();i++){
                if (users.get(i).getUserid().equals(userlist.get(i).getUserid())){
                    break;
                }else {
                    if (j == (userlist.size() - 1)){
                        flagUser = true;
                    }
                }
                if (flagUser){
                }
            }
        }
    }
}
