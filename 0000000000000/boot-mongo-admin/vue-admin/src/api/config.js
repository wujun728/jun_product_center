import request from '@/utils/request'

export function getEntity(id) {
  return request({
    url:'/api/site/get?id='+id,
    method:'get',
  })
}

export function updateEntity(data) {
  return request({
    url:'/api/site/update',
    method:'post',
    data:data
  })
}

export function getConfig(id) {
  return request({
    url:'/api/site/config/'+id,
    method:'get',
  })
}

export function updateConfig(data) {
  return request({
    url:'/api/site/config_update',
    method:'post',
    data:data
  })
}
