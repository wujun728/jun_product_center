//package com.jun.plugin.service;
//
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.Writer;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.alibaba.fastjson.JSONArray;
//import com.beust.jcommander.internal.Maps;
//import com.jun.plugin.module.oss.cloud.OSSFactory;
//import com.jun.plugin.module.oss.common.PageUtils;
//import com.jun.plugin.module.oss.common.Query;
//import com.jun.plugin.module.oss.common.RRException;
//import com.jun.plugin.module.oss.common.Result;
//import com.jun.plugin.module.oss.entity.SysConfigEntity;
//import com.jun.plugin.module.oss.entity.SysOssEntity;
//import com.jun.plugin.module.oss.service.SysConfigService;
//import com.jun.plugin.module.oss.service.SysOssService;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class Test2SysConfigService {
//
//	@Autowired
//	private SysConfigService sysConfigService;
//	
//	@Autowired
//	private SysOssService sysOssService;
//
//	@Test
//	public void upload(/* @RequestParam("file") MultipartFile file */) throws Exception {
//		String str="‪D:\\\\quickstart-1111111.tar.zip";
//		String location=str.replace("\\\\", "/");
//		File file = new File(location);
////		File file = new File("‪‪d:\\quickstart-1111111.tar.zip");
////		File file = new File("d:\\Documents\\Desktop\\微信截图_20211112093537.png");
////		if (!file.exists()) {
////			throw new RRException("上传文件不能为空");
////		}
//		
//		//上传文件
//		String url = OSSFactory.build().upload(Test2SysConfigService.File2byte(file));
//		//保存文件信息
//		SysOssEntity ossEntity = new SysOssEntity();
//		ossEntity.setUrl(url);
//		ossEntity.setCreateDate(new Date());
//		sysOssService.save(ossEntity);
//		JSONArray.toJSON(Result.ok().put("url", url));
//	}
//	
//	public static byte[] File2byte(File tradeFile){
//        byte[] buffer = null;
//        try
//        {
//            FileInputStream fis = new FileInputStream(tradeFile);
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            byte[] b = new byte[1024];
//            int n;
//            while ((n = fis.read(b)) != -1)
//            {
//                bos.write(b, 0, n);
//            }
//            fis.close();
//            bos.close();
//            buffer = bos.toByteArray();
//        }catch (FileNotFoundException e){
//            e.printStackTrace();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        return buffer;
//    }
//	
////	@Test
//	public void testSelect() {
//		Map<String, Object> params = Maps.newHashMap();
//		params.put("page", 1);
//		params.put("limit", 100);
//		params.put("sidx", "");
//		params.put("order", "");
//		// 查询列表数据
//		Query query = new Query(params);
//		List<SysConfigEntity> configList = sysConfigService.queryList(query);
//		int total = sysConfigService.queryTotal(query);
//		PageUtils pageUtil = new PageUtils(configList, total, query.getLimit(), query.getPage());
//		JSONArray.toJSON(Result.ok().put("page", pageUtil));
//	}
//}