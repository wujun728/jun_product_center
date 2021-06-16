package com.lu.single.modular.business.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.lu.core.utils.ToolUtil;
import com.lu.single.core.utils.HttpUtils;
import com.lu.single.modular.business.mapper.ArticleMapper;
import com.lu.single.modular.business.model.Article;
import com.lu.single.modular.business.model.MapDto;
import com.lu.single.modular.business.service.IArticleService;
import com.sun.tools.hat.internal.parser.ReadBuffer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.ldap.HasControls;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;

@Slf4j
@Service
public class IArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    @Override
    public Object get01() {
        Article article = this.baseMapper.selectById(96);
//        this.baseMapper.updateById(article);
        Article article2 = this.baseMapper.selectById(96);

        article.setAuthor("111");
        this.baseMapper.updateById(article);
        return this.baseMapper.selectById(96);
    }

    @Override
    public Object get02() {
        return this.baseMapper.selectById(96);
    }

    @Override
    public void region() {
        List<String> regionList = this.baseMapper.region();
        List<String> ls = new ArrayList<>();
        regionList.stream().forEach(o -> {
            ls.add(o.replace(",", " "));
        });
        try {
            FileWriter fw=new FileWriter(new File("/Users/zhanglu/Desktop/lu/workspace/xinfu_workspace/ocr/OCR技术相关/数据格式/全国省市区县.txt"));
            //写入中文字符时会出现乱码
            BufferedWriter  bw=new BufferedWriter(fw);
            for (String s : ls) {
                bw.write(s + "\t\n");
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void map(String s1, String s2) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("query", s2);
        param.put("tag", s2);
        param.put("region", s1);
        param.put("output", "json");
        param.put("ak", "gly4DySCTgc33KctoTW0QMYVYsqlVkka");
        param.put("page_size", 20);
        String url = "http://api.map.baidu.com/place/v2/search";
        String res = HttpUtils.sendGet(url, param);
        Map<String, Object> resMap = JSONObject.parseObject(res, Map.class);
        Integer status = (Integer) resMap.get("status");
        if(status == 0){
            Integer total = (Integer) resMap.get("total");
            Integer totalPage = total / 20 + 1;
            MapDto mapDto = new MapDto();
            List<String> mapData = (List<String>) mapDto.getMapData(new ArrayList<>(), url, param, totalPage, s2, 1);
            FileWriter fw=new FileWriter(new File("/Users/zhanglu/Desktop/lu/workspace/xinfu_workspace/ocr/OCR技术相关/数据格式/"+s1+"-"+s2+".txt"));
            //写入中文字符时会出现乱码
            BufferedWriter bw = new BufferedWriter(fw);
            for (String s : mapData) {
                bw.write(s + "\t\n");
            }
            bw.close();
            fw.close();
        }
    }

    private volatile int count = 0;

    public void t(final String s2) {

        Set<String> regionList = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("/Users/zhanglu/Desktop/lu/workspace/xinfu_workspace/ocr/OCR技术相关/数据格式/1.txt")));
            regionList = new CopyOnWriteArraySet<>();
            String readLine = "";
            while((readLine = br.readLine()) != null){
                String[] rls = readLine.split(" ");
                if(rls.length == 4){
                    String s = rls[2];
                    regionList.add(s.replace("\t", ""));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


//        List<List<String>> partition = Lists.partition(regionList, 4000);

//        System.out.println(regionList);

//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        List<String> finalRegionList = regionList;


        //                        "a8mBcvep5ppKDGLyOmQ3pVZ3xvpy067A"
//                        "G6ayv2ItCj4xDzekYBawanvVNYxB4fsY"
//                        "of0oMn0Cdy5YEK5htxILZoWsRGW57Q6M"
//                        "931o8YLsQqYupwLzsX3yp5oGqq2GvQbe"
//                        "hCwBd8X9yOcQh3aM1jSdHTBtsT8SYFQU"
//                        "92OcRMoZRFISRjBvDIj6H3iK9SSLh40M"
//                        "FeNKeeffiTzGI26Pccg1C2KzoKeGjvue"
//                        "EUfGbbzfZZABTivdSaW6WCFS7ZkUNzKV"

        for (String s1 : regionList) {
            try {
                Map<String, Object> param = new HashMap<>();
                param.put("query", s2);
                param.put("tag", s2);
                param.put("region", s1);
                param.put("output", "json");

                param.put("ak", "maUzEKQn5OLtYhYZ280pQ6WZ8scbPY08");
                param.put("page_size", 20);
                String url = "http://api.map.baidu.com/place/v2/search";
                HttpUtils httpUtils = new HttpUtils();
                String res = httpUtils.sendGet2(url, param);
                Map<String, Object> resMap = JSONObject.parseObject(res, Map.class);
                Integer status = (Integer) resMap.get("status");
                if(status == 0){
                    Integer total = (Integer) resMap.get("total");
                    List<Map<String, Object>> resList = (List<Map<String, Object>>) resMap.get("results");
                    String province = (String) resList.get(0).get("province");
                    String city = (String) resList.get(0).get("city");
                    Integer totalPage = total / 20 + 1;
                    MapDto mapDto = new MapDto();
                    List<String> mapData = (List<String>) mapDto.getMapData(new ArrayList<>(), url, param, totalPage, s2,  1);
                    String s3 = s2 + "/" + province + "/" + city;
                    String path = "/Users/zhanglu/Desktop/lu/workspace/xinfu_workspace/ocr/OCR技术相关/数据格式/"+s3;
                    File file = new File(path);
                    if(!file.exists()){
                        file.mkdirs();
                    }
                    path += "/"+s1+".txt";
                    System.out.println("写入文件" + path);
                    FileWriter fw=new FileWriter(new File(path));
                    //写入中文字符时会出现乱码
                    BufferedWriter bw = new BufferedWriter(fw);
                    for (String s : mapData) {
                        if((s.indexOf("东门") < 0) && (s.indexOf("南门") < 0) && (s.indexOf("西门") < 0) && (s.indexOf("北门") < 0) &&
                                (s.indexOf("东北门") < 0) && (s.indexOf("东南门") < 0) && (s.indexOf("西北门") < 0) && (s.indexOf("西南门") < 0) &&
                                (s.indexOf("停车场") < 0)){
                            bw.write(s + "\t\n");
                        }
                    }
                    bw.close();
                    fw.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

//        List<String> keys = new ArrayList<>();
//        keys.add("a8mBcvep5ppKDGLyOmQ3pVZ3xvpy067A");
//        keys.add("G6ayv2ItCj4xDzekYBawanvVNYxB4fsY");
//        keys.add("of0oMn0Cdy5YEK5htxILZoWsRGW57Q6M");
//        keys.add("931o8YLsQqYupwLzsX3yp5oGqq2GvQbe");
//        keys.add("hCwBd8X9yOcQh3aM1jSdHTBtsT8SYFQU");
//        keys.add("92OcRMoZRFISRjBvDIj6H3iK9SSLh40M");
//        keys.add("FeNKeeffiTzGI26Pccg1C2KzoKeGjvue");
//        keys.add("EUfGbbzfZZABTivdSaW6WCFS7ZkUNzKV");
//        int count = 0;
//        for (List<String> strings : partition) {
//            count ++;
//            new Thread(() -> {
//                for (String s1 : strings) {
//                    try {
//                        Map<String, Object> param = new HashMap<>();
//                        param.put("query", s2);
//                        param.put("tag", s2);
//                        param.put("region", s1);
//                        param.put("output", "json");
//
//                        param.put("ak", "wqBXfIN3HkpM1AHKWujjCdsi");
//                        param.put("page_size", 20);
//                        String url = "http://api.map.baidu.com/place/v2/search";
//                        HttpUtils httpUtils = new HttpUtils();
//                        String res = httpUtils.sendGet2(url, param);
//                        Map<String, Object> resMap = JSONObject.parseObject(res, Map.class);
//                        Integer status = (Integer) resMap.get("status");
//                        if(status == 0){
//                            Integer total = (Integer) resMap.get("total");
//                            List<Map<String, Object>> resList = (List<Map<String, Object>>) resMap.get("results");
//                            String province = (String) resList.get(0).get("province");
//                            String city = (String) resList.get(0).get("city");
//                            Integer totalPage = total / 20 + 1;
//                            MapDto mapDto = new MapDto();
//                            List<String> mapData = (List<String>) mapDto.getMapData(new ArrayList<>(), url, param, totalPage, s2,  1);
//                            String s3 = s2 + "/" + province + "/" + city;
//                            String path = "/Users/zhanglu/Desktop/lu/workspace/xinfu_workspace/ocr/OCR技术相关/数据格式/"+s3;
//                            File file = new File(path);
//                            if(!file.exists()){
//                                file.mkdirs();
//                            }
//                            path += "/"+s1+".txt";
//                            System.out.println("写入文件" + path);
//                            FileWriter fw=new FileWriter(new File(path));
//                            //写入中文字符时会出现乱码
//                            BufferedWriter bw = new BufferedWriter(fw);
//                            for (String s : mapData) {
//                                bw.write(s + "\t\n");
//                            }
//                            bw.close();
//                            fw.close();
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }, "").start();
//        }

    }

    @Override
    public void t2(String s2) {
        Set<String> regionList = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("/Users/zhanglu/Desktop/lu/workspace/xinfu_workspace/ocr/OCR技术相关/数据格式/数据采集(安徽)/详细地址/固定居住地/统计-经纬度.txt")));
            regionList = new CopyOnWriteArraySet<>();
            String readLine = "";
            while((readLine = br.readLine()) != null){
                if(ToolUtil.isNotEmpty(readLine)){
                    regionList.add(readLine);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String s1 : regionList) {
            try {
                String[] rls = s1.split(" ---> ");

                Map<String, Object> param = new HashMap<>();
                param.put("query", "工作$单位$公司$服务$店$超市$医院$大厦");
                param.put("tag", "工作$单位$公司$服务$店$超市$医院$大厦");
                param.put("location", rls[1]);
                param.put("output", "json");
                param.put("ak", "maUzEKQn5OLtYhYZ280pQ6WZ8scbPY08");
                param.put("page_size", 10);
                String url = "http://api.map.baidu.com/place/v2/search";
                HttpUtils httpUtils = new HttpUtils();
                String res = httpUtils.sendGet2(url, param);
                Map<String, Object> resMap = JSONObject.parseObject(res, Map.class);
                Integer status = (Integer) resMap.get("status");
                if(status == 0){
                    Integer total = (Integer) resMap.get("total");
                    List<Map<String, Object>> resList = (List<Map<String, Object>>) resMap.get("results");
                    String province = (String) resList.get(0).get("province");
                    String city = (String) resList.get(0).get("city");
                    Integer totalPage = total / 20 + 1;
                    MapDto mapDto = new MapDto();
                    List<String> mapData = (List<String>) mapDto.getMapData2(new ArrayList<>(), url, param, totalPage, s2,  1);
                    String s3 = s2 + "/" + province + "/" + city;
                    String path = "/Users/zhanglu/Desktop/lu/workspace/xinfu_workspace/ocr/OCR技术相关/数据格式/"+s3;
                    File file = new File(path);
                    if(!file.exists()){
                        file.mkdirs();
                    }
                    path += "/"+rls[0]+".txt";
                    System.out.println("写入文件" + path);
                    FileWriter fw=new FileWriter(new File(path));
                    //写入中文字符时会出现乱码
                    BufferedWriter bw = new BufferedWriter(fw);
                    for (String s : mapData) {
                        if((s.indexOf("东门") < 0) && (s.indexOf("南门") < 0) && (s.indexOf("西门") < 0) && (s.indexOf("北门") < 0) &&
                                (s.indexOf("东北门") < 0) && (s.indexOf("东南门") < 0) && (s.indexOf("西北门") < 0) && (s.indexOf("西南门") < 0) &&
                                (s.indexOf("停车场") < 0)){
                            bw.write(s + "\t\n");
                        }
                    }
                    bw.close();
                    fw.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void getRanger() throws Exception {
        String src = "/Users/zhanglu/Desktop/lu/workspace/xinfu_workspace/ocr/OCR技术相关/数据格式/数据采集(安徽)/详细地址/固定居住地/统计.txt";
        String target = "/Users/zhanglu/Desktop/lu/workspace/xinfu_workspace/ocr/OCR技术相关/数据格式/数据采集(安徽)/详细地址/固定居住地/统计-经纬度.txt";
        BufferedReader br = new BufferedReader(new FileReader(new File(src)));
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(target)));
        String line = "";
        while((line = br.readLine()) != null){
            String[] temp = line.split(":");
            if(!line.contains("市级统计") && !temp[0].equals("")){
                List<Map<String, String>> lms = this.baseMapper.regionLike(temp[0]);
                for (Map<String, String> lm : lms) {
                    String mername = lm.get("mername");
                    String lat = lm.get("lat");
                    String lng = lm.get("lng");
                    String[] lens = mername.split(",");
                    if(lens.length == 3){
                        System.out.println(temp[0] + " ---> " + lat + "," + lng + "\t\n");
                        bw.write(temp[0] + " ---> " + lat + "," + lng + "\t\n");
                    }
                }
            }
            if(!line.contains("区县级统计") && !temp[0].equals("")){
                List<Map<String, String>> lms = this.baseMapper.regionLike(temp[0]);
                for (Map<String, String> lm : lms) {
                    String mername = lm.get("mername");
                    String lat = lm.get("lat");
                    String lng = lm.get("lng");
                    String[] lens = mername.split(",");
                    if(lens.length == 4){
                        System.out.println(temp[0] + " ---> " + lat + "," + lng + "\t\n");
                        bw.write(temp[0] + " ---> " + lat + "," + lng + "\t\n");
                    }
                }
            }
        }
        bw.close();
        br.close();
    }

    @Override
    public List<Article> articleList() {
        List<Article> articleList = this.baseMapper.articleList(97);
        log.info("第一次：" + articleList.get(0).getAuthor());

        Article article = new Article();
        article.setId(articleList.get(0).getId());
        article.setAuthor("666");

        //mybatisplus自己封装的
        this.baseMapper.updateById(article);

        //自己手动编写mapper.xml 实现的
//        this.baseMapper.updateById2(article);

        List<Article> articleList2 = this.baseMapper.articleList(97);
        log.info("第二次：" + articleList2.get(0).getAuthor());
        return articleList;
    }

    @Override
    public Integer testSave() throws Exception {
        return this.baseMapper.testSave();
    }


}
