package com.zhiyu.flybbs.mapper;

import com.zhiyu.flybbs.domain.User;
import org.springframework.stereotype.Repository;

/**
 * @author Wujun
 */
@Repository
public interface UserWriteMapper {
    int insertUser(User user);

    int updateUser(User user);
}
