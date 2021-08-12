import { RouteRecordRaw } from 'vue-router'
import { h } from 'vue'
import Icon from '@/components/BasicComponents/Icons/Icon.vue'
import dynamicRouteMap from './dynamicRoutes'
import { kebabCase } from 'lodash-es'
import type { BackedRouteTree, AppMenuOption } from '@/router/interface'

function generateUserRouteItem(item: BackedRouteTree): RouteRecordRaw {
  const route: RouteRecordRaw = {
    path: item.path,
    name: item.component,
    component: dynamicRouteMap.get('BasicLayout'),
    meta: {
      title: item.title,
      icon: item.icon
    }
  }
  if (item.children && item.children.length !== 0) {
    route.children = generateUserRoutes(item.children)
  } else {
    route.component = dynamicRouteMap.get(item.component)
  }
  return route
}

export function generateUserRoutes(
  backendList: BackedRouteTree[]
): RouteRecordRaw[] {
  // if (isNull(backendList)) return []
  const result: RouteRecordRaw[] = []
  for (let i = 0; i < backendList.length; i++) {
    const item = backendList[i]
    if (!item.deleted && !item.hidden) {
      const route = generateUserRouteItem(item)
      result.push(route)
    }
  }
  return result
}

export function generateUserMenus(
  data: RouteRecordRaw[],
  parentPath = ''
): AppMenuOption[] {
  const result: AppMenuOption[] = []
  for (let i = 0; i < data.length; i++) {
    const it: RouteRecordRaw = data[i]
    if (it.meta && !it.meta.hidden) {
      const icon = it.meta.icon
        ? `ant-design:${kebabCase(it.meta?.icon as string)}`
        : ''
      const item: AppMenuOption = {
        key: parentPath ? `${parentPath}/${it.path}` : it.path,
        label: it?.meta?.title,
        icon: it.meta.icon ? () => h(Icon, { icon }) : undefined
      }
      if (it.children && it?.children?.length > 0) {
        item.children = generateUserMenus(it.children, it.path)
      }
      result.push(item)
    }
  }
  return result
}
