import { usePermission } from '@/store/modules/permission'
import type { Router } from 'vue-router'
export default function setRouterPermission(router: Router): void {
  router.beforeEach(async (to, from, next) => {
    // const { getToken, getPermissionRoutes, setRoutes } = usePermission()
    const permissionStore = usePermission()
    const getPerRoutes = permissionStore.getPermissionRoutes
    const getToken = permissionStore.getToken
    // to and from are both route objects. must call `next`.
    if (!getToken && to.name !== 'login') {
      next('/login')
    } else {
      if (getToken) {
        if (to.fullPath === '/login') {
          next('/')
        } else {
          if (getPerRoutes.length === 0) {
            const dynamicRoutes = await permissionStore.setRoutes()
            for (const route of dynamicRoutes) {
              router.addRoute(route)
            }
            // 目标跳转
            next(to.fullPath)
          } else {
            next()
          }
        }
      } else {
        next()
      }
    }
  })
}
