import request from '../request'

const Api = {
    page: '/api/sys/post/page',
    list: '/api/sys/post/list',
    remove: '/api/sys/post/remove',
    removeBatch: '/api/sys/post/removeBatch',
    save: '/api/sys/post/save',
    edit: '/api/sys/post/edit',
}

/** 岗位列表 */
export const page = data => {
    return request.request({
        url: Api.page,
        params: data,
        method: 'GET'
    })
}

/** 岗位列表 */
export const list = data => {
    return request.request({
        url: Api.list,
        params: data,
        method: 'GET'
    })
}

/** 新增岗位 */
export const save = data => {
    return request.request({
        url: Api.save,
        data: data,
        method: 'POST'
    })
}

/** 修改岗位 */
export const edit = data => {
    return request.request({
        url: Api.edit,
        data: data,
        method: 'PUT'
    })
}

/** 删除岗位 */
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
