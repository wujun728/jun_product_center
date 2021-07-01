package cn.lxsir.uniapp.service;

import cn.lxsir.uniapp.entity.QuestionBank;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 题库表 服务类
 * </p>
 *
 * @author luoxiang
 * @since 2019-07-13
 */
public interface QuestionBankService extends IService<QuestionBank> {

    Map<String,Object> searchQuestionByUniName(String name);

    Map<String,Object> uploadExcel(String filePath);


}
