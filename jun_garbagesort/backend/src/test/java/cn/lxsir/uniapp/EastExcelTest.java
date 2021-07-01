package cn.lxsir.uniapp;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @创建人 luoxiang
 * @创建时间 2019/7/17  13:20
 * @描述
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EastExcelTest {

    @Test
    public void easyExcelTest() throws IOException {
//        new BufferedInputStream(new File("C:\\Users\\CH000\\Desktop\\test.xlsx").).
        InputStream inputStream = FileUtil.getResourcesFileInputStream("C:\\Users\\CH000\\Desktop\\test.xlsx");
        List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(1, 0));
        for (Object datum : data) {
            System.out.println(datum.toString());
        }
        inputStream.close();

    }
}
