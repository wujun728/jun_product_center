package com.doroodo.util.socket;

import java.io.InputStream;

/**
 * IO的相关处理, 这里面提供的都是静态类, 满足IO处理
 * @author tiant5
 *
 */
public class IOUTIL {
	
	
	public static byte[] inputStream2bytes(InputStream in) throws Exception
	{
		byte[] bufIn = new byte[BUFFER_SIZE];
		int bytesLen = in.available();
		int totalCount = 0;
		//注意, 要设置Socket超时. 不然会死死等待
		while( bytesLen > 0)
		{
			
			try
			{
				bytesLen = in.read(bufIn, totalCount, bytesLen);
				totalCount += bytesLen;
				bytesLen = in.available();
			}catch(Exception e)
			{
				//超时抛出异常, 这样可以中断read阻塞
				break;
			}
		}
		
		byte[] stores = new byte[totalCount];
		System.arraycopy(bufIn, 0, stores, 0, totalCount);
		return stores;
	}
	// 设置一次接收数据的大小, 如果不设置,默认为1M, 后续最好不要修改
	private static int BUFFER_SIZE = 1024 * 1024;

}
