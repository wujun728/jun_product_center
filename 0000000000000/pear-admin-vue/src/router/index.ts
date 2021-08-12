import { createRouter } from 'vue-router'
import routes from './routes'
import store from '@/store'
import { createApplicationRouteMode } from './_utils'
// test
// import { dynamicRoutes, dynamicBase, generatorDynamicRoute } from './dynamicRoutes'

const router = createRouter({
  history: createApplicationRouteMode(),
  routes
})

router.beforeEach(async (to, from, next) => {
  await store.dispatch('app/toggleRouterLoading', true)
  next()
})

router.afterEach(async () => {
  await store.dispatch('app/toggleRouterLoading', false)
})

export default router
