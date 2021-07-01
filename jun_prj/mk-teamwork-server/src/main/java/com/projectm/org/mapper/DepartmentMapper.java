package com.projectm.org.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.projectm.org.domain.Department;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Map;

@SuppressWarnings("rawtypes")
@Repository
@Mapper
public interface DepartmentMapper  extends BaseMapper<Department> {

    @Select("SELECT * FROM team_department WHERE organization_code = #{orgCode} AND pcode = #{pCode} ORDER BY id")
    IPage<Map> selectDepartmentByOrgCodeAndPCode(IPage<Map> page,@Param("orgCode") String orgCode,@Param("pCode") String pCode);

    @Select("SELECT * FROM team_department WHERE code=#{depCode}")
    Map selectDepartmentByCode(@Param("depCode") String depCode);

    @Delete("DELETE FROM team_department WHERE code = #{depCode}")
    Integer deleteDepartmentByCode(@Param("depCode") String depCode);

}
