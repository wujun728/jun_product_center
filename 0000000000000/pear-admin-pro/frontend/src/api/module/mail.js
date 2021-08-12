import request from '../request'

const Api = {
    send: '/api/sys/mail/send'
}

export const send = data => {
    return request.request({
        url: Api.send,
        data: data,
        method: 'POST'
    })
}