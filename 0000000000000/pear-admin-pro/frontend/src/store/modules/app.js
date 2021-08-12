import config from '@/pear';

const state = {
  language: config.defaultLanguage == null ? "zh-CN" : localStorage.getItem("pear_lang") == null ? config.defaultLanguage : localStorage.getItem("pear_lang"),
  cancelToken: []
}

const mutations = {
  PUSH_CANCEL_TOKEN (state, payload) {
    state.cancelToken.push(payload.cancelToken)
  },
  EXEC_CANCEL_TOKEN (state) {
    state.cancelToken.forEach(executor => {
      executor('路由跳转取消上个页面的请求')
    })
    state.cancelToken = []
  },
  SET_LANGUAGE (state, payload) {
    state.language = payload
    localStorage.setItem('pear_lang', payload)
  }
}

const actions = {
  execCancelToken ({commit}) {
    return new Promise(resolve => {
      commit('EXEC_CANCEL_TOKEN');
      resolve()
    })
  },
  setLanguage({commit}, payload) {
    commit('SET_LANGUAGE', payload)
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}