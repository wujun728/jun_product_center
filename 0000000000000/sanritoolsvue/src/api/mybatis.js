import request from '@/utils/request'

export default {
  // 需要传 project,classloaderName,mapper:File
  uploadMapperFile(formData){return request.post('/mybatis/uploadMapperFile',formData)},
  reload(){return request.get('/mybatis/reload')},
  projects(){return request.get('/mybatis/projects')},
  statementIds(project){return request.get('/mybatis/statementIds',{params:{project}})},
  statementParams(project,statementId){return request.get('/mybatis/statementParams',{params:{project,statementId}})},
  boundSql(project,statementId,connName,className,args){
    let arg = {arg:{value:args}}
    return request.post('/mybatis/boundSql',{project,statementId,connName,className,...arg})
  }
}
