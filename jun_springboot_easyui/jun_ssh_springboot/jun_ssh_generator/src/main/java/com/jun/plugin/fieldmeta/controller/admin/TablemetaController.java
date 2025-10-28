package com.jun.plugin.fieldmeta.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jun.plugin.common.model.EasyUIPage;
import com.jun.plugin.common.model.JsonData;
import com.jun.plugin.fieldmeta.entity.EntityField;
import com.jun.plugin.fieldmeta.entity.PageField;
import com.jun.plugin.fieldmeta.entity.Tablemeta;
import com.jun.plugin.fieldmeta.service.TablemetaService;

@Controller("adminTablemetaController")
@RequestMapping("/admin/action/tablemeta")
public class TablemetaController {
	@Autowired
	TablemetaService tablemetaService;

	@ResponseBody
	@RequestMapping("/add")
	public JsonData add(@ModelAttribute Tablemeta tablemeta) {

		tablemetaService.save(tablemeta);
		return JsonData.success();
	}

	@ResponseBody
	@RequestMapping("/save_all")
	public JsonData saveAll(@RequestBody Map<String, String> models) {

		// tablemetaService.save(tablemeta);
		return JsonData.success();
	}

	@ResponseBody
	@RequestMapping("/clone")
	public JsonData clone(@RequestParam(value = "tablemetaIds[]") List<Long> tablemetaIds) {

		tablemetaService.clone(tablemetaIds);
		return JsonData.success();
	}
	
	@ResponseBody
	@RequestMapping("/save_fields")
	public JsonData saveFields(@RequestBody TableMetaModel model, @RequestParam Long tableId) {

		List<EntityField> entityFields = model.entityFields;
		List<PageField> pageFields = model.pageFields;
		tablemetaService.saveFields(tableId, entityFields, pageFields);
		;
		return JsonData.success();
	}
	@ResponseBody
	@RequestMapping("/save_fields_tbname")
	public JsonData saveFieldsTbname(@RequestBody TableMetaModel model, @RequestParam String tableName) {

		List<EntityField> entityFields = model.entityFields;
		List<PageField> pageFields = model.pageFields;
		tablemetaService.saveFieldsTbname(tableName, entityFields, pageFields);
		;
		return JsonData.success();
	}

	@ResponseBody
	@RequestMapping("/edit")
	public JsonData edit(@ModelAttribute Tablemeta tablemeta) {

		tablemetaService.update(tablemeta);
		return JsonData.success();
	}

	@ResponseBody
	@RequestMapping("/delete")
	public JsonData delete(@RequestParam Long id) {

		tablemetaService.delete(id);
		return JsonData.success();
	}
	
	@ResponseBody
	@RequestMapping("/deleteFiledPair")
	public JsonData deleteFiledPair(@RequestParam Long id) {

		tablemetaService.deleteFieldPair(id);
		return JsonData.success();
	}

	@ResponseBody
	@RequestMapping("/findpage")
	public EasyUIPage findpage(@ModelAttribute Tablemeta tablemeta, @RequestParam int page, @RequestParam int rows) {
		Pageable pageable = new PageRequest(page < 1 ? 0 : page - 1, rows, new Sort(Direction.DESC, "id"));
		Page<Tablemeta> pageData = tablemetaService.findPage(tablemeta, pageable);
		return new EasyUIPage(pageData);
	}

	@ResponseBody
	@RequestMapping("/findlist")
	public JsonData findlist(@ModelAttribute Tablemeta tablemeta) {

		List<Tablemeta> listData = tablemetaService.findList(tablemeta, new Sort(Direction.DESC, "id"));
		return JsonData.success(listData);
	}
}
class TableMetaModel {
	public List<EntityField> entityFields = new ArrayList<>();
	public List<PageField> pageFields = new ArrayList<>();
}