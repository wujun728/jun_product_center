import CryptoJS from 'crypto-js'

export default {
  encrypt: function (data) {
    /* AES前端加密 */
    var key = CryptoJS.enc.Utf8.parse('4Dd2Bb3Cc1Aa5Ee0')
    var iv = CryptoJS.enc.Utf8.parse('4Dd2Bb3Cc1Aa5Ee0')
    var str = CryptoJS.AES.encrypt(data, key, {iv: iv, mode: CryptoJS.mode.CBC, padding: CryptoJS.pad.ZeroPadding}).toString()
    return this.filter(str)
  },
  filter: function (value) {
    value = value.replace('#', '%23')
      .replace('%', '%25')
      .replace('&', '%26')
      .replace('+', '%2B')
      .replace('//', '%2F')
      .replace('?', '%3F')
    return value
  }
}
