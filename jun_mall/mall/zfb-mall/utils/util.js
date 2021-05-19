var api = require('../config/api.js');

function formatTime(date) {
  var year = date.getFullYear()
  var month = date.getMonth() + 1
  var day = date.getDate()

  var hour = date.getHours()
  var minute = date.getMinutes()
  var second = date.getSeconds()


  return [year, month, day].map(formatNumber).join('-') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

function formatNumber(n) {
  n = n.toString()
  return n[1] ? n : '0' + n
}

/**
 * 封封微信的的request
 */
function request(url, data = {}, method = "POST", header = "application/x-www-form-urlencoded") {
  my.showLoading({
    content: '加载中...',
  });
  return new Promise(function (resolve, reject) {
    my.request({
      url: url,
      data: data,
      method: method,
      headers: {
        'Content-Type': header,
        'X-Nideshop-Token': my.getStorageSync({key:'token'}).data || ''
      },
      success: function (res) {
        my.hideLoading();

        if (res.status == 200) {

          if (res.data.errno == 401) {
            my.navigateTo({
              url: '/pages/auth/btnAuth/btnAuth',
            })
          } else {
            resolve(res.data);
          }
        } else {
          reject(res.errMsg);
        }

      },
      fail: function (err) {
        reject(err)
      }
    })
  });
}

/**
 * 检查微信会话是否过期
 */
function checkSession() {
  return new Promise(function (resolve, reject) {
    my.checkSession({
      success: function () {
        resolve(true);
      },
      fail: function () {
        reject(false);
      }
    })
  });
}

/**
 * 调用支付宝登录
 */
function login() {
  return new Promise(function (resolve, reject) {
    my.getAuthCode({
      scopes: 'auth_user',
      success: (res) => {
        my.getAuthUserInfo({
          success: function (res) {
            if (res.code) {
              resolve(res);
            } else {
              reject(res);
            }
          },
          fail: function (err) {
            reject(err);
          }
        });
      },
    });
  });
}

function redirect(url) {

  //判断页面是否需要登录
  if (false) {
    my.redirectTo({
      url: '/pages/auth/login/login'
    });
    return false;
  } else {
    my.redirectTo({
      url: url
    });
  }
}

function showErrorToast(msg) {
  my.showToast({
    title: msg,
    image: '/static/images/icon_error.png'
  })
}

function showSuccessToast(msg) {
  my.showToast({
    title: msg,
  })
}

module.exports = {
  formatTime,
  request,
  redirect,
  showErrorToast,
  showSuccessToast,
  checkSession,
  login,
}


