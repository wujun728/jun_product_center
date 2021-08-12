import http from '../http'

const Api = {
  login: '/login',
  logout: '/logout',
  getUserMenusArray: '/getUserMenusArray',
  getUserMenusTree: '/getUserMenusTree',
}

export const login = data => {
  return http.request({
    url: Api.login,
    data: data,
    method: 'post'
  })
}

export const logout = data => {
  return http.request({
    url: Api.logout,
    data: data,
    method: 'post'
  })
}

export const getUserMenusArray = data => {
  return http.request({
    url: Api.getUserMenusArray,
    data: data,
    method: 'post'
  })
}

export const getUserMenusTree = data => {
  return http.request({
    url: Api.getUserMenusTree,
    data: data,
    method: 'post'
  })
}
