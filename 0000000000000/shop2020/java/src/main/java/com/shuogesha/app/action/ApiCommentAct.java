package com.shuogesha.app.action;

import static com.shuogesha.platform.web.mongo.SimplePage.cpn;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shuogesha.app.version.AccessTokenRequired;
import com.shuogesha.app.web.util.ApiUtils;
import com.shuogesha.cms.entity.Comment;
import com.shuogesha.cms.service.CommentService;
import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;
import com.shuogesha.platform.entity.Site;
import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.platform.web.util.RequestUtils;

@Controller
@RequestMapping("/app/")
public class ApiCommentAct{
	private static Logger log = LoggerFactory.getLogger(ApiCommentAct.class);
	/**
	 * 获取评论列表
	 * @param refer
	 * @param referid
	 * @param pageNo
	 * @param pageSize
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "get_comment_list") 
	public @ResponseBody Object get_comment_list(String refer,Long referid,Integer pageNo,Integer pageSize,HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {
  		Pagination pagination = commentService.getPage(null,refer,referid,cpn(pageNo),cpn(pageSize));
// 		List<Comment> list = pagination.getDatas();
// 		for (Comment comment : list) {
// 			List<Comment> child=commentService.findList(refer,referid,comment.getId());
// 			comment.setChild(child);
// 			if(comment.getUnifiedUser()!=null){
//				UnifiedUser unifiedUser2=comment.getUnifiedUser(); 
//				unifiedUser2.setPassword("");
//				comment.setUnifiedUser(unifiedUser2);
//			}  
//		}
// 		pagination.setDatas(list); 
		return new JsonResult(ResultCode.SUCCESS, pagination);
	}
	
	@RequestMapping(value = "remove_comment") 
	public @ResponseBody Object remove_comment(Long id, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {
		Map<String, Object> map = new HashMap<>(); 
		commentService.removeById(id); 
 		return new JsonResult(ResultCode.SUCCESS, null);
	}
	 
	
	@RequestMapping(value = "save_comment", method = RequestMethod.POST) 
	@AccessTokenRequired
	public @ResponseBody Object save_comment(Comment bean,HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception { 
		Site site = ApiUtils.getSite(request);
//		if(site==null||!"open".equals(site.getComment())){
//			map.put("error", "评论功能暂未开放！");
// 			return map;
//		} 
 		if(StringUtils.isBlank(bean.getName())){
 			return new JsonResult(ResultCode.FAIL, "参数不能为空");
 		} 
 		bean.setDateline(RequestUtils.getNow()); 
 		bean.setUnifiedUser(ApiUtils.getUnifiedUser(request));
 		commentService.save(bean);  
 		return new JsonResult(ResultCode.SUCCESS, null);
	}
	
	
	@Autowired
	public CommentService commentService;  
	 
}
