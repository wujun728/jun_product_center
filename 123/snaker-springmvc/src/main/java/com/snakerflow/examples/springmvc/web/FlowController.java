/* Copyright 2013-2015 www.snakerflow.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.snakerflow.examples.springmvc.web;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;
import org.snaker.engine.access.Page;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.Process;
import org.snaker.engine.entity.HistoryOrder;
import org.snaker.engine.model.TaskModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.snakerflow.examples.springmvc.engine.SnakerEngineFacets;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yuqs
 * @since 2.0
 */
@Controller
@RequestMapping(value = "/snaker/flow")
public class FlowController {
    @Autowired
    private SnakerEngineFacets facets;
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "process")
    public String process(HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        Enumeration<String> paraNames = request.getParameterNames();
        while (paraNames.hasMoreElements()) {
            String element = paraNames.nextElement();
            int index = element.indexOf("_");
            String paraValue = request.getParameter(element);
            if(index == -1) {
                params.put(element, paraValue);
            } else {
                char type = element.charAt(0);
                String name = element.substring(index + 1);
                Object value = null;
                switch(type) {
                    case 'S':
                        value = paraValue;
                        break;
                    case 'I':
                        value = ConvertUtils.convert(paraValue, Integer.class);
                        break;
                    case 'L':
                        value = ConvertUtils.convert(paraValue, Long.class);
                        break;
                    case 'B':
                        value = ConvertUtils.convert(paraValue, Boolean.class);
                        break;
                    case 'D':
                        value = ConvertUtils.convert(paraValue, Date.class);
                        break;
                    case 'N':
                        value = ConvertUtils.convert(paraValue, Double.class);
                        break;
                    default:
                        value = paraValue;
                        break;
                }
                params.put(name, value);
            }
        }
        String processId = request.getParameter("processId");
        String orderId = request.getParameter("orderId");
        String taskId = request.getParameter("taskId");
        String nextOperator = request.getParameter("");
        if (StringUtils.isEmpty(orderId) && StringUtils.isEmpty(taskId)) {
            facets.startAndExecute(processId, "admin", params);
        } else {
            String methodStr = request.getParameter("method");
            int method;
            try {
                method = Integer.parseInt(methodStr);
            } catch(Exception e) {
                method = 0;
            }
            switch(method) {
                case 0://任务执行
                    facets.execute(taskId, "admin", params);
                    break;
                case -1://驳回、任意跳转
                    facets.executeAndJump(taskId, "admin", params, request.getParameter("nodeName"));
                    break;
                case 1://转办
                    if(StringUtils.isNotEmpty(nextOperator)) {
                        facets.transferMajor(taskId, "admin", nextOperator.split(","));
                    }
                    break;
                case 2://协办
                    if(StringUtils.isNotEmpty(nextOperator)) {
                        facets.transferAidant(taskId, "admin", nextOperator.split(","));
                    }
                    break;
                default:
                    facets.execute(taskId, "admin", params);
                    break;
            }
        }
        String ccOperator = request.getParameter("ccoperator");
        if(StringUtils.isNotEmpty(ccOperator)) {
            facets.getEngine().order().createCCOrder(orderId, ccOperator.split(","));
        }
        return "redirect:/snaker/task/active";
    }
    /**
     * 流程实例查询
     * @param model
     * @param page
     * @return
     */
    @RequestMapping(value = "order", method= RequestMethod.GET)
    public String order(Model model, Page<HistoryOrder> page) {
        facets.getEngine().query().getHistoryOrders(page, new QueryFilter());
        model.addAttribute("page", page);
        return "snaker/order";
    }

    /**
     * 抄送实例已读
     * @param id
     * @param url
     * @return
     */
    @RequestMapping(value = "ccread")
    public String ccread(String id, String url) {
    	String[] assignees = new String[]{"admin"};
        facets.getEngine().order().updateCCStatus(id, assignees);
        return "redirect:" + url;
    }

    /**
     * 通用的流程展现页面入口
     * 将流程中的各环节表单以tab+iframe方式展现
     */
    @RequestMapping(value = "all")
    public String all(Model model, String processId, String orderId, String taskId) {
		model.addAttribute("processId", processId);
		model.addAttribute("orderId", orderId);
		model.addAttribute("taskId", taskId);
        if(StringUtils.isNotEmpty(processId)) {
            model.addAttribute("process", facets.getEngine().process().getProcessById(processId));
        }
        if(StringUtils.isNotEmpty(orderId)) {
            model.addAttribute("order", facets.getEngine().query().getOrder(orderId));
        }
        if(StringUtils.isNotEmpty(taskId)) {
            model.addAttribute("task", facets.getEngine().query().getTask(taskId));
        }
        return "snaker/all";
    }

    /**
     * 节点信息以json格式返回
     * all页面以节点信息构造tab及加载iframe
     */
    @RequestMapping(value = "node")
    @ResponseBody
    public Object node(String processId) {
        Process process = facets.getEngine().process().getProcessById(processId);
        List<TaskModel> models = process.getModel().getModels(TaskModel.class);
        List<TaskModel> viewModels = new ArrayList<TaskModel>();
        for(TaskModel model : models) {
            TaskModel viewModel = new TaskModel();
            viewModel.setName(model.getName());
            viewModel.setDisplayName(model.getDisplayName());
            viewModel.setForm(model.getForm());
            viewModels.add(viewModel);
        }
        return viewModels;
    }
}
