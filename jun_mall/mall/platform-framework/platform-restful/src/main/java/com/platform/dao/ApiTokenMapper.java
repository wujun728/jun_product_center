package com.platform.dao;

import com.platform.entity.TokenEntity;
import org.apache.ibatis.annotations.Param;

/**
 * 用户Token
 *
 * @author lipengjun
 * @date 2017年11月20日 下午3:29:40
 */
public interface ApiTokenMapper extends BaseDao<TokenEntity> {

    TokenEntity queryByUserId(@Param("userId") Long userId);

    TokenEntity queryByToken(@Param("token") String token);

}
