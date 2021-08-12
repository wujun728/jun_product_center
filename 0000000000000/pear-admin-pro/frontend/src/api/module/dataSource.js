import request from '../request'

/** 接口列表 */
const Api = {
    page: '/api/sys/dataSource/page',
    save: '/api/sys/dataSource/save',
    edit: '/api/sys/dataSource/edit',
    remove: '/api/sys/dataSource/remove',
    removeBatch: '/api/sys/dataSource/removeBatch'
}

/** 多库列表 */
export const page = data => {
    return request.request({
        url: Api.page,
        params: data,
        method: 'get'
    })
}

/** 新增多库 */
export const save = data => {
    return request.request({
        url: Api.save,
        data: data,
        method: 'POST'
    })
}

/** 修改多库 */
export const edit = data => {
    return request.request({
        url: Api.edit,
        data: data,
        method: 'PUT'
    })
}

/** 删除角色 */
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