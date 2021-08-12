import axios from 'axios'
import { Message } from 'element-ui'
import store from '@/store/index'
import { getRouteToken, removeRouteToken } from '@/utils/auth'

// 请求超时时间，10s
const requestTimeOut = 10 * 1000
const success = 200
// 提示信息显示时长
const messageDuration = 5 * 1000

// 系统全局请求对象
const request = axios.create({
  baseURL: process.env.VUE_APP_BASE_API,
  timeout: requestTimeOut,
  responseType: 'json',
  validateStatus(status) {
    return status === success
  }
})

request.interceptors.request.use(
  config => {
    try {
      const token = getRouteToken()
      if (token) {
        config.headers['Authorization'] = 'bearer ' + token
      }
    } catch (e) {
      console.error(e)
    }
    return config
  },
  error => {
    console.log(error)
    return Promise.reject(error)
  }
)

request.interceptors.response.use((config) => {
  return config
}, (error) => {
  if (error.response) {
    const errorMessage = error.response.data === null ? '系统内部异常，请联系网站管理员' : error.response.data.message
    switch (error.response.status) {
      case 404:
        Message({
          message: '很抱歉，资源未找到',
          type: 'error',
          duration: messageDuration
        })
        break
      case 403:
        Message({
          message: '很抱歉，当前网关用户暂无该操作权限',
          type: 'error',
          duration: messageDuration
        })
        break
      case 401:
        remove()
        Message({
          message: '网关认证已失效，请重新认证',
          type: 'error',
          duration: messageDuration
        })
        break
      default:
        Message({
          message: errorMessage,
          type: 'error',
          duration: messageDuration
        })
        break
    }
  }
  return Promise.reject(error)
})

const r = {
  post(url, params) {
    return request.post(url, params, {
      transformRequest: [(params) => {
        return tansParams(params)
      }],
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      }
    })
  },
  put(url, params) {
    return request.put(url, params, {
      transformRequest: [(params) => {
        return tansParams(params)
      }],
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      }
    })
  },
  get(url, params) {
    let _params
    if (Object.is(params, undefined)) {
      _params = ''
    } else {
      _params = '?'
      for (const key in params) {
        // eslint-disable-next-line no-prototype-builtins
        if (params.hasOwnProperty(key) && params[key] !== null) {
          _params += `${key}=${params[key]}&`
        }
      }
    }
    return request.get(`${url}${_params}`)
  },
  delete(url, params) {
    let _params
    if (Object.is(params, undefined)) {
      _params = ''
    } else {
      _params = '?'
      for (const key in params) {
        // eslint-disable-next-line no-prototype-builtins
        if (params.hasOwnProperty(key) && params[key] !== null) {
          _params += `${key}=${params[key]}&`
        }
      }
    }
    return request.delete(`${url}${_params}`)
  }
}

function tansParams(params) {
  let result = ''
  Object.keys(params).forEach((key) => {
    if (!Object.is(params[key], undefined) && !Object.is(params[key], null)) {
      result += encodeURIComponent(key) + '=' + encodeURIComponent(params[key]) + '&'
    }
  })
  return result
}

function remove() {
  removeRouteToken()
  store.commit('account/setRouteToken', null)
}

export default r
