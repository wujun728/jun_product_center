import { getModule } from '@/tools/common'
import antLocal from 'ant-design-vue/es/locale/zh_CN'

const requireENContext = require.context('./zh-cn/', false, /[\w+].(js)$/)
const zhCN = getModule(requireENContext)

export default {
  ...zhCN,
  antLocal
}