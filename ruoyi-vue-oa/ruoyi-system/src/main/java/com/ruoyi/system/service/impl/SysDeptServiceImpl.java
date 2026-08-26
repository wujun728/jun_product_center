package com.ruoyi.system.service.impl;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.TreeSelect;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.module.CommonOptions;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.system.mapper.SysDeptMapper;
import com.ruoyi.system.mapper.SysRoleMapper;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.tools.lock.RedisLock;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * 部门管理 服务实现
 * 
 * @author ruoyi
 */
@Service
public class SysDeptServiceImpl implements ISysDeptService
{
    @Autowired
    private SysDeptMapper deptMapper;

    @Autowired
    private SysRoleMapper roleMapper;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private RedisLock redisLock;
    @Autowired
    private ISysUserService sysUserService;

    private static final String DEPT_TREE_KEY_PREFIX = "dept:tree";
    private static final String DEPT_TREE_CHILD_KEY_PREFIX = "dept:tree:child:";
    private static final String DEPT_TREE_KEY_LOCK = "dept:tree:lock:";

    /**
     * 查询部门管理数据
     * 
     * @param dept 部门信息
     * @return 部门信息集合
     */
    @Override
    @DataScope(deptAlias = "d")
    public List<SysDept> selectDeptList(SysDept dept)
    {
        List<SysDept> depts = deptMapper.selectDeptList(dept);
        if (CollectionUtils.isNotEmpty(depts)) {
            depts.stream().forEach(d -> {
                if(StringUtils.isNotBlank(d.getLeader())) {
                    List<SysUser> users = sysUserService.selectByUserIds(Arrays.asList(d.getLeader().split(Constants.COMMA)));
                    if (CollectionUtils.isNotEmpty(users)) {
                        List<String> names = users.stream().map(SysUser::getNickName).collect(Collectors.toList());
                        d.setLeader(String.join(Constants.COMMA, names));
                    }
                }
            });
        }
        return depts;
    }

    /**
     * 查询部门树结构信息
     * 
     * @param dept 部门信息
     * @return 部门树信息集合
     */
    @Override
    public List<TreeSelect> selectDeptTreeList(SysDept dept)
    {
        List<SysDept> depts = SpringUtils.getAopProxy(this).selectDeptList(dept);
        return buildDeptTreeSelect(depts);
    }

    /**
     * 构建前端所需要树结构
     *
     * @param dept 查询参数
     * @return 树结构列表
     */
    @Override
    public List<SysDept> buildDeptTreeCache(SysDept dept) {
        List<SysDept> depts = selectInitDeptList(dept);
        return buildDeptTree(depts);
    }

    /**
     * 构建前端所需要树结构
     * 
     * @param depts 部门列表
     * @return 树结构列表
     */
    @Override
    public List<SysDept> buildDeptTree(List<SysDept> depts)
    {
        List<SysDept> returnList = new ArrayList<SysDept>();
        List<String> tempList = depts.stream().map(SysDept::getDeptId).collect(Collectors.toList());
        for (SysDept dept : depts)
        {
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(dept.getParentId()))
            {
                recursionFn(depts, dept);
                returnList.add(dept);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = depts;
        }
        return returnList;
    }

    /**
     * 构建前端所需要下拉树结构
     * 
     * @param depts 部门列表
     * @return 下拉树结构列表
     */
    @Override
    public List<TreeSelect> buildDeptTreeSelect(List<SysDept> depts)
    {
        List<SysDept> deptTrees = buildDeptTree(depts);
        return deptTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    /**
     * 根据角色ID查询部门树信息
     * 
     * @param roleId 角色ID
     * @return 选中部门列表
     */
    @Override
    public List<String> selectDeptListByRoleId(String roleId)
    {
        SysRole role = roleMapper.selectRoleById(roleId);
        return deptMapper.selectDeptListByRoleId(roleId, role.isDeptCheckStrictly());
    }

    /**
     * 根据部门ID查询信息
     * 
     * @param deptId 部门ID
     * @return 部门信息
     */
    @Override
    public SysDept selectDeptById(String deptId)
    {
        SysDept dept = deptMapper.selectDeptById(deptId);
        if (dept != null && StringUtils.isNotBlank(dept.getLeader())) {
            List<SysUser> users = sysUserService.selectByUserIds(Arrays.asList(dept.getLeader().split(Constants.COMMA)));
            dept.setLeaders(users);
        }
        return dept;
    }

    /**
     * 根据ID查询所有子部门（正常状态）
     * 
     * @param deptId 部门ID
     * @return 子部门数
     */
    @Override
    public int selectNormalChildrenDeptById(String deptId)
    {
        List<SysDept> childs = getDeptChildByParentId(deptId);
        if (CollectionUtils.isEmpty(childs)) {
            return 0;
        }
        return childs.stream()
                .filter(child -> child.getStatus().equals("0"))
                .collect(Collectors.toSet())
                .size();
    }

    /**
     * 是否存在子节点
     * 
     * @param deptId 部门ID
     * @return 结果
     */
    @Override
    public boolean hasChildByDeptId(String deptId)
    {
        int result = deptMapper.hasChildByDeptId(deptId);
        return result > 0;
    }

    /**
     * 查询部门是否存在用户
     * 
     * @param deptId 部门ID
     * @return 结果 true 存在 false 不存在
     */
    @Override
    public boolean checkDeptExistUser(String deptId)
    {
        int result = deptMapper.checkDeptExistUser(deptId);
        return result > 0;
    }

    /**
     * 校验部门名称是否唯一
     * 
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    public boolean checkDeptNameUnique(SysDept dept)
    {
        String deptId = StringUtils.isNull(dept.getDeptId()) ? StringUtils.EMPTY : dept.getDeptId();
        SysDept info = deptMapper.checkDeptNameUnique(dept.getDeptName(), dept.getParentId());
        if (StringUtils.isNotNull(info) && !StringUtils.equals(info.getDeptId(), deptId))
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验部门是否有数据权限
     * 
     * @param deptId 部门id
     */
    @Override
    public void checkDeptDataScope(String deptId)
    {
        if (!SysUser.isAdmin(SecurityUtils.getUserId()) && StringUtils.isNotNull(deptId))
        {
            SysDept dept = new SysDept();
            dept.setDeptId(deptId);
            List<SysDept> depts = SpringUtils.getAopProxy(this).selectDeptList(dept);
            if (StringUtils.isEmpty(depts))
            {
                throw new ServiceException("没有权限访问部门数据！");
            }
        }
    }

    /**
     * 新增保存部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertDept(SysDept dept)
    {
        SysDept info = deptMapper.selectDeptById(dept.getParentId());
        // 如果父节点不为正常状态,则不允许新增子节点
        if (!UserConstants.DEPT_NORMAL.equals(info.getStatus()))
        {
            throw new ServiceException("部门停用，不允许新增");
        }
        if(CollectionUtils.isNotEmpty(dept.getLeaders())) {
            dept.setLeader(dept.getLeaders().stream().map(SysUser::getUserId).collect(Collectors.joining(Constants.COMMA)));
        }
        dept.setDeptId(IdUtils.fastSimpleUUID());
        dept.setAncestors(info.getAncestors() + "," + dept.getParentId());
        int result = deptMapper.insertDept(dept);
        refreshDeptTreeCache();
        return result;
    }

    /**
     * 修改保存部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateDept(SysDept dept)
    {
        AtomicInteger result = new AtomicInteger();
        redisLock.doLock(DEPT_TREE_KEY_LOCK + dept.getDeptId(), () -> {
            SysDept newParentDept = deptMapper.selectDeptById(dept.getParentId());
            SysDept oldDept = deptMapper.selectDeptById(dept.getDeptId());
            if (StringUtils.isNotNull(newParentDept) && StringUtils.isNotNull(oldDept)) {
                String newAncestors = newParentDept.getAncestors() + "," + newParentDept.getDeptId();
                String oldAncestors = oldDept.getAncestors();
                dept.setAncestors(newAncestors);
                updateDeptChildren(dept.getDeptId(), newAncestors, oldAncestors);
            }
            if(CollectionUtils.isNotEmpty(dept.getLeaders())) {
                dept.setLeader(dept.getLeaders().stream().map(SysUser::getUserId).collect(Collectors.joining(Constants.COMMA)));
            }
            result.set(deptMapper.updateDept(dept));
            if (UserConstants.DEPT_NORMAL.equals(dept.getStatus()) && StringUtils.isNotEmpty(dept.getAncestors())
                    && !StringUtils.equals("0", dept.getAncestors())) {
                // 如果该部门是启用状态，则启用该部门的所有上级部门
                updateParentDeptStatusNormal(dept);
            }
            updateDeptTreeCache(dept);
        });
        return result.get();
    }

    /**
     * 获取部门树子部门ID
     *
     * @param parentId 父部门ID
     * @return
     */
    @Override
    public List<String> getDeptChildIdByParentId(String parentId) {
        if (StringUtils.isEmpty(parentId)) {
            return new ArrayList<>();
        }
        String cacheKey = DEPT_TREE_CHILD_KEY_PREFIX + parentId;
        if (redisCache.hasKey(cacheKey)) {
            return redisCache.getCacheObject(cacheKey);
        }
        List<SysDept> childsByParentId = getDeptChildByParentId(parentId);
        List<String> childIds = childsByParentId.stream()
                .map(SysDept::getDeptId)
                .collect(Collectors.toList());
        redisCache.setCacheObject(cacheKey, childIds, 1, TimeUnit.DAYS);
        return childIds;
    }

    /**
     * 获取部门树子部门
     *
     * @param parentId 父部门ID
     * @return
     */
    @Override
    public List<SysDept> getDeptChildByParentId(String parentId) {
        List<SysDept> sysDepts = redisCache.getCacheObject(DEPT_TREE_KEY_PREFIX);
        if (CollectionUtils.isEmpty(sysDepts)) {
            return new ArrayList<>();
        }
        SysDept dept = recursionDept(sysDepts, parentId);
        if (Objects.isNull(dept)) {
            return new ArrayList<>();
        }
        List<SysDept> allChildDepts = new ArrayList<>();
        recursionDeptTree(allChildDepts, dept.getChildren());
        return allChildDepts;
    }

    /**
     * 递归获取部门树子部门
     *
     * @param sysDepts 部门树所有部门
     * @param parentId 父部门ID
     */
    private SysDept recursionDept(List<SysDept> sysDepts, String parentId) {
        if (CollectionUtils.isEmpty(sysDepts)) {
            return  null;
        }
        for (SysDept dept : sysDepts) {
            if (dept.getDeptId().equals(parentId)) {
                return dept;
            }
            SysDept result = recursionDept(dept.getChildren(), parentId);
            if (Objects.nonNull(result)) {
                return result;
            }
        }
        return null;
    }

    /**
     * 修改该部门的父级部门状态
     * 
     * @param dept 当前部门
     */
    private void updateParentDeptStatusNormal(SysDept dept)
    {
        String ancestors = dept.getAncestors();
        String[] deptIds = Convert.toStrArray(ancestors);
        deptMapper.updateDeptStatusNormal(deptIds);
    }

    /**
     * 修改子元素关系
     * 
     * @param deptId 被修改的部门ID
     * @param newAncestors 新的父ID集合
     * @param oldAncestors 旧的父ID集合
     */
    public void updateDeptChildren(String deptId, String newAncestors, String oldAncestors)
    {
        List<SysDept> children = getDeptChildByParentId(deptId);
        for (SysDept child : children)
        {
            child.setAncestors(child.getAncestors().replaceFirst(oldAncestors, newAncestors));
        }
        if (!children.isEmpty())
        {
            deptMapper.updateDeptChildren(children);
        }
    }

    /**
     * 删除部门管理信息
     * 
     * @param deptId 部门ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteDeptById(String deptId)
    {
        int result = deptMapper.deleteDeptById(deptId);
        refreshDeptTreeCache();
        redisCache.deleteObject(DEPT_TREE_CHILD_KEY_PREFIX + deptId);
        return result;
    }

    @Override
    public List<CommonOptions> findAllDeptOrg() {
        List<CommonOptions> res = new ArrayList<>();

        return null;
    }

    @Override
    public List<TreeSelect> selectCorpTreeList(SysDept dept) {
        List<SysDept> depts = deptMapper.selectDeptList(dept);
        return buildDeptTreeSelect(depts);
    }

    /**
     * 查询部门管理数据
     *
     * @param dept 部门信息
     * @return 部门信息集合
     */
    @Override
    public List<SysDept> selectInitDeptList(SysDept dept)
    {
        return deptMapper.selectDeptList(dept);
    }

    /**
     * 递归列表
     */
    private void recursionFn(List<SysDept> list, SysDept t)
    {
        // 得到子节点列表
        List<SysDept> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysDept tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<SysDept> getChildList(List<SysDept> list, SysDept t)
    {
        List<SysDept> tlist = new ArrayList<>();
        for (SysDept n : list) {
            if (StringUtils.isNotNull(n.getParentId()) && StringUtils.equals(n.getParentId(), t.getDeptId())) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysDept> list, SysDept t)
    {
        return !getChildList(list, t).isEmpty();
    }

    /**
     * 递归部门树
     *
     * @param childDepts 所有子部门
     * @param childDepts 子部门
     */
    private void recursionDeptTree(List<SysDept> allChildDepts, List<SysDept> childDepts) {
        if (CollectionUtils.isEmpty(childDepts)) {
            return;
        }
        for (SysDept dept : childDepts) {
            allChildDepts.add(dept);
            recursionDeptTree(allChildDepts, dept.getChildren());
        }
    }

    /**
     * 新增部门后，更新部门树缓存
     */
    private void refreshDeptTreeCache() {
        SysDept queryParam = new SysDept();
        List<SysDept> depts = selectInitDeptList(queryParam);
        List<SysDept> deptTrees = buildDeptTree(depts);
        redisCache.setCacheObject(DEPT_TREE_KEY_PREFIX, deptTrees);
    }

    /**
     * 更新部门树缓存
     *
     * @param dept 当前部门
     */
    private void updateDeptTreeCache(SysDept dept) {
        refreshDeptTreeCache();
        redisCache.deleteObject(DEPT_TREE_CHILD_KEY_PREFIX + dept.getDeptId());
    }
}
