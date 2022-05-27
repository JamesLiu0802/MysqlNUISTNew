package com.nuist.service.impl;

import com.nuist.bean.*;
import com.nuist.dao.DepartmentDao;
import com.nuist.dao.impl.DepartmentDaoImpl;
import com.nuist.service.DepartmentService;

public class DepartmentServiceImpl implements DepartmentService {

    DepartmentDao dao = new DepartmentDaoImpl();

    @Override
    public Response insertService(String access_token, ConvertDepartment department) {
        return dao.insert(access_token, department);
    }

    @Override
    public Response deleteService(String access_token, String id) {
        return dao.delete(access_token, id);
    }

    @Override
    public DepartmentResponse selectService(String access_token, String id) {
        return dao.select(access_token,id);
    }

    @Override
    public Response updateService(String access_token, ConvertDepartment department) {
        return dao.update(access_token, department);
    }
}
