package org.myframework.commons.jredis;

import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;

public class TestRedisProduce {
	private RedisDAOImpl redisDAO = null;

	private RedisConnection jedisConnection = null;

	@Before
	public void setUp() throws Exception {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"spring-service-test.xml");
		redisDAO = (RedisDAOImpl) applicationContext.getBean("redisDAO");

		jedisConnection = applicationContext
				.getBean(JedisConnectionFactory.class).getConnection();
	}

	@Test
	public void testPublishMessage() throws Exception {
		String msg = "Hello, Redis!";
		redisDAO.sendMessage("java", msg); // 发布字符串消息

		Integer[] values = new Integer[] { 21341, 123123, 12323 };
		redisDAO.sendMessage("java", values); // 发布一个数组消息
	}

	@Test
	public void testPushMessage() throws Exception {

		//
		// for (int i = 0; i < 10; i++) {
		//// System.out.println(ls.rightPop("java"));
		// System.out.println(new String(
		// jedisConnection.rPop("java".getBytes())));
		// }

		Runnable task = new Runnable() {
			@Override
			public void run() {
				System.out.println("task over");
				System.out.println(
						new String(jedisConnection.rPop("java".getBytes())));
			}
		};
		// executor.execute(task);
		ScheduledExecutorService scheduler = Executors
				.newScheduledThreadPool(10);
		scheduler.scheduleAtFixedRate(task, 10, 10, TimeUnit.SECONDS);

		String msg = "Hello, Redis!";
		ListOperations<String, Object> ls = redisDAO.getRedisTemplate()
				.opsForList();
		for (int i = 0; i < 10; i++) {
			ls.leftPush("java", msg + i);
		}

	}
	
 
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"spring-service-test.xml");
		final RedisTemplate<String,String> redisTemplate =  applicationContext
				.getBean( RedisTemplate.class);
		redisTemplate.opsForValue().set("redis_test:test1", "444", 10000 );
		redisTemplate.opsForValue().set("redis_test:test2", "333", 10000,TimeUnit.SECONDS);
		redisTemplate.opsForValue().set("redis_test:test3", "333", 10000,TimeUnit.SECONDS);
		Set keys = redisTemplate.keys("redis_test*");
		System.out.println(keys);
	}
	
	 
}
