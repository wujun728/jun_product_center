// 进一步封装axios
import axios from "axios";
import qs from 'qs' //引入qs

const http = axios.create({
	baseURL: "http://localhost:12301", //设置请求的base url
	timeout: 40000 //超时时长
});

//添加请求拦截器
http.interceptors.request.use(config => {
	//判断当前请求是否设置了不显示Loading
	uni.showLoading({
		title: '加载中'
	});
	try {
		let value = uni.getStorageSync('authorization');
		if (value) {
			config.headers.authorization = a;
		}
	} catch (e) {
		// error
	}
	return config;
}, err => {
	//判断当前请求是否设置了不显示Loading
	uni.hideLoading();
	return Promise.resolve(err);
});
//响应拦截器
http.interceptors.response.use(
	response => {
		uni.hideLoading();
		return response;
	},
	error => {
		uni.hideLoading();
		return Promise.reject(error);
	}
);
/**
 * 
 * @param {*} url 地址
 * @param {*} params 参数
 */
function Post(url, params, headers) {
	params = qs.stringify(params);
	return new Promise((resolve) => {
		http.post(url, params, headers).then(res => {
			resolve(res.data);
		}).catch((error) => {
			reject(error);
		})
	});
}
/**
 * 
 * @param {*} url 地址
 * @param {*} params 参数
 */
function Get(url, params) {
	return new Promise((resolve, reject) => {
		http.get(url, params).then(res => {
			resolve(res.data);
		}).catch((error) => {
			reject(error);
		})
	});
}

function Upload(url, params) {
	return axios({
		method: 'post',
		url: url,
		data: params,
		headers: {
			'Content-Type': 'multipart/form-data'
		}
	});
}
http.defaults.adapter = function(config) {
	return new Promise((resolve, reject) => {
		var settle = require('axios/lib/core/settle');
		var buildURL = require('axios/lib/helpers/buildURL');
		uni.request({
			method: config.method.toUpperCase(),
			url: config.baseURL + buildURL(config.url, config.params, config.paramsSerializer),
			header: config.headers,
			data: config.data,
			dataType: config.dataType,
			responseType: config.responseType,
			sslVerify: config.sslVerify,
			complete: function complete(response) {
				response = {
					data: response.data,
					status: response.statusCode,
					errMsg: response.errMsg,
					header: response.header,
					config: config
				};

				settle(resolve, reject, response);
			}
		})
	})
}
export {
	Post,
	Get,
	Upload
}
