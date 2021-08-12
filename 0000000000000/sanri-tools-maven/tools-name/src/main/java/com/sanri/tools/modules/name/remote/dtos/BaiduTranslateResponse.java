package com.sanri.tools.modules.name.remote.dtos;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class BaiduTranslateResponse {
    private String error_code;
    private String error_msg;

    private List<TranslateResult> trans_result;

    @Data
    public static class TranslateResult{
        private String src;
        private String dst;
    }
}
