package com.ruoyi.flowable.service.user.impl;

import cn.hutool.core.collection.CollUtil;
import com.ruoyi.common.enums.CommonStatusEnum;
import com.ruoyi.common.utils.collection.CollectionUtils;
import com.ruoyi.flowable.service.user.PostApi;
import com.ruoyi.system.domain.SysPost;
import com.ruoyi.system.service.ISysPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.ruoyi.common.exception.util.ServiceExceptionUtil.exception;
import static com.ruoyi.flowable.core.enums.user.SysErrorCodeConstants.POST_NOT_ENABLE;
import static com.ruoyi.flowable.core.enums.user.SysErrorCodeConstants.POST_NOT_FOUND;

/**
 * 岗位 API 实现类
 * <p>
 * hasPermi
 */
@Service
public class PostApiImpl implements PostApi {

    @Autowired
    private ISysPostService postService;

    @Override
    public void validPosts(Collection<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return;
        }
        // 获得岗位信息
        List<SysPost> posts = postService.listByIds(ids);
        Map<Long, SysPost> postMap = CollectionUtils.convertMap(posts, SysPost::getPostId);
        // 校验
        ids.forEach(id -> {
            SysPost post = postMap.get(id);
            if (post == null) {
                throw exception(POST_NOT_FOUND);
            }
            if (!CommonStatusEnum.ENABLE.getStatus().equals(Integer.parseInt(post.getStatus()))) {
                throw exception(POST_NOT_ENABLE, post.getPostName());
            }
        });
    }
}
