package com.projectm.project.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.projectm.project.domain.ProjectNode;
import com.projectm.project.mapper.ProjectNodeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class ProjectNodeService extends ServiceImpl<ProjectNodeMapper, ProjectNode> {

    public List<Map> getProjectNodeByNodeLike(String node) {

        return baseMapper.selectProjectNodeByNodeLike(node);
    }

    public List<Map> getAllProjectNode() {
        return baseMapper.selectAllProjectNode();
    }

    public void get(String module) {
        if ("project".equals(module)) {

        }
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean saveNode(String jsonList) {
        List<Map> maps = JSON.parseArray(jsonList, Map.class);
        maps.forEach(o -> {
            ProjectNode.ProjectNodeBuilder node = ProjectNode.builder().node(o.get("node").toString());
            List<Map> name = (List<Map>) o.get("name");
            List<Map> value = (List<Map>) o.get("value");
            for (int i = 0; i < name.size(); i++) {
                String auth = name.get(i).get("name").toString();
                Boolean authVal = (Boolean) value.get(i).get("value");
                if (Objects.equals("is_auth", auth)) {
                    node.is_auth(authVal ? 1 : 0);
                } else {
                    node.is_login(authVal ? 1 : 0);
                }
            }
            ProjectNode build = node.build();
            LambdaUpdateChainWrapper<ProjectNode> wrapper = lambdaUpdate().eq(ProjectNode::getNode, build.getNode());
            if (build.getIs_auth() != null){
                wrapper.set(ProjectNode::getIs_auth, build.getIs_auth());
            }
            if (build.getIs_login() != null){
                wrapper.set(ProjectNode::getIs_login, build.getIs_login());
            }
            boolean update = wrapper.update();
            log.info("节点：{},保存：{}", build.getNode(), update);
        });
        return true;
    }
}
