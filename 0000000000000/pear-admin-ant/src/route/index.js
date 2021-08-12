// 可以根据路由模式的不同，后面俩可以只引用一个
import {createRouter, createWebHashHistory} from 'vue-router'
// 引入基础路由
// import routes from './routes.js'
import routes from './module/base-routes'
import NProgress from "nprogress";
import "nprogress/nprogress.css";
import { permissionController } from "@/route/permission";

const router = createRouter({
  // 使用 hash 模式构建路由（ url中带 # 号的那种)
  history: createWebHashHistory(),
  // 使用 history 模式构建路由 （ url 中没有 # 号，但生产环境需要特殊配置）
  // history: createWebHistory(),
  routes
})

router.beforeEach(permissionController)

router.afterEach((to, from) => {
  NProgress.done();
})

export default router
