import Vue from 'vue'
import Router from 'vue-router'
import Layout from '@/layout'
import db from '@/utils/localstorage'
import request from '@/utils/request'
import store from '@/store/index'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'

Vue.use(Router)

const constRouter = [
  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path*',
        component: (resolve) => require(['@/views/redirect/index'], resolve)
      }
    ]
  },
  {
    path: '/404',
    component: (resolve) => require(['@/views/error-page/404'], resolve),
    hidden: true
  },
  {
    path: '/login',
    name: '登录页',
    component: (resolve) => require(['@/views/login/index'], resolve)
  },
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [
      {
        path: 'dashboard',
        component: (resolve) => require(['@/views/dashboard/index'], resolve),
        name: 'Dashboard',
        meta: { title: 'dashboard', icon: 'dashboard', affix: true }
      }
    ]
  },
  {
    path: '/profile',
    component: Layout,
    redirect: '/profile/index',
    hidden: true,
    children: [
      {
        path: 'index',
        component: (resolve) => require(['@/views/profile/index'], resolve),
        name: 'Profile',
        meta: { title: 'profile', icon: 'user', noCache: true }
      }
    ]
  },
  {
    path: '/error',
    component: Layout,
    redirect: 'noRedirect',
    name: 'ErrorPages',
    meta: {
      title: 'errorPages',
      icon: '404'
    },
    children: [
      {
        path: '404',
        component: (resolve) => require(['@/views/error-page/404'], resolve),
        name: 'Page404',
        meta: { title: 'page404', noCache: true }
      }
    ]
  }
]

const router = new Router({
  scrollBehavior: () => ({ y: 0 }),
  routes: constRouter
})

const whiteList = ['/login']

let asyncRouter

// 导航守卫，渲染动态路由
router.beforeEach((to, from, next) => {
  NProgress.start()
  if (whiteList.indexOf(to.path) !== -1) {
    next()
  } else {
    const token = db.get('ACCESS_TOKEN')
    const user = db.get('USER')
    const userRouter = get('USER_ROUTER')
    if (token.length && user) {
      if (!asyncRouter) {
        if (!userRouter) {
          request.get(`system/menu/${user.username}`).then((res) => {
            const permissions = res.data.data.permissions
            store.commit('account/setPermissions', permissions)
            asyncRouter = res.data.data.routes
            store.commit('account/setRoutes', asyncRouter)
            save('USER_ROUTER', asyncRouter)
            go(to, next)
          })
        } else {
          asyncRouter = userRouter
          go(to, next)
        }
      } else {
        next()
      }
    } else {
      if (to.path === '/login') {
        next()
      } else {
        next('/login')
        NProgress.done()
      }
    }
  }
})

router.afterEach((to, from) => {
  if (to.path === '/login') {
    asyncRouter = null
  }
  NProgress.done()
})

function go(to, next) {
  asyncRouter = filterAsyncRouter(asyncRouter)
  router.addRoutes(asyncRouter)
  next({ ...to, replace: true })
}

function save(name, data) {
  localStorage.setItem(name, JSON.stringify(data))
}

function get(name) {
  return JSON.parse(localStorage.getItem(name))
}

function filterAsyncRouter(routes) {
  return routes.filter((route) => {
    const component = route.component
    if (component) {
      if (route.component === 'Layout') {
        route.component = Layout
      } else {
        route.component = view(component)
      }
      if (route.children && route.children.length) {
        route.children = filterAsyncRouter(route.children)
      }
      return true
    }
  })
}

function view(path) {
  return (resolve) => require([`@/views/${path}.vue`], resolve)
}

export default router
