package com.ruoyi.kbs.service.impl;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.WhetherStatus;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.kbs.domain.KbsDocumentComment;
import com.ruoyi.kbs.domain.KbsDocumentCommentLike;
import com.ruoyi.kbs.domain.qo.KbsDocumentCommentUpdateQo;
import com.ruoyi.kbs.domain.vo.KbsDocumentCommentVo;
import com.ruoyi.kbs.mapper.KbsDocumentCommentMapper;
import com.ruoyi.kbs.mapper.KbsSourceTargetMapper;
import com.ruoyi.kbs.service.IKbsDocumentCommentLikeService;
import com.ruoyi.kbs.service.IKbsDocumentCommentService;
import com.ruoyi.kbs.utils.TransferUtil;
import com.ruoyi.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 知识库文档评论Service业务层处理
 *
 * @author wocurr.com
 */
@Slf4j
@Service
public class KbsDocumentCommentServiceImpl implements IKbsDocumentCommentService {
    @Autowired
    private KbsDocumentCommentMapper kbsDocumentCommentMapper;
    @Autowired
    private IKbsDocumentCommentLikeService kbsDocumentCommentLikeService;
    @Autowired
    private ISysUserService sysUserService;

    /**
     * 查询知识库文档评论列表
     *
     * @param kbsDocumentComment 知识库文档评论
     * @return 知识库文档评论
     */
    @Override
    public List<KbsDocumentCommentVo> listKbsDocumentComment(KbsDocumentComment kbsDocumentComment) {
        Assert.notNull(kbsDocumentComment, "参数不能为空");
        Assert.hasText(kbsDocumentComment.getDocId(), "文档ID不能为空");
        List<KbsDocumentComment> kbsDocumentComments = kbsDocumentCommentMapper.selectKbsDocumentCommentList(kbsDocumentComment);
        if (CollectionUtils.isEmpty(kbsDocumentComments)) {
            return Collections.emptyList();
        }

        List<String> commentIds = kbsDocumentComments.stream()
                .map(KbsDocumentComment::getId).collect(Collectors.toList());
        // 查询子评论
        Map<String, List<KbsDocumentComment>> childrenCommentListMap = getChildrenComments(commentIds);
        // 查询点赞
        Map<String, List<KbsDocumentCommentLike>> commentLikeListMap = getCommentLikeListMap(commentIds);

        List<String> userIds = kbsDocumentComments.stream()
                .map(KbsDocumentComment::getCreateId).collect(Collectors.toList());
        // 查询创建用户信息
        Map<String, SysUser> createUserMap = getCreateUserMap(userIds);

        String userId = SecurityUtils.getUserId();
        List<KbsDocumentCommentVo> resultList = new ArrayList<>();
        kbsDocumentComments.forEach(comment -> {
            KbsDocumentCommentVo kbsDocumentCommentVo = KbsSourceTargetMapper.INSTANCE.convertDocumentComment2DocumentCommentVo(comment);
            List<KbsDocumentComment> children = childrenCommentListMap.get(comment.getId());
            kbsDocumentCommentVo.setChildren(handleChildComment(children));
            handlike(userId, kbsDocumentCommentVo, commentLikeListMap);
            kbsDocumentCommentVo.setUserAvatar(createUserMap.get(comment.getCreateId()).getAvatar());
            resultList.add(kbsDocumentCommentVo);
        });
        return resultList;
    }

    /**
     * 查询子评论列表
     *
     * @param kbsDocumentComment 知识库文档评论
     * @return
     */
    @Override
    public List<KbsDocumentCommentVo> listChildKbsDocumentComment(KbsDocumentComment kbsDocumentComment) {
        if (StringUtils.isBlank(kbsDocumentComment.getRootParentId())) {
            throw new BaseException("参数错误");
        }
        List<KbsDocumentComment> kbsDocumentComments = kbsDocumentCommentMapper.selectKbsDocumentCommentByRootParentId(kbsDocumentComment);
        if (CollectionUtils.isEmpty(kbsDocumentComments)) {
            return Collections.emptyList();
        }
        return handleChildComment(kbsDocumentComments);
    }

    /**
     * 新增知识库文档评论
     *
     * @param kbsDocumentComment 知识库文档评论
     * @return 结果
     */
    @Override
    public String saveKbsDocumentComment(KbsDocumentComment kbsDocumentComment) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        String id = IdUtils.fastSimpleUUID();
        kbsDocumentComment.setId(id);
        kbsDocumentComment.setCreateId(loginUser.getUserId());
        kbsDocumentComment.setCreateBy(loginUser.getUser().getNickName());
        kbsDocumentComment.setCreateTime(DateUtils.getNowDate());
        kbsDocumentCommentMapper.insertKbsDocumentComment(kbsDocumentComment);
        return id;
    }

    /**
     * 修改知识库文档评论
     *
     * @param kbsDocumentComment 知识库文档评论
     * @return 结果
     */
    @Override
    public int updateKbsDocumentComment(KbsDocumentComment kbsDocumentComment) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        kbsDocumentComment.setUpdateId(loginUser.getUserId());
        kbsDocumentComment.setUpdateBy(loginUser.getUser().getNickName());
        kbsDocumentComment.setUpdateTime(DateUtils.getNowDate());
        return kbsDocumentCommentMapper.updateKbsDocumentComment(kbsDocumentComment);
    }

    /**
     * 批量删除知识库文档评论（逻辑删除）
     *
     * @param ids 需要删除的知识库文档评论主键
     * @return 结果
     */
    @Override
    public int deleteKbsDocumentCommentByIds(String[] ids) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        KbsDocumentCommentUpdateQo qo = new KbsDocumentCommentUpdateQo();
        qo.setIds(TransferUtil.arrayToList(ids));
        qo.setDelFlag(WhetherStatus.YES.getCode());
        qo.setUpdateId(loginUser.getUserId());
        qo.setUpdateBy(loginUser.getUser().getNickName());
        qo.setUpdateTime(DateUtils.getNowDate());
        return kbsDocumentCommentMapper.updateDelFlagByIds(qo);
    }

    /**
     * 统计文档的评论数量
     *
     * @param docId 文档ID
     * @return 评论数量
     */
    @Override
    public Long statDocumentCommentNum(String docId) {
        return kbsDocumentCommentMapper.statDocumentCommentNum(docId);
    }

    /**
     * 获取用户信息
     *
     * @param userIds
     * @return
     */
    private Map<String, SysUser> getCreateUserMap(List<String> userIds) {
        List<SysUser> createUsers = sysUserService.selectByUserIds(userIds);
        return createUsers.stream()
                .collect(Collectors.toMap(SysUser::getUserId, sysUser -> sysUser));
    }

    /**
     * 查询子评论
     *
     * @param commentIds 评论ID列表
     * @return
     */
    private Map<String, List<KbsDocumentComment>> getChildrenComments(List<String> commentIds) {
        List<KbsDocumentComment> childrenComments = kbsDocumentCommentMapper.selectKbsDocumentCommentByParentIds(commentIds);
        return childrenComments.stream()
                .collect(Collectors.groupingBy(KbsDocumentComment::getRootParentId));
    }

    /**
     * 获取评论点赞列表
     *
     * @param commentIds
     * @return
     */
    private Map<String, List<KbsDocumentCommentLike>> getCommentLikeListMap(List<String> commentIds) {
        List<KbsDocumentCommentLike> kbsDocumentCommentLikes = kbsDocumentCommentLikeService.listKbsDocumentCommentLikeByCommentIds(commentIds);
        return kbsDocumentCommentLikes.stream()
                .collect(Collectors.groupingBy(KbsDocumentCommentLike::getCommentId));
    }

    /**
     * 处理点赞
     *
     * @param userId
     * @param kbsDocumentCommentVo
     * @param commentLikeListMap
     */
    private void handlike(String userId, KbsDocumentCommentVo kbsDocumentCommentVo, Map<String, List<KbsDocumentCommentLike>> commentLikeListMap) {
        List<KbsDocumentCommentLike> commentLikes = commentLikeListMap.get(kbsDocumentCommentVo.getId());
        if (CollectionUtils.isNotEmpty(commentLikes)) {
            List<String> likeUserIds = commentLikes.stream().map(KbsDocumentCommentLike::getCreateId).collect(Collectors.toList());
            boolean liseFlag = likeUserIds.contains(userId);
            kbsDocumentCommentVo.setLikeFlag(liseFlag);
            kbsDocumentCommentVo.setLikeNum((long) commentLikes.size());
        }
    }

    /**
     * 处理子评论
     *
     * @param kbsDocumentComments
     * @return
     */
    private List<KbsDocumentCommentVo> handleChildComment(List<KbsDocumentComment> kbsDocumentComments) {
        if (CollectionUtils.isEmpty(kbsDocumentComments)) {
            return Collections.emptyList();
        }
        //查询创建用户信息
        List<String> userIds = kbsDocumentComments.stream()
                .map(KbsDocumentComment::getCreateId).collect(Collectors.toList());
        Map<String, SysUser> createUserMap = getCreateUserMap(userIds);

        //查询点赞
        List<String> commentIds = kbsDocumentComments.stream()
                .map(KbsDocumentComment::getId).collect(Collectors.toList());
        Map<String, List<KbsDocumentCommentLike>> commentLikeListMap = getCommentLikeListMap(commentIds);

        String userId = SecurityUtils.getUserId();
        List<KbsDocumentCommentVo> resultList = new ArrayList<>();
        kbsDocumentComments.forEach(comment -> {
            KbsDocumentCommentVo kbsDocumentCommentVo = KbsSourceTargetMapper.INSTANCE.convertDocumentComment2DocumentCommentVo(comment);
            handlike(userId, kbsDocumentCommentVo, commentLikeListMap);
            kbsDocumentCommentVo.setUserAvatar(createUserMap.get(comment.getCreateId()).getAvatar());
            resultList.add(kbsDocumentCommentVo);
        });
        return resultList;
    }
}
