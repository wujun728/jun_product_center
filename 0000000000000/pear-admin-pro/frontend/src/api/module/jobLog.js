import request from '../request'

const Api = {
    page: '/api/sys/job/log/page',
}

/** 日志列表 */
export const page = data => {
    return request.request({
        url: Api.page,
        params: data,
        method: 'GET'
    })
}
