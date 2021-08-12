package com.ruoyi.citylife.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.citylife.config.WxMaConfiguration;
import com.ruoyi.citylife.domain.CUser;
import com.ruoyi.citylife.service.ICUserService;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.service.WxLoginService;
import com.ruoyi.framework.web.service.WxUser;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CurrencyEditor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * 微信小程序用户接口
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@RestController
@RequestMapping("/wx/user/{appid}")
@Slf4j
public class WxMaUserController {
    
    @Autowired
    private WxLoginService wxLoginService;
    @Autowired
    private ICUserService icUserService;
    
    /**
     * 登陆接口
     */
    @GetMapping("/login")
    
    public R login(@PathVariable String appid, String code, String token) {
        
        System.out.println(token);
        WxUser wxUser = null;
        if (StringUtils.isNotEmpty(token)) {
            wxUser = wxLoginService.getWxUser(token);
            System.out.println(wxUser);
        }
        
        if (StringUtils.isEmpty(token) || StringUtils.isNull(wxUser)) {
            if (StringUtils.isBlank(code)) {
                return R.error("empty jscode");
            }
            
            final WxMaService wxService = WxMaConfiguration.getMaService(appid);
            
            try {
                WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);
                log.info(session.getSessionKey());
                log.info(session.getOpenid());
                wxUser = new WxUser();
                wxUser.setOpenid(session.getOpenid());
                QueryWrapper<CUser> wrapper = new QueryWrapper<>();
                wrapper.eq("openid", session.getOpenid());
                CUser cUser = icUserService.getOne(wrapper);
                if (StringUtils.isNotNull(cUser)) {
                    if (StringUtils.isNotEmpty(cUser.getNickname())) {
                        wxUser.setNickname(cUser.getNickname());
                        String newToken = wxLoginService.createToken(wxUser);
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("token", newToken);
                        cUser.setNull();
                        map.put("user", cUser);
                        return R.success("登录成功", map);
                        
                    } else {
                        //非首次,未授权
                        String newToken = wxLoginService.createToken(wxUser);
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("token", newToken);
                        map.put("user", null);
                        return R.error("非首次,未授权", map);
                    }
                } else {
                    //首次尝试登陆
                    String newToken = wxLoginService.createToken(wxUser);
                    CUser user = new CUser();
                    user.setOpenid(session.getOpenid());
                    icUserService.save(user);
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("token", newToken);
                    map.put("user", null);
                    return R.error("未登录,请授权", map);
                }
                
                
            } catch (WxErrorException e) {
                log.error(e.getMessage());
                return R.error(e.toString());
            }
            
        } else {
            //token ,wxUser 都不是空
            String nickname = wxUser.getNickname();
            if (StringUtils.isNotBlank(nickname)) {
                QueryWrapper<CUser> wrapper = new QueryWrapper<>();
                wrapper.eq("openid", wxUser.getOpenid());
                CUser cUser = icUserService.getOne(wrapper);
                //更新缓存
                wxLoginService.setWxUser(wxUser);
                HashMap<String, Object> map = new HashMap<>();
                map.put("token", token);
                cUser.setNull();
                map.put("user", cUser);
                return R.success("登录成功", map);
            } else {
                wxLoginService.setWxUser(wxUser);
                HashMap<String, Object> map = new HashMap<>();
                map.put("token", token);
                map.put("user", null);
                return R.error("非首次,未授权",map);
            }
        }
    }
    
    @PostMapping("postAuth")
    public R postAuth(@RequestBody CUser cUser, HttpServletRequest request) {
        String token = wxLoginService.getToken(request);
        WxUser wxUser = wxLoginService.getWxUser(token);
        wxUser.setNickname(cUser.getNickname());
        wxLoginService.setWxUser(wxUser);
        cUser.setOpenid(wxUser.getOpenid());
        QueryWrapper<CUser> wrapper = new QueryWrapper<>();
        wrapper.eq("openid", wxUser.getOpenid());
        icUserService.update(cUser, wrapper);
        return R.success("授权成功");
    }


//    /**
//     * <pre>
//     * 获取用户信息接口
//     * </pre>
//     */
//    @GetMapping("/info")
//    public String info(@PathVariable String appid, String sessionKey,
//                       String signature, String rawData, String encryptedData, String iv) {
//        final WxMaService wxService = WxMaConfiguration.getMaService(appid);
//
//        // 用户信息校验
//        if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
//            return "user check failed";
//        }
//
//        // 解密用户信息
//        WxMaUserInfo userInfo = wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);
//
//        return JsonUtils.toJson(userInfo);
//    }
//
//    /**
//     * <pre>
//     * 获取用户绑定手机号信息
//     * </pre>
//     */
//    @GetMapping("/phone")
//    public String phone(@PathVariable String appid, String sessionKey, String signature,
//                        String rawData, String encryptedData, String iv) {
//        final WxMaService wxService = WxMaConfiguration.getMaService(appid);
//
//        // 用户信息校验
//        if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
//            return "user check failed";
//        }
//
//        // 解密
//        WxMaPhoneNumberInfo phoneNoInfo = wxService.getUserService().getPhoneNoInfo(sessionKey, encryptedData, iv);
//
//        return JsonUtils.toJson(phoneNoInfo);
//    }

}
