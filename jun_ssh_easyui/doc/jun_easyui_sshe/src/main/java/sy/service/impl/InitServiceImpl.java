package sy.service.impl;

import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.base.BaseDaoI;
import sy.model.base.Syorganization;
import sy.model.base.Syresource;
import sy.model.base.Syresourcetype;
import sy.model.base.Syrole;
import sy.model.base.Syuser;
import sy.service.InitServiceI;
import sy.util.base.MD5Util;

import com.alibaba.fastjson.JSON;

/**
 * 初始化数据库
 * 
 * @author Wujun
 * 
 */
@Service
public class InitServiceImpl implements InitServiceI {

	private static final Logger logger = Logger.getLogger(InitServiceImpl.class);

	private static final String FILEPATH = "initDataBase.xml";

	@Autowired
	private BaseDaoI baseDao;

	@Override
	synchronized public void initDb() {
		try {
			Document document = new SAXReader().read(Thread.currentThread().getContextClassLoader().getResourceAsStream(FILEPATH));

			initResourcetype(document);// 初始化资源类型

			initResource(document);// 初始化资源

			initRole(document);// 初始化角色

			initRoleResource(document);// 初始化角色和资源的关系

			initOrganization(document);// 初始化机构

			initOrganizationResource(document);// 初始化机构和资源的关系

			initUser(document);// 初始化用户

			initUserRole(document);// 初始化用户和角色的关系

			initUserOrganization(document);// 初始化用户和机构的关系

		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	private void initResourcetype(Document document) {
		List childNodes = document.selectNodes("//resourcetypes/resourcetype");
		for (Object obj : childNodes) {
			Node node = (Node) obj;
			Syresourcetype type = (Syresourcetype) baseDao.getById(Syresourcetype.class, node.valueOf("@id"));
			if (type == null) {
				type = new Syresourcetype();
			}
			type.setId(node.valueOf("@id"));
			type.setName(node.valueOf("@name"));
			type.setDescription(node.valueOf("@description"));
			logger.info(JSON.toJSONStringWithDateFormat(type, "yyyy-MM-dd HH:mm:ss"));
			baseDao.saveOrUpdate(type);
		}
	}

	private void initResource(Document document) {
		List childNodes = document.selectNodes("//resources/resource");
		for (Object obj : childNodes) {
			Node node = (Node) obj;
			Syresource resource = (Syresource) baseDao.getById(Syresource.class, node.valueOf("@id"));
			if (resource == null) {
				resource = new Syresource();
			}
			resource.setId(node.valueOf("@id"));
			resource.setName(node.valueOf("@name"));
			resource.setUrl(node.valueOf("@url"));
			resource.setDescription(node.valueOf("@description"));
			resource.setIconCls(node.valueOf("@iconCls"));
			resource.setSeq(Integer.parseInt(node.valueOf("@seq")));

			if (node.valueOf("@pid") != null && !node.valueOf("@pid").equals("")) {
				resource.setSyresource((Syresource) baseDao.getById(Syresource.class, node.valueOf("@pid")));
			} else {
				resource.setSyresource(null);
			}

			Node n = node.selectSingleNode("resourcetype");
			Syresourcetype type = (Syresourcetype) baseDao.getById(Syresourcetype.class, n.valueOf("@id"));
			if (type != null) {
				resource.setSyresourcetype(type);
			}

			logger.info(JSON.toJSONStringWithDateFormat(resource, "yyyy-MM-dd HH:mm:ss"));
			baseDao.saveOrUpdate(resource);
		}
	}

	private void initRole(Document document) {
		List childNodes = document.selectNodes("//roles/role");
		for (Object obj : childNodes) {
			Node node = (Node) obj;
			Syrole role = (Syrole) baseDao.getById(Syrole.class, node.valueOf("@id"));
			if (role == null) {
				role = new Syrole();
			}
			role.setId(node.valueOf("@id"));
			role.setName(node.valueOf("@name"));
			role.setDescription(node.valueOf("@description"));
			role.setSeq(Integer.parseInt(node.valueOf("@seq")));
			logger.info(JSON.toJSONStringWithDateFormat(role, "yyyy-MM-dd HH:mm:ss"));
			baseDao.saveOrUpdate(role);
		}
	}

	private void initRoleResource(Document document) {
		List<Node> childNodes = document.selectNodes("//roles_resources/role");
		for (Node node : childNodes) {
			Syrole role = (Syrole) baseDao.getById(Syrole.class, node.valueOf("@id"));
			if (role != null) {
				role.setSyresources(new HashSet());
				List<Node> cNodes = node.selectNodes("resource");
				for (Node n : cNodes) {
					Syresource resource = (Syresource) baseDao.getById(Syresource.class, n.valueOf("@id"));
					if (resource != null) {
						role.getSyresources().add(resource);
					}
				}
				logger.info(JSON.toJSONStringWithDateFormat(role, "yyyy-MM-dd HH:mm:ss"));
				baseDao.saveOrUpdate(role);
			}
		}

		Syrole role = (Syrole) baseDao.getById(Syrole.class, "0");// 将角色为0的超级管理员角色，赋予所有权限
		if (role != null) {
			role.getSyresources().addAll(baseDao.find("from Syresource t"));
			logger.info(JSON.toJSONStringWithDateFormat(role, "yyyy-MM-dd HH:mm:ss"));
			baseDao.saveOrUpdate(role);
		}
	}

	private void initOrganization(Document document) {
		List childNodes = document.selectNodes("//organizations/organization");
		for (Object obj : childNodes) {
			Node node = (Node) obj;
			Syorganization organization = (Syorganization) baseDao.getById(Syorganization.class, node.valueOf("@id"));
			if (organization == null) {
				organization = new Syorganization();
			}
			organization.setId(node.valueOf("@id"));
			organization.setName(node.valueOf("@name"));
			organization.setAddress(node.valueOf("@address"));
			organization.setSeq(Integer.parseInt(node.valueOf("@seq")));
			organization.setIconCls(node.valueOf("@iconCls"));

			if (node.valueOf("@pid") != null && !node.valueOf("@pid").equals("")) {
				organization.setSyorganization((Syorganization) baseDao.getById(Syorganization.class, node.valueOf("@pid")));
			} else {
				organization.setSyorganization(null);
			}

			logger.info(JSON.toJSONStringWithDateFormat(organization, "yyyy-MM-dd HH:mm:ss"));
			baseDao.saveOrUpdate(organization);
		}
	}

	private void initOrganizationResource(Document document) {
		List<Node> childNodes = document.selectNodes("//organizations_resources/organization");
		for (Node node : childNodes) {
			Syorganization organization = (Syorganization) baseDao.getById(Syorganization.class, node.valueOf("@id"));
			if (organization != null) {
				organization.setSyresources(new HashSet());
				List<Node> cNodes = node.selectNodes("resource");
				for (Node n : cNodes) {
					Syresource resource = (Syresource) baseDao.getById(Syresource.class, n.valueOf("@id"));
					if (resource != null) {
						organization.getSyresources().add(resource);
					}
				}
				logger.info(JSON.toJSONStringWithDateFormat(organization, "yyyy-MM-dd HH:mm:ss"));
				baseDao.saveOrUpdate(organization);
			}
		}
	}

	private void initUser(Document document) {
		List<Node> childNodes = document.selectNodes("//users/user");
		for (Node node : childNodes) {
			Syuser user = (Syuser) baseDao.getById(Syuser.class, node.valueOf("@id"));
			if (user == null) {
				user = new Syuser();
			}
			user.setId(node.valueOf("@id"));
			user.setName(node.valueOf("@name"));
			user.setLoginname(node.valueOf("@loginname"));
			user.setPwd(MD5Util.md5(node.valueOf("@pwd")));
			user.setSex(node.valueOf("@sex"));
			user.setAge(Integer.valueOf(node.valueOf("@age")));
			logger.info(JSON.toJSONStringWithDateFormat(user, "yyyy-MM-dd HH:mm:ss"));
			List<Syuser> ul = baseDao.find("from Syuser u where u.loginname = '" + user.getLoginname() + "' and u.id != '" + user.getId() + "'");
			for (Syuser u : ul) {
				baseDao.delete(u);
			}
			baseDao.saveOrUpdate(user);
		}
	}

	private void initUserRole(Document document) {
		List<Node> childNodes = document.selectNodes("//users_roles/user");
		for (Node node : childNodes) {
			Syuser user = (Syuser) baseDao.getById(Syuser.class, node.valueOf("@id"));
			if (user != null) {
				user.setSyroles(new HashSet());
				List<Node> cNodes = node.selectNodes("role");
				for (Node n : cNodes) {
					Syrole role = (Syrole) baseDao.getById(Syrole.class, n.valueOf("@id"));
					if (role != null) {
						user.getSyroles().add(role);
					}
				}
				logger.info(JSON.toJSONStringWithDateFormat(user, "yyyy-MM-dd HH:mm:ss"));
				baseDao.saveOrUpdate(user);
			}
		}

		Syuser user = (Syuser) baseDao.getById(Syuser.class, "0");// 用户为0的超级管理员，赋予所有角色
		if (user != null) {
			user.getSyroles().addAll(baseDao.find("from Syrole"));
			logger.info(JSON.toJSONStringWithDateFormat(user, "yyyy-MM-dd HH:mm:ss"));
			baseDao.saveOrUpdate(user);
		}
	}

	private void initUserOrganization(Document document) {
		List<Node> childNodes = document.selectNodes("//users_organizations/user");
		for (Node node : childNodes) {
			Syuser user = (Syuser) baseDao.getById(Syuser.class, node.valueOf("@id"));
			if (user != null) {
				user.setSyorganizations(new HashSet());
				List<Node> cNodes = node.selectNodes("organization");
				for (Node n : cNodes) {
					Syorganization organization = (Syorganization) baseDao.getById(Syorganization.class, n.valueOf("@id"));
					if (organization != null) {
						user.getSyorganizations().add(organization);
					}
				}
				logger.info(JSON.toJSONStringWithDateFormat(user, "yyyy-MM-dd HH:mm:ss"));
				baseDao.saveOrUpdate(user);
			}
		}

		Syuser user = (Syuser) baseDao.getById(Syuser.class, "0");// 用户为0的超级管理员，赋予所有机构
		if (user != null) {
			user.getSyorganizations().addAll(baseDao.find("from Syorganization"));
			logger.info(JSON.toJSONStringWithDateFormat(user, "yyyy-MM-dd HH:mm:ss"));
			baseDao.saveOrUpdate(user);
		}
	}

}
