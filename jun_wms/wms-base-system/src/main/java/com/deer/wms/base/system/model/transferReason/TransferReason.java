package com.deer.wms.base.system.model.transferReason;

import java.util.Date;
import javax.persistence.*;

@Table(name = "transfer_reason")
public class TransferReason {
    /**
     * 转移子库原因id
     */
    @Id
    @Column(name = "transfer_reason_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transferReasonId;

    /**
     * 转移字库原因
     */
    @Column(name = "transfer_reason")
    private String transferReason;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private String createTime;

    /**
     * 创建人
     */
    @Column(name = "create_user_no")
    private String createUserNo;

    /**
     * 获取转移子库原因id
     *
     * @return transfer_reason_id - 转移子库原因id
     */
    public Integer getTransferReasonId() {
        return transferReasonId;
    }

    /**
     * 设置转移子库原因id
     *
     * @param transferReasonId 转移子库原因id
     */
    public void setTransferReasonId(Integer transferReasonId) {
        this.transferReasonId = transferReasonId;
    }

    /**
     * 获取转移字库原因
     *
     * @return transfer_reason - 转移字库原因
     */
    public String getTransferReason() {
        return transferReason;
    }

    /**
     * 设置转移字库原因
     *
     * @param transferReason 转移字库原因
     */
    public void setTransferReason(String transferReason) {
        this.transferReason = transferReason;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserNo() {
        return createUserNo;
    }

    public void setCreateUserNo(String createUserNo) {
        this.createUserNo = createUserNo;
    }

    public TransferReason() {
    }

    public TransferReason(String transferReason, String createTime, String createUserNo) {
        this.transferReason = transferReason;
        this.createTime = createTime;
        this.createUserNo = createUserNo;
    }
}