import { createWebHashHistory, createRouter } from 'vue-router'
import basicRoutes from './basicRoutes'
import setRouterPermission from './permission'

const router = createRouter({
  history: createWebHashHistory('/'),
  routes: basicRoutes
})

setRouterPermission(router)

export default router
