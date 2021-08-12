import request from '@/utils/request'
export function fetchList(params) {
  return request({
    url:'/api/menu/list',
    method:'get',
    params:params
  })
}
export function addEntity(data) {
  return request({
    url:'/api/menu/save',
    method:'post',
    data:data
  })
}

export function deleteEntity(id) {
  return request({
    url:'/api/menu/delete?ids='+id,
    method:'get',
  })
}

export function getEntity(id) {
  return request({
    url:'/api/menu/get?id='+id,
    method:'get',
  })
}

export function updateEntity(data) {
  return request({
    url:'/api/menu/update',
    method:'post',
    data:data
  })
}

export function getNodes(params) {
  return request({
    url:'/api/menu/getAllNodes',
    method:'get',
    params:params
  })
}
