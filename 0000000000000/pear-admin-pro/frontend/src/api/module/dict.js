import request from '../request'

const Api = {
    page: '/api/sys/dict/page',
    save: '/api/sys/dict/save',
    edit: '/api/sys/dict/edit',
    remove: '/api/sys/dict/remove',
    removeBatch: '/api/sys/dict/removeBatch',
}

export const page = data => {
    return request.request({
        url: Api.page,
        params: data,
        method: 'get'
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