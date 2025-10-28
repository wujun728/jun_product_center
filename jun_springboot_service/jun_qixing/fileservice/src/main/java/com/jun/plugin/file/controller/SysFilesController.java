package com.jun.plugin.file.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jun.plugin.common.utils.DataResult;
import com.jun.plugin.file.entity.SysFilesV2Entity;
import com.jun.plugin.file.service.SysFilesService;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文件上传
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
@RestController
@RequestMapping("/sysFiles")
@Api(tags = "文件管理")
@Slf4j
@DataSource(DataSourceType.QIXING)
public class SysFilesController {
	@Resource
	private SysFilesService sysFilesService;

	@ApiOperation(value = "新增")
	@PostMapping("/upload")
//	@PreAuthorize(value = { "sysFiles:add", "sysContent:update", "sysContent:add" }, logical = Logical.OR)
	public DataResult add(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "bizid",required = false) String bizid,
						  @RequestParam(value = "biztype",required = false) String biztype) {
		log.info(biztype);
		log.info(bizid);
		// 判断文件是否空
		if (file == null || file.getOriginalFilename() == null
				|| "".equalsIgnoreCase(file.getOriginalFilename().trim())) {
			return DataResult.fail("文件为空");
		}
		return sysFilesService.saveFile(file, biztype, bizid);
	}

	@ApiOperation(value = "删除")
	@DeleteMapping("/delete")
	//@PreAuthorize("@ss.hasPermi('sysFiles:delete")
	public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids) {
		sysFilesService.removeByIdsAndFiles(ids);
		return DataResult.success();
	}

	@ApiOperation(value = "查询分页数据")
	@PostMapping("/listByPage")
	//@PreAuthorize("@ss.hasPermi('sysFiles:list")
	public DataResult findListByPage(@RequestBody SysFilesV2Entity sysFiles) {
		Page page = new Page(sysFiles.getPage(), sysFiles.getLimit());
		IPage<SysFilesV2Entity> iPage = sysFilesService.page(page,
				Wrappers.<SysFilesV2Entity>lambdaQuery().orderByDesc(SysFilesV2Entity::getCreateDate));
		return DataResult.success(iPage);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "查询分页数据")
	@PostMapping("/listByPageUser")
	//@PreAuthorize("@ss.hasPermi('sysFiles:list")
	public DataResult listByPageUser(@RequestBody SysFilesV2Entity sysFiles) {
		Page page = new Page(sysFiles.getPage(), sysFiles.getLimit());
		IPage<SysFilesV2Entity> iPage = sysFilesService.page(page,
				Wrappers.<SysFilesV2Entity>lambdaQuery()
				.eq(SysFilesV2Entity::getRefBizid, sysFiles.getRefBizid())
				.eq(SysFilesV2Entity::getDictBiztype, sysFiles.getDictBiztype())
				.orderByDesc(SysFilesV2Entity::getCreateDate));
		return DataResult.success(iPage);
	}

}
