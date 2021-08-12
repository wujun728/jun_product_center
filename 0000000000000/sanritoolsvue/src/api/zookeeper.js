import request from '@/utils/request'
import { headers } from '@/utils/request'
import qs from 'qs'

export default {
  childrens(connName,path){ return request.get('/zookeeper/childrens',{params:{connName,path}})},
  meta(connName,path){return request.get('/zookeeper/meta',{params:{connName,path}})},
  acls(connName,path){return request.get('/zookeeper/acls',{params:{connName,path}})},
  readData(connName,path){return request.get('/zookeeper/readData',{params:{connName,path}})},

  // zookeeper 扩展 , 添加收藏
  favorites(connName){return request.get('/zookeeper/favorites',{params:{connName}})},
  addFavorite(connName,name,path){return request.post('/zookeeper/addFavorite',qs.stringify({connName,name,path}))},
  removeFavorite(connName,name){return request.post('/zookeeper/removeFavorite',qs.stringify({connName,name}))}
}
