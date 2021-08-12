package com.sanri.tools.modules.name.remote.dtos;

import lombok.Data;

import java.util.Set;

@Data
public class YoudaoTranslateResponse {
    private String errorCode;
    private Set<String> translation;
}
