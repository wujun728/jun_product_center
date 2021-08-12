import { getModules } from '@/locales/_utils'
import antdLocal from 'ant-design-vue/es/locale/en_US'
const requireENContext = require.context('./en-US/', false, /[\w+]\w+.(ts)$/)

const enUS = getModules(requireENContext)

export default {
  ...enUS,
  antdLocal
}
