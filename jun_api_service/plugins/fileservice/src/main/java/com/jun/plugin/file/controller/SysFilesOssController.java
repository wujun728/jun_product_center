package com.jun.plugin.file.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jun.plugin.common.Result;
import com.jun.plugin.file.common.comfig.FileUploadProperties;
import com.jun.plugin.file.common.utils.BizUtils;
import com.jun.plugin.file.entity.SysFilesOssEntity;
import com.jun.plugin.file.service.SysFilesOssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.PictureRenderData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

/**
 * 文件上传
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
@RestController
@RequestMapping("/sysFilesOss")
@Api(tags = "文件管理")
@Slf4j
public class SysFilesOssController {
	@Autowired
	private SysFilesOssService service;
	
//	@Autowired
//    HttpSessionService sessionService;
    
	@Autowired
    FileUploadProperties fileUploadProperties;
	
//	@Autowired
//    private SysUserMapper userMapper;

	@ApiOperation(value = "新增")
	@PostMapping("/upload")
//	@RequiresPermissions(value = { "sysFiles:add", "sysContent:update", "sysContent:add" }, logical = Logical.OR)
	public Result add(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "bizid",required = false) String bizid,
					  @RequestParam(value = "biztype",required = false) String biztype) {
		log.info(biztype);
		log.info(bizid);
		// 判断文件是否空
		if (file == null || file.getOriginalFilename() == null
				|| "".equalsIgnoreCase(file.getOriginalFilename().trim())) {
			return Result.fail("文件为空");
		}
		return service.saveFile(file, biztype, bizid);
	}
	
	@ApiOperation(value = "生成简历")
	@PostMapping("/genResume")
//	@RequiresPermissions(value = { "sysFiles:add", "sysContent:update", "sysContent:add" }, logical = Logical.OR)
	public Result genResume(@RequestParam(value = "bizid",required = false) String bizid,
								@RequestParam(value = "username",required = false) String username,
								@RequestParam(value = "realName",required = false) String realName,
								@RequestParam(value = "deptName",required = false) String deptName,
								@RequestParam(value = "biztype",required = false) String biztype) throws IOException {
		Map<String, Object> resume = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource1 = resourceLoader.getResource("classpath:files/"+"logo_resume.jpg");
        data.put("pictures", new PictureRenderData(100, 120, resource1.getFile().getAbsolutePath()));
        //SysUser user = userMapper.getUserByName(username);
        Map m = null;//JSON.parseObject(JSON.toJSONString(user), Map.class);
        data.putAll(m);
        List<Map<String,Object>> list11=new ArrayList<>();
        System.err.println(JSON.toJSON(data));
        list11.add(data);
        resume.put("resume",list11);
        Resource resource = resourceLoader.getResource("classpath:files/"+"简历模板2.docx");
        InputStream inputStream =resource.getInputStream() ;
        XWPFTemplate template = XWPFTemplate.compile(inputStream).render( resume);
        String newFile = fileUploadProperties.getPath()+realName+"简历"+username+".docx";
        File file = new File(newFile);
        BizUtils.createFile(file);
        template.writeAndClose(new FileOutputStream(newFile));
		log.info(bizid);
		// 判断文件是否空
		if (file == null) {
			return Result.fail("文件为空");
		}
		return service.saveFile(file, biztype, bizid);
	}
	 

	
	@ApiOperation(value = "删除")
	@DeleteMapping("/delete")
//	@RequiresPermissions("sysFiles:delete")
	public Result delete(@RequestBody @ApiParam(value = "id集合") List<String> ids) {
		service.removeByIdsAndFiles(ids);
		return Result.success();
	}
	
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "查询分页数据")
	@PostMapping("/listByPage")
//	@RequiresPermissions("sysFiles:list")
	public Result findListByPage(@RequestBody SysFilesOssEntity sysFiles) {
		Page page = new Page(sysFiles.getPage(), sysFiles.getLimit());
		IPage<SysFilesOssEntity> iPage = service.page(page,
				Wrappers.<SysFilesOssEntity>lambdaQuery().orderByDesc(SysFilesOssEntity::getCreateDate));
		List<SysFilesOssEntity> records = iPage.getRecords();
        String userid = "sessionService.getCurrentUsername()";
		records.forEach(item -> {
			if(userid.equalsIgnoreCase(item.getCreator())) {
				item.setIsOwner(1);
			}
		});
		return Result.success(iPage);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "查询分页数据")
	@PostMapping("/listByPageUser")
//	@RequiresPermissions("sysFiles:list")
	public Result listByPageUser(@RequestBody SysFilesOssEntity sysFiles) {
		Page page = new Page(sysFiles.getPage(), sysFiles.getLimit());
		IPage<SysFilesOssEntity> iPage = service.page(page,
				Wrappers.<SysFilesOssEntity>lambdaQuery()
				.eq(SysFilesOssEntity::getRefBizid, sysFiles.getRefBizid())
//				.eq(SysFilesOssEntity::getDictBiztype, sysFiles.getDictBiztype())
				.orderByDesc(SysFilesOssEntity::getCreateDate));
		List<SysFilesOssEntity> records = iPage.getRecords();
        String userid = "sessionService.getCurrentUsername()";
		records.forEach(item -> {
			if(userid.equalsIgnoreCase(item.getCreator())) {
				item.setIsOwner(1);
			}
		});
		return Result.success(iPage);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "查询分页数据")
	@PostMapping("/listByPageUserProjectID")
	public Result listByPageUserProjectID(@RequestBody SysFilesOssEntity sysFiles) {
		String pid = sysFiles.getRefBizid();
		Page page = new Page(sysFiles.getPage(), sysFiles.getLimit());
		String tmpSQL = getProjectFilesRefIDS(pid);
		IPage<SysFilesOssEntity> iPage = service.page(page,
				Wrappers.<SysFilesOssEntity>lambdaQuery()
//				.eq(SysFilesOssEntity::getRefBizid, sysFiles.getRefBizid())
				.inSql(true, SysFilesOssEntity::getRefBizid, tmpSQL )
//				.eq(SysFilesOssEntity::getRefBizid, sysFiles.getRefBizid())
				.orderByDesc(SysFilesOssEntity::getCreateDate));
		List<SysFilesOssEntity> records = iPage.getRecords();
		String userid = "sessionService.getCurrentUsername()";
		records.forEach(item -> {
			if(userid.equalsIgnoreCase(item.getCreator())) {
				item.setIsOwner(1);
			}
		});
		return Result.success(iPage);
	}

	@ApiOperation(value = "查询userLogo")
	@PostMapping("/getImgByBizid")
	public Result getImgByBizid(@RequestBody SysFilesOssEntity sysFiles) {
		LambdaQueryWrapper<SysFilesOssEntity> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(SysFilesOssEntity::getDictBiztype,sysFiles.getDictBiztype());
		queryWrapper.eq(SysFilesOssEntity::getRefBizid,sysFiles.getRefBizid());
		queryWrapper.orderByDesc(SysFilesOssEntity::getCreateDate);
		queryWrapper.last(" limit 1 ");
		List<SysFilesOssEntity> list = service.list(queryWrapper);
		String userid = "sessionService.getCurrentUsername()";
		if(list.size()>0){
			return Result.success(list.get(0));
		}else{
			return Result.success();
		}
	}


	private String getProjectFilesRefIDS(String pid) {
		String tmpSQL = " select  pjc.id -- ,pjc.*\r\n"
				+ "				from pj_project t\r\n"
				+ "				left join pj_contract pjc on t.project_code=pjc.refid_project_code_hide\r\n"
				+ "		where t.id='"+pid+"'  \r\n"
				+ "\r\n  union  SELECT '"+pid+"'  from pj_project"
				+ "		union \r\n"
				+ "		select  pjp.id -- ,pjp.*\r\n"
				+ "				from pj_project t\r\n"
				+ "				left join pj_project_plan pjp on t.project_code=pjp.ref_project_code\r\n"
				+ "		where t.id='"+pid+"' \r\n"
				+ "\r\n"
				+ "		union  \r\n"
				+ "		select  pjd.id -- ,pjd.*\r\n"
				+ "				from pj_project t\r\n"
				+ "				left join pj_project_draft pjd on t.project_code=pjd.ref_project_code\r\n"
				+ "		where t.id='"+pid+"' \r\n"
				+ "\r\n"
				+ "		union  \r\n"
				+ "		select  pjr.id -- ,pjr.*\r\n"
				+ "				from pj_project t\r\n"
				+ "				left join pj_project_recheck pjr on t.project_code=pjr.ref_pcode\r\n"
				+ "		where t.id='"+pid+"' \r\n"
				+ "		union  \r\n"
				+ "		select  pja.id -- ,pja.*\r\n"
				+ "				from pj_project t\r\n"
				+ "				left join pj_project_appraise pja on t.project_code=pja.ref_project_code\r\n"
				+ "		where t.id='"+pid+"' \r\n"
				+ "		union  \r\n"
				+ "		select  pji.id -- ,pji.*\r\n"
				+ "				from pj_project t\r\n"
				+ "				left join pj_project_invoice pji on t.project_code=pji.ref_project_code\r\n"
				+ "		where t.id='"+pid+"' \r\n"
				+ "		union  \r\n"
				+ "		select  pjm.id -- ,pjm.*\r\n"
				+ "				from pj_project t\r\n"
				+ "				left join pj_project_member pjm on t.project_code=pjm.ref_project_code\r\n"
				+ "		where t.id='"+pid+"' \r\n"
				+ "		union  \r\n"
				+ "		select  pjt.id -- ,pjt.*\r\n"
				+ "				from pj_project t\r\n"
				+ "				left join pj_project_prodess_task pjt on t.project_code=pjt.ref_project_code\r\n"
				+ "		where t.id='"+pid+"' \r\n"
				+ "		union \r\n"
				+ "		select  pjr1.id -- ,pjr1.*\r\n"
				+ "				from pj_project t\r\n"
				+ "				left join pj_project_report pjr1 on t.project_code=pjr1.ref_project_code\r\n"
				+ "		where t.id='"+pid+"' \r\n"
				+ "		union \r\n"
				+ "		select  pjrn.id -- ,pjrn.*\r\n"
				+ "				from pj_project t\r\n"
				+ "				left join pj_project_reportnumber pjrn on t.project_code=pjrn.ref_reportnumber_code\r\n"
				+ "		where t.id='"+pid+"'  ";
		return tmpSQL;
	}

}
