package cn.iocoder.yudao.adminserver.modules.tool.dal.mysql.codegen;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.QueryWrapperX;
import cn.iocoder.yudao.adminserver.modules.tool.controller.codegen.vo.table.ToolCodegenTablePageReqVO;
import cn.iocoder.yudao.adminserver.modules.tool.dal.dataobject.codegen.ToolCodegenTableDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ToolCodegenTableMapper extends BaseMapperX<ToolCodegenTableDO> {

    default ToolCodegenTableDO selectByTableName(String tableName) {
        return selectOne(new QueryWrapper<ToolCodegenTableDO>().eq("table_name", tableName));
    }

    default PageResult<ToolCodegenTableDO> selectPage(ToolCodegenTablePageReqVO pageReqVO) {
        return selectPage(pageReqVO, new QueryWrapperX<ToolCodegenTableDO>()
                .likeIfPresent("table_name", pageReqVO.getTableName())
                .likeIfPresent("table_comment", pageReqVO.getTableComment())
                .betweenIfPresent("create_time", pageReqVO.getBeginCreateTime(), pageReqVO.getEndCreateTime()));
    }

}
