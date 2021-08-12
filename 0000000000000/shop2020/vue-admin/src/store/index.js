
import Vue from 'vue'
import Vuex from 'vuex'

import menu from './modules/menu'
import app from './modules/app'
import websocket from './modules/websocket'
Vue.use(Vuex)

const state = {
  login: false,   // 是否登录
  unifieduser: null, // 用户信息
  menuRouteLoaded:false//重新加载菜单
}

const store = new Vuex.Store({
  modules: {
      app: app,
      menu: menu,
      websocket:websocket
  }
})

export default store
