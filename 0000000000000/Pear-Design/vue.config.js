module.exports = {
    pages: {
        index: {
            entry: 'example/main.js',
            template: 'public/index.html',
            filename: 'index.html'
        }
    },
    chainWebpack: config => {
        config.module
            .rule('js')
            .include
            .add('/package')
            .end()
            .use('babel')
            .loader('babel-loader')
    },
    publicPath: "/pear-design/dist"
}