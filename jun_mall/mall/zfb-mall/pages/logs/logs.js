//logs.js
var util = require('../../utils/util.js')
Page({
  data: {
    logs: []
  },
  onLoad: function () {
    this.setData({
      logs: (my.getStorageSync({key:'logs'}).data || []).map(function (log) {
        return util.formatTime(new Date(log))
      })
    })
  }
})
