package com.nuist.utils.User;

import com.nuist.bean.ConvertUser;
import com.nuist.bean.Response;
import com.nuist.bean.User;
import com.nuist.utils.LogUtil;
import com.nuist.utils.UpdateUtils;

import java.util.List;

import static com.nuist.test.TestJunit.userService;

public class UserCRUD {

    public static void insert(String access_token, ConvertUser user, List<User> users) throws Exception {
        LogUtil.Log("开始向企业微信后台添加单个用户数据");

        //向企业微信后台进行添加数据操作
        Response response = userService.insertService(access_token, user);

        //插入成功则将该行数据插入到本地数据库
        if (response.getErrcode().equals("0")) {
            LogUtil.Log("企业微信后台添加单个用户数据完成");
            String sql = "insert into users values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            for (int i = 0;i < users.size();i++){
                if (user.getUserid().equals(users.get(i).getUserid())){
                    UpdateUtils.update("druid.properties",sql,users.get(i).getName(),
                            users.get(i).getMobile(),users.get(i).getUserid(),users.get(i).getGender(),
                            users.get(i).getPosition(),users.get(i).getDepartment(),users.get(i).getUsertype(),
                            users.get(i).getRoomname(),users.get(i).getEmail(),users.get(i).getStatus(),users.get(i).getLdzj(),
                            users.get(i).getRyfl(),users.get(i).getRyzt(),users.get(i).getGwlb(),users.get(i).getRs());
                    break;
                }
            }
        }else {
            if(response.getErrcode().equals("60102")){
                LogUtil.Log("该用户已存在,进行更新。");
                Response response1 = userService.updateService(access_token, user);
                if (response1.getErrcode().equals("0")){
                    String sql = "insert into users values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    for (int i = 0;i < users.size();i++){
                        if (user.getUserid().equals(users.get(i).getUserid())){
                            UpdateUtils.update("druid.properties",sql,users.get(i).getName(),
                                    users.get(i).getMobile(),users.get(i).getUserid(),users.get(i).getGender(),
                                    users.get(i).getPosition(),users.get(i).getDepartment(),users.get(i).getUsertype(),
                                    users.get(i).getRoomname(),users.get(i).getEmail(),users.get(i).getStatus(),users.get(i).getLdzj(),
                                    users.get(i).getRyfl(),users.get(i).getRyzt(),users.get(i).getGwlb(),users.get(i).getRs());
                            break;
                        }
                    }
                }
            }else {
                LogUtil.Log(user.getName() + "未添加成功");
                LogUtil.Log("错误信息为:"+response.getErrmsg());
                LogUtil.Log(response.toString());
            }
        }
    }

    public static void update(String access_token, ConvertUser user, List<User> users) throws Exception {
        LogUtil.Log("开始向企业微信后台更新单个用户数据");

        //向企业微信后台进行更新数据操作
        Response response = userService.updateService(access_token, user);

        //插入成功则将该行数据插入到本地数据库
        if (response.getErrcode().equals("0")) {
            LogUtil.Log("企业微信后台添加更新单个用户数据完成");
            String sql = "update users SET name=? mobile=? userid=? gender=? position=? department=? usertype=?" +
                    " roomname=? email=? status=? Ldzj=? Ryfl=? Ryzt=? Gwlb=? rs=?";
            for (int i = 0;i < users.size();i++){
                if (user.getUserid().equals(users.get(i).getUserid())){
                    UpdateUtils.update("druid.properties",sql,users.get(i).getName(),
                            users.get(i).getMobile(),users.get(i).getUserid(),users.get(i).getGender(),
                            users.get(i).getPosition(),users.get(i).getDepartment(),users.get(i).getUsertype(),
                            users.get(i).getRoomname(),users.get(i).getEmail(),users.get(i).getStatus(),users.get(i).getLdzj(),
                            users.get(i).getRyfl(),users.get(i).getRyzt(),users.get(i).getGwlb(),users.get(i).getRs());
                    break;
                }
            }
        }else {

            LogUtil.Log(user.getName() + "未更新成功");
            LogUtil.Log("错误信息为:"+response.toString());
        }
    }

    public static void delete(String access_token, String userId, List<User> users) throws Exception {
        LogUtil.Log("开始向企业微信后台删除单个用户数据");

        //向企业微信后台进行更新数据操作
        Response response = userService.deleteService(access_token, userId);

        //插入成功则将该行数据插入到本地数据库
        if (response.getErrcode().equals("0")) {
            LogUtil.Log("企业微信后台删除单个用户数据完成");
            String sql = "delete from users where userid=?";
            for (int i = 0;i < users.size();i++){
                if (userId.equals(users.get(i).getUserid())){
                    UpdateUtils.update("druid.properties",sql,userId);
                    break;
                }
            }
        }else {
            LogUtil.Log(userId + "未更新成功");
            LogUtil.Log("错误信息为:"+response.toString());
        }
    }
}
