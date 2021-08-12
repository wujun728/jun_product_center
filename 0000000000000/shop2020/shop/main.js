import Vue from 'vue'
import store from './store'
import App from './App'

const msg = (title, duration=1500, mask=false, icon='none')=>{
	//统一提示方便全局修改
	if(Boolean(title) === false){
		return;
	}
	uni.showToast({
		title,
		duration,
		mask,
		icon
	});
}
const json = type=>{
	//模拟异步请求数据
	return new Promise(resolve=>{
		setTimeout(()=>{
			resolve(Json[type]);
		}, 500)
	})
}

const prePage = ()=>{
	let pages = getCurrentPages();
	let prePage = pages[pages.length - 2];
	// #ifdef H5
	return prePage;
	// #endif
	return prePage.$vm;
}


Vue.config.productionTip = false
Vue.prototype.$fire = new Vue();
Vue.prototype.$store = store;
Vue.prototype.$api = {msg, json, prePage};

//封装网络请求
Vue.prototype.sendRequest = function(param,backpage, backtype){
    var _self = this, 
        url = param.url,
        method = param.method,
        header = {},
        data = param.data || {}, 
        token = "",
        hideLoading = param.hideLoading || false;
        
    //拼接完整请求地址
    var requestUrl = this.siteBaseUrl + url;
    //固定参数:仅仅在小程序绑定页面通过code获取token的接口默认传递了参数token = login
    if(!header.token){//其他业务接口传递过来的参数中无token
        token = uni.getStorageSync(this.sessionKey);//参数中无token时在本地缓存中获取
        console.log("当前token:" + token);
        if(!token){//本地无token需重新登录(退出时清缓存token)
            // _self.login(backpage, backtype);
            // return;
        }else{
            header.token = token;
        }
    }
    var timestamp = Date.parse(new Date());//时间戳
    header["timestamp"] = timestamp;
    // #ifdef MP-WEIXIN
    header["appid"] = "75186a364ca149e29b32c82923c30ba3";
     // #endif
    // #ifdef APP-PLUS || H5
    header["appid"] = "75186a364ca149e29b32c82923c30ba3";
    // #endif
    
    //请求方式:GET或POST(POST需配置header: {'content-type' : "application/x-www-form-urlencoded"},)
    if(method){
        method = method.toUpperCase();//小写改为大写
        if(method=="POST"){
            // header = {'content-type' : "application/x-www-form-urlencoded"};
			header["content-type"]="application/json";
         }else{
            header["content-type"]="application/json";
        }
    }else{
        method = "GET";
        header["content-type"]="application/json";
    }
    //用户交互:加载圈
    if (!hideLoading) {
        uni.showLoading({title:'加载中...'});
    }
     console.log("网络请求start");
    //网络请求
    uni.request({
        url: requestUrl,
        method: method,
        header: header,
        data: data,
        success: res => {
            console.log("网络请求success:" + JSON.stringify(res));
            if (res.statusCode && res.statusCode != 200) {//api错误
                uni.showModal({
                    content:"" + res.errMsg
                });
                return;
            }
            if (res.data.code) {//返回结果码code判断:0成功,1错误,-1未登录(未绑定/失效/被解绑)
                if (res.data.code == "10001"||res.data.code == "10002") { 
					uni.setStorageSync(this.sessionKey,''); 
					uni.setStorageSync("unifiedUser",''); 
					uni.navigateTo({
						url: '../public/login',
					})
                    return;
                }
                if (res.data.code == "10000") {
                    uni.showModal({
                        showCancel:false,
                        content:"" + res.data.message
                    });
                    return;
                }
            } else{
                uni.showModal({
                    showCancel:false,
                    content:"No ResultCode:" + res.data.message
                });
                return;
            }
            typeof param.success == "function" && param.success(res.data);
        },
        fail: (e) => {
            console.log("网络请求fail:" + JSON.stringify(e));
            uni.showModal({
                content:"" + e.errMsg
            });
            typeof param.fail == "function" && param.fail(e.data);
        },
        complete: () => {
            console.log("网络请求complete");
            if (!hideLoading) {
                uni.hideLoading();
            }
            typeof param.complete == "function" && param.complete();
            return;
        }
    });
}

//封装文件上传
Vue.prototype.sendUpload = function(param){
    var _self = this, 
        url = param.url,
		method = param.method,
        header = {},
        token = "",
        hideLoading = param.hideLoading || false;
        
    //拼接完整请求地址
    var requestUrl = this.siteBaseUrl + url;
    //固定参数:仅仅在小程序绑定页面通过code获取token的接口默认传递了参数token = login
    if(!header.token){//其他业务接口传递过来的参数中无token
        token = uni.getStorageSync(this.sessionKey);//参数中无token时在本地缓存中获取
        console.log("当前token:" + token);
        if(!token){//本地无token需重新登录(退出时清缓存token)
            // _self.login(backpage, backtype);
            // return;
        }else{
            header.token = token;
        }
    }
    var timestamp = Date.parse(new Date());//时间戳
    header["timestamp"] = timestamp;
    // #ifdef MP-WEIXIN
    header["appid"] = "75186a364ca149e29b32c82923c30ba3";
     // #endif
    // #ifdef APP-PLUS || H5
    header["appid"] = "75186a364ca149e29b32c82923c30ba3";
    // #endif
    
     // header["content-type"]="application/json";
    //用户交互:加载圈
    if (!hideLoading) {
        uni.showLoading({title:'加载中...'});
    }
     console.log("网络请求start");
    //网络请求
    uni.uploadFile({
        url: requestUrl,
        filePath: param.filePath,
		method: method,
		name: param.name,
        header: header,
        formData: param.data,
        success: res => { 
			console.log("网络请求success:" + JSON.stringify(res));
			if (res.statusCode && res.statusCode != 200) {//api错误
			    uni.showModal({
			        content:"" + res.errMsg
			    });
			    return;
			}
			let data=JSON.parse(res.data);
            if (data.code) {//返回结果码code判断:0成功,1错误,-1未登录(未绑定/失效/被解绑)
                if (data.code == "10001"||data.code == "10002") { 
					uni.setStorageSync(this.sessionKey,''); 
					uni.setStorageSync("unifiedUser",''); 
					uni.navigateTo({
						url: '../public/login',
					})
                    return;
                }
                if (data.code == "10000") {
                    uni.showModal({
                        showCancel:false,
                        content:"" + data.message
                    });
                    return;
                }
            } else{
                uni.showModal({
                    showCancel:false,
                    content:"No ResultCode:" + data.message
                });
                return;
            }
            typeof param.success == "function" && param.success(data);
        },
        fail: (e) => {
            console.log("网络请求fail:" + JSON.stringify(e));
            uni.showModal({
                content:"" + e.errMsg
            });
            typeof param.fail == "function" && param.fail(e.data);
        },
        complete: () => {
            console.log("网络请求complete");
            if (!hideLoading) {
                uni.hideLoading();
            }
            typeof param.complete == "function" && param.complete();
            return;
        }
    });
}
 
//wx全局登录
Vue.prototype.loginWx = function(backpage, backtype){
    var _self = this;
    uni.login({
        success:function(res){
            _self.requestData({
                url : "user/login",
                data : {
                    code : res.code, 
                    token : "login"
                },
                success : function(res2){
                    if (res2.data.errCode == "0") {//用户存在:存储token
                        uni.setStorageSync(_self.sessionKey,res2.data.token);
                    } else if (res2.data.errCode == "0") {//用户不存在:调转到绑定页面
                        uni.redirectTo({url:'../binding/binding?backpage='+backpage+'&backtype='+backtype});
                        return false;
                    }
                }
            },backpage, backtype)
        },
        fail:function(e){
            console.log("微信login接口调用失败:" + JSON.stringify(e));
        }
    });
    return;
}
 
Vue.prototype.siteHost = 'http://localhost:8080'; 
Vue.prototype.siteBaseUrl = Vue.prototype.siteHost+'/app/'; 
Vue.prototype.sessionKey = "shuogesha_tk";

App.mpType = 'app'

const app = new Vue({
     ...App
})
app.$mount()