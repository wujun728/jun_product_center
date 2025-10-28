 function getDataCustomer() {
  $.ajax({
    type:'get',
    url:'http://qixing.hbqxcpa.cn/public/getAllProjectList?t='+(new Date()).valueOf(),
    //url:'http://localhost:8081/public/getAllProjectList?t='+(new Date()).valueOf(),
    
    //url:'./configInfo/config.json?authorization=' + tokenQuery,
    success:function (res) {
      dataLen =res.data.length;
      res.data.sort(function (a, b) {
        return parseInt(b.actualStartTime.replace(/\//g, ''), 10) - parseInt(a.actualStartTime.replace(/\//g, ''), 10);//降序
      });
      res.data.forEach(function(item,index){
        getData1(item.url,dataLen,index)
      });

    }
  });

}