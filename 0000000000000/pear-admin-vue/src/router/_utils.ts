import config from '@/config/pear.config'
import { createWebHashHistory, createWebHistory } from 'vue-router'

export function createApplicationRouteMode () {
  return config.routeMode === 'hash' ? createWebHashHistory(process.env.BASE_URL) : createWebHistory(process.env.BASE_URL)
}
