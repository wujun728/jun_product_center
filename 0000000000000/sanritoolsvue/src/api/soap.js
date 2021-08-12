import request from '@/utils/request'
import {headers} from '@/utils/request'

export default {
  ports(wsdl){return request.get('/soap/ports',{params:{wsdl}})},
  methods(wsdl,port){return request.get(`/soap/${port}/methods`,{params:{wsdl}})},
  input(wsdl,port,method){return request.get(`/soap/${port}/${method}/input`,{params:{wsdl}})},
  output(wsdl,port,method){return request.get(`/soap/${port}/${method}/output`,{params:{wsdl}})},
  build(wsdl,port,method){return request.get(`/soap/${port}/${method}/build`,{params:{wsdl}})},
  invoke(wsdl,port,method,body){
    return request.post(`/soap/${port}/${method}/request?wsdl=${wsdl}`,body,headers.xml);
  }
}
