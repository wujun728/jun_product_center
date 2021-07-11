package me.wuwenbin.noteblogv5.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.wuwenbin.noteblogv5.annotation.MybatisMapper;
import me.wuwenbin.noteblogv5.model.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * created by Wuwenbin on 2019-08-05 at 13:37
 *
 * @author wuwenbin
 */
@MybatisMapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 今日新增用户
     *
     * @return
     */
    long findTodayUser();

    /**
     * 统计普通注册用户role
     *
     * @param email
     * @param username
     * @return
     */
    int countRegUserEmailAndUsername(@Param("email") String email, @Param("username") String username);

    /**
     * 更新余额硬币
     *
     * @param userId
     * @param remainCoin
     */
    void updateRemainCoin(@Param("userId") long userId, @Param("remainCoin") int remainCoin);

}
