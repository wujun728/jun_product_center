import request from '../request'

const Api = {
    page: '/api/sys/log/page',
    clean: '/api/sys/log/clean',
    export: '/api/sys/log/export',
}

/** 查询日志 */
export const page = data => {
    return request.request({
        url: Api.page,
        params: data,
        method: 'GET'
    })
}

/** 清空日志 */
export const clean = data => {
    return request.request({
        url: Api.clean,
        params: data,
        method: 'DELETE'
    })
}

export const exportExcel = data => {
    request.request({
        url: Api.export,
        params: data,
        method: 'GET',
        responseType: 'blob',
    }).then((result) => {
          if (!result) return;
          let url = window.URL.createObjectURL(new Blob([result]));
          let link = document.createElement('a');
          link.style.display = 'none';
          link.href = url;
          link.setAttribute('download', '测试excel.xlsx');
          document.body.appendChild(link);
          link.click()
          document.body.removeChild(link);
    })
}