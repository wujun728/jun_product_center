import Vue from 'vue'
import Vuex from 'vuex'
import app from './modules/app'
import dict from './modules/dict'
import user from './modules/user'
import tagsView from './modules/tagsView'
import permission from './modules/permission'
import settings from './modules/settings'
import getters from './getters'    
import uiStore from './modules/uiStore.js';
// wangEditor 按钮
import wangEditorStore from './modules/wangEditorStore.js';

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    app,
    dict,
    user,
    tagsView,
    permission,
    settings, 
    uiStore,
    wangEditorStore
  },
  getters
})

export default store
