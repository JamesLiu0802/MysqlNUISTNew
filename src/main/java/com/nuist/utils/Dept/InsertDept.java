package com.nuist.utils.Dept;

import com.mysql.jdbc.log.Log;
import com.nuist.bean.*;
import com.nuist.utils.JDBCUtils;
import com.nuist.utils.LogUtil;
import com.nuist.utils.SelectListUtils;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static com.nuist.test.TestJunit.departmentService;

public class InsertDept {

    public static Connection conn;

    public static void insert(String access_token, String properties) throws Exception {

        LogUtil.Log("教职工部门开始");

        conn = JDBCUtils.getConnection(properties);
        String sqlDepartment = "select * from party";
        String sqlUser = "select * from users";

        //从数据库获取部门数据并转换成可传入企业微信后台的Bean对象
        List<Department> departments = SelectListUtils.getList(conn, sqlDepartment, Department.class);
        List<User> users = SelectListUtils.getList(conn, sqlUser, User.class);


        List<Department> departments1 = SelectListUtils.getList(conn, sqlDepartment, Department.class);
//        List<Department> departments = new ArrayList<>();
        List<User> users1 = new ArrayList<>();

        String parentId = "";


        LogUtil.Log("数据库部门数:" + departments.size());

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getRyfl().equals("教职工")){
                users1.add(users.get(i));
            }
        }
        for (int i = 0; i < departments.size(); i++) {
            if (departments.get(i).getMc().equals("南京信息工程大学")){
                departments.remove(i);
            }
        }

        LogUtil.Log("数据库教职工数量:" + users.size());

        DepartmentResponse departmentResponse = departmentService.selectService(access_token, "");
        List<ConvertDepartment> department = departmentResponse.getDepartment();
        List<ConvertDepartment> departmentFjjg = new ArrayList<>();

        //获取南京信息工程大学的id作为教职工部门的parentid
        boolean flagNuist = false;
        for (int i = 0;i < department.size();i++){
            flagNuist = false;
            if (department.get(i).getName().equals("南京信息工程大学")){
                parentId = department.get(i).getId();
                break;
            }else {
                if ( i == (department.size() - 1 )){
                    flagNuist = true;
                }
            }
            if (flagNuist) {
                ConvertDepartment convertDepartment = new ConvertDepartment();
                convertDepartment.setName("南京信息工程大学");
                convertDepartment.setParentid("1");
                Response response = departmentService.insertService(access_token, convertDepartment);
                LogUtil.Log(response.toString());
                break;
            }
        }

        //获取教职工所在层级的部门
        for(int i = 0;i < department.size();i++){
            if (department.get(i).getName().equals("教职工")){
                departmentFjjg.add(department.get(i));
            }
        }

        //若教职工不存在则创建
        String insertId = "";
        boolean insertFlag = false;

        for (int i = 0;i < departmentFjjg.size();i++){
            if (departmentFjjg.get(i).getName().equals("教职工")){
                for (int j = 0;j < department.size();j++){
                    if (departmentFjjg.get(i).getParentid().equals(department.get(j).getId())){
                        if (department.get(j).getName().equals("南京信息工程大学")){
                            insertId = departmentFjjg.get(i).getId();
                            LogUtil.Log("教职工部门已存在，不需新建。id为：" + insertId);
                            break;
                        } else {
                            if (i == (departmentFjjg.size() - 1)){
                                insertFlag = true;
                            }
                        }
                    }
                }
            }
            if (insertFlag){
                ConvertDepartment convertDepartment = new ConvertDepartment();
                convertDepartment.setName("教职工");
                convertDepartment.setParentid(parentId);
                Response response = departmentService.insertService(access_token, convertDepartment);
                if (response.getErrcode().equals("0")){
                    insertId = response.getId();
                    LogUtil.Log("教职工部门不存在，新建完成。id为：" + insertId);
                }else {
                    LogUtil.Log("教职工部门不存在，新建失败！");
                    LogUtil.Log(response.toString());
                }
            }
        }

        //从企业微信后台获取数据已经添加的部门列表
        departmentResponse = departmentService.selectService(access_token, insertId);
        department = departmentResponse.getDepartment();
        //若父部门id为100000000则将获取到的id作为教职工二级部门id
        for (int i = 0; i < department.size(); i++) {
            for (int j = 0; j < departments.size(); j++) {
                if (!(departments.get(j).getFjjg().equals("100000000"))){
                    for (int k = 0;k < departments.size();k++){
                        if (departments.get(k).getMc().length() <= 32){
                            if ((departments.get(j).getFjjg().equals(departments.get(k).getDm())) &&
                                    department.get(i).getName().equals(departments.get(k).getMc())){
                                departments.get(j).setFjjg(department.get(i).getId());
                                break;
                            }
                        } else {
                            if ((departments.get(j).getFjjg().equals(departments.get(k).getDm())) &&
                                    department.get(i).getName().equals(departments.get(k).getMc().substring(0,31))){
//                                departments.get(j).setMc(departments.get(j).getJC());
                                departments.get(j).setFjjg(department.get(i).getId());
                            }
                        }
                    }
                }else {
//                    if (departments.get(j).getMc().length() > 32){
//                        departments.get(j).setMc(departments.get(j).getJC());
//                    }
                    departments.get(j).setFjjg(insertId);
                }
            }
        }


        //flag判断是否新建与department相同的部门
        //新建教职工二级部门
        boolean flag1 = false;
        for (int i = 0; i < departments.size(); i++) {
            flag1 = false;
            for (int j = 0; j < department.size(); j++) {
                if (departments.get(i).getFjjg().equals(insertId)){
                    ConvertDepartment department1 = new ConvertDepartment();
                    if(departments.get(i).getMc().length() <= 32){
                        if (department.get(j).getName().equals(departments.get(i).getMc())){
                            break;
                        }else {
                            if (j == (department.size() - 1)){
                                flag1 = true;
                            }
                        }
                        if (flag1){
                            department1.setName(departments.get(i).getMc());
                            department1.setParentid(departments.get(i).getFjjg());
                            DeptCRUD.insert(access_token,department1,departments1,insertId, parentId);
//                            DepartmentUtils.insert(access_token,department1,departments);
                        }
                    }else {
                        if (department.get(j).getName().equals(departments.get(i).getMc().substring(0,31))){
                            break;
                        }else {
                            if (j == (department.size() - 1)){
                                flag1 = true;
                            }
                        }
                        if (flag1){
                            department1.setName(departments.get(i).getMc().substring(0,31));
                            department1.setParentid(departments.get(i).getFjjg());
                            DeptCRUD.insert(access_token, department1, departments1, insertId, parentId);
//                            DepartmentUtils.insert(access_token,department1,departments);
                        }
                    }

                }
            }
        }


        departmentResponse = departmentService.selectService(access_token, insertId);
        department = departmentResponse.getDepartment();

        //从企业微信后台获取教职工二级部门id，若部门id且name相同，则将其作为教职工三级部门的parentid
        for (int i = 0; i < department.size(); i++) {
            for (int j = 0; j < departments.size(); j++) {
                if (!(departments.get(j).getFjjg().equals(parentId))){
                    for (int k = 0;k < departments.size();k++){
                        if (departments.get(k).getMc().length() <= 32){
                            if ((departments.get(j).getFjjg().equals(departments.get(k).getDm())) && department.get(i).getName().equals(departments.get(k).getMc())){
                                departments.get(j).setFjjg(department.get(i).getId());
                            }
                        } else {
                            if ((departments.get(j).getFjjg().equals(departments.get(k).getDm())) && department.get(i).getName().equals(departments.get(k).getMc().substring(0,31))){
                                departments.get(j).setMc(departments.get(j).getJC());
                                departments.get(j).setFjjg(department.get(i).getId());
                            }
                        }
                    }
                }else {
//                    if (departments.get(j).getMc().length() > 32){
//                        departments.get(j).setMc(departments.get(j).getJC());
//                    }
//                    departments.get(j).setFjjg(department.get(0).getId());
                }
            }
        }

        //新建教职工三级部门
        boolean flag = false;
        for (int i = 0; i < departments.size(); i++) {
            flag = false;
            for (int j = 0; j < department.size(); j++) {
                ConvertDepartment department1 = new ConvertDepartment();
                if(departments.get(i).getMc().length() <= 32){
                    if (department.get(j).getName().equals(departments.get(i).getMc())){
                        break;
                    }else {
                        if (j == (department.size() - 1)){
                            flag = true;
                        }
                    }
                    if (flag){
                        department1.setName(departments.get(i).getMc());
                        department1.setParentid(departments.get(i).getFjjg());
                        DeptCRUD.insert(access_token,department1,departments1, insertId, parentId);
                        break;
//                    DepartmentUtils.insert(access_token,department1,departments);
                    }
                }else {
                    if (department.get(j).getName().equals(departments.get(i).getMc().substring(0,31))){
                        break;
                    }else {
                        if (j == (department.size() - 1)){
                            flag = true;
                        }
                    }
                    if (flag){
                        department1.setName(departments.get(i).getMc().substring(0,31));
                        department1.setParentid(departments.get(i).getFjjg());
                        DeptCRUD.insert(access_token, department1, departments1, insertId, parentId);
                        break;
//                    DepartmentUtils.insert(access_token,department1,departments);
                    }
                }


            }
        }

        conn.close();
    }
}
