package cn.iocoder.yudao.adminserver.modules.tool.dal.mysql.codegen;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.adminserver.modules.tool.dal.dataobject.codegen.ToolCodegenColumnDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ToolCodegenColumnMapper extends BaseMapperX<ToolCodegenColumnDO> {

    default List<ToolCodegenColumnDO> selectListByTableId(Long tableId) {
        return selectList(new QueryWrapper<ToolCodegenColumnDO>().eq("table_id", tableId)
                .orderByAsc("ordinal_position"));
    }

    default void deleteListByTableId(Long tableId) {
        delete(new QueryWrapper<ToolCodegenColumnDO>().eq("table_id", tableId));
    }

}
