package com.shuogesha.common.util;

import java.math.BigDecimal;

public class BigDecimalArith {
    private static final BigDecimal zero = new BigDecimal("0"); 
    
	/** arg1 + arg2
     * @param arg1
     * @param arg2
     * @return
     */
    public static BigDecimal add(BigDecimal arg1,BigDecimal arg2){
        return arg1.add(arg2);
    }
    
    /** arg1 - arg2
     * @param arg1
     * @param arg2
     * @return
     */
    public static BigDecimal sub(BigDecimal arg1,BigDecimal arg2){
        return arg1.subtract(arg2);
    }
    
    /** arg1 * arg2
     * @param arg1
     * @param arg2
     * @return
     */
    public static BigDecimal mul(BigDecimal arg1,BigDecimal arg2){
        return arg1.multiply(arg2);
    }
    
    /** arg1 / arg2
     * 默认保留两位小数，四舍五入
     * @param arg1
     * @param arg2
     * @return
     */
    public static BigDecimal div(BigDecimal arg1,BigDecimal arg2){
        if(arg2.compareTo(zero) == 0){
            return new BigDecimal("0.00");
        }
        return arg1.divide(arg2,2,BigDecimal.ROUND_HALF_UP);
    }
    
    /** 格式化小数,保留两位小数,舍入根据模式判断
     * @param arg1
     * @return
     */
    public static BigDecimal setScale(BigDecimal arg1){
        return arg1.setScale(2,BigDecimal.ROUND_HALF_UP); 
    }
}
