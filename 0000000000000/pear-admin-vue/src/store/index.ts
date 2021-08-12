import { createStore } from 'vuex'
import modules from '@/store/stores'
import { isProduction } from '@/utils/utils'

export default createStore({
  modules,
  state: {
  },
  mutations: {
  },
  actions: {
  },
  strict: isProduction
  // plugins: !isProduction ? [createLogger()] : []
})
