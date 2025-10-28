package io.github.wujun728.admin.page.service;

import io.github.wujun728.admin.page.data.InputField;

import java.util.Map;

public interface InputFieldService {
    Map<String,Object> buildInputField(InputField inputField,boolean selector);
}
