import type { VNodeChild } from 'vue'

export interface BackedRouteTree {
  code?: string | null
  component: string
  createBy?: string
  createTime?: string | null
  deleted: boolean
  enable: boolean
  hidden: boolean | null
  i18n: string
  icon: string | undefined | null
  id: string | number
  link?: null | string
  parent: string | '0'
  path: string
  remark?: null | string
  sort: number
  title: string
  type: string
  updateBy: null | string
  updateTime: null | string
  children: BackedRouteTree[] | undefined
}

export interface AppMenuOption {
  label: string | unknown | undefined
  key: string | undefined
  children?: AppMenuOption[]
  icon?: () => VNodeChild
}
