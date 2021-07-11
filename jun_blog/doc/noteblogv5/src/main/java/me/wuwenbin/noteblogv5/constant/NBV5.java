package me.wuwenbin.noteblogv5.constant;

/**
 * created by Wuwenbin on 2019-08-01 at 08:54
 *
 * @author wuwenbin
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
public interface NBV5 {

    String VERSION = "5.9.2";
    /**
     * 初始状态
     */
    String INIT_STATUS = "init_status";

    /**
     * 网站标题
     */
    String WEBSITE_TITLE ="website_title";

    /**
     * 上传文件夹参数key
     */
    String APP_UPLOAD_PATH = "app.upload.path";

    /**
     * 初始化管理员帐号
     */
    String ADMIN_INIT_STATUS = "admin_init_status";


    /**
     * 下面是邮件相关的参数
     */
    String MAIL_SMPT_SERVER_ADDR = "mail_smpt_server_addr";
    String MAIL_SMPT_SERVER_PORT = "mail_smpt_server_port";
    String MAIL_SERVER_ACCOUNT = "mail_server_account";
    String MAIL_SENDER_NAME = "mail_sender_name";
    String MAIL_SERVER_PASSWORD = "mail_server_password";

    /**
     * 发送验证码的key
     */
    String MAIL_CODE_KEY = "sendMailCodeKey";

    /**
     * 邮件通知
     */
    String COMMENT_MAIL_NOTICE_ONOFF = "comment_mail_notice_onoff";
    String MESSAGE_MAIL_NOTICE_ONOFF = "message_mail_notice_onoff";
    String USER_SIMPLE_REG_ONOFF = "user_simple_reg_onoff";

    /**
     * 信息板显示
     */
    String INFO_LABEL_NICKNAME = "info_label_nickname";
    String INFO_LABEL_LOGO = "info_label_logo";


    /**
     * session中的user参数key
     */
    String SESSION_USER_KEY = "nbv5_session_user_key";

    /**
     * 日志缓存的key
     */
    String LOG_CACHE_KEY = "log_cache_key";


    String QQ_APP_ID = "qq_app_id";
    String QQ_APP_KEY = "qq_app_key";
    String GITHUB_CLIENT_ID = "github_client_id";
    String GITHUB_CLIENT_SECRET = "github_client_secret";

    /**
     * github授权认证时候需要的state
     */
    String GITHUB_AUTH_STATE = "5047c2fe57f9b94a765e898cf54f63c7";

    String MANAGEMENT_INDEX = "/management/index";
    String FRONTEND_INDEX = "/index";

    /**
     * 上传类型
     */
    String UPLOAD_TYPE = "upload_type";
    /**
     * 七牛相关key值
     */
    String QINIU_ACCESS_KEY = "qiniu_accessKey";
    String QINIU_SECRET_KEY = "qiniu_secretKey";
    String QINIU_BUCKET = "qiniu_bucket";
    String QINIU_DOMAIN = "qiniu_domain";

    /**
     * 二维码，赞赏码
     */
    String QRCODE_ALIPAY = "qrcode_alipay";
    String QRCODE_WECHAT = "qrcode_wechat";

    /**
     * 其他的一些参数
     */
    String STATISTICS_ONOFF = "statistics_onoff";
    String IS_OPEN_OSS_UPLOAD = "is_open_oss_upload";


}
