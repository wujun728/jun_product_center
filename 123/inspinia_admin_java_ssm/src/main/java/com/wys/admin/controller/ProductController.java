package com.wys.admin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wys.admin.pojo.Category;
import com.wys.admin.pojo.Product;
import com.wys.admin.service.CategoryService;
import com.wys.admin.service.ProductService;
import com.wys.util.bean.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;

/**
 * Created by wangyushuai@fang.com on 2018/6/14.
 */

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @RequestMapping("/list")
    public String List(Integer cid,Model model,Page page){
        if(cid == null) cid = 1;
        page.setCount(5);
        Category c = categoryService.get(cid);
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Product> ps = productService.list(cid);
        int total = (int) new PageInfo<>(ps).getTotal();
        page.setTotal(total);
        page.setParam("&cid=" + c.getId());

        model.addAttribute("ps",ps);
        model.addAttribute("c",c);
        model.addAttribute("page",page);
        return "admin/product/list";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(Model model,Product product) {
        product.setCreateDate(new Date());
        boolean isSuccess =  productService.add(product);
        return "redirect: list?cid=" + product.getCid();
    }

    @RequestMapping(value = "/delete")
    public String  delete(int id) {
        Product product = productService.get(id);
        if(product != null) {
           productService.delete(id);
        }
        return "redirect:list?cid=" + product.getCid();
    }

}

