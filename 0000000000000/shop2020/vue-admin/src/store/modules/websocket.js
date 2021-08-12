import { getStore,setStore } from '/utils/storage'

export default {
  state: {
    websock: null,
    eventlist:[]
  },
  getters: {
    onEvent(state) {
      return function (method) {
          let index = state.eventlist.map((eb) => {return eb.method}).indexOf(method);
          if (state.eventlist.length > 0 && index >= 0) {
              let result = Object.assign({}, state.eventlist[index]);
              state.eventlist.splice(index, 1);
              return result.data;
          }
          return null;
      }
    }
  },
  mutations: {
    WEBSOCKET_INIT(state,url) {
       //兼容 FireFox
      if ("WebSocket" in window) {
        state.websock = new WebSocket(url);
      } else if ("MozWebSocket" in window) {
        state.websock = new MozWebSocket(url);

      }
      //websocket默认是传输字符串的，需要改为arraybuffer二进制传输类型
       state.websock.onopen = function () {
          console.log("连接成功！");
      }
      state.websock.onmessage = function (callBack) {
          console.log("ws接收！");
          console.log(callBack.data);
          //将接收到的二进制数据转为字符串
          var unit8Arr = new Uint8Array(callBack.data) ;
          console.log(unit8Arr)
          var type = 0;
          if(unit8Arr){
            console.log(byteToString(unit8Arr))
            unit8Arr=JSON.parse(byteToString(unit8Arr));
            if(unit8Arr.type&&unit8Arr.type>=100){
              if (type==100){
                console.log("收到服务器心跳包！")
                return;
              }
            }
            if(unit8Arr.method){
              console.log("method:"+unit8Arr.method)
              state.eventlist.push({
                method:unit8Arr.method,
                data:unit8Arr.data
              })
            }
          }
        }
        state.websock.οnerrοr=function(e) { //错误
            console.log("ws错误!");
            console.log(e);
        }
        state.websock.onclose=function(e) { //关闭
            console.log("ws关闭！");
            console.log(e);
        }
        state.websock.binaryType = "arraybuffer";
        //发送心跳包
        setInterval(function() {
            console.log("ws发送心跳！");
            var heart={
                type:100,
                method: "",
                data:{}
            };
            state.websock.send(stringToByte(heart));
        }, 30000)
    },
    WEBSOCKET_SEND(state, p) {
        console.log("ws发送！");
        state.websock.send(stringToByte(p));
    }
  },
  actions: {
    WEBSOCKET_INIT({commit},url) {
      commit('WEBSOCKET_INIT',url)
    },
    WEBSOCKET_SEND({commit}, p) {
        p.type=3;
        commit('WEBSOCKET_SEND', p)
    }
  }
}

//将字符串转为 Array byte数组
function stringToByte(str) {
  var bytes = new Array();
  var len, c;
  len = str.length;
  for(var i = 0; i < len; i++) {
      c = str.charCodeAt(i);
      if(c >= 0x010000 && c <= 0x10FFFF) {
          bytes.push(((c >> 18) & 0x07) | 0xF0);
          bytes.push(((c >> 12) & 0x3F) | 0x80);
          bytes.push(((c >> 6) & 0x3F) | 0x80);
          bytes.push((c & 0x3F) | 0x80);
      } else if(c >= 0x000800 && c <= 0x00FFFF) {
          bytes.push(((c >> 12) & 0x0F) | 0xE0);
          bytes.push(((c >> 6) & 0x3F) | 0x80);
          bytes.push((c & 0x3F) | 0x80);
      } else if(c >= 0x000080 && c <= 0x0007FF) {
          bytes.push(((c >> 6) & 0x1F) | 0xC0);
          bytes.push((c & 0x3F) | 0x80);
      } else {
          bytes.push(c & 0xFF);
      }
  }
  return bytes;


}

//byte数组转字符串
function byteToString(arr) {
if(typeof arr === 'string') {
  return arr;
}
var str = '',
  _arr = arr;
for(var i = 0; i < _arr.length; i++) {
  var one = _arr[i].toString(2),
    v = one.match(/^1+?(?=0)/);
  if(v && one.length == 8) {
    var bytesLength = v[0].length;
    var store = _arr[i].toString(2).slice(7 - bytesLength);
    for(var st = 1; st < bytesLength; st++) {
      store += _arr[st + i].toString(2).slice(2);
    }
    str += String.fromCharCode(parseInt(store, 2));
    i += bytesLength - 1;
  } else {
    str += String.fromCharCode(_arr[i]);
  }
}
return str;
}
