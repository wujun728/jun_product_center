package me.wuwenbin.noteblogv5.service.impl.login;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import me.wuwenbin.noteblogv5.constant.NBV5;
import me.wuwenbin.noteblogv5.enums.OperateType;
import me.wuwenbin.noteblogv5.enums.RoleEnum;
import me.wuwenbin.noteblogv5.mapper.UserCoinRecordMapper;
import me.wuwenbin.noteblogv5.model.json.ResultBeanObj;
import me.wuwenbin.noteblogv5.model.bo.login.GithubLoginData;
import me.wuwenbin.noteblogv5.model.entity.Param;
import me.wuwenbin.noteblogv5.model.entity.User;
import me.wuwenbin.noteblogv5.model.entity.UserCoinRecord;
import me.wuwenbin.noteblogv5.service.LoginService;
import me.wuwenbin.noteblogv5.service.UserService;
import me.wuwenbin.noteblogv5.service.ParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * created by Wuwenbin on 2019/3/24 at 11:11
 *
 * @author wuwenbin
 */
@Slf4j
@Service("githubLoginService")
public class GithubLoginServiceImpl implements LoginService<ResultBeanObj, GithubLoginData> {

    private final ParamService paramService;
    private final UserService userService;
    private final UserCoinRecordMapper userCoinRecordMapper;

    @Autowired
    public GithubLoginServiceImpl(ParamService paramService, UserService userService, UserCoinRecordMapper userCoinRecordMapper) {
        this.paramService = paramService;
        this.userService = userService;
        this.userCoinRecordMapper = userCoinRecordMapper;
    }

    @Override
    public ResultBeanObj doLogin(GithubLoginData data) {
        try {
            Param githubClientId = paramService.findByName(NBV5.GITHUB_CLIENT_ID);
            Param githubClientSecret = paramService.findByName(NBV5.GITHUB_CLIENT_SECRET);
            String accessTokenUrl = "https://github.com/login/oauth/access_token";
            Map<String, Object> accessTokenUrlParam = new HashMap<>(5);
            accessTokenUrlParam.put("state", NBV5.GITHUB_AUTH_STATE);
            accessTokenUrlParam.put("code", data.getCode());
            accessTokenUrlParam.put("client_id", githubClientId.getValue());
            accessTokenUrlParam.put("client_secret", githubClientSecret.getValue());
            accessTokenUrlParam.put("redirect_uri", data.getCallbackDomain());
            String accessTokenResult = HttpUtil.post(accessTokenUrl, accessTokenUrlParam);
            String accessToken = accessTokenResult.substring(13, accessTokenResult.indexOf("&scope"));
            String userInfoUrl = "https://api.github.com/user?access_token={}";
            userInfoUrl = StrUtil.format(userInfoUrl, accessToken);
            String userInfoResult = HttpUtil.get(userInfoUrl);
            JSONObject jsonObject = JSONUtil.parseObj(userInfoResult);
            String login = jsonObject.getStr("login");
            User githubUser = userService.findByGithub(login, true);
            if (githubUser != null) {
                return ResultBeanObj.ok("授权成功！", "/").put(NBV5.SESSION_USER_KEY, githubUser);
            } else {
                User lockedUser = userService.findByGithub(login, false);
                if (lockedUser != null) {
                    return ResultBeanObj.error("GITHUB登录授权失败，原因：用户已被锁定！");
                }
                String email = jsonObject.getStr("email");
                String avatar = jsonObject.getStr("avatar_url");
                String nickname = jsonObject.getStr("name");
                int cnt = userService.countNickname(nickname);
                nickname = cnt > 0 ? nickname + System.currentTimeMillis() : nickname;
                User registerUser = User.builder()
                        .role(RoleEnum.GITHUB_USER).createDate(new Date())
                        .nickname(nickname).avatar(avatar).username(login)
                        .enable(true)
                        .build();
                if (StrUtil.isNotEmpty(email)) {
                    registerUser.setEmail(email);
                }
                boolean githubRegUser = userService.save(registerUser);
                if (githubRegUser) {
                    userCoinRecordMapper.insert(
                            UserCoinRecord.builder().operateTime(new Date()).operateType(OperateType.INIT_REG)
                                    .operateValue(0).remainCoin(0).remark(OperateType.INIT_REG.getDesc())
                                    .userId(registerUser.getId()).build()
                    );
                    return ResultBeanObj.ok("授权成功！", "/").put(NBV5.SESSION_USER_KEY, registerUser);
                } else {
                    return ResultBeanObj.error("GITHUB授权失败，原因：注册失败！");
                }
            }
        } catch (Exception e) {
            log.error("GITHUB登录授权失败", e);
            return ResultBeanObj.error("GITHUB登录授权失败，原因：{}", e.getMessage());
        }
    }
}
