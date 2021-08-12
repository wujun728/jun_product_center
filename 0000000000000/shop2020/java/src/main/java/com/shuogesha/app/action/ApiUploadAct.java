package com.shuogesha.app.action;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.shuogesha.common.file.AliOSSUpload;
import com.shuogesha.common.image.ImageUtils;
import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;
import com.shuogesha.platform.entity.Site;
import com.shuogesha.platform.service.FileService;
import com.shuogesha.platform.web.FrontUtils;

@Controller
@RequestMapping("/app/")
public class ApiUploadAct {

	@Value("${file.uploadFolder}")
	private String filePath;

	static long start = 1024 * 200;
	static long end = 1024 * 300;

	@RequestMapping(value = "image_Up", method = RequestMethod.POST)
 	public @ResponseBody Object image_Up(MultipartFile file, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws IOException {
		String filename = file.getOriginalFilename();
		String ext = FilenameUtils.getExtension(filename).toLowerCase(Locale.ENGLISH);

		if (!ImageUtils.isValidImageExt(ext)) {
			// TODO 异常处理（图片格式不正确）
		}
		String origName = generateFilename("", ext);
		String fileUrl = null;
		try {
			Site site = FrontUtils.getSite(request);
			if (site != null && ImageUtils.ALIOSS.equals(site.getOss())) {// 阿里云存储
				fileUrl = AliOSSUpload.upload(origName, file);// 测试阿里云的上传，后期系统配置是否开启云存储 TODO
			} else {
				InputStream in = file.getInputStream();
				File f = new File(filePath);
				// 如果文件夹不存在则创建
				if (!f.exists() && !f.isDirectory()) {
					f.mkdir();
				}
				File outFile = new File(filePath + "/" + origName); // 输出文件
				OutputStream os = new FileOutputStream(outFile);
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = in.read(buffer)) != -1) {
					os.write(buffer, 0, len);
				}
				in.close();
				os.close();
				// 加上部署路径
				fileUrl = "/upload/" + origName;
			}
			// 资源文件保存记录
			fileService.init(fileUrl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject re = new JSONObject();
		re.put("fileName", origName);
		re.put("fileUrl", fileUrl);
		return new JsonResult(ResultCode.SUCCESS, re);
 	}

	private BufferedImage getBufferedImage(MultipartFile file) throws Exception {
		BufferedImage sourceImg = ImageIO.read(file.getInputStream());
		return sourceImg;
	}

	/**
	 * 把上传图片压缩
	 * 
	 * @param file
	 * @param boxWidth
	 * @param boxHeight
	 * @return
	 * @throws Exception
	 */
	private File zipImageFile(MultipartFile file, double comBase, double scale) throws Exception {
		String path = System.getProperty("java.io.tmpdir");
		File tempFile = new File(path, String.valueOf(System.currentTimeMillis()));
		file.transferTo(tempFile);
		try {
			/** 对服务器上的临时文件进行处理 */
			Image src = ImageIO.read(tempFile);
			int srcHeight = src.getHeight(null);
			int srcWidth = src.getWidth(null);
			int deskHeight = 0;// 缩略图高
			int deskWidth = 0;// 缩略图宽
			double srcScale = (double) srcHeight / srcWidth;
			/** 缩略图宽高算法 */
			if ((double) srcHeight > comBase || (double) srcWidth > comBase) {
				if (srcScale >= scale || 1 / srcScale > scale) {
					if (srcScale >= scale) {
						deskHeight = (int) comBase;
						deskWidth = srcWidth * deskHeight / srcHeight;
					} else {
						deskWidth = (int) comBase;
						deskHeight = srcHeight * deskWidth / srcWidth;
					}
				} else {
					if ((double) srcHeight > comBase) {
						deskHeight = (int) comBase;
						deskWidth = srcWidth * deskHeight / srcHeight;
					} else {
						deskWidth = (int) comBase;
						deskHeight = srcHeight * deskWidth / srcWidth;
					}
				}
			} else {
				deskHeight = srcHeight;
				deskWidth = srcWidth;
			}
			BufferedImage tag = new BufferedImage(deskWidth, deskHeight, BufferedImage.TYPE_3BYTE_BGR);
			tag.getGraphics().drawImage(src, 0, 0, deskWidth, deskHeight, null); // 绘制缩小后的图
			FileOutputStream deskImage = new FileOutputStream(tempFile); // 输出到文件流
//    	        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(deskImage); 
//    	        encoder.encode(tag); //近JPEG编码
			ImageIO.write(tag, "jpeg", deskImage);
			deskImage.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tempFile;
	}

	public static String generateFilename(String path, String ext) {
		return path + MONTH_FORMAT.format(new Date()) + RandomStringUtils.random(4, N36_CHARS) + "." + ext;
	}

	/**
	 * 日期格式化对象
	 */
	public static final DateFormat MONTH_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");

	/**
	 * 36个小写字母和数字
	 */
	public static final char[] N36_CHARS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

	@Autowired
	private FileService fileService;
}
