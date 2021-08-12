import '@/styles/tailwind.css'
import '@/styles/index.less'
import 'animate.css'
import { createApp, h } from 'vue'
import naiveUI from 'naive-ui'
import App from './App.vue'
import store from './store'
import router from './router'
// svg
import 'virtual:svg-icons-register'

const app = createApp(App)

app.use(store)
app.use(router)
app.use(naiveUI)

app.mount('#app')

// todo: 如果使用isReady(), 需要在加载前把naive-ui 的message 等组件注册到windows上
// router.isReady().then(() => {
//   app.mount('#app')
// })
