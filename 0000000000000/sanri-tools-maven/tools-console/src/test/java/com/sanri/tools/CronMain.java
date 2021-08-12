package com.sanri.tools;

import com.sanri.tools.modules.core.utils.RandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;
import org.springframework.scheduling.support.CronSequenceGenerator;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;

public class CronMain {

    @Test
    public void testSpringCron(){
        CronSequenceGenerator cronSequenceGenerator = new CronSequenceGenerator("1-2/1 * * * * ?");
        Date current = new Date();
        int count = 10;
        while (count --> 0) {
            current = cronSequenceGenerator.next(current);
            System.out.println(DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT.format(current));
        }
    }

    @Test
    public void test(){
        String start = "43060219970719";
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9 ; j++) {
                for (int k = 0; k < 9; k+=2) {
                    String sno = i+""+j+""+k;
                    String idcard = RandomUtil.idcard("430602", "19970719", sno);
                    System.out.println(idcard);
                }
            }
        }
    }

    @Test
    public void testss() throws Exception {
//        InputStream resource = CronMain.class.getResourceAsStream("/time");
//        System.out.println(resource);
        long time = 1L * 366 * 24 * 60 * 60 * 10000;
        System.out.println(time);
        String encrypt = DesUtil.encrypt(time + "");
        System.out.println(encrypt);
        System.out.println(DesUtil.decrypt(encrypt));
    }
}
