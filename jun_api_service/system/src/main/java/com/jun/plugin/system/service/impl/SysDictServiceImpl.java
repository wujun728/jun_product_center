package com.jun.plugin.system.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jun.plugin.system.entity.SysDictDetailEntity;
import com.jun.plugin.system.entity.SysDictEntity;
import com.jun.plugin.system.mapper.SysDictDetailMapper;
import com.jun.plugin.system.mapper.SysDictMapper;
import com.jun.plugin.system.service.SysDictService;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据字典 服务类
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
@Service("sysDictService")
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDictEntity> implements SysDictService {

    @Resource
    private SysDictDetailMapper sysDictDetailMapper;

    /**
     * 根据字典类型查询字典数据信息
     *
     * @param name 字典名称
     * @return 参数键值
     **/
    @Override
    public JSONArray getType(String name) {
        if (StringUtils.isEmpty(name)) {
            return new JSONArray();
        }
        //根据名称获取字典
        SysDictEntity dict = this.getOne(Wrappers.<SysDictEntity>lambdaQuery().eq(SysDictEntity::getName, name));
        if (dict == null || dict.getId() == null) {
            return new JSONArray();
        }
        //获取明细
        List<SysDictDetailEntity> list = sysDictDetailMapper.selectList(Wrappers.<SysDictDetailEntity>lambdaQuery().eq(SysDictDetailEntity::getDictId, dict.getId()).orderByAsc(SysDictDetailEntity::getSort));
        return JSONArray.parseArray(JSON.toJSONString(list));
    }
    
    /**
     * 根据字典类型查询字典数据信息
     *
     * @param name 字典名称
     * @return 参数键值
     **/
    @Override
    public JSONArray getType2(String name) {
    	if (StringUtils.isEmpty(name)) {
    		return new JSONArray();
    	}
    	//根据名称获取字典
    	List<SysDictEntity> dicts = this.list(Wrappers.<SysDictEntity>lambdaQuery().like(SysDictEntity::getName, name));
    	if (dicts == null || dicts.size() < 1 ) {
    		return new JSONArray();
    	}
    	List<String> str = new ArrayList<String>(); 
    	for(SysDictEntity obj : dicts) {
    		str.add(obj.getId());
    	}
    	//获取明细
    	List<SysDictDetailEntity> list = sysDictDetailMapper.selectList(Wrappers.<SysDictDetailEntity>lambdaQuery().in(SysDictDetailEntity::getDictId, str));
    	return JSONArray.parseArray(JSON.toJSONString(list));
    }

}