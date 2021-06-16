import com.alibaba.fastjson.JSON;
import com.lu.dynamicplus.DynamicPlusApplication;
import com.lu.dynamicplus.modular.business.model.Article;
import com.lu.dynamicplus.modular.business.service.IArticleService;
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
@SpringBootTest(classes = DynamicPlusApplication.class)
public class MyTest {

    @Autowired
    private IArticleService articleService;

    @Test
    public void test01(){
        Article article = new Article();
        article.setHeaderPid(1l);
        article.setTitle("title");
        article.setAuthor("showlu");
        articleService.getBaseMapper().insert(article);
        articleService.get01();
    }

}
