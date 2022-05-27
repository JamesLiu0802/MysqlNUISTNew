package com.nuist.bean;

import java.util.List;

/**
 * 将从数据库获取User数据的Bean转化为可传入企业微信接口对象的Bean
 */
public class ConvertUser {
    private String userid;
    private String name;
    private String mobile;
    private List<String> department;
    private String position;
    private String gender;
    private String email;

    @Override
    public String toString() {
        return "ConvertUser{" +
                "userid='" + userid + '\'' +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", department=" + department +
                ", position='" + position + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public ConvertUser() {
    }

    public ConvertUser(String userid, String name, String mobile, List<String> department, String position, String gender, String email) {
        this.userid = userid;
        this.name = name;
        this.mobile = mobile;
        this.department = department;
        this.position = position;
        this.gender = gender;
        this.email = email;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<String> getDepartment() {
        return department;
    }

    public void setDepartment(List<String> department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
