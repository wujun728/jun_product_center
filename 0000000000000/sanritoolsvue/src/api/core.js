import request, { headers } from '@/utils/request'
import qs from 'qs'

export default {
  version(){return request.get('/version/current')},

  // 连接管理
  modules(){ return request.get('/connect/modules')},
  moduleConnects(module){return request.get(`/connect/${module}/names`)},
  connects(){return request.get('/connect/all')},
  content(module,connName){return request.get(`/connect/${module}/${connName}`)},
  createConnect(module,params){return request.post(`/connect/create/${module}`,params,headers.json)},
  createKafkaConnect(yamlConfig){return request.post('/kafka/connect/create',yamlConfig,headers.plain)},
  createModule(module){return request.post('/connect/createModule',qs.stringify({name: module}))},
  deleteModule(module){return request.post('/connect/deleteModule',qs.stringify({name: module}))},
  dropConnect(module,connect){return request.post(`/connect/dropConnect/${module}/${connect}`)},
  example(module,format){return request.get(`/connect/${module}/${format}/example`)},

  // 类加载器管理
  classloaders(){ return request.get('/classloader/classloaders')},
  listLoadedClasses(classloaderName){ return request.get('/classloader/listLoadedClasses',{params:{classloaderName}})},
  classloaderLoadedClasses(classloaderName){return request.get('/classloader/classLoaderLoadedClasses',{params:{classloaderName}})},
  uploadClassesZip(formData){ return request.post('/classloader/uploadClassesZip',formData) },
  uploadClassesZipSimple(formData){ return request.post('/classloader/uploadClassesZipSimple',formData)},
  uploadSingleClass(formData){ return request.post('/classloader/uploadSingleClass',formData)},
  uploadSingleJavaFile(formData){ return request.post('/classloader/uploadSingleJavaFile',formData)},
  methodNames(classloaderName,className){return request.get(`/classloader/${classloaderName}/${className}/methodNames`)},
  classStruct(classloaderName,className){return request.get(`/classloader/${classloaderName}/${className}/classStruct`)},
  buildMethodParams(classloaderName,className,methodName){return request.get(`/classloader/${classloaderName}/${className}/${methodName}/buildParams`)},

  // 插件管理
  pluginNames(){return request.get('/plugin/names')},
  plugins(){return request.get('/plugin/list')},
  pluginDetail(key){return request.get('/plugin/detail',{params:{key}})},
  visited(key){return request.get('/plugin/visited',{params:{key}})},
  serializer(){return request.get('/plugin/serializer')},

  // 序列化工具
  serializers(){return request.get('/serializer/names')},

  // 随机数据
  randomData(className,classloaderName){return request.get('/data/random',{params:{className,classloaderName}})},

  // 文件管理
  fileDownload(baseName){
    baseName = baseName.replace(/\\/g,'/');
    this.download("/file/manager/download?baseName="+baseName)
  },

  // 通用下载
  download(url,params){
    request({
      method: 'get',
      url: url,
      params: params,
      responseType: 'blob' // 表明返回服务器返回的数据类型
    }).then(res => {
      const content = res.data
      const blob = new Blob([content]) // 构造一个blob对象来处理数据
      // 对于<a>标签，只有 Firefox 和 Chrome（内核） 支持 download 属性
      // IE10以上支持blob但是依然不支持download
      let fileName = res.headers.filename;
      if ('download' in document.createElement('a')) { // 支持a标签download的浏览器
        const link = document.createElement('a') // 创建a标签
        link.download = fileName // a标签添加属性
        link.style.display = 'none'
        link.href = URL.createObjectURL(blob)
        document.body.appendChild(link)
        link.click() // 执行下载
        URL.revokeObjectURL(link.href) // 释放url
        document.body.removeChild(link) // 释放标签
      } else { // 其他浏览器
        navigator.msSaveBlob(blob, fileName)
      }
    }).catch((error) => {
      console.log(error)
    })

  }
}
