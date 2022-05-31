package com.nuist.utils.User;

import com.nuist.bean.*;
import com.nuist.utils.ConvertUtils;
import com.nuist.utils.JDBCUtils;
import com.nuist.utils.LogUtil;
import com.nuist.utils.SelectListUtils;

import java.sql.Connection;
import java.util.List;

import static com.nuist.test.TestJunit.*;

public class CompareUsers {

    static Connection conn;
    static Connection connNew;

    public static void compare(String properties,String newProperties) throws Exception {

        LogUtil.Log("开始对比数据库用户数据");
        conn = JDBCUtils.getConnection(properties);
        connNew = JDBCUtils.getConnection(newProperties);
        String sql = "select * from users";

        LogUtil.Log("开始将数据库数据转换成可添加企业微信后台的List对象");
        List<User> users = SelectListUtils.getList(conn, sql, User.class);
        List<ConvertUser> convertUsers = ConvertUtils.convertUsers(properties,users);
        List<User> users1 = SelectListUtils.getList(connNew, sql, User.class);
        List<ConvertUser> convertUsers1 = ConvertUtils.convertUsers(newProperties,users1);
        LogUtil.Log("数据库数据转换成可添加企业微信后台的List对象完毕");


        boolean flag;
        int count = 0;
        //通过循环筛选需要修改和删除的记录
        for (int i = 0;i < convertUsers.size();i++){
            ConvertUser convertUser = new ConvertUser();
            flag = true;
            for (int j = 0;j < convertUsers1.size();j++){
                if (convertUsers.get(i).getUserid().equals(convertUsers1.get(j).getUserid())){
//                    若有更改则更新
                    if ( convertUsers.get(i).getName().equals(convertUsers1.get(j).getName()) &&
                            /*convertUsers.get(i).getMobile().equals(convertUsers1.get(j).getMobile()) &&*/
                            convertUsers.get(i).getDepartment().equals(convertUsers1.get(j).getDepartment()) &&
                            convertUsers.get(i).getPosition().equals(convertUsers1.get(j).getPosition()) &&
                            convertUsers.get(i).getGender() == convertUsers1.get(j).getGender() &&
                            convertUsers.get(i).getEmail().equals(convertUsers1.get(j).getEmail())){
                        LogUtil.Log(convertUsers.get(i).getName() + "无需修改");
                        break;
                    }else {
                        convertUser.setUserid(convertUsers1.get(j).getUserid());
                        convertUser.setName(convertUsers1.get(j).getName());
                        convertUser.setMobile(convertUsers1.get(j).getMobile());
                        convertUser.setDepartment(convertUsers1.get(j).getDepartment());
                        convertUser.setPosition(convertUsers1.get(j).getPosition());
                        convertUser.setGender(convertUsers1.get(j).getGender());
                        convertUser.setEmail(convertUsers1.get(j).getEmail());
                        LogUtil.Log(convertUser.getName() + "需要更新");

                        UserCRUD.update(access_token,convertUser,users);
                        break;
                    }
                }else {
                    //若新数据库的记录无法找到与旧数据库相匹配的id则将flag设置为false
                    if (j == (convertUsers1.size()-1)){
                        flag = false;
                    }
                }
                //若flag为false则执行删除操作
                if (flag == false){
                    LogUtil.Log(convertUsers.get(i).getName() + "需要删除");
                    UserCRUD.delete(access_token,convertUsers.get(i).getUserid(),users);
                    break;
                }
                count++;
                if (count > 15000){
                    break;
                }
            }
        }

        count = 0;
        //通过循环筛选需要插入的记录
        for (int i = 0;i < convertUsers1.size();i++){
            ConvertUser convertUser = new ConvertUser();
            flag = true;
            for (int j = 0;j < convertUsers.size();j++){
                if (convertUsers1.get(i).getUserid().equals(convertUsers.get(j).getUserid())){
                    break;
                }else {
                    if (j == (convertUsers.size() -1 )){
                        flag = false;
                    }
                }

                if (flag == false){
                    convertUser.setUserid(convertUsers1.get(i).getUserid());
                    convertUser.setName(convertUsers1.get(i).getName());
                    convertUser.setMobile(convertUsers1.get(i).getMobile());
                    convertUser.setDepartment(convertUsers1.get(i).getDepartment());
                    convertUser.setPosition(convertUsers1.get(i).getPosition());
                    convertUser.setGender(convertUsers1.get(i).getGender());
                    convertUser.setEmail(convertUsers1.get(i).getEmail());
                    //如果旧数据库中无该条数据则新建用户
                    LogUtil.Log(convertUsers1.get(i).getName() + "需要新建");
                    UserCRUD.insert(access_token, convertUser,users);
                    count++;
                    break;
                }
            }
            if (count > 15000){
                break;
            }
        }
        LogUtil.Log("对比数据库用户数据完成");
    }
}
