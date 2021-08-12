import axios from "axios";
import { useStore } from "vuex";
import { notification, message as Msg } from "ant-design-vue";
import store from "../store";
class Http {
  constructor(config) {
    this.config = config || {
      timeout: 6000,
      withCredentials: true,
      baseURL: process.env.VUE_APP_API_BASE_URL,
      headers: {
        "Content-Type": "application/json; charset=utf-8"
      }
    };
  }

  interceptors(instance) {
    /**
     * 请求拦截器
     */
    instance.interceptors.request.use(
      config => {
        const token = localStorage.getItem("pear_admin_ant_token");
        if (token) {
          config.headers["Access-Token"] = token;
        }
        // 请求时缓存该请求，路由跳转时取消, 如果timeout值过大，可能在上一个次请求还没完成时，切换了页面。
        config.cancelToken = new axios.CancelToken(async cancel => {
          await store.dispatch("app/execCancelToken", { cancelToken: cancel });
        });
        return config;
      },
      error => {
        return Promise.reject(error);
      }
    );

    instance.interceptors.response.use(
      response => {
        return response.data;
      },
      error => {
        if (error.response) {
          const data = error.response.data;
          if (error.response.status === 403) {
            notification.error({
              message: "无权限访问",
              description: data.message
            });
          }
          if (error.response.status === 401) {
            const store = useStore();
            store.dispatch("app/logout").then(() => {
              setTimeout(() => {
                window.location.reload();
              }, 2000);
            });
          }
        } else {
          let { message } = error;
          if (message === "Network Error") {
            message = "连接异常";
          }
          if (message.includes("timeout")) {
            message = "请求超时";
          }
          if (message.includes("Request failed with status code")) {
            const code = message.substr(message.length - 3);
            message = "接口" + code + "异常";
          }
          Msg.error(message);
        }
        return Promise.reject(error);
      }
    );
  }

  request(options) {
    const instance = axios.create();
    const requestOptions = Object.assign({}, this.config, options);
    this.interceptors(instance);
    return instance(requestOptions);
  }
}

const http = new Http();
export default http;
