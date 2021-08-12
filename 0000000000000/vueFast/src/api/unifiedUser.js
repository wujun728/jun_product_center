import request from '@/utils/request'
export function fetchList(params) {
  return request({
    url:'/api/unifiedUser/list',
    method:'get',
    params:params
  })
}
export function addEntity(data) {
  return request({
    url:'/api/unifiedUser/save',
    method:'post',
    data:data
  })
}

export function deleteEntity(id) {
  return request({
    url:'/api/unifiedUser/delete?ids='+id,
    method:'get',
  })
}

export function getEntity(id) {
  return request({
    url:'/api/unifiedUser/get?id='+id,
    method:'get',
  })
}

export function updateEntity(data) {
  return request({
    url:'/api/unifiedUser/update',
    method:'post',
    data:data
  })
}

export function diableEntity(id) {
  return request({
    url:'/api/unifiedUser/stop?ids='+id,
    method:'get',
  })
}

export function enableEntity(id) {
  return request({
    url:'/api/unifiedUser/start?ids='+id,
    method:'get',
  })
}
/**
 * 查询用户分页
 * @param {*} params
 */
export function getUnifiedUserList(params) {
  return request({
    url:'/api/unifiedUser/list',
    method:'get',
    params:params
  })
}



