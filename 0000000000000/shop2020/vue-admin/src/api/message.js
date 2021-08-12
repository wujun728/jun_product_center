import request from '@/utils/request'

export function fetchList(params) {
  return request({
    url:'/api/message/list',
    method:'get',
    params:params
  })
}
export function addEntity(data) {
  return request({
    url:'/api/message/save',
    method:'post',
    data:data
  })
}

export function deleteEntity(id) {
  return request({
    url:'/api/message/delete?ids='+id,
    method:'get',
  })
}

export function getEntity(id) {
  return request({
    url:'/api/message/get?id='+id,
    method:'get',
  })
}

export function updateEntity(data) {
  return request({
    url:'/api/message/update',
    method:'post',
    data:data
  })
}
//私信列表
export function fetchTalkList(params) {
  return request({
    url:'/api/message/talk',
    method:'get',
    params:params
  })
}
//发起私信
export function save_talkEntity(data) {
  return request({
    url:'/api/message/save_talk',
    method:'post',
    data:data
  })
}

