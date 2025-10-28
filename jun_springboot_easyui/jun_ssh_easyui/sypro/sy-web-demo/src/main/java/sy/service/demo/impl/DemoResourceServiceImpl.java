package sy.service.demo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import sy.dao.base.BaseDao;
import sy.model.demo.DemoResource;
import sy.pageModel.demo.DemoResourceTreeGrid;
import sy.pageModel.demo.Tree;
import sy.service.base.impl.BaseServiceImpl;
import sy.service.demo.DemoResourceService;
import sy.util.base.BeanUtil;
import sy.util.base.QueryFilter;

/**
 * DemoResourceServiceImpl
 *
 * http://git.oschina.net/sphsyv/sypro
 *
 * 由代码生成器生成
 *
 * @author 孙宇
 *
 */
@Service("demoResourceService")
public class DemoResourceServiceImpl extends BaseServiceImpl<DemoResource, Long> implements DemoResourceService {

	@Resource(name = "demoResourceDao")
	@Override
	public void setDao(BaseDao<DemoResource, Long> dao) {
		super.dao = dao;
	}

	@Override
	public List<Tree> findTree() {
		QueryFilter filter = new QueryFilter();
		filter.setSort("id asc,name asc");
		List<Tree> l = new ArrayList<Tree>();
		for (DemoResource resource : dao.find(filter)) {
			Tree t = new Tree();
			t.setId("" + resource.getId());
			t.setName(resource.getName());
			if (resource.getResource() != null) {// 是否有父级
				t.setPid("" + resource.getResource().getId());
			}
			l.add(t);
		}
		return l;
	}

	@Override
	public List<DemoResourceTreeGrid> findTreeGrid(QueryFilter filter, String level) {
		List<DemoResourceTreeGrid> l = new ArrayList<DemoResourceTreeGrid>();
		for (DemoResource t : dao.find(filter)) {
			DemoResourceTreeGrid tg = new DemoResourceTreeGrid();

			BeanUtil.copyProperties(t, tg);

			tg.setLevel(Integer.parseInt(level));
			if (t.getResource() != null) {
				tg.setParent(t.getResource().getId());
			}
			if (t.getResources() != null && t.getResources().size() > 0) {// 是否有子级
				tg.setIsLeaf(false);// 有子级就不是叶子节点
				tg.setExpanded(false);
				tg.setLoaded(false);
			}

			l.add(tg);
		}
		return l;
	}

}
