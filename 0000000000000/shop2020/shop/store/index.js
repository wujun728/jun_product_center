import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = new Vuex.Store({
	state: {
		hasLogin: false,
		unifiedUser: {},
	},
	mutations: {
		login(state, provider) {
   			state.hasLogin = true;
			state.unifiedUser = provider.unifiedUser;
			uni.setStorage({//缓存用户登陆状态
			    key: 'unifiedUser',  
			    data: provider.unifiedUser  
			}) 
			console.log(state.unifiedUser);
		},
		logout(state) {
  			state.hasLogin = false;
			state.unifiedUser = {};
			uni.removeStorage({  
                key: 'unifiedUser'  
            })  
		}
	},
	actions: {
	
	}
})

export default store
