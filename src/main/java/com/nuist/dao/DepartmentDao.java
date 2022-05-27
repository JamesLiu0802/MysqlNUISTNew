package com.nuist.dao;

import com.nuist.bean.ConvertDepartment;
import com.nuist.bean.DepartmentResponse;
import com.nuist.bean.Response;

/**
 * 向企业微信接口进行部门操作的Dao
 */
public interface DepartmentDao {


    //创建部门(适用于更新后的传入企业微信接口的UpdateDepartment数据)
    Response insert(String access_token, ConvertDepartment department);

    //删除部门(不可删除有子部门的部门)
    Response delete(String access_token, String id);
    //查询部门
    DepartmentResponse select(String access_token, String id);

    Response update(String access_token,ConvertDepartment department);
}
