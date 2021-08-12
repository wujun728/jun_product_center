### 环境要求 :id=install
- PHP >= 7.1.0
- Mysql >= 5.7.0 (需支持innodb引擎)
- Apache 或 Nginx

###  安装配置
- git clone https://gitee.com/pear-admin/Pear-Admin-Think
- 更新包composer update
- 将网站入口部署至public目录下面
- 修改伪静态配置, 请参考下方伪静态设置。
- 运行网站地址, 会自动进入安装界面, 请根据提示进行设置, 然后点击安装。
- 安装完成后会自动生成安装锁public/install.lock, 如需重新安装, 删掉该文件即可。

###  伪静态配置
通过伪静态配置, 将URL重写隐藏应用的入口文件index.php, 不配置的话, 会存在访问路径不正确的问题。
```javascript
<IfModule mod_rewrite.c>
    Options +FollowSymlinks -Multiviews
    RewriteEngine On
    RewriteCond %{REQUEST_FILENAME} !-d
    RewriteCond %{REQUEST_FILENAME} !-f
    RewriteRule ^(.*)$ index.php?/$1 [QSA,PT,L]
</IfModule>
```
在Nginx低版本中，是不支持PATHINFO的，但是可以通过在Nginx.conf中配置转发规则实现

```javascript
location / {
	if (!-e $request_filename){
		rewrite  ^(.*)$  /index.php?s=$1  last;   break;
	}
}
```