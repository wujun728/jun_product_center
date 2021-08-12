import request from '@/utils/request'
import qs from 'qs'

export default {
  nodes(connParam){ return request.get('/redis/monitor/nodes',{params:{...connParam}})},
  clientList(connParam){return request.get('/redis/monitor/clientList',{params:{...connParam}})},
  memoryUses(connParam){return request.get('/redis/monitor/memoryUses',{params:{...connParam}})},
  killClient(connParam,clientId){return request.post(`/redis/monitor/client/kill/${clientId}`,qs.stringify({...connParam,clientId}))},
  mode(connParam){return request.get('/redis/monitor/mode',{params:{...connParam}})},
  dbs(connParam){return request.get('/redis/monitor/dbs',{params:{...connParam}})},
  slowlogs(connParam){return request.get('/redis/monitor/slowlogs',{params:{...connParam}})},

  scan(connParam,scanParam,serializerParam){ return request.get('/redis/key/scan',{params:{...connParam,...scanParam,...serializerParam}})},
  dropKeys(connParam,keys){return request.post('/redis/key/drop',qs.stringify({...connParam,keys}))},
  subKeys(connParam,key,redisScanParam,serializerParam){return request.get('/redis/key/subKeys',{params:{...connParam,key,...redisScanParam,...serializerParam}})},
  keyLength(connParam,key,serializerParam){return request.get('/redis/key/length',{params:{...connParam,key,...serializerParam}})},
  readData(connParam,subKeyParam,rangeParam,redisScanParam,serializerParam){return request.get('/redis/data',{params:{...connParam,...subKeyParam,...rangeParam,...redisScanParam,...serializerParam}})},
  collectionMethods(connParam,keys,command,serializerParam){return request.get('/redis/collectionMethods',{params:{...connParam,keys,command,...serializerParam}})}
}
