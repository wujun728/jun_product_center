package io.github.wujun728.admin.page.inputRender;
import io.github.wujun728.admin.page.data.InputField;

import java.util.Map;

public interface InputRender{
    Map<String,Object> render(InputField inputField);
}