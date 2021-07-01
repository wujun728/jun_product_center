package com.du.lin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.du.lin.annotation.BizLog;
import com.du.lin.bean.Memo;
import com.du.lin.service.MemoService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MemoController {
	@Autowired
	private MemoService service;


	@RequestMapping(value = "/memopage", method = { RequestMethod.GET })
	public String memoPage(HttpServletRequest request) {
		List<Memo> list = service.getUserMemoList();
		request.setAttribute("listsize", list.size());
		request.setAttribute("memolist", list);
		return "memo";
	}
	@BizLog("添加便签")
	@ResponseBody
	@RequestMapping(value = "/addmemo", method = { RequestMethod.POST })
	public String addMemo(String text, String title, String time) {
		String result = service.addMemo(title, text, time);
		return result;
	}
	@BizLog("删除便签")
	@ResponseBody
	@RequestMapping(value="/deletememo" , method={RequestMethod.POST})
	public String deleteMemo(int id){
		return service.deleteMemo(id);
	}

}
