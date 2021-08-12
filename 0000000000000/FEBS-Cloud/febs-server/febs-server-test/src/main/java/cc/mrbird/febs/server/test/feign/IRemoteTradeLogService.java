package cc.mrbird.febs.server.test.feign;

import cc.mrbird.febs.common.core.entity.constant.FebsServerConstant;
import cc.mrbird.febs.common.core.entity.system.TradeLog;
import cc.mrbird.febs.server.test.feign.fallback.RemoteTradeLogServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author MrBird
 */
@FeignClient(value = FebsServerConstant.FEBS_SERVER_SYSTEM, contextId = "tradeLogServiceClient", fallbackFactory = RemoteTradeLogServiceFallback.class)
public interface IRemoteTradeLogService {

    /**
     * 打包派送
     *
     * @param tradeLog 交易日志
     */
    @PostMapping("package/send")
    void packageAndSend(@RequestBody TradeLog tradeLog);
}
