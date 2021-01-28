package sy.action.base;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import sy.action.BaseAction;
import sy.model.base.Syonline;
import sy.service.base.SyonlineServiceI;

/**
 * 上线用户
 * 
 * @author Wujun
 * 
 */
@Namespace("/base")
@Action
public class SyonlineAction extends BaseAction<Syonline> {

	/**
	 * 注入业务逻辑，使当前action调用service.xxx的时候，直接是调用基础业务逻辑
	 * 
	 * 如果想调用自己特有的服务方法时，请使用((TServiceI) service).methodName()这种形式强转类型调用
	 * 
	 * @param service
	 */
	@Autowired
	public void setService(SyonlineServiceI service) {
		this.service = service;
	}

}
