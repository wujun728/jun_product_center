package cn.iocoder.mall.system.application.controller.admins;

import cn.iocoder.common.framework.vo.CommonResult;
import com.qiniu.util.Auth;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admins/file")
@Api("文件模块")
public class FileController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Auth auth;
    @Value("${qiniu.bucket}")
    private String bucket;

    @GetMapping("/get-qiniu-token")
    public CommonResult<String> getQiniuToken() {
        String token = auth.uploadToken(bucket);
        logger.info("[qiniu_token][token({}) get]", token);
        return CommonResult.success(token);
    }

}
