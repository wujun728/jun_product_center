<div align="center" style="margin-top:30px;">
    <img src="https://min.io/resources/img/logo.svg" width="30%" />
</div>
<h1 align="center">
    minio-spring-boot-starter
</h1>
<h4 align="center">
    基 于 Minio 对 象 存 储 的 Spring Boot 快 速 启 动 器，开 箱 即 用
</h4> 


<p align="center">
	<a target="_blank" href="https://gitee.com/pear-admin/minio-spring-boot-starter/blob/master/LICENSE">
	    <img src="https://img.shields.io/badge/license-Apache--2.0-blue" />
	</a>
	<a target="_blank">
	    <img src="https://img.shields.io/badge/minio-7.1.0-blue" />
	</a>
	<a target="_blank">
	    <img src="https://img.shields.io/badge/spring--boot-2.3.7.RELEASE-blue" />
	</a>
        <br/>
	<a target="_blank" href="https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html">
	    <img src="https://img.shields.io/badge/JDK-8+-green.svg" />
	</a>
	<a target="_blank" href="https://jitpack.io/#com.gitee.pear-admin/minio-spring-boot-starter">
	    <img src="https://jitpack.io/v/com.gitee.pear-admin/minio-spring-boot-starter.svg" />
	</a>
</p>



### 项目介绍

基 于 Minio 对 象 存 储 的 Spring Boot 快 速 启 动 器，开 箱 即 用

<p>
    <a target="_blank" href="https://apidoc.gitee.com/pear-admin/minio-spring-boot-starter"> 
        参考API
    </a>
</p>



### 依赖关系

|  项目名称   |    版本号     | 官网地址                                              |
| :---------: | :-----------: | ----------------------------------------------------- |
|    minio    |     7.1.0     | https://docs.min.io/docs/java-client-quickstart-guide |
| spring-boot | 2.3.7.RELEASE | https://spring.io/projects/spring-boot                |
| hutool-core |     5.7.5     | https://www.hutool.cn                                 |



### 如何使用

#### maven

在项目的pom.xml中添加

```xml
    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>
```

```xml
	<dependency>
	    <groupId>com.gitee.pear-admin</groupId>
	    <artifactId>minio-spring-boot-starter</artifactId>
	    <version>${last.version}</version>
	</dependency>
```

尝鲜版

```xml
	<dependency>
	    <groupId>com.gitee.pear-admin</groupId>
	    <artifactId>minio-spring-boot-starter</artifactId>
	    <version>master-SNAPSHOT</version>
	</dependency>
```



#### gradle

```groovy
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

```groovy
	dependencies {
	        implementation 'com.gitee.pear-admin:minio-spring-boot-starter:${last.version}'
	}
```

尝鲜版

```groovy
	dependencies {
	        implementation 'com.gitee.pear-admin:minio-spring-boot-starter:master-SNAPSHOT'
	}
```

