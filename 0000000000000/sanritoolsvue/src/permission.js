import router from './router'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
import getPageTitle from '@/utils/get-page-title'
import store from './store'
import core from '@/api/core'

NProgress.configure({ showSpinner: false }) // NProgress Configuration

router.beforeEach(async(to, from, next) => {
  // start progress bar
  NProgress.start()

  // set page title
  document.title = getPageTitle(to.meta.title)

  // generate accessible routes map based on roles
  const accessRoutes = await store.dispatch('permission/generateRoutes')

  next()
})

router.afterEach((to, from) => {
  // finish progress bar
  NProgress.done()

  // 调用访问接口, 增加一次接口访问
  if (to.meta.key && !to.meta.front){
    core.visited(to.meta.key).then(res => {
      // console.log(to.meta.key)
    })
  }

})
