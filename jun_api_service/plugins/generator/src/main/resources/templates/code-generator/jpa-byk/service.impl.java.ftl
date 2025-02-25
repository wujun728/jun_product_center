<#if isWithPackage?exists && isWithPackage==true>package ${packageName}.service.impl;</#if>
<#if isAutoImport?exists && isAutoImport==true>
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ${packageName}.entity.${classInfo.className}Entity;
import ${packageName}.service.${classInfo.className}Service;
import java.util.List;
</#if>
/**
 * @description ${classInfo.classComment}服务层实现
 * @author ${authorName}
 * @date ${.now?string('yyyy-MM-dd')}
 */
@Service
public class ${classInfo.className}ServiceImpl  implements ${classInfo.className}Service {


    @Override
    public boolean save(${classInfo.className}Entity vo) throws Exception {
        return false;
    }
    
    @Override
    public boolean update(${classInfo.className}Entity vo) throws Exception {
        return false;
    }
    
    @Override
    public boolean delete(${classInfo.className}Entity vo) throws Exception {
        return false;
    }
    
    @Override
    public boolean deleteById(Long id) throws Exception {
        return false;
    }
    
    @Override
    public ${classInfo.className}Entity findById(Long id) throws Exception {
        return null;
    }
    
    @Override
    public List<${classInfo.className}Entity> list(${classInfo.className}Entity vo) throws Exception {
        return null;
    }

    @Override
    public List<${classInfo.className}Entity> page(${classInfo.className}Entity vo, int page, int size) throws Exception {
        return null;
    }

    @Override
    public int count(${classInfo.className}Entity vo) throws Exception {
        return 0;
    }

}



