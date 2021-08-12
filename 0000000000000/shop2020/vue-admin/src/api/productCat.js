import request from '@/utils/request'

export function fetchList(params) {
  return request({
    url:'/api/productCat/list',
    method:'get',
    params:params
  })
}
export function addEntity(data) {
  return request({
    url:'/api/productCat/save',
    method:'post',
    data:data
  })
}

export function deleteEntity(id) {
  return request({
    url:'/api/productCat/delete?ids='+id,
    method:'get',
  })
}

export function getEntity(id) {
  return request({
    url:'/api/productCat/get?id='+id,
    method:'get',
  })
}

export function updateEntity(data) {
  return request({
    url:'/api/productCat/update',
    method:'post',
    data:data
  })
}

export function getNodes(params) {
  return request({
    url:'/api/productCat/getNodes',
    method:'get',
    params:params
  })
}
