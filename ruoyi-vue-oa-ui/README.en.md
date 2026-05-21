# RuoYi-OA-Vue

#### Description
{**When you're done, you can delete the content in this README and update the file with details for others getting started with your repository**}

#### Software Architecture
Software architecture description

#### Installation

1.  xxxx
2.  xxxx
3.  xxxx

#### Instructions

1.  xxxx
2.  xxxx
3.  xxxx

#### Contribution

1.  Fork the repository
2.  Create Feat_xxx branch
3.  Commit your code
4.  Create Pull Request


#### Gitee Feature

1.  You can use Readme\_XXX.md to support different languages, such as Readme\_en.md, Readme\_zh.md
2.  Gitee blog [blog.gitee.com](https://blog.gitee.com)
3.  Explore open source project [https://gitee.com/explore](https://gitee.com/explore)
4.  The most valuable open source project [GVP](https://gitee.com/gvp)
5.  The manual of Gitee [https://gitee.com/help](https://gitee.com/help)
6.  The most popular members  [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)


superAdmin/amdin123




## 开发

```bash
# 克隆项目
git clone https://gitee.com/y_project/RuoYi-Vue

# 进入项目目录
cd ruoyi-ui

# 安装依赖
npm install



# 建议不要直接使用 cnpm 安装依赖，会有各种诡异的 bug。可以通过如下操作解决 npm 下载速度慢的问题
npm install --registry=https://registry.npmmirror.com

"dev": "SET NODE_OPTIONS=--openssl-legacy-provider && vue-cli-service serve",

# 启动服务
npm run dev
```

浏览器访问 http://localhost:80

## 发布

```bash
# 构建测试环境
npm run build:stage

# 构建生产环境
npm run build:prod
```



#更新npm到最新version
npm -i -g npm

npm install -g npm@latest
npm config set python D:\Java\Python 3.5\python.exe
npm install --global --production windows-build-tools --Python="D:\Java\Python 3.5\python.exe"


#安装windows build tools
npm install --global --production windows-build-tools
#安装node-gyp
npm install -g node-gyp
npm install -g node-gyp@5.1.0

node-gyp configure --msbuild_path="C:\Program Files (x86)\Microsoft Visual Studio\2019\BuildTools\MSBuild\Current\Bin\MSBuild.exe"
 
// 在package.json项目dev中添加node-gyp依赖
"devDependencies": {
    "node-gyp": "3.8.0"
}

// 在yarn.lock文件添加对应的依赖限制，如果没有lock应该就不用处理这个
node-gyp@3.8.0, node-gyp@^3.8.0: 
npm install -g --production windows-build-tools --registry=https://registry.npmmirror.com


yarn cache clean
// 有必要的话把node_modules都删除
yarn install

1. 替换 node-sass 为 sass（推荐）
# 卸载 node-sass
npm uninstall node-sass

# 安装 sass
npm install sass --save-dev

# 清除 npm 缓存
npm cache clean --force

# 删除 node_modules 和 package-lock.json
rm -rf node_modules package-lock.json

# 重新安装依赖
npm install

```bash

npm install --registry=https://registry.npmmirror.com

#安装gyp
npm install -g node-gyp
npm install --global --production windows-build-tools

npm config set python 'C:\Python27'

#PowerShell
#按下Win + X，选择 "Windows PowerShell (管理员)"
Get-ExecutionPolicy
Set-ExecutionPolicy RemoteSigned


#删除现有依赖并重新安装
    # 删除 node_modules 和 package-lock.json
    rm -rf node_modules package-lock.json

    # 清除 npm 缓存
    npm cache clean --force

    # 重新安装依赖
    npm install
#手动安装有问题的包
    npm install true-case-path --save
#检查包是否存在问题
    npm install true-case-path@2.2.1 --save

 

功能	npm	pnpm
安装依赖	npm install	pnpm install
简写	npm i	pnpm i
运行 dev	npm run dev	pnpm run dev
安装全局包	npm i -g xxx	pnpm add -g xxx
删除依赖	npm uninstall	pnpm remove


1 分钟切换到 pnpm（无风险）
安装 pnpm
 
npm install -g pnpm
进入你的项目
 
pnpm approve-builds
pnpm install

pnpm install --ignore-scripts=false
 
pnpm run dev