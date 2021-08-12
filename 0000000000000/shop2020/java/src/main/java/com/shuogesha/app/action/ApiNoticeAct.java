package com.shuogesha.app.action;

import static com.shuogesha.platform.web.mongo.SimplePage.cpn;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shuogesha.app.version.AccessToken;
import com.shuogesha.cms.service.NoticeService;
import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;
import com.shuogesha.platform.web.mongo.Pagination;

@Controller
@RequestMapping("/app/")
public class ApiNoticeAct {
	private static Logger log = LoggerFactory.getLogger(ApiNoticeAct.class);
 
	@RequestMapping(value = "get_notice_list")
	@AccessToken
	public @ResponseBody Object get_notice_list(Integer pageNo, Integer pageSize,HttpServletRequest request, HttpServletResponse response) throws IOException {
		Pagination pagination = noticeService.getPage(null, cpn(pageNo), pageSize);
		return new JsonResult(ResultCode.SUCCESS, pagination);
	}

	@Autowired
	private NoticeService noticeService;

}
