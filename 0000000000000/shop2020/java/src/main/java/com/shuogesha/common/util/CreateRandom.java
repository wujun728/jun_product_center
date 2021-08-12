package com.shuogesha.common.util;

import java.util.Random;

public class CreateRandom {
	private static Random ran = null;  
    private static String string = "";  
    public static String getRandom(){  
          
        if(ran == null){  
            ran = new Random();  
        }  
        string = "";  
        for(int i=0;i<4;i++){    //生成4位随机数的验证码  
            string += ran.nextInt(9) + 1;  
        }  
        return string;  
    }  
}
