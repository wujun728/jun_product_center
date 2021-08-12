import axios from 'axios'
import { Message, MessageBox } from 'element-ui'
import { authorizationValue } from '@/settings'
import store from '@/store/index'
import router from '@/router'
import { getToken, getRefreshToken, getExpireTime } from '@/utils/auth'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'

// 请求超时时间，10s
const requestTimeOut = 10 * 1000
const success = 200
// 更换令牌的时间区间
const checkRegion = 5 * 60 * 1000
// 提示信息显示时长
const messageDuration = 5 * 1000

// 系统全局请求对象
const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API,
  timeout: requestTimeOut,
  responseType: 'json',
  validateStatus(status) {
    return status === success
  }
})

// 系统令牌刷新请求对象
const refresh_service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API,
  timeout: requestTimeOut,
  responseType: 'json',
  validateStatus(status) {
    return status === success
  }
})

service.interceptors.request.use(
  config => {
    let _config = config
    try {
      const expireTime = getExpireTime()
      if (expireTime) {
        const left = expireTime - new Date().getTime()
        const refreshToken = getRefreshToken()
        if (left < checkRegion && refreshToken) {
          _config = queryRefreshToken(_config, refreshToken)
        } else {
          if (getToken()) {
            _config.headers['Authorization'] = 'bearer ' + getToken()
          }
        }
      }
    } catch (e) {
      console.error(e)
    }
    return _config
  },
  error => {
    console.log(error)
    return Promise.reject(error)
  }
)

// response interceptor
service.interceptors.response.use((config) => {
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
          message: '很抱歉，您暂无该操作权限',
          type: 'error',
          duration: messageDuration
        })
        break
      case 401:
        Message({
          message: '很抱歉，认证已失效，请重新登录',
          type: 'error',
          duration: messageDuration
        })
        break
      default:
        if (errorMessage === 'refresh token无效') {
          MessageBox.alert('登录已过期，请重新登录', '温馨提示', {
            confirmButtonText: '确定',
            showClose: false,
            callback: action => {
              router.push('/login')
            }
          })
        } else {
          Message({
            message: errorMessage,
            type: 'error',
            duration: messageDuration
          })
        }
        break
    }
  }
  return Promise.reject(error)
})

const request = {
  refresh(url, params) {
    params['grant_type'] = 'refresh_token'
    return refresh_service.post(url, params, {
      transformRequest: [(params) => {
        return tansParams(params)
      }],
      headers: {
        'Authorization': authorizationValue
      }
    })
  },
  login(url, params) {
    params['grant_type'] = 'password'
    return service.post(url, params, {
      transformRequest: [(params) => {
        return tansParams(params)
      }],
      headers: {
        'Authorization': authorizationValue
      }
    })
  },
  post(url, params) {
    return service.post(url, params, {
      transformRequest: [(params) => {
        return tansParams(params)
      }],
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      }
    })
  },
  put(url, params) {
    return service.put(url, params, {
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
    return service.get(`${url}${_params}`)
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
    return service.delete(`${url}${_params}`)
  },
  download(url, params, filename) {
    NProgress.start()
    return service.post(url, params, {
      transformRequest: [(params) => {
        return tansParams(params)
      }],
      responseType: 'blob'
    }).then((r) => {
      const content = r.data
      const blob = new Blob([content])
      if ('download' in document.createElement('a')) {
        const elink = document.createElement('a')
        elink.download = filename
        elink.style.display = 'none'
        elink.href = URL.createObjectURL(blob)
        document.body.appendChild(elink)
        elink.click()
        URL.revokeObjectURL(elink.href)
        document.body.removeChild(elink)
      } else {
        navigator.msSaveBlob(blob, filename)
      }
      NProgress.done()
    }).catch((r) => {
      console.error(r)
      NProgress.done()
      Message({
        message: '下载失败',
        type: 'error',
        duration: messageDuration
      })
    })
  },
  upload(url, params) {
    return service.post(url, params, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    })
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

async function queryRefreshToken(config, refreshToken) {
  const result = await request.refresh('auth/oauth/token', {
    refresh_token: refreshToken
  })
  if (result.status === success) {
    saveData(result.data)
    config.headers['Authorization'] = 'bearer ' + getToken()
  }
  return config
}

function saveData(data) {
  store.commit('account/setAccessToken', data.access_token)
  store.commit('account/setRefreshToken', data.refresh_token)
  const current = new Date()
  const expireTime = current.setTime(current.getTime() + 1000 * data.expires_in)
  store.commit('account/setExpireTime', expireTime)
}

export default request
