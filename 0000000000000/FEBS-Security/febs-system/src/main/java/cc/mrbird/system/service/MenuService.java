package cc.mrbird.system.service;

import cc.mrbird.common.domain.Tree;
import cc.mrbird.common.service.IService;
import cc.mrbird.system.domain.Menu;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Map;

@CacheConfig(cacheNames = "MenuService")
public interface MenuService extends IService<Menu> {

    String findUserPermissions(String userName);

    List<Menu> findUserMenus(String userName);

    List<Menu> findAllMenus(Menu menu);

    Tree<Menu> getMenuButtonTree();

    Tree<Menu> getMenuTree();

    Tree<Menu> getUserMenu(String userName);

    Menu findById(Long menuId);

    Menu findByNameAndType(String menuName, String type);

    void addMenu(Menu menu);

    void updateMenu(Menu menu);

    void deleteMeuns(String menuIds);

    @Cacheable(key = "'url_'+ #p0")
    List<Map<String, String>> getAllUrl(String p1);

}
