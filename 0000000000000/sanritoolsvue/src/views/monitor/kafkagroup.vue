<template>
    <div class="app-container">
      <el-row>
        <el-col>
          <el-button class="margin-right" type="text" icon="el-icon-refresh" @click="reloadAllConnects"/>
          <el-select v-model="input.connect" @change="loadGroups" size="small">
            <el-option v-for="connect in connects" :key="connect" :value="connect" :label="connect" />
          </el-select>
        </el-col>
      </el-row>
      <el-row class="margin-top margin-bottom">
        <p>brokers({{brokers.length}}): {{brokers.join(',')}}</p>
      </el-row>
      <el-row class="">
        <el-col :span="6">
          <list-group v-loading="view.groupsLoading" :list="groups" @click-item="choseGroup"/>
        </el-col>
        <el-col :span="17" class="margin-left">
          <p>当前选中分组: {{input.group}} , 消费主题数: {{currentGroupInfo.topics.length}} </p>
          <p>组协调器: {{currentGroupInfo.subscribes.coordinator}} </p>
          <p>分区策略: {{currentGroupInfo.subscribes.partitionAssignor}} </p>
          <p>
            <el-button-group>
              <el-button type="danger"  size="small" icon="el-icon-delete" @click="deleteGroup">删除 {{input.group}}</el-button>
              <el-button size="small" icon="el-icon-search" >{{input.group}} 消费情况预览</el-button>
            </el-button-group>
          </p>
          <el-row>
            <el-col :span="10">
              <list-group :list="currentGroupInfo.topics" @click-item="showGroupTopicSubscribe" />
            </el-col>
            <el-col :span="12" class="margin-left">
              <p>当前选中主题: {{currentGroupInfo.currentTopicInfo.topic}}, 分区数: {{subscribes.length}}</p>
              <p>logSize: {{currentGroupInfo.currentTopicInfo.logSize}} offset: {{currentGroupInfo.currentTopicInfo.offset}} lag: {{currentGroupInfo.currentTopicInfo.lag}}</p>
              <el-table
                v-loading="view.loading"
                :data="subscribes"
                border
                stripe
                size="mini">
                <el-table-column
                  prop="partition"
                  label="分区"
                  width="45"/>
                <el-table-column
                  prop="host"
                  label="消费主机"
                  width="105"/>
                <el-table-column
                  label="minOffset">
                  <template slot-scope="scope">
                    <span>{{showMinOffset(scope.row)}}</span>
                  </template>
                </el-table-column>
                <el-table-column
                  prop="logSize"
                  label="logSize"/>
                <el-table-column
                  prop="offset"
                  label="offset"/>
                <el-table-column
                  prop="lag"
                  label="lag"/>
                <el-table-column
                  fixed="right"
                  label="操作"
                  width="140">
                  <template slot-scope="scope">
                    <el-button type="text" :disabled="scope.row.offset === scope.row.logSize" size="small" @click="showDataDialog(scope.row,'topicDataNearby','附近数据')">附近数据</el-button>
                    <el-button type="text" :disabled="scope.row.minOffset === scope.row.logSize" size="small" @click="showDataDialog(scope.row,'topicDataLast','尾部数据')">尾部数据</el-button>
                  </template>
                </el-table-column>
              </el-table>
            </el-col>
          </el-row>
        </el-col>
      </el-row>

      <el-dialog  :visible.sync="view.dialog" :title="dialogTips" width="90%">
        选择分区
        <el-input-number v-model="dialog.configs.partition" :min="0" :max="10" label="选择分区" size="small"></el-input-number>
        <kafka-data-view :datas="dialog.datas" :loading="view.dialogLoading"  @update-data-show="reloadData" @next-partition="loadNextPartition" />
      </el-dialog>
    </div>
</template>

<script>
  import kafka from '../../api/kafka'
  import core from '../../api/core'
  import ListGroup from '../../components/ListGroup'
  import KafkaDataView from '../components/KafkaDataView'

  export default {
    name: 'kafkaGroup',
    components: { ListGroup, KafkaDataView },
    data(){
      return {
        input: {
          connect: null,
          group: null
        },
        view: {
          loading: false,
          dialog: false,
          dialogLoading: false,
          groupsLoading: false
        },
        currentGroupInfo: {
          topics: [],
          subscribes: {},
          currentTopicInfo: {
            topic: null,
            logSize: 0,
            offset: 0,
            lag: 0
          }
        },
        connects: [],
        groups: [],
        subscribes:[],
        brokers: [],
        dialog: {
          input: {},
          datas: [],
          configs: {
            partition: null,
            method: null,
            chinese: null,
            max: 0
          }
        }
      }
    },
    methods:{
      showDataDialog(row,method,chinese){
        this.dialog.configs.partition = row.partition;
        this.dialog.configs.method = method //'topicDataNearby';topicDataLast
        this.dialog.configs.chinese = chinese;
        this.dialog.configs.max = this.subscribes.length - 1;
        this.view.dialog = true;
        this.dialog.datas = [];

        // this.dialog.tips = row.topic + '_'+row.partition +' '+ chinese + '的数据 ';
      },
      loadNextPartition(serializer,classloader,loadCount){
        this.dialog.configs.partition ++;
        if (this.dialog.configs.partition > this.dialog.configs.max){
          this.dialog.configs.partition = 0;
        }
        this.reloadData(serializer,classloader,loadCount);
      },
      reloadData(serializer,classloader,loadCount){
        let dialogConfig = this.dialog.configs;
        this.view.dialogLoading = true;
        kafka[dialogConfig.method](this.input.connect,this.currentGroupInfo.currentTopicInfo.topic,dialogConfig.partition,loadCount,serializer,classloader).then(res => {
          this.dialog.datas = res.data;
          res.data.forEach(item => !item.partition ? item.partition = this.dialog.configs.partition : item.partition)
          this.view.dialogLoading = false;
        })
      },
      deleteGroup(){
        this.$confirm('确定删除组 '+this.input.group+' 此操作不可逆?','警告',{type: 'warning'}).then(() => {
          kafka.deleteGroup(this.input.connect,this.input.group).then(res => {
            this.loadGroups(this.input.connect);
          })
        }).catch(()=>{})

      },
      loadGroups(connect){
        this.view.groupsLoading = true
        // 加载所有的分组
        kafka.groups(connect).then(res => {
          this.view.groupsLoading = false;
          this.groups = res.data;
          if (this.groups && this.groups.length > 0){
            this.choseGroup(this.groups[0])
          }
        })
        // 加载所有的 brokers
        kafka.brokers(connect).then(res => {
          this.brokers = res.data;
        })
      },
      choseGroup(value){
        this.input.group = value;
        this.subscribes = [];
        kafka.subscribeTopics(this.input.connect,value).then(res => {
          this.currentGroupInfo.topics = res.data;
          if (this.currentGroupInfo.topics && this.currentGroupInfo.topics.length > 0){
            kafka.subscribes(this.input.connect,value).then(res => {
              this.currentGroupInfo.subscribes = res.data;
              this.showGroupTopicSubscribe(this.currentGroupInfo.topics[0]);
            })
          }
        });

      },
      showMinOffset(row){
        return row.minOffset+' ('+(row.logSize - row.minOffset)+')'
      },
      showGroupTopicSubscribe(value){
        this.view.loading = true;
        this.currentGroupInfo.currentTopicInfo.topic = value;

        let subscribes = [];
        // 获取消费主机信息, 对于每个分区
        let members = this.currentGroupInfo.subscribes.memberInfos;
        for (let i = 0; i < members.length; i++) {
          let topicPartitions = members[i].topicPartitions;
          for (let j = 0; j < topicPartitions.length; j++) {
            if (topicPartitions[j].topic === value){
              topicPartitions[j].host = members[i].host;
              subscribes.push(topicPartitions[j]);
            }
          }
        }
        subscribes.sort((a,b) => a.partition - b.partition);

        // 获取消费的 logSize offset lag 信息
        this.currentGroupInfo.currentTopicInfo.logSize = 0 ;
        this.currentGroupInfo.currentTopicInfo.offset = 0 ;
        this.currentGroupInfo.currentTopicInfo.lag = 0 ;
        kafka.subscribeTopicOffset(this.input.connect,this.input.group,value).then(res => {
          let offsetInfos = res.data;
          offsetInfos.sort((a,b) => a.partition - b.partition);
          for (let i = 0; i < subscribes.length; i++) {
            let item = subscribes[i];
            let mergeItem = offsetInfos[i];

            if (mergeItem){
              Object.assign(item,mergeItem);

              this.currentGroupInfo.currentTopicInfo.logSize += mergeItem.logSize;
              this.currentGroupInfo.currentTopicInfo.offset += mergeItem.offset;
              this.currentGroupInfo.currentTopicInfo.lag += mergeItem.lag;
            }
          }

          this.subscribes = subscribes;
          this.view.loading = false;
        })
      },
      reloadAllConnects(){
        core.moduleConnects('kafka').then(res => {
          this.connects = res.data;
          if (this.connects && this.connects.length > 0){
            this.input.connect = this.connects[0];
            this.loadGroups(this.input.connect)
          }
        });
      }
    },
    mounted() {
      // 加载所有连接
      this.reloadAllConnects();
    },
    computed: {
      dialogTips(){
        return this.currentGroupInfo.currentTopicInfo.topic + '_'+this.dialog.configs.partition+'_'+this.dialog.configs.chinese+' 的数据';
      }
    }
  }
</script>

<style scoped>

</style>
