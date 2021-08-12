package com.alipay.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.config.AlipayConfig;

public class AlipayUtils {
	/**
	 * 网页支付宝支付
	 * @param orderNo
	 * @param m_amount
	 * @param subject
	 * @param body
	 * @param show_url
	 * @return
	 */
	public static String pay_now(String orderNo,String m_amount, String subject, String body, String show_url) {
		// 支付类型
		String payment_type = "1";
		// 必填，不能修改
		// 服务器异步通知页面路径
		String notify_url = AlipayConfig.props.getProperty("notify_url");
		// 页面跳转同步通知页面路径
		String return_url = AlipayConfig.props.getProperty("return_url");
		// 商户订单号
		String out_trade_no = orderNo;
		// 订单名称
		// 付款金额
		String total_fee = m_amount;
		if(StringUtils.isBlank(show_url)){
			show_url = AlipayConfig.props.getProperty("show_url");
		}
		// 防钓鱼时间戳
		String anti_phishing_key = "";
		// 若要使用请调用类文件submit中的query_timestamp函数
		// 客户端的IP地址
		String exter_invoke_ip = "";
		// 非局域网的外网IP地址，如：221.0.0.1

		// 把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "create_direct_pay_by_user");
		sParaTemp.put("partner", AlipayConfig.partner);
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", payment_type);
		sParaTemp.put("notify_url", notify_url);
		sParaTemp.put("return_url", return_url);
		sParaTemp.put("seller_email", AlipayConfig.seller_email);
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("subject", subject);
		sParaTemp.put("total_fee", total_fee);
		sParaTemp.put("body", body);
		sParaTemp.put("show_url", show_url);
		sParaTemp.put("anti_phishing_key", anti_phishing_key);
		sParaTemp.put("exter_invoke_ip", exter_invoke_ip);

		// 建立请求
		String alipay = AlipaySubmit.buildRequest(sParaTemp, "post", "确认");
		return alipay;
	}
	
	/**
	 * app支付下单
	 * @param orderNo
	 * @param m_amount
	 * @param subject
	 * @param body
	 * @param show_url
	 * @return
	 */
	public static String pay_app(String orderNo,String m_amount, String subject, String body, String show_url){ 
       try{
			AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, 
	        		AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, 
	        		"json", AlipayConfig.input_charset, AlipayConfig.ALIPAY_PUBLIC_KEY,
	        		AlipayConfig.sign_type); 
	        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay 
	        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest(); 
	        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。 
	        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel(); 
	        //业务参数传入,可以传很多，参考API 
	        //model.setPassbackParams(URLEncoder.encode(request.getBody().toString())); //公用参数（附加数据） 
	        model.setBody(body.toString());//对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。 
	        model.setSubject(subject);//商品名称 
	        model.setOutTradeNo(orderNo);////商户订单号 
	        model.setTimeoutExpress("30m");//交易超时时间 
	        model.setTotalAmount(m_amount);//支付总金额 
	        model.setProductCode("QUICK_MSECURITY_PAY");//销售产品码，商家和支付宝签约的产品码 
	        request.setBizModel(model); 
	        request.setNotifyUrl(AlipayConfig.notify_url);//异步回调地址（后台） 
	        //这里和普通的接口调用不同，使用的是sdkExecute 
	        AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);  
	        String orderString = response.getBody();//就是orderString 可以直接给客户端请求，无需再做处理。 
	        return orderString;  
	    } catch (AlipayApiException e) { 
	        e.printStackTrace(); 
	        return "";
	
	    }
	} 

}
