package com.projectm.web;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.framework.common.AjaxResult;
import com.framework.common.constant.Constants;
import com.framework.security.util.RedisCache;
import com.framework.security.util.UserUtil;
import com.projectm.config.WebSocketServer;

import cn.hutool.core.util.StrUtil;

@RestController
@RequestMapping("/index")
public class IndexController  extends BaseController {

    @Autowired
    private RedisCache redisCache;
    @Autowired
    private WebSocketServer webSocketServer;
    
    @PostMapping("/index/bindClientId")
    @ResponseBody
    public AjaxResult bindClientId(@RequestParam Map<String,Object> mmap)  throws Exception {
        String client_id = MapUtils.getString(mmap,"client_id");
        String uid = MapUtils.getString(mmap,"uid");
        if(StrUtil.isEmpty(uid)){
            uid = UserUtil.getLoginUser().getUser().getCode();
        }
        Set<String> stringSet = redisCache.getCacheObject(Constants.WEBSOCKET + uid);
        if (stringSet == null) {
            stringSet = new HashSet<>();
        }
        stringSet = WebSocketServer.cleanInfo(stringSet);
        stringSet.add(client_id);
        redisCache.setCacheObject(Constants.WEBSOCKET + uid, stringSet, 120, TimeUnit.MINUTES);
        return AjaxResult.success();
    }
}