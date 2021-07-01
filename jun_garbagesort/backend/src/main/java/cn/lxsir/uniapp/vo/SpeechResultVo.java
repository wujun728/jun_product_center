package cn.lxsir.uniapp.vo;

import lombok.Data;

/**
 * @创建人 luoxiang
 * @创建时间 2019/7/8  13:27
 * @描述
 */
@Data
public class SpeechResultVo {
    private String corpus_no;
    private String err_msg;
    private String err_no;
    private String[] result;
    private String sn;
}
