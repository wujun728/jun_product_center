package com.deer.wms.base.system.service.MESWebService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface MesWebService {
    @WebMethod(operationName = "macIntf")
    @WebResult(name="webServiceResponse")
    WebserviceResponse macIntf(@WebParam(name = "methodInvoke") String methodInvoke,
                               @WebParam(name = "input") String input);
}
