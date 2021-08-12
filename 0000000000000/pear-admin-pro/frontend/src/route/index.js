import {createRouter, createWebHashHistory} from 'vue-router';
import { permissionController } from "@/route/permission";
import routes from './module/base-routes';
import NProgress from "nprogress";
import "nprogress/nprogress.css";

// 构建路由
const router = createRouter({
  history: createWebHashHistory(),
  routes: routes
})

// 前置拦截
router.beforeEach(permissionController)

// 后置拦截
router.afterEach(() => {
  NProgress.done();
})

export default router
