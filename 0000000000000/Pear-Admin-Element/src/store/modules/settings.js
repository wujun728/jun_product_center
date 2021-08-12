import defaultSettings from '@/settings'

const { showSettings, fixedHeader, sideLogo, muiltTab } = defaultSettings

const state = {
  showSettings: showSettings,
  fixedHeader: fixedHeader,
  sideLogo: sideLogo,
  muiltTab: muiltTab
}

const mutations = {
  CHANGE_SETTING: (state, { key, value }) => {
    if (state.hasOwnProperty(key)) {
      state[key] = value
    }
  }
}

const actions = {
  changeSetting({ commit }, data) {
    commit('CHANGE_SETTING', data)
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

