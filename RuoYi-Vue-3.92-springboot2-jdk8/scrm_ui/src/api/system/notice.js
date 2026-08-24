import request from '@/utils/request'

// 查询公告列表
export function listNotice(query) {
  return request({
    url: '/system/notice/list',
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

// 首页顶部公告列表（带已读状态）
export function listNoticeTop() {
  return request({
    url: '/system/notice/listTop',
    method: 'get'
  })
}

// 标记公告已读
export function markNoticeRead(noticeId) {
  return request({
    url: '/system/notice/markRead',
    method: 'post',
    params: { noticeId }
  })
}

// 批量标记已读
export function markNoticeReadAll(ids) {
  return request({
    url: '/system/notice/markReadAll',
    method: 'post',
    params: { ids }
  })
}

// 查询公告已读用户列表
export function listNoticeReadUsers(query) {
  return request({
    url: '/system/notice/readUsers/list',
    method: 'get',
    params: query
  })
}

// [MIG] 以下为老 OA UI 迁移的公司公告/公司动态页面依赖的接口
// 查询首页公告
export function listHomeNotice(query) {
  return request({
    url: '/system/notice/home/list',
    method: 'get',
    params: query
  })
}

// 查询用户公告（公司公告列表）
export function listUserNotice(query) {
  return request({
    url: '/system/notice/user/list',
    method: 'get',
    params: query
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
