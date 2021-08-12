import request from '../request'

const Api = {
    page: '/api/sys/oss/page',
    upload: '/api/sys/oss/upload',
}

/** 文件列表 */
export const page = data => {
    return request.request({
        url: Api.page,
        params: data,
        method: 'get'
    })
}

/** 文件上传 */
export const upload = data => {
    
}