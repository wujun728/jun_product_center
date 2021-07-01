package cn.lxsir.uniapp.service.impl;

import cn.lxsir.uniapp.entity.KeywordResult;
import cn.lxsir.uniapp.entity.KeywordSearchNum;
import cn.lxsir.uniapp.entity.QuestionBank;
import cn.lxsir.uniapp.entity.SpeechClassify;
import cn.lxsir.uniapp.mapper.SpeechClassifyMapper;
import cn.lxsir.uniapp.service.KeywordResultService;
import cn.lxsir.uniapp.service.KeywordSearchNumService;
import cn.lxsir.uniapp.service.SpeechClassifyService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 语音识别记录 服务实现类
 * </p>
 *
 * @author luoxiang
 * @since 2019-07-13
 */
@Service
@Log4j2
public class SpeechClassifyServiceImpl extends ServiceImpl<SpeechClassifyMapper, SpeechClassify> implements SpeechClassifyService {

    @Autowired
    KeywordResultService krService;
    @Autowired
    KeywordSearchNumService ksnService;

    @Async
    @Override
    public void speechHandle(String fileName, String speechResult, List<QuestionBank> list, String speechStr, QuestionBank questionBankOne) {
        log.error("speech 333");
        SpeechClassify speechClassify = SpeechClassify.builder().oneKeyword(speechStr)
                .oneResult(JSON.toJSONString(questionBankOne))
                .allKeyword(speechResult)
                .allResult(JSON.toJSONString(list))
                .userName("张三")
                .filepath(fileName).build();
        this.save(speechClassify);

        String name = speechStr;
        boolean keywordResultsSave = krService.save(KeywordResult.builder().keyword(name).result(JSON.toJSONString(list)).build());
        KeywordSearchNum keywordNum = ksnService.getOne(new QueryWrapper<KeywordSearchNum>().eq("keyword", name));
        if(StringUtils.isEmpty(keywordNum)){
            keywordNum=KeywordSearchNum.builder().keyword(name).num(1).build();
        }else{
            keywordNum.setNum(keywordNum.getNum()+1);
        }
        ksnService.saveOrUpdate(keywordNum);
    }
}
