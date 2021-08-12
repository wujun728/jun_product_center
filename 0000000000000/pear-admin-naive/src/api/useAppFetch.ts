import { createFetch, useFetch } from '@vueuse/core'
import HttpStatusCode from './HttpStatusCode'
import { usePermission } from '@/store/modules/permission'

enum BackedReturnStatus {
  ok = 200
}

export type AppFetch = typeof useFetch

/**
 *  @author 落小梅
 *  Notice:
 *   when http fetch response data is look like this
 *   const { status: 200, data: { code, msg, data: innerData, success} } = response
 *   if  innerData is null or undefined, will return data is response
 *   or  return innerData
 *  @example
 *  login api response has not innerData, will return response
 *  getValidateCode response has innerData, will return innerData
 *  @page login/index.vue
 */
const useAppFetch: AppFetch = createFetch({
  baseUrl: import.meta.env.VITE_APP_BASIC_FETCH_URL,
  options: {
    async beforeFetch({ options }) {
      const permissionStore = usePermission()
      if (permissionStore.getToken) {
        const [tokenKey, token] = Object.values(permissionStore.getToken)
        Object.assign(options.headers, {
          Authorization: token,
          'Authorization-key': tokenKey
        })
      }
      return { options }
    },
    async afterFetch(ctx) {
      const {
        response: { status, statusText },
        data: responseData
      } = ctx
      if (HttpStatusCode.OK !== status) {
        window.$notification.error({
          title: '网络异常，请稍后再试!',
          description: `${status}: ${statusText}`
        })
        return {}
      } else {
        const { code, msg, success, data: state } = responseData
        if (BackedReturnStatus.ok !== code) {
          window.$message.error(msg)
          // setTimeout(() => window.$message.error(msg), 100)
          return {
            code,
            data: responseData,
            msg,
            success
          }
        } else {
          return {
            code,
            data: state ? state : responseData,
            msg,
            success
          }
        }
      }
    }
  },
  fetchOptions: {
    mode: 'cors'
  }
})

export default useAppFetch
