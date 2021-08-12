// import BasicLyout from '@/layouts/BasicLayout.vue'
const dynamicRouteMap = new Map()
dynamicRouteMap.set('BasicLayout', () => import('@/layouts/BasicLayout.vue'))
dynamicRouteMap.set('profile', () => import('@/views/profile/index.vue'))
dynamicRouteMap.set(
  'dashboard-console',
  () => import('@/views/dashboard/console/index.vue')
)
dynamicRouteMap.set(
  'dashboard-workspace',
  () => import('@/views/dashboard/workspace/index.vue')
  // () => import('@/views/dashboard/workspace/test.tsx')
)
dynamicRouteMap.set(
  'result-success',
  () => import('@/views/result/success.vue')
)
dynamicRouteMap.set(
  'result-failure',
  () => import('@/views/result/failure.vue')
)
dynamicRouteMap.set('error-403', () => import('@/views/error/403.vue'))
dynamicRouteMap.set('error-404', () => import('@/views/error/404.vue'))
dynamicRouteMap.set('error-500', () => import('@/views/error/500.vue'))
dynamicRouteMap.set('user', () => import('@/views/user/index.vue'))
dynamicRouteMap.set('role', () => import('@/views/role/index.vue'))
dynamicRouteMap.set('log-oper', () => import('@/views/log/oper.vue'))
dynamicRouteMap.set('log-auth', () => import('@/views/log/auth.vue'))
dynamicRouteMap.set('power', () => import('@/views/power/index.vue'))
dynamicRouteMap.set('config', () => import('@/views/config/index.vue'))
dynamicRouteMap.set('dict', () => import('@/views/dict/index.vue'))
dynamicRouteMap.set('post', () => import('@/views/post/index.vue'))
dynamicRouteMap.set('dept-list', () => import('@/views/dept/index.vue'))
dynamicRouteMap.set('server', () => import('@/views/server/index.vue'))
dynamicRouteMap.set('online', () => import('@/views/online/index.vue'))
dynamicRouteMap.set('redis', () => import('@/views/redis/index.vue'))
dynamicRouteMap.set('doc', () => import('@/views/doc/index.vue'))
dynamicRouteMap.set('mail', () => import('@/views/mail/index.vue'))
dynamicRouteMap.set('oss', () => import('@/views/oss/index.vue'))
dynamicRouteMap.set('dept', () => import('@/views/dept/index.vue'))
dynamicRouteMap.set('dataSource', () => import('@/views/dataSource/index.vue'))
dynamicRouteMap.set('job', () => import('@/views/job/index.vue'))
dynamicRouteMap.set('jobLog', () => import('@/views/jobLog/index.vue'))
dynamicRouteMap.set('announce', () => import('@/views/announce/index.vue'))
dynamicRouteMap.set('inbox', () => import('@/views/inbox/index.vue'))

export default dynamicRouteMap
