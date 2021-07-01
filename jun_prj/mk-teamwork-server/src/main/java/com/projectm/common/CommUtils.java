package com.projectm.common;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class CommUtils {
    public static final String loginMember = "LOGIN_MEMBER";//记录登录用户的常量名

    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "page";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    public static String getUUID(){
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString().replaceAll("-","");
        // 去掉"-"符号
        String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
        return str;
    }

    public static Map getMapField(Map map, String [] fields){
        Map result = new HashMap();
        for(String field:fields){
            result.put(field, MapUtils.getString(map,field));
        }
        return result;
    }

    public static List stringArrayToList(String[] str){
        List<String> result = new ArrayList<>();
        if(null != str){
            for(String s : str){
                result.add(s);
            }
        }
        return result;
    }

    public static List<String> getListStringForListMapField(List<Map> list,String key){
        List<String> result = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(list)){
            for(Map m:list){
                result.add(String.valueOf(m.get(key)));
            }
        }
        return result;
    }

    public static List<Map> listGetStree(List<Map> list,String node,String pnode) {
        List<Map> treeList = new ArrayList<Map>();
        for (Map tree : list) {
            //找到根
            if ("".equals(MapUtils.getString(tree,pnode))) {
                treeList.add(tree);
            }
            //找到子
            for (Map treeNode : list) {
                //if (treeNode.getPid() == tree.getId()) {
                if (MapUtils.getString(treeNode,pnode).equals(MapUtils.getString(tree,node))) {
                    if (MapUtils.getObject(tree,"children") == null) {
                        tree.put("children",new ArrayList<Map>());
                    }
                    List tmp = (List)tree.get("children");
                    tmp.add(treeNode);
                    tree.put("children",tmp);
                }
            }
        }
        return treeList;
    }

    public static boolean isEmpty(String str){
        return str == null || "".equals(str);
    }

    /**
     * 大陆号码或香港号码均可
     */
    public static boolean isPhoneLegal(String str) throws PatternSyntaxException {
        return isChinaPhoneLegal(str) || isHKPhoneLegal(str);
    }

    /**
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 145,147,149
     * 15+除4的任意数(不要写^4，这样的话字母也会被认为是正确的)
     * 166
     * 17+3,5,6,7,8
     * 18+任意数
     * 198,199
     */
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        // ^ 匹配输入字符串开始的位置
        // \d 匹配一个或多个数字，其中 \ 要转义，所以是 \\d
        // $ 匹配输入字符串结尾的位置
        String regExp = "^((13[0-9])|(14[5,7,9])|(15[0-3,5-9])|(166)|(17[3,5,6,7,8])" +
                "|(18[0-9])|(19[8,9]))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 香港手机号码8位数，5|6|8|9开头+7位任意数
     */
    public static boolean isHKPhoneLegal(String str) throws PatternSyntaxException {
        // ^ 匹配输入字符串开始的位置
        // \d 匹配一个或多个数字，其中 \ 要转义，所以是 \\d
        // $ 匹配输入字符串结尾的位置
        String regExp = "^(5|6|8|9)\\d{7}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
