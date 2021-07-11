package cn.iocoder.mall.systemservice.dal.mysql.mapper.oauth;

import cn.iocoder.mall.systemservice.dal.mysql.dataobject.oauth.OAuth2AccessTokenDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OAuth2AccessTokenMapper extends BaseMapper<OAuth2AccessTokenDO> {

    default OAuth2AccessTokenDO selectByUserIdAndUserType(Integer userId, Integer userType) {
        return selectOne(new QueryWrapper<OAuth2AccessTokenDO>()
                .eq("user_id", userId).eq("user_type", userType));
    }

    default int deleteByUserIdAndUserType(Integer userId, Integer userType) {
        return delete(new QueryWrapper<OAuth2AccessTokenDO>()
                .eq("user_id", userId).eq("user_type", userType));
    }

    default int deleteByRefreshToken(String refreshToken) {
        return delete(new QueryWrapper<OAuth2AccessTokenDO>().eq("refresh_token", refreshToken));
    }

    default List<OAuth2AccessTokenDO> selectListByRefreshToken(String refreshToken) {
        return selectList(new QueryWrapper<OAuth2AccessTokenDO>().eq("refresh_token", refreshToken));
    }

}
