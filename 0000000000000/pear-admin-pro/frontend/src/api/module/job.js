import request from '../request'

const Api = {
    page: '/api/sys/job/page',
    remove: '/api/sys/job/remove',
    removeBatch: '/api/sys/job/removeBatch',
    save: '/api/sys/job/save',
    edit: '/api/sys/job/edit',
    resume: '/api/sys/job/resume',
    pause: '/api/sys/job/pause',
}

/** 岗位列表 */
export const page = data => {
    return request.request({
        url: Api.page,
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

/** 恢复 */
export const resume = data => {
    return request.request({
        url: Api.resume,
        params: data,
        method: 'PUT'
    })
}

/** 暂停 */
export const pause = data => {
    return request.request({
        url: Api.pause,
        params: data,
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
