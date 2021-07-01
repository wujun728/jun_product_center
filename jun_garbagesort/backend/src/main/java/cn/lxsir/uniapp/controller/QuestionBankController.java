package cn.lxsir.uniapp.controller;


import cn.lxsir.uniapp.entity.KeywordSearchNum;
import cn.lxsir.uniapp.entity.QuestionBank;
import cn.lxsir.uniapp.mapper.QuestionBankMapper;
import cn.lxsir.uniapp.service.KeywordResultService;
import cn.lxsir.uniapp.service.KeywordSearchNumService;
import cn.lxsir.uniapp.service.QuestionBankService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 题库表 前端控制器
 * </p>
 *
 * @author luoxiang
 * @since 2019-07-07
 */
@RestController
@RequestMapping("/qb")
@Api(value = "题库 Controller",tags = {"题库访问接口"})
public class QuestionBankController {

    @Autowired
    QuestionBankService qbService;
    @Autowired
    QuestionBankMapper questionBankMapper;
    @Autowired
    KeywordResultService krService;
    @Autowired
    KeywordSearchNumService ksnService;
    @Autowired
    KeywordSearchNumService searchNumService;


    @GetMapping("/name/{name}")
    @ApiOperation(value = "根据名称模糊查询垃圾分类")
    public R searchQuestionByName(@PathVariable(value = "name") String name){
        if(StringUtils.isEmpty(name)) {
            return null;
        }
        List<QuestionBank> list = qbService.list(new QueryWrapper<QuestionBank>().like("garbage_name", name));
        return R.ok(list);
    }

    @GetMapping("/uniname/{name}")
    @ApiOperation(value = "根据名称精准查询垃圾分类")
    public R searchQuestionByUniName(@PathVariable("name") String name){
        Map<String, Object> map = qbService.searchQuestionByUniName(name);
        return R.ok(map);
    }



    @GetMapping("/type/{type}")
    @ApiOperation(value = "根据类型来进行查询")
    public R searchQuestionByType(@PathVariable("type") Integer type){
        if(StringUtils.isEmpty(type)) {
            return null;
        }
        List<QuestionBank> list = qbService.list(new QueryWrapper<QuestionBank>().eq("garbage_type", type));
        return R.ok(list);
    }

    @GetMapping("/randOne")
    @ApiOperation(value = "生成随机数量的数量")
    public R qbRandOne(@RequestParam(required = false,name = "num") Integer num){
        if(StringUtils.isEmpty(num)) {
            num=1;
        }
        List<QuestionBank> qbRandOne = questionBankMapper.qbRandOne(num);
        return R.ok(qbRandOne);
    }

    @GetMapping("/top10")
    @ApiOperation(value = "查询 最热门的10个垃圾分类的物体")
    public R topTen(){
        List<KeywordSearchNum> list = searchNumService.list(new QueryWrapper<KeywordSearchNum>().orderByDesc("num").last("limit 10"));
        return R.ok(list);
    }


}

