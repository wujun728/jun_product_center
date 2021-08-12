import request from '../request'

const Api = {
    page: '/api/sys/tenant/page',
    list: '/api/sys/tenant/list',
    save: '/api/sys/tenant/save',
    edit: '/api/sys/tenant/edit',
    give: '/api/sys/tenant/give',
    power: '/api/sys/tenant/power',
    remove: '/api/sys/tenant/remove',
    removeBatch: '/api/sys/tenant/removeBatch'
}

/** 租户列表 */
export const page = data => {
    return request.request({
        url: Api.page,
        params: data,
        method: 'GET'
    })
}

/** 租户列表 */
export const list = data => {
    return request.request({
        url: Api.list,
        params: data,
        method: 'GET'
    })
}

/** 租户权限 */
export const power = data => {
    return request.request({
        url: Api.power,
        params: data,
        method: 'GET'
    })
}

/** 权限分配 */
export const give = data => {
    return request.request({
        url: Api.give,
        data: data,
        method: 'POST'
    })
}

/** 新增租户 */
export const save = data => {
    return request.request({
        url: Api.save,
        data: data,
        method: 'POST'
    })
}

/** 修改租户 */
export const edit = data => {
    return request.request({
        url: Api.edit,
        data: data,
        method: 'PUT'
    })
}

/** 删除租户 */
export const remove = data => {
    return request.request({
        url: Api.remove,
        params: data,
        method: 'DELETE'
    })
}

/** 批量租户 */
export const removeBatch = data => {
    return request.request({
        url: Api.removeBatch,
        params: data,
        method: 'DELETE'
    })
}