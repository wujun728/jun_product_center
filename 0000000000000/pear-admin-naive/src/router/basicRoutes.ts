import { RouteRecordRaw } from 'vue-router'

const basicRoutes: RouteRecordRaw[] = [
  {
    path: '/login',
    name: 'login',
    meta: {
      title: '登录',
      hidden: true
    },
    component: () => import('@/views/application/login/index.vue')
  },
  {
    path: '/',
    name: 'root',
    meta: {
      hidden: true
    },
    redirect: '/dashboard/workspace'
  }
]

export default basicRoutes
