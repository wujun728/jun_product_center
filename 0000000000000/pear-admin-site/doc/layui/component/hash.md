## 基本介绍

Pear Hash 为前端开发 提供 加密服务


## 使用方式

```javascript
layui.use([ 'hash' ], function() {
	var hash = layui.hash;
	
	hash.md5( str );
	hash.sha1( str );
	hash.sha256( str );
	hash.sha512( str);
	hash.rmd160( str );
	hash.crc32( str);
	hash.Base64Encode( str );

})
```

- hash.md5( str ) -- MD5加密

- hash.sha1( str ) -- SHA1加密

- hash.sha256( str ) -- SHA256加密

- hash.sha512( str ) -- SHA512加密

- hash.rmd160( str ) -- RMD160加密

- hash.crc32( str ) -- CRC32加密

- hash.Base64Encode( str ) -- BASE64加密

