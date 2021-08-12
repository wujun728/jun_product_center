package systest;

import com.sanri.tools.modules.core.utils.MybatisXNode;
import com.sanri.tools.modules.core.utils.MybatisXPathParser;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

public class XPathParserMain {

    @Test
    public void testXmlParser() throws IOException {
//        ClassPathResource classPathResource = new ClassPathResource("soap/soap11.xml");
        ClassPathResource classPathResource = new ClassPathResource("soap11.xml");
        String s = IOUtils.toString(classPathResource.getInputStream());
        System.out.println(s);
        MybatisXPathParser mybatisXPathParser = new MybatisXPathParser(classPathResource.getInputStream());
        MybatisXNode mybatisXNode = mybatisXPathParser.evalNode("/soap:Envelope/soap:Body");
        System.out.println(mybatisXNode);
    }
}
