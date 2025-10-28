package io.github.wujun728.admin.page.service;

import io.github.wujun728.admin.common.Result;
import io.github.wujun728.admin.page.data.Form;

import java.util.Map;

public interface FormEvent {
    default Result beforeSave(Map<String,Object> obj, String tableName, Form form){return null;}
    default Result afterSave(Map<String,Object> obj, String tableName, Form form){return null;}
    default void init(Map<String,Object> obj,Form form){};
}
