package com.shuogesha.app.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shuogesha.app.version.AccessTokenRequired;
import com.shuogesha.app.web.util.ApiUtils;
import com.shuogesha.cms.entity.Feedback;
import com.shuogesha.cms.service.FeedbackService;
import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;
import com.shuogesha.platform.web.util.RequestUtils;

/**
 * 意见投诉
 *  
 */
@Controller
@RequestMapping("/app/")
public class ApiFeedbackAct {

	@Autowired
	private FeedbackService fs;

	/**
	 * 发布投诉 投诉类型、投诉内容不能为空，为空返回error，否则成功
	 */
	@RequestMapping(value = "add_feedback", method = RequestMethod.POST)
	@AccessTokenRequired
	@ResponseBody
	public Object add_feedback(Feedback feed, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (StringUtils.isBlank(feed.getName()) || StringUtils.isBlank(feed.getContent())) {
			return new JsonResult(ResultCode.FAIL, "请完善信息", null);
		} else {
			feed.setUserId(ApiUtils.getUnifiedUserId(request));
			feed.setDateline(RequestUtils.getNow());
			fs.save(feed);
			return new JsonResult(ResultCode.SUCCESS, null);
		}
 	}

}
