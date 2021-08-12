package com.sanri.tools.modules.zookeeper.controller;

import com.sanri.tools.modules.zookeeper.dtos.PathFavorite;
import com.sanri.tools.modules.zookeeper.dtos.ZooNodeACL;
import com.sanri.tools.modules.zookeeper.service.ZookeeperExtendService;
import com.sanri.tools.modules.zookeeper.service.ZookeeperService;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/zookeeper")
@Validated
public class ZookeeperController {

    @Autowired
    private ZookeeperService zookeeperService;

    @Autowired
    private ZookeeperExtendService zookeeperExtendService;

    /**
     * 添加路径收藏
     * @param connName
     * @param name
     * @param path
     */
    @PostMapping("/addFavorite")
    public void addFavorite(@NotNull String connName, @NotNull String name, @NotNull String path){
        PathFavorite pathFavorite = new PathFavorite(name, path);
        zookeeperExtendService.addFavorite(connName,pathFavorite);
    }

    @PostMapping("/removeFavorite")
    public void removeFavorite(@NotNull String connName,@NotNull String name){
        zookeeperExtendService.removeFavorite(connName,name);
    }

    /**
     * 列出收藏夹
     * @param connName
     * @return
     */
    @GetMapping("/favorites")
    public Set<PathFavorite> favorites(@NotNull String connName){
        return zookeeperExtendService.favorites(connName);
    }

    @GetMapping("/childrens")
    public List<String> childrens(@NotNull String connName, @NotNull String path) throws IOException {
        return zookeeperService.childrens(connName,path);
    }

    @GetMapping("/meta")
    public Stat meta(@NotNull String connName, @NotNull String path) throws IOException{
        return zookeeperService.meta(connName,path);
    }

    @GetMapping("/acls")
    public List<ZooNodeACL> acls(@NotNull String connName, @NotNull String path) throws IOException{
        return zookeeperService.acls(connName,path);
    }

    @GetMapping("/readData")
    public Object readData(@NotNull String connName,@NotNull String path,String deserialize) throws IOException{
        return zookeeperService.readData(connName,path,deserialize);
    }

    @PostMapping("/deleteNode")
    public void deleteNode(@NotNull String connName,@NotNull String path) throws IOException{
        zookeeperService.deleteNode(connName,path);
    }

    @PostMapping("/writeData")
    public void writeData(@NotNull String connName,@NotNull String path,@NotNull String data) throws IOException {
        zookeeperService.writeData(connName,path,data);
    }
}
