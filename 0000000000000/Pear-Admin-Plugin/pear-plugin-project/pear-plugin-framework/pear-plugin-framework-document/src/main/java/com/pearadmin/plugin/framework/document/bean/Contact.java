package com.pearadmin.plugin.framework.document.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Contact {

    /** 名称 */
    private String name;

    /** 网址 */
    private String url;

    /** 邮箱 */
    private String email;
}
