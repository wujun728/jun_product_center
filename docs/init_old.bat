@echo off
chcp 65001 >nul
echo === my-project 环境检查 ===

echo [1/6] 检查工作目录...
if not exist "jun_ui_sa_admin" (echo   X 新前端目录 jun_ui_sa_admin 不存在 & exit /b 1)
if not exist "ruoyi-vue-oa-ui" (echo   X 原前端目录 ruoyi-vue-oa-ui 不存在 & exit /b 1)
if not exist "ruoyi-vue-oa" (echo   X 后端目录 ruoyi-vue-oa 不存在 & exit /b 1)
echo   OK 三个项目目录均存在

echo [2/6] 检查新前端核心文件...
if not exist "jun_ui_sa_admin\index.html" (echo   X 缺失: index.html)
if not exist "jun_ui_sa_admin\sa-frame\index\index.js" (echo   X 缺失: index.js)
if not exist "jun_ui_sa_admin\sa-frame\sa-code.js" (echo   X 缺失: sa-code.js)
if not exist "jun_ui_sa_admin\static\sa.js" (echo   X 缺失: sa.js)
echo   OK 核心文件检查完成

echo [3/6] 检查原前端...
if exist "ruoyi-vue-oa-ui\src\views" (echo   OK 原前端 views 目录存在) else (echo   X 原前端 views 不存在)
if exist "ruoyi-vue-oa-ui\src\api" (echo   OK 原前端 api 目录存在) else (echo   X 原前端 api 不存在)

echo [4/6] 检查后端...
if exist "ruoyi-vue-oa\pom.xml" (echo   OK 后端 pom.xml 存在) else (echo   X pom.xml 不存在)

echo [5/6] 检查开发工具...
where java >nul 2>&1 && echo   OK java 已安装 || echo   WARN java 未安装
where mvn >nul 2>&1 && echo   OK mvn 已安装 || echo   WARN mvn 未安装
where node >nul 2>&1 && echo   OK node 已安装 || echo   WARN node 未安装
where npm >nul 2>&1 && echo   OK npm 已安装 || echo   WARN npm 未安装
where git >nul 2>&1 && echo   OK git 已安装 || echo   WARN git 未安装

echo.
echo === 检查完成 ===
