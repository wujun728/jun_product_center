const path = require('path');

function resolve(dir) {
    return path.join(__dirname, dir)
}

const HOST = '0.0.0.0';
const PORT = '8045';
// const DEV_URL = 'http://teamworkapi.mokingsoft.com';
const DEV_URL = 'http://localhost:8888';
module.exports = {
    publicPath: process.env.NODE_ENV === 'production' ? './' : '/',
    productionSourceMap: false,
    css: {
        loaderOptions: {
            less: {
                modifyVars: {
                    blue: '#3a82f8',
                    'text-color': '#333'
                },
                javascriptEnabled: true
            }
        }
    },
    devServer: {
        host: HOST,
        port: PORT,
        proxy: { // 配置跨域
            '/api': {
                //要访问的跨域的api的域名
                target: `${DEV_URL}/`,
                ws: true,
                changOrigin: true,
                pathRewrite: {
                    '^/api': ''
                }
            }
        },
    },
    configureWebpack: config => {
        config.resolve = {
            extensions: ['.js', '.vue', '.json', ".css"],
            alias: {
                'vue$': 'vue/dist/vue.esm.js',
                '@': resolve('src'),
                'assets': resolve('src/assets'),
                'components': resolve('src/components')
            }
        }
    },
    lintOnSave: undefined,
    chainWebpack: config => {
        // config.module.rules.delete('svg')
        config.module.rule('iview') .test(/\.js$/) .use('babel').loader('babel-loader') .end() // 此处是添加内容重点
        config.resolve.alias.set("@", resolve("src"))
    }

};
