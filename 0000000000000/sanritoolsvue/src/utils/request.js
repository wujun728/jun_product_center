import axios from 'axios'
import { MessageBox, Message } from 'element-ui'
import store from '@/store'

// create an axios instance
const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API, // url = base url + request url
  // withCredentials: true, // send cookies when cross-domain requests
  timeout: 20000 // request timeout
})

// request interceptor
service.interceptors.request.use(
  config => {
    // do something before request is sent
    return config
  },
  error => {
    // do something with request error
    console.log(error) // for debug
    return Promise.reject(error)
  }
)

// response interceptor
service.interceptors.response.use(
  /**
   * If you want to get http information such as headers or status
   * Please return  response => response
  */

  /**
   * Determine the request status by custom code
   * Here is just an example
   * You can also judge the status by HTTP Status Code
   */
  response => {
    const res = response.data
    if ( !response.headers['content-type'].startsWith('application/json')){
      if (response.headers['content-type'].startsWith('application/octet-stream')){
        return response;
      }
      return res;
    }

    const success = [0, 1, 2, 3, 4]
    // if the custom code is not in 0,1,2,3,4 , it is judged as an error.
    if (success.find(item => item+'' === res.code) === undefined) {
      Message({
        message: res.message || 'Error',
        type: 'error',
        duration: 5 * 1000
      })
      return Promise.reject(new Error(res.message || 'Error'))
    } else {
      return res
    }
  },
  error => {
    console.log('err' + error) // for debug
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service

// post 常用 contentType 定义
export const headers = {
  formData: {
    headers: { 'Content-Type': 'multipart/form-data'}
  },
  json: {
    headers: { 'Content-Type': 'application/json'}
  },
  urlencoded: {
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
  },
  plain: {
    headers : {'Content-Type': 'application/yaml' }
  },
  xml: {
    headers : {'Content-Type': 'application/xml' }
  },
  html: {
    headers : {'Content-Type': 'text/html' }
  }
}

