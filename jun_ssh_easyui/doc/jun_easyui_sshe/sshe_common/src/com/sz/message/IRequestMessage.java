package com.sz.message;

import org.codehaus.jackson.annotate.JsonIgnore;

public interface IRequestMessage extends IMessage {

	/**
	 * �����Ϣ�����
	 */
	@JsonIgnore
	public String getName();

}
