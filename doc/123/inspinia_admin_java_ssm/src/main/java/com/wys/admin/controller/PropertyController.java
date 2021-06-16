package com.wys.admin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wys.admin.pojo.Category;
import com.wys.admin.pojo.Property;
import com.wys.admin.service.CategoryService;
import com.wys.admin.service.PropertyService;
import com.wys.util.bean.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by wangyushuai@fang.com on 2018/5/3.
 */

@Controller
@RequestMapping("/property")
public class PropertyController {
    @Autowired
    PropertyService propertyService;
    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(int cid, Model model, Page page) {
        Category category = categoryService.get(cid);
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Property> properties = propertyService.list(cid);

        int total = (int) new PageInfo<>(properties).getTotal();
        page.setTotal(total);
        page.setParam("&cid="+ category.getId());

        model.addAttribute("properties",properties);
        model.addAttribute("category",category);
        model.addAttribute("page",page);

        return "/admin/property/property";
    }


}
