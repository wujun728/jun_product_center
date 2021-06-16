package com.wys.admin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wys.admin.pojo.Category;
import com.wys.admin.service.CategoryService;
import com.wys.util.common.ImageUtil;
import com.wys.util.bean.JsonResult;
import com.wys.util.bean.Page;
import com.wys.util.bean.UploadImageFile;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by wangyushuai@fang.com on 2018/3/8.
 */
@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService service;

    private final static String  categoryImgPath = "_upload/img/category";

    @RequestMapping("/list")
    public String List(Model model, Page page) {
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Category> cs = service.list();
        int total = (int)new PageInfo<>(cs).getTotal();
        page.setTotal(total);
        model.addAttribute("cs",cs);
        model.addAttribute("page",page);
        return "/admin/category/category";
    }

    @ResponseBody
    @RequestMapping("/ajaxList")
    public String GetAll() {
      List<Category> list =  service.list();
      JsonResult<List<Category>> jsonResult = new JsonResult<>();
      if (list != null && list.size() > 0) {
          jsonResult.setMessage("成功");
          jsonResult.setCode(100);
          jsonResult.setData(list);
      }
      return new JSONObject(jsonResult).toString();
    }



    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String Add(Category category, HttpSession session, UploadImageFile categoryImage) throws IOException {
        service.add(category);
        File imageFolder = new File(session.getServletContext().getRealPath(categoryImgPath));
        File file = new File(imageFolder, category.getId() +".jpg");
        if (! file.getParentFile().exists())
            file.getParentFile().mkdirs();
        categoryImage.getImage().transferTo(file);
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img,"jpg",file);
        return "redirect: /admin/category/list";
    }

    @ResponseBody
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public String delete(int id,HttpSession session) {
        boolean status = service.delete(id);
        File imageFolder = new File(session.getServletContext().getRealPath(categoryImgPath));
        File file = new File(imageFolder,id + "jpg");
        file.delete();
        JsonResult<String> result = new JsonResult<>();
        if (status) {
            result.setCode(100);
        } else {
            result.setCode(101);
        }
        return new JSONObject(result).toString();
    }

    @ResponseBody
    @RequestMapping(value = "/get",method = RequestMethod.GET ,produces ="application/json;charset=utf-8" )
    public String get(int id) {
        Category category = service.get(id);
        JsonResult<Category> result = new JsonResult<>();
        if (category != null) {
            result.setCode(100);
            result.setData(category);
        }
        return new JSONObject(result).toString();
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(Category category,HttpSession session,UploadImageFile image) throws IOException {
        boolean status = service.update(category);
        File imageFolder = new File(session.getServletContext().getRealPath(categoryImgPath));
        File file = new File(imageFolder, category.getId() +".jpg");
        if (image != null) {
            if (! file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            else{
                file.delete();
                image.getImage().transferTo(file);
                BufferedImage img = ImageUtil.change2jpg(file);
                ImageIO.write(img,"jpg",file);
            }
        }

        return "redirect: /admin/category/list";
    }






}
