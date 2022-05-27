package com.nuist.bean;

import java.util.List;

/**
 * 接收企业微信接口返回响应数据
 */
public class Response {

    private String errcode;
    private String errmsg;
    private String access_token;
    private String expires_in;
    private String id;
    private List<User> userlist;

    @Override
    public String toString() {
        return "Response{" +
                "errcode='" + errcode + '\'' +
                ", errmsg='" + errmsg + '\'' +
                ", access_token='" + access_token + '\'' +
                ", expires_in='" + expires_in + '\'' +
                ", id='" + id + '\'' +
                ", userlist=" + userlist +
                '}';
    }

    public Response() {
    }

    public Response(String errcode, String errmsg, String access_token, String expires_in, String id, List<User> userlist) {
        this.errcode = errcode;
        this.errmsg = errmsg;
        this.access_token = access_token;
        this.expires_in = expires_in;
        this.id = id;
        this.userlist = userlist;
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

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<User> getUserlist() {
        return userlist;
    }

    public void setUserlist(List<User> userlist) {
        this.userlist = userlist;
    }
}
