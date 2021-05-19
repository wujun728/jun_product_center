const util = require('../../../utils/util.js');
const api = require('../../../config/api.js');

//获取应用实例
const app = getApp()
Page({
  data: {
    navUrl: '',
    code: ''
  },

  onLoad: function (options) {
    let that = this;
    if (my.getStorageSync({ key: "navUrl" }).data) {
      that.setData({
        navUrl: my.getStorageSync({ key: "navUrl" }).data
      })
    } else {
      that.setData({
        navUrl: '/pages/index/index'
      })
    }

    let userInfo = my.getStorageSync({ key: 'userInfo' }).data;
    let token = my.getStorageSync({ key: 'token' }).data;
    if (userInfo && token) {
      return;
    }
  },

  bindGetUserInfo: function (e) {
    let that = this;
    //登录远程服务器


    my.getAuthCode({
      scopes: 'auth_user',
      success: function (res) {
        if (res.authCode) {
          util.request(api.AuthLoginByAli, {
            code: res.authCode
          }, 'POST', 'application/json').then(res => {
            if (res.errno === 0) {
              //存储用户信息
              my.setStorageSync({ key: 'userInfo', data: res.data.userInfo });
              my.setStorageSync({ key: 'token', data: res.data.token });
              my.setStorageSync({ key: 'userId', data: res.data.userId });

            } else {
              // util.showErrorToast(res.errmsg)
              my.showModal({
                title: '提示',
                content: res.errmsg,
                showCancel: false
              });
            }
          });
        }
      }
    });
    if (that.data.navUrl && that.data.navUrl == '/pages/index/index') {
      my.switchTab({
        url: that.data.navUrl,
      })
    } else if (that.data.navUrl) {
      my.redirectTo({
        url: that.data.navUrl,
      })
    }
  },
  onReady: function () {
    // 页面渲染完成
  },
  onShow: function () {
    // 页面显示
  },
  onHide: function () {
    // 页面隐藏
  },
  onUnload: function () {
    // 页面关闭
  }
})
