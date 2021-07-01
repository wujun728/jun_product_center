package cn.lxsir.uniapp.mapper;

import cn.lxsir.uniapp.entity.QuestionBank;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 题库表 Mapper 接口
 * </p>
 *
 * @author luoxiang
 * @since 2019-07-07
 */
public interface QuestionBankMapper extends BaseMapper<QuestionBank> {
    List<QuestionBank> qbRandOne(@Param("num") Integer num);
}
