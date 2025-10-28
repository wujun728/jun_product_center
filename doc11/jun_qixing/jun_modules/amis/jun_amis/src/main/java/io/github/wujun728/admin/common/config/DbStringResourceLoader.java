//package io.github.wujun728.admin.common.config;
//
//import io.github.wujun728.admin.common.service.DbCacheService;
//import io.github.wujun728.admin.util.SpringUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.collections.ExtendedProperties;
//import org.apache.velocity.exception.ResourceNotFoundException;
//import org.apache.velocity.runtime.resource.Resource;
//import org.apache.velocity.runtime.resource.loader.ResourceLoader;
//
//import java.io.ByteArrayInputStream;
//import java.io.InputStream;
//import java.nio.charset.StandardCharsets;
//import java.util.Map;
//
//@Slf4j
//public class DbStringResourceLoader extends ResourceLoader {
//    @Override
//    public void init(ExtendedProperties configuration) {
//
//    }
//
//    @Override
//    public InputStream getResourceStream(String source) throws ResourceNotFoundException {
//        log.info("加载模板:{}",source);
//        String content = null;
//        DbCacheService dbCacheService = SpringUtil.getBean(DbCacheService.class);
//        if(dbCacheService != null){
//            Map<String, Object> template = dbCacheService.getData("template", source);
//            if(template != null){
//                content = (String) template.get("content");
//            }
//        }
//        if(content == null){
//            content = "";
//        }
//        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
//        return byteArrayInputStream;
//    }
//
//    @Override
//    public boolean isSourceModified(Resource resource) {
//        return false;
//    }
//
//    @Override
//    public long getLastModified(Resource resource) {
//        return 0;
//    }
//
//
//}
