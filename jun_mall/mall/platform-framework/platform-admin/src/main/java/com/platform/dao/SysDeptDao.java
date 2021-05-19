package com.platform.dao;

import com.platform.entity.SysDeptEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 部门管理
 *
 * @author liepngjun
 * @date 2017年11月18日 下午13:13:23
 */
@Mapper
public interface SysDeptDao extends BaseDao<SysDeptEntity> {

    /**
     * 查询子部门ID列表
     *
     * @param parentId 上级部门ID
     */
    List<String> queryDetpIdList(String parentId);

    String queryMaxIdByParentId(String parentId);
}
