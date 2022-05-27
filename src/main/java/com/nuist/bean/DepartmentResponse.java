package com.nuist.bean;

import java.util.List;

/**
 * 接收查询部门时所返回数据
 */
public class DepartmentResponse {

    private String errcode;
    private String errmsg;
    private List<ConvertDepartment> department;

    @Override
    public String toString() {
        return "DepartmentResponse{" +
                "errcode='" + errcode + '\'' +
                ", errmsg='" + errmsg + '\'' +
                ", department=" + department +
                '}';
    }

    public DepartmentResponse() {
    }

    public DepartmentResponse(String errcode, String errmsg, List<ConvertDepartment> department) {
        this.errcode = errcode;
        this.errmsg = errmsg;
        this.department = department;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public List<ConvertDepartment> getDepartment() {
        return department;
    }

    public void setDepartment(List<ConvertDepartment> department) {
        this.department = department;
    }
}
