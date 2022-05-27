package com.nuist.dao;

import com.nuist.bean.ConvertUser;
import com.nuist.bean.Response;

import java.io.IOException;
/**
 * 向企业微信接口进行用户操作的Dao
 */
public interface UserDao {

    //获取token
    Response getAccess_token(String corpId, String corpSecret);

    //创建成员（适用于更新后的传入企业微信接口的UpdateUser数据）
    Response insert(String access_token, ConvertUser convertUser);

    //删除成员
    Response delete(String access_token, String userId);
    //更新成员
    Response update(String access_token, ConvertUser convertUser) throws IOException;
    //查询成员
    Response select(String access_token, String userId);
    //查询部门成员(递归获取)
    Response selectDeptUsers(String access_token, String userId);
}
