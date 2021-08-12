import request from '@/utils/request'

export function fetchList(params) {
  return request({
    url:'/api/paylog/list',
    method:'get',
    params:params
  })
}
export function addEntity(data) {
  return request({
    url:'/api/paylog/save',
    method:'post',
    data:data
  })
}

export function deleteEntity(id) {
  return request({
    url:'/api/paylog/delete?ids='+id,
    method:'get',
  })
}

export function getEntity(id) {
  return request({
    url:'/api/paylog/get?id='+id,
    method:'get',
  })
}

export function updateEntity(data) {
  return request({
    url:'/api/paylog/update',
    method:'post',
    data:data
  })
}

