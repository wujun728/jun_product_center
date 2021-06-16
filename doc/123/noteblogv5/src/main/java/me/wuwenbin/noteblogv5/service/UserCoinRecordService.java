package me.wuwenbin.noteblogv5.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.wuwenbin.noteblogv5.model.entity.User;
import me.wuwenbin.noteblogv5.model.entity.UserCoinRecord;

/**
 * @author wuwen
 */
public interface UserCoinRecordService extends IService<UserCoinRecord> {

    /**
     * 计算管理员插入的硬币，并插入一条记录
     *
     * @param targetCoinValue
     * @param userId
     */
    void calcAdminInsertRecord(int targetCoinValue, long userId);

    /**
     * 用户今日是否已签到，返回签到的记录数
     *
     * @param userId
     * @return
     */
    int todayIsSigned(long userId);

    /**
     * 用户签到
     *
     * @param userId
     * @return
     */
    boolean userSign(long userId);

    /**
     * 用户卡密充值硬币
     *
     * @param user
     * @param cashNoWithLine
     * @return
     */
    boolean userCashRecharge(User user, String cashNoWithLine);
}
