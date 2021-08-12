import request from '@/utils/request'
export function fetchList(params) {
  return request({
    url:'/api/scheduleJob/list',
    method:'get',
    params:params
  })
}
export function addEntity(data) {
  return request({
    url:'/api/scheduleJob/save',
    method:'post',
    data:data
  })
}

export function deleteEntity(id) {
  return request({
    url:'/api/scheduleJob/delete?ids='+id,
    method:'get',
  })
}

export function getEntity(id) {
  return request({
    url:'/api/scheduleJob/get?id='+id,
    method:'get',
  })
}

export function updateEntity(data) {
  return request({
    url:'/api/scheduleJob/update',
    method:'post',
    data:data
  })
}

