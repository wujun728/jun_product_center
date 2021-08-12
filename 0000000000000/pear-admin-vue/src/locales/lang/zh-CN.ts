import { getModules } from '@/locales/_utils'
import antdLocal from 'ant-design-vue/es/locale/zh_CN'

const requireZHContext = require.context('./zh-CN/', false, /[\w+]\w+.(ts)$/)

const zhCN = getModules(requireZHContext)

export default {
  ...zhCN,
  antdLocal
}
