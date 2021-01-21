package org.myframework.web.filter;

import org.apache.commons.lang.StringEscapeUtils;
import org.junit.Test;
import org.myframework.web.filter.XssFilter.XssRequestWrapper;
import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.Policy;
import org.owasp.validator.html.PolicyException;

public class XssFilterTest {

	private static Policy policy = null;

	static {
		try {
			policy = Policy.getInstance( XssRequestWrapper.class.getClassLoader()
					.getResourceAsStream("antisamy-anythinggoes.xml"));
		} catch (PolicyException e) {
			 
		}
	}

	public String clean(String dirtyInput) throws Exception, PolicyException {
		AntiSamy antiSamy = new AntiSamy();
		final CleanResults cr = antiSamy.scan(dirtyInput, policy);
		// 安全的HTML输出
		// 在我们使用AntiSamy进行测试时，发现确实能够有效的控制xss攻击，用户的非法输入全部被删除或替换了，但是也发现会把“&nbsp;”转换成乱码，把双引号转换成"&quot;"
		String str = StringEscapeUtils.unescapeHtml(cr.getCleanHTML());
//		String nbsp = antiSamy.scan("", policy).getCleanHTML();
		return cr.getCleanHTML()    ;
	}

	@Test
	public void testXss() throws PolicyException, Exception {
		String dirtyInput ="<IMG align=\"left\"> </IMG>&nbsp;";
		String cleanHtml = clean(dirtyInput);
		System.out.println(dirtyInput);
		System.out.println(cleanHtml);
	}
	
	@Test
	public void testXssScript() throws PolicyException, Exception {
		String dirtyInput ="<IMG src='http://www.baidu.com.jpg' />";
		String cleanHtml = clean(dirtyInput);
		System.out.println(dirtyInput);
		System.out.println(cleanHtml);
	}
	
	@Test
	public void testXssLi() throws PolicyException, Exception {
		String dirtyInput ="<li id=\"abc\">abc&nbsp;</li>";
		String cleanHtml = clean(dirtyInput);
		System.out.println(dirtyInput);
		System.out.println(cleanHtml);
	}
	
	@Test
	public void testXssIllegalHtml() throws PolicyException, Exception {
		String dirtyInput =">5";
		String cleanHtml = clean(dirtyInput);
		System.out.println(dirtyInput);
		System.out.println(cleanHtml);
	}
	
	@Test
	public void testXssHtml() throws PolicyException, Exception {
		String dirtyInput ="\"'\"/><img src=\"https://www.baidu.com/img/bdlogo.png\"></ScRIpt>";
		String cleanHtml = clean(dirtyInput);
		System.out.println(dirtyInput);
		System.out.println(cleanHtml);
		System.out.println("//abc///efg".replaceAll("//", "/"));
//		StringEscapeUtils.
	}
		
}
