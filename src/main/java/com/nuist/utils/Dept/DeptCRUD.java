package com.nuist.utils.Dept;

import com.nuist.bean.*;
import com.nuist.utils.*;

import java.util.ArrayList;
import java.util.List;

import static com.nuist.test.TestJunit.departmentService;

public class DeptCRUD {


    public static void insert(String access_token, ConvertDepartment convertDepartment, List<Department> departments1,
                              String insertId,String parentId) throws Exception {
        LogUtil.Log("开始向企业微信后台添加单个部门数据");

        String sqlDepartment = "select * from party";

        //向企业微信后台进行添加数据操作
        Response response = departmentService.insertService(access_token, convertDepartment);
        LogUtil.Log(response.toString());

        //插入成功则将该行数据插入到本地数据库
        if (response.getErrcode().equals("0")) {

//            convertDepartment.setParentid(parentId);

            List<ConvertDepartment> department = departmentService.selectService(access_token, insertId).getDepartment();
            List<Department> departments = new ArrayList<>();

            LogUtil.Log("企业微信后台添加单个部门数据完成");
            String sql = "insert into party values (?,?,?,?,?,?,?,?)";
            for (int i = 0; i < department.size(); i++) {
                boolean flagj = false;
                //1、先判断企业微信后台获取的部门id是否和插入的父部门id是否相同，
                if (department.get(i).getId().equals(convertDepartment.getParentid())){
                    for (int j = 0; j < departments1.size(); j++) {
                        if (departments1.get(j).getMc().length() <= 32){
                            //2、相同则判断当前数据库的部门名是否和插入数据部门相同
                            if (departments1.get(j).getMc().equals(convertDepartment.getName())){
                                for (int k = 0;k < departments1.size();k++){
                                    if (departments1.get(k).getMc().length() <= 32){
                                        //3、若当前数据库的部门名是否和插入数据部门相同，
                                        // 则判断其父部门是否相同，相同则插入数据库
                                        if (departments1.get(k).getDm().equals(departments1.get(j).getFjjg())){
                                            if (convertDepartment.getParentid().equals(insertId)){
                                                departments.add(departments1.get(j));
                                                System.out.println(departments1.get(k).getDm());
                                                System.out.println(departments1.get(j).getFjjg());
                                                flagj = true;
                                                break;
                                            }else {
                                                if (department.get(i).getName().equals(departments1.get(k).getMc())) {
                                                    departments.add(departments1.get(j));
                                                    System.out.println(departments1.get(k).getDm());
                                                    System.out.println(departments1.get(j).getFjjg());
                                                    flagj = true;
                                                    break;
                                                }
                                            }
                                        }
                                    }else {
                                        //3、若当前数据库的部门名是否和插入数据部门相同，
                                        // 则判断其父部门是否相同，相同则插入数据库
                                        if (departments1.get(k).getDm().equals(departments1.get(j).getFjjg())){
                                            if (convertDepartment.getParentid().equals(insertId)){
                                                departments.add(departments1.get(j));
                                                System.out.println(departments1.get(k).getDm());
                                                System.out.println(departments1.get(j).getFjjg());
                                                flagj = true;
                                                break;
                                            }else {
                                                if (department.get(i).getName().equals(departments1.get(k).getMc().substring(0,31))) {
                                                    departments.add(departments1.get(j));
                                                    System.out.println(departments1.get(k).getDm());
                                                    System.out.println(departments1.get(j).getFjjg());
                                                    flagj = true;
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }else {
                            //2、相同则判断当前数据库的部门名是否和插入数据部门相同
                            if (departments1.get(j).getMc().substring(0,31).equals(convertDepartment.getName())){
                                for (int k = 0;k < departments1.size();k++){
                                    if (departments1.get(k).getMc().length() <= 32){
                                        //3、若当前数据库的部门名是否和插入数据部门相同，
                                        // 则判断其父部门是否相同，相同则插入数据库
                                        if (departments1.get(k).getDm().equals(departments1.get(j).getFjjg()) &&
                                                department.get(i).getName().equals(departments1.get(k).getMc())){
                                            departments.add(departments1.get(j));
                                            System.out.println(departments1.get(k).getDm());
                                            System.out.println(departments1.get(j).getFjjg());
                                            flagj = true;
                                            break;
                                        }
                                    }else {
                                        if (departments1.get(k).getDm().equals(departments1.get(j).getFjjg()) &&
                                                department.get(i).getName().equals(departments1.get(k).getMc().substring(0,31))){
                                            departments.add(departments1.get(j));
                                            System.out.println(departments1.get(k).getDm());
                                            System.out.println(departments1.get(j).getFjjg());
                                            flagj = true;
                                            break;
                                        }
                                    }

                                }
                            }
                        }
                        if(flagj){
                            break;
                        }
                    }
                }
            }

            InsertMysqlUtils.insertDepts(departments);
        }else {
            if(response.getErrcode().equals("60008")){
                LogUtil.Log("该部门已存在,进行更新。");
                Response response1 = departmentService.updateService(access_token, convertDepartment);
                LogUtil.Log(response1.toString());

                List<ConvertDepartment> department = departmentService.selectService(access_token, insertId).getDepartment();

                if (response1.getErrcode().equals("0")){
                    String sql = "insert into party values (?,?,?,?,?,?,?,?";
                    for (int i = 0; i < department.size(); i++) {
                        //1、先判断企业微信后台获取的部门id是否和插入的父部门id是否相同，
                        if (department.get(i).getId().equals(convertDepartment.getParentid())){
                            for (int j = 0; j < departments1.size(); j++) {
                                if (departments1.get(j).getMc().length() <= 32){
                                    //2、相同则判断当前数据库的部门名是否和插入数据部门相同
                                    if (departments1.get(j).getMc().equals(convertDepartment.getName())){
                                        for (int k = 0;k < departments1.size();k++){
                                            if (departments1.get(k).getMc().length() <= 32){
                                                //3、若当前数据库的部门名是否和插入数据部门相同，
                                                // 则判断其父部门是否相同，相同则插入数据库
                                                if (departments1.get(k).getDm().equals(departments1.get(j).getFjjg()) &&
                                                        department.get(i).getName().equals(departments1.get(k).getMc())){
                                                    UpdateUtils.update("druid.properties", sql, departments1.get(j).getDm(),
                                                            departments1.get(j).getMc(), departments1.get(j).getFjjg(), departments1.get(j).getJglx(),
                                                            departments1.get(j).getQyzt(),departments1.get(j).getCC(),departments1.get(j).getPX(),
                                                            departments1.get(j).getJC());
                                                    break;
                                                }
                                            }else {
                                                if (departments1.get(k).getDm().equals(departments1.get(j).getFjjg()) &&
                                                        department.get(i).getName().equals(departments1.get(k).getMc().substring(0,31))){
                                                    UpdateUtils.update("druid.properties",sql,departments1.get(j).getDm(),
                                                            departments1.get(j).getMc(),departments1.get(j).getFjjg(), departments1.get(j).getJglx(),
                                                            departments1.get(j).getQyzt(),departments1.get(j).getCC(),departments1.get(j).getPX(),
                                                            departments1.get(j).getJC());
                                                    break;
                                                }
                                            }

                                        }
                                    }
                                }else {
                                    //2、相同则判断当前数据库的部门名是否和插入数据部门相同
                                    if (departments1.get(j).getMc().substring(0,31).equals(convertDepartment.getName())){
                                        for (int k = 0;k < departments1.size();k++){
                                            if (departments1.get(k).getMc().length() <= 32){
                                                //3、若当前数据库的部门名是否和插入数据部门相同，
                                                // 则判断其父部门是否相同，相同则插入数据库
                                                if (departments1.get(k).getDm().equals(departments1.get(j).getFjjg()) &&
                                                        department.get(i).getName().equals(departments1.get(k).getMc())){
                                                    UpdateUtils.update("druid.properties",sql,departments1.get(j).getDm(),
                                                            departments1.get(j).getMc(),departments1.get(j).getFjjg(), departments1.get(j).getJglx(),
                                                            departments1.get(j).getQyzt(),departments1.get(j).getCC(),departments1.get(j).getPX(),
                                                            departments1.get(j).getJC());
                                                    break;
                                                }
                                            }else {
                                                if (departments1.get(k).getDm().equals(departments1.get(j).getFjjg()) &&
                                                        department.get(i).getName().equals(departments1.get(k).getMc().substring(0,31))){
                                                    UpdateUtils.update("druid.properties",sql,departments1.get(j).getDm(),
                                                            departments1.get(j).getMc(),departments1.get(j).getFjjg(), departments1.get(j).getJglx(),
                                                            departments1.get(j).getQyzt(),departments1.get(j).getCC(),departments1.get(j).getPX(),
                                                            departments1.get(j).getJC());
                                                    break;
                                                }
                                            }

                                        }
                                    }
                                }
                            }
                        }

                    }
                }
            }else {
                LogUtil.Log(convertDepartment.getName() + "未添加成功");
                LogUtil.Log("错误信息为:"+response.getErrmsg());
                LogUtil.Log(response.toString());
            }
        }
    }

    public static void update(String access_token, ConvertDepartment convertDepartment,
                              List<Department> departments, List<ConvertDepartment> department ) throws Exception {
        LogUtil.Log("开始向企业微信后台更新单个部门数据");

        //向企业微信后台进行更新数据操作
        Response response = departmentService.updateService(access_token, convertDepartment);
        LogUtil.Log(response.toString());

        //插入成功则将该行数据插入到本地数据库
        if (response.getErrcode().equals("0")) {
            LogUtil.Log("企业微信后台添加更新单个部门数据完成");
            String sql = "update users SET name=? mobile=? userid=? gender=? position=? department=? usertype=?" +
                    " roomname=? email=? status=? Ldzj=? Ryfl=? Ryzt=? Gwlb=? rs=?";
            for (int i = 0; i < department.size(); i++) {
                //1、先判断企业微信后台获取的部门id是否和插入的父部门id是否相同，
                if (department.get(i).getId().equals(convertDepartment.getParentid())){
                    for (int j = 0; j < departments.size(); j++) {
                        if (departments.get(j).getMc().length() <= 32){
                            //2、相同则判断当前数据库的部门名是否和插入数据部门相同
                            if (departments.get(j).getMc().equals(convertDepartment.getName())){
                                for (int k = 0;k < departments.size();k++){
                                    if (departments.get(k).getMc().length() <= 32){
                                        //3、若当前数据库的部门名是否和插入数据部门相同，
                                        // 则判断其父部门是否相同，相同则插入数据库
                                        if (departments.get(k).getDm().equals(departments.get(j).getFjjg()) &&
                                                department.get(i).getName().equals(departments.get(k).getMc())){
                                            UpdateUtils.update("druid.properties",sql,departments.get(j).getDm(),
                                                    departments.get(j).getMc(),departments.get(j).getFjjg(), departments.get(j).getJglx(),
                                                    departments.get(j).getQyzt(),departments.get(j).getCC(),departments.get(j).getPX(),
                                                    departments.get(j).getJC());
                                        }
                                    }else {
                                        if (departments.get(k).getDm().equals(departments.get(j).getFjjg()) &&
                                                department.get(i).getName().equals(departments.get(k).getMc().substring(0,31))){
                                            UpdateUtils.update("druid.properties",sql,departments.get(j).getDm(),
                                                    departments.get(j).getMc(),departments.get(j).getFjjg(), departments.get(j).getJglx(),
                                                    departments.get(j).getQyzt(),departments.get(j).getCC(),departments.get(j).getPX(),
                                                    departments.get(j).getJC());
                                        }
                                    }

                                }
                            }
                        }else {
                            //2、相同则判断当前数据库的部门名是否和插入数据部门相同
                            if (departments.get(j).getMc().substring(0,31).equals(convertDepartment.getName())){
                                for (int k = 0;k < departments.size();k++){
                                    if (departments.get(k).getMc().length() <= 32){
                                        //3、若当前数据库的部门名是否和插入数据部门相同，
                                        // 则判断其父部门是否相同，相同则插入数据库
                                        if (departments.get(k).getDm().equals(departments.get(j).getFjjg()) &&
                                                department.get(i).getName().equals(departments.get(k).getMc())){
                                            UpdateUtils.update("druid.properties",sql,departments.get(j).getDm(),
                                                    departments.get(j).getMc(),departments.get(j).getFjjg(), departments.get(j).getJglx(),
                                                    departments.get(j).getQyzt(),departments.get(j).getCC(),departments.get(j).getPX(),
                                                    departments.get(j).getJC());
                                        }
                                    }else {
                                        if (departments.get(k).getDm().equals(departments.get(j).getFjjg()) &&
                                                department.get(i).getName().equals(departments.get(k).getMc().substring(0,31))){
                                            UpdateUtils.update("druid.properties",sql,departments.get(j).getDm(),
                                                    departments.get(j).getMc(),departments.get(j).getFjjg(), departments.get(j).getJglx(),
                                                    departments.get(j).getQyzt(),departments.get(j).getCC(),departments.get(j).getPX(),
                                                    departments.get(j).getJC());
                                        }
                                    }

                                }
                            }
                        }
                    }
                }

            }
        }else {

            LogUtil.Log(convertDepartment.getName() + "未更新成功");
            LogUtil.Log("错误信息为:"+response.toString());
        }
    }

    /*public static void delete(String access_token, String departmentId, List<Department> departments) throws Exception {
        LogUtil.Log("开始向企业微信后台删除单个部门数据");

        //向企业微信后台进行更新数据操作
        Response response = userService.deleteService(access_token, departmentId);

        //插入成功则将该行数据插入到本地数据库
        if (response.getErrcode().equals("0")) {
            LogUtil.Log("企业微信后台删除单个部门数据完成");
            String sql = "delete from party where userid=?";
            for (int i = 0;i < departments.size();i++){
                if (departmentId.equals(departments.get(i).getDm())){
                    UpdateUtils.update("druid.properties",sql,departmentId);
                    break;
                }
            }
        }else {
            LogUtil.Log(departmentId + "未删除成功");
            LogUtil.Log("错误信息为:"+response.toString());
        }
    }*/
}
