package sy.action;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import sy.util.ImageUtil;

import com.opensymphony.xwork2.ActionContext;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

@Namespace("/")
@Action
public class ImageAction extends BaseAction {

	private InputStream imageStream;
	
	private String time;
	
	public InputStream getImageStream() {
		return imageStream;
	}

	public void setImageStream(InputStream imageStream) {
		this.imageStream = imageStream;
	}

	public void doNotNeedSessionAndSecurity_execute() {
		try {
			Map<String, BufferedImage> map = ImageUtil.getImage();
			// 获取验证图片上的字符，保存到session
			String key = map.keySet().iterator().next();
			Map<String, Object> session = ActionContext.getContext()
					.getSession();
			session.put("code", key);
			// 获取验证图片，以stream方式响应
			BufferedImage image = map.get(key);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			JPEGImageEncoder jpeg = JPEGCodec.createJPEGEncoder(bos);
			
			try {
				jpeg.encode(image);
				byte[] bts = bos.toByteArray();
				imageStream = new ByteArrayInputStream(bts);
				// return "success";
			} catch (IOException e) {
				e.printStackTrace();
				// return "error";
			}
			OutputStream output = getResponse().getOutputStream();
			byte[] buf = new byte[204800];
			int bufsize = 0;	
			while ((bufsize = imageStream.read(buf, 0, buf.length)) != -1) {
				// 返回流
				output.write(buf, 0, bufsize);
			}
			//output.write(buf, 0, bufsize);
			output.flush();
			output.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}
}
