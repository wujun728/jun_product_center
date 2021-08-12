const path = require("path");
 const resolve = dir => {
   return path.join(__dirname, dir);
 };
 const isDev = process.env.NODE_ENV === "development";
 const CompressionWebpackPlugin = require("compression-webpack-plugin");
 const ProductionGzipExtensions = ["html", "js", "css", "svg"];
 const WebpackBar = require("webpackbar");
 const webpackBarName = "pear-admin";
 
 module.exports = {
   publicPath: "",
   outputDir: "dist/admin",
   devServer: {
     open: true, // 自动
     port: 8080 // 端口
   },
   // 开启多进程 babel 编译
   parallel: require("os").cpus().length > 1,
   configureWebpack(config) {
     //配置根目录
     return {
       resolve: {
         alias: {
           "@": resolve("src")
         }
       },
       plugins: [
         new WebpackBar({
           name: webpackBarName
         })
       ]
     };
   },
   chainWebpack(config) {
     config.when(isDev, config => {
       config.devtool("source-map");
     });
     /// 拆包配置
     config.plugins.delete("prefetch");
     config.resolve.symlinks(true);
     config.when(!isDev, config => {
       config.devtool("none");
       config.optimization.splitChunks({
         chunks: "all",
         cacheGroups: {
           libs: {
             name: "Chunk-Libs",
             test: /[\\/]node_modules[\\/]/,
             priority: 10,
             chunks: "initial"
           },
           AntdUI: {
             name: "Chunk-Antd",
             priority: 20,
             test: /[\\/]node_modules[\\/]_?@ant-design(.*)/
           }
         }
       });
       // 资源压缩
       config
         .plugin("compression")
         .use(CompressionWebpackPlugin, [
           {
             filename: "[path][base].gz[query]",
             algorithm: "gzip",
             test: new RegExp(
               "\\.(" + ProductionGzipExtensions.join("|") + ")$"
             ),
             threshold: 8192,
             minRatio: 0.8
           }
         ])
         .end();
     });
   },
   runtimeCompiler: true,
   productionSourceMap: false,
   css: {
     requireModuleExtension: true,
     sourceMap: true,
     loaderOptions: {
       less: {
         lessOptions: {
           javascriptEnabled: true
         }
       }
     }
   }
 };