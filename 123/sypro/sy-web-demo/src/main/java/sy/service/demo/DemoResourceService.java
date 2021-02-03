package sy.service.demo;

import java.util.List;

import sy.model.demo.DemoResource;
import sy.pageModel.demo.DemoResourceTreeGrid;
import sy.pageModel.demo.Tree;
import sy.service.base.BaseService;
import sy.util.base.QueryFilter;

/**
 * DemoResourceService
 *
 * http://git.oschina.net/sphsyv/sypro
 *
 * 由代码生成器生成
 *
 * @author 孙宇
 *
 */
public interface DemoResourceService extends BaseService<DemoResource, Long> {

	List<Tree> findTree();

	List<DemoResourceTreeGrid> findTreeGrid(QueryFilter filter, String level);

}