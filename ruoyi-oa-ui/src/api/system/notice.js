import request from '@/utils/request'

// 查询公告列表
export function listNotice(query) {
  return request({
    url: '/system/notice/list',
    method: 'get',
    params: query
  })
}

// 查询首页公告
export function listHomeNotice(query) {
  return request({
    url: '/system/notice/home/list',
    method: 'get',
    params: query
  })
}

// 查询用户公告
export function listUserNotice(query) {
  return request({
    url: '/system/notice/user/list',
    method: 'get',
    params: query
  })
}

// 查询公告详细
export function getNotice(noticeId) {
  return request({
    url: '/system/notice/' + noticeId,
    method: 'get'
  })
}

// 新增公告
export function addNotice(data) {
  return request({
    url: '/system/notice',
    method: 'post',
    data: data
  })
}

// 修改公告
export function updateNotice(data) {
  return request({
    url: '/system/notice',
    method: 'put',
    data: data
  })
}

// 删除公告
export function delNotice(noticeId) {
  return request({
    url: '/system/notice/' + noticeId,
    method: 'delete'
  })
}

// 状态调整
export function changeStatus(noticeId, status) {
  const data = {
    noticeId,
    status
  }
  return request({
    url: '/system/notice/changeStatus',
    method: 'put',
    data: data
  })
}

// 已读公告
export function readNotice(noticeId) {
  return request({
    url: '/system/notice/readNotice/' + noticeId,
    method: 'put'
  })
}
