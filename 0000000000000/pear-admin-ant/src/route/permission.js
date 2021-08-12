import permissionRoutes from './module/main-routes'
import NProgress from "nprogress";
import store from "@/store";
import router from "@/route/index";

/**
 * 菜单数组转树
 * @param list
 * @returns {[]}
 */
export const listToTree = list => {
  list.sort((a, b) => {
    return a.sort - b.sort
  })
  const map = {}
  let node
  const tree = []

  for (let i = 0; i < list.length; i++) {
    map[list[i].name] = i;
  }

  for (let i = 0; i < list.length; i++) {
    node = list[i];
    if (node.parent) {
      const children = list[map[node.parent]].children || []
      list[map[node.parent]].children = [...children, node]
    } else {
      tree.push(node)
    }
  }
  return tree
}

/**
 * 跟据后端返回的权限路由 树 生成vue-router所需要的路由树
 * 注: component这里不加载。因为生成的路由树，除了菜单使用外, 还需要在vue-router中做为动态路由使用
 * @param menuList
 * @returns {*}
 */
export const generatorUserMenuForTree = (menuList) => {
  const userRoutes = menuList.map(menu => {
    const {parent, icon, name, children = [], path, hidden = false, title, i18n} = menu
    // const i18nTitle = parent ? `${parent}.${name}` : `${name}` // name不一定为中文，如果用到i18n，则可以跟据name去做
    // key是唯一的，防止重复，所以拼上父级菜单
    const key = parent ? `${parent}-${name}` : `${name}`
    // todo: 如果后端返回的父级菜单的路径为不带'/'号，则需要拼上 '/', 子菜单不需要，因为vue-router会自动跟据路径拼
    const currentMenu = {
      path,
      name,
      hidden,
      parent,
      meta: {
        key: key,
        title,
        i18n,
        icon
      },
      children: children.length === 0 ? [] : generatorUserMenuForTree(children)
    }
    if (children.length <= 0) {
      delete currentMenu.children
    }
    return currentMenu
  })
  return userRoutes
}

/**
 * 跟据后端返回的权限路由 数组 生成vue-router所需要的路由树
 * @param menuList
 * @returns {*}
 */
export const generatorUserMenuForList = menuList => {
  // 如果后端返回的是纯数组的菜单（即：没有转换成菜单树结构的，要先转化成树结构）
  const tree = listToTree(menuList)
  const routes = generatorUserMenuForTree(tree)
  // 最后添加404页面
  routes.push({
    path: '/:pathMatch(.*)*',
    hidden: true,
    redirect: '/error/404'
  })
  return routes
}

/**
 * 为所有路由添加component视图
 * @param routes
 */
export const setUserRouteComponent = routes => {
  routes.forEach(r => {
    r.component = !r.parent ? permissionRoutes.Layout : permissionRoutes[r.name]
    if (r.children && r.children.length > 0) {
      setUserRouteComponent(r.children)
    }
  })
}

/**
 * 用户权限路由中是否包含当前访问路由的路径，如有则可以添加
 * @param routes
 * @param path
 * @returns {boolean}
 */
export const findRouteForUserRoutes = (routes, path) => {
  let hasRoute = false
  const routeArr = path.split('/')
  const routePath = routeArr[routeArr.length - 1]
  for (let i = 0; i < routes.length; i++) {
    const { path, children = [] } = routes[i]
    if (path === routePath) {
      hasRoute = true
    } else if (children.length > 0) {
      hasRoute = findRouteForUserRoutes(children, routePath)
    }
    if (hasRoute) {
      break
    }
  }
  return hasRoute
}

const setDocumentTitle = title => {
  // 如有i18n在这里修改
  document.title = `pear-admin-ant-${title}`
}
/**
 * 权限控制
 * @param to
 * @param from
 * @param next
 * @returns {Promise<void>}
 */
export const permissionController = async (to, from, next) => {
  //配置路由加载动画效果
  NProgress.start();
  const { meta: { title = '' } } = to
  setDocumentTitle(title)
  // 取消未完成的http请求
  await store.dispatch('app/execCancelToken')
  //这里也可以验证权限
  // 前往页面不是登陆，且没有登陆的情况，统一重定向到登陆
  if (!to.fullPath.includes('login') && !localStorage.getItem('pear_admin_ant_token')) {
    next({path: '/login'})
  } else {
    // 如果基本路由中不包含页面前往的路径
    if (!router.getRoutes().map(it => it.path).includes(to.fullPath)) {
      console.log('========== 开始加载用户权限菜单 ==========')
      // 后端返回的为一维数组菜单列表，如果返回的是树结构的菜单用 'user/addUserRouteForTree'
      await store.dispatch('user/addUserRouteForArray')
      // 用户权限菜单保存在vuex中。vuex是不允许在mutations外部改变state中的属性。所以这里简单的深拷贝一份，用于改变component属性的值
      const userRoutes = JSON.parse(JSON.stringify(store.getters.menu))
      // 如果url被改变
      const hasRoute = findRouteForUserRoutes(userRoutes, to.fullPath)
      if (hasRoute) {
        // 为解决刷新页面后页面不显示将用户的权限菜单缓存于LocalStorage。而存放于storage中必然要将数组字符串化，那么对应的() => import('@/views/xx/xx')
        // 异步加载会失效，所以在使用真正添加路由时再生成component的值
        setUserRouteComponent(userRoutes)
        userRoutes.forEach(r => {
          router.addRoute(r)
        })
        next(to.fullPath)
      } else {
        next('/error/404')
      }
    } else {
      next()
    }
  }
}
