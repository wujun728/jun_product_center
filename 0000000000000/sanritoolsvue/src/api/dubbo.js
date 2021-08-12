import request from '@/utils/request'

export default {
  services(connName){return request.get('/dubbo/services',{params:{connName}})},
  providers(connName,serviceName){return request.get('/dubbo/providers',{params:{connName,serviceName}})},
  invoke(params){return request.post('/dubbo/invoke',params)},
  connects(){return request.get('/dubbo/connects')}
}
