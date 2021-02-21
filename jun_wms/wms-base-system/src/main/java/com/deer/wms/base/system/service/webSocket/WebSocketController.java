package com.deer.wms.base.system.service.webSocket;

import com.deer.wms.common.core.result.CommonCode;
import com.deer.wms.common.core.result.Result;
import com.deer.wms.common.core.result.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("/websocket/push")
public class WebSocketController {
    //页面请求
    @GetMapping("/socket")
    public ModelAndView socket() {
        ModelAndView mav=new ModelAndView("/socket");
//        mav.addObject("cid", cid);
        return mav;
    }
    //推送数据接口
    /*@ResponseBody
    @RequestMapping("/socket/push")
    public Result pushToWeb(String message) {
        try {
            WebSocketServer.sendInfo(message);
        } catch (IOException e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult(CommonCode.GENERAL_WARING_CODE);
        }
        return ResultGenerator.genSuccessResult();
    }*/
}
