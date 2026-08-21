package com.ruoyi.seal.utils.pdf.param;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 公司合同pdf表单域字段类
 * @Author wocurr.com
 */
@Data
public class CompanyPdfAcroFields implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 甲方 */
    private String partA;

    /** 甲方联系人 */
    private String partAContacter;

    /** 甲方联系电话 */
    private String partAContactPhone;

    /** 甲方公司地址 */
    private String partAAddress;

    /** 甲方社会信用代码 */
    private String partACreditCode;

    /** 甲方联系邮箱 */
    private String partAEmail;

    /** 乙方 */
    private String partB;

    /** 乙方联系人 */
    private String partBContacter;

    /** 乙方联系电话 */
    private String partBContactPhone;

    /** 乙方公司地址 */
    private String partBAddress;

    /** 乙方社会信用代码 */
    private String partBCreditCode;

    /** 乙方联系邮箱 */
    private String partBEmail;

    /** 合同金额 */
    private BigDecimal amount;

    /** 收款人（户名） */
    private String payeeName;

    /** 收款账号 */
    private String payeeAccount;

    /** 收款开户行 */
    private String payeeBank;

    /** 合同签订-年 */
    private String year;

    /** 合同签订-月 */
    private String month;

    /** 合同签订-天 */
    private String day;
}
