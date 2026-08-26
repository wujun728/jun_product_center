package com.ruoyi.flowable.service;

import com.ruoyi.flowable.domain.FlowOption;
import com.ruoyi.flowable.domain.dto.FlowProcDefDto;
import com.ruoyi.flowable.domain.qo.FlowUpdateRouteXmlQo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 流程定义服务接口
 *
 * @author wocurr.com
 */
public interface IFlowDefinitionService {

    /**
     * 结束流程
     *
     * @param processDefinitionKey 流程定义key
     */
    boolean exist(String processDefinitionKey);


    /**
     * 流程定义列表
     *
     * @param name     流程定义名称
     * @param pageNum  当前页码
     * @param pageSize 每页条数
     * @return 流程定义分页列表数据
     */
    List<FlowProcDefDto> list(String name, Integer pageNum, Integer pageSize);

    /**
     * 保存流程文件
     * 当每个key的流程第一次部署时，指定版本为1。对其后所有使用相同key的流程定义，
     * 部署时版本会在该key当前已部署的最高版本号基础上加1。key参数用于区分流程定义
     *
     * @param name     流程定义名称
     * @param category 分类
     * @param xml      xml文件内容
     */
    void saveFile(String name, String category, String xml);

    /**
     * 导入流程文件
     * 当每个key的流程第一次部署时，指定版本为1。对其后所有使用相同key的流程定义，
     * 部署时版本会在该key当前已部署的最高版本号基础上加1。key参数用于区分流程定义
     *
     * @param name     流程定义名称
     * @param category 分类
     * @param file     文件流
     */
    void importFile(String name, String category, MultipartFile file);

    /**
     * 读取xml
     *
     * @param deployId 部署ID
     * @return xml文件内容
     */
    String readXml(String deployId);

    /**
     * 根据流程定义ID启动流程实例
     *
     * @param procDefId 流程定义ID
     * @param variables 流程变量
     */

    void startProcessInstanceById(String procDefId, Map<String, Object> variables);


    /**
     * 激活或挂起流程定义
     *
     * @param state    状态
     * @param deployId 流程部署ID
     */
    void updateState(Integer state, String deployId);


    /**
     * 删除流程定义
     *
     * @param procDefKey 流程定义key
     */
    void delete(String procDefKey);


    /**
     * 读取图片文件
     *
     * @param deployId 部署ID
     * @param response 响应对象
     */
    void readImage(String deployId, HttpServletResponse response);

    /**
     * 获取流程定义列表
     *
     * @return List<FlowOption> 流程定义列表
     */
    List<FlowOption> getOptionSelect();

    /**
     * 更新流程定义的在途流程
     *
     * @param qo 流程定义更新参数对象
     */
    void migrateByProcessDefinitionKey(FlowUpdateRouteXmlQo qo);
}
