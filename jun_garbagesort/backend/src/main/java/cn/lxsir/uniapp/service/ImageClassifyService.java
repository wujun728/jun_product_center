package cn.lxsir.uniapp.service;

import cn.lxsir.uniapp.entity.ImageClassify;
import cn.lxsir.uniapp.entity.QuestionBank;
import com.baomidou.mybatisplus.extension.service.IService;
import org.json.JSONObject;

import java.util.List;

/**
 * <p>
 * 图像识别记录 服务类
 * </p>
 *
 * @author luoxiang
 * @since 2019-07-13
 */
public interface ImageClassifyService extends IService<ImageClassify> {

    void imageHandle(String filename, JSONObject res, JSONObject resultVo, List<QuestionBank> questionBanks, QuestionBank questionBankOne);

}
