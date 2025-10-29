import request from '@/utils/request'

// 查询树形字典数据列表
export function listTreeDictData(query) {
  return request({
    url: '/system/tree/dict/data/list',
    method: 'get',
    params: query
  })
}

// fsd 查询树形字典数据列表
export function treeTreeDictData(query) {
  return request({
    url: '/system/tree/dict/data/tree',
    method: 'get',
    params: query
  })
}

// 查询树形字典数据详细
export function getTreeDictData(id) {
  return request({
    url: '/system/tree/dict/data/' + id,
    method: 'get'
  })
}

// fsd 新增树形字典数据
export function addTreeDictData(data) {
  return request({
    url: '/system/tree/dict/data',
    method: 'post',
    data: data
  })
}

// 修改树形字典数据
export function updateTreeDictData(data) {
  return request({
    url: '/system/tree/dict/data',
    method: 'put',
    data: data
  })
}

// 删除树形字典数据
export function delTreeDictData(id) {
  return request({
    url: '/system/tree/dict/data/' + id,
    method: 'delete'
  })
}

// 导出树形字典数据
export function exportTreeDictData(query) {
  return request({
    url: '/system/tree/dict/data/export',
    method: 'get',
    params: query
  })
}

// fsd 自动生成编码、层次码、层次、排序
export function genAutoInfo(data) {
  return request({
    url: '/system/tree/dict/data/gen/autoInfo',
    method: 'post',
    data: data
  })
}

// fsd 检查名称唯一性
export function checkUniqueByLabel(data) {
  return request({
    url: '/system/tree/dict/data/check/unique/label',
    method: 'post',
    data: data
  })
}

// fsd 校验编码
export function checkCode(data) {
  return request({
    url: '/system/tree/dict/data/check/code',
    method: 'post',
    data: data
  })
}
