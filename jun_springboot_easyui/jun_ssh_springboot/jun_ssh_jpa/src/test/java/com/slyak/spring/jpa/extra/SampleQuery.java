package com.slyak.spring.jpa.extra;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:stormning@163.com">stormning</a>
 * @version V1.0, 16/3/15.
 */
public class SampleQuery {

	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		if (content != null) {
			this.content = "%" + content + "%";
		}
	}
}
