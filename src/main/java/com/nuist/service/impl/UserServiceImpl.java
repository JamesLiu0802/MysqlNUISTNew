package com.nuist.service.impl;

import com.nuist.bean.ConvertUser;
import com.nuist.bean.Response;
import com.nuist.dao.UserDao;
import com.nuist.dao.impl.UserDaoImpl;
import com.nuist.service.UserService;

import java.io.IOException;

public class UserServiceImpl implements UserService {

    private UserDao dao = new UserDaoImpl();

    @Override
    public Response getAccess_tokenService(String corpId, String corpSecret) {

        return dao.getAccess_token(corpId,corpSecret);
    }

    @Override
    public Response insertService(String access_token, ConvertUser convertUser) {
        return dao.insert(access_token, convertUser);
    }

    @Override
    public Response deleteService(String access_token, String userId) {
        return dao.delete(access_token, userId);
    }

    @Override
    public Response updateService(String access_token, ConvertUser convertUser) throws IOException {
        return dao.update(access_token, convertUser);
    }

    @Override
    public Response selectService(String access_token, String userId) {
        return dao.select(access_token, userId);
    }

    @Override
    public Response selectDeptUsers(String access_token, String departmentId) {
        return dao.selectDeptUsers(access_token, departmentId);
    }
}
