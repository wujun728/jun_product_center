import db from 'utils/localstorage'

export default {
  namespaced: true,
  state: {
    dicts: db.get('DICTS')
  },
  mutations: {
    setDicts (state, val) {
      db.save('DICTS', val)
      state.dicts = val
    }
  }
}
