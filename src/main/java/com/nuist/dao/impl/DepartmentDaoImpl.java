package com.nuist.dao.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.nuist.bean.*;
import com.nuist.dao.DepartmentDao;
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

public class DepartmentDaoImpl implements DepartmentDao {


    @Override
    public Response insert(String access_token, ConvertDepartment department) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=" + access_token;

        // 赋值代码略
        String msg= JSON.toJSONString(department);

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
            JSONObject responseJson = JSONObject.parseObject(content);
            Gson gson = new Gson();
            Response response = gson.fromJson(content, Response.class);
//            System.out.println(content);
            return response;
        } catch (ClientProtocolException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response delete(String access_token, String id) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/department/delete?access_token=" +
                access_token + "&id=" + id;

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
    public DepartmentResponse select(String access_token, String id) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=" + access_token + "&id=" + id;


        HttpGet httpGet = new HttpGet(url);

        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity entity1 = httpResponse.getEntity();;
            String content= EntityUtils.toString(entity1,"utf8");

            Gson gson = new Gson();
            DepartmentResponse response = gson.fromJson(content, DepartmentResponse.class);//对于javabean直接给出class实例

            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response update(String access_token,ConvertDepartment department) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token=" + access_token;

        // 赋值代码略
        String msg= JSON.toJSONString(department);

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
}
