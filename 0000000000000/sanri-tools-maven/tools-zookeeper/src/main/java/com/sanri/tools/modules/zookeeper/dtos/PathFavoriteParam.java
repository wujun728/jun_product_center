package com.sanri.tools.modules.zookeeper.dtos;

import lombok.Data;

import java.util.List;

@Data
public class PathFavoriteParam {
    private String connName;
    private List<PathFavorite> favorites;
}
