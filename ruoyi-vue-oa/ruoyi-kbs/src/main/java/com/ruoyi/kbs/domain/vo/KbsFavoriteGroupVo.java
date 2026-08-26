package com.ruoyi.kbs.domain.vo;

import com.ruoyi.kbs.domain.KbsFavorite;
import lombok.Data;

import java.util.List;

/**
 * 知识库收藏组对象
 *
 * @author wocurr.com
 */
@Data
public class KbsFavoriteGroupVo {

    /**
     * 收藏组ID
     */
    private String groupId;

    /**
     * 收藏组名称
     */
    private String groupName;

    /**
     * 收藏列表
     */
    private List<KbsFavorite> favorites;
}
