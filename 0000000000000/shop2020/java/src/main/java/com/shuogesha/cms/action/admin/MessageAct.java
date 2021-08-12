package com.shuogesha.cms.action.admin;

import static com.shuogesha.platform.web.mongo.SimplePage.cpn;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.shuogesha.async.task.AsyncLogTaskService;
import com.shuogesha.cms.entity.Message;
import com.shuogesha.cms.service.MessageService;
import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;
import com.shuogesha.common.util.UtilDate;
import com.shuogesha.mq.log.SystemLogSender;
import com.shuogesha.mq.message.MessageSender;
import com.shuogesha.platform.entity.SystemLog;
import com.shuogesha.platform.web.CmsUtils;
import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.platform.web.util.RequestUtils;

@RestController 
@RequestMapping("/api/message/")
public class MessageAct {
	@RequestMapping(value = "/list")
	public @ResponseBody Object v_list(String name, Integer pageNo, Integer pageSize, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Pagination pagination = messageService.getPage(name,null, cpn(pageNo), pageSize);
		return new JsonResult(ResultCode.SUCCESS, pagination);
	}

	@RequestMapping(value = "/get")
	public @ResponseBody Object v_get(Long id) {
		Message bean = messageService.findById(id);
		return new JsonResult(ResultCode.SUCCESS, bean);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Object o_save(@RequestBody Message bean) {
		bean.setDateline(UtilDate.getNow());
		JSONObject data = new JSONObject();
//		data.put("name", bean.getName());
//		data.put("title", title);
 		String content = bean.getContent();
		Message message= new Message(Message.TYPE_ALL, "USER", content,data.toString(),RequestUtils.getNow(), bean.getUserId());
  		messageService.saveSend(message,false);//默认不发送
// 		messageService.save(bean);
		return new JsonResult(ResultCode.SUCCESS, true);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody Object o_update(@RequestBody Message bean) {
		messageService.update(bean);
		return new JsonResult(ResultCode.SUCCESS, true);
	}

	@RequestMapping(value = "/delete")
	public @ResponseBody Object o_delete(Long[] ids) {
		messageService.removeByIds(ids);
		return new JsonResult(ResultCode.SUCCESS, true);
	} 
	
	@RequestMapping(value = "/save_talk", method = RequestMethod.POST)
	public @ResponseBody Object save_talk(@RequestBody Message bean,HttpServletRequest request) {
		bean.setDateline(UtilDate.getNow()); 
		bean.setFromUid(CmsUtils.getUserId(request));
    	messageService.save(bean);
    	Message message=messageService.findById(bean.getId());
    	messageSender.sendLog(message); 
		return new JsonResult(ResultCode.SUCCESS,message);
	}

	
	@RequestMapping(value = "/talk")
	public @ResponseBody Object  talk(Long userId, Integer pageNo, HttpServletRequest request,
			HttpServletResponse response)
			throws UnsupportedEncodingException { 
		Pagination pagination = messageService.getPage(Message.TYPE_MESSAGE,userId,cpn(pageNo),
				20);
		return new JsonResult(ResultCode.SUCCESS, pagination);
	}
	 
	@Autowired
	public MessageService messageService; 
	@Autowired
	public MessageSender messageSender;
	
	@Autowired
    private AsyncLogTaskService asyncLogTaskService;
    @Autowired
    private SystemLogSender systemLogSender;
 
    
	
	
	
}
