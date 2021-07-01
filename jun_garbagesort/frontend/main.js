import Vue from 'vue'
import App from './App'

Vue.config.productionTip = false

// prod 
// Vue.prototype.serverUrl="https://felix.picp.vip/wx"
// dev
 Vue.prototype.serverUrl="http://localhost:8899/wx"



App.mpType = 'app'

const app = new Vue({
    ...App
})
app.$mount()
