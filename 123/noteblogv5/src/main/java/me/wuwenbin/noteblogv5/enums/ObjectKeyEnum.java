package me.wuwenbin.noteblogv5.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * @author wuwen
 */
public enum ObjectKeyEnum implements IEnum<String> {

    /**
     * 上传功能object标识
     */
    MANAGEMENT_ARTICLE_ADD_EDITORMD(1, "添加文章 editorMD 编辑器上传"),
    MANAGEMENT_ARTICLE_ADD_NKINDEDITOR(2, "添加文章 NKindEditor 编辑器上传"),
    MANAGEMENT_ARTICLE_EDIT_EDITORMD(3, "编辑文章 editorMD 编辑器上传"),
    MANAGEMENT_ARTICLE_EDIT_NKINDEDITOR(4, "编辑文章 NKindEditor 编辑器上传"),
    MANAGEMENT_ARTICLE_ADD_COVER(5, "添加文章封面上传"),
    MANAGEMENT_ARTICLE_EDIT_COVER(6, "编辑文章封面上传"),
    MANAGEMENT_NOTE_ADD_EDITORMD(7, "添加笔记 editorMD 编辑器上传"),
    MANAGEMENT_NOTE_EDIT_EDITORMD(8, "编辑笔记 editorMD 编辑器上传"),
    MANAGEMENT_PROFILE_AVATAR(9, "管理员个人设置头像上传"),
    MANAGEMENT_PROFILE_ALIPAY_QRCODE(10, "管理员个人设置支付宝二维码上传"),
    MANAGEMENT_PROFILE_WECHAT_QRCODE(11, "管理员个人设置微信二维码上传"),
    MANAGEMENT_WEBSITE_BOTTOM_LOGO(12, "管理员网站设置底部logo图片上传"),
    FRONTEND_UBS_AVATAR(13, "网站用户修改头像上传"),
    MANAGEMENT_WELCOME_CARD_LOGO(14, "网站欢迎页的卡片logo图片"),
    OTHER(15, "其他");


    private String desc;
    private int code;

    ObjectKeyEnum(int code, String desc) {
        this.desc = desc;
        this.code = code;
    }

    public static ObjectKeyEnum getObjectKeyEnumByCode(int code) {
        switch (code) {
            case 1:
                return MANAGEMENT_ARTICLE_ADD_EDITORMD;
            case 2:
                return MANAGEMENT_ARTICLE_ADD_NKINDEDITOR;
            case 3:
                return MANAGEMENT_ARTICLE_EDIT_EDITORMD;
            case 4:
                return MANAGEMENT_ARTICLE_EDIT_NKINDEDITOR;
            case 5:
                return MANAGEMENT_ARTICLE_ADD_COVER;
            case 6:
                return MANAGEMENT_ARTICLE_EDIT_COVER;
            case 7:
                return MANAGEMENT_NOTE_ADD_EDITORMD;
            case 8:
                return MANAGEMENT_NOTE_EDIT_EDITORMD;
            case 9:
                return MANAGEMENT_PROFILE_AVATAR;
            case 10:
                return MANAGEMENT_PROFILE_ALIPAY_QRCODE;
            case 11:
                return MANAGEMENT_PROFILE_WECHAT_QRCODE;
            case 12:
                return MANAGEMENT_WEBSITE_BOTTOM_LOGO;
            case 13:
                return FRONTEND_UBS_AVATAR;
            default:
                return OTHER;
        }
    }

    /**
     * 枚举数据库存储值
     */
    @Override
    public String getValue() {
        return this.name().toLowerCase();
    }

    public int getCode() {
        return code;
    }


    public String getDesc() {
        return desc;
    }
}
