import Vue from 'vue'
import App from './App'
import uView from "uview-ui";
import store from './store'
Vue.use(uView);
Vue.prototype.$store=store
Vue.config.productionTip = false
Vue.prototype.baseUrl="http://localhost:7777/citylife"
Vue.prototype.appid="wxd11371c8691f221d"

App.mpType = 'app'

const app = new Vue({
    ...App,
	store
})

// http拦截器，此为需要加入的内容，如果不是写在common目录，请自行修改引入路径
import httpInterceptor from '@/common/http.interceptor.js'
// 这里需要写在最后，是为了等Vue创建对象完成，引入"app"对象(也即页面的"this"实例)
Vue.use(httpInterceptor, app)

// http接口API集中管理引入部分
import httpApi from '@/common/http.api.js'
Vue.use(httpApi, app)


app.$mount()
