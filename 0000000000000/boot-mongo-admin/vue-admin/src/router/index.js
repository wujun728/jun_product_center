import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const Login = () =>
    import ('/page/login/login.vue')
const Index = () =>
    import ('/page/index.vue')
const Home = () =>
    import ('/page/home/home.vue')
const Page404 = () =>
    import ('/page/404.vue')

let routes = [{
        path: '/',
        component: Index,
        name: '首页',
        redirect: '/home',
        children: [
            { name: '欢迎页', path: 'home', component: Home }
        ],
        meta: {
            title: '后台管理系统'
        }
    },
    {
        path: '/login',
        name: 'login',
        component: () =>
            import ('@/page/login/login.vue'),
        meta: {
            title: '用户登录'
        }
    },
    // {
    //     path: '/config/config',
    //     name: Index,
    //     component: () =>
    //         import ('@/page/config/config.vue'),
    //     meta: {
    //         title: '系统设置'
    //     }
    // },
    // {
    //     path: '/app',
    //     component: Index,
    //     redirect: '/app/list',
    //     name: '应用管理',
    //     meta: {
    //         title: '应用管理',
    //         icon: 'example'
    //     },
    //     children: [{
    //         path: 'list',
    //         component: () =>
    //             import ('@/page/app/list'),
    //         name: '应用管理',
    //         meta: { title: '应用管理列表', icon: 'list' }
    //     }]
    // },
    // {
    //     path: '/dictionaryCtg',
    //     component: Index,
    //     redirect: '/dictionaryCtg/list',
    //     name: '字典管理',
    //     meta: {
    //         title: '字典管理',
    //         icon: 'example'
    //     },
    //     children: [{
    //         path: 'list',
    //         component: () =>
    //             import ('@/page/dictionaryCtg/list'),
    //         name: '字典管理',
    //         meta: { title: '字典管理列表', icon: 'list' }
    //     }]
    // },
    // {
    //     path: '/dictionary',
    //     component: Index,
    //     redirect: '/dictionary/list',
    //     name: '字典管理',
    //     meta: {
    //         title: '字典管理',
    //         icon: 'example'
    //     },
    //     children: [{
    //         path: 'list',
    //         component: () =>
    //             import ('@/page/dictionary/list'),
    //         name: '字典代码',
    //         meta: { title: '字典管理列表', icon: 'list' }
    //     }]
    // },
    // {
    //     path: '/menu',
    //     component: Index,
    //     redirect: '/menu/list',
    //     name: '菜单管理',
    //     meta: {
    //         title: '菜单管理',
    //         icon: 'example'
    //     },
    //     children: [{
    //         path: 'list',
    //         component: () =>
    //             import ('@/page/menu/list'),
    //         name: '菜单管理',
    //         meta: { title: '菜单管理列表', icon: 'list' }
    //     }]
    // },
    // {
    //     path: '/role',
    //     component: Index,
    //     redirect: '/role/list',
    //     name: '角色管理',
    //     meta: {
    //         title: '角色管理',
    //         icon: 'example'
    //     },
    //     children: [{
    //         path: 'list',
    //         component: () =>
    //             import ('@/page/role/list'),
    //         name: '角色管理',
    //         meta: { title: '角色管理列表', icon: 'list' }
    //     }]
    // },
    // {
    //     path: '/user',
    //     component: Index,
    //     redirect: '/user/list',
    //     name: '管理员管理',
    //     meta: {
    //         title: '管理员管理',
    //         icon: 'example'
    //     },
    //     children: [{
    //         path: 'list',
    //         component: () =>
    //             import ('@/page/user/list'),
    //         name: '管理员管理',
    //         meta: { title: '管理员管理列表', icon: 'list' }
    //     }]
    // },
    // {
    //     path: '/unifiedUser',
    //     component: Index,
    //     redirect: '/unifiedUser/list',
    //     name: '用户管理',
    //     meta: {
    //         title: '用户管理',
    //         icon: 'example'
    //     },
    //     children: [{
    //         path: 'list',
    //         component: () =>
    //             import ('@/page/unifiedUser/list'),
    //         name: '用户管理',
    //         meta: { title: '用户管理列表', icon: 'list' }
    //     }]
    // },
    // {
    //   path: '/scheduleJob',
    //   component: Index,
    //   redirect: '/scheduleJob/list',
    //   name: '调度任务',
    //   meta: {
    //     title: '调度任务',
    //     icon: 'example'
    //   },
    //   children: [
    //     {
    //       path: 'list',
    //       component: () => import('@/page/scheduleJob/list'),
    //       name: '调度任务',
    //       meta: { title: '调度任务列表', icon: 'list' }
    //     }
    //   ]
    // },
    // {
    //   path: '/jobLog',
    //   component: Index,
    //   redirect: '/jobLog/list',
    //   name: '任务日志',
    //   meta: {
    //     title: '任务日志',
    //     icon: 'example'
    //   },
    //   children: [
    //     {
    //       path: 'list',
    //       component: () => import('@/page/jobLog/list'),
    //       name: '任务日志',
    //       meta: { title: '任务日志列表', icon: 'list' }
    //     }
    //   ]
    // },
    // {
    //     path: '/systemLog',
    //     component: Index,
    //     redirect: '/systemLog/list',
    //     name: '系统日志',
    //     meta: {
    //         title: '系统日志',
    //         icon: 'example'
    //     },
    //     children: [{
    //         path: 'list',
    //         component: () =>
    //             import ('@/page/systemLog/list'),
    //         name: '系统日志',
    //         meta: { title: '系统日志列表', icon: 'list' }
    //     }]
    // },
    // {
    //   path: '/adsense',
    //   component: Index,
    //   redirect: '/adsense/list',
    //   name: '广告位管理',
    //   meta: {
    //     title: '广告位管理',
    //     icon: 'example'
    //   },
    //   children: [
    //     {
    //       path: 'list',
    //       component: () => import('@/page/adsense/list'),
    //       name: '广告位管理',
    //       meta: { title: '广告位列表', icon: 'list' }
    //     }
    //   ]
    // },
    // {
    //   path: '/ad',
    //   component: Index,
    //   redirect: '/ad/list',
    //   name: '广告管理',
    //   meta: {
    //     title: '广告管理',
    //     icon: 'example'
    //   },
    //   children: [
    //     {
    //       path: 'list',
    //       component: () => import('@/page/ad/list'),
    //       name: '广告管理',
    //       meta: { title: '广告列表', icon: 'list' }
    //     }
    //   ]
    // },
    // {
    //   path: '/channel',
    //   component: Index,
    //   redirect: '/channel/list',
    //   name: '栏目管理',
    //   meta: {
    //     title: '栏目管理',
    //     icon: 'example'
    //   },
    //   children: [
    //     {
    //       path: 'list',
    //       component: () => import('@/page/channel/list'),
    //       name: '栏目管理',
    //       meta: { title: '栏目列表', icon: 'list' }
    //     }
    //   ]
    // },
    // {
    //   path: '/content',
    //   component: Index,
    //   redirect: '/content/list',
    //   name: '内容管理',
    //   meta: {
    //     title: '内容管理',
    //     icon: 'example'
    //   },
    //   children: [
    //     {
    //       path: 'list',
    //       component: () => import('@/page/content/list'),
    //       name: '内容管理',
    //       meta: { title: '内容列表', icon: 'list' }
    //     }
    //   ]
    // },
    {
        path: '/404',
        name: '404',
        component: Page404,
        meta: {
            title: '404'
        }
    },
    { path: '*', redirect: '/home' }
]

export default new VueRouter({
    routes: routes
})

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}