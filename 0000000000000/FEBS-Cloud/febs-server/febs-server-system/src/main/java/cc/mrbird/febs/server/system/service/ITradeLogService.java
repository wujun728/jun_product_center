package cc.mrbird.febs.server.system.service;

import cc.mrbird.febs.common.core.entity.system.TradeLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author MrBird
 */
public interface ITradeLogService extends IService<TradeLog> {

    /**
     * 打包并派送
     *
     * @param tradeLog 交易日志
     */
    void packageAndSend(TradeLog tradeLog);
}
