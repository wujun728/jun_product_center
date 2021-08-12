import request from '@/utils/request'
import qs from 'qs'

export default {
  brokers(clusterName){return request.get('/kafka/brokers',{params:{clusterName}})},
  topics(clusterName){return request.get('/kafka/topics',{params:{clusterName}})},
  logSize(clusterName,topic){return request.get('/kafka/topic/logSize',{params:{clusterName,topic}})},
  groups(clusterName){return request.get('/kafka/groups',{params:{clusterName}})},
  subscribeTopics(clusterName,group){return request.get('/kafka/group/topics',{params:{clusterName,group}})},
  subscribes(clusterName,group){return request.get('/kafka/group/subscribes',{params:{clusterName,group}})},
  subscribeTopicOffset(clusterName,group,topic){return request.get('/kafka/group/topic/offset',{params:{clusterName,group,topic}})},
  subscribesOffset(clusterName,group){return request.get('/kafka/group/topics/offset',{params:{clusterName,group}})},
  topicDataLast(clusterName,topic,partition,perPartitionSize,serializer,classloader){
    return request.get('/kafka/topic/data/last',{params:{clusterName,topic,partition,perPartitionSize,serializer,classloader}})
  },
  topicDataOne(clusterName,topic,serializer,classloader){
    return request.get('/kafka/topic/data/one',{params:{clusterName,topic,serializer,classloader}})
  },
  topicDataNearby(clusterName,topic,partition,perPartitionSize,serializer,classloader){
    return request.get('/kafka/group/topic/data/nearby',{params:{clusterName,topic,partition,perPartitionSize,serializer,classloader}})
  },
  topicSendJsonData(params){return request.post('/kafka/topic/data/send/json',params)},
  createIndex(clusterName,topic,perPartitionSize,serializer,classloader){return request.get('/kafka/topic/data/consumerDataAndCreateIndex',{params:{clusterName,topic,perPartitionSize,serializer,classloader}})},
  indexSearch(keyword){return request.get('/kafka/topic/data/search',{params:{keyword}})},
  deleteGroup(clusterName,group){return request.post('/kafka/group/delete',qs.stringify({clusterName,group}))},
  deleteTopic(clusterName,topic){return request.post('/kafka/topic/delete',qs.stringify({clusterName,topic}))},
  createTopic(form){return request.post('/kafka/topic/create',qs.stringify(form))},
  monitorTopic(clusterName,topic){return request.get(`/kafka/monitor/topic/${topic}`,{params:{clusterName}})},
  monitorBroker(clusterName){return request.get('/kafka/monitor/broker',{params:{clusterName}})}
}
