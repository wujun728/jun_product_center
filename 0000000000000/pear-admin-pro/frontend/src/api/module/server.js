import request from '../request'

const Api = {
    info: '/api/sys/server/info',
}

export const info = data => {
    return request.request({
        url: Api.info,
        params: data,
        method: 'get'
    })
}