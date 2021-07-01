package com.projectm.common;

import com.alibaba.fastjson.JSONObject;
import com.projectm.project.domain.ProjectMenu;
import org.apache.commons.collections.MapUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NodeUtils {

    private List<Map> nodeList = new ArrayList<Map>();

    /**
     * 递归获取菜单编号
     * treeRoot:( ). <br/>
     * @author lishang
     * @param sourceList
     * @param rootMenu
     * @return
     */
    public  List<Integer> getMenuIds(List<Map> sourceList,Map rootMenu)
    {
        List<Integer> result = new ArrayList<>();
        for (Map menu : sourceList) {
            if(MapUtils.getString(rootMenu,"node","")==MapUtils.getString(menu,"pnode")){
                result.addAll(getMenuIds(sourceList, menu));
            }
        }
        return result;
    }
    public void refreshNodeListsIgnore(List<Map> nodeListMaps){
        List<String> ignore = new ArrayList(){{add("index");add("api");add("project/login");add("project/register");add("project/getCaptcha");}};
        String node = null;
        String [] nodeSplit = null;
        for(Map m:nodeListMaps){
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
                nodeList.add(m);
            }
        }
    }

    //建立树形结构
    public List<Map> builTree(){
        List<Map> treeMenus =new  ArrayList<Map>();
        for(Map menuNode : getRootNode()) {
            menuNode=buildChilTree(menuNode);
            treeMenus.add(menuNode);
        }
        return treeMenus;
    }

    //递归，建立子树形结构
    private Map buildChilTree(Map pNode){
        List<Map> chilMenus =new  ArrayList<Map>();
        for(Map menuNode : nodeList) {
            if(MapUtils.getString(menuNode,"pnode","").equals(MapUtils.getString(pNode,"node"))) {
                chilMenus.add(buildChilTree(menuNode));
            }
        }
        if(chilMenus.size()>0){
            pNode.put("children",chilMenus);
        }
        return pNode;
    }

    //获取根节点
    private List<Map> getRootNode() {
        List<Map> rootMenuLists =new  ArrayList<Map>();
        for(Map menuNode : nodeList) {
            if("project".equals(MapUtils.getString(menuNode,"node"))) {
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
        //NodeUtils mu = new NodeUtils(sourceList);
        //System.out.println(JSONObject.toJSON(mu.builTree()));
    }
}
