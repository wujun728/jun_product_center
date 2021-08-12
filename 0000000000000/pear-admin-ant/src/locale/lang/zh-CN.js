import { getModule } from '@/tools/common'
import antLocal from "ant-design-vue/es/locale/zh_CN";

const requireZHContext = require.context('./zh-CN/', false, /[\w+].(js)$/)

const zhCN = getModule(requireZHContext)

export default {
  ...zhCN,
  antLocal
}
