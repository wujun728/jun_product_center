package com.pearadmin.plugin.sample.sequence.controller;

import com.pearadmin.plugin.framework.sequence.pool.SequencePool;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("sequence")
public class SequenceController {

    @Resource
    private SequencePool sequencePool;

    @GetMapping("genId")
    public String genId(){

        long startTime = System.currentTimeMillis();

        for (int i = 1; i<30000;i++){

            System.out.println("序号:"+sequencePool.getId());
        }
        long endTime = System.currentTimeMillis();

        System.out.println("消耗时间:"+ (endTime - startTime));

        return "访问成功";
    }
}
