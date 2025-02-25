package com.jun.plugin.sms.utils;

import lombok.Data;

@Data
public class Sms {
	private String sdkAppId;
	private String signName;
	private String sessionContext;
	private String templateId;
	private String[] phoneNumberSet;
	private String[] templateParamSet;

}
