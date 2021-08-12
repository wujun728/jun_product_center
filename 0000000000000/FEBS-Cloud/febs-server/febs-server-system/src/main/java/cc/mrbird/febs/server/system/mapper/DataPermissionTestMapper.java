package cc.mrbird.febs.server.system.mapper;

import cc.mrbird.febs.common.core.entity.system.DataPermissionTest;
import cc.mrbird.febs.common.datasource.starter.annotation.DataPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author MrBird
 */
@DataPermission(methods = {"selectPage"})
public interface DataPermissionTestMapper extends BaseMapper<DataPermissionTest> {

}
