package com.doroodo.util.socket;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 启动一个ServerSocket. 注意这个ServerSocket负责分发所有消息.
 * 即: 服务端收到一个消息, 立马交给处理线程
 */

public class SanySocketServer {


	private ServerSocket serverSocket;
	
	private Class<?> singleTaskCls;
	
	private ThreadPoolExecutor threadPool;
	
	private int maxClientCon;
	
	private List<SingleTask> activeClients;
	
	public static final long TIME_OUT = 30 * 60 * 1000; 


	/**
	 * 服务端初始化
	 * @param serverPort 服务端口
	 * @param threadSize 最多连接的客户端个数
	 * @param taskClass  应对单个客户端的类, 每个客户端处理自己的工作
	 * @throws Exception
	 */
	public SanySocketServer(int serverPort, int threadSize, Class<?> taskClass) throws Exception {
		

		serverSocket = new ServerSocket(serverPort);
		
		
		ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(
	            5);
		
		//设置线程的大小为threadSize, 最大的size为threadSize, 每次连接处理的时间不能超过5s
		//原因是, 超过5S, 客户端的请求可能已经中断了
		threadPool = new ThreadPoolExecutor(threadSize, threadSize + 5,
                5, TimeUnit.SECONDS, queue);
		
		singleTaskCls = taskClass;
		
		maxClientCon = threadSize;
		
		activeClients = new ArrayList<SingleTask>();
	}
	

	public void process() throws Exception {
		
		while (true) {
			
			checkValidClient();
			
			int size = activeClients.size();
			
			if (size > maxClientCon)
			{

				Thread.sleep(60000);
				continue;
			}
			
			System.out.println("当前建立连接的个数为:" + size);
			Socket client = serverSocket.accept();
			SingleTask task = (SingleTask)singleTaskCls.newInstance();
			task.setSocket(client);
			activeClients.add(task);
			threadPool.execute(task);
	
		}
	}
	

	private void checkValidClient()
	{
		for (int i = 0; i < activeClients.size(); i++)
		{
			SingleTask client = activeClients.get(i);
			if (!client.isAlive())
			{
				client.close();
				activeClients.remove(client);
			}
		}
	}

}

