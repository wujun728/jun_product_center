package cn.iocoder.yudao.adminserver.modules.system.service.permission.impl;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import cn.iocoder.yudao.adminserver.modules.system.controller.permission.vo.menu.SysMenuCreateReqVO;
import cn.iocoder.yudao.adminserver.modules.system.controller.permission.vo.menu.SysMenuListReqVO;
import cn.iocoder.yudao.adminserver.modules.system.controller.permission.vo.menu.SysMenuUpdateReqVO;
import cn.iocoder.yudao.adminserver.modules.system.convert.permission.SysMenuConvert;
import cn.iocoder.yudao.adminserver.modules.system.dal.mysql.permission.SysMenuMapper;
import cn.iocoder.yudao.adminserver.modules.system.dal.dataobject.permission.SysMenuDO;
import cn.iocoder.yudao.adminserver.modules.system.enums.permission.MenuIdEnum;
import cn.iocoder.yudao.adminserver.modules.system.enums.permission.MenuTypeEnum;
import cn.iocoder.yudao.adminserver.modules.system.mq.producer.permission.SysMenuProducer;
import cn.iocoder.yudao.adminserver.modules.system.service.permission.SysMenuService;
import cn.iocoder.yudao.adminserver.modules.system.service.permission.SysPermissionService;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.adminserver.modules.system.enums.SysErrorCodeConstants.*;

/**
 * 菜单 Service 实现
 *
 * @author 芋道源码
 */
@Service
@Slf4j
public class SysMenuServiceImpl implements SysMenuService {

    /**
     * 定时执行 {@link #schedulePeriodicRefresh()} 的周期
     * 因为已经通过 Redis Pub/Sub 机制，所以频率不需要高
     */
    private static final long SCHEDULER_PERIOD = 5 * 60 * 1000L;

    /**
     * 菜单缓存
     * key：菜单编号
     *
     * 这里声明 volatile 修饰的原因是，每次刷新时，直接修改指向
     */
    private volatile Map<Long, SysMenuDO> menuCache;
    /**
     * 权限与菜单缓存
     * key：权限 {@link SysMenuDO#getPermission()}
     * value：SysMenuDO 数组，因为一个权限可能对应多个 SysMenuDO 对象
     *
     * 这里声明 volatile 修饰的原因是，每次刷新时，直接修改指向
     */
    private volatile Multimap<String, SysMenuDO> permissionMenuCache;
    /**
     * 缓存菜单的最大更新时间，用于后续的增量轮询，判断是否有更新
     */
    private volatile Date maxUpdateTime;

    @Resource
    private SysMenuMapper menuMapper;
    @Resource
    private SysPermissionService permissionService;

    @Resource
    private SysMenuProducer menuProducer;

    /**
     * 初始化 {@link #menuCache} 和 {@link #permissionMenuCache} 缓存
     */
    @Override
    @PostConstruct
    public synchronized void initLocalCache() {
        // 获取菜单列表，如果有更新
        List<SysMenuDO> menuList = this.loadMenuIfUpdate(maxUpdateTime);
        if (CollUtil.isEmpty(menuList)) {
            return;
        }

        // 构建缓存
        ImmutableMap.Builder<Long, SysMenuDO> menuCacheBuilder = ImmutableMap.builder();
        ImmutableMultimap.Builder<String, SysMenuDO> permMenuCacheBuilder = ImmutableMultimap.builder();
        menuList.forEach(menuDO -> {
            menuCacheBuilder.put(menuDO.getId(), menuDO);
            permMenuCacheBuilder.put(menuDO.getPermission(), menuDO);
        });
        menuCache = menuCacheBuilder.build();
        permissionMenuCache = permMenuCacheBuilder.build();
        assert menuList.size() > 0; // 断言，避免告警
        maxUpdateTime = menuList.stream().max(Comparator.comparing(BaseDO::getUpdateTime)).get().getUpdateTime();
        log.info("[initLocalCache][缓存菜单，数量为:{}]", menuList.size());
    }

    @Scheduled(fixedDelay = SCHEDULER_PERIOD, initialDelay = SCHEDULER_PERIOD)
    public void schedulePeriodicRefresh() {
        initLocalCache();
    }

    /**
     * 如果菜单发生变化，从数据库中获取最新的全量菜单。
     * 如果未发生变化，则返回空
     *
     * @param maxUpdateTime 当前菜单的最大更新时间
     * @return 菜单列表
     */
    private List<SysMenuDO> loadMenuIfUpdate(Date maxUpdateTime) {
        // 第一步，判断是否要更新。
        if (maxUpdateTime == null) { // 如果更新时间为空，说明 DB 一定有新数据
            log.info("[loadMenuIfUpdate][首次加载全量菜单]");
        } else { // 判断数据库中是否有更新的菜单
            if (!menuMapper.selectExistsByUpdateTimeAfter(maxUpdateTime)) {
                return null;
            }
            log.info("[loadMenuIfUpdate][增量加载全量菜单]");
        }
        // 第二步，如果有更新，则从数据库加载所有菜单
        return menuMapper.selectList();
    }

    @Override
    public Long createMenu(SysMenuCreateReqVO reqVO) {
        // 校验父菜单存在
        checkParentResource(reqVO.getParentId(), null);
        // 校验菜单（自己）
        checkResource(reqVO.getParentId(), reqVO.getName(), null);
        // 插入数据库
        SysMenuDO menu = SysMenuConvert.INSTANCE.convert(reqVO);
        initMenuProperty(menu);
        menuMapper.insert(menu);
        // 发送刷新消息
        menuProducer.sendMenuRefreshMessage();
        // 返回
        return menu.getId();
    }

    @Override
    public void updateMenu(SysMenuUpdateReqVO reqVO) {
        // 校验更新的菜单是否存在
        if (menuMapper.selectById(reqVO.getId()) == null) {
            throw ServiceExceptionUtil.exception(MENU_NOT_EXISTS);
        }
        // 校验父菜单存在
        checkParentResource(reqVO.getParentId(), reqVO.getId());
        // 校验菜单（自己）
        checkResource(reqVO.getParentId(), reqVO.getName(), reqVO.getId());
        // 更新到数据库
        SysMenuDO updateObject = SysMenuConvert.INSTANCE.convert(reqVO);
        initMenuProperty(updateObject);
        menuMapper.updateById(updateObject);
        // 发送刷新消息
        menuProducer.sendMenuRefreshMessage();
    }

    /**
     * 删除菜单
     *
     * @param menuId 菜单编号
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteMenu(Long menuId) {
        // 校验是否还有子菜单
        if (menuMapper.selectCountByParentId(menuId) > 0) {
            throw ServiceExceptionUtil.exception(MENU_EXISTS_CHILDREN);
        }
        // 校验删除的菜单是否存在
        if (menuMapper.selectById(menuId) == null) {
            throw ServiceExceptionUtil.exception(MENU_NOT_EXISTS);
        }
        // 标记删除
        menuMapper.deleteById(menuId);
        // 删除授予给角色的权限
        permissionService.processMenuDeleted(menuId);
        // 发送刷新消息. 注意，需要事务提交后，在进行发送刷新消息。不然 db 还未提交，结果缓存先刷新了
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {

            @Override
            public void afterCommit() {
                menuProducer.sendMenuRefreshMessage();
            }

        });
    }

    @Override
    public List<SysMenuDO> getMenus() {
        return menuMapper.selectList();
    }

    @Override
    public List<SysMenuDO> getMenus(SysMenuListReqVO reqVO) {
        return menuMapper.selectList(reqVO);
    }

    @Override
    public List<SysMenuDO> listMenusFromCache(Collection<Integer> menuTypes, Collection<Integer> menusStatuses) {
        // 任一一个参数为空，则返回空
        if (CollectionUtils.isAnyEmpty(menuTypes, menusStatuses)) {
            return Collections.emptyList();
        }
        // 创建新数组，避免缓存被修改
        return menuCache.values().stream().filter(menu -> menuTypes.contains(menu.getType())
                && menusStatuses.contains(menu.getStatus()))
                .collect(Collectors.toList());
    }

    @Override
    public List<SysMenuDO> listMenusFromCache(Collection<Long> menuIds, Collection<Integer> menuTypes,
                                              Collection<Integer> menusStatuses) {
        // 任一一个参数为空，则返回空
        if (CollectionUtils.isAnyEmpty(menuIds, menuTypes, menusStatuses)) {
            return Collections.emptyList();
        }
        return menuCache.values().stream().filter(menu -> menuIds.contains(menu.getId())
                && menuTypes.contains(menu.getType())
                && menusStatuses.contains(menu.getStatus()))
                .collect(Collectors.toList());
    }

    @Override
    public List<SysMenuDO> getMenuListByPermissionFromCache(String permission) {
        return new ArrayList<>(permissionMenuCache.get(permission));
    }

    @Override
    public SysMenuDO getMenu(Long id) {
        return menuMapper.selectById(id);
    }

    /**
     * 校验父菜单是否合法
     *
     * 1. 不能设置自己为父菜单
     * 2. 父菜单不存在
     * 3. 父菜单必须是 {@link MenuTypeEnum#MENU} 菜单类型
     *
     * @param parentId 父菜单编号
     * @param childId 当前菜单编号
     */
    @VisibleForTesting
    public void checkParentResource(Long parentId, Long childId) {
        if (parentId == null || MenuIdEnum.ROOT.getId().equals(parentId)) {
            return;
        }
        // 不能设置自己为父菜单
        if (parentId.equals(childId)) {
            throw ServiceExceptionUtil.exception(MENU_PARENT_ERROR);
        }
        SysMenuDO menu = menuMapper.selectById(parentId);
        // 父菜单不存在
        if (menu == null) {
            throw ServiceExceptionUtil.exception(MENU_PARENT_NOT_EXISTS);
        }
        // 父菜单必须是目录或者菜单类型
        if (!MenuTypeEnum.DIR.getType().equals(menu.getType())
            && !MenuTypeEnum.MENU.getType().equals(menu.getType())) {
            throw ServiceExceptionUtil.exception(MENU_PARENT_NOT_DIR_OR_MENU);
        }
    }

    /**
     * 校验菜单是否合法
     *
     * 1. 校验相同父菜单编号下，是否存在相同的菜单名
     *
     * @param name 菜单名字
     * @param parentId 父菜单编号
     * @param id 菜单编号
     */
    @VisibleForTesting
    public void checkResource(Long parentId, String name, Long id) {
        SysMenuDO menu = menuMapper.selectByParentIdAndName(parentId, name);
        if (menu == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的菜单
        if (id == null) {
            throw ServiceExceptionUtil.exception(MENU_NAME_DUPLICATE);
        }
        if (!menu.getId().equals(id)) {
            throw ServiceExceptionUtil.exception(MENU_NAME_DUPLICATE);
        }
    }

    /**
     * 初始化菜单的通用属性。
     *
     * 例如说，只有目录或者菜单类型的菜单，才设置 icon
     *
     * @param menu 菜单
     */
    private void initMenuProperty(SysMenuDO menu) {
        // 菜单为按钮类型时，无需 component、icon、path 属性，进行置空
        if (MenuTypeEnum.BUTTON.getType().equals(menu.getType())) {
            menu.setComponent("");
            menu.setIcon("");
            menu.setPath("");
        }
    }

}
