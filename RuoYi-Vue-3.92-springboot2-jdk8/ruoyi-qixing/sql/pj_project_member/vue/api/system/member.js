import request from '@/utils/request'

// 查询项目成员与结算列表
export function listMember(query) {
  return request({
    url: '/system/member/list',
    method: 'get',
    params: query
  })
}

// 查询项目成员与结算详细
export function getMember(id) {
  return request({
    url: '/system/member/' + id,
    method: 'get'
  })
}

// 新增项目成员与结算
export function addMember(data) {
  return request({
    url: '/system/member',
    method: 'post',
    data: data
  })
}

// 修改项目成员与结算
export function updateMember(data) {
  return request({
    url: '/system/member',
    method: 'put',
    data: data
  })
}

// 删除项目成员与结算
export function delMember(id) {
  return request({
    url: '/system/member/' + id,
    method: 'delete'
  })
}
