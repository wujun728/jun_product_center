package com.deer.wms.framework.util;

import java.text.DecimalFormat;

/**
 * shiro 工具类
 *
 * @author deer
 */
public class BillNoUtils
{
    /**
     * 生成入库单号
     */
    public static String generateInBillNo() {
        String billNo = "RK" + "-" + RandomNoUtils.createDateTimeNo();
        return billNo;
    }

    /**
     * 生成出库单号
     */
    public static String generateOutBillNo() {
        String billNo = "CK" + "-" + RandomNoUtils.createDateTimeNo();
        return billNo;
    }

    public static String breakUpDate(String dateTime){
        String date = "20" + dateTime.substring(0,2) + "-" + dateTime.substring(2,4) + "-" + dateTime.substring(4,6);
        return date;
    }

    public static void main(String[] args) {
//        System.out.println(generateInBillNo());
//        System.out.println(generateOutBillNo());
//        System.out.println(breakUpDate("191111"));
//        System.out.println(new DecimalFormat("0.00%").format(0.0081));
    }

}
