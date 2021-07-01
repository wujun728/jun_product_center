package com.projectm.common;

import com.alibaba.fastjson.JSONObject;
import com.projectm.project.domain.ProjectMenu;

import java.util.ArrayList;
import java.util.List;

public class MenuUtils {

    private List<ProjectMenu> menuList = new ArrayList<ProjectMenu>();
    public MenuUtils(List<ProjectMenu> menuList) {
        this.menuList=menuList;
    }

    /**
     * 递归获取菜单编号
     * treeRoot:( ). <br/>
     * @author lishang
     * @param sourceList
     * @param rootMenu
     * @return
     */
    public static  List<Integer> getMenuIds(List<ProjectMenu> sourceList,ProjectMenu rootMenu)
    {
        List<Integer> result = new ArrayList<>();
        for (ProjectMenu menu : sourceList) {
            if(rootMenu.getId()==menu.getPid()){
                result.addAll(getMenuIds(sourceList, menu));
            }
        }
        return result;
    }

    //建立树形结构
    public List<ProjectMenu> builTree(){
        List<ProjectMenu> treeMenus =new  ArrayList<ProjectMenu>();
        for(ProjectMenu menuNode : getRootNode()) {
            menuNode=buildChilTree(menuNode);
            treeMenus.add(menuNode);
        }
        return treeMenus;
    }

    //递归，建立子树形结构
    private ProjectMenu buildChilTree(ProjectMenu pNode){
        List<ProjectMenu> chilMenus =new  ArrayList<ProjectMenu>();
        for(ProjectMenu menuNode : menuList) {
            if(menuNode.getPid().equals(pNode.getId())) {
                chilMenus.add(buildChilTree(menuNode));
            }
        }
        pNode.setChildren(chilMenus);
        return pNode;
    }

    //获取根节点
    private List<ProjectMenu> getRootNode() {
        List<ProjectMenu> rootMenuLists =new  ArrayList<ProjectMenu>();
        for(ProjectMenu menuNode : menuList) {
            if(menuNode.getPid()==0) {
                rootMenuLists.add(menuNode);
            }
        }
        return rootMenuLists;
    }

    public static void main(String[] args) {
        List<ProjectMenu> sourceList=new ArrayList<>();

        ProjectMenu menu=new ProjectMenu();
        menu.setPid(0);
        menu.setId(1);
        menu.setTitle("菜单一级");
        sourceList.add(menu);

        ProjectMenu menu12=new ProjectMenu();
        menu12.setPid(0);
        menu12.setId(12);
        menu12.setTitle("菜单一级12");
        sourceList.add(menu12);
        ProjectMenu menu13=new ProjectMenu();
        menu13.setPid(12);
        menu13.setId(13);
        menu13.setTitle("菜单二级13");
        sourceList.add(menu13);


        ProjectMenu menu2=new ProjectMenu();
        menu2.setPid(1);
        menu2.setId(2);
        menu2.setTitle("菜单二级1");
        sourceList.add(menu2);

        ProjectMenu menu3=new ProjectMenu();
        menu3.setPid(2);
        menu3.setId(3);
        menu3.setTitle("菜单三级");
        sourceList.add(menu3);

        ProjectMenu menu4=new ProjectMenu();
        menu4.setPid(3);
        menu4.setId(4);
        menu4.setTitle("菜单四级");
        sourceList.add(menu4);

        ProjectMenu menu5=new ProjectMenu();
        menu5.setPid(1);
        menu5.setId(6);
        menu5.setTitle("菜单二级2");
        sourceList.add(menu5);

        //ProjectMenu childrens = treeRoot(sourceList, menu);
        //System.out.println(JSONObject.toJSON(childrens));
        //System.out.println(treeMenuList(sourceList));
        MenuUtils mu = new MenuUtils(sourceList);
        System.out.println(JSONObject.toJSON(mu.builTree()));
    }
}
