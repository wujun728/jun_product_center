package com.projectm.system.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.framework.common.constant.Constants;
import com.framework.security.util.UserUtil;
import com.projectm.common.DateUtil;
import com.projectm.login.entity.LoginUser;
import com.projectm.system.domain.Notify;
import com.projectm.system.mapper.NotifyMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class NotifyService extends ServiceImpl<NotifyMapper, Notify> {

    //获取用户所在的所有组织信息
    public IPage<Map> getAllOrganizationByMemberCode(IPage<Map> page, Map params){
        return baseMapper.getAllNotifyByParams(page,params);
    }

    @Transactional
    public void setReadied(List<Integer> idList){
        if(CollectionUtils.isNotEmpty(idList)){
            List<Notify> notifyList = new ArrayList<>();
            for(Integer id:idList){
                notifyList.add(Notify.builder()
                .id(id)
                .is_read(1)
                .read_time(DateUtil.getCurrentDateTime())
                .build());
            }
            if(CollectionUtils.isNotEmpty(notifyList)){
                updateBatchById(notifyList);
            }
        }
    }
    @Transactional
    public void batchDel(List<Integer> idList){
        if(CollectionUtils.isNotEmpty(idList)){
            lambdaUpdate().in(Notify::getId,idList).remove();
        }
    }


    public Map<String, Object> getNoReads() {
        LoginUser loginUser = UserUtil.getLoginUser();
        //获取通知信息  is_read: 0 未读  1 已读
        List<Notify> list = lambdaQuery().eq(Notify::getIs_read, "0").eq(Notify::getTo, loginUser.getUser().getCode()).last("limit 100").list();
        List<Notify> message = list.parallelStream().filter(o -> StrUtil.equals(Constants.MESSAGE, o.getType())).sorted(Comparator.comparing(Notify::getCreate_time).reversed()).collect(Collectors.toList());
        List<Notify> notice = list.parallelStream().filter(o -> StrUtil.equals(Constants.NOTICE, o.getType())).sorted(Comparator.comparing(Notify::getCreate_time).reversed()).collect(Collectors.toList());
        List<Notify> task = list.parallelStream().filter(o -> StrUtil.equals(Constants.TASK, o.getType())).sorted(Comparator.comparing(Notify::getCreate_time).reversed()).collect(Collectors.toList());
        int messageSum = CollUtil.isNotEmpty(message) ? message.size() : 0;
        int noticeSum = CollUtil.isNotEmpty(notice) ? notice.size() : 0;
        int taskSum = CollUtil.isNotEmpty(task) ? task.size() : 0;
        int total = messageSum + noticeSum + taskSum;
        Map<String, Object> result = new HashMap<>(8);
        Map<String, Object> listMap = new HashMap<>(8);
        listMap.put("notice", CollUtil.isNotEmpty(notice) && notice.size() > 5 ? notice.subList(0, 5) : notice);
        listMap.put("message", CollUtil.isNotEmpty(message) && message.size() > 5 ? message.subList(0, 5) : message);
        listMap.put("task", CollUtil.isNotEmpty(task) && task.size() > 5 ? task.subList(0, 5) : task);
        Map<String, Object> totalSum = new HashMap<>(8);
        totalSum.put("notice", noticeSum);
        totalSum.put("message", messageSum);
        totalSum.put("task", taskSum);
        //返回数据
        result.put("list", listMap);
        result.put("total", total);
        result.put("totalSum", totalSum);
        return result;
    }
}
