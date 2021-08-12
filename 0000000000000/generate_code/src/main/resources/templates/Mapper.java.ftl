package ${packageName}.mapper;

import ${packageName}.model.${modelName};
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ${mapperName}{
    List<${modelName}> getAll${modelName}s();
}