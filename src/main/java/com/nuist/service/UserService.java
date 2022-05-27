package com.nuist.service;

import com.nuist.bean.ConvertUser;
import com.nuist.bean.Response;

import java.io.IOException;

public interface UserService {

    Response getAccess_tokenService(String corpId, String corpSecret);

    Response insertService(String access_token, ConvertUser convertUser);

    Response deleteService(String access_token, String userId);

    Response updateService(String access_token, ConvertUser convertUser) throws IOException;

    Response selectService(String access_token, String userId);

    Response selectDeptUsers(String access_token, String departmentId);

}
