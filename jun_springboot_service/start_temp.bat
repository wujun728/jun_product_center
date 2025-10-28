@echo off

REM 临时启动脚本
set PROJECT_HOME=%CD%

REM 设置classpath包含主jar文件
echo Starting application with jun_admin.jar...
java -jar "%PROJECT_HOME%\jun_admin\target\jun_admin.jar"