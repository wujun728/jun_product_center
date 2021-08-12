package cc.mrbird.web.controller.system;

import cc.mrbird.common.annotation.Log;
import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.common.utils.FileUtils;
import cc.mrbird.system.domain.Dict;
import cc.mrbird.system.service.DictService;
import cc.mrbird.web.controller.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class DictController extends BaseController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DictService dictService;

	@Log("获取字典信息")
	@RequestMapping("dict")
	@PreAuthorize("hasAuthority('dict:list')")
	public String index() {
		return "system/dict/dict";
	}

	@RequestMapping("dict/list")
	@PreAuthorize("hasAuthority('dict:list')")
	@ResponseBody
	public Map<String, Object> dictList(QueryRequest request, Dict dict) {
		return super.selectByPageNumSize(request, () -> this.dictService.findAllDicts(dict,request));
	}

	@RequestMapping("dict/getDict")
	@ResponseBody
	public ResponseBo getDict(Long dictId) {
		try {
			Dict dict = this.dictService.findById(dictId);
			return ResponseBo.ok(dict);
		} catch (Exception e) {
			log.error("获取字典信息失败", e);
			return ResponseBo.error("获取字典信息失败，请联系网站管理员！");
		}
	}

	@Log("新增字典 ")
	@PreAuthorize("hasAuthority('dict:add')")
	@RequestMapping("dict/add")
	@ResponseBody
	public ResponseBo addDict(Dict dict) {
		try {
			this.dictService.addDict(dict);
			return ResponseBo.ok("新增字典成功！");
		} catch (Exception e) {
			log.error("新增字典失败", e);
			return ResponseBo.error("新增字典失败，请联系网站管理员！");
		}
	}

	@Log("删除字典")
	@PreAuthorize("hasAuthority('dict:delete')")
	@RequestMapping("dict/delete")
	@ResponseBody
	public ResponseBo deleteDicts(String ids) {
		try {
			this.dictService.deleteDicts(ids);
			return ResponseBo.ok("删除字典成功！");
		} catch (Exception e) {
			log.error("删除字典失败", e);
			return ResponseBo.error("删除字典失败，请联系网站管理员！");
		}
	}

	@Log("修改字典 ")
	@PreAuthorize("hasAuthority('dict:update')")
	@RequestMapping("dict/update")
	@ResponseBody
	public ResponseBo updateDict(Dict dict) {
		try {
			this.dictService.updateDict(dict);
			return ResponseBo.ok("修改字典成功！");
		} catch (Exception e) {
			log.error("修改字典失败", e);
			return ResponseBo.error("修改字典失败，请联系网站管理员！");
		}
	}

	@RequestMapping("dict/excel")
	@ResponseBody
	public ResponseBo dictExcel(Dict dict) {
		try {
			List<Dict> list = this.dictService.findAllDicts(dict,null);
			return FileUtils.createExcelByPOIKit("字典表", list, Dict.class);
		} catch (Exception e) {
			log.error("导出字典信息Excel失败", e);
			return ResponseBo.error("导出Excel失败，请联系网站管理员！");
		}
	}

	@RequestMapping("dict/csv")
	@ResponseBody
	public ResponseBo dictCsv(Dict dict){
		try {
			List<Dict> list = this.dictService.findAllDicts(dict,null);
			return FileUtils.createCsv("字典表", list, Dict.class);
		} catch (Exception e) {
			log.error("导出字典信息Csv失败", e);
			return ResponseBo.error("导出Csv失败，请联系网站管理员！");
		}
	}
}
