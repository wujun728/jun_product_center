@echo off
setlocal enabledelayedexpansion

REM 设置项目根目录
set PROJECT_HOME=%~dp0

REM 设置Maven本地仓库路径
set MAVEN_REPO=D:\Java\.m2\repository

REM 构建classpath，包含所有必要的jar文件
set CLASSPATH=

REM 添加项目模块的jar文件
echo 添加项目模块jar文件...
for %%j in (
    "jun_admin\target\jun_admin.jar"
    "jun_common\target\jun_common-1.0.24.jar"
    "jun_framework\target\jun_framework-1.0.24.jar"
    "jun_system\target\jun_system-1.0.24.jar"
    "jun_flowable\target\jun_flowable-1.0.24.jar"
    "jun_logic\target\jun_logic-1.0.24.jar"
    "jun_quartz\target\jun_quartz-1.0.24.jar"
    "jun_generator\target\jun_generator-1.0.24.jar"
    "jun_amis\target\jun_amis-1.0.1.jar"
    "jun-onlineForm-spring-boot-starter\target\jun-onlineForm-spring-boot-starter-1.0.24.jar"
) do (
    if exist "%PROJECT_HOME%%%j" (
        set "CLASSPATH=!CLASSPATH!;%PROJECT_HOME%%%j"
    ) else (
        echo 警告: 未找到文件 %PROJECT_HOME%%%j
    )
)

REM 添加Maven依赖
REM 这里我们只添加一些关键的Spring依赖，因为全部添加会太复杂
echo 添加Spring核心依赖...
for /r "%MAVEN_REPO%\org\springframework" %%f in (*.jar) do (
    set "CLASSPATH=!CLASSPATH!;%%f"
)

REM 添加其他重要的第三方依赖
echo 添加其他重要依赖...
for /r "%MAVEN_REPO%\com\alibaba" %%f in (*.jar) do (
    set "CLASSPATH=!CLASSPATH!;%%f"
)

for /r "%MAVEN_REPO%\org\mybatis" %%f in (*.jar) do (
    set "CLASSPATH=!CLASSPATH!;%%f"
)

for /r "%MAVEN_REPO%\com\baomidou" %%f in (*.jar) do (
    set "CLASSPATH=!CLASSPATH!;%%f"
)

for /r "%MAVEN_REPO%\mysql" %%f in (*.jar) do (
    set "CLASSPATH=!CLASSPATH!;%%f"
)

for /r "%MAVEN_REPO%\redis" %%f in (*.jar) do (
    set "CLASSPATH=!CLASSPATH!;%%f"
)

REM 移除classpath开头的分号
echo.%CLASSPATH% | findstr /B ";" >nul && set CLASSPATH=!CLASSPATH:~1!

REM 显示classpath（可选，用于调试）
echo Classpath设置完成，开始启动应用程序...

REM 启动应用程序
java -cp "%CLASSPATH%" com.ruoyi.QixingApplication

endlocal