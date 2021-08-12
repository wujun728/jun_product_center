import request from '../request'

/** 接口管理 */
const Api = {
    page: '/api/sys/inbox/page',
    save: '/api/sys/inbox/save',
    edit: '/api/sys/inbox/edit',
    remove: '/api/sys/inbox/remove',
    removeBatch: '/api/sys/inbox/removeBatch'
}

/** 公告列表 (分页) */
export const page = data => {
    return request.request({
        url: Api.page,
        params: data,
        method: 'GET'
    })
}

/** 保存 */
export const save = data => {
    return request.request({
        url: Api.save,
        data: data,
        method: 'POST'
    })
}

/** 修改 */
export const edit = data => {
    return request.request({
        url: Api.edit,
        data: data,
        method: 'PUT'
    })
}

/** 删除 */
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