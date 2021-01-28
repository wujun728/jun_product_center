package sy.util.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import sy.action.UdpGetClientMacAddr;
import sy.action.base.SysDefAction;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * IP工具类
 * 
 */
public class IpUtil {

	private static final Logger logger = Logger.getLogger(IpUtil.class);

	/**
	 * 获得mac地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpMacAddr(HttpServletRequest request) {
		String sip = getIpAddr(request);
		 if (isLocal(sip)) {
			 return sip;
		 }

		UdpGetClientMacAddr umac;

		try {
//			umac = new UdpGetClientMacAddr(sip);
//			String mac = umac.GetRemoteMacAddr();
//			logger.debug("获得的物理地址:" + mac);
//			return mac;
			 return sip;
		} catch (Exception e) {
			e.printStackTrace();
			String mac = getMAC(sip);
			return mac;
		}

//		return "";
	}
	
	// 运行速度【慢】
	public static String getMAC(String ip) {
		String str = null;
		String macAddress = null;
		try {
			Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);
			InputStreamReader ir = new InputStreamReader(p.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			for (; true;) {
				str = input.readLine();
				if (str != null) {
					if (str.indexOf("MAC Address") > 1) {
						macAddress = str
								.substring(str.indexOf("MAC Address") + 14);
						break;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace(System.out);
			return null;
		}
		return macAddress;
	}

	/**
	 * 本地地址， 返回 true
	 * 
	 * @param ipstr
	 */
	public static boolean isLocal(String ipstr) {
		if (ipstr != null
				&& (ipstr.equalsIgnoreCase("localhost")
						|| ipstr.equalsIgnoreCase("本地") ||
						ipstr
							.equalsIgnoreCase("127.0.0.1") ||
							ipstr.equalsIgnoreCase("0:0:0:0:0:0:0:1"))) {
			return true;
		}

		return false;
	}

	/**
	 * 获取登录用户的IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
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
		String host = request.getRemoteHost();
		if (ip.equals("0:0:0:0:0:0:0:1")) {
			//ip = "本地";
		}
		if (ip.split(",").length > 1) {
			ip = ip.split(",")[0];
		}
		return ip;
	}

	/**
	 * 通过IP获取地址(需要联网，调用淘宝的IP库)
	 * 
	 * @param ip
	 * @return
	 */
	public static String getIpInfo(String ip) {
		if (ip.equals("本地")) {
			ip = "127.0.0.1";
		}
		String info = "";
		try {
			URL url = new URL("http://ip.taobao.com/service/getIpInfo.php?ip="
					+ ip);
			HttpURLConnection htpcon = (HttpURLConnection) url.openConnection();
			htpcon.setRequestMethod("GET");
			htpcon.setDoOutput(true);
			htpcon.setDoInput(true);
			htpcon.setUseCaches(false);

			InputStream in = htpcon.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(in));
			StringBuffer temp = new StringBuffer();
			String line = bufferedReader.readLine();
			while (line != null) {
				temp.append(line).append("\r\n");
				line = bufferedReader.readLine();
			}
			bufferedReader.close();
			JSONObject obj = (JSONObject) JSON.parse(temp.toString());
			if (obj.getIntValue("code") == 0) {
				JSONObject data = obj.getJSONObject("data");
				info += data.getString("country") + " ";
				info += data.getString("region") + " ";
				info += data.getString("city") + " ";
				info += data.getString("isp");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return info;
	}

}
