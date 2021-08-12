import { preRouter } from '../commons/PRE_ROUTER'

//不需要渲染框架的页面
export const noFrames = [
  `${preRouter}/login`
];

// 不需要登录就可以访问的页面路径
export const noAuths = [
  `${preRouter}/login`
];

// 需要keep alive 页面
export const keepAlives = [
  {
      path:`${preRouter}/login`,
      keepAlive: false,
  },
];

//外部来源路由
const orderRoutes=[
  
];
const routes = [
  {
    path: `${preRouter}/`,
    exact:true,
    component: () => import('../pages/home/index.jsx'),
  },
  {
    path: `${preRouter}/login`,
    component: () => import('../pages/login/index.jsx'),
  },
  {
    path: `${preRouter}/users`,
    component: () => import('../pages/users/index.jsx'),
  },
  {
    path: `${preRouter}/users/:id`,
    component: () => import('../pages/user-center/index.jsx'),
  },
  {
    path: `${preRouter}/roles`,
    component: () => import('../pages/roles/index.jsx'),
  },
  {
    path: `${preRouter}/menus`,
    component: () => import('../pages/menus/index.jsx'),
  },
  {
    path: `${preRouter}/department`,
    component: () => import('../pages/department/index.jsx'),
  },
  {
    path: `${preRouter}/dictionaries`,
    component: () => import('../pages/dictionaries/index.jsx'),
  },
  {
    path: `${preRouter}/iframe_page_/:src`,
    component: () => import('../pages/iframe/index.jsx'),
  },
];

orderRoutes.forEach(item => {
  let num = 0
  routes.some(val => {
      num++
      if (val.path === item.path) {
          val.component = item.component
          return true
      } else if (num === routes.length) {
        routes.push(Object.assign({}, item))
      }
  })
})
export default routes;
