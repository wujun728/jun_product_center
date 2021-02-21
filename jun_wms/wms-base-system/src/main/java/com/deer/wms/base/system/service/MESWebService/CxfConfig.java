package com.deer.wms.base.system.service.MESWebService;

import com.deer.wms.base.system.model.ServerVisitAddress;
import com.deer.wms.base.system.service.ServerVisitAddressService;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class CxfConfig {
    @Autowired
    private MesWebService mesWebService;
    @Autowired
    private ServerVisitAddressService serverVisitAddressService;
    @Bean
    public Endpoint getEndpoint() {
        ServerVisitAddress serverVisitAddress = serverVisitAddressService.findAddressById(2);
        String ipAddress = serverVisitAddress.getVisitAddress();
        Endpoint publish = EndpointImpl.publish(ipAddress,mesWebService);
        return publish;
    }
}
