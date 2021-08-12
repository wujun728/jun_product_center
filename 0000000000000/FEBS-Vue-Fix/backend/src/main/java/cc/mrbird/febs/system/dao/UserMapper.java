package cc.mrbird.febs.system.dao;

import cc.mrbird.febs.system.domain.DeptUsers;
import cc.mrbird.febs.system.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper extends BaseMapper<User> {

    IPage<User> findUserDetail(Page page, @Param("user") User user);

    /**
     * 获取单个用户详情
     *
     * @param username 用户名
     * @return 用户信息
     */
    User findDetail(String username);
    String findSubordinates(@Param("deptId")Long deptId);

    List<DeptUsers> findSubordinatesMap();
}