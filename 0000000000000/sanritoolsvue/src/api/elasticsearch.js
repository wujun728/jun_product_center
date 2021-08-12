import request, { headers } from '@/utils/request'
import qs from 'qs'

export default {
  clusterHealth(connName){return request.get('/elasticsearch/cluster/health',{params:{connName}})},
  clusterState(connName){return request.get('/elasticsearch/cluster/state',{params:{connName}})},
  clusterNodes(connName){return request.get('/elasticsearch/cluster/nodes',{params:{connName}})},
  nodeStats(connName){return request.get('/elasticsearch/node/stats',{params:{connName}})},
  status(connName){return request.get('/elasticsearch/status',{params:{connName}})},
  search(connName,indexName,dsl){return request.post(`/elasticsearch/search/${connName}/${indexName}`,dsl,headers.json)},
  dslSearch(connName,dsl){return request.post(`/elasticsearch/search/${connName}`,dsl,headers.json)}
}
