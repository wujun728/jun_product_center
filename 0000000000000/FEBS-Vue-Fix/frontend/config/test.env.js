'use strict'
const merge = require('webpack-merge')
const devEnv = require('./dev.env')

module.exports = merge(devEnv, {
  NODE_ENV: '"testing"',
  CONFIG_TEXT: '"测试环境"',
  BACKEND_API_HOST: '"127.0.0.1:9527"',
  bundleAnalyzerReport: false
})
