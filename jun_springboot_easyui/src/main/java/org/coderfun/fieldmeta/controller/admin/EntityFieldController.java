package org.coderfun.fieldmeta.controller.admin;

import java.util.List;

import org.coderfun.fieldmeta.dao.EntityFieldDAO;
import org.coderfun.fieldmeta.entity.EntityField;
import org.coderfun.fieldmeta.service.EntityFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import klg.j2ee.common.model.EasyUIPage;
import klg.j2ee.common.model.JsonData;

@Controller("adminEntityFieldController")
@RequestMapping("/admin/action/entityfield")
public class EntityFieldController {
	@Autowired
	EntityFieldService entityFieldService;


	@ResponseBody
	@RequestMapping(value = "/update_sort", method = RequestMethod.POST)
	public JsonData updateSort(@RequestBody List<EntityField> entityFields) {

		entityFieldService.executeUpdate(EntityFieldDAO.UPDATE_SORT, entityFields);
		return JsonData.success();
	}


	@ResponseBody
	@RequestMapping("/findpage")
	public EasyUIPage findpage(@ModelAttribute EntityField entityField, @RequestParam int page, @RequestParam int rows) {
		Pageable pageable = new PageRequest(page < 1 ? 0 : page - 1, rows, new Sort(Direction.ASC, "columnSort"));
		Page<EntityField> pageData = entityFieldService.findPage(entityField, pageable);
		return new EasyUIPage(pageData);
	}

	@ResponseBody
	@RequestMapping("/findlist")
	public JsonData findlist(@ModelAttribute EntityField entityField) {

		List<EntityField> listData = entityFieldService.findList(entityField, new Sort(Direction.ASC, "columnSort"));
		return JsonData.success(listData);
	}
}
