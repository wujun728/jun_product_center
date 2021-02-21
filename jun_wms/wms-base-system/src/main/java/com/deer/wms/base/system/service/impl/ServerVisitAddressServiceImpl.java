package com.deer.wms.base.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.deer.wms.base.system.dao.ServerVisitAddressMapper;
import com.deer.wms.base.system.model.*;
import com.deer.wms.base.system.model.BaseQueryParams;
import com.deer.wms.base.system.model.Body;
import com.deer.wms.base.system.model.EbsBack;
import com.deer.wms.base.system.model.SystemParams;
import com.deer.wms.base.system.model.item.BaseQueryParams2;
import com.deer.wms.base.system.model.item.Body2;
import com.deer.wms.base.system.service.MESWebService.WebserviceResponse;
import com.deer.wms.base.system.service.ServerVisitAddressService;


import com.deer.wms.common.core.service.AbstractService;
import com.deer.wms.common.utils.DateUtils;
import com.deer.wms.framework.util.MyUtils;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by  on 2019/09/26.
 */
@Service
public class ServerVisitAddressServiceImpl extends AbstractService<ServerVisitAddress, Integer> implements ServerVisitAddressService {

    @Autowired
    private ServerVisitAddressMapper serverVisitAddressMapper;

    @Override
    public List<ServerVisitAddress> findList(ServerVisitAddressCriteria criteria){
        return serverVisitAddressMapper.findList(criteria);
    }

    @Override
    public ServerVisitAddress findAddressById(@Param("id") Integer id){
        return serverVisitAddressMapper.findAddressById(id);
    }

    @Override
    public EbsBack requestItemId(String itemCode,Integer organizationId)
    {
        CloseableHttpClient client = HttpClients.createDefault();
        String entityStr = null;
        CloseableHttpResponse response = null;
        //获取token的地址及账号密码
        ServerVisitAddress serverVisitAddressFour = serverVisitAddressMapper.findAddressById(4);
        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
        try {
            OAuthClientRequest accessTokenRequest = OAuthClientRequest
                    .tokenLocation(serverVisitAddressFour.getVisitAddress())
                    .setGrantType(GrantType.CLIENT_CREDENTIALS)
                    .setUsername(serverVisitAddressFour.getAccount())
                    .setPassword(serverVisitAddressFour.getPassword())
                    .buildQueryMessage();
            accessTokenRequest.addHeader("Accept", "application/json");
            accessTokenRequest.addHeader("Content-Type", "application/json");
            String auth = MyUtils.encode(serverVisitAddressFour.getAccount() + ':' + serverVisitAddressFour.getPassword());
            accessTokenRequest.addHeader("Authorization", "Basic " + auth);
            OAuthAccessTokenResponse oAuthResponse = oAuthClient.accessToken(accessTokenRequest, OAuth.HttpMethod.POST); //去服务端请求access_token，并返回响应
            String accessToken = oAuthResponse.getAccessToken(); //获取服务端返回过来的access_token
            //获取EBS请求接口地址
            ServerVisitAddress serverVisitAddressThree = serverVisitAddressMapper.findAddressById(5);
            HttpPost httpPost = new HttpPost(serverVisitAddressThree.getVisitAddress());
            httpPost.setHeader("Authorization","bearer "+accessToken);
            httpPost.setHeader("Content-Type","application/json");
            SystemParams systemParams = new SystemParams("1","PCB_APS","APS","EBS",
                    "getInvItemInfosForPcbAps",MyUtils.randomAssignFigures(6),System.currentTimeMillis(),
                    TaskTypeConstant.QUERY,"1.0","fd");
            BaseQueryParams2 baseQueryParams = new BaseQueryParams2(
                organizationId,"2000-01-01",DateUtils.getDate(),1,50
            );
            /*List<Map<String, String>> baseQueryParams = new ArrayList<>();
            Map<String, String> map1 = new HashMap<>();
            map1.put("organizationId",organizationId.toString());
            map1.put("lastUpdateDateF","2000-01-01");
            map1.put("lastUpdateDateT",DateUtils.getDate());
            map1.put("startPosition","1");
            map1.put("rowsCnt","50");
            baseQueryParams.add(map1);*/
            List<Map<String, String>> lists = new ArrayList<>();
            Map<String, String> map = new HashMap<>();
            map.put("itemCode",itemCode);
            lists.add(map);
//            systemParams.setToken(accessToken);
            Body2 body2 = new Body2(systemParams, baseQueryParams,lists);
            String listJson = JSON.toJSONString(body2);
            StringEntity stringEntity = new StringEntity(listJson,"UTF-8");
            httpPost.setEntity(stringEntity);
            response = client.execute(httpPost);
            // 获得响应的实体对象
            HttpEntity entity = response.getEntity();
            // 使用Apache提供的工具类进行转换成字符串
            entityStr = EntityUtils.toString(entity, "UTF-8");
            JSONObject jsonObject = JSONObject.parseObject(entityStr);
            String success = jsonObject.get("success").toString().trim();
            String msg = jsonObject.get("msg").toString().trim();
            Integer total = null;
            String rows = "";
            if(success.equals("true")) {
                String obj = jsonObject.get("obj").toString().trim();
                JSONObject jsonObject1 = JSONObject.parseObject(obj);
                total = jsonObject1.get("total").toString().trim() == null ? 0 : Integer.parseInt(jsonObject1.get("total").toString().trim());
                rows = jsonObject1.get("rows").toString().trim();
            }
            return new EbsBack(success,msg,total,rows);
        } catch (ClientProtocolException e) {
            System.err.println("Http协议出现问题");
            e.printStackTrace();
        } catch (ParseException e) {
            System.err.println("解析错误");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("IO异常");
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            // 释放连接
            if (null != response) {
                try {
                    response.close();
                    client.close();
                } catch (IOException e) {
                    System.err.println("释放连接出错");
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public EbsBack requestServerCode(String requestId, String serviceName, String serviceOperation, Integer organizationId,List<Map<String, String>> lists)
            {
        CloseableHttpClient client = HttpClients.createDefault();
        String entityStr = null;
        CloseableHttpResponse response = null;
        //获取token的地址及账号密码
        ServerVisitAddress serverVisitAddressFour = serverVisitAddressMapper.findAddressById(4);
        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
        try {
            OAuthClientRequest accessTokenRequest = OAuthClientRequest
                    .tokenLocation(serverVisitAddressFour.getVisitAddress())
                    .setGrantType(GrantType.CLIENT_CREDENTIALS)
                    .setUsername(serverVisitAddressFour.getAccount())
                    .setPassword(serverVisitAddressFour.getPassword())
                    .buildQueryMessage();
            accessTokenRequest.addHeader("Accept", "application/json");
            accessTokenRequest.addHeader("Content-Type", "application/json");
            String auth = MyUtils.encode(serverVisitAddressFour.getAccount() + ':' + serverVisitAddressFour.getPassword());
            accessTokenRequest.addHeader("Authorization", "Basic " + auth);
            OAuthAccessTokenResponse oAuthResponse = oAuthClient.accessToken(accessTokenRequest, OAuth.HttpMethod.POST); //去服务端请求access_token，并返回响应
            String accessToken = oAuthResponse.getAccessToken(); //获取服务端返回过来的access_token
            //获取EBS请求接口地址
            ServerVisitAddress serverVisitAddressThree = serverVisitAddressMapper.findAddressById(3);
            HttpPost httpPost = new HttpPost(serverVisitAddressThree.getVisitAddress());
            httpPost.setHeader("Authorization","bearer "+accessToken);
            httpPost.setHeader("Content-Type","application/json");
            SystemParams systemParams = new SystemParams(requestId,"NT_WMS","NT_WMS","EBS",
                    serviceName,MyUtils.randomAssignFigures(6),System.currentTimeMillis(),
                    serviceOperation,"1.0","");
            String listJson = "";
            if(organizationId != null) {
                BaseQueryParams baseQueryParams = new BaseQueryParams(organizationId);
                Body body = new Body(systemParams,baseQueryParams,lists);
                listJson = JSON.toJSONString(body, SerializerFeature.WriteMapNullValue);
            }else{
                BaseQueryParams1 baseQueryParams = new BaseQueryParams1();
                Body1 body = new Body1(systemParams,baseQueryParams,lists);
                listJson = JSON.toJSONString(body, SerializerFeature.WriteMapNullValue);
            }
//            systemParams.setToken(accessToken);
            StringEntity stringEntity = new StringEntity(listJson,"UTF-8");
            httpPost.setEntity(stringEntity);
            response = client.execute(httpPost);
            // 获得响应的实体对象
            HttpEntity entity = response.getEntity();
            // 使用Apache提供的工具类进行转换成字符串
            entityStr = EntityUtils.toString(entity, "UTF-8");
            JSONObject jsonObject = JSONObject.parseObject(entityStr);
            String success = jsonObject.get("success").toString().trim();
            String msg = jsonObject.get("msg").toString().trim();
            Integer total = null;
            String rows = "";
            if(success.equals("true")) {
                String obj = jsonObject.get("obj").toString().trim();
                JSONObject jsonObject1 = JSONObject.parseObject(obj);
                total = jsonObject1.get("total").toString().trim() == null ? 0 : Integer.parseInt(jsonObject1.get("total").toString().trim());
                rows = jsonObject1.get("rows").toString().trim();
            }
            return new EbsBack(success,msg,total,rows);
        } catch (ClientProtocolException e) {
            System.err.println("Http协议出现问题");
            e.printStackTrace();
        } catch (ParseException e) {
            System.err.println("解析错误");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("IO异常");
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            // 释放连接
            if (null != response) {
                try {
                    response.close();
                    client.close();
                } catch (IOException e) {
                    System.err.println("释放连接出错");
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 给mes发送请求
     * @param methodName
     * @param code
     * @return
     */
    @Override
    public WebserviceResponse requestMesServer(String methodName,String code) {
        WebserviceResponse webserviceResponse = null;
        try{
            Object[] objects;
            ServerVisitAddress MesIpAddress = serverVisitAddressMapper.findAddressById(1);
            JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
            Client client = dcf.createClient(MesIpAddress.getVisitAddress());
            objects = client.invoke("macIntf", methodName,code);
            webserviceResponse = analysisObject(objects[0]);
//            System.out.println("返回数据:" + objects[0].toString());
        } catch (Exception e) {
            e.printStackTrace();
            webserviceResponse = new WebserviceResponse(null,"-1","调用WMS接口出错",null);
        }
        return webserviceResponse;
    }

    public WebserviceResponse analysisObject(Object object) throws Exception{
        Field taskCode = object.getClass().getDeclaredField("x003CTaskCodeX003EKBackingField");
        taskCode.setAccessible(true);
        Field errorCode = object.getClass().getDeclaredField("x003CErrorCodeX003EKBackingField");
        errorCode.setAccessible(true);
        Field errorMsg = object.getClass().getDeclaredField("x003CErrorMsgX003EKBackingField");
        errorMsg.setAccessible(true);
        Field resultData = object.getClass().getDeclaredField("x003CResultDataX003EKBackingField");
        resultData.setAccessible(true);
        String taskCode1 = taskCode.get(object).toString();
        String errorCode1 = errorCode.get(object).toString();
        String errorMsg1 =  errorMsg.get(object).toString();
        String resultData1 = resultData.get(object)==null?"":resultData.get(object).toString();
        WebserviceResponse webserviceResponse = new WebserviceResponse(taskCode1,errorCode1, errorMsg1, resultData1);
        return webserviceResponse;
    }
}
