package com.nuist.service;

import com.nuist.bean.*;

public interface DepartmentService {


    Response insertService(String access_token, ConvertDepartment department);

    Response deleteService(String access_token, String id);

    DepartmentResponse selectService(String access_token, String id);

    Response updateService(String access_token, ConvertDepartment department);
}
