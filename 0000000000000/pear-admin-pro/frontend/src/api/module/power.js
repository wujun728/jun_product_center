import request from '../request'

const Api = {
    tree: '/api/sys/power/tree',
    save: '/api/sys/power/save',
    edit: '/api/sys/power/edit',
    assign: '/api/sys/power/tree/assign',
    remove: '/api/sys/power/remove',
    removeBatch: '/api/sys/power/removeBatch'
}

export const tree = data => {
    return request.request({
        url: Api.tree,
        params: data,
        method: 'GET'
    })
}

export const assign = data => {
    return request.request({
        url: Api.assign,
        params: data,
        method: 'GET'
    })
}

export const save = data => {
    return request.request({
        url: Api.save,
        data: data,
        method: 'POST'
    })
}

export const edit = data => {
    return request.request({
        url: Api.edit,
        data: data,
        method: 'PUT'
    })
}

export const remove = data => {
    return request.request({
        url: Api.remove,
        params: data,
        method: 'DELETE'
    })
}

export const removeBatch = data => {
    return request.request({
        url: Api.removeBatch,
        params: data,
        method: 'DELETE'
    })
}