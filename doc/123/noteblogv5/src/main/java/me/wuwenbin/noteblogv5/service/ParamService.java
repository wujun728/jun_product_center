package me.wuwenbin.noteblogv5.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import me.wuwenbin.noteblogv5.mapper.ParamMapper;
import me.wuwenbin.noteblogv5.model.json.ResultBeanObj;
import me.wuwenbin.noteblogv5.model.entity.Param;
import me.wuwenbin.noteblogv5.utils.CacheUtils;
import me.wuwenbin.noteblogv5.utils.NbUtils;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * created by Wuwenbin on 2019-08-05 at 11:43
 *
 * @author wuwenbin
 */
public interface ParamService extends IService<Param> {

    /**
     * 初始化设置的保存
     *
     * @param map
     */
    void saveInitParam(Map<String, Object> map);

    /**
     * 初始化管理员账号
     *
     * @param username
     * @param password
     * @param email
     */
    void initMasterAccount(String username, String password, String email);

    /**
     * 获取初始状态
     *
     * @return
     */
    Param getInitStatus();

    /**
     * 查找参数对象
     *
     * @param name
     * @return
     */
    Param findByName(String name);

    /**
     * 修改开关类型的设置
     *
     * @param name
     * @param value
     * @return
     */
    ResultBeanObj updateSettings(String name, String value);

    /**
     * 更新发送邮件服务器配置
     *
     * @param paramMap
     * @return
     */
    ResultBeanObj updateMailConfig(Map<String, Object> paramMap);

    /**
     * 计算网站运营时间
     *
     * @return
     */
    String getSystemInitDatetime();

    /**
     * 计算时间
     * @return
     */
    long calcRunningDays();

    /**
     * 默认更新操作
     *
     * @param name
     * @param value
     * @return
     */
    default ResultBeanObj update(String name, String value) {
        CacheUtils.getParamCache().clear();
        if (StringUtils.isEmpty(name)) {
            return ResultBeanObj.error("参数 key 不能为空！");
        } else {
            ParamMapper paramMapper = NbUtils.getBean(ParamMapper.class);
            int cnt = paramMapper.selectCount(Wrappers.<Param>query().eq("name", name));
            if (cnt == 0) {
                return ResultBeanObj.error("不存在参数：" + name);
            } else {
                final String val = value == null ? "" : value;
                paramMapper.updateValueByName(name, val);
                CacheUtils.removeParamCache(name);
                return ResultBeanObj.ok(StrUtil.format("更新成功！"));
            }
        }
    }
}
