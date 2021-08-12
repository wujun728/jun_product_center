import request from '@/utils/request'

export function fetchList(params) {
  return request({
    url:'/api/paybill/list',
    method:'get',
    params:params
  })
}
export function addEntity(data) {
  return request({
    url:'/api/paybill/save',
    method:'post',
    data:data
  })
}

export function deleteEntity(id) {
  return request({
    url:'/api/paybill/delete?ids='+id,
    method:'get',
  })
}

export function getEntity(id) {
  return request({
    url:'/api/paybill/get?id='+id,
    method:'get',
  })
}

export function updateEntity(data) {
  return request({
    url:'/api/paybill/update',
    method:'post',
    data:data
  })
}

