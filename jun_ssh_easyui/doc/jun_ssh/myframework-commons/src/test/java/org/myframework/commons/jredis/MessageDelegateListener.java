package org.myframework.commons.jredis;

import java.io.Serializable;

public interface MessageDelegateListener {

	void handleMessage(Serializable message);

}
