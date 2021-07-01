package cn.lxsir.uniapp.controller;


import cn.lxsir.uniapp.entity.SlideShow;
import cn.lxsir.uniapp.service.SlideShowService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 幻灯片播放表 前端控制器
 * </p>
 *
 * @author luoxiang
 * @since 2019-07-07
 */
@RestController
@RequestMapping("/slideShow")
@Api(value = "滑动窗口 Controller",tags = {"滑动窗口 图片访问接口"})
public class SlideShowController {

    @Autowired
    SlideShowService service;

    @GetMapping
    @ApiOperation(value = " 获取 滑动窗口的图像加载")
    public R slideShow(){
        List<SlideShow> list = service.list(new QueryWrapper<SlideShow>().eq("user_id", 1).orderByAsc("sort_id"));
        return R.ok(list);
    }

}

