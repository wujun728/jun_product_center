package cn.lxsir.uniapp.service.impl;

import cn.lxsir.uniapp.common.AsrMain;
import cn.lxsir.uniapp.common.DemoException;
import cn.lxsir.uniapp.entity.QuestionBank;
import cn.lxsir.uniapp.service.BaiduService;
import cn.lxsir.uniapp.service.ImageClassifyService;
import cn.lxsir.uniapp.service.QuestionBankService;
import cn.lxsir.uniapp.service.SpeechClassifyService;
import com.alibaba.fastjson.JSON;
import com.baidu.aip.imageclassify.AipImageClassify;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Auth:luoxiang
 * Time:2019/7/7 3:27 PM
 * Desc:    百度服务接口
 */
@Service
@Log4j2
public class BaiduServiceImpl implements BaiduService {

    @Value("${baidu.appid}")
    String appid;
    @Value("${baidu.apikey}")
    String apikey;
    @Value("${baidu.secretkey}")
    String secretkey;

    @Value("${upload.image.path}")
    String imagePath;
    @Value("${upload.record.path}")
    String recordPath;

    @Autowired
    QuestionBankService qbService;

    @Autowired
    AsrMain asrMain;

    @Autowired
    ImageClassifyService imageClassifyService;

    @Autowired
    SpeechClassifyService speechClassifyService;


    @Override
    public Map<String, Object> imageClassify(String filename) {
        System.out.println("111222333");
        AipImageClassify client = new AipImageClassify(appid, apikey, secretkey);
        HashMap<String, String> options = new HashMap<>();
//        options.put("baike_num", "5");
        JSONObject res = client.advancedGeneral(filename, options);
        JSONArray results = res.getJSONArray("result");
        JSONObject resultVo = results.getJSONObject(0);
        log.error("score:"+resultVo.getDouble("score"));
        log.error(resultVo.getDouble("score")>0.8);
        QuestionBank questionBankOne=null;
        List<QuestionBank> questionBanks = new ArrayList<>();
        if(resultVo.getDouble("score")>0.8){
            questionBankOne = qbService.getOne(new QueryWrapper<QuestionBank>().eq("garbage_name", resultVo.getString("keyword")));  //异步保存 识别结果
            questionBanks = qbService.list(new QueryWrapper<QuestionBank>().like("garbage_name", resultVo.getString("keyword")));  //异步保存 识别结果
        }
        /**  查询多个关键字
        QueryWrapper<QuestionBank> queryWrapper = new QueryWrapper<>();
        List<QuestionBank> questionBanks = new ArrayList<>();
        if (!StringUtils.isEmpty(results) && results.length() > 1) {
            for (int i = 0; i < results.length(); i++) {
                queryWrapper.like("garbage_name", results.getJSONObject(i).getString("keyword"));
                if (i != results.length()) {
                    queryWrapper.or();
                }
            }
            questionBanks = qbService.list(queryWrapper);
        }
         */

        imageClassifyService.imageHandle(filename, res, resultVo, null, null);
        //log.info("keyword："+resultVo.getString("keyword"));
        //log.info("results："+ questionBanks.toString());
        //log.info("response:"+res.toString());
        Map<String, Object> map = new HashMap<>();
        map.put("results", questionBanks);
        map.put("keyword", resultVo.getString("keyword"));
        map.put("response", res.toString());
        map.put("uni",questionBankOne);
        return map;

    }


    @Override
    public Map<String, Object> apiSpecch(String fileName) {
        String speechResult = "";
        List<QuestionBank> list = new ArrayList<>();
        String speechStr = "";
        try {
            speechResult = asrMain.runJsonPostMethod(fileName);
            log.info(speechResult);
            com.alibaba.fastjson.JSONObject object = JSON.parseObject(speechResult);
            com.alibaba.fastjson.JSONArray results = object.getJSONArray("result");
            String[] split = results.getString(0).split("。");
            if(split.length==0) throw new DemoException("语音识别中出了点小差");
            speechStr = split[0];
            list = qbService.list(new QueryWrapper<QuestionBank>().like("garbage_name", speechStr));

            System.out.println(object.toString());
        } catch (DemoException e) {
            log.error("demoException:" + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            log.error("IOException:" + e.getMessage());
            e.printStackTrace();
        }catch (Exception e){
            log.error("Exception:" + e.getMessage());
        }
        QuestionBank questionBankOne = qbService.getOne(new QueryWrapper<QuestionBank>().eq("garbage_name", speechStr));

        log.error("speech 111");
        speechClassifyService.speechHandle(fileName, speechResult, list, speechStr, questionBankOne);         //异步保存 识别结果
        log.error("speech 222");

        log.info(speechResult);
        System.out.println("---");
        log.info("speech:——> keyword:"+ speechStr);
        log.info("speech:——> results:"+list.toString());
        log.info("speech:——> response:"+speechResult);
        Map<String, Object> map = new HashMap<>();
        map.put("keyword", speechStr);
        map.put("results", list);
        map.put("response", speechResult);
        map.put("uni",questionBankOne);
        return map;
    }



}
