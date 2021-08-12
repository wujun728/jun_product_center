// common/http.interceptor.js

// 这里的Vue为Vue对象(非创建出来的实例)，vm为main.js中“Vue.use(httpInterceptor, app)”这一句的第二个参数，
// 为一个Vue的实例，也即每个页面的"this"
// 如果需要了解这个install方法是什么，请移步：https://uviewui.com/components/vueUse.html
const install = (Vue, vm) => {
	// 此为自定义配置参数，具体参数见上方说明
	Vue.prototype.$u.http.setConfig({
		baseUrl: Vue.prototype.baseUrl, // 请求的本域名
		method: 'POST',
		// 设置为json，返回后会对数据进行一次JSON.parse()
		dataType: 'json',
		showLoading: true, // 是否显示请求中的loading
		loadingText: '请求中...', // 请求loading中的文字提示
		loadingTime: 500, // 在此时间内，请求还没回来的话，就显示加载中动画，单位ms
		originalData: false, // 是否在拦截器中返回服务端的原始数据
		loadingMask: true, // 展示loading的时候，是否给一个透明的蒙层，防止触摸穿透
		// // 配置请求头信息
		// header: {
		// 	'content-type': 'application/json;charset=UTF-8'
		// },
	});

	// 请求拦截部分，如配置，每次请求前都会执行
	Vue.prototype.$u.http.interceptor.request = (config) => {
		if(config.url.search(`/wx/user/${Vue.prototype.appid}/login`)===-1 && config.url.search(`/citylife/nocheck`)===-1){
			config.header.Authorization='wx '+uni.getStorageSync('token');
		}else{
			//访问登录等接口
			config.header.Authorization='';
		}
		
		// 引用token
		// 方式一，存放在vuex的token，假设使用了uView封装的vuex方式
		// 见：https://uviewui.com/components/globalVariable.html
		// config.header.token = vm.token;

		// 方式二，如果没有使用uView封装的vuex方法，那么需要使用$store.state获取
		// config.header.token = vm.$store.state.token;

		// 方式三，如果token放在了globalData，通过getApp().globalData获取
		// config.header.token = getApp().globalData.username;

		// 方式四，如果token放在了Storage本地存储中，拦截是每次请求都执行的
		// 所以哪怕您重新登录修改了Storage，下一次的请求将会是最新值
		// const token = uni.getStorageSync('token');
		// config.header.token = token;
		// config.header.Token = 'xxxxxx';

		// 可以对某个url进行特别处理，此url参数为this.$u.get(url)中的url值
		// if (config.url == '/user/login') config.header.noToken = true;
		// 最后需要将config进行return
		return config;
		// 如果return一个false值，则会取消本次请求
		// if(config.url == '/user/rest') return false; // 取消某次请求
	}
	
	
	// 响应拦截，如配置，每次请求结束都会执行本方法
		Vue.prototype.$u.http.interceptor.response = (res) => {
			return res;
			// if(res.code == 200) {
			// 	// res为服务端返回值，可能有code，result等字段
			// 	// 这里对res.result进行返回，将会在this.$u.post(url).then(res => {})的then回调中的res的到
			// 	// 如果配置了originalData为true，请留意这里的返回值
			// 	return res.result;
			// } else if(res.code == 201) {
			// 	// 假设201为token失效，这里跳转登录
			// 	vm.$u.toast('验证失败，请重新登录');
			// 	setTimeout(() => {
			// 		// 此为uView的方法，详见路由相关文档
			// 		vm.$u.route('/pages/user/login')
			// 	}, 1500)
			// 	return false;
			// } else {
			// 	// 如果返回false，则会调用Promise的reject回调，
			// 	// 并将进入this.$u.post(url).then().catch(res=>{})的catch回调中，res为服务端的返回值
			// 	return false;
			// }
		}
}

export default {
	install
}
