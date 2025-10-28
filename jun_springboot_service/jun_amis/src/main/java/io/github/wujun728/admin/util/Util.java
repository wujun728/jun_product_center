package io.github.wujun728.admin.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import io.github.wujun728.admin.common.TreeData;
import io.github.wujun728.admin.common.constants.Constants;
import io.github.wujun728.admin.common.data.Obj;
import io.github.wujun728.admin.page.constants.RefType;
import io.github.wujun728.admin.page.data.Form;
import io.github.wujun728.admin.page.data.Page;
import io.github.wujun728.admin.page.data.PageRef;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
public class Util {


    public static boolean exists(Integer count) {
        return count != null && count > 0;
    }

    public static boolean isTest() {
        return !Objects.equals("prod", SpringUtil.getActiveProfile());
    }

    public static void writeResponse(HttpServletResponse response, Object obj) {
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        try {
            response.getWriter().print(JSONUtil.toJsonStr(obj));
            response.getWriter().flush();
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
    }

    /***
     * 构建树状结构
     * @param list
     * @param <T>
     * @return
     */
    public static <T extends TreeData<T>> List<T> buildTree(List<T> list){
        //根节点
        List<T> roots = new ArrayList<>();

        //全部节点id
        Set<Long> allIds = new HashSet<>();

        //父节点id:子节点id列表
        Map<Long,Set<Long>> idChildren = new HashMap<>();

        //id:节点对象
        Map<Long,T> idMap = new HashMap<>();
        for(T t:list){
            allIds.add(t.getId());
            idMap.put(t.getId(),t);
            //如果有父节点,将自己加入父节点的子节点列表里面
            if(t.getParentId() != null){
                if(!idChildren.containsKey(t.getParentId())){
                    idChildren.put(t.getParentId(),new HashSet<>());
                }
                idChildren.get(t.getParentId()).add(t.getId());
            }
        }

        for(T t:list){
            //父节点为空或者父节点id不在id列表里面,即为根节点
            if(t.getParentId() == null || !allIds.contains(t.getParentId())){
                roots.add(t);
            }
        }
        for(Map.Entry<Long,Set<Long>> entry:idChildren.entrySet()){
            T parent = idMap.get(entry.getKey());
            if(parent != null){
                //组装子节点
                for(Long childId:entry.getValue()){
                    parent.getChildren().add(idMap.get(childId));
                }
                //子节点排序
                Collections.sort(parent.getChildren());
            }
        }

        //根节点排序
        Collections.sort(roots);
        return roots;
    }
    public static void addRowNum(List<Map<String,Object>> list, int page,int perPage){
        int start = (page - 1) * perPage;
        addRowNum(list,new Obj<>(start));
    }
    private static void addRowNum(List<Map<String,Object>> list, Obj<Integer> rowNum){
        if(list == null){
            return;
        }
        for(Map<String,Object> item:list){
            Integer curValue = rowNum.getValue()+1;
            item.put("rowNum",curValue);
            rowNum.setValue(curValue);
            List<Map<String,Object>> children = (List<Map<String, Object>>) item.get("children");
            addRowNum(children,rowNum);
        }
    }

    public static <T> T clone(T obj){
        if(obj instanceof List && !((List<?>) obj).isEmpty()){
            return (T) JSONUtil.toList(JSONUtil.toJsonStr(obj),((List<?>) obj).get(0).getClass());
        }else{
            return (T) JSONUtil.toBean(JSONUtil.toJsonStr(obj),obj.getClass());
        }
    }
    public static String dateFormat(Object value,String format){
        if(value == null){
            return null;
        }
        if(value instanceof LocalDateTime){
            return DateUtil.format((LocalDateTime) value,format);
        }else if(value instanceof java.sql.Date){
            return DateUtil.format((java.sql.Date) value,format);
        }else if(value instanceof java.util.Date){
            return DateUtil.format((java.util.Date) value,format);
        }else{
            return value.toString();
        }
    }

    public static String getPageTitle(Page page){
        return getPageTitle(page.getName(),page.getCode());
    }
    public static String getPageTitle(String name,String code){
        return StrUtil.format("<a href='/page/static/sys/page?code={}' target='_blank'>{}</a>"
                ,code
                ,name);
    }
    public static String getFormTitle(Form form){
        return getFormTitle(form.getName(),form.getCode());
    }
    public static String getFormTitle(String name,String code){
        return StrUtil.format("<a href='/page/static/sys/form?code={}' target='_blank'>{}</a>"
                ,code
                ,name);
    }
    public static String getDic(String code){
        return StrUtil.format("<a href='/crud/dic?{}dicCode={}' target='_blank'>{}</a>"
                , Constants.QUERY_KEY_START,code
                ,code);
    }

    public static String getRefTitle(PageRef ref){
        if(RefType.Page.equals(ref.getRefType())){
            return getPageTitle(ref.getRefName(),ref.getRefPageCode());
        }
        if(RefType.Form.equals(ref.getRefType())){
            return getFormTitle(ref.getRefName(),ref.getRefPageCode());
        }
        return ref.getRefName()+"-"+ref.getRefPageCode();
    }
}
