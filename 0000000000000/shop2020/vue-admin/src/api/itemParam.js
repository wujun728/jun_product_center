import request from '@/utils/request'

export function fetchList(params) {
  return request({
    url:'/api/itemParam/list',
    method:'get',
    params:params
  })
}
export function addEntity(data) {
  return request({
    url:'/api/itemParam/save',
    method:'post',
    data:data
  })
}

export function deleteEntity(id) {
  return request({
    url:'/api/itemParam/delete?ids='+id,
    method:'get',
  })
}

export function getEntity(id) {
  return request({
    url:'/api/itemParam/get?id='+id,
    method:'get',
  })
}

export function updateEntity(data) {
  return request({
    url:'/api/itemParam/update',
    method:'post',
    data:data
  })
}

export function getAllItemParamList(params) {
  return request({
    url:'/api/itemParam/getAll',
    method:'get',
    params:params
  })
}
