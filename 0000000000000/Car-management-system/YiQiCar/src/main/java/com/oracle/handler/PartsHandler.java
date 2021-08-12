package com.oracle.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oracle.service.PartsService;
import com.oracle.vo.Parts;

@Controller
@RequestMapping("/pages/partssys/parts/")
public class PartsHandler {

	@Autowired
	PartsService service;

	/**
	 * 分页查询配件
	 * @param map
	 * @param start
	 * @return
	 */
	
	@RequestMapping("/partslist/{start}")
	public String partsList(Map<String, Object> map, @PathVariable("start") Integer start) {

		List<Parts> list = new ArrayList<Parts>();

		PageHelper.startPage(start, 5);

		list = service.getAll();

		PageInfo<Parts> info = new PageInfo<Parts>(list);

		map.put("pageInfo", info);

		return "/pages/partssys/parts/partslist";

	}

	@RequestMapping("/search")
	public String getPartsByLike(String PartsName,Map<String, Object> map) {
		
			List<Parts> list = new ArrayList<Parts>();

			list = service.getPartsByLike(PartsName);
			
			map.put("pageInfo", list);

			return "pages/partssys/parts/partslist2";
		
			
	}

	@RequestMapping(value = "/{path}")
	public String frame(@PathVariable("path") String path) {
		return "pages/partssys/parts/" + path;
	}
	
	/**
	 * 增加配件
	 * @param p
	 * @return
	 */
	
	@RequestMapping("/insertParts")
	public String insertParts(Parts p) {
		service.insert(p);
		return "redirect:/pages/partssys/parts/partslist/1";
	}
	
	@RequestMapping("/selectById")
	public String selectById(String partsid,HttpSession session) {

		int id = Integer.valueOf(partsid);
		Integer Id = new Integer(id);
		Parts p = service.selectById(Id);
		System.out.println(p);
		session.setAttribute("parts", p);
		
		return "redirect:/pages/partssys/parts/partsedit";
	}
	
	@RequestMapping("/jump")
	public String jump(@RequestParam("num") int num) {
		
		return "redirect:/pages/partssys/parts/partslist/"+num;
	}
	
	@RequestMapping("/update")
	public String update(Parts p) {
		
		service.update(p);
		return "redirect:/pages/partssys/parts/partslist/1";
	}
	
	@RequestMapping("/deleteById")
	public String delete(@RequestParam("partsid") Integer partsid) {
		
		service.delete(partsid);
		return "redirect:/pages/partssys/parts/partslist/1";
	}
}
