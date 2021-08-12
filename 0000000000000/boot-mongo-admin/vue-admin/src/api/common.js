import request from '@/utils/request'
//用户登录
export function authLogin(params) {
  return request({
    url:'/api/login',
    method:'post',
    data:params
  })
}
//用户退出登录
export function ajax_logout() {
  return request({
    url: '/api/logout',
    method: 'get',
   })
}
//获取数据字典
export function get_dictionary_list(code) {
  return request({
    url:'/api/get_dictionary_list',
    method:'get',
    params:{code:code}
  })
}

//获取当前用户的权限
export function getAllMenusByUser(userId) {
  return request({
    url:'/api/role/getAllMenusByUser',
    method:'get',
    params:{userId:userId,type:'0'}
  })
}

//获取当前用户的权限
export function getAllPermsByUser(userId) {
  return request({
    url:'/api/role/getAllPermsByUser',
    method:'get',
    params:{userId:userId}
  })
}
