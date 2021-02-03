package sy.controller.demo;

import java.io.File;
import java.util.Iterator;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import sy.pageModel.base.JsonResult;

/**
 * 文件上传控制器演示
 * 
 * http://git.oschina.net/sphsyv/sypro
 * 
 * @author 孙宇
 *
 */
@Controller
@RequestMapping("/demoUploadController")
public class DemoUploadController {

	/**
	 * 上传文件演示，那个otherString可以替换成其他类、属性等等
	 * 
	 * @param multipartRequest
	 * @param session
	 * @param otherString
	 * @return
	 */
	@RequestMapping("/upload")
	@ResponseBody
	public JsonResult upload(DefaultMultipartHttpServletRequest multipartRequest, HttpSession session, String otherString) {
		JsonResult json = new JsonResult();
		json.setSuccess(true);
		if (multipartRequest != null) {
			Iterator<String> iterator = multipartRequest.getFileNames();
			while (iterator.hasNext()) {
				MultipartFile file = multipartRequest.getFile((String) iterator.next());
				if (!file.isEmpty()) {
					System.out.println(file.getContentType());// 获取文件MIME类型
					System.out.println(file.getName());// 获取表单中文件组件的名字
					System.out.println(file.getOriginalFilename());// 获取上传文件的原名
					System.out.println(file.getSize());// 获取文件的字节大小，单位byte
					try {
						// 文件保存路径
						String filePath = session.getServletContext().getRealPath("/") + File.separator + "attached" + File.separator;
						json.setMsg("上传成功，您的其他表单项信息：【" + otherString + "】，请查看【" + filePath + "】目录");
						File uploadFile = new File(filePath + file.getOriginalFilename());
						uploadFile.mkdirs();
						file.transferTo(uploadFile);// 保存到一个目标文件中。
					} catch (Exception e) {
						json.setSuccess(false);
						json.setMsg(e.getLocalizedMessage());
						e.printStackTrace();
					}
				}
			}
		}
		return json;
	}

}
