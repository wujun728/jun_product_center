package com.sanri.tools.modules.zookeeper.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sanri.tools.modules.core.service.file.FileManager;
import com.sanri.tools.modules.zookeeper.dtos.PathFavorite;
import com.sanri.tools.modules.zookeeper.dtos.PathFavoriteParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

/**
 * 扩展功能 : 收藏路径,几个重要的路径可以初始化加载
 */
@Service
@Slf4j
public class ZookeeperExtendService {
    // 路径收藏  connName ==> PathFavorite
    private Map<String, Set<PathFavorite>> pathFavorites = new HashMap<>();

    @Autowired
    FileManager fileManager;

    /**
     * 添加收藏 ,前端需要把所有的收藏全拿过来,后端直接覆盖
     * @param pathFavoriteParam
     */
    public void addFavorite(String connName, PathFavorite pathFavorite){
        Set<PathFavorite> pathFavorites = this.pathFavorites.computeIfAbsent(connName, k -> new LinkedHashSet<>());
        pathFavorites.add(pathFavorite);
        serializer();
    }

    public void removeFavorite(String connName,String name){
        Set<PathFavorite> pathFavorites = this.pathFavorites.computeIfAbsent(connName, k -> new LinkedHashSet<>());
        Iterator<PathFavorite> iterator = pathFavorites.iterator();
        while (iterator.hasNext()){
            PathFavorite next = iterator.next();
            if (next.getName().equals(name)){
                iterator.remove();break;
            }
        }

        serializer();
    }

    /**
     * 列出当前连接关注的路径列表
     * @param connName
     * @return
     */
    public Set<PathFavorite> favorites(String connName){
        Set<PathFavorite> pathFavorites = this.pathFavorites.computeIfAbsent(connName, k -> new LinkedHashSet<PathFavorite>());
        return pathFavorites;
    }

    /**
     * 序列化收藏列表到文件
     */
    private void serializer() {
        try {
            fileManager.writeConfig(ZookeeperService.module,"favorites", JSON.toJSONString(pathFavorites));
        } catch (IOException e) {
            log.error("zookeeper serializer favorites error : {}",e.getMessage(),e);
        }
    }

    @PostConstruct
    void loadFavorites(){
        try {
            String favorites = fileManager.readConfig(ZookeeperService.module, "favorites");
            TypeReference<Map<String,Set<PathFavorite>>> typeReference =  new TypeReference<Map<String,Set<PathFavorite>>>(){};
            this.pathFavorites = JSON.parseObject(favorites,typeReference);
        } catch (IOException e) {
            log.error("zookeeper load path favorites error : {}",e.getMessage(),e);
        }
    }
}
