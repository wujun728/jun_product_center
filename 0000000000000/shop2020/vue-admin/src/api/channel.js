import request from '@/utils/request'
export function fetchList(params) {
  return request({
    url:'/api/channel/list',
    method:'get',
    params:params
  })
}

export function addEntity(data) {
   return request({
    url:'/api/channel/save',
    method:'post',
    data:data
  })
}

export function deleteEntity(id) {
  return request({
    url:'/api/channel/delete?ids='+id,
    method:'get',
  })
}

export function getEntity(id) {
  return request({
    url:'/api/channel/get?id='+id,
    method:'get',
  })
}

export function updateEntity(data) {
  return request({
    url:'/api/channel/update',
    method:'post',
    data:data
  })
}

export function getNodes(params) {
  return request({
    url:'/api/channel/getNodes',
    method:'get',
    params:params
  })
}
