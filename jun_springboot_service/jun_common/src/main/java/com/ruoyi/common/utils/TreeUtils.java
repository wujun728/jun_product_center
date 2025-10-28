package com.ruoyi.common.utils;

import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.constant.TreeConstants;
import com.ruoyi.common.core.domain.DictTreeEntity;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.spring.SpringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 字典工具类
 *
 * @author wangzongrun
 */
public class TreeUtils {

    /**
     * 设置cache key
     *
     * @param entities 参数键
     * @return 缓存键key
     */
    public List<Object> buildTree(List<?> entities) {

        List<Object> returnList = new ArrayList<>();
        for (Iterator<?> iterator = entities.iterator(); iterator.hasNext(); ) {
            Object obj = iterator.next();
            DictTreeEntity entity = (DictTreeEntity) obj;
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (TreeConstants.PARENT_ROOT_VALUE.equals(entity.getPid())) {
                recursionFn(entities, obj);
                returnList.add(obj);
            }
        }
        if (returnList.isEmpty()) {
            entities.forEach(item -> {
                returnList.add(item);
            });
        }

        return returnList;
    }

    /**
     * 递归列表
     */
    private void recursionFn(List<?> list, Object node) {
        DictTreeEntity treeEntity = (DictTreeEntity) node;
        // 得到子节点列表， 并赋值给node节点
        List<?> childList = getChildList(list, node);
        treeEntity.setChildren(childList);

        // 递归遍历
        for (Object tChild : childList) {
            if (hasChild(list, tChild)) {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<?> getChildList(List<?> list, Object t) {
        DictTreeEntity treeEntity = (DictTreeEntity) t;
        List<Object> tlist = new ArrayList<Object>();
        Iterator<?> it = list.iterator();
        while (it.hasNext()) {
            Object obj = it.next();
            DictTreeEntity n = (DictTreeEntity) obj;
            if (StringUtils.isNotNull(n.getPid()) && n.getPid().equals(treeEntity.getId())) {
                tlist.add(obj);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<?> list, Object t) {
        return getChildList(list, t).size() > 0 ? true : false;
    }

    /**
     * 获取下一个编码
     */
    public static String genNextLevelCode(String maxLevelCode, String parentLevelCode, String initCode, Integer step) {
        String nextCode = "";
        if (StringUtils.isNotEmpty(maxLevelCode)) {
            List<String> levelCodeList = Stream.of(maxLevelCode.split("\\" + TreeConstants.LAYOUT_CODE_SEPARATOR)).collect(Collectors.toList());
            String lastLevelCode = null;

            if (levelCodeList.size() > 0) {
                lastLevelCode = String.valueOf(Integer.valueOf(levelCodeList.get(levelCodeList.size() - 1)) + step);
            } else {
                lastLevelCode = String.valueOf(Integer.valueOf(maxLevelCode) + step);
            }
            nextCode = maxLevelCode.substring(0, maxLevelCode.length() - lastLevelCode.length()) + lastLevelCode;

        } else {
            nextCode = (StringUtils.isEmpty(parentLevelCode) ? "" : parentLevelCode + TreeConstants.LAYOUT_CODE_SEPARATOR) + initCode;
        }
        return nextCode;
    }

    /**
     * 获取层次
     */
    public static Integer genLevelDepth(String levelCode) {
        return levelCode.split("\\" + TreeConstants.LAYOUT_CODE_SEPARATOR).length;
    }

    /**
     * 获取排序
     */
    public static Integer genOrderNum(String levelCode) {
        List<String> levelCodeList = Stream.of(levelCode.split("\\" + TreeConstants.LAYOUT_CODE_SEPARATOR)).collect(Collectors.toList());
        if (levelCodeList.size() > 0) {
            return Integer.valueOf(levelCodeList.get(levelCodeList.size() - 1));
        } else {
            return Integer.valueOf(levelCode);
        }
    }

    /**
     * 获取编码
     */
    public static String genCode(String levelCode) {
        return levelCode.replace(TreeConstants.LAYOUT_CODE_SEPARATOR, "");
    }

    /**
     * 获取编码
     */
    public static boolean isParentNode(String pLevelCode, String levelCode) {
        Integer pLen = pLevelCode.length();
        Integer len = levelCode.length();

        if (pLen >= len) {
            return false;
        }

        if (levelCode.startsWith(pLevelCode)) {
            pLevelCode += TreeConstants.LAYOUT_CODE_SEPARATOR;
            String temp = levelCode.replace(pLevelCode, "");
            String[] temps = temp.split("\\" + TreeConstants.LAYOUT_CODE_SEPARATOR);

            if (temps.length > 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * 树形结构数据到redis缓存中
     */
    public static void initCacheTreeData(String key, List<Object> node) {
        // 存储到redis
        SpringUtils.getBean(RedisCache.class).setCacheObject(getCacheKey(key), node);
    }

    /**
     * 设置cache key
     * @param configKey 参数键
     * @return 缓存键key
     */
    public static String getCacheKey(String configKey) {
        return CacheConstants.TREE_DICT_KEY+configKey;
    }
}
