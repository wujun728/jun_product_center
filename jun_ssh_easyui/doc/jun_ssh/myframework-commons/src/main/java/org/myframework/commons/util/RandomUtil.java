package org.myframework.commons.util;  

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.math.RandomUtils;


/**
 * 随机工具类
 * @ClassName: RandomUtil 
 * @Description: TODO
 * @author Wujun
 * @date 2015年6月27日 下午4:18:57
 */
@SuppressWarnings("all")
public class RandomUtil{

	 /**
	  * 得到单个随机中文字符
	  * @return
	  */
	 public static String getSingerChr() {
		 String str = null;
		 	int highPos, lowPos;
		 	Random random = new Random();
		 	highPos = (176 + Math.abs(random.nextInt(39)));
		 	lowPos = 161 + Math.abs(random.nextInt(93));
		 byte[] b = new byte[2];
		 	b[0] = (new Integer(highPos)).byteValue();
		 	b[1] = (new Integer(lowPos)).byteValue();
		 try {
			 str = new String(b, "GBk");//转成中文
		 } catch (Exception e) {
		 e.printStackTrace();
		 }
		 return str;
	}
	
	/**
	 * 取随机中文字符串
	 * @param max  	 最大长度范围
	 * @param random 位数是否可变(默认可变)
	 * @return
	 */
	public static String getRandomChinese(int max,Boolean isRandom){
		StringBuffer str =new StringBuffer("");
		int chrLength=max;
		if(isRandom){
			int min=3;
			max=(max<3)?3:max;
			chrLength=Math.abs((int)(Math.random()*(max-min+1))+min);
		}
		for (int i = chrLength; i > 0; i--) {
			str.append(getSingerChr()) ;
		}
		return str.toString();
	}
	
	/**
	 * 取随机英文字符串
	 * @param max  	 最大长度范围
	 * @param random 位数是否随机
	 * @return
	 */
	public static String getRandomChar(int max,Boolean isRandom){
		StringBuffer str =new StringBuffer("");
		isRandom=(isRandom==null)?false:isRandom;
		int chrLength=max;
		if(isRandom){
			int min=3;
			max=(max<3)?3:max;
			chrLength=Math.abs((int)(Math.random()*(max-min+1))+min);
		}
		for (int i = chrLength; i > 0; i--) {
			 int intRand = (int) (Math.random() * 52);
	         char base = (intRand < 26) ? 'A' : 'a';
	         char c = (char) (base + intRand % 26);
	         str.append(c); 
		}
		return str.toString();
	}
	
	/**
	 * 随机数字
	 * @param max  	 最大长度范围
	 * @param random 位数是否随机
	 * @return
	 */
	public static int getRandomNumber(int max,Boolean isRandom){
		Random rm = new Random(); 
		int chrLength=max;
		isRandom=(isRandom==null)?false:isRandom;
		if(isRandom){
			int min=3;
			max=(max<1)?1:max;
			chrLength=Math.abs((int)(Math.random()*(max-min+1))+min);
		}
	    double pross = (1 + rm.nextDouble()) * Math.pow(10, chrLength);   
	    String fixLenthString = String.valueOf(pross).replace(".", "");  
	    return Integer.parseInt(fixLenthString.substring(1, chrLength + 1));  
	}
	
	/**
	 * 随机数字+中文
	 * @param max  	 最大长度范围
	 * @param random 位数是否随机
	 * @return
	 */
   public static String getRandomCharAndNumr(int max,Boolean isRandom) {
       StringBuffer sb = new StringBuffer();
       Random random = new Random();
       int chrLength=max;
       isRandom=(isRandom==null)?false:isRandom;
       if(isRandom){
   			int min=3;
   			max=(max<3)?3:max;
   			chrLength=Math.abs((int)(Math.random()*(max-min+1))+min);
       }
       for (int i = 0; i < chrLength; i++) {
           String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字
           if ("char".equalsIgnoreCase(charOrNum)){// 字符串
               int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; // 取得大写字母还是小写字母
               sb.append((char) (choice + random.nextInt(26)));
           } else if ("num".equalsIgnoreCase(charOrNum)){// 数字
               sb.append(String.valueOf(random.nextInt(10)));
           }
       }
       return sb.toString();
   }
   /**
    * 得到随机的一组List(随机数据)
    * @param list 需处理的List集合。为空，表示直接生成随机数List，不为空，则在已有的list集合中随机取。
    * @param type 返回list集合存放的类型[日期Date,字符String,中文Chinese,数字Number]
    * @param itemLength 集合中元素的最大长度
    * @param isRandom 集合中元素位数是否随机
    * @param listSize （可选）返回的List最大记录数,为空，则取默认list.size
    * @return
    */

	public static List getRandomList(List list,String type, Integer itemLength,Boolean isRandom, Integer...listSize) {
		Assert.notNull(type,"the Random type not be null");
		int maxSize=0;
		List returnList=new ArrayList();
		Random random = new Random();
		if(list!=null){//不为空，则在已有的list集合中随机取。
			 for(int i=0;i<list.size();i++){
				 returnList.add(random.nextInt(returnList.size()),list.get(i));
			 }
		}else{//为空，表示直接生成随机数List
			maxSize=listSize.length;
			for(int i=0;i<maxSize;i++){
				if(type.equalsIgnoreCase("Date")){
					 returnList.add(i, getRandomDate());
				}else if(type.equalsIgnoreCase("Chinese")){
					returnList.add(i, getRandomChinese(itemLength==null?random.nextInt(10):itemLength,isRandom==null?true:isRandom));
				}else if(type.equalsIgnoreCase("Number")){
					returnList.add(i, getRandomNumber(itemLength==null?random.nextInt(10):itemLength,isRandom==null?true:isRandom));
				}else{
					returnList.add(i, getRandomCharAndNumr(itemLength==null?random.nextInt(10):itemLength,isRandom==null?true:isRandom));
				}
			 }
		}
		return returnList;
	}
	
	/**
	 * 随机日期
	 * @return
	 */
	public static String getRandomDate(){
		Random rand = new Random();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.set(2010, 0, 1);
		long start = cal.getTimeInMillis();
		cal.set(2013, 0, 1);
		long end = cal.getTimeInMillis();
		Date d = new Date(start + (long)(rand.nextDouble() * (end - start)));
		return format.format(d);
	}

	/**
	 * 随机获取数组中的一个元素
	 * List集合可用list.toArray()方法，先专为数组，再用此方法
	 * @param array
	 * @return
	 */
	public static Object getRandomRow(Object[] array) {
		Object data = null;
		try {
			if (null != array && array.length > 0) {
				Random random = new Random();
				int length = array.length;
				int index = random.nextInt(length);
				if (index >= 0 && index < length) {
					data = array[index];
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return data;
	}
	/**
	 * 得到list中随机的某行数据
	 * @param list
	 * @return
	 */
	public static Object getRowByList(List list) {
		Object obj = null;
		try {
			if (list!=null && list.size()>0){
				Random random = new Random();
				int length = list.size();
				int index = random.nextInt(length);
				if (index >= 0 && index < length) {
					obj = list.get(index);
				}
				if(obj==null){
					obj=getRowByList(list);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return obj;
	}
	/**
	 * 随机范围的数字
	 * @param min  最小值
	 * @param max  最大值
	 * @return
	 */
	public static int getRandomNumber(int min,int max)
	{
		Long temp = Math.round(Math.random()*(max-min)+min); 
		return temp.intValue();
	}

}
