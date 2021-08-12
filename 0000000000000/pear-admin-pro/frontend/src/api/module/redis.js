import request from '../request'

const Api = {
    info: '/api/sys/redis/info',
    size: '/api/sys/redis/size',
    comd: '/api/sys/redis/comd',
}

/** 缓存详情 */
export const info = data => {
    return request.request({
        url: Api.info,
        params: data,
        method: 'GET'
    })
}

/** 缓存数量 */
export const size = data => {
    return request.request({
        url: Api.size,
        params: data,
        method: 'GET'
    })
}

/** 内存信息 */
export const comd = data => {
    return request.request({
        url: Api.comd,
        params: data,
        method: 'GET'
    })
}