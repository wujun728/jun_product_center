package org.myframework.commons.jredis;

import java.io.Serializable;

public interface RedisDAO {

	void sendMessage(String channel, Serializable message);

}
