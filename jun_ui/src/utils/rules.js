/* eslint-disable */
/**
 * @param {string} path
 * @returns {Boolean}
 */
export function isExternal(rule, value, callback) {
  const reg = /^(https?:|mailto:|tel:)/

  if (new RegExp(reg).test(value)) {
    callback()
  } else {
    callback(rule.message)
  }
}

/**
 * @param {string} str
 * @returns {Boolean}
 */
export function validUsername(rule, value, callback) {
  const validMap = ['admin', 'editor']

  if (validMap.indexOf(value.trim()) >= 0) {
    callback()
  } else {
    callback(rule.message)
  }

}

/**
 * @param {string} url
 * @returns {Boolean}
 */
export function validURL(rule, value, callback) {
  const reg = /^(https?|ftp):\/\/([a-zA-Z0-9.-]+(:[a-zA-Z0-9.&%$-]+)*@)*((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]?)(\.(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])){3}|([a-zA-Z0-9-]+\.)*[a-zA-Z0-9-]+\.(com|edu|gov|int|mil|net|org|biz|arpa|info|name|pro|aero|coop|museum|[a-zA-Z]{2}))(:[0-9]+)*(\/($|[a-zA-Z0-9.,?'\\+&%$#=~_-]+))*$/

  if (new RegExp(reg).test(value)) {
    callback()
  } else {
    callback(rule.message)
  }
}

/**
 * @param {string} str 英文
 * @returns {Boolean}
 */
export function validAlphabets(rule, value, callback) {
  const reg = /^[A-Za-z]+$/

  if (new RegExp(reg).test(value)) {
    callback()
  } else {
    callback(rule.message)
  }
}

/**
 * @param {string} str 大写字母
 * @returns {Boolean}
 */
export function validUpperCase(rule, value, callback) {
  const reg = /^[A-Z]+$/

  if (new RegExp(reg).test(value)) {
    callback()
  } else {
    callback(rule.message)
  }
}

/**
 * @param {string} str 小写字母
 * @returns {Boolean}
 */
export function validLowerCase(rule, value, callback) {
  const reg = /^[a-z]+$/

  if (new RegExp(reg).test(value)) {
    callback()
  } else {
    callback(rule.message)
  }
}

/**
 * @param {string} str 中文
 * @returns {Boolean}
 */
export function validChinese(rule, value, callback) {
  const reg = /^[\u4e00-\u9fa5]+$/

  if (new RegExp(reg).test(value)) {
    callback()
  } else {
    callback(rule.message)
  }
}

/**
 * @param {string} str中文、英文、数字
 * @returns {Boolean}
 */
export function validAlphanumeric(rule, value, callback) {
  const reg = /^[A-Za-z0-9]+$/

  if (new RegExp(reg).test(value)) {
    callback()
  } else {
    callback(rule.message)
  }
}

/**
 * @param {string} str 数字
 * @returns {Boolean}
 */
export function validNumeric(rule, value, callback) {
  const reg = /^[0-9]+$/

  if (new RegExp(reg).test(value)) {
    callback()
  } else {
    callback(rule.message)
  }
}

/**
 * @param {string} email
 * @returns {Boolean}
 */
export function validEmail(rule, value, callback) {
  const reg = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/

  if (new RegExp(reg).test(value)) {
    callback()
  } else {
    callback(rule.message)
  }
}

/**
 * @param {string} 腾讯QQ号
 * @returns {Boolean}
 */
export function validQQ(rule, value, callback) {
  const reg = /[1-9][0-9]{4,}/

  if (new RegExp(reg).test(value)) {
    callback()
  } else {
    callback(rule.message)
  }
}

/**
 * @param {string} 中国邮政编码为6位数字
 * @returns {Boolean}
 */
export function validPostalCode(rule, value, callback) {
  const reg = /[1-9]\d{5}(?!\d)/

  if (new RegExp(reg).test(value)) {
    callback()
  } else {
    callback(rule.message)
  }
}

/**
 * @param {string} IPv4地址
 * @returns {Boolean}
 */
export function validIPv4(rule, value, callback) {
  const reg = /((2(5[0-5]|[0-4]\d))|[0-1]?\d{1,2})(\.((2(5[0-5]|[0-4]\d))|[0-1]?\d{1,2})){3}/

  if (new RegExp(reg).test(value)) {
    callback()
  } else {
    callback(rule.message)
  }
}

/**
 * @param {string} XML后缀名
 * @returns {Boolean}
 */
export function validXMLSuffix(rule, value, callback) {
  const reg = /^([a-zA-Z]+-?)+[a-zA-Z0-9]+\\.[x|X][m|M][l|L]$/

  if (new RegExp(reg).test(value)) {
    callback()
  } else {
    callback(rule.message)
  }
}

/**
 * @param {string} 腾讯QQ号
 * @returns {Boolean}
 */
export function validMoney(rule, value, callback) {
  const reg = /^[0-9]+(.[0-9]{1,2})?$/

  if (new RegExp(reg).test(value)) {
    callback()
  } else {
    callback(rule.message)
  }
}

/**
 * @param {string} 日期-天
 * @returns {Boolean}
 */
export function validDay(rule, value, callback) {
  const reg = /^((0?[1-9])|((1|2)[0-9])|30|31)$/

  if (new RegExp(reg).test(value)) {
    callback()
  } else {
    callback(rule.message)
  }
}

/**
 * @param {string} 日期-月
 * @returns {Boolean}
 */
export function validMouth(rule, value, callback) {
  const reg = /^(0?[1-9]|1[0-2])$/

  if (new RegExp(reg).test(value)) {
    callback()
  } else {
    callback(rule.message)
  }
}

/**
 * @param {string} 强密码 必须包含大小写字母和数字的组合，可以使用特殊字符，长度在8-10之间
 * @returns {Boolean}
 */
export function validStrongPassword2(rule, value, callback) {
  const reg = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,10}$/

  if (new RegExp(reg).test(value)) {
    callback()
  } else {
    callback(rule.message)
  }
}

/**
 * @param {string} 密码(必须包含大小写字母和数字的组合，不能使用特殊字符，长度在 8-10 之间)
 * @returns {Boolean}
 */
export function validStrongPassword1(rule, value, callback) {
  const reg = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9]{8,10}$/

  if (new RegExp(reg).test(value)) {
    callback()
  } else {
    callback(rule.message)
  }
}

/**
 * @param {string} 密码(以字母开头，长度在6~18之间，只能包含字母、数字和下划线)
 * @returns {Boolean}
 */
export function validPassword1(rule, value, callback) {
  const reg = /^[a-zA-Z]\w{5,17}$/

  if (new RegExp(reg).test(value)) {
    callback()
  } else {
    callback(rule.message)
  }
}

/**
 * @param {string} 帐号是否合法(字母开头，允许5-16字节，允许字母数字下划线)：
 * @returns {Boolean}
 */
export function validAccount(rule, value, callback) {
  const reg = /^[a-zA-Z][a-zA-Z0-9_]{4,15}$/

  if (new RegExp(reg).test(value)) {
    callback()
  } else {
    callback(rule.message)
  }
}

/**
 * @param {string} 身份证号(15位、18位数字)，最后一位是校验位，可能为数字或字符X
 * @returns {Boolean}
 */
export function validIdCard(rule, value, callback) {
  const reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/

  if (new RegExp(reg).test(value)) {
    callback()
  } else {
    callback(rule.message)
  }
}

/**
 * @param {string} 电话号码正则表达式（支持手机号码，3-4位区号，7-8位直播号码，1－4位分机号）
 * @returns {Boolean}
 */
export function validTel(rule, value, callback) {
  const reg = /((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/

  if (new RegExp(reg).test(value)) {
    callback()
  } else {
    callback(rule.message)
  }
}

/**
 * @param {string} 电话号码正则表达式（支持手机号码，3-4位区号，7-8位直播号码，1－4位分机号）
 * @returns {Boolean}
 */
export function validChinaTel(rule, value, callback) {
  const reg = /\d{3}-\d{8}|\d{4}-\d{7}/

  if (new RegExp(reg).test(value)) {
    callback()
  } else {
    callback(rule.message)
  }
}

/**
 * @param {string} 电话号码正则表达式（支持手机号码，3-4位区号，7-8位直播号码，1－4位分机号）
 * @returns {Boolean}
 */
export function validPhone(rule, value, callback) {
  const reg = /^(13[0-9]|14[5|7]|15[0|1|2|3|4|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/

  if (new RegExp(reg).test(value)) {
    callback()
  } else {
    callback(rule.message)
  }
}

/**
 * @param {string} 电话号码正则表达式（支持手机号码，3-4位区号，7-8位直播号码，1－4位分机号）
 * @returns {Boolean}
 */
export function validLevelCode(rule, value, callback) {
  if(rule.max && value.length > rule.max) {
    callback('编码长度最大长度为:' + rule.max)
  } else {
    const reg = /^[a-zA-Z0-9]{1,}\d*$/

    if (new RegExp(reg).test(value)) {
      callback()
    } else {
      callback("编码规则以数字字母开头，并且以数字结尾")
    }
  }
}
