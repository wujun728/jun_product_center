package cn.iocoder.yudao.adminserver.modules.infra.convert.redis;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.redis.core.RedisKeyDefine;
import cn.iocoder.yudao.adminserver.modules.infra.controller.redis.vo.InfRedisKeyRespVO;
import cn.iocoder.yudao.adminserver.modules.infra.controller.redis.vo.InfRedisMonitorRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Mapper
public interface RedisConvert {

    RedisConvert INSTANCE = Mappers.getMapper(RedisConvert.class);

    default InfRedisMonitorRespVO build(Properties info, Long dbSize, Properties commandStats) {
        InfRedisMonitorRespVO respVO = InfRedisMonitorRespVO.builder().info(info).dbSize(dbSize)
                .commandStats(new ArrayList<>(commandStats.size())).build();
        commandStats.forEach((key, value) -> {
            respVO.getCommandStats().add(InfRedisMonitorRespVO.CommandStat.builder()
                    .command(StrUtil.subAfter((String) key, "cmdstat_", false))
                    .calls(Integer.valueOf(StrUtil.subBetween((String) value, "calls=", ",")))
                    .usec(Integer.valueOf(StrUtil.subBetween((String) value, "usec=", ",")))
                    .build());
        });
        return respVO;
    }

    List<InfRedisKeyRespVO> convertList(List<RedisKeyDefine> list);

}
