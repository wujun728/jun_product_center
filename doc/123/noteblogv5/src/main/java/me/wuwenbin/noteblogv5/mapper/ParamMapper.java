package me.wuwenbin.noteblogv5.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.wuwenbin.noteblogv5.annotation.MybatisMapper;
import me.wuwenbin.noteblogv5.model.entity.Param;

/**
 * created by Wuwenbin on 2019-07-23 at 14:55
 *
 * @author wuwenbin
 */
@MybatisMapper
public interface ParamMapper extends BaseMapper<Param> {

    /**
     * 更新参数
     *
     * @param name
     * @param value
     */
    void updateValueByName(@org.apache.ibatis.annotations.Param("name") String name, @org.apache.ibatis.annotations.Param("value") Object value);

}
