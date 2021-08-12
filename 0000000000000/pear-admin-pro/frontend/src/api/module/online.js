import request from '../request'

const Api = {
    list: '/api/sys/online/list',
}

export const list = data => {
    return request.request({
        url: Api.list,
        params: data,
        method: 'get'
    })
}