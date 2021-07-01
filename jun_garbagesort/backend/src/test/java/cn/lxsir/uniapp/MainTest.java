package cn.lxsir.uniapp;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @创建人 luoxiang
 * @创建时间 2019/7/18  14:18
 * @描述
 */
public class MainTest {

    public static void main(String[] args) {
        String path="E:/testpath/excel/";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        File filePathIF = new File(path,simpleDateFormat.format(new Date()));
        if (!filePathIF.getParentFile().exists()) {
            filePathIF.getParentFile().mkdir();
        }
    }
}
