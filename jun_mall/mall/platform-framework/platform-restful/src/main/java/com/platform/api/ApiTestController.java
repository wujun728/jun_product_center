package com.platform.api;

import com.platform.annotation.IgnoreAuth;
import com.platform.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * API测试接口
 *
 * @author lipengjun
 * @date 2017年11月20日 下午3:29:40
 */
@Api(value = "ApiTestController|一个用来测试Token的控制器")
@RestController
@RequestMapping("/api/test")
public class ApiTestController {

    /**
     * 忽略Token验证测试
     */
    @ApiOperation(value = "忽略Token验证测试", notes = "无需token也能访问")
    @IgnoreAuth
    @GetMapping("notToken")
    public R notToken() {
        return R.ok().put("msg", "无需token也能访问。。。");
    }
}
