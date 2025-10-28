package io.github.wujun728.admin;

import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.runtime.resource.loader.ResourceLoader;
import org.apache.velocity.util.ExtProperties;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;

@Slf4j
public class MyStringResourceLoader extends ResourceLoader {
    @Override
    public void init(ExtProperties configuration) {

    }

    @Override
    public InputStream getResourceStream(String source) throws ResourceNotFoundException {
        log.info("source:{}",source);

        String t = "";
        if(source.equals("test.vm")){
            t = "hello #parse('/abc.vm')";
        }else if(source.equals("/abc.vm")){
            t = "abc 123";
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(t.getBytes());
        return byteArrayInputStream;
    }

    @Override
    public boolean isSourceModified(Resource resource) {
        return false;
    }

    @Override
    public long getLastModified(Resource resource) {
        return 0;
    }
    
    // Spring Boot 2.5.15升级后Velocity库需要的新方法
    @Override
    public Reader getResourceReader(String source, String encoding) throws ResourceNotFoundException {
        log.info("source:{}, encoding:{}", source, encoding);
        
        String t = "";
        if(source.equals("test.vm")){
            t = "hello #parse('/abc.vm')";
        }else if(source.equals("/abc.vm")){
            t = "abc 123";
        }
        return new StringReader(t);
    }
}
