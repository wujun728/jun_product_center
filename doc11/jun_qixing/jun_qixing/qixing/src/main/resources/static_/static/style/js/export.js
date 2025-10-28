function downloadFile($, url,params, fileName){   
   
   var xhr = new XMLHttpRequest();
 
   // xhr.open('GET', url, true);        // 使用GET方式比较简单，参数直接附在URL上
 
   xhr.open('post', url, true); 		//POST的格式相对比较灵活，参数可以有比较多的形式，例如JSON，表单FORM等
   
   xhr.responseType = "blob";    // 返回类型blob
   
   xhr.setRequestHeader("Content-Type","application/json");//提交的数据为json格式
 
   
   // 定义请求完成的处理函数
   xhr.onload = function () {
 
       // 请求完成
 
       if (this.status === 200) {
 
           // 返回200
 
           var blob = this.response;
 
           var reader = new FileReader();
 
           reader.readAsDataURL(blob);    // 转换为base64，可以直接放入a表情href
 
           reader.onload = function (e) {
 
               // 转换完成，创建一个a标签用于下载
 
               var a = document.createElement('a');
 
               a.download = fileName;
 
               a.href = e.target.result;
 
               $("body").append(a);    // 修复firefox中无法触发click
 
               a.click();
 
               $(a).remove();
 
           }
 
       }
 
   };
 
   // 发送ajax请求,案例中我们使用POST的请求格式，参数类型为JSON
   xhr.send(JSON.stringify(params));
}