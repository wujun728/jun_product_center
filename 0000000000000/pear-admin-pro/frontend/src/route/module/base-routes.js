export default [
  {
    path: '/',
    redirect: "/dashboard/workspace",
    hidden: true,
  },
  {
    path: '/login',
    component: () => import('@/view/login/index.vue'),
    meta: {
      title: '登录'
    },
    hidden: true,
  }, 
  {
    path: '/error/403',
    component: () => import('@/view/error/404.vue'),
    meta: { title: '404' },
    hidden: true,
  }, 
  {
    path: '/error/404',
    component: () => import('@/view/error/404.vue'),
    meta: {
      title: '404'
    },
    hidden: true,
  }, 
  {
    path: '/error/500',
    component: () => import('@/view/error/404.vue'),
    meta: {
      title: '500'
    },
    hidden: true,
  }
]