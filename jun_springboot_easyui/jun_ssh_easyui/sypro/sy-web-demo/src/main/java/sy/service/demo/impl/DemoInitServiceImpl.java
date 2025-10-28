package sy.service.demo.impl;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import sy.dao.base.BaseDao;
import sy.model.demo.DemoA;
import sy.model.demo.DemoB;
import sy.model.demo.DemoC;
import sy.model.demo.DemoCompany;
import sy.model.demo.DemoResource;
import sy.model.demo.DemoRole;
import sy.model.demo.DemoUser;
import sy.service.base.impl.BaseServiceImpl;
import sy.service.demo.DemoAService;
import sy.service.demo.DemoBService;
import sy.service.demo.DemoCService;
import sy.service.demo.DemoCompanyService;
import sy.service.demo.DemoInitService;
import sy.service.demo.DemoResourceService;
import sy.service.demo.DemoRoleService;
import sy.service.demo.DemoUserService;
import sy.util.base.DateUtil;

/**
 * 初始化一些测试数据
 * 
 * http://git.oschina.net/sphsyv/sypro
 * 
 * @author 孙宇
 *
 */
@Service("demoInitService")
public class DemoInitServiceImpl extends BaseServiceImpl implements DemoInitService {

	@Resource(name = "demoUserService")
	private DemoUserService demoUserService;

	@Resource(name = "demoRoleService")
	private DemoRoleService demoRoleService;

	@Resource(name = "demoResourceService")
	private DemoResourceService demoResourceService;

	@Resource(name = "demoCompanyService")
	private DemoCompanyService demoCompanyService;

	@Resource(name = "demoAService")
	private DemoAService demoAService;

	@Resource(name = "demoBService")
	private DemoBService demoBService;

	@Resource(name = "demoCService")
	private DemoCService demoCService;

	@Override
	public void init() {
		DemoCompany company = new DemoCompany();
		company.setName("公司1");
		demoCompanyService.save(company);

		DemoCompany company2 = new DemoCompany();
		company2.setName("公司2");
		demoCompanyService.save(company2);

		for (int i = 0; i < 50; i++) {// 添加50个用户用于测试
			DemoUser user = new DemoUser();
			user.setName("孙" + (i + 1) + "宇");
			user.setPwd(DigestUtils.sha1Hex("123456"));// 密码加密
			user.setBirthday(DateUtil.stringToDate("2000-11-01"));
			user.setAge((short) 14);
			user.setSex((short) 1);
			user.setCompany(company);
			demoUserService.save(user);
		}

		for (int i = 0; i < 50; i++) {// 添加50个用户用于测试
			DemoUser user = new DemoUser();
			user.setName("白" + (i + 1) + "兰兰");
			user.setPwd(DigestUtils.sha1Hex("654321"));// 密码加密
			user.setBirthday(DateUtil.stringToDate("2000-11-01"));
			user.setAge((short) 31);
			user.setSex((short) 0);
			user.setCompany(company2);
			demoUserService.save(user);
		}

		for (int i = 0; i < 10; i++) {// 添加10个角色
			DemoRole role = new DemoRole();
			role.setName("角" + (i + 1) + "色");
			role.setCompany(company);
			demoRoleService.save(role);
		}

		for (int i = 0; i < 10; i++) {// 添加10个资源
			DemoResource resource = new DemoResource();
			resource.setName("资" + (i + 1) + "源");
			demoResourceService.save(resource);
			for (int k = 0; k < 5; k++) {// 为当前资源添加5个子资源
				DemoResource r = new DemoResource();
				r.setName("子资源" + (k + 1));
				r.setResource(resource);
				demoResourceService.save(r);
			}
		}

		DemoRole role = demoRoleService.get(1L);
		role.getResources().addAll(demoResourceService.find());// 给这个角色添加所有资源
		demoRoleService.update(role);

		DemoUser user = demoUserService.get(1L);
		user.getRoles().addAll(demoRoleService.find());// 给这个用户添加所有角色
		demoUserService.update(user);// 更新

		DemoA a = new DemoA();
		a.setName("我是A");
		demoAService.save(a);

		DemoB b = new DemoB();
		b.setName("我是B");
		b.setDemoa(a);
		demoBService.save(b);

		DemoC c = new DemoC();
		c.setName("我是C");
		c.setDemob(b);
		demoCService.save(c);

	}

	@Override
	public void setDao(BaseDao dao) {
		// 由于是聚合service，所以不需要设置baseDao了。
	}

}
