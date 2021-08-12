import request from '../request'

// 接口
const Api = {
  create: '/api/captcha/create',
  verify: '/api/captcha/verify'
}

// 创建
export const create = () =>{
  return request.request({
    url: Api.create,
    method: 'get'
  })
}

// 验证
export const verify = () =>{
  return request.request({
    url: Api.verify,
    method: 'post'
  })
}
  


