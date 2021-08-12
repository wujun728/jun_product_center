import { ajax_logout } from '@/api/common'
import { getStore,setStore } from '/utils/storage'

export default {
  state: {
    token:null,  // 导航栏收缩状态
    collapse:false,  // 导航栏收缩状态
    menuRouteLoaded:false,   // 菜单和路由是否已经加载
    unifieduser:null,   // 菜单和路由是否已经加载
    user:null,   // 菜单和路由是否已经加载
    perms: [],  // 用户权限标识集合
    // 主入口标签页
    mainTabs: [],
    // 当前标签页名
    mainTabsActiveName: ''
  },
  getters: {
    collapse(state){// 对应着上面state
      return state.collapse
    }
  },
  mutations: {
    setToken(state, token){  // user
      state.token = token;
    },
    onCollapse(state){  // 改变收缩状态
        state.collapse = !state.collapse
    },
    menuRouteLoaded(state, menuRouteLoaded){  // 改变菜单和路由的加载状态
        state.menuRouteLoaded = menuRouteLoaded;
    },
    setPerms(state, perms){  // 用户权限标识集合
        state.perms = perms;
    },
    setUnifieduser(state, unifieduser){  // 用户权限标识集合
      state.unifieduser = unifieduser;
    },
    setUser(state, user){  // user
      state.user = user;
    },
    updateMainTabs (state, tabs) {//table
      state.mainTabs = tabs
    },
    updateMainTabsActiveName (state, name) {//当前
      state.mainTabsActiveName = name
    }

  },
  actions: {
    logout({ commit, state, dispatch }) {
      ajax_logout().then(() => {
        setStore('shuogesha_auth_id','');
        setStore('unifieduser','');
        return new Promise((resolve, reject) => {
            // reset visited views and cached views
            // to fixed https://github.com/PanJiaChen/vue-element-admin/issues/2485
            dispatch('login', null, {})

            resolve()
        })
      }).catch(error => {
        setStore('shuogesha_auth_id','');
        setStore('unifieduser','');
        // reject(error)
      })
    },
  }
}
