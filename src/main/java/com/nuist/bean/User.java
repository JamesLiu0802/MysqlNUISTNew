package com.nuist.bean;

/**
 * 从数据库读取User数据并转换的Bean
 */
public class User {
    //name	mobile	userid	gender	position	department
    //usertype	roomname	email	status	Ldzj	Ryfl	Ryzt	Gwlb	rs
    private String name;
    private String mobile;
    private String userid;
    private String gender;
    private String position;
    private String department;
    private String usertype;
    private String roomname;
    private String email;
    private String status;
    private String Ldzj;
    private String Ryfl;
    private String Ryzt;
    private String Gwlb;
    private String rs;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", userid='" + userid + '\'' +
                ", gender='" + gender + '\'' +
                ", position='" + position + '\'' +
                ", department='" + department + '\'' +
                ", usertype='" + usertype + '\'' +
                ", roomname='" + roomname + '\'' +
                ", email='" + email + '\'' +
                ", status='" + status + '\'' +
                ", Ldzj='" + Ldzj + '\'' +
                ", Ryfl='" + Ryfl + '\'' +
                ", Ryzt='" + Ryzt + '\'' +
                ", Gwlb='" + Gwlb + '\'' +
                ", rs='" + rs + '\'' +
                '}';
    }

    public User() {
    }

    public User(String name, String mobile, String userid, String gender, String position, String department, String usertype, String roomname, String email, String status, String ldzj, String ryfl, String ryzt, String gwlb, String rs) {
        this.name = name;
        this.mobile = mobile;
        this.userid = userid;
        this.gender = gender;
        this.position = position;
        this.department = department;
        this.usertype = usertype;
        this.roomname = roomname;
        this.email = email;
        this.status = status;
        Ldzj = ldzj;
        Ryfl = ryfl;
        Ryzt = ryzt;
        Gwlb = gwlb;
        this.rs = rs;
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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLdzj() {
        return Ldzj;
    }

    public void setLdzj(String ldzj) {
        Ldzj = ldzj;
    }

    public String getRyfl() {
        return Ryfl;
    }

    public void setRyfl(String ryfl) {
        Ryfl = ryfl;
    }

    public String getRyzt() {
        return Ryzt;
    }

    public void setRyzt(String ryzt) {
        Ryzt = ryzt;
    }

    public String getGwlb() {
        return Gwlb;
    }

    public void setGwlb(String gwlb) {
        Gwlb = gwlb;
    }

    public String getRs() {
        return rs;
    }

    public void setRs(String rs) {
        this.rs = rs;
    }
}
