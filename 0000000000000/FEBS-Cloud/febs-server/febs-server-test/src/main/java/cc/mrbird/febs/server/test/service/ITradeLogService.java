package cc.mrbird.febs.server.test.service;

import cc.mrbird.febs.common.core.entity.system.TradeLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author MrBird
 */
public interface ITradeLogService extends IService<TradeLog> {

    /**
     * 下单并支付
     *
     * @param tradeLog 交易日志
     */
    void orderAndPay(TradeLog tradeLog);
}