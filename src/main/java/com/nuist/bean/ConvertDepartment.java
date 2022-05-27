package com.nuist.bean;
/**
 * 将从数据库获取Department数据的Bean转化为可传入企业微信接口对象的Bean
 */
public class ConvertDepartment {

    private String name;
    private String parentid;
    private String id;

    @Override
    public String toString() {
        return "ConvertDepartment{" +
                "name='" + name + '\'' +
                ", parentid='" + parentid + '\'' +
                ", id=" + id +
                '}';
    }

    public ConvertDepartment() {
    }
    public ConvertDepartment(String name, String parentid, String id) {
        this.name = name;
        this.parentid = parentid;
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
