import request from '@/utils/request'

// 查询系统树形列表
export function listTreeDict(query) {
  return request({
    url: '/system/dict/tree/list',
    method: 'get',
    params: query
  })
}

// 查询系统树形列表
export function listTreeDictAll(query) {
  return request({
    url: '/system/dict/tree/list/all',
    method: 'get',
    params: query
  })
}

// 查询系统树形详细
export function getTreeDict(id) {
  return request({
    url: '/system/dict/tree/' + id,
    method: 'get'
  })
}

// 新增系统树形
export function addTreeDict(data) {
  return request({
    url: '/system/dict/tree',
    method: 'post',
    data: data
  })
}

// 修改系统树形
export function updateTreeDict(data) {
  return request({
    url: '/system/dict/tree',
    method: 'put',
    data: data
  })
}

// 删除系统树形
export function delTreeDict(id) {
  return request({
    url: '/system/dict/tree/' + id,
    method: 'delete'
  })
}

// 导出系统树形
export function exportTreeDict(query) {
  return request({
    url: '/system/dict/tree/export',
    method: 'get',
    params: query
  })
}
