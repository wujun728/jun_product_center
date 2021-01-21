package com.doroodo.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;

import com.doroodo.code.GeneratorProperties;
import com.doroodo.config.SysVal;

public class SmarteUntil {

	/**
	 * DateMin(计算两个日期之间相差多少分钟) TODO(传入的日期需要有统一的数据格式，如“yyyy-MM-dd HH:mm:ss”)
	 * 
	 * @param startTime
	 *            (开始日期)
	 * @param endTime
	 *            (结束日期)
	 * @param format
	 *            (日期格式，如“yyyy-MM-dd HH:mm:ss”)
	 * @param @return 两个日期之间相差多少分钟
	 * @Exception ParseException
	 * @since SMARTE平台编程规范v0.1
	 */
	public static int DateMin(String startTime, String endTime, String format) {
		int min = 0;
		long diff = 0;
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long nh = 1000 * 60 * 60;// 一小时的毫秒数
		long nm = 1000 * 60;// 一分钟的毫秒数
		long ns = 1000;// 一秒钟的毫秒数
		SimpleDateFormat sd = new SimpleDateFormat(format);
		try {
			diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
			min = (int) (diff % nd % nh / nm);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return min;
	}

	/**
	 * DateNowMin(计算某日期到现在相差多少分钟) TODO(传入的日期需要有统一的数据格式，如“yyyy-MM-dd HH:mm:ss”)
	 * 
	 * @param endTime
	 *            (结束日期)
	 * @param format
	 *            (日期格式，如“yyyy-MM-dd HH:mm:ss”)
	 * @param @return 某日期到现在相差多少分钟
	 * @since SMARTE平台编程规范v0.1
	 */
	public static int DateNowMin(String endTime, String format) {
		int i = 0;
		SimpleDateFormat df = new SimpleDateFormat(format);// 设置日期格式
		i = DateMin(df.format(new Date()), endTime, format);
		return i;
	}

	/**
	 * GetSurplusMonths(获取一年剩余月份，包括当前月)
	 * 
	 * @param month
	 *            (当前月，如“2”，“02”这种形式)
	 * @param @return 获取一年剩余月份的字符串数组
	 * @since SMARTE平台编程规范v0.1
	 */
	public static String[] GetSurplusMonths(String month) {
		String stringSurplusMonths = "";
		int iMonth = Integer.parseInt(month);
		 String[] MONTHS = { "01", "02", "03", "04", "05", "06",
					"07", "08", "09", "10", "11", "12" };
		for (int i = 0; i < MONTHS.length; i++) {
			String strMonth = MONTHS[i];
			if (Integer.parseInt(strMonth) >= iMonth) {
				stringSurplusMonths += strMonth + ",";
			}
		}
		if (stringSurplusMonths.length() > 1) {
			stringSurplusMonths = stringSurplusMonths.substring(0,
					stringSurplusMonths.length() - 1);
		}
		return stringSurplusMonths.split(",");
	}

	/**
	 * getModthDays(获取某年某月有多少天)
	 * 
	 * @param year
	 *            (年份，如“2014”)
	 * @param month
	 *            (月份，如“02”，“2”)
	 * @param @return 一个月有多少天
	 * @since SMARTE平台编程规范v0.1
	 */
	public static int getModthDays(String year, String month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.parseInt(year));
		cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);// Java月份才0开始算
		return cal.getActualMaximum(Calendar.DATE);
	}

	/**
	 * getDateByString(通过日期字符串获取Date类型日期)
	 * 
	 * @param time
	 *            (日期字符串)
	 * @param format
	 *            (传入的日期需要有统一的数据格式，如“yyyy-MM-dd HH:mm:ss”)
	 * @param @return Date类型日期
	 * @Exception ParseException
	 * @since SMARTE平台编程规范v0.1
	 */
	public static Date getDateByString(String time, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		try {
			return df.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 在文件里面的指定行插入一行数据
	 * 
	 * @param inFile
	 *            文件
	 * @param lineno
	 *            行号
	 * @param lineToBeInserted
	 *            要插入的数据
	 * @throws Exception
	 *             IO操作引发的异常
	 */
	public static int insertStringInFile(String file, int lineno,
			String lineToBeInserted) {
		int i = -1;
		try {
			// 临时文件
			File inFile = new File(file);
			File outFile = File.createTempFile("name", ".tmp");
			// 输入
			FileInputStream fis = new FileInputStream(inFile);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis,
					"UTF-8"));
			// 输出
			FileOutputStream fos = new FileOutputStream(outFile);
			PrintWriter out = new PrintWriter(new OutputStreamWriter(fos,
					"UTF-8"));
			// 保存一行数据
			String thisLine;
			// 行号从1开始
			i = 1;
			while ((thisLine = in.readLine()) != null) {
				// 如果行号等于目标行，则输出要插入的数据
				if (i == lineno) {
					out.println(lineToBeInserted);
				}
				// 输出读取到的数据
				out.println(thisLine);
				// 行号增加
				i++;
			}
			out.flush();
			out.close();
			in.close();
			// 删除原始文件
			inFile.delete();
			// 把临时文件改名为原文件名
			outFile.renameTo(inFile);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return i;
		}
	}

	public static void removeLineFromFile(String file, String lineToRemove)
			throws Exception {
		File inFile = new File(file);
		File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
		// 输出
		FileOutputStream fos = new FileOutputStream(tempFile);
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(fos, "UTF-8"));

		// 输入
		FileInputStream fis = new FileInputStream(inFile);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis,
				"UTF-8"));
		String line = null;
		while ((line = br.readLine()) != null) {
			if (!line.trim().equals(lineToRemove)) {
				pw.println(line);
				pw.flush();
			}
		}
		br.close();
		pw.close();
		inFile.delete();
		tempFile.renameTo(inFile);
	}

	/**
	 * 
	 * @Title: getTxtContent
	 * @Description: 将文本读取到List中并返回
	 * @param @param path - 文件路径
	 * @param @return
	 * @return List<String> - 返回读取文件行的集合
	 * @throws
	 */
	public static List<String> getFileContent(String path) {
		List<String> strList = new ArrayList<String>();
		try {
			File file = new File(path);
			InputStreamReader read = new InputStreamReader(new FileInputStream(
					file), "UTF-8");
			BufferedReader reader = new BufferedReader(read);
			String line;
			while ((line = reader.readLine()) != null) {
				strList.add(line);
			}
			read.close();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strList;
	}

	/**
	 * 
	 * @Title: listFileByRow
	 * @Description: 获取指定行的值
	 * @param @param path - 文件路径
	 * @param @param row - 指定行
	 * @return String - 返回指定行的数据,没有指定行时数据返回空字符串
	 * @throws
	 */
	public static String listFileByRow(String path, Integer row) {
		List<String> strList = getFileContent(path);
		int size = strList.size();
		if (size >= (row - 1))
			return strList.get(row - 1);
		else
			return "";

	}

	public static int findLineFormFile(String file, String line) {
		int i = -1;
		try {
			// 临时文件
			File inFile = new File(file);
			// 输入
			FileInputStream fis = new FileInputStream(inFile);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis,
					"UTF-8"));
			// 保存一行数据
			String thisLine;
			// 行号从1开始
			i = 1;
			while ((thisLine = in.readLine()) != null) {
				if (thisLine.trim().endsWith(line)) {
					fis.close();
					in.close();
					return i;
				}
				// 行号增加
				i++;
			}
		} catch (Exception e) {

		} finally {
			return i;
		}

	}

	public static void removeLineFromFile(String file, int iline) {
		try {
			File inFile = new File(file);
			File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
			// 输出
			FileOutputStream fos = new FileOutputStream(tempFile);
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(fos,
					"UTF-8"));

			// 输入
			FileInputStream fis = new FileInputStream(inFile);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis,
					"UTF-8"));
			String line = null;
			int i = 1;
			while ((line = br.readLine()) != null) {
				if (!(i == iline)) {
					pw.println(line);
					pw.flush();
				}
				i++;
			}
			fos.flush();
			fos.close();
			fis.close();
			br.close();
			pw.flush();
			pw.close();
			inFile.delete();
			tempFile.renameTo(inFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void moveFile(File pdfName, String newPath) {
		String oldPath = pdfName.getPath();
		try {
			int bytesum = 0;
			int byteread = 0;
			// 新建一个文件的路径
			File file = new File(newPath + "\\" + pdfName.getName());
			// 判断该路径是否有这个文件
			if (!file.isDirectory()) {

				// 可以创建多个文件夹，若出现文件访问失败，则要加上"getParentFile()","mkdir()"只能建立单个的文件夹
				file.getParentFile().mkdirs();
			}

			// 找到原文件，读取
			InputStream inStream = new FileInputStream(oldPath);
			// 在新的路径下面写入文件，其中FILE对象的path和name用： "/" 隔开
			FileOutputStream fs = new FileOutputStream(newPath + "/"
					+ pdfName.getName());
			byte[] buffer = new byte[1444];
			while ((byteread = inStream.read(buffer)) != -1) {
				bytesum += byteread;
				fs.write(buffer, 0, byteread);
			}
			inStream.close();

			// 找到旧路径的文件，删除
			File delPdfName = new File(oldPath);
			delPdfName.delete();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	// 获取当前时间
	public static String getCurrentDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		return df.format(new Date());
	}

	public static String printfWeeks(String w1, String m1, String y1)
			throws Exception {
		int y = Integer.parseInt(y1);
		int m = Integer.parseInt(m1) - 1;
		int w = Integer.parseInt(w1) - 1;
		Calendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setFirstDayOfWeek(Calendar.MONDAY); // 设置每周的第一天为星期一
		gregorianCalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);// 每周从周一开始
		gregorianCalendar.setMinimalDaysInFirstWeek(7); // 设置每周为7天
		gregorianCalendar.set(Calendar.YEAR, y);
		gregorianCalendar.set(Calendar.MONTH, m);
		gregorianCalendar.set(Calendar.WEEK_OF_MONTH, w);
		String mon1 = "";
		String d1 = "";
		if ((gregorianCalendar.get(Calendar.MONTH) + 1) < 10) {
			mon1 = "0" + (gregorianCalendar.get(Calendar.MONTH) + 1);
		} else {
			mon1 = Integer.toString(gregorianCalendar.get(Calendar.MONTH) + 1);
		}
		if (gregorianCalendar.get(Calendar.DAY_OF_MONTH) < 10) {
			d1 = "0" + gregorianCalendar.get(Calendar.DAY_OF_MONTH);
		} else {
			d1 = Integer.toString(gregorianCalendar.get(Calendar.DAY_OF_MONTH));
		}
		String begintime = gregorianCalendar.get(Calendar.YEAR) + "-" + mon1
				+ "-" + d1;
		gregorianCalendar.add(Calendar.DAY_OF_WEEK, 6);
		String mon2 = "";
		String d2 = "";
		if ((gregorianCalendar.get(Calendar.MONTH) + 1) < 10) {
			mon2 = "0" + (gregorianCalendar.get(Calendar.MONTH) + 1);
		} else {
			mon2 = Integer.toString(gregorianCalendar.get(Calendar.MONTH) + 1);
		}
		if (gregorianCalendar.get(Calendar.DAY_OF_MONTH) < 10) {
			d2 = "0" + gregorianCalendar.get(Calendar.DAY_OF_MONTH);
		} else {
			d2 = Integer.toString(gregorianCalendar.get(Calendar.DAY_OF_MONTH));
		}
		String endtime = gregorianCalendar.get(Calendar.YEAR) + "-" + mon2
				+ "-" + d2;
		String datetime = begintime + "!" + endtime;
		return datetime;

	}
	
	//获取当前时间
	public static	String getCurrentTime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		return df.format(new Date());
	}
	
	public static void log(String sysInfo){
		System.out.println("["+getCurrentTime()+"]-系统日志："+sysInfo);
	}
	
	// "yyyy-MM-dd HH:mm:ss"格式的时间转成数字串
	public static long StringTounixTime(String time) {
		long epoch = 0;
		try {
			epoch = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(time).getTime();
		} catch (ParseException e) {
		}
		return epoch;
	}

		// 数字串转成"yyyy-MM-dd HH:mm:ss"格式的时间
		public static String unixTimeToString(long unixtime) {
			return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.format(new java.util.Date(unixtime));
		}
		
		// 根据日期获取星期
		public static String getWeekOfDate(Date date) {
			String[] weekDaysName = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五",
					"星期六" };
			String[] weekDaysCode = { "0", "1", "2", "3", "4", "5", "6" };
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
			return weekDaysName[intWeek];
		}
		
		public static String getCurrentTimeForFileName() {
			SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");// 设置日期格式
			return df.format(new Date());
		}
		
		// md5
		public static String md5(String string) {
			if (string.trim().isEmpty())
				return "";
			char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
					'a', 'b', 'c', 'd', 'e', 'f' };
			try {
				byte[] bytes = string.getBytes();
				MessageDigest messageDigest = MessageDigest.getInstance("MD5");
				messageDigest.update(bytes);
				byte[] updateBytes = messageDigest.digest();
				int len = updateBytes.length;
				char myChar[] = new char[len * 2];
				int k = 0;
				for (int i = 0; i < len; i++) {
					byte byte0 = updateBytes[i];
					myChar[k++] = hexDigits[byte0 >>> 4 & 0x0f];
					myChar[k++] = hexDigits[byte0 & 0x0f];
				}
				return new String(myChar);
			} catch (Exception e) {
				return null;
			}
		}

		public static FileItem fileToFileItem(File file) {
			FileItemFactory factory = new DiskFileItemFactory(16, null);
			FileItem item = factory.createItem("file", "text/plain", true,
					file.getName());
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			try {
				FileInputStream fis = new FileInputStream(file);
				OutputStream os = item.getOutputStream();
				while ((bytesRead = fis.read(buffer, 0, 8192)) != -1) {
					os.write(buffer, 0, bytesRead);
				}
				os.close();
				fis.close();
			} catch (IOException e) {
			}

			return item;
		}
		
		public static byte[] toByteArray(InputStream input) throws IOException {
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buffer = new byte[4096];
			int n = 0;
			while (-1 != (n = input.read(buffer))) {
				output.write(buffer, 0, n);
			}
			return output.toByteArray();
		}
		
		/**
		 * 获取指定HTML标签的指定属性的值
		 * @param source 要匹配的源文本
		 * @param element 标签名称
		 * @param attr 标签的属性名称
		 * @return 属性值列表
		 */
		public static List<String> match(String source, String element, String attr) {
			List<String> result = new ArrayList<String>();
			String reg="<" + element + "[^>]+" + attr + "\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";
			Matcher m = Pattern.compile(reg).matcher(source);
			while (m.find()) {
				String r = m.group(1);
				result.add(r);
			}
			return result;
		}
		
		public static HashMap<String,String> socketCmd(String ip,int port ,String Cmd){
			//int _result=0;
			HashMap<String,String>_res=new HashMap<String,String>();
			System.out.println(Cmd);
			Socket s = new Socket();
			try {
				int connectiontimeout=SysVal.DAS_SERVER_CONNECTION_TIMEOUT;
				int readtimeout=SysVal.DAS_SERVER_READ_TIMEOUT;
				s.connect(new InetSocketAddress( ip, port ),connectiontimeout);//建立连接超时
				s.setSoTimeout(readtimeout);
		        System.out.println( "remote socket " + s.getRemoteSocketAddress()); 
		        
		        InputStream in = s.getInputStream();  	        
		        InputStreamReader reader = new InputStreamReader(in);  
		        OutputStreamWriter writer = new OutputStreamWriter(s.getOutputStream()); 
		        writer.write(Cmd);
		        writer.flush();
		        
		        char [] cbuf = new char[100];  
		        int len = reader.read(cbuf);  
		        StringBuilder sb = new StringBuilder(100);  
		          
		        sb.append(cbuf, 0, len);  
		        System.out.println(sb.toString()); 
		        _res.put("code", "0");
				_res.put("msg", sb.toString());
		        writer.close();  
		        reader.close();  
		        s.close(); 
			} catch (UnknownHostException e) {
				//_result=3;//配置有误，未知主机
				_res.put("code", "3");
				_res.put("msg", "配置有误，未知主机");
				System.out.println("配置有误，未知主机");
			} catch(ConnectException e){
				//_result=3;//不能连接到服务
				System.out.println("不能连接到服务");
				_res.put("code", "3");
				_res.put("msg", "不能连接到服务");
			} catch(SocketTimeoutException e){			
				if (!s.isClosed() && s.isConnected())
	                {System.out.println("读取超时");
	               // _result=5;//等待返回超时，执行命令超时
	    			_res.put("code", "5");
	    			_res.put("msg", "执行命令超时");
	                }
	            else
	                {System.out.println("连接超时");
	                //_result=4;//连接超时
	    			_res.put("code", "4");
	    			_res.put("msg", "连接超时");
	                }
				
				//等待返回超时，执行命令超时
				//e.printStackTrace();
			}catch (IOException e) {
				//_result=7;//其他异常
				e.printStackTrace();
				_res.put("code", "7");
				_res.put("msg", "其他异常");
			}		
			catch(Exception ex){
				//_result=7;//其他异常
				ex.printStackTrace();
				_res.put("code", "7");
				_res.put("msg", "其他异常");
			}
			return _res;
	        
	 
		}
		
		
}
