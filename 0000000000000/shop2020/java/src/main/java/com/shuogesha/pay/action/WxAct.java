package com.shuogesha.pay.action;

import java.io.BufferedReader;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shuogesha.platform.entity.Paylog;
import com.shuogesha.platform.service.PaybillService;
import com.shuogesha.platform.service.PaylogService;
import com.wechat.oauth.PaySign;
import com.wechat.util.XMLUtil;

@Controller
public class WxAct {

	@RequestMapping(value = "/pay/wx/notify_url")
	public @ResponseBody String notify_url(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		String FEATURE = null;
		try {
			// This is the PRIMARY defense. If DTDs (doctypes) are disallowed, almost all XML entity attacks are prevented
			// Xerces 2 only - http://xerces.apache.org/xerces2-j/features.html#disallow-doctype-decl
			FEATURE = "http://apache.org/xml/features/disallow-doctype-decl";
			dbf.setFeature(FEATURE, true);
			
			// If you can't completely disable DTDs, then at least do the following:
			// Xerces 1 - http://xerces.apache.org/xerces-j/features.html#external-general-entities
			// Xerces 2 - http://xerces.apache.org/xerces2-j/features.html#external-general-entities
			// JDK7+ - http://xml.org/sax/features/external-general-entities 
			FEATURE = "http://xml.org/sax/features/external-general-entities";
			dbf.setFeature(FEATURE, false);
			
			// Xerces 1 - http://xerces.apache.org/xerces-j/features.html#external-parameter-entities
			// Xerces 2 - http://xerces.apache.org/xerces2-j/features.html#external-parameter-entities
			// JDK7+ - http://xml.org/sax/features/external-parameter-entities 
			FEATURE = "http://xml.org/sax/features/external-parameter-entities";
			dbf.setFeature(FEATURE, false);
			
			// Disable external DTDs as well
			FEATURE = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
			dbf.setFeature(FEATURE, false);
			
			// and these as well, per Timothy Morgan's 2014 paper: "XML Schema, DTD, and Entity Attacks"
			dbf.setXIncludeAware(false);
			dbf.setExpandEntityReferences(false);
			
			// And, per Timothy Morgan: "If for some reason support for inline DOCTYPEs are a requirement, then 
			// ensure the entity settings are disabled (as shown above) and beware that SSRF attacks
			// (http://cwe.mitre.org/data/definitions/918.html) and denial 
			// of service attacks (such as billion laughs or decompression bombs via "jar:") are a risk."
			
			// remaining parser logic
		} catch (Exception e) {
			// This should catch a failed setFeature feature
			return FEATURE + "' is probably not supported by your XML processor.";
		} 
		DocumentBuilder safebuilder = dbf.newDocumentBuilder();
		// 获取微信POST过来反馈信息
		BufferedReader reader = request.getReader();
		String line = "";
		String xmlString = null;
		StringBuffer inputString = new StringBuffer();

		while ((line = reader.readLine()) != null) {
			inputString.append(line);
		}
		xmlString = inputString.toString();
		request.getReader().close();
		Map<String, String> map;
		//开始解析
		try{
			
			map = XMLUtil.getMap(safebuilder,xmlString); 
		} catch (Exception e) {
			// This should catch a failed setFeature feature
			return "fail";
		} 
		System.out.println("----接收到的数据如下：---" );
		
		String trade_no = map.get("transaction_id");
		String out_trade_no = map.get("out_trade_no");
		String result_code = map.get("result_code");
		System.out.println(out_trade_no+"微信支付结果:" + result_code);
 		if (checkSign(xmlString)) {
			//请在这里加上商户的业务逻辑程序代码
 			Paylog payLog = paylogService.add(out_trade_no,trade_no,result_code);
			if (result_code.equals("SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序
				if(payLog!=null&&!payLog.getStatus().equals("1")){
					boolean pay = true;
					if("ORDER".equals(payLog.getName())){
						pay = false;
					}
					paybillService.pay(payLog,1,pay);
				}
				//注意：
				//付款完成后，支付宝系统发送该交易状态通知
			}
			return returnXML(result_code);
		} else {
			return returnXML("FAIL");
		}
	}

	private boolean checkSign(String xmlString) throws Exception {
		Map<String, String> map = null;
		try {
			map = XMLUtil.getResult(xmlString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String signFromAPIResponse = map.get("sign").toString();
		if (signFromAPIResponse == "" || signFromAPIResponse == null) {
			System.out.println("API返回的数据签名数据不存在，有可能被第三方篡改!!!");
			return false;
		} 
		// 清掉返回数据对象里面的Sign数据（不能把这个数据也加进去进行签名），然后用签名算法进行签名
		map.put("sign", "");
		// 将API返回的数据根据用签名算法进行计算新的签名，用来跟API返回的签名进行比较
		String signForAPIResponse = getSign(map);
		if (!signForAPIResponse.equals(signFromAPIResponse)) {
			// 签名验不过，表示这个API返回的数据有可能已经被篡改了
			System.out.println("API返回的数据签名验证不通过，有可能被第三方篡改!!! signForAPIResponse生成的签名为" + signForAPIResponse);
			return false; 
		} 
		System.out.println("恭喜，API返回的数据签名验证通过!!!"); 
		return true;

	}

	private String returnXML(String return_code) {

		return "<xml><return_code><![CDATA[" 
				+ return_code 
				+ "]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
	}

	public String getSign(Map<String, String> map) throws Exception {
		SortedMap<String, String> signParams = new TreeMap<String, String>();
		for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
			signParams.put(stringStringEntry.getKey(), stringStringEntry.getValue());
		}
		signParams.remove("sign"); 
		return PaySign.getSign(signParams);
	}
	
	public static void main(String[] args) { 
		
		System.out.println(DigestUtils.md5Hex("appid=wxa48a8b45c449adc9&bank_type=CFT&cash_fee=1&fee_type=CNY&is_subscribe=N&mch_id=1430982602&nonce_str=8cfef17bee2b7a75a3ce09d40b497f6b&openid=oWxJ1wYLXS7_RYUDe0CxhOeXT-vo&out_trade_no=20170206191553103&result_code=SUCCESS&return_code=SUCCESS&time_end=20170206191712&total_fee=1&trade_type=APP&transaction_id=4004762001201702068967640698&key=wwwshuogeshacomzhaohaiyuan201702").toUpperCase());
	}

	@Autowired
	private PaylogService paylogService;
	@Autowired
	private PaybillService paybillService;
}
