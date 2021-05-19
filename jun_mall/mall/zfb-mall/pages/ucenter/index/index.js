var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');
var user = require('../../../services/user.js');
var app = getApp();

Page({
    data: {
        userInfo: {},
        hasMobile: ''
    },
    onLoad: function (options) {
        // 页面初始化 options为页面跳转所带来的参数
    },
    onReady: function () {

    },
    onShow: function () {

        let userInfo = my.getStorageSync({key:'userInfo'}).data;
        let token = my.getStorageSync({key:'token'}).data;

        // 页面显示
        if (userInfo && token) {
            app.globalData.userInfo = userInfo;
            app.globalData.token = token;
        }

        this.setData({
            userInfo: app.globalData.userInfo,
        });

    },
    onHide: function () {
        // 页面隐藏

    },
    onUnload: function () {
        // 页面关闭
    },
    bindGetUserInfo(e) {
      let userInfo = my.getStorageSync({key:'userInfo'}).data;
      let token = my.getStorageSync({key:'token'}).data;
      if (userInfo && token) {
        return;
      }
        if (e.detail.userInfo){
            //用户按了允许授权按钮
            user.loginByWeixin(e.detail).then(res => {
                this.setData({
                    userInfo: res.data.userInfo
                });
                app.globalData.userInfo = res.data.userInfo;
                app.globalData.token = res.data.token;
            }).catch((err) => {
                console.log(err)
            });
        } else {
            //用户按了拒绝按钮
            my.showModal({
                title: '警告通知',
                content: '您点击了拒绝授权,将无法正常显示个人信息,点击确定重新获取授权。',
                success: function (res) {
                    if (res.confirm) {
                        my.openSetting({
                            success: (res) => {
                                if (res.authSetting["scope.userInfo"]) {////如果用户重新同意了授权登录
                                    user.loginByWeixin(e.detail).then(res => {
                                        this.setData({
                                            userInfo: res.data.userInfo
                                        });
                                        app.globalData.userInfo = res.data.userInfo;
                                        app.globalData.token = res.data.token;
                                    }).catch((err) => {
                                        console.log(err)
                                    });
                                }
                            }
                        })
                    }
                }
            });
        }
    },
    exitLogin: function () {
        my.showModal({
            title: '',
            confirmColor: '#b4282d',
            content: '退出登录？',
            success: function (res) {
                if (res.confirm) {
                    my.removeStorageSync({key:'token'});
                    my.removeStorageSync({key:'userInfo'});
                    my.switchTab({
                        url: '/pages/index/index'
                    });
                }
            }
        })

    }
})
