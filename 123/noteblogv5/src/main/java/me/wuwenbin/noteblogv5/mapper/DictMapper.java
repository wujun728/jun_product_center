package me.wuwenbin.noteblogv5.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.wuwenbin.noteblogv5.annotation.MybatisMapper;
import me.wuwenbin.noteblogv5.model.entity.Dict;

import java.util.List;
import java.util.Map;

/**
 * @author wuwen
 */
@MybatisMapper
public interface DictMapper extends BaseMapper<Dict> {

    /**
     * 查询标签使用数到首页标签面板上显示
     *
     * @return
     */
    List<Map<String, Object>> findTagsTab();

    /**
     * 查找商品分类
     *
     * @return
     */
    Dict findProductDict();
}
