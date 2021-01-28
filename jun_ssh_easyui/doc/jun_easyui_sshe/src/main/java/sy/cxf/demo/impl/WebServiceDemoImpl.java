package sy.cxf.demo.impl;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import sy.cxf.demo.WebServiceDemoI;
import sy.model.base.Syuser;
import sy.service.base.SyuserServiceI;

/**
 * WebService接口实现类
 * 
 * @author Wujun
 * 
 */
@WebService(endpointInterface = "sy.cxf.demo.WebServiceDemoI", serviceName = "webServiceDemo")
public class WebServiceDemoImpl implements WebServiceDemoI {

	@Autowired
	private SyuserServiceI userService;

	@Override
	public String helloWs(String name) {
		if (name == null || name.trim().equals("")) {
			name = "SunYu";
		}
		String str = "hello[" + name.trim() + "]WebService test ok!";
		System.out.println(str);
		return str;
	}

	@Override
	public Syuser getUser(String id) {
		if (id == null || id.trim().equals("")) {
			id = "0";
		}
		Syuser user = userService.getById(id.trim());
		Syuser u = new Syuser();
		u.setName(user.getName());
		u.setAge(user.getAge());
		u.setCreatedatetime(user.getCreatedatetime());
		u.setUpdatedatetime(user.getUpdatedatetime());
		u.setId(user.getId());
		u.setLoginname(user.getLoginname());
		u.setSex(user.getSex());
		u.setPhoto(user.getPhoto());
		return u;
	}

}
