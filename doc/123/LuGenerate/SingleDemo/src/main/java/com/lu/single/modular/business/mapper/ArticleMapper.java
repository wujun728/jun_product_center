package com.lu.single.modular.business.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lu.single.modular.business.model.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章列表 Mapper 接口
 * </p>
 *
 * @author lu
 * @since 2019-02-07
 */
public interface ArticleMapper extends BaseMapper<Article> {

    @Insert("insert into test_table(id, email, last_name, time) select replace(uuid(),'-',''), email, last_name, now() from test_table")
    Integer testSave();

    @Select("select mername from region")
    List<String> region();

    @Select("select mername,lat,lng from region where mername like concat('%',#{mername},'%')  ")
    List<Map<String, String>> regionLike(String mername);

    List<Article> articleList(Integer id);

    void updateById2(Article article);

}
