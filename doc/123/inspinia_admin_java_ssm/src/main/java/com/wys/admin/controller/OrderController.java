package com.wys.admin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wys.admin.pojo.Order;
import com.wys.admin.service.OrderService;
import com.wys.util.bean.DTRequestModel;
import com.wys.util.bean.DTResponseModel;
import com.wys.util.bean.JsonResult;
import com.wys.util.bean.Page;
import jdk.nashorn.internal.runtime.Debug;
import org.apache.ibatis.annotations.Param;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by wangyushuai@fang.com on 2018/7/17.
 */
@Controller
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    OrderService service;

    @RequestMapping(value = "/list")
    public String list(Model model) {
        return "/admin/order/list";
    }

    @ResponseBody
    @RequestMapping(value = "/ajaxList")
    public String list(
            @RequestParam(value = "draw") int draw,
            @RequestParam(value = "start",defaultValue = "0") int start,
            @RequestParam(value="length",defaultValue = "10") int length,
            @RequestParam(value="search[value]")String searchValue
    ) {
        int allCount = 0;
        PageHelper.offsetPage(start,length);
        if (! searchValue.isEmpty()) {

        }
        List<Order> orderList = service.list();
        allCount = (int) new PageInfo<>(orderList).getTotal();
        DTResponseModel<List<Order>> result = new DTResponseModel<>(draw,allCount,allCount,orderList);
        return new JSONObject(result).toString();
    }

}
