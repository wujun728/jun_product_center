package org.myframework.spider;

import java.io.IOException;
import java.net.MalformedURLException;

import org.xml.sax.SAXException;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.HttpUnitOptions;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebLink;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

public class HttpunitTest {

	public static void main(String[] args) {
		try {
			//HttpunitTest.testGetHtmlContent();
			//HttpunitTest.testGetMethod();
			//HttpunitTest.testPostMethod();
			HttpunitTest.testFormSubmit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void testGetHtmlContent() throws IOException, SAXException{
		System.out.println("直接获取网页内容：");  
		HttpUnitOptions.setScriptingEnabled(false);
        // 建立一个WebConversation实例  
        WebConversation wc = new WebConversation();  
        // 向指定的URL发出请求，获取响应  
        WebResponse wr = wc.getResponse("http://www.baidu.com.cn");  
        // 用getText方法获取相应的全部内容  
        // 用System.out.println将获取的内容打印在控制台上  
        System.out.println(wr.getText());  
	}
	
	/** 
     * 用get方法获取页面内容 
     * @throws MalformedURLException 
     * @throws IOException 
     * @throws SAXException 
     */  
    public static void testGetMethod() throws MalformedURLException,  
            IOException, SAXException {  
        System.out.println("向服务器发送数据，然后获取网页内容：");  
        // 建立一个WebConversation实例  
        WebConversation wc = new WebConversation();  
        // 向指定的URL发出请求  
        WebRequest req = new GetMethodWebRequest("http://localhost:8080/test.html");  
        // 给请求加上参数  
        req.setParameter("query", "四氯化碳");  
        // 获取响应对象  
        WebResponse resp = wc.getResponse(req);  
        // 用getText方法获取相应的全部内容  
        // 用System.out.println将获取的内容打印在控制台上  
        System.out.println(resp.getText());  
  
    } 
    
    /** 
     * 用post方法获取页面内容 
     * @throws MalformedURLException 
     * @throws IOException 
     * @throws SAXException 
     */  
    public static void testPostMethod() throws MalformedURLException,  
            IOException, SAXException {  
        System.out.println("使用Post方式向服务器发送数据，然后获取网页内容：");  
        // 建立一个WebConversation实例  
        WebConversation wc = new WebConversation();  
        // 向指定的URL发出请求  
        WebRequest req = new PostMethodWebRequest("http://baidu.com/lii");  
        // 给请求加上参数  
        req.setParameter("user_name", "test");  
        req.setParameter("password", "111111");  
        // 获取响应对象  
        WebResponse resp = wc.getResponse(req);  
  
        // 用getText方法获取相应的全部内容  
        // 用System.out.println将获取的内容打印在控制台上  
        System.out.println(resp.getText());  
    }
    
    /** 
     * 获取页面链接并模拟点击 
     * @throws MalformedURLException 
     * @throws IOException 
     * @throws SAXException 
     */  
    public static void testClickLink() throws MalformedURLException,  
            IOException, SAXException {  
        System.out.println("获取页面中链接指向页面的内容：");  
        // 建立一个WebConversation实例  
        WebConversation wc = new WebConversation();  
        // 获取响应对象  
        WebResponse resp = wc.getResponse("http://www.265.com/");  
        // 获得页面链接对象  
        WebLink link = resp.getLinkWith("百度");  
        // 模拟用户单击事件  
        link.click();  
        // 获得当前的响应对象  
        WebResponse nextLink = wc.getCurrentPage();  
  
        // 用getText方法获取相应的全部内容  
        // 用System.out.println将获取的内容打印在控制台上  
        System.out.println(nextLink.getText());  
  
    }
    
    /**
	 * 测试WebForm的处理表单和提交能力
	 */
	public static void testFormSubmit(){
		HttpUnitOptions.setScriptingEnabled(false);
		WebConversation wc = new WebConversation();
		WebRequest request = new PostMethodWebRequest("http://baidu.com/li/");
		WebResponse response = null;
		try {
			response = wc.getResponse(request);
			//获得Html中的form表单，HttpUnit将他封装成WebForm对象
			WebForm form = response.getForms()[0];
			//WebForm对象提供getParameterValue的方法将根据表单中的name获得对应的value,而不用管该元素的类型。
			//对表单进行处理操作
			form.setParameter("username", "le");
			form.setParameter("password", "le");
			//提交表单 获得新的response
			response = form.submit();
			System.out.println(response.getText());
			System.out.println("----------------------------");
			// 获得页面链接对象  
	        WebLink link = response.getLinkWith("新闻中心");  
	        //模拟用户单击事件  
	        link.click();  
	        // 获得当前的响应对象  
	        WebResponse nextLink = wc.getCurrentPage();  
	        // 用getText方法获取相应的全部内容  
	        // 用System.out.println将获取的内容打印在控制台上  
	        System.out.println(nextLink.getText());  
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}
	
}

