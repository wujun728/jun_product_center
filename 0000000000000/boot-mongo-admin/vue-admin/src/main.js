// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import Router from 'vue-router'
import router from './router'
import store from './store'
import ElementUI from 'element-ui'

import 'element-ui/lib/theme-chalk/index.css'
import 'font-awesome/css/font-awesome.min.css'

import '@/icons' // icon
import './permission' // 资源拦截和路由
import Auth from './utils/auth' // 权限控制

import echarts from 'echarts'
Vue.prototype.$echarts = echarts

import { get_dictionary_list } from '@/api/common'
// // WebSocket封装方法
// import * as socketApi from './utils/socket'

Vue.config.productionTip = false
Vue.prototype.get_dictionary_list = get_dictionary_list //获取数据字典
    // Vue.prototype.socketApi = socketApi
Vue.use(ElementUI)
Vue.use(Router)
Vue.use(Auth)

/* eslint-disable no-new */
new Vue({
    el: '#app',
    store,
    router,
    render: h => h(App)
})
