import request from '../request'

const Api = {
    page: '/api/sys/config/page',
    save: '/api/sys/config/save',
    edit: '/api/sys/config/edit',
    remove: '/api/sys/config/remove',
    removeBatch: '/api/sys/config/removeBatch'
}

/** 配置列表 */
export const page = data => {
    return request.request({
        url: Api.page,
        params: data,
        method: 'GET'
    })
}

/** 新增配置 */
export const save = data => {
    return request.request({
        url: Api.save,
        data: data,
        method: 'POST'
    })
}

/** 修改配置 */
export const edit = data => {
    return request.request({
        url: Api.edit,
        data: data,
        method: 'PUT'
    })
}

/** 删除配置 */
export const remove = data => {
    return request.request({
        url: Api.remove,
        params: data,
        method: 'DELETE'
    })
}

/** 批量删除 */
export const removeBatch = data => {
    return request.request({
        url: Api.removeBatch,
        params: data,
        method: 'DELETE'
    })
}