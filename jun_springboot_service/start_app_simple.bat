@echo off

REM 设置项目根目录为当前目录
set PROJECT_HOME=%CD%

REM 设置classpath包含所有模块的jar文件
echo Setting classpath...
set CLASSPATH=%PROJECT_HOME%\jun_admin\target\jun_admin.jar
set CLASSPATH=%CLASSPATH%;%PROJECT_HOME%\jun_common\target\jun_common-1.0.24.jar
set CLASSPATH=%CLASSPATH%;%PROJECT_HOME%\jun_framework\target\jun_framework-1.0.24.jar
set CLASSPATH=%CLASSPATH%;%PROJECT_HOME%\jun_system\target\jun_system-1.0.24.jar
set CLASSPATH=%CLASSPATH%;%PROJECT_HOME%\jun_flowable\target\jun_flowable-1.0.24.jar
set CLASSPATH=%CLASSPATH%;%PROJECT_HOME%\jun_logic\target\jun_logic-1.0.24.jar
set CLASSPATH=%CLASSPATH%;%PROJECT_HOME%\jun_quartz\target\jun_quartz-1.0.24.jar
set CLASSPATH=%CLASSPATH%;%PROJECT_HOME%\jun_generator\target\jun_generator-1.0.24.jar
set CLASSPATH=%CLASSPATH%;%PROJECT_HOME%\jun_amis\target\jun_amis-1.0.1.jar
set CLASSPATH=%CLASSPATH%;%PROJECT_HOME%\jun-onlineForm-spring-boot-starter\target\jun-onlineForm-spring-boot-starter-1.0.24.jar

REM 添加Maven仓库中的Spring核心依赖
echo Adding Spring dependencies...
set MAVEN_REPO=D:\Java\.m2\repository
set CLASSPATH=%CLASSPATH%;%MAVEN_REPO%\org\springframework\spring-core\5.3.29\spring-core-5.3.29.jar
set CLASSPATH=%CLASSPATH%;%MAVEN_REPO%\org\springframework\spring-beans\5.3.29\spring-beans-5.3.29.jar
set CLASSPATH=%CLASSPATH%;%MAVEN_REPO%\org\springframework\spring-context\5.3.29\spring-context-5.3.29.jar
set CLASSPATH=%CLASSPATH%;%MAVEN_REPO%\org\springframework\spring-web\5.3.29\spring-web-5.3.29.jar
set CLASSPATH=%CLASSPATH%;%MAVEN_REPO%\org\springframework\spring-webmvc\5.3.29\spring-webmvc-5.3.29.jar
set CLASSPATH=%CLASSPATH%;%MAVEN_REPO%\org\springframework\spring-jdbc\5.3.29\spring-jdbc-5.3.29.jar
set CLASSPATH=%CLASSPATH%;%MAVEN_REPO%\org\springframework\spring-tx\5.3.29\spring-tx-5.3.29.jar
set CLASSPATH=%CLASSPATH%;%MAVEN_REPO%\org\springframework\spring-orm\5.3.29\spring-orm-5.3.29.jar
set CLASSPATH=%CLASSPATH%;%MAVEN_REPO%\org\springframework\spring-aop\5.3.29\spring-aop-5.3.29.jar
set CLASSPATH=%CLASSPATH%;%MAVEN_REPO%\org\springframework\boot\spring-boot\2.5.15\spring-boot-2.5.15.jar
set CLASSPATH=%CLASSPATH%;%MAVEN_REPO%\org\springframework\boot\spring-boot-autoconfigure\2.5.15\spring-boot-autoconfigure-2.5.15.jar
set CLASSPATH=%CLASSPATH%;%MAVEN_REPO%\org\springframework\boot\spring-boot-starter\2.5.15\spring-boot-starter-2.5.15.jar
set CLASSPATH=%CLASSPATH%;%MAVEN_REPO%\org\springframework\boot\spring-boot-starter-web\2.5.15\spring-boot-starter-web-2.5.15.jar

REM 启动应用程序
echo Starting application...
java -cp "%CLASSPATH%" com.ruoyi.QixingApplication