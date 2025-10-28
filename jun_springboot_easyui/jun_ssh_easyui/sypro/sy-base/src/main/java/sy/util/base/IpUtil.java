package sy.util.base;

import javax.servlet.http.HttpServletRequest;

import org.nutz.http.Http;
import org.nutz.http.Response;
import org.nutz.json.Json;
import org.nutz.lang.hardware.Networks;
import org.nutz.mapl.Mapl;

/**
 * IP工具类
 * 
 * http://git.oschina.net/sphsyv/sypro
 * 
 * @author 孙宇
 *
 */
public class IpUtil {

	/**
	 * 获得IP地址
	 * 
	 * @return
	 */
	public static String getIp() {
		return Networks.ipv4();
	}

	/**
	 * 获得IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip.equals("0:0:0:0:0:0:0:1")) {
			ip = "本地";
		}
		if (ip.split(",").length > 1) {
			ip = ip.split(",")[0];
		}
		return ip;
	}

	/**
	 * 通过IP获得地址信息，调用的是淘宝的IP接口
	 * 
	 * @param ip
	 * @return
	 */
	public static String getAddr(String ip) {
		String info = "";
		Response resp = Http.get("http://ip.taobao.com/service/getIpInfo.php?ip=" + ip);
		if (resp.isOK()) {
			String result = resp.getContent();
			Object obj = Json.fromJson(result);
			if (Mapl.cell(obj, "code").toString().equals("0")) {
				info += Mapl.cell(obj, "data.country") + " ";
				info += Mapl.cell(obj, "data.region") + " ";
				info += Mapl.cell(obj, "data.city") + " ";
				info += Mapl.cell(obj, "data.isp") + " ";
			}
		}
		return info;
	}

}
