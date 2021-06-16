import com.alibaba.fastjson.JSON;
import com.lu.dynamic.DynamicApplication;
import com.lu.dynamic.modular.business.service.IArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program LuGenerate
 * @description: 测试类
 * @author: zhanglu
 * @create: 2019-12-11 14:27:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DynamicApplication.class)
public class MyTest {

    @Autowired
    private IArticleService articleService;

    @Test
    public void test01(){
        Object o1 = articleService.get01();
        Object o2 = articleService.get02();
        System.out.println(JSON.toJSONString(o1));
        System.out.println(JSON.toJSONString(o2));
    }

}
