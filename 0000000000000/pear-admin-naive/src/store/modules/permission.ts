import { until } from '@vueuse/core'
import { AppMenuOption } from '@/types/menu'
import { defineStore } from 'pinia'
import basicRoutes from '@/router/basicRoutes'
import { RouteRecordRaw } from 'vue-router'
import { unref } from 'vue'
import useAppFetch from '@/api/useAppFetch'
import { AUTH_API } from '@/api/moduels/auth'
import { generateUserMenus, generateUserRoutes } from '@/router/routeUtil'

interface PermissionState {
  token: Record<string, any>
  permissionRoutes: RouteRecordRaw[]
  menus: AppMenuOption[]
  allRoutes: RouteRecordRaw[]
}
export const usePermission = defineStore({
  id: 'permission',
  state: (): PermissionState => ({
    token: {},
    permissionRoutes: [],
    menus: [],
    allRoutes: []
  }),
  getters: {
    getToken: (state) => {
      if (Object.keys(state.token).length !== 0) {
        return state.token
      }
      return JSON.parse(localStorage.getItem('token') as string)
    },
    getAllRoutes: (state) => {
      const { permissionRoutes } = state
      const allRoutes = basicRoutes.concat(permissionRoutes)
      return allRoutes
    },
    getPermissionRoutes: (state) => {
      return state.permissionRoutes
    },
    getMenus: (state) => {
      const { allRoutes } = state
      const menus = generateUserMenus([
        ...basicRoutes,
        ...allRoutes
      ]) as AppMenuOption[]
      return menus
    }
  },
  actions: {
    async setRoutes() {
      const { data, isFinished } = useAppFetch(
        AUTH_API.getMenu,
        {
          method: 'get'
        },
        { immediate: true }
      ).json()
      await until(isFinished).toBe(true)
      const backendMenus = unref(data)
      const userRoutes = generateUserRoutes(backendMenus)
      this.permissionRoutes = userRoutes
      this.allRoutes = basicRoutes.concat(userRoutes) as RouteRecordRaw[]
      // localStorage.setItem('backendRoutes', JSON.stringify(userRoutes))
      return userRoutes
    },
    setToken(name: string, token: { tokenKey: string; token: string }) {
      // const tokenRef = useStorage(name, token)
      localStorage.setItem(name, JSON.stringify(token))
      this.token = token
    },
    clear() {
      this.token = {}
      localStorage.removeItem('token')
    }
  }
})
