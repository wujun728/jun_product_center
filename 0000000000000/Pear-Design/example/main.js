import { createApp } from 'vue';
import App from './App.vue';
import PEAR from '../package/index';
import router from "./router";

const app = createApp(App);
app.use(PEAR);
app.use(router);
app.mount('#app');