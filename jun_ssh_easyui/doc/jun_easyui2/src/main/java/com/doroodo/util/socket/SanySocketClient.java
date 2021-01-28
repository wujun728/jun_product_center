package com.doroodo.util.socket;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Date;

/**
 * 该类是和业务无关的Socket连接
 * 优点:
 * 1. 直接使用二进制Byte作为通信接口, 效率高
 * 2. 所有的接口采用装饰器模式, 出现问题只需要修改最底层的代码, 容易维护
 * @author tiant5
 *
 */
public class SanySocketClient {
	
	
	private Socket clientSocket;
	
	private InetSocketAddress tcpAddress;
	
	private int timeOut = 1000;  //超时设置, 默认一分钟
	
	private OutputStream out;
	
	private InputStream in;
	
	private Date sendTime;
	
	private final int receiveMaxSize = 1024 * 1024; // 设置一次接收数据的大小, 如果不设置,默认为1M
	
	//客户端唯一标识号码, 类似于HTTP的Session
	private long clientID = -1;
	
	public SanySocketClient(String ip, int port)
	{
		tcpAddress = new InetSocketAddress(ip,port);
	}
	
	/**
	 * 设置超时时间, 客户端必备良药, 否则, 服务端不反馈, 就需要等到天荒地老了, 因为这个是阻塞的模式
	 * @param tm
	 */
	public void setTimeOut(int tm)
	{
		timeOut = tm;
	}
	
	
	/**
	 * 对服务端口的连接
	 * @return true成功, false表示失败
	 */
	public boolean connect()
	{
		try
		{
			if(clientSocket != null && clientSocket.isConnected()) 
			{
				out.close();
				in.close();
				clientSocket.close();
				clientSocket = null;
			}
			clientSocket = new Socket();
			clientSocket.connect(tcpAddress);
			
			if( clientSocket.isConnected() )
			{
				clientSocket.setSoTimeout(timeOut);
				out = clientSocket.getOutputStream();
				in = clientSocket.getInputStream();
				return true;
			}
		}
		catch(IOException ex)
		{
		}
		clientSocket = null;
		out = null;
		in = null;
		return false;
	}
	
	/**
	 * 发送特定编码的String到服务端
	 * @param sendString
	 * @param charset 指定的编码
	 * @throws Exception 
	 */
	public void send(String sendString, String charset) throws Exception
	{
		
		byte[] datas = sendString.getBytes(charset);
		send(datas);
	}
	
	
	/**
	 * 发送二进制码流到服务端, 这个功能很弱的说, 不如大家都发字节流
	 * 后面应该修正为发字节流的接口
	 * @param datas
	 * @throws Exception 
	 */
	public void send(byte[] datas) throws Exception
	{
		if( null == clientSocket || clientSocket.isClosed()) 
		{
			throw new Exception("socket closed!");
		}
		out.write(datas);
		out.flush();
		sendTime = new Date(System.currentTimeMillis()); //获取当前时间);
	}
	
	/**
	 * 接收Server端的信息, 并且以特定编码的String 保存
	 * 最佳的方式, 是将另外一个receive方法包装, 避免维护过多的接口.
	 * @param charset
	 * @return
	 * @throws Exception 
	 */
	public String receive(String charset) throws Exception
	{
		byte[] receiveData = receive();
		if (receiveData == null)
		{
			return null;
		}
		
		String sData = new String(receiveData, charset);
		return sData.trim();
	}
	
	
	
	/**
	 * 接收指定位置的信息, 并用byte[]的方式存储.
	 * 实质上什么时候结束一条消息, 是跟业务相关.
	 * usage/com/ostrichmyself/socket/server/SimpleTask.java 中用死循环来阻塞读取
	 * 会是更好的方式
	 * @return
	 * @throws Exception 
	 */
	public byte[] receive() throws Exception
	{
		if( null == clientSocket || clientSocket.isClosed()) 
		{
			throw new Exception("socket closed!");
		}
		byte[] bufIn = new byte[receiveMaxSize];
		Thread.sleep(1000);
		int bytesLen = in.available();
		int totalCount = 0;
		
		while( bytesLen > 0)
		{
			
			try
			{
				bytesLen = in.read(bufIn, totalCount, bytesLen);
				totalCount += bytesLen;
				bytesLen = in.available();
			}catch(SocketTimeoutException e)
			{
				System.out.println(e.getMessage());
				//超时抛出异常, 这样可以中断read阻塞
				break;
			}
			
		}
		
		byte[] stores = new byte[totalCount];
		
		System.arraycopy(bufIn, 0, stores, 0, totalCount);
		return stores;
	}

	/**
	 * 得到发送时间
	 * @return
	 */
	public Date getSendTime() {
		return sendTime;
	}
	
	public void close()
	{
		try {
			clientSocket.close();
			in.close();
			out.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public long getClientID() {
		return clientID;
	}

	public void setClientID(long clientID) {
		this.clientID = clientID;
	}
	
	
	/**
	 * 比较特殊的接收方式, 比如按照状态机的接收方式,  需要将该接口暴露在外面, 留给外部较大的自由度
	 * 原因是大部分数据接收是分批次, 并且有结束标示
	 * @return
	 */
	public InputStream getInputStream()
	{
		return in;
	}
	
	
	public OutputStream getOutputStream()
	{
		return out;
	}

}
