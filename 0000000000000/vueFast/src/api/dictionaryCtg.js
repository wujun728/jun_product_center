import request from '@/utils/request'
export function fetchList(params) {
  return request({
    url:'/api/dictionaryCtg/list',
    method:'get',
    params:params
  })
}
export function addEntity(data) {
  return request({
    url:'/api/dictionaryCtg/save',
    method:'post',
    data:data
  })
}

export function deleteEntity(id) {
  return request({
    url:'/api/dictionaryCtg/delete?ids='+id,
    method:'get',
  })
}

export function getEntity(id) {
  return request({
    url:'/api/dictionaryCtg/get?id='+id,
    method:'get',
  })
}

export function updateEntity(data) {
  return request({
    url:'/api/dictionaryCtg/update',
    method:'post',
    data:data
  })
}

