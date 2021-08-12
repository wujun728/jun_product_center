import request, { headers } from '@/utils/request'
import qs from 'qs'

export default {
  loadCache(){return request.get('/quartz/loadCache')},
  triggers(connName,catalog,schema){return request.get('/quartz/triggers',{params:{connName,catalog,schema}})},
  bindQuartz(connName,settings){return request.post(`/quartz/${connName}/bindQuartz`,settings,headers.json)},
  editJob(connName,editJobParam){return request.post(`/quartz/${connName}/editJob`,editJobParam,headers.json)},
  trigger(connName,triggerKey,classloaderName){return request.get('/quartz/trigger',{params:{connName,...triggerKey,classloaderName}})},
  pause(connName,triggerKey,classloaderName){return request.get('/quartz/pause',{params:{connName,...triggerKey,classloaderName}})},
  resume(connName,triggerKey,classloaderName){return request.get('/quartz/resume',{params:{connName,...triggerKey,classloaderName}})},
  remove(connName,triggerName,triggerGroup,jobName,jobGroup){return request.get('/quartz/remove',{params:{connName,triggerName,triggerGroup,jobName,jobGroup}})},
}
