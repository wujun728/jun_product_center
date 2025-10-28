package com.ruoyi;

import com.ruoyi.common.utils.MessageUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Title RuoyiApplicationTest
 * @Description
 * @Author wangyan
 * @Date 2020/7/14  9:58
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RuoyiApplicationTest {
    @Test
   public void contextLoads() {
        String msg= MessageUtils.message("user.jcaptcha.error");
        System.out.println("----------msg= "+msg);
    }
}
