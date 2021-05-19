package com.platform.api;

import com.platform.annotation.IgnoreAuth;
import com.platform.oss.OSSFactory;
import com.platform.util.ApiBaseAction;
import com.platform.utils.RRException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传
 *
 * @author lipengjun
 * @date 2017年11月20日 下午3:29:40
 */
@Api(value = "ApiUploadController|一个用来文件上传的控制器")
@RestController
@RequestMapping("/api/upload")
public class ApiUploadController extends ApiBaseAction {

    /**
     * 上传文件
     */
    @ApiOperation(value = "上传文件", notes = "上传文件")
    @ApiImplicitParam(paramType = "query", name = "file", value = "文件", required = true, dataType = "MultipartFile")
    @IgnoreAuth
    @PostMapping("/upload")
    public Object upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }
        //上传文件
        String url = OSSFactory.build().upload(file);
        return toResponsSuccess(url);
    }
}