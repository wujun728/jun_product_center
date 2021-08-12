import request from '@/utils/request'
export function fetchList(params) {
  return request({
    url:'/api/adsense/list',
    method:'get',
    params:params
  })
}
export function addEntity(data) {
   return request({
    url:'/api/adsense/save',
    method:'post',
    data:data
  })
}

export function deleteEntity(id) {
  return request({
    url:'/api/adsense/delete?ids='+id,
    method:'get',
  })
}

export function getEntity(id) {
  return request({
    url:'/api/adsense/get?id='+id,
    method:'get',
  })
}

export function updateEntity(data) {
  return request({
    url:'/api/adsense/update',
    method:'post',
    data:data
  })
}

export function findAll(id) {
  return request({
    url:'/api/adsense/findAll',
    method:'get',
  })
}
