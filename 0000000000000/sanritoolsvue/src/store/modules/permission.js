import { constantRoutes } from '@/router'
import core from '@/api/core'

const state = {
  routes: [],
  addRoutes: []
}

const mutations = {
  SET_ROUTES: (state, routes) => {
    state.addRoutes = routes
    state.routes = routes
  }
}

export function filterSupportRoutes(routes,plugins) {
  const res = []

  let pluginGroup = {};
  for (let i = 0; i < plugins.length; i++) {
    let plugin = plugins[i].pluginDto;
    pluginGroup[plugin.module] = pluginGroup[plugin.module] || [];
    pluginGroup[plugin.module].push(plugins[i]);
  }

  for (let m = 0; m < routes.length; m++) {
    if (!routes[m].name || routes[m].meta.front){
      res.push(routes[m]);
      continue;
    }
    if (routes[m].name in pluginGroup){
      let groupItem = pluginGroup[routes[m].name];
      if (groupItem.length > 0){
        let filters = [];
        for (let j = 0; j < routes[m].children.length; j++) {
          // if (!routes[m].children[j].meta.backend){
          //   filters.push(routes[m].children[j]);
          //   continue;
          // }
          for (let k = 0; k < groupItem.length; k++) {
            if (routes[m].children[j].name === groupItem[k].pluginDto.name || routes[m].children[j].meta.front){
              routes[m].children[j].meta.key = routes[m].name+':'+routes[m].children[j].name;
              filters.push(routes[m].children[j]);
            }
          }
        }
        routes[m].children = filters;

        res.push(routes[m]);
      }
    }
  }
  return res
}

const actions = {
  generateRoutes({ commit }) {
    return new Promise(resolve => {
      // 加载后台的支持插件
      core.plugins().then(res => {
        let supportRoutes = filterSupportRoutes(constantRoutes,res.data);
        commit('SET_ROUTES', supportRoutes)
        resolve(supportRoutes)
      }).catch(res => {
        resolve(constantRoutes)
        commit('SET_ROUTES', constantRoutes)
      })
    })
  }
}


export default {
  namespaced: true,
  state,
  mutations,
  actions
}
