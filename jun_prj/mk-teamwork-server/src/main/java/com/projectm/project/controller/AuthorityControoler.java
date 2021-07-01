package com.projectm.project.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.framework.common.utils.StringUtils;
import com.framework.common.AjaxResult;
import com.projectm.common.CommUtils;
import com.projectm.common.DateUtil;
import com.projectm.common.NodeUtils;
import com.projectm.project.domain.ProjectAuth;
import com.projectm.project.domain.ProjectAuthNode;
import com.projectm.project.domain.ProjectMenu;
import com.projectm.project.service.ProjectAuthService;
import com.projectm.project.service.ProjectMenuService;
import com.projectm.project.service.ProjectNodeService;
import com.projectm.web.BaseController;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/project")
public class AuthorityControoler extends BaseController {

    @Autowired
    private ProjectMenuService projectMenuService;

    @Autowired
    private ProjectNodeService projectNodeService;

    @Autowired
    private ProjectAuthService projectAuthService;

    /**
     * 系统设置/成员管理/访问授权/设置默认
     * @param mmap
     * @return
     */
    @PostMapping("/auth/apply")
    @ResponseBody
    public AjaxResult authApply(@RequestParam Map<String,Object> mmap) {

        String action = MapUtils.getString(mmap,"action");
        Integer id = MapUtils.getInteger(mmap,"id",-1);
        if("save".equals(action)){
            String nodesStr = MapUtils.getString(mmap,"nodes","[]");
            JSONArray jsonArray = JSON.parseArray(nodesStr);
            List<ProjectAuthNode> nodes = new ArrayList<ProjectAuthNode>();
            String [] nodesArr = null;
            if(StringUtils.isNotEmpty(jsonArray)){
                //nodesArr = nodesStr.split(",");
                ProjectAuthNode pan = null;
                for (Object obj : jsonArray) {
                    pan = new ProjectAuthNode();
                    pan.setAuth(id);pan.setNode(String.valueOf(obj));
                    nodes.add(pan);
                }
            }
            Integer result = projectAuthService.authApply(id,nodes);
            return AjaxResult.success("节点授权更新成功！",result);
        }else if("getnode".equals(action)){

            List<Map> allProjectNodeMap = projectNodeService.getAllProjectNode();
            List<String> ignore = new ArrayList(){{add("index");add("api");add("project/login");add("project/register");add("project/getCaptcha");}};
            List<String> checkedNode = projectAuthService.getProjectAuthNodeNodeByAuth(id);
            List<Map> allProjectNode = new ArrayList<>();
            if(CollectionUtils.isNotEmpty(allProjectNodeMap)){
                String node = null;
                String [] nodeSplit = null;
                for(Map m:allProjectNodeMap){
                    if(!ignore.contains(MapUtils.getString(m,"node"))){
                        node = MapUtils.getString(m,"node");
                        nodeSplit = node.split("/");
                        if(nodeSplit.length == 1){
                            m.put("pnode","");
                        }else if(nodeSplit.length == 2){
                            m.put("pnode",nodeSplit[0]);
                        }else if(nodeSplit.length == 3){
                            m.put("pnode",nodeSplit[0]+"/"+nodeSplit[1]);
                        }
                        allProjectNode.add(m);
                    }
                }
            }
            allProjectNodeMap = new ArrayList<>();
            String node = null;
            for(Map m:allProjectNode){
                node = MapUtils.getString(m,"node");
                if(checkedNode.contains(node)){
                    m.put("checked",true);
                }else{
                    m.put("checked",false);
                }
                m.put("key",MapUtils.getString(m,"node"));
                allProjectNodeMap.add(m);
            }
            List<Map> projectNodeList = CommUtils.listGetStree(allProjectNodeMap,"node","pnode");

            Map resultData = new HashMap();
            resultData.put("list",projectNodeList);
            List<String> checkedList = new ArrayList<>();
            for(String s:checkedNode){
                if(s.split("/").length>2){
                    checkedList.add(s);
                }
            }
            resultData.put("checkedList",checkedList);
            return  AjaxResult.success(resultData);
        }
        return AjaxResult.success();

    }
    /**
     * 系统设置/成员管理/访问授权/设置默认
     * @param mmap
     * @return
     */
    @PostMapping("/auth/setDefault")
    @ResponseBody
    public AjaxResult authSetDefault(@RequestParam Map<String,Object> mmap) {
        Integer id = MapUtils.getInteger(mmap,"id",-1);
        Integer is_default = MapUtils.getInteger(mmap,"is_default",0);
        ProjectAuth pa = new ProjectAuth();
        pa.setId(id);pa.setIs_default(is_default);
        Map loginMember = getLoginMember();
        String orgCode = MapUtils.getString(loginMember,"organizationCode");

        return AjaxResult.success(projectAuthService.setProjectAuthDefault(0,orgCode,pa));
    }

    /**
     * 系统设置/成员管理/访问授权/删除
     * @param mmap
     * @return
     */
    @PostMapping("/auth/del")
    @ResponseBody
    public AjaxResult authDel(@RequestParam Map<String,Object> mmap) {
        Integer id = MapUtils.getInteger(mmap,"id",-1);
        return AjaxResult.success(projectAuthService.authDelete(id));
    }

    /**
     * 系统设置/成员管理/访问授权
     * @param mmap
     * @return
     */
    @PostMapping("/auth/index")
    @ResponseBody
    public AjaxResult auth(@RequestParam Map<String,Object> mmap) {
        Map loginMember = getLoginMember();
        Integer page = MapUtils.getInteger(mmap,"page",1);
        Integer pageSize = MapUtils.getInteger(mmap,"pageSize",100);

        String orgCode = MapUtils.getString(loginMember,"organizationCode");

        IPage<Map> iPage = new Page<>();
        iPage.setCurrent(page);iPage.setSize(pageSize);
        IPage<Map> result = projectAuthService.gettProjectAuthByOrgCode(iPage,orgCode);
        List<Map> records = new ArrayList<>();
        if(null != result && result.getRecords()!=null){
            for(Map map:result.getRecords()){
                String type = MapUtils.getString(map,"type");
                if("admin".equals(type) || "member".equals(type)){
                    map.put("canDelete",0);
                }else{
                    map.put("canDelete",1);
                }
                records.add(map);
            }
        }
        Map resultData = new HashMap();
        resultData.put("list",records);
        resultData.put("total",result.getTotal());
        resultData.put("page",result.getCurrent());
        return AjaxResult.success(resultData);
    }

    /**
     * 系统设置/成员管理/访问授权 编辑保存
     * @param mmap
     * @return
     */
    @PostMapping("/auth/edit")
    @ResponseBody
    public AjaxResult authEdit(@RequestParam Map<String,Object> mmap)  throws Exception
    {
        Map loginMap=getLoginMember();
        String title = MapUtils.getString(mmap,"title");
        String desc = MapUtils.getString(mmap,"desc");
        Integer id = MapUtils.getInteger(mmap,"id",-1);

        ProjectAuth pa = new ProjectAuth();
        pa.setTitle(title);pa.setDesc(desc);pa.setId(id);
        return AjaxResult.success(projectAuthService.updateById(pa));
    }

    /**
     * 系统设置/成员管理/访问授权 添加保存
     * @param mmap
     * @return
     */
    @PostMapping("/auth/add")
    @ResponseBody
    public AjaxResult authAdd(@RequestParam Map<String,Object> mmap)  throws Exception
    {
        Map loginMap=getLoginMember();
        String title = MapUtils.getString(mmap,"title");
        String desc = MapUtils.getString(mmap,"desc");
        Integer status = MapUtils.getInteger(mmap,"status");
        Integer sort = MapUtils.getInteger(mmap,"sort");
        ProjectAuth pa = new ProjectAuth();
        pa.setTitle(title);pa.setDesc(desc);pa.setStatus(status);pa.setSort(sort);
        pa.setOrganization_code(MapUtils.getString(loginMap,"organizationCode"));
        pa.setCreate_at(DateUtil.formatDateTime(new Date()));
        pa.setIs_default(0);pa.setCreate_by(0);
        projectAuthService.save(pa);
        if("admin".equals(pa.getType()) || "member".equals(pa.getType())){
            pa.setCanDelete(0);
        }else{
            pa.setCanDelete(1);
        }
        return AjaxResult.success(pa);
    }

    /**
     * 系统设置	系统管理	菜单编辑
     * @param menuEdit
     * @return
     */
    @PostMapping("/menu/menuEdit")
    @ResponseBody
    public AjaxResult menuDel(ProjectMenu menuEdit) {
        if (menuEdit.getIs_inner()) {
            menuEdit.setIsinner(1);
        } else {
            menuEdit.setIsinner(0);
        }
        return AjaxResult.success("修改成功", projectMenuService.updateById(menuEdit));
    }

    /**
     * 系统设置	系统管理	菜单启用
     * @param mmap
     * @return
     */
    @PostMapping("/menu/menuDel")
    @ResponseBody
    public AjaxResult menuDel(@RequestParam Map<String,Object> mmap)  throws Exception
    {
        Integer id = MapUtils.getInteger(mmap,"id",-1);
        return AjaxResult.success(projectMenuService.menuDelete(id));
    }

    /**
     * 系统设置	系统管理	菜单启用
     * @param mmap
     * @return
     */
    @PostMapping("/menu/menuResume")
    @ResponseBody
    public AjaxResult menuResume(@RequestParam Map<String,Object> mmap)  throws Exception
    {
        Integer id = MapUtils.getInteger(mmap,"id");
        Integer status = MapUtils.getInteger(mmap,"status");
        return AjaxResult.success(projectMenuService.lambdaUpdate()
                .set(ProjectMenu::getStatus, status).eq(ProjectMenu::getId, id).update());
    }
    /**
     * 系统设置	系统管理	菜单禁用
     * @param mmap
     * @return
     */
    @PostMapping("/menu/menuForbid")
    @ResponseBody
    public AjaxResult menuForbid(@RequestParam Map<String,Object> mmap)  throws Exception
    {
        Integer id = MapUtils.getInteger(mmap,"id");
        Integer status = MapUtils.getInteger(mmap,"status");
        return AjaxResult.success(projectMenuService.lambdaUpdate()
                .set(ProjectMenu::getStatus, status).eq(ProjectMenu::getId, id).update());
    }

    /**
     * 系统设置	系统管理	菜单路由
     * @param mmap
     * @return
     */
    @PostMapping("/menu/menu")
    @ResponseBody
    public AjaxResult menuList(@RequestParam Map<String,Object> mmap)  throws Exception
    {
        return AjaxResult.success(projectMenuService.getAllProjectMenuTree());
    }

    /**
     * 系统设置	系统管理	菜单路由 添加菜单  节点初始化
     * @param mmap
     * @return
     */
    @PostMapping("/node/allList")
    @ResponseBody
    public AjaxResult nodeAllList(@RequestParam Map<String,Object> mmap)  throws Exception
    {
        String module = MapUtils.getString(mmap,"module");
        String node = MapUtils.getString(mmap,"node");
        return AjaxResult.success(projectNodeService.getProjectNodeByNodeLike(node));
    }

    @PostMapping("/node/clear")
    @ResponseBody
    public AjaxResult nodeClear(@RequestParam Map<String,Object> mmap)  throws Exception
    {
        return AjaxResult.success("无效节点清单成功！");
    }
    @PostMapping("/node/index")
    @ResponseBody
    public AjaxResult nodeIndex(@RequestParam Map<String,Object> mmap)  throws Exception
    {
        String module = MapUtils.getString(mmap,"module");
        List<Map> listNode = projectNodeService.getAllProjectNode();
        NodeUtils nus = new NodeUtils();
        nus.refreshNodeListsIgnore(listNode);
        List<Map> nodeTree = nus.builTree();
        List<Map> groups = new ArrayList<>();
        for(Map map:nodeTree){
            Map pnodeMap = new HashMap();
            String node = MapUtils.getString(map,"node","");
            String pnode = node.split("/")[0];
            if(node.equals(pnode)){
                pnodeMap.put("node",map);
            }
            pnodeMap.put("list",map);
            groups.add(pnodeMap);
        }
        Map result = new HashMap();
        result.put("nodes",nodeTree);
        result.put("groups",groups);
        return AjaxResult.success(result);
    }

    /**
     * 系统设置	系统管理	菜单路由 添加菜单  保存
     * @param mmap
     * @return
     */
    @PostMapping("/menu/menuAdd")
    @ResponseBody
    public AjaxResult menuMenuAdd(@RequestParam Map<String,Object> mmap)  throws Exception
    {
        String title = MapUtils.getString(mmap,"title");
        String url = MapUtils.getString(mmap,"url");
        String file_path = MapUtils.getString(mmap,"file_path");
        String node = MapUtils.getString(mmap,"node");
        Integer sort = MapUtils.getInteger(mmap,"sort");
        String params = MapUtils.getString(mmap,"params");
        String values = MapUtils.getString(mmap,"values");
        String icon = MapUtils.getString(mmap,"icon");
        String show_slider = MapUtils.getString(mmap,"show_slider");
        String is_inner = MapUtils.getString(mmap,"is_inner");
        Integer status = MapUtils.getInteger(mmap,"status");
        String dept = MapUtils.getString(mmap,"dept");
        Integer pid = MapUtils.getInteger(mmap,"pid");

        ProjectMenu pm = new ProjectMenu();
        pm.setTitle(title);pm.setUrl(url);pm.setFile_path(file_path);
        pm.setNode(node);pm.setSort(sort);pm.setParams(params);
        pm.set_values(values);pm.setIcon(icon);pm.setStatus(status);
        pm.setPid(pid);pm.setShowslider("true".equalsIgnoreCase(show_slider)?1:0);
        pm.setIsinner("true".equalsIgnoreCase(is_inner)?1:0);
        pm.setCreate_at(DateUtil.formatDateTime(new Date()));
        pm.setCreate_by(0);
        projectMenuService.save(pm);

        if(pm.getIsinner() == 0){pm.setInnerText("导航");}else if(pm.getIsinner() == 1){ pm.setInnerText("内页");}
        if(pm.getStatus() == 0){pm.setStatusText("禁用");}else if(pm.getStatus() == 1){ pm.setStatusText("使用中");}
        if((null != pm.getParams() && null != pm.get_values()) || !"".equals(pm.get_values())){
            pm.setFullUrl(pm.getUrl()+"/"+pm.get_values());
        }else {
            pm.setFullUrl(pm.getUrl());
        }
        pm.setValues(pm.get_values());
        return AjaxResult.success(pm);
    }
}
