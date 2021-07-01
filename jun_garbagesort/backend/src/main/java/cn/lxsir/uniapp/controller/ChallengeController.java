package cn.lxsir.uniapp.controller;

import cn.lxsir.uniapp.service.ChallengeResultService;
import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Auth:luoxiang
 * Time:2019/7/15 10:18 PM
 * Desc:    挑战赛结果的统计
 */
@RestController
@RequestMapping("/challenge")
@Api(value = "挑战赛 Controller",tags = {"挑战赛接口统计"})
@Log4j2
public class ChallengeController {

    @Autowired
    ChallengeResultService challengeResultService;

    @PostMapping
    @ApiOperation(value = "处理挑战赛的结果")
    public R challengeResults(@RequestBody  Map<String,Object> map){
         Map<String, Object> map1 = challengeResultService.challengeResults(map);
        return R.ok(map1);
    }
}
