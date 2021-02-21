package com.deer.wms.base.system.service.MESWebService;

import com.deer.wms.base.system.service.ServerVisitAddressService;
import com.deer.wms.base.system.service.bill.IBillOutDetailService;

import com.deer.wms.base.system.service.bill.impl.BillOutDetailServiceImpl;
import com.deer.wms.base.system.service.task.ITaskInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
@Service
@Lazy
public class MesWebServiceImpl implements MesWebService {
    @Autowired(required = true)
    private IBillOutDetailService iBillOutDetailService;
    @Override
    public WebserviceResponse macIntf(@WebParam(name = "methodInvoke") String methodInvoke,
                                      @WebParam(name = "input") String input){
        WebserviceResponse webServiceResponse = null;
        try{
            if(methodInvoke.equals("DownWipToStock")){
                webServiceResponse = iBillOutDetailService.downWipToStock(input);
            }else if(methodInvoke.equals("EmptyShelfArrive")){
                webServiceResponse = iBillOutDetailService.emptyShelfArrive(input);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return webServiceResponse;
    }
}
