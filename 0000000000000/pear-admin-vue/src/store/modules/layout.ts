import routes from '@/router/routes'
export default {
  namespaced: true,
  state: {
    collapsed: false,
    menuList: routes.find(it => it.path === '/')?.children,
    openKeys: [],
    selectedKeys: []
  },
  mutations: {
    'TOGGLE_COLLAPSED' (state) {
      state.collapsed = !state.collapsed
    },
    'SET_OPEN_KEYS' (state, payload) {
      state.openKeys = payload
    },
    'SET_SELECTED_KEYS' (state, payload) {
      state.selectedKeys = payload
    }
  },
  actions: {
    toggleCollapsed ({ commit }) {
      commit('TOGGLE_COLLAPSED')
    },
    setOpenKeys ({ commit }, payload) {
      commit('SET_OPEN_KEYS', payload)
    },
    setSelectedKeys ({ commit }, payload) {
      commit('SET_SELECTED_KEYS', payload)
    }
  }
}
