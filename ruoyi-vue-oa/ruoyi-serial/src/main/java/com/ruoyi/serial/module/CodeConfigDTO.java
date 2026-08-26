package com.ruoyi.serial.module;

import com.ruoyi.serial.domain.CodeConfig;
import com.ruoyi.serial.domain.CodeConfigRule;
import lombok.Data;

import java.util.List;

/**
 * @Author wocurr.com
 */
@Data
public class CodeConfigDTO extends CodeConfig {

    /**
     * 子規則
     */
    private List<CodeConfigRule> rules;

}
