import request, { headers } from '@/utils/request'
import qs from 'qs'
const UUID = require('uuid')

export default {
  // 元数据接口
  connections() { return request.get('/db/metadata/connections') },
  catalogs(connName) { return request.get('/db/metadata/catalogs',{params: {connName}}) },
  tables(connName, catalog) { return request.get('/db/metadata/tables',{params:{connName,catalog}})},
  refreshCatalogOrSchema(connName, catalog, schema) { return request.get('/db/metadata/refreshCatalogOrSchema',{params:{connName,catalog,schema}})},
  refreshTable(connName,actualTableName){return request.get('/db/metadata/refreshTable',{params:{connName,...actualTableName}})},
  searchTables(connName,catalog,schemas,keyword){return request.get('/db/metadata/searchTables',{params:{connName,catalog,...{schemas:schemas.join(',')},keyword}})},

  // 数据查询
  executeQuery(connName,sql){return request.post('/db/data/executeQuery',{connName:connName,sqls:[sql]})},
  groups(connName,schemaName){return request.get('/db/data/config/groups',{params:{connName,schemaName}})},
  dataIds(connName,schemaName,groupId){return request.get('/db/data/config/dataIds',{params:{connName,schemaName,groupId}})},
  content(connName,schemaName,groupId,dataId){return request.get('/db/data/config/content',{params:{connName,schemaName,groupId,dataId}})},
  emptyTable(connName,actualTableName){return request.post('/db/data/emptyTable',qs.stringify({connName,...actualTableName}))},

  // 代码生成相关接口
  renameStrategies(){return request.get('/db/code/renameStrategies')},
  buildJavaBean(params){return request.post('/db/code/build/javaBean',params,headers.json)},
  buildMapper(params){return request.post('/db/code/build/mapper',params,headers.json)},
  templates(){return request.get('/db/code/templates')},
  uploadTemplate(formData){return request.post('/db/code/template/upload',formData)},
  templateContent(templateName){return request.get(`/db/code/${templateName}/content`)},
  // dropTemplate(templateName){ return request.post('/db/code/template/drop',qs.stringify({templateName}))},
  override(params){return request.post('/db/code/override',params,headers.json)},
  codeSchemas(){return request.get('/db/code/schemas')},
  codeSchemaTemplates(schema){return request.get(`/db/code/${schema}/templates`)},
  codePreview(params){return request.post('/db/code/template/code/preview',params)},
  codeGenerate(params){return request.post('/db/code/template/code/generator',params)},

  // 表标记,表关系 元数据扩展
  tags(){return request.get('/db/metadata/extend/mark/tags')},
  markTag(params){return request.post('/db/metadata/extend/mark/config/tableMark',params,headers.json)},
  tableTags(connName,catalog,schema,tableName){return request.get('/db/metadata/extend/mark/tableTags',{params:{connName,catalog,schema,tableName}})},
  tagTables(connName,catalog,schema,tag){return request.get('/db/metadata/extend/mark/tagTables',{params:{connName,catalog,schema,tag}})},
  configRelation(params){return request.post('/db/metadata/extend/relation/config',params,headers.json)},
  parentRelations(connName,table){return request.get('/db/metadata/extend/relation/parents',{params:{connName,...table}})},
  childRelations(connName,table){return request.get('/db/metadata/extend/relation/childs',{params:{connName,...table}})},
  hierarchy(connName,table){return request.get('/db/metadata/extend/relation/hierarchy',{params:{connName,...table}})},
  superTypes(connName,table){return request.get('/db/metadata/extend/relation/superTypes',{params:{connName,...table}})},

  // 表数据导出相关
  exportPreview(connName,sql){
    // let uuid = uuid.v1();
    // console.log(UUID,'UUID');
    let uuid = UUID.v1();
    return request.post('/db/data/exportPreview',{connName:connName,sqls:[sql],traceId:uuid},headers.json);
  },
  exportData(connName,sql){
    // let uuid = uuid.v1()
    let uuid = UUID.v1();
    return request.post('/db/data/exportData',{connName:connName,sqls:[sql],traceId:uuid},headers.json);
  }
}
