import request from '@/utils/request'

export function fetchList(params) {
  return request({
    url:'/api/order/list',
    method:'get',
    params:params
  })
}
export function addEntity(data) {
  return request({
    url:'/api/order/save',
    method:'post',
    data:data
  })
}

export function deleteEntity(id) {
  return request({
    url:'/api/order/delete?ids='+id,
    method:'get',
  })
}

export function getEntity(id) {
  return request({
    url:'/api/order/get?id='+id,
    method:'get',
  })
}

export function updateEntity(data) {
  return request({
    url:'/api/order/update',
    method:'post',
    data:data
  })
}
//取消订单
export function cancelEntity(data) {
  return request({
    url:'/api/order/cancel',
    method:'post',
    data:data
  })
}
export function payEntity(data) {
  return request({
    url:'/api/order/pay',
    method:'post',
    data:data
  })
}
export function shippingEntity(data) {
  return request({
    url:'/api/order/shipping',
    method:'post',
    data:data
  })
}
export function saveShippingEntity(data) {
  return request({
    url:'/api/order/save_shipping',
    method:'post',
    data:data
  })
}
export function updateAddressEntity(data) {
  return request({
    url:'/api/order/updateAddress',
    method:'post',
    data:data
  })
}

export function saveOrderNoteEntity(data) {
  return request({
    url:'/api/orderNote/save',
    method:'post',
    data:data
  })
}
