'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  CONFIG_TEXT: '"开发环境"',
  BACKEND_API_HOST: '"127.0.0.1:9527"',
  HOST: '"127.0.0.1"',
  PORT: '"8081"'
})
