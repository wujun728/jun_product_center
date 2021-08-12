import { RouteRecordRaw } from 'vue-router'
import {
  BaseLayout,
  RouterViewLayout,
  OtherView
} from '@/layout'

const routes: RouteRecordRaw[] = [
  {
    path: '/',
    name: 'root',
    redirect: '/dashboard',
    meta: {
      hidden: true
    },
    component: BaseLayout,
    children: [
      {
        path: '/dashboard',
        name: 'dashboard',
        component: RouterViewLayout,
        redirect: { name: 'analysis' },
        meta: {
          icon: 'DashboardOutlined',
          title: 'Dashboard',
          i18nTitle: 'menu.dashboard'
        },
        children: [
          {
            path: 'analysis',
            name: 'analysis',
            meta: {
              title: '分析页',
              i18nTitle: 'menu.dashboard.analysis'
            },
            component: () => import(/* webpackChunkName: "analysis" */ '@/views/dashboard/analysis/index.vue')
          },
          {
            path: 'monitor',
            name: 'monitor',
            meta: {
              title: '监控页',
              i18nTitle: 'menu.dashboard.monitor'
            },
            component: () => import(/* webpackChunkName: "monitor" */ '@/views/dashboard/monitor/index.vue')
          }, {
            path: 'workplace',
            name: 'workplace',
            meta: {
              title: '工作台',
              i18nTitle: 'menu.dashboard.workplace'
            },
            component: () => import(/* webpackChunkName: "workplace" */ '@/views/dashboard/workplace/index.vue')
          }
        ]
      },
      {
        path: '/form',
        name: 'form',
        component: RouterViewLayout,
        redirect: { name: 'basic-form' },
        meta: {
          icon: 'FormOutlined',
          title: '表单页',
          i18nTitle: 'menu.form'
        },
        children: [
          {
            path: 'basic-form',
            name: 'basic-form',
            meta: {
              title: '基础表单',
              i18nTitle: 'menu.form.basic-form'
            },
            component: () => import(/* webpackChunkName: "basic-form" */ '@/views/form/basicForm/index.vue')
          },
          {
            path: 'step-form',
            name: 'step-form',
            meta: {
              title: '分步表单',
              i18nTitle: 'menu.form.step-form'
            },
            component: () => import(/* webpackChunkName: "step-form" */ '@/views/form/stepForm/index.vue')
          },
          {
            path: 'advanced-form',
            name: 'advanced-form',
            meta: {
              title: '高级表单',
              i18nTitle: 'menu.form.advanced-form'
            },
            component: () => import(/* webpackChunkName: "advanced-form" */ '@/views/form/advancedForm/index.vue')
          }
        ]
      },
      {
        path: '/list',
        name: 'list',
        component: RouterViewLayout,
        redirect: { name: 'search' },
        meta: {
          icon: 'TableOutlined',
          title: '列表页',
          i18nTitle: 'menu.list'
        },
        children: [
          {
            path: 'search',
            name: 'search',
            meta: {
              title: '搜索列表',
              i18nTitle: 'menu.list.search-list'
            },
            redirect: { name: 'articles' },
            component: OtherView,
            children: [
              {
                path: 'articles',
                name: 'articles',
                meta: {
                  title: '搜索列表（文章）',
                  i18nTitle: 'menu.list.search-list.articles'
                },
                component: () => import(/* webpackChunkName: "articles" */ '@/views/list/search/articles/index.vue')
              },
              {
                path: 'projects',
                name: 'projects',
                meta: {
                  title: '搜索列表（项目）',
                  i18nTitle: 'menu.list.search-list.projects'
                },
                component: () => import(/* webpackChunkName: "projects" */ '@/views/list/search/projects/index.vue')
              },
              {
                path: 'applications',
                name: 'applications',
                meta: {
                  title: '搜索列表（应用）',
                  i18nTitle: 'menu.list.search-list.applications'
                },
                component: () => import(/* webpackChunkName: "applications" */ '@/views/list/search/applications/index.vue')
              }
            ]
          },
          {
            path: 'table-list',
            name: 'table-list',
            meta: {
              title: '查询表格',
              i18nTitle: 'menu.list.table-list'
            },
            component: () => import(/* webpackChunkName: "table-list" */ '@/views/list/tableList/index.vue')
          },
          {
            path: 'basic-list',
            name: 'basic-list',
            meta: {
              title: '标准列表',
              i18nTitle: 'menu.list.basic-list'
            },
            component: () => import(/* webpackChunkName: "basic-list" */ '@/views/list/basicList/index.vue')
          },
          {
            path: 'card-list',
            name: 'card-list',
            meta: {
              title: '卡片列表',
              i18nTitle: 'menu.list.card-list'
            },
            component: () => import(/* webpackChunkName: "card-list" */ '@/views/list/cardList/index.vue')
          }
        ]
      },
      {
        path: '/profile',
        name: 'profile',
        component: RouterViewLayout,
        redirect: { name: 'basic' },
        meta: {
          icon: 'ProfileOutlined',
          title: '详情页',
          i18nTitle: 'menu.profile'
        },
        children: [
          {
            path: 'basic',
            name: 'basic',
            meta: {
              title: '基础详情页',
              i18nTitle: 'menu.profile.basic'
            },
            component: () => import(/* webpackChunkName: "basic" */ '@/views/profile/basic/index.vue')
          },
          {
            path: 'advanced',
            name: 'advanced',
            meta: {
              title: '高级详情页',
              i18nTitle: 'menu.profile.advanced'
            },
            component: () => import(/* webpackChunkName: "advanced" */ '@/views/profile/advanced/index.vue')
          }
        ]
      },
      {
        path: '/result',
        name: 'result',
        component: RouterViewLayout,
        redirect: { name: 'success' },
        meta: {
          icon: 'CheckCircleOutlined',
          title: '结果页',
          i18nTitle: 'menu.result'
        },
        children: [
          {
            path: 'success',
            name: 'success',
            meta: {
              title: '成功页',
              i18nTitle: 'menu.result.success'
            },
            component: () => import(/* webpackChunkName: "fail" */ '@/views/result/success/index.vue')
          },
          {
            path: 'fail',
            name: 'fail',
            meta: {
              title: '失败页',
              i18nTitle: 'menu.result.fail'
            },
            component: () => import(/* webpackChunkName: "fail" */ '@/views/result/fail/index.vue')
          }
        ]
      },
      {
        path: '/exception',
        name: 'exception',
        component: RouterViewLayout,
        redirect: { name: 'not-permission' },
        meta: {
          icon: 'WarningOutlined',
          title: '异常页',
          i18nTitle: 'menu.exception'
        },
        children: [
          {
            path: '403',
            name: 'not-permission',
            meta: {
              title: '403',
              i18nTitle: 'menu.exception.not-permission'
            },
            component: () => import(/* webpackChunkName: "403" */ '@/views/exception/403/index.vue')
          },
          {
            path: '404',
            name: 'not-find',
            meta: {
              title: '404',
              i18nTitle: 'menu.exception.not-find'
            },
            component: () => import(/* webpackChunkName: "404" */ '@/views/exception/404/index.vue')
          },
          {
            path: '500',
            name: 'server-error',
            meta: {
              title: '500',
              i18nTitle: 'menu.exception.server-error'
            },
            component: () => import(/* webpackChunkName: "500" */ '@/views/exception/500/index.vue')
          }
        ]
      },
      {
        path: '/account',
        name: 'account',
        component: RouterViewLayout,
        redirect: { name: 'center' },
        meta: {
          icon: 'UserOutlined',
          title: '个人页',
          i18nTitle: 'menu.account'
        },
        children: [
          {
            path: 'center',
            name: 'center',
            meta: {
              title: '个人中心',
              i18nTitle: 'menu.account.center'
            },
            component: () => import(/* webpackChunkName: "center" */ '@/views/account/center/index.vue')
          },
          {
            path: 'settings',
            name: 'settings',
            meta: {
              title: '个人设置',
              i18nTitle: 'menu.account.settings'
            },
            component: () => import(/* webpackChunkName: "settings" */ '@/views/account/settings/index.vue')
          }
        ]
      },
      {
        path: '/editor',
        name: 'editor',
        component: RouterViewLayout,
        redirect: { name: 'flow' },
        meta: {
          icon: 'HighlightOutlined',
          title: '图形编辑器',
          i18nTitle: 'menu.editor'
        },
        children: [
          {
            path: 'flow',
            name: 'flow',
            meta: {
              title: '流程编辑器',
              i18nTitle: 'menu.editor.flow'
            },
            component: () => import(/* webpackChunkName: "flow" */ '@/views/editor/flow/index.vue')
          },
          {
            path: 'mind',
            name: 'mind',
            meta: {
              title: '脑图编辑器',
              i18nTitle: 'menu.editor.mind'
            },
            component: () => import(/* webpackChunkName: "mind" */ '@/views/editor/mind/index.vue')
          },
          {
            path: 'koni',
            name: 'koni',
            meta: {
              title: '拓扑编辑器',
              i18nTitle: 'menu.editor.koni'
            },
            component: () => import(/* webpackChunkName: "koni" */ '@/views/editor/koni/index.vue')
          }
        ]
      },
      {
        path: '/:data(.*)',
        meta: {
          hidden: true
        },
        redirect: { name: 'NotFound' }
      }
    ]
  },
  {
    path: '/404',
    name: 'NotFound',
    component: () => import('@/views/exception/404/index.vue')
  }
]

export default routes
