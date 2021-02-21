package com.deer.wms.framework.util;

import com.deer.wms.common.exception.ServiceException;
import com.deer.wms.common.utils.DateUtils;
import com.deer.wms.common.utils.StringUtils;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * my 工具类
 *
 * @author deer
 */
public class MyUtils
{
    /**
     * 任务列表中位置拼接
     */
    public static String connectShelfNameAndRowAndColumn(String shelfName,Integer sColumn,Integer sRow){
        String shelf = shelfName;
        if(shelf.length() == 1){
            shelf = "0"+shelf;
        }
        String column = Integer.toString(sColumn);
        if(column.length()== 1){
            column = "00"+column;
        }else if(column.length()== 2){
            column = "0"+column;
        }
        String row = Integer.toString(sRow);
        if(row.length()== 1){
            row = "00"+row;
        }else if(row.length()== 2){
            row = "0"+row;
        }

        return shelf+column+row;
    }

    /**
     * 时间修改时间秒数为00
     * @param code
     * @return
     */
    public static String spliteSeconds(String code){
//        System.out.println(code.substring(6,8));
        if(!code.substring(6,8).equals(00)){
            StringBuilder stringBuilder = new StringBuilder(code);
            stringBuilder.replace(6,8,"00");
            return stringBuilder.toString();
        }else{
            return code;
        }
    }

    //获取token的用户名密码转换成编码
    public static String encode(String s) {
        if (s == null)
            return null;
        String res = "";
        try {
            res = new sun.misc.BASE64Encoder().encode(s.getBytes("GBK"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return res;
    }

    //解码
    public static String decode(String s) {
        if (s == null)
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(s);
            return new String(b,"GBK");
        } catch (Exception e) {
            return null;
        }
    }

    //解析httpServletRequest
    public static String analysisHttpServletRequest(HttpServletRequest request) throws IOException {
        String str, wholeStr = "";
        BufferedReader br = request.getReader();
        while ((str = br.readLine()) != null) {
            wholeStr += str;
        }
        return wholeStr;
    }

    //拼接打印机需要的参数
    public static String connectPrintString(String itemCode,Integer quantity,String exp,String batch,String itemName){
        return itemCode + ":" + quantity + ":" + exp + ":" + batch + ":" + itemName;
    }

    //生成随机数_指定位数位随机整数
    public static Integer randomAssignFigures(Integer number){
        Random r = new Random();
        StringBuilder rs = new StringBuilder();
        for (int i = 0; i < number; i++) {
            rs.append(r.nextInt(10));
        }
        return Integer.parseInt(rs.toString());
    }

    //yyMMdd转换为yyyy-MM-dd
    public static String stringFromatDate(String number){
        String date = "";
        try {
            Date d = new SimpleDateFormat("yyMMdd").parse(number);
//            System.out.println(d.toString());
            date = new SimpleDateFormat("yyyy-MM-dd").format(d);
        }catch(Exception e){
        }
        return date;
    }

    public static Integer backDouble(Object obj){
        Integer num = obj != null ? Double.valueOf(obj.toString().trim()).intValue() : null;
        return num;
    }
    public static Integer backInteger(Object obj){
        Integer num = obj != null ? Integer.parseInt(obj.toString().trim()) : null;
        return num;
    }
    public static String backString(Object obj){
        String varchar = obj != null ? obj.toString().trim() : null;
        return varchar;
    }

    /**
     * 获取90秒之前的时间
     */
    public static String getNinetySecondsAgo(){
//        System.out.print(DateUtils.getTime());
        long i = new Date().getTime()-90*1000;
        Date date = new Date();
        date.setTime(i);
        String ninetySecondsAgo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        return ninetySecondsAgo;
    }

    /**
     *  交货
     */
    public static Map<String,String> delivery(String transDate,String transId,String organizationId,String subInventory,
                                                          String locatorId,String lotNumber,String quantity,String shipmentNum){
        Map<String, String> map = new HashMap<>();
        map.put("transDate", transDate);
        map.put("transId", transId);
        map.put("organizationId", organizationId);
        map.put("subInventory", subInventory);
        map.put("locatorId", locatorId);
        map.put("lotNumber", lotNumber);
        map.put("quantity", quantity);
        map.put("shipmentNum", shipmentNum);
        return map;
    }

    /**
     * 工单出库
     */
    public static Map<String,String> wipOut(String organizationId,String transTypeId,String wipEntityId,String itemId,
                                              String quantity,String operationSeqNum,String lotNumber,String subInventory,
                                            String locatorId,String transDate,String transUom){
        Map<String, String> map = new HashMap<>();
        map.put("organizationId", organizationId);
        map.put("transTypeId", transTypeId);
        map.put("wipEntityId",wipEntityId);
        map.put("itemId",itemId);
        map.put("quantity",quantity);
        map.put("operationSeqNum",operationSeqNum);
        map.put("lotNumber",lotNumber);
        map.put("subInventory",subInventory);
        map.put("locatorId",locatorId);
        map.put("transDate",transDate);
        map.put("transUom",transUom);
        return map;
    }

    /**
     * 账户别名发放
     */
    public static Map<String,String> accountAliasOut(String transTypeId,String organizationId,String itemId,String subInventory,
                                            String locatorId,String transSourceName,String transSourceId,String transLotNumber,
                                            String quantity,String transDate,String transUom,String sourceHeaderId,String sourceLineId){
        Map<String, String> map = new HashMap<>();
        map.put("transTypeId", transTypeId);
        map.put("organizationId", organizationId);
        map.put("itemId",itemId);
        map.put("subInventory",subInventory);
        map.put("locatorId",locatorId);
        map.put("transSourceName",transSourceName);
        map.put("transSourceId",transSourceId);
        map.put("transLotNumber",transLotNumber);
        map.put("quantity",quantity);
        map.put("transDate",transDate);
        map.put("transUom",transUom);
        map.put("sourceHeaderId",sourceHeaderId);
        map.put("sourceLineId",sourceLineId);
        return map;
    }

    /**
     *  子库转移
     */
    public static Map<String,String> subInventoryTransfer(String transTypeId,String organizationId,String itemId,String quantity,
                                                          String subInventory,String locatorId,String transDate,String transUom,String transSubInventory,String transLocatorId,
                                                          String transLotNumber,String sourceHeaderId,String sourceLineId){
        Map<String, String> map = new HashMap<>();
        map.put("transTypeId", transTypeId);
        map.put("organizationId", organizationId);
        map.put("itemId", itemId);
        map.put("quantity", quantity);
        map.put("subInventory", subInventory);
        map.put("locatorId", locatorId);
        map.put("transDate", transDate);
        map.put("transUom", transUom);
        map.put("transSubInventory", transSubInventory);
        map.put("transLocatorId", transLocatorId);
        map.put("transLotNumber", transLotNumber);
        map.put("sourceHeaderId", sourceHeaderId);
        map.put("sourceLineId", sourceLineId);
        return map;
    }

//    计算两个时间差值是多少小时
    //type  1-计算相差天数  2-计算相差小时  3-计算相差分钟
    public static long calculateDateDiffer(Date endDate,Date nowDate){
        long nd = 1000 * 24 * 60 * 60;

        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        return day;
    }

    //生成报警map
    public static Map<String,String> backWaringMessage(String msg,String wareId,String type){
        Map<String,String> maps = new HashMap<>();
        maps.put("msg",msg);
        maps.put("warnId",wareId);
        maps.put("type",type);
        return maps;
    }

    public static List<TaskInfoWcs> hh(){
        List<TaskInfoWcs> lists = new ArrayList<>();
        TaskInfoWcs taskInfoWcs = null;
        try {

            for(int i = 0;i<20;i++){
                taskInfoWcs = new TaskInfoWcs();
                taskInfoWcs.setFromStation(i+"");
                taskInfoWcs.setTaskNo(i+"");
                taskInfoWcs.setToStation(i+"");
                lists.add(taskInfoWcs);
                taskInfoWcs = null;
            }

            return lists;
        }catch(Exception e){

        }finally{
            lists = null;
        }
        return lists;
    }

    static class TaskInfoWcs {
        private String taskNo;
        private String fromStation;
        private String toStation;
        public String getTaskNo() {
            return taskNo;
        }
        public void setTaskNo(String taskNo) {
            this.taskNo = taskNo;
        }
        public String getFromStation() {
            return fromStation;
        }
        public void setFromStation(String fromStation) {
            this.fromStation = fromStation;
        }
        public String getToStation() {
            return toStation;
        }
        public void setToStation(String toStation) {
            this.toStation = toStation;
        }
    }

    public static void main(String args[]) throws Exception{
        for(TaskInfoWcs d:hh()) {
//            System.out.println(d.fromStation);
        }
        /*long a = calculateDateDiffer(new SimpleDateFormat("yyyy-mm-dd").parse("2020-4-4"), new SimpleDateFormat("yyyy-mm-dd").parse("2020-4-1"));
          System.out.println(a);

        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        String path2 = ResourceUtils.getURL("classpath:").getPath();
        String systemName = System.getProperty("ruoyi.profile");
        //判断当前环境，如果是Windows 要截取路径的第一个 '/'
        if(!StringUtils.isBlank(systemName) && systemName.indexOf("Windows") !=-1){
            path = MyUtils.class.getResource("/").getFile().toString().substring(1);
        }else {
            path = MyUtils.class.getResource("/").getFile().toString();
        }
        System.out.println(path2);
        System.out.println(path);
//        System.out.println(ad);
        System.out.println(systemName);*/
//        File upload = new File(path2.getAbsolutePath(),"static/images/upload/");
//        if(!upload.exists()) upload.mkdirs();
//        System.out.println("upload url:"+upload.getAbsolutePath());
//        System.out.println(DateUtils.dateTimeNow());
//        System.out.println(randomAssignFigures(3));
//        System.out.println(randomAssignFigures(6));
//        System.out.println(randomAssignFigures(8));
//        Date d = new SimpleDateFormat("yyMMdd").parse("190909");
//        System.out.println(d.toString());
//        String s = new SimpleDateFormat("yyyy-MM-dd").format(d);
//        System.out.println(s);
        /*String msg = "error";
        for(int i=0;i<3;i++){
            if(i==2){
                msg = "success";
            }
            if(msg.equals("error")) {
                System.out.println(i+" "+DateUtils.getTime());
                Thread.sleep(5000);
            }else{
                System.out.println(i+" "+DateUtils.getTime());
            }
        }*/

    }
}
