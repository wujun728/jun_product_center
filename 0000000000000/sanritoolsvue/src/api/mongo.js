import request, { headers } from '@/utils/request'
import qs from 'qs'

export default {
  databaseNames(connName){return request.get('/mongo/databaseNames',{params:{connName}})},
  collectionNames(connName,databaseName){return request.get(`/mongo/collectionNames/${databaseName}`,{params:{connName}})},
  queryPage(connName,databaseName,collectionName,page){return request.get('/mongo/queryPage',{params:{connName,databaseName,collectionName,...page}})}
}
