jquery ajax 添加自定义请求头 - Authorization 字段.txt

参考：
https://www.cnblogs.com/yanggb/p/12039665.html
https://www.w3school.com.cn/jquery/ajax_ajax.asp
https://www.cnblogs.com/winyh/p/6405437.html

语法
    window.localStorage
保存数据语法：
    localStorage.setItem("key", "value");
读取数据语法：
    var lastname = localStorage.getItem("key");
删除数据语法：
    localStorage.removeItem("key");


0.token
let token = window.localStorage.token;
console.log("token:", token);
// 把 token 进行 url 转义，然后Base64编码，构造 授权。
// 经测试，Basic/basic 都行。
let authorization = "Basic " + window.btoa(encodeURI(token + ":"));
console.log("authorization:", authorization);

1.beforeSend: function(XHR){
    /*功能：添加自定义 HTTP 头 Authorization。*/
    XHR.setRequestHeader("Authorization", authorization);
},
2.headers: {'Accept': 'application/json', 'Authorization': authorization},
    headers: {'Accept': 'application/json', 'Authorization': localStorage.getItem("Authorization")},
3.完整样式。
$.ajax({
    type: "POST",  // 规定请求的类型（GET 或 POST）， 默认为 "GET"。
    // dataType: "json",  // 预期服务器返回的数据类型。
    url: "http://61.240.19.180:6201/v1/Administration/Stuff/Project",
    data: '',
    /*beforeSend: function (XHR) {
        /!*功能：添加自定义 HTTP 头 Authorization。*!/
        XHR.setRequestHeader("Authorization", authorization);
    },*/
    headers: {'Accept': 'application/json', 'Authorization': authorization},
    error: function (xhr, status, error) {
        /*如果请求失败要运行的函数。
        * xhr, XMLHttpRequest 对象。
        * status, 请求状态（parsererror）。
        * error, 错误信息。比如：error: SyntaxError: Unexpected token N in JSON at position 29。*/
        // console.log("xhr:", xhr);
        // console.log("status:", status);
        // console.log("error:", error);
    },
    success: function (result, status, xhr) {
        /*当请求成功时运行的函数。
        * result, 服务器返回的项目对象的数组，数据结构：[{...},{...}]。
        * status, 请求状态（success）。
        * xhr, XMLHttpRequest 对象。*/
        console.log("result:", result);
    }
})