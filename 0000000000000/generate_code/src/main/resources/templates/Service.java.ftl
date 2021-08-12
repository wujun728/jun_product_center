package ${packageName}.service;

import ${packageName}.model.${modelName};
import ${packageName}.mapper.${mapperName};
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class ${serviceName}{

    @Autowired
    ${mapperName} ${mapperName?uncap_first};
    public List<${modelName}> getAll${modelName}s(){
        return ${mapperName?uncap_first}.getAll${modelName}s();
    }
}