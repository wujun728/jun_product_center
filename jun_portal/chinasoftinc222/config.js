var a =document.location.protocol;
if(a=="https:"){
   window.g = {
      //生产环境
      NODE_ENV: "production",
      //用户信息初始化接口地址(获取认证接口地址)
      BASE_API_GETTOKEN_PUL: "https://ics.chinasoftinc.com:18002",
      //个人信息公共接口地址
      BASE_API_PUL: "https://ics.chinasoftinc.com:18997",
      //获取mannjutoken
      BASE_API_MANNJUTOKEN:"https://ics.chinasoftinc.com:18997",
      //基础信息的module
      MODULE: "cce_scube",
      //发薪账户基础信息接口地址
      BASE_API_PUL_ACCOUNT: "https://ics.chinasoftinc.com:18997/?method=camma/mobile",
      //获取图片上传地址
      BASE_API_UPLOAD:'https://ics.chinasoftinc.com:18002',
      //发薪账户跳转地址
      BANK: 'https://sse.chinasoftinc.com:18080/#!/index',
    
    };
}else{
    window.g = {
      //生产环境
      NODE_ENV: "production",
      //用户信息初始化接口地址(获取认证接口地址)
      BASE_API_GETTOKEN_PUL: "http://117.78.49.59:30113/",
      //个人信息公共接口地址
      BASE_API_PUL: "http://ics.chinasoftinc.com:8997/",
      //获取mannjutoken
      BASE_API_MANNJUTOKEN:"http://ics.chinasoftinc.com:8997/",
      //基础信息的module
      MODULE: "cce_scube",
      //发薪账户基础信息接口地址
      BASE_API_PUL_ACCOUNT: "http://ics.chinasoftinc.com:8997/?method=camma/mobile",
      //获取图片上传地址
      BASE_API_UPLOAD:'http://117.78.49.59:30113/',
      //发薪账户跳转地址
      BANK: 'https://sse.chinasoftinc.com:18080/#!/index',

   };
}