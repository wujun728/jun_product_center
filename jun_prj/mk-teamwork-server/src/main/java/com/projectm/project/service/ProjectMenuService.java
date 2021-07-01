package com.projectm.project.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.framework.common.utils.ServletUtils;
import com.projectm.common.Constant;
import com.projectm.common.MenuUtils;
import com.projectm.project.domain.ProjectMenu;
import com.projectm.project.mapper.ProjectMenuMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ProjectMenuService extends ServiceImpl<ProjectMenuMapper, ProjectMenu> {

    public List<ProjectMenu> getCurrentUserMenu(){
//        Map member = (Map)ServletUtils.getRequest().getSession().getAttribute(Constant.CURRENT_USER);
//        List nodes = (List)member.get(Constant.NODES);
        List<ProjectMenu> lstMenu = baseMapper.selectAllProjectMenu(new HashMap(){{put("status",1);}});
        MenuUtils mu = new MenuUtils(lstMenu);
        return mu.builTree();
    }
    public List<ProjectMenu> getAllProjectMenuTree(){
        List<ProjectMenu> listMenu = baseMapper.selectAllProjectMenu(new HashMap());
        MenuUtils mu = new MenuUtils(listMenu);
        return mu.builTree();
    }

    public List<ProjectMenu> getProjectMenuByStatus(String status){
        List<ProjectMenu> listMenu = baseMapper.selectAllProjectMenu(new HashMap(){{put("status",status);}});
        MenuUtils mu = new MenuUtils(listMenu);
        return mu.builTree();
    }
    /**
     * 获取子目录的ID，并删除（问题）
     * @param id
     * @return
     */
    @Transactional
    public Integer menuDelete(Integer id){
        List<Integer> ids = new ArrayList<>();
        ids.add(id);
        List<ProjectMenu> childMenu = baseMapper.selectProjectMenuByPid(new HashMap(){{put("pid",id);}});
        List<ProjectMenu> pMenuList = baseMapper.selectAllProjectMenu(new HashMap());
        for(ProjectMenu m:childMenu){
            ids.add(m.getId());
            ids.addAll(MenuUtils.getMenuIds(pMenuList,m));
        }
        return baseMapper.deleteBatchIds(ids);
    }

}
