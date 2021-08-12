import config from '@/config/pear.config'
import storage from 'store'
export default {
  namespaced: true,
  state: {
    routerLoading: false,
    primaryColor: '',
    language: ''
  },
  getters: {
    primaryColor: state => {
      return state.primaryColor ? state.primaryColor : storage.get('pear_primary_color') ? storage.get('pear_primary_color') : config.primaryColor
    },
    language: state => {
      return state.language ? state.language : storage.get('pear_language') ? storage.get('pear_language') : config.defaultLanguage
    }
  },
  mutations: {
    'TOGGLE_ROUTER_LOADING' (state, payload) {
      state.routerLoading = payload
    },
    'CHANGE_PRIMARY_COLOR' (state, payload) {
      state.primaryColor = payload
      storage.set('pear_primary_color', payload)
    },
    'SET_LANGUAGE' (state, payload) {
      state.language = payload
      storage.set('pear_language', payload)
    }
  },
  actions: {
    toggleRouterLoading ({ commit }, payload) {
      commit('TOGGLE_ROUTER_LOADING', payload)
    },
    changePrimaryColor ({ commit }, payload) {
      commit('CHANGE_PRIMARY_COLOR', payload)
    },
    setLanguage ({ commit }, payload) {
      commit('SET_LANGUAGE', payload)
    }
  }
}
