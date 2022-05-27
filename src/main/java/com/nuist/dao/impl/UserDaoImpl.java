package com.nuist.dao.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.nuist.bean.ConvertUser;
import com.nuist.bean.Response;
import com.nuist.dao.UserDao;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class UserDaoImpl implements UserDao {


    /**
     * 获取access_token
     * @param corpId
     * @param corpSecret
     * @return
     */
    @Override
    public Response getAccess_token(String corpId, String corpSecret) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=" + corpId + "&corpsecret=" + corpSecret;

        HttpGet httpGet = new HttpGet(url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity entity1 = httpResponse.getEntity();;
            String content= EntityUtils.toString(entity1,"utf8");

            Gson gson = new Gson();
            Response response = gson.fromJson(content, Response.class);

            return response;
        } catch (ClientProtocolException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 适用于更新后的传入企业微信接口的User数据
     * @param access_token
     * @param convertUser
     * @return
     */
    @Override
    public Response insert(String access_token, ConvertUser convertUser) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token=" + access_token;

        // 赋值代码略
        String msg= JSON.toJSONString(convertUser);

        HttpPost httpPost = new HttpPost(url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        StringEntity entity = new StringEntity(msg, "utf-8");//解决中文乱码问题
        entity.setContentEncoding("UTF-8");
        entity.setContentType("text/json");
        httpPost.setEntity(entity);
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity entity1 = httpResponse.getEntity();;
            String content= EntityUtils.toString(entity1,"utf8");

            Gson gson = new Gson();
            Response response = gson.fromJson(content, Response.class);

            return response;
        } catch (ClientProtocolException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response delete(String access_token, String userId) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/user/delete?access_token=" +
                access_token + "&userid=" + userId;

        HttpGet httpGet = new HttpGet(url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity entity1 = httpResponse.getEntity();;
            String content= EntityUtils.toString(entity1,"utf8");

            Gson gson = new Gson();
            Response response = gson.fromJson(content, Response.class);

            return response;
        } catch (ClientProtocolException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response update(String access_token, ConvertUser convertUser) throws IOException {

            String url = "https://qyapi.weixin.qq.com/cgi-bin/user/update?access_token=" + access_token;

            CloseableHttpResponse httpResponse = null;
            // 赋值代码略
            String msg= JSON.toJSONString(convertUser);

            HttpPost httpPost = new HttpPost(url);
            CloseableHttpClient httpClient = HttpClients.createDefault();

            StringEntity entity = new StringEntity(msg, "utf-8");//解决中文乱码问题
            entity.setContentEncoding("UTF-8");
            entity.setContentType("text/json");
            httpPost.setEntity(entity);
            try {
                httpResponse = httpClient.execute(httpPost);
                HttpEntity entity1 = httpResponse.getEntity();;
                String content= EntityUtils.toString(entity1,"utf8");

                Gson gson = new Gson();
                Response response = gson.fromJson(content, Response.class);

                return response;
            } catch (ClientProtocolException e) {
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                httpClient.close();
                httpResponse.close();
            }
            return null;
    }

    @Override
    public Response select(String access_token, String userId) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=" +
                access_token + "&userid=" + userId;

        HttpGet httpGet = new HttpGet(url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity entity1 = httpResponse.getEntity();;
            String content= EntityUtils.toString(entity1,"utf8");

            Gson gson = new Gson();
            Response response = gson.fromJson(content, Response.class);

            return response;
        } catch (ClientProtocolException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response selectDeptUsers(String access_token, String departmentId) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token=" +
                access_token + "&department_id=" + departmentId + "&fetch_child=1";

        HttpGet httpGet = new HttpGet(url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity entity1 = httpResponse.getEntity();;
            String content= EntityUtils.toString(entity1,"utf8");

            Gson gson = new Gson();
            Response response = gson.fromJson(content, Response.class);

            return response;
        } catch (ClientProtocolException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
