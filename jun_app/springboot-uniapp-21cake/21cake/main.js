import Vue from 'vue'
import App from './App'

Vue.config.productionTip = false;

App.mpType = 'app';


import {
	Get,
	Post,
	Upload
} from '@/utils/http.js';

Vue.prototype.POST = Post;
Vue.prototype.GET = Get;
Vue.prototype.UPLOAD = Upload;

import uView from "uview-ui";
Vue.use(uView);
const app = new Vue({
	...App
})
app.$mount()
