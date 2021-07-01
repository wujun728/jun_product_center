package cn.lxsir.uniapp.service.impl;

import cn.lxsir.uniapp.entity.KeywordResult;
import cn.lxsir.uniapp.entity.KeywordSearchNum;
import cn.lxsir.uniapp.entity.QuestionBank;
import cn.lxsir.uniapp.mapper.QuestionBankMapper;
import cn.lxsir.uniapp.service.KeywordResultService;
import cn.lxsir.uniapp.service.KeywordSearchNumService;
import cn.lxsir.uniapp.service.QuestionBankService;
import cn.lxsir.uniapp.vo.QuestionBankVo;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 题库表 服务实现类
 * </p>
 *
 * @author luoxiang
 * @since 2019-07-13
 */
@Service
@Log4j2
public class QuestionBankServiceImpl extends ServiceImpl<QuestionBankMapper, QuestionBank> implements QuestionBankService {

    @Autowired
    QuestionBankService qbService;
    @Autowired
    KeywordResultService krService;
    @Autowired
    KeywordSearchNumService ksnService;


    @Override
    public Map<String, Object> searchQuestionByUniName(String name) {
        if(StringUtils.isEmpty(name)) {
            return null;
        }
        QuestionBank questionBank = qbService.getOne(new QueryWrapper<QuestionBank>().eq("garbage_name", name));
        List<QuestionBank> list = qbService.list(new QueryWrapper<QuestionBank>().like("garbage_name", name));
        boolean keywordResultsSave = krService.save(KeywordResult.builder().keyword(name).result(JSON.toJSONString(list)).build());
        KeywordSearchNum keywordNum = ksnService.getOne(new QueryWrapper<KeywordSearchNum>().eq("keyword", name));
        if(StringUtils.isEmpty(keywordNum)){
            keywordNum=KeywordSearchNum.builder().keyword(name).num(1).build();
        }else{
            keywordNum.setNum(keywordNum.getNum()+1);
        }
        ksnService.saveOrUpdate(keywordNum);
        HashMap<String, Object> map = new HashMap<>();
        map.put("uni",questionBank);
        map.put("results", list);
        return map;
    }

    @Override
    public Map<String, Object> uploadExcel(String filepath) {
        Map<String, Object> map = new HashMap<>();
        if(StringUtils.isEmpty(filepath)){
            map.put("error","空文件");
        }
        InputStream inputStream;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(new File(filepath)));
            List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(1, 1,QuestionBankVo.class));
            inputStream.close();
            List<QuestionBank> list = new ArrayList<>();
            for (Object item : data) {
                log.info(item);
                QuestionBankVo bankVo = (QuestionBankVo) item;
                QuestionBank bank = new QuestionBank();
                BeanUtils.copyProperties(bankVo,bank);
                list.add(bank);
            }
            log.debug(list);
            qbService.saveBatch(list);
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
            map.put("error","报错了,可能有重复录入的可能");
            return map;
        }
        map.put("success","成功");
        return map;
    }
}
