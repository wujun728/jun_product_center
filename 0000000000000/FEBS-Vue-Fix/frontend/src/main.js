import Vue from 'vue'
import Febs from './FEBS'
import router from './router'
import Antd from 'ant-design-vue'
import store from './store'
import request from 'utils/request'
import db from 'utils/localstorage'
import AesEncrypt from 'utils/aesEncrypt'
import VueMeta from 'vue-meta'
import 'ant-design-vue/dist/antd.css'
import echarts from 'echarts'
import 'utils/install'

Vue.config.productionTip = false
Vue.use(Antd)
Vue.use(db)
Vue.use(VueMeta)
Vue.prototype.$echarts = echarts
Vue.use({
  install (Vue) {
    Vue.prototype.$db = db
  }
})

Vue.prototype.$post = request.post
Vue.prototype.$get = request.get
Vue.prototype.$put = request.put
Vue.prototype.$delete = request.delete
Vue.prototype.$export = request.export
Vue.prototype.$download = request.download
Vue.prototype.$upload = request.upload
Vue.prototype.$originalGet = request.originalGet
Vue.prototype.$aesEncrypt = AesEncrypt

/* eslint-disable no-new */
new Vue({
  router,
  store,
  render: h => h(Febs)
}).$mount('#febs')
