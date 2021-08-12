import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
    affix: true                  if set true, the tag will affix in the tags-view
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  // {
  //   path: '/404',
  //   component: () => import('@/views/404'),
  //   hidden: true
  // },
  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path(.*)',
        component: () => import('@/views/redirect/index')
      }
    ]
  },
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '工具首页', icon: 'el-icon-s-home', affix: true ,noCache: true}
    }]
  },
  {
    path: '/core',
    component: Layout,
    redirect: '/core/connect',
    name: 'core',
    meta: { title: '基础数据', icon: 'el-icon-office-building' },
    children: [{
      path: 'connect',
      name: 'connect',
      component: () => import('@/views/core/connect'),
      meta: { title: '连接管理', icon: 'el-icon-connection' }
    },{
      path: 'classloader',
      name: 'classloader',
      component: () => import('@/views/core/classloaderNew'),
      meta: { title: '类加载器管理', icon: 'tree' }
    }]
  },
  {
    path: '/monitor',
    component: Layout,
    redirect: '/monitor/zookeeper',
    name: 'monitor',
    meta: { title: '数据监控工具', icon: 'el-icon-monitor' },
    children: [{
      path: 'redis',
      name: 'redis',
      component: () => import('@/views/monitor/redis'),
      meta: { title: 'Redis', icon: 'el-icon-coin' }
    },{
      path: 'zookeeper',
      name: 'zookeeper',
      component: () => import('@/views/monitor/zookeeper'),
      meta: { title: 'Zookeeper',icon: 'tree' }
    },{
      path: 'kafka/group',
      name: 'kafkaGroup',
      component: () => import('@/views/monitor/kafkagroup'),
      meta: { title: 'kafka 消费组', icon:'el-icon-notebook-2' }
    },{
      path: 'kafka/topic',
      name: 'kafkaTopic',
      component: () => import('@/views/monitor/kafkatopic'),
      meta: { title: 'kafka 主题管理' , icon:'el-icon-notebook-2' }
    },{
      path: 'quartz',
      name: 'quartz',
      component: () => import('@/views/monitor/quartz'),
      meta: { title: 'quartz 任务管理' , icon:'el-icon-s-operation' }
    },{
      path: 'mongo',
      name: 'mongo',
      component: () => import('@/views/monitor/mongo'),
      meta: { title: 'mongo数据监控' , icon:'el-icon-document' }
    },{
      path: 'elasticsearch',
      name: 'elasticsearch',
      component: () => import('@/views/monitor/elasticsearch'),
      meta: { title: 'elasticsearch监控' , icon:'el-icon-document' }
    }]
  },
  {
    path: '/call',
    component: Layout,
    redirect: '/call/mybatis',
    name: 'call',
    meta: { title: '快速调用', icon: 'el-icon-phone' },
    children: [{
      path: 'mybatis',
      name: 'mybatis',
      component: () => import('@/views/call/mybatis'),
      meta: { title: 'Mybatis',icon:'el-icon-s-cooperation' }
    },{
      path: 'dubbo',
      name: 'dubbo',
      component: () => import('@/views/call/dubbo'),
      meta: { title: 'Dubbo',icon:'el-icon-chat-round' }
    },{
      path: 'soap',
      name: 'soap',
      component: () => import('@/views/call/soap'),
      meta: { title: 'SoapUI',icon:'el-icon-chat-dot-square'}
    },{
      path: 'translate',
      name: 'translate',
      component: () => import('@/views/call/nametool'),
      meta: { title: '取名工具', icon: 'el-icon-data-board' }
    }]
  },
  {
    path: '/database',
    component: Layout,
    alwaysShow: true,
    redirect: '/database/metadata',
    name: 'database',
    meta: { title: '数据库管理', icon: 'el-icon-receiving' },
    children: [{
      path: 'metadata',
      name: 'metadata',
      component: () => import('@/views/database/meta'),
      meta: { title: '数据库元数据', icon: 'el-icon-magic-stick' }
    },{
      path: 'code/generate',
      name: 'codeGenerate',
      component: () => import('@/views/database/codeGenerate'),
      meta: { title: '代码生成', icon: 'el-icon-toilet-paper' }
    },{
      path: 'config/data',
      name: 'configData',
      component: () => import('@/views/database/configdata'),
      meta: { title: '配置数据', icon: 'el-icon-set-up' }
    },{
      path: 'data/export',
      name: 'dataExport',
      component: () => import('@/views/database/dataExport'),
      meta: { title: '数据导出', icon: 'el-icon-set-up' }
    }]
  },
  {
    path: '/other',
    component: Layout,
    alwaysShow: true,
    redirect: '/other/idcard',
    name: 'other',
    meta: { title: '其它工具', icon: 'el-icon-files',front: true },
    children: [{
      path: 'idcard',
      name: 'idcard',
      component: () => import('@/views/other/idcard'),
      meta: { title: '身份证工具', icon: 'el-icon-postcard'}
    },{
      path: 'cron',
      name: 'cron',
      component: () => import('@/views/other/cron'),
      meta: { title: 'cron 表达式',icon:'el-icon-menu'}
    }]
  },
  {
    path: '/docs',
    component: Layout,
    alwaysShow: true,
    redirect: '/docs/swagger',
    name: 'docs',
    meta: { title: '文档工具', icon: 'el-icon-document' },
    children: [{
      path: 'swagger',
      name: 'swagger',
      component: () => import('@/views/docs/swagger'),
      meta: { title: 'swagger 文档',icon:'el-icon-notebook-2'}
    }]
  }
  // 404 page must be placed at the end !!!
  // { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
