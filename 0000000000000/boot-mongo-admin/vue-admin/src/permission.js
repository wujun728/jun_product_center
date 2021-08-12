import router from './router'
import store from './store'
import { Message } from 'element-ui'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style

const Index = () =>
    import ('/page/index.vue')

import { getStore } from '/utils/storage'
import { getAllMenusByUser, getAllPermsByUser } from '@/api/common'


NProgress.configure({ showSpinner: false }) // NProgress Configuration

const whiteList = ['/login'] // 不重定向白名单

router.beforeEach(async(to, from, next) => {
    // start progress bar
    NProgress.start()
    let token = getStore('shuogesha_auth_id')
    let unifieduser = getStore('unifieduser')
    if (token && unifieduser) {
        // 访问服务器缓存数据，判断当前token是否失效
        if (to.path === '/login') {
            return next({ path: '/' })
            NProgress.done() // if current page is dashboard will not trigger	afterEach hook, so manually handle it
        }
        let unifieduser = JSON.parse(getStore('unifieduser'))
        addDynamicMenuAndRoutes(unifieduser.id, to, from)
    } else {
        //如果不再白名单内跳转到首页
        if (whiteList.indexOf(to.path) === -1) {
            return next({
                path: '/login',
                query: {
                    redirect: to.fullPath
                }
            })
            NProgress.done()
        }
    }
    // 加载动态菜单和路由
    /* 路由发生变化修改页面title */
    if (to.meta.title) {
        document.title = to.meta.title
    }
    // document.title = getPageTitle(to.meta.title)
    return next()
})

router.afterEach(() => {
    // finish progress bar
    NProgress.done()
})


/**
 * 加载动态菜单和路由
 */
function addDynamicMenuAndRoutes(userId, to, from) {
    if (store.state.app.menuRouteLoaded) {
        console.log('动态菜单和路由已经存在.')
        return
    }

    getAllMenusByUser(userId).then(res => {
            // 添加动态路由菜单
            let dynamicRoutes = addDynamicRoutes(res.data)
                // 处理静态组件绑定路由
            handleStaticComponent(router, dynamicRoutes)
            router.addRoutes(router.options.routes)
                // 保存加载状态
            store.commit('menuRouteLoaded', true)
                // 保存菜单树
            store.commit('setNavTree', res.data)
        }).then(res => {
            getAllPermsByUser(userId).then(res => {
                // 保存用户权限标识集合
                store.commit('setPerms', res.data)
            })
        })
        .catch(function(res) {})
}
/**
 * 添加动态(菜单)路由
 * @param {*} menuList 菜单列表
 * @param {*} routes 递归创建的动态(菜单)路由
 */
function addDynamicRoutes(menuList = [], routes = []) {
    var temp = [];
    for (var i = 0; i < menuList.length; i++) {
        //每次都把按钮的路由注册进去
        // if (menuList[i].operate && menuList[i].operate.length >= 1) {
        //     temp = temp.concat(menuList[i].operate)
        // }
        //如果是菜单
        if (menuList[i].path && /\S/.test(menuList[i].path)) {
            menuList[i].path = menuList[i].path.replace(/^\//, '')
                // 创建路由配置
            var route = {
                path: menuList[i].path,
                component: Index,
                name: menuList[i].name,
                meta: {
                    icon: menuList[i].icon,
                    index: menuList[i].id,
                    title: menuList[i].name
                }
            }
            try {
                // 根据菜单URL动态加载vue组件，这里要求vue组件须按照url路径存储
                // 如url="sys/user"，则组件路径应是"@/views/sys/user.vue",否则组件加载不到
                let array = menuList[i].path.split('/')
                let url = ''
                for (let i = 0; i < array.length; i++) {
                    url += array[i].substring(0, 1) + array[i].substring(1) + '/'
                        // url += array[i].substring(0,1).toUpperCase() + array[i].substring(1) + '/'
                }
                url = url.substring(0, url.length - 1)
                route['component'] = resolve => require([`@/page/${url}`], resolve)
            } catch (e) {}
            routes.push(route);
        }
        if (menuList[i].children && menuList[i].children.length >= 1) {
            addDynamicRoutes(menuList[i].children, routes);
        }
    }
    // if (temp.length >= 1) {
    //     addDynamicRoutes(temp, routes)
    // } else {
    //     console.log('动态路由加载...')
    //     console.log('动态路由加载完成.')
    // }
    // console.log(JSON.stringify(temp));
    return routes
}

/**
 * 处理路由到本地直接指定页面组件的情况
 * 比如'代码生成'是要求直接绑定到'Generator'页面组件
 */
function handleStaticComponent(router, dynamicRoutes) {
    // for(let j=0;j<dynamicRoutes.length; j++) {
    //   if(dynamicRoutes[j].name == '代码生成') {
    //     dynamicRoutes[j].component = Generator
    //     break
    //   }
    // }
    router.options.routes[0].children = router.options.routes[0].children.concat(dynamicRoutes)
}