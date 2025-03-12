package com.jun.plugin.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.snaker.engine.SnakerEngine;
import org.snaker.engine.entity.Order;
import org.snaker.engine.entity.Process;
import org.snaker.engine.model.NodeModel;
import org.snaker.engine.model.ProcessModel;
import org.snaker.engine.model.TaskModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import com.jun.plugin.snakerflow.process.SnakerEngineFacets;

import cn.hutool.core.lang.Console;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SnakerEngineTest {
    @Autowired
    SnakerEngineFacets snakerEngineFacets;

    @Test
    public void initFlows() {
        String flows = snakerEngineFacets.initFlows();
        Assert.assertNotNull(flows);
    }

    @Test
    public void getAllProcess() {
        List<Process> allProcess = snakerEngineFacets.getAllProcess();
        Assert.assertNotNull(allProcess);
    }

    @Test
    public void getEngine() {
        SnakerEngine engine = snakerEngineFacets.getEngine();
        Assert.assertNotNull(engine);
    }

    @Test
    public void getAllProcessNames() {
        List<String> allProcessNames = snakerEngineFacets.getAllProcessNames();
        Console.log(allProcessNames);
        Assert.assertNotNull(allProcessNames);
    }

    @Test
    public void getProcessByOrderId() {
        snakerEngineFacets.initFlows();
        List<Process> allProcess = snakerEngineFacets.getAllProcess();
        if (!CollectionUtils.isEmpty(allProcess)) {
            Process process = allProcess.get(0);
            Order dm = snakerEngineFacets.startInstanceById(process.getId(), "apply.operator", null);
            Assert.assertNotNull(dm);
            List<Process> processByOrderId = snakerEngineFacets.getProcessByOrderId(dm.getId());
            Console.log(processByOrderId);
            Assert.assertNotNull(processByOrderId);
        }
    }
    

    @Test
    public void getAllProcessNames11() {
    	List<Process> allProcess = snakerEngineFacets.getAllProcess();
    	List<Process> p = snakerEngineFacets.getProcessByOrderId("orderid");
        if (!CollectionUtils.isEmpty(allProcess)) {
            Process process = allProcess.get(0);
            String processName = process.getName();
            ProcessModel model = process.getModel();
            List<NodeModel> nodeModel = model.getNodes();
            List<TaskModel> taskModel = model.getTaskModels();
            List<TaskModel> taskModelNext = taskModel.get(0).getNextModels(TaskModel.class);
//            Order dm = snakerEngineFacets.startInstanceById(process.getId(), "apply.operator", null);
//            Assert.assertNotNull(dm);
//            List<Process> processByOrderId = snakerEngineFacets.getProcessByOrderId(dm.getId());
//            Console.log(processByOrderId);
//            Assert.assertNotNull(processByOrderId);
        }
    }
    
    
//    @SuppressWarnings({ "unchecked", "rawtypes" })
//	@RequestMapping(value = "/task/approval", method = RequestMethod.GET)
//    @ApiOperation(value = "【审批任务】同意", tags = "流程引擎-任务")
//    public Response doApproval(String taskId, String flag,String taskOperatorFlag,String taskOperatorMsg,String operatorNextid,
//    		String operatorNext,String  processName,String processId) {
//    	// ************************************************************************************
//    	// snakerEngineFacets.getEngine().query().getTaskActorsByTaskId(task.getId());
//    	Task task = snakerEngineFacets.getEngine().query().getTask(taskId);
//    	task.getModel().getNextModels(TaskModel.class);
//    	Process process =snakerEngineFacets.getEngine().process().getProcessById(processId);
//        // String processName = process.getName();
//		log.info(JSON.toJSONString(processName));
//		
//		ProcessModel model = process.getModel();
//		log.info(JSON.toJSONString(model));
//		
//		List<NodeModel> nodeModel = model.getNodes();
//		log.info(JSON.toJSONString(nodeModel));
//		
//		List<TaskModel> taskModel = model.getTaskModels();
//		log.info(JSON.toJSONString(taskModel));
//		
//		for(TaskModel taskModel1 : taskModel) {
//			List<TaskModel> taskModelNext = taskModel1.getNextModels(TaskModel.class);
//			log.info("sub-"+JSON.toJSONString(taskModelNext));
//		}
//		// ************************************************************************************
//    	Map args = new HashMap(8);
//    	args.put("taskOperator", sessionService.getCurrentUsername());
//    	args.put("taskOperatorName", operatorNext);
////    	args.put("flag", 2);
//    	args.put("taskOperatorMsg", taskOperatorMsg);
//    	args.put("taskOperatorFlag", taskOperatorFlag);
//    	args.put("flag", Integer.valueOf(flag));
//    	List<Task> tasks = snakerEngineFacets.execute(taskId, sessionService.getCurrentUsername(), args);
//    	for (Task task : tasks) {
//           snakerEngineFacets.getEngine().task().addTaskActor(task.getId(), operatorNextid.split(","));
//        }
//        return Response.ok();
//    }
    
    
    public void testname() throws Exception {
    	// ************************************************************************************
    	// snakerEngineFacets.getEngine().query().getTaskActorsByTaskId(task.getId());
//    	Process process =snakerEngineFacets.getEngine().process().getProcessById(processId);
    	// String processName = process.getName();
//    	Task task1 = snakerEngineFacets.getEngine().query().getTask(taskId);
//    	Process process = snakerEngineFacets.getEngine().process().getProcessByName(processName);
//    	
//    	List<TaskModel> tm = process.getModel().getNode(task1.getTaskName()).getNextModels(TaskModel.class);
//    	if(tm.size() > 1 && operatorNextid!=null && operatorNextid.length() < 1) {
//    		return Response.error("1010", "流程审批人不能为空(驳回及非最终环节)！");
//    	}
//		log.info(JSON.toJSONString(processName));
//		
//		ProcessModel model = process.getModel();
//		log.info(JSON.toJSONString(model));
//		
//		List<NodeModel> nodeModel = model.getNodes();
//		log.info(JSON.toJSONString(nodeModel));
//		
//		List<TaskModel> taskModel = model.getTaskModels();
//		log.info(JSON.toJSONString(taskModel));
//		
//		for(TaskModel taskModel1 : taskModel) {
//			List<TaskModel> taskModelNext = taskModel1.getNextModels(TaskModel.class);
//			log.info("sub-"+JSON.toJSONString(taskModelNext));
//		}
//		// ************************************************************************************
//    	Map args = new HashMap(8);
//    	args.put("taskOperator", sessionService.getCurrentUsername());
//    	args.put("taskOperatorName", operatorNext);
//    	args.put("taskOperatorMsg", taskOperatorMsg);
//    	args.put("taskOperatorFlag", taskOperatorFlag);
//    	args.put("flag", Integer.valueOf(flag));
//    	List<Task> tasks = snakerEngineFacets.execute(taskId, sessionService.getCurrentUsername(), args);
//    	for (Task task : tasks) {
//           snakerEngineFacets.getEngine().task().addTaskActor(task.getId(), operatorNextid.split(","));
//        }
//        return Response.ok();
	}
 

}