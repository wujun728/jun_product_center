@echo off
chcp 65001 >nul
setlocal enabledelayedexpansion

REM jun-oa-frontend-upgrade 环境检查和初始化脚本（Windows版本）
REM 用途：一键检查前端升级环境，安装依赖，执行基础验证

echo ========================================
echo   jun-oa-frontend-upgrade 环境检查
echo ========================================
echo.

set WORK_DIR=%~dp0
set OLD_FRONTEND_DIR=%WORK_DIR%ruoyi-vue-oa-ui
set NEW_FRONTEND_DIR=%WORK_DIR%yudao-ui-admin-vue3
set BACKEND_DIR=%WORK_DIR%ruoyi-vue-oa

echo 工作目录: %WORK_DIR%
echo.

REM 1. 检查Node.js版本
echo ^>^>^> [1/8] 检查 Node.js 版本...
where node >nul 2>&1
if %errorlevel% neq 0 (
    echo [✗] 错误: 未安装 Node.js
    exit /b 1
)
for /f "tokens=1 delims=." %%a in ('node -v') do set NODE_MAJOR=%%a
set NODE_MAJOR=%NODE_MAJOR:v=%
echo [✓] Node.js 版本:
node -v
if %NODE_MAJOR% lss 16 (
    echo [✗] 错误: Node.js 版本需要 ^>= 16
    exit /b 1
)

REM 2. 检查npm和pnpm
echo.
echo ^>^>^> [2/8] 检查 npm 和 pnpm...
where npm >nul 2>&1
if %errorlevel% neq 0 (
    echo [✗] 错误: 未安装 npm
    exit /b 1
)
echo [✓] npm 版本:
npm -v

where pnpm >nul 2>&1
if %errorlevel% neq 0 (
    echo [⚠] 警告: 未安装 pnpm，正在安装...
    call npm install -g pnpm
    echo [✓] pnpm 安装完成
)
echo [✓] pnpm 版本:
pnpm -v

REM 3. 检查Java和Maven
echo.
echo ^>^>^> [3/8] 检查 Java 和 Maven（后端依赖）...
where java >nul 2>&1
if %errorlevel% neq 0 (
    echo [⚠] 警告: 未安装 Java（后端需要）
) else (
    echo [✓] Java 版本:
    java -version 2>&1 | findstr /C:"version"
)

where mvn >nul 2>&1
if %errorlevel% neq 0 (
    echo [⚠] 警告: 未安装 Maven（后端需要）
) else (
    echo [✓] Maven 版本:
    mvn -v | findstr /C:"Apache Maven"
)

REM 4. 检查目录结构
echo.
echo ^>^>^> [4/8] 检查项目目录结构...
if exist "%OLD_FRONTEND_DIR%" (
    echo [✓] 原前端目录存在: %OLD_FRONTEND_DIR%
) else (
    echo [✗] 错误: 原前端目录不存在
    exit /b 1
)

if exist "%NEW_FRONTEND_DIR%" (
    echo [✓] 新前端目录存在: %NEW_FRONTEND_DIR%
) else (
    echo [✗] 错误: 新前端目录不存在
    exit /b 1
)

if exist "%BACKEND_DIR%" (
    echo [✓] 后端目录存在: %BACKEND_DIR%
) else (
    echo [⚠] 警告: 后端目录不存在
)

REM 5. 安装原前端依赖
echo.
echo ^>^>^> [5/8] 安装原前端依赖（ruoyi-vue-oa-ui）...
cd /d "%OLD_FRONTEND_DIR%"
if not exist "package.json" (
    echo [✗] 错误: package.json 不存在
    exit /b 1
)
if not exist "node_modules" (
    echo    正在安装依赖...
    call npm install --legacy-peer-deps
    echo [✓] 原前端依赖安装完成
) else (
    echo [✓] 原前端依赖已存在，跳过安装
)

REM 6. 安装新前端依赖
echo.
echo ^>^>^> [6/8] 安装新前端依赖（yudao-ui-admin-vue3）...
cd /d "%NEW_FRONTEND_DIR%"
if not exist "package.json" (
    echo [✗] 错误: package.json 不存在
    exit /b 1
)
if not exist "node_modules" (
    echo    正在安装依赖...
    call pnpm install
    echo [✓] 新前端依赖安装完成
) else (
    echo [✓] 新前端依赖已存在，跳过安装
)

REM 7. 检查后端编译（可选）
echo.
echo ^>^>^> [7/8] 检查后端编译（可选）...
if exist "%BACKEND_DIR%\pom.xml" (
    cd /d "%BACKEND_DIR%"
    echo    尝试编译后端...
    call mvn clean compile -DskipTests -q >nul 2>&1
    if %errorlevel% equ 0 (
        echo [✓] 后端编译成功
    ) else (
        echo [⚠] 警告: 后端编译失败或跳过
    )
) else (
    echo [⚠] 跳过后端编译检查
)

REM 8. 生成环境报告
echo.
echo ^>^>^> [8/8] 生成环境报告...
cd /d "%WORK_DIR%"
(
echo # jun-oa-frontend-upgrade 环境检查报告
echo.
echo **检查时间:** %date% %time%
echo.
echo ## 环境信息
echo - Node.js:
node -v
echo - npm:
npm -v
echo - pnpm:
pnpm -v
echo.
echo ## 项目结构
echo - 原前端: %OLD_FRONTEND_DIR%
echo - 新前端: %NEW_FRONTEND_DIR%
echo - 后端: %BACKEND_DIR%
echo.
echo ## 下一步
echo 1. 查看 task_list.json 了解任务清单
echo 2. 启动原前端: cd ruoyi-vue-oa-ui ^&^& npm run dev
echo 3. 启动新前端: cd yudao-ui-admin-vue3 ^&^& pnpm dev
echo 4. 启动后端: cd ruoyi-vue-oa ^&^& mvn spring-boot:run
) > env-report.md

echo [✓] 环境报告已生成: env-report.md

REM 完成
echo.
echo ========================================
echo [✓] 环境检查完成！
echo ========================================
echo.
echo 后续步骤:
echo   1. 查看任务清单: type task_list.json
echo   2. 查看环境报告: type env-report.md
echo   3. 启动原前端: cd ruoyi-vue-oa-ui ^&^& npm run dev
echo   4. 启动新前端: cd yudao-ui-admin-vue3 ^&^& pnpm dev
echo   5. 启动后端: cd ruoyi-vue-oa ^&^& mvn spring-boot:run
echo.

endlocal
pause
