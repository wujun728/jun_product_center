package org.myframework.support.file.rest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.myframework.commons.file.FileSystem;
import org.myframework.commons.file.VfsFileSystem;
import org.myframework.commons.util.StringUtils;
import org.myframework.commons.util.io.FileUtils;
import org.myframework.commons.util.io.VfsUtils;
import org.myframework.support.base.ActionLog;
import org.myframework.support.base.BaseRest;
import org.myframework.support.file.config.VfsConfig;
import org.myframework.support.file.config.VfsConfig.VfsPath;
import org.myframework.support.file.config.VfsXmlCofig;
import org.myframework.support.file.entities.SysMediaFile;
import org.myframework.support.file.service.MediaFileServiceImpl;
import org.myframework.web.bind.ServiceResult;
import org.myframework.web.commons.util.WebUtils;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

@RestController
@RequestMapping("/mediafile")
public class MediaFileRest extends BaseRest  {

	private static final Log  logger = LogFactory.getLog(MediaFileRest.class);

	@Resource(name = "mediaFileService")
	MediaFileServiceImpl fileService;

	FileSystem vfs = new VfsFileSystem();

	/**
	 * 上传文件
	 * @param file
	 * @param request
	 * @return
	 */
	@ActionLog(type = "", content = "文件上传", description = "")
	@RequestMapping(value = "/upload" , method =RequestMethod.POST , produces="text/html; charset=UTF-8" )
	public String upload(@RequestParam MultipartFile file,
			@RequestParam Map<String, String> map) throws Exception{
		Assert.isTrue(!file.isEmpty(), "没有上传文件");
		logger.debug("开始上传文件 " + file.getOriginalFilename() );
		SysMediaFile mediaFile = saveMediaFile(file, map);
		mediaFile.setFileUri(null);
		mediaFile.setState(null);
		logger.debug("上传完毕！ "   );
		return  createSuccessResultJson(mediaFile) ;
	}
	
	String createSuccessResultJson(Object content){
		return JSON.toJSONString(createSuccessResult(content));
	}
	
	 

	/**
	 * 文件下载
	 * @param response
	 * @param mediaId
	 * @param map
	 * @throws IOException
	 */
	@ActionLog(type = "", content = "文件下载", description = "")
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void download(HttpServletResponse response,
			@RequestParam  String mediaId, @RequestParam Map<String, Object> map)
			throws IOException {
		SysMediaFile mediaFile = fileService.findOne(mediaId);
		if (mediaFile==null ||"0".equals(mediaFile.getState())) {
			throw new IllegalArgumentException("文件记录不存在!");
		}
		String fileUri = mediaFile.getFileUri();
		String oldName = mediaFile.getOldFileName();
		InputStream in = vfs.download(fileUri);
		WebUtils.download(response,in,oldName);
	}

	@ActionLog(type = "文件信息获取", content = "文件信息获取", description = "文件信息获取")
	@RequestMapping(value = "/get")
	public String getMediaInfo( @RequestParam String mediaId) {
		SysMediaFile mediaFile = fileService.findOne(mediaId);
		if (mediaFile==null ||"0".equals(mediaFile.getState())) {
			throw new IllegalArgumentException("文件记录不存在!");
		}
		mediaFile.setFileUri(null);
		mediaFile.setState(null);
		return createSuccessResultJson(mediaFile);
	}
	/**
	 * 文件浏览，图片等可直接通过浏览器查看
	 * @param response
	 * @param mediaId
	 * @param map
	 * @throws IOException
	 */
	@ActionLog(type = "", content = "文件浏览", description = "")
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public void view(HttpServletResponse response,
			@RequestParam String mediaId, @RequestParam Map<String, Object> map)
			throws IOException {
		SysMediaFile mediaFile = fileService.findOne(mediaId);
		if (mediaFile==null ||"0".equals(mediaFile.getState())) {
			throw new IllegalArgumentException("文件记录不存在!");
		}
		String fileUri = mediaFile.getFileUri();
		String contentType = mediaFile.getContentType();
		InputStream in = vfs.download(fileUri);
		// 1.设置文件ContentType类型
		response.setContentType(contentType);
		ServletOutputStream out = response.getOutputStream();
		BufferedInputStream bis = new BufferedInputStream(in);
		BufferedOutputStream bos = new BufferedOutputStream(out);
		byte[] buff = new byte[2048];
		int bytesRead;
		while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
			bos.write(buff, 0, bytesRead);
		}
		bis.close();
		bos.close();
	}


	/**
	 * 文件逻辑删除
	 * @param mediaId
	 * @return
	 */
	@ActionLog(type = "", content = "文件逻辑删除", description = "")
	@RequestMapping(value = "/del", method = RequestMethod.GET)
	public ServiceResult remove( @RequestParam String mediaId ) {
		SysMediaFile mediaFile =fileService.findOne(mediaId);
		if (mediaFile==null ||"0".equals(mediaFile.getState())) {
			throw new IllegalArgumentException("文件记录不存在!");
		}
		fileService.removeFile(mediaId);
		return createSuccessResult(null) ;
	}

	/**
	 * 文件物理删除
	 * @param mediaId
	 * @return
	 */
	@ActionLog(type = "", content = "文件物理删除", description = "")
	@RequestMapping(value = "/delfile", method = RequestMethod.GET)
	public ServiceResult delfile( @RequestParam String mediaId ) {
		SysMediaFile mediaFile =fileService.findOne(mediaId);
		if (mediaFile==null ||"0".equals(mediaFile.getState())) {
			throw new IllegalArgumentException("文件记录不存在!");
		}
		VfsUtils.delete(mediaFile.getFileUri());
		fileService.removeFile(mediaId);
		return createSuccessResult(null) ;
	}

	/**
	 * 保存文件到存储目录，并进行数据库记录
	 *
	 * @param file
	 * @param request
	 * @return
	 * @throws Exception
	 */
	protected SysMediaFile saveMediaFile(MultipartFile file, Map<String, String> param)
			throws Exception {
		String oldFileName = file.getOriginalFilename();
		String contentType = file.getContentType();
		String newFileName = FileUtils.newFileName(oldFileName);
		String fileExt = FileUtils.getExtension(oldFileName, "");
		//URL参数限制的上传类型
		String accept = param.get("accept");
		long size = file.getSize();
		// 验证上传文件是否合法
		checkMedia(fileExt, size,  accept);
		// STEP 1 保存文件基础信息
		SysMediaFile mediaFile = new SysMediaFile();
		mediaFile.setOldFileName(oldFileName);
		mediaFile.setFileName(newFileName);
		mediaFile.setFileExt(fileExt);
		mediaFile.setContentType(contentType);
		mediaFile.setSize(size);
		mediaFile.setState("1");
		// STEP 2 上传文件 
		InputStream in = file.getInputStream();
		String toFile = VfsXmlCofig.getDynamicPath(getFileDiretory(fileExt),param) + "/" + newFileName;
		vfs.upload(in, toFile);
		// STEP 3上传成功后保存最终存储地址
		mediaFile.setFileUri(toFile);
		String baseUrl = getBaseUrl(fileExt);
		String httpUrl = StringUtils.isNullOrBlank(baseUrl) ? "" :  VfsXmlCofig.getDynamicPath(baseUrl,param)
				+ "/" + newFileName;
		mediaFile.setFileHttpUrl(httpUrl);
		logger.debug("保存文件"+oldFileName +"到" + toFile +",http访问方式：" + httpUrl);
		fileService.save(mediaFile);
		logger.debug("文件在数据库中的记录ID 为:"+mediaFile.getMediaId() );
		return mediaFile;
	}
	
	
	/**
	 * 验证上传文件是否合法，是否超大
	 *
	 * @param fileExt
	 * @param size
	 */
	protected void checkMedia(String fileExt, long size,String accept) {
		accept = StringUtils.isNullOrBlank(accept)?"*.*":accept;
		VfsConfig vfsconfig = VfsXmlCofig.getInstance().getVfsConfig();
		// 是否上传非法文件
		String illegalExt = vfsconfig.getIllegalExt();
		String allowExt = vfsconfig.getAllowExt();
		String[] illegalExts = illegalExt.split(";");
		String[] allowExts = allowExt.toLowerCase().split(";");
		String[] inputAcceptExts = accept.toLowerCase().split(",");
		//判断是否属于上传文件类型的范围(*.*表示所有文件属于可上传范围)
		if (!isAccept(fileExt,inputAcceptExts)) {
			throw new IllegalArgumentException("上传文件扩展名必须以下一种:"+accept);
		}

		//判断是否属于上传文件类型的范围(*.*表示所有文件属于可上传范围)
		if (!isAccept("*." + fileExt,allowExts)) {
			throw new IllegalArgumentException("上传文件扩展名必须以下一种:"+allowExt);
		}

		//判断是否非法上传文件类型
		for (String pattren : illegalExts) {
			if (("*." + fileExt).equalsIgnoreCase(pattren))
				throw new IllegalArgumentException("上传非法文件,以下扩展名为非法文件:"+illegalExt);
		}

		// 是否文件大小符合标准
		long maxsize = vfsconfig.getDefaultMaxSize();
		for (VfsPath path : vfsconfig.getPaths()) {
			String[] fileExts = path.getFileExt().split(";");
			if(isAccept("*." + fileExt,fileExts)) {
				maxsize = path.getMaxSize();
				break;
			}
		}

		if (size > maxsize)
			throw new IllegalArgumentException("上传文件大小超过"+ FileUtils.byteCountToDisplaySize(maxsize));

	}

	/**
	 * 判断文件是否在可上传文件扩展名列表
	 * @param fileExt
	 * @param inputAcceptExts
	 * @return
	 */
	boolean isAccept( String fileExt ,String[] inputAcceptExts){
		boolean isAccept = false ;
		for (String pattren : inputAcceptExts) {
			if ((pattren).equalsIgnoreCase(fileExt)|| ("*.*" ).equalsIgnoreCase(pattren)) {
				isAccept = true ;
				break;
			}
		}
		return isAccept;
	}

	/**
	 * 不同文件类型存放到不同的目录中
	 *
	 * @param fileExt
	 * @return
	 */
	protected String getFileDiretory(String fileExt) {
		VfsConfig vfsconfig = VfsXmlCofig.getInstance().getVfsConfig();
		String savePath = vfsconfig.getBaseStoreLocation() ;
		for (VfsPath path : vfsconfig.getPaths()) {
			String[] fileExts = path.getFileExt().split(";");
			for (String pattren : fileExts) {
				if (("*." + fileExt).equalsIgnoreCase(pattren))
					return path.getAbsolutePath();
			}
		}
		return savePath+ "/" + vfsconfig.getDefaultPath();
	}

	/**
	 * 不同文件类型存放到不同的URL目录中
	 *
	 * @param fileExt
	 * @return
	 */
	protected String getBaseUrl(String fileExt) {
		VfsConfig vfsconfig = VfsXmlCofig.getInstance().getVfsConfig();
		String urlPath = vfsconfig.getBaseUrl();
		if (StringUtils.isNullOrBlank(urlPath))
			return "";
		for (VfsPath path : vfsconfig.getPaths()) {
			String[] fileExts = path.getFileExt().split(";");
			for (String pattren : fileExts) {
				if (("*." + fileExt).equalsIgnoreCase(pattren))
					return urlPath + "/" + path.getRelativeDir();
			}
		}
		return urlPath+ "/"+ vfsconfig.getDefaultPath();
	}

}
