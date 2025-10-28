package io.github.wujun728.admin.page.inputRender;

import io.github.wujun728.admin.page.data.InputField;

import java.util.Map;

public class LocationPickerRender extends InputDefaultRender {
    @Override
    protected void extra(Map<String, Object> config, InputField field) {
        //百度地图ak
        config.put("ak","51SEhCogrVzdWmAdolU0FCbEjqKgWRv4");
    }
}
