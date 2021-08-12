import { RouterViewLayout } from '@/layout'

export const dynamicRoutes = {
  baseRouterLayout: RouterViewLayout,
  'test-dynamic-route': () => import(/* webpackChunkName: "flow" */ '@/views/editor/flow/index.vue'),
  'test-dynamic-route2': () => import(/* webpackChunkName: "flow" */ '@/views/editor/flow/index.vue')
}

export const dynamicBase = [
  {
    path: '/test',
    name: 'test',
    redirect: { name: 'test-dynamic-route' },
    meta: {
      icon: 'HighlightOutlined',
      title: '测试',
      i18nTitle: 'menu.editor'
    },
    children: [
      {
        path: 'test-dynamic-route',
        name: 'test-dynamic-route',
        meta: {
          title: '测试动态路由1',
          i18nTitle: 'menu.editor.flow'
        },
        component: () => import(/* webpackChunkName: "flow" */ '@/views/editor/flow/index.vue')
      },
      {
        path: 'test-dynamic-route2',
        name: 'test-dynamic-route2',
        meta: {
          title: '测试动态路由2',
          i18nTitle: 'menu.editor.mind'
        },
        component: () => import(/* webpackChunkName: "mind" */ '@/views/editor/mind/index.vue')
      },
      {
        path: 'test-dynamic-route3',
        name: 'test-dynamic-route3',
        meta: {
          title: '测试动态路由3',
          i18nTitle: 'menu.editor.koni'
        },
        component: () => import(/* webpackChunkName: "koni" */ '@/views/editor/koni/index.vue')
      }
    ]
  }
]

export const generatorDynamicRoute = (menus, routeMap) => {
  menus.forEach(it => {
    const hasChild = (it.children && it.children.length > 0)
    it.component = routeMap[it.name] ? routeMap[it.name] : routeMap.baseRouterLayout
    if (hasChild) {
      it.redirect = { name: it.children[0].name }
      it.chidlren = generatorDynamicRoute(it.children, routeMap)
    }
  })
}
//
// export const getRouteNames = route => {
//   const result: string[] = []
//   result.push(route.name)
//   if (route.children) {
//     const hash = route.children.map(it => {
//       return it.name
//     })
//     result.concat(hash)
//   }
//   return result
// }
