import request from '@/utils/request'
export function fetchList(params) {
  return request({
    url:'/api/role/list',
    method:'get',
    params:params
  })
}
export function addEntity(data) {
  return request({
    url:'/api/role/save',
    method:'post',
    data:data
  })
}

export function deleteEntity(id) {
  return request({
    url:'/api/role/delete?ids='+id,
    method:'get',
  })
}

export function getEntity(id) {
  return request({
    url:'/api/role/get?id='+id,
    method:'get',
  })
}

export function updateEntity(data) {
  return request({
    url:'/api/role/update',
    method:'post',
    data:data
  })
}

export function saveRoleMenus(data) {
  return request({
    url:'/api/role/saveRoleMenus',
    method:'post',
    data:data
  })
}

export function getAllRole() {
  return request({
    url:'/api/role/getAllRole',
    method:'get',
  })
}

