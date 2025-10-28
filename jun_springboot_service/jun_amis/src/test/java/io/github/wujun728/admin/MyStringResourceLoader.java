package io.github.wujun728.admin;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.ExtendedProperties;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.runtime.resource.loader.ResourceLoader;
import org.apache.velocity.util.ExtProperties;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

@Slf4j
public class MyStringResourceLoader extends ResourceLoader {
    /*@Override
    public void init(ExtendedProperties configuration) {

    }*/

    /*@Override
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
    }*/

    @Override
    public long getLastModified(Resource arg0) {
        return 0;
    }

    @Override
    public void init(ExtProperties configuration) {

    }

    /**
     * 动态加载vm文件内容
     * @param source
     * @param encoding
     */
    @Override
    public Reader getResourceReader(String source, String encoding) throws ResourceNotFoundException {
//        String str = "-- 菜单 SQL\n" +
//                "insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)\n" +
//                "values('${functionName}', '${parentMenuId}', '1', '${businessName}', '${moduleName}/${businessName}/index', 1, 0, 'C', '0', '0', '${permissionPrefix}:list', '#', 'admin', sysdate(), '', null, '${functionName}菜单');\n" +
//                "\n" +
//                "-- 按钮父菜单ID\n" +
//                "SELECT @parentId := LAST_INSERT_ID();\n" +
//                "\n" +
//                "-- 按钮 SQL\n" +
//                "insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)\n" +
//                "values('${functionName}查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', '${permissionPrefix}:query',        '#', 'admin', sysdate(), '', null, '');\n" +
//                "\n" +
//                "insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)\n" +
//                "values('${functionName}新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', '${permissionPrefix}:add',          '#', 'admin', sysdate(), '', null, '');\n" +
//                "\n" +
//                "insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)\n" +
//                "values('${functionName}修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', '${permissionPrefix}:edit',         '#', 'admin', sysdate(), '', null, '');\n" +
//                "\n" +
//                "insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)\n" +
//                "values('${functionName}删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', '${permissionPrefix}:remove',       '#', 'admin', sysdate(), '', null, '');\n" +
//                "\n" +
//                "insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)\n" +
//                "values('${functionName}导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', '${permissionPrefix}:export',       '#', 'admin', sysdate(), '', null, '');";
//        InputStream inputStream = new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8));

        log.info("source:{}",source);

        String t = "";
        if(source.equals("test.vm")){
            t = "hello #parse('/abc.vm')";
        }else if(source.equals("/abc.vm")){
            t = "abc 123";
        }
        ByteArrayInputStream inputStream = new ByteArrayInputStream(t.getBytes());

        Reader reader = new InputStreamReader(inputStream);
//        inputStream = new ByteArrayInputStream(reader.toString().getBytes(StandardCharsets.UTF_8));
        return reader;
    }

    @Override
    public boolean isSourceModified(Resource arg0) {
        return false;
    }
}
