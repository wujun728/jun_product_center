package com.deer.wms.base.system.web.EBSVisitInterface;


import com.deer.wms.base.system.service.box.IBoxItemService;
import com.deer.wms.common.core.result.CommonCode;
import com.deer.wms.common.core.result.Result;

import com.deer.wms.common.exception.ServiceException;
import com.deer.wms.framework.util.MyUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/wms")
public class EbsVisitInterfaceController {

    /**
     *  EBS查询接口
     */
    @PostMapping("/dataInteraction")
    @ResponseBody
    public Result selectInventory(HttpServletRequest request){
        try {
//            String wholeStr = MyUtils.analysisHttpServletRequest(request);
//            JSONObject jsonObject = JSONObject.parseObject(wholeStr);
//            String methodName = jsonObject.get("methodName") == null ? "" : jsonObject.get("methodName").toString().trim();
//            //EBS查询库存接口
//            if (methodName.equals("selectStockSupplyforEbs")) {
//                String itemCode = jsonObject.get("itemCode") == null ? "" : jsonObject.get("itemCode").toString().trim();
//                String itemState = jsonObject.get("itemState") == null ? "" : jsonObject.get("itemState").toString().trim();
//                String batch = jsonObject.get("batch") == null ? "" : jsonObject.get("batch").toString().trim();
//                String exp = jsonObject.get("exp") == null ? "" : jsonObject.get("exp").toString().trim();
//                BoxItemCriteria boxItemCriteria = new BoxItemCriteria();
//                boxItemCriteria.setItemCode(itemCode);
//                boxItemCriteria.setItemState(itemState);
//                boxItemCriteria.setBatch(batch);
//                boxItemCriteria.setExp(exp);
//                List<EbsSelectInventory> lists = boxItemService.ebsSelectInventory(boxItemCriteria);
//                return ResultGenerator.genSuccessResult(lists);
//            }
        }catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(CommonCode.SERVER_INERNAL_ERROR);
        }
        return null;
    }
}
