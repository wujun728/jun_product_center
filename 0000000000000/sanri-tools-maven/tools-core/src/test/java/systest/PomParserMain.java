package systest;

import com.sanri.tools.modules.core.utils.MybatisXNode;
import com.sanri.tools.modules.core.utils.MybatisXPathParser;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PomParserMain {

    @Test
    public void fileAccessTimeRead() throws IOException {
        File file = new File("D:\\tmp\\classloader\\job\\com\\itstyle\\quartz\\job/ChickenJob.class");
        BasicFileAttributes basicFileAttributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
        FileTime fileTime = basicFileAttributes.lastAccessTime();
        System.out.println(new Date(fileTime.to(TimeUnit.MILLISECONDS)));
    }

    @Test
    public void testJmx() throws IOException {
        JMXServiceURL jmxServiceURL = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://10.101.72.42:59949/jmxrmi");
        JMXConnector connect = JMXConnectorFactory.connect(jmxServiceURL, null);

        MBeanServerConnection mBeanServerConnection = connect.getMBeanServerConnection();
        String[] domains = mBeanServerConnection.getDomains();
        for (String domain : domains) {
            System.out.println(domain);
        }

        connect.close();
    }

    @Test
    public void test222(){
        boolean su = true;
        for (int i = 0; i < 10; i+=1) {
            boolean current = i % 2 == 0;
            su &= current;
        }
        System.out.println(su);
    }

    @Test
    public void testPom() throws IOException, URISyntaxException {
        File baseDir = new File("d:/test/repository");baseDir.mkdir();
        String repository = "https://mirrors.huaweicloud.com/repository/maven/";

        ClassPathResource classPathResource = new ClassPathResource("classloaderdefault.pom");
        MybatisXPathParser mybatisXPathParser = new MybatisXPathParser(classPathResource.getInputStream());
        MybatisXNode mybatisXNode = mybatisXPathParser.evalNode("/project/dependencies");
        List<MybatisXNode> children = mybatisXNode.getChildren();
        if (CollectionUtils.isNotEmpty(children)) {
            for (MybatisXNode child : children) {
                String groupId = child.evalString("groupId");
                String artifactId = child.evalString("artifactId");
                String version = child.evalString("version");

                groupId = StringUtils.replace(groupId,".","/");

                Path resolve = Paths.get(baseDir.toURI()).resolve(Paths.get(groupId, artifactId, version));
                File targetDir = resolve.toFile();
                targetDir.mkdirs();

                String fileName = artifactId+"-"+version+".jar";
                URI resource = new URL(repository).toURI().resolve(new URI(groupId + "/" + artifactId + "/" + version+"/"+fileName));
                UrlResource urlResource = new UrlResource(resource);
                InputStream inputStream = urlResource.getInputStream();
                FileOutputStream fileOutputStream = new FileOutputStream(new File(targetDir, fileName));
                IOUtils.copy(inputStream,fileOutputStream);
                inputStream.close();
                fileOutputStream.close();
            }
        }
    }
}
