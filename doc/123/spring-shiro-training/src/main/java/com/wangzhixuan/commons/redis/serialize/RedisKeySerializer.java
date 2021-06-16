package com.wangzhixuan.commons.redis.serialize;

import java.nio.charset.Charset;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.Assert;

import com.wangzhixuan.commons.utils.Charsets;

/**
 * Redis key序列化，支持更多基本类型
 * 
 * @author L.cm
 *
 */
public class RedisKeySerializer implements RedisSerializer<Object> {

	private final Charset charset;
	private final ConversionService converter;

	public RedisKeySerializer() {
		this(Charsets.UTF_8);
	}

	public RedisKeySerializer(Charset charset) {
		Assert.notNull(charset, "Charset must not be null!");
		this.charset = charset;
		this.converter = DefaultConversionService.getSharedInstance();
	}

	@Override
	public Object deserialize(byte[] bytes) {
		throw new RuntimeException("Used only for serializing key, not for deserialization.");
	}

	@Override
	public byte[] serialize(Object object) {
		if (object == null) {
			return null;
		}
		String string = converter.convert(object, String.class);
		return string.getBytes(charset);
	}

}
