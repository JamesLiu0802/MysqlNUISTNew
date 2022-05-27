package com.nuist.utils;

import com.nuist.bean.ConvertDepartment;
import com.nuist.bean.ConvertUser;
import com.nuist.bean.Department;
import com.nuist.bean.User;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ConvertUtils {

    static Connection conn;

    public static List<ConvertUser> convertUsers(String properties, List<User> users) throws Exception {

        conn = JDBCUtils.getConnection(properties);
        String sqlDept = "select * from party";
        String sqlStuDept = "select * from stuparty";

        //存储转换后的用户数据并作为方法返回
        List<ConvertUser> convertUsers = new ArrayList<>();

        List<Department> depts = SelectListUtils.getList(conn, sqlDept, Department.class);
        List<Department> stuDepts = SelectListUtils.getList(conn, sqlStuDept, Department.class);

        //将教职工部门和学生部门放在同一个List
        for (int i = 0;i < stuDepts.size();i++){
            depts.add(stuDepts.get(i));
        }

        //将数据库用户数据转换成可插入企业微信后台的用户数据
        for (int i = 0;i < users.size();i++){
            //1、新建接收转换数据的用户和接收部门id的List
            ConvertUser convertUser = new ConvertUser();
            List<String> departments = new ArrayList<>();

            //2、为convertUser对象进行赋值
            convertUser.setUserid(users.get(i).getUserid());
            convertUser.setName(users.get(i).getName());
//            convertUser.setMobile(users.get(i).getMobile());

            //获取department
            switch (users.get(i).getRyfl()){
                case "教职工" :
                    departments.add(UsertypeConvertUtils.getStuffingDepartmentId(
                            depts, users.get(i).getDepartment(),users.get(i).getRoomname()));
                    break;
                case "学生" :
                    departments.add(UsertypeConvertUtils.getStuDepartmentId(
                            depts, users.get(i).getDepartment(),users.get(i).getRoomname()));
                    break;
                case "校友" :
                    departments.add(UsertypeConvertUtils.getGraduateDepartmentId(
                            depts, users.get(i).getDepartment(),users.get(i).getRoomname()));
                    break;
                case "离退休人员" :
                    departments.add(UsertypeConvertUtils.getRetireDepartmentId(
                            depts, users.get(i).getDepartment(),users.get(i).getRoomname()));
                    break;
                case "临时工" :
                    departments.add(UsertypeConvertUtils.getTempDepartmentId(
                            depts, users.get(i).getDepartment(),users.get(i).getRoomname()));
                    break;
                case "兼职教职工" :
                    departments.add(UsertypeConvertUtils.getPartDepartmentId(
                            depts, users.get(i).getDepartment(),users.get(i).getRoomname()));
                    break;
                case "部门招聘人员" :
                    departments.add(UsertypeConvertUtils.getRecruitDepartmentId(
                            depts, users.get(i).getDepartment(),users.get(i).getRoomname()));
                    break;
            }
            convertUser.setDepartment(departments);
            convertUser.setPosition(users.get(i).getPosition());
            convertUser.setGender(users.get(i).getGender());
            convertUser.setEmail(users.get(i).getEmail().split("@")[0] + "@ddd.com");
            convertUsers.add(convertUser);
        }
        return convertUsers;
    }
    public static List<ConvertDepartment> convertDepts(String properties, List<Department> departments) throws Exception {


        return null;
    }
}
