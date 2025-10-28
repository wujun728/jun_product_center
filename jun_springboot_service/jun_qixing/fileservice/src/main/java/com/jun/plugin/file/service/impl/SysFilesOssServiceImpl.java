package com.jun.plugin.file.service.impl;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import com.jun.plugin.common.exception.BusinessException;
import com.jun.plugin.common.utils.DataResult;
import com.jun.plugin.common.utils.DateUtils;
import com.jun.plugin.file.entity.SysFilesOssV2Entity;
import com.jun.plugin.file.mapper.SysFilesOssMapper;
import com.jun.plugin.file.qiniu.QiniuUtils;
import com.jun.plugin.file.service.SysFilesOssService;
import com.ruoyi.common.utils.SecurityUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.ImmutableMap;
import com.jun.plugin.file.common.comfig.FileUploadProperties;
//

/**
 * 文件上传 服务类
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
@EnableConfigurationProperties(FileUploadProperties.class)
@Service("sysFilesOssService")
public class SysFilesOssServiceImpl extends ServiceImpl<SysFilesOssMapper, SysFilesOssV2Entity> implements SysFilesOssService {
    @Resource
    private FileUploadProperties fileUploadProperties;

    @Resource
    private SysFilesOssMapper sysFilesOssMapper;

//    @Resource
//    private HttpSessionService httpSessionService;

    private static final ThreadLocal<Map<Object, Object>> mapTmp = new ThreadLocal();

    @Override
    public DataResult saveFile(MultipartFile file) {
        //存储文件夹
        String createTime = DateUtils.format(new Date(), DateUtils.DATEPATTERN);
        String newPath = fileUploadProperties.getPath() + createTime + File.separator;
        File uploadDirectory = new File(newPath);
        if (uploadDirectory.exists()) {
            if (!uploadDirectory.isDirectory()) {
                uploadDirectory.delete();
            }
        } else {
            uploadDirectory.mkdir();
        }
        try {
            String fileName = file.getOriginalFilename();
            //id与filename保持一直，删除文件
            String fileNameNew = UUID.randomUUID().toString().replace("-", "") + getFileType(fileName);
            String newFilePathName = newPath + fileNameNew;
            String url = fileUploadProperties.getUrl() + "/" + createTime + "/" + fileNameNew;
            //创建输出文件对象
            File outFile = new File(newFilePathName);
            //拷贝文件到输出文件对象
            FileUtils.copyInputStreamToFile(file.getInputStream(), outFile);
            //保存文件记录
            saveFilesEntity(fileName, newFilePathName, url, "filemanager", "");
            Map<String, String> resultMap = new HashMap<>();
            resultMap.put("src", url);
            return DataResult.success(resultMap);
        } catch (Exception e) {
            throw new BusinessException("上传文件失败");
        }
    }

    @Override
    public DataResult saveOssFile(MultipartFile file) {
    	try {
    		String username = SecurityUtils.getUsername();

    		String fileName = file.getOriginalFilename();
    		String fileNameNew = QiniuUtils.getFileNameByDate(username,fileName);
//    		QiniuUtils.uploadFile(file, fileNameNew);
    		String downUrl = QiniuUtils.uploadFileV2(file, fileNameNew);
    		String URL = QiniuUtils.domain + "/" + fileName;
//    		String downUrl = QiniuUtils.download(fileNameNew);;
    		//保存文件记录
    		saveFilesOssEntity(fileNameNew, URL, downUrl, "filemanager", QiniuUtils.FormetFileSize(file.getSize()));
    		Map<String, String> resultMap = new HashMap<>();
    		resultMap.put("src", downUrl);
    		return DataResult.success(resultMap);
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw new BusinessException("上传文件失败");
    	}
    }
    @Override
    public DataResult saveOssFile(File file) {
    	try {
    		String username = SecurityUtils.getUsername();
    		String fileName =  file.getName();
    		String fileNameNew = QiniuUtils.getFileNameByDate(username,fileName);
//    		QiniuUtils.uploadFile(file, fileNameNew);
    		String downUrl = QiniuUtils.uploadFileV2(file, fileNameNew);
    		String URL = QiniuUtils.domain + "/" + fileName;
//    		String downUrl = QiniuUtils.download(fileNameNew);;
    		//保存文件记录
    		saveFilesOssEntity(fileNameNew, URL, downUrl, "filemanager", QiniuUtils.FormetFileSize(FileUtils.sizeOfAsBigInteger(file).longValue()));
    		Map<String, String> resultMap = new HashMap<>();
    		resultMap.put("src", downUrl);
    		return DataResult.success(resultMap);
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw new BusinessException("上传文件失败");
    	}
    }

	private void saveFilesEntity(String fileName, String newFilePathName, String url, String biztype, String bizid) {
		SysFilesOssV2Entity entity = new SysFilesOssV2Entity();
		entity.setFileName(fileName);
		entity.setFilePath(newFilePathName);
		entity.setUrl(url);
		entity.setDictBiztype(String.valueOf(mapTmp.get().get("biztype")));
		entity.setRefBizid(String.valueOf(mapTmp.get().get("bizid")));
		this.save(entity);
	}

	private void saveFilesOssEntity(String fileName, String newFilePathName, String url, String biztype, String fileSize) {
		SysFilesOssV2Entity entity = new SysFilesOssV2Entity();
		entity.setFileName(fileName);
		entity.setFilePath(newFilePathName);
		entity.setUrl(url);
		entity.setFileSize(fileSize);
		entity.setDictBiztype(String.valueOf(mapTmp.get().get("biztype")));
		entity.setRefBizid(String.valueOf(mapTmp.get().get("bizid")));
		this.save(entity);
//		sysFilesOssMapper.insert(entity);
	}

    @Override
    public void removeByIdsAndFiles(List<String> ids) {
        List<SysFilesOssV2Entity> list = this.listByIds(ids);
        list.forEach(entity -> {
        	QiniuUtils.deleteFileFromQiniu(entity.getFileName());
            //如果之前的文件存在，删除
            File file = new File(entity.getFilePath());
            if (file.exists()) {
                file.delete();
            }
        });
        this.removeByIds(ids);

    }

    /**
     * 获取文件后缀名
     *
     * @param fileName 文件名
     * @return 后缀名
     */
    private String getFileType(String fileName) {
        if (fileName != null && fileName.contains(".")) {
            return fileName.substring(fileName.lastIndexOf("."));
        }
        return "";
    }

	@Override
	public DataResult saveFile(MultipartFile file, String biztype, String bizid) {
		mapTmp.set(ImmutableMap.builder().put("biztype",biztype).put("bizid", bizid).build());
//		this.saveOssFile(file);
		return this.saveOssFile(file);
	}

	@Override
	public DataResult saveFile(File file, String biztype, String bizid) {
		mapTmp.set(ImmutableMap.builder().put("biztype",biztype).put("bizid", bizid).build());
//		this.saveOssFile(file);
		return this.saveOssFile(file);
	}

}
