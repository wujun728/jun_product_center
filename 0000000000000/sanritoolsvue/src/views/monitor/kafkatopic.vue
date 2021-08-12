<template>
  <div class="app-container">
    <el-row>
      <el-col>
        <el-button class="margin-right" type="text" icon="el-icon-refresh" @click="reloadAllConnects"/>

        <el-select v-model="input.connect" @change="loadTopics" size="small">
          <el-option v-for="connect in connects" :key="connect" :value="connect" :label="connect" />
        </el-select>
      </el-col>
    </el-row>
    <el-row class="margin-top margin-bottom">
      <p><el-button type="text" @click="refreshBrokerMonitor">JMX监控</el-button> brokers({{brokers.length}}): {{brokers.join(',')}}</p>
    </el-row>
    <el-row class="">
      <el-col :span="10">
        <list-group v-loading="view.topicsLoading" :list="topicNames" @click-item="choseTopic"/>
      </el-col>
      <el-col :span="12" class="margin-left" >
        <p>{{input.topicName}} 分区数: {{input.topic.partitions ? input.topic.partitions.length: 0}}</p>
        <el-button-group>
          <el-button size="small" type="danger" icon="el-icon-delete" @click="deleteTopic">删除 {{input.topicName}}</el-button>
          <el-button size="small" plain icon="el-icon-plus" @click="view.createTopicDialog = true">创建新主题</el-button>
        </el-button-group>

        <el-tabs v-model="view.activeTabName" @tab-click="switchTab">
          <el-tab-pane label="JMX监控" name="zero">
            {{tab.zero.lastUpdateTime}} <el-button type="text" icon="el-icon-refresh" @click="refreshTopicMonitor"/>
            <el-table
              v-loading="tab.zero.view.loading"
              :data="tab.zero.tableData"
              border
              stripe
              size="mini">
              <el-table-column
                prop="mBean"
                label="监控属性"
                width="450"/>
              <el-table-column
                prop="oneMinute"
                label="1分钟"
                width="100"/>
              <el-table-column
                prop="meanRate"
                label="速率"
                width="100"/>
              <el-table-column
                prop="fiveMinute"
                label="5分钟"
                width="100"/>
              <el-table-column
                prop="fifteenMinute"
                label="15分钟"/>
            </el-table>
          </el-tab-pane>
          <el-tab-pane label="基本属性" name="first">
            <el-table
              :data="input.topic.partitions"
              border
              stripe
              style="width: 100%"
              size="mini">
              <el-table-column type="expand">
                <template slot-scope="props">
                  <div class="margin-bottom">Leader: {{props.row.leader}}</div>
                  <p >isr: </p>
                  <el-table
                    :data="props.row.isr"
                    border
                    stripe
                    style="width: 100%"
                    size="mini">
                    <el-table-column
                      type="index"
                      width="50"
                      label=""
                    />
                    <el-table-column
                      prop="id"
                      label="id"
                      width="180"/>
                    <el-table-column
                      prop="host"
                      label="host"
                      width="180"/>
                    <el-table-column
                      prop="port"
                      label="port"/>
                    <el-table-column
                      prop="jmxPort"
                      label="jmxPort"/>
                  </el-table>
                  <p >replicas: </p>
                  <el-table
                    :data="props.row.replicas"
                    border
                    stripe
                    style="width: 100%"
                    size="mini">
                    <el-table-column
                      type="index"
                      width="50"
                      label=""
                    />
                    <el-table-column
                      prop="id"
                      label="id"
                      width="180"/>
                    <el-table-column
                      prop="host"
                      label="host"
                      width="180"/>
                    <el-table-column
                      prop="port"
                      label="port"/>
                    <el-table-column
                      prop="jmxPort"
                      label="jmxPort"/>
                  </el-table>
                </template>
              </el-table-column>
              <el-table-column
                prop="partition"
                label="partition"
                width="180">
              </el-table-column>
              <el-table-column
                prop="leader"
                label="leader"
                width="240">
                <template slot-scope="scope">
                  <span>{{ scope.row.leader? scope.row.leader.host + ':'+ scope.row.leader.port : null}}</span>
                </template>
              </el-table-column>
              <el-table-column
                label="isr"
                width="180">
                <template slot-scope="scope">
                  <span>{{ scope.row.isr.length }}</span>
                </template>
              </el-table-column>
              <el-table-column
                label="replicas">
                <template slot-scope="scope">
                  <span>{{ scope.row.replicas.length }}</span>
                </template>
              </el-table-column>
            </el-table>
          </el-tab-pane>
          <el-tab-pane label="数据查看" name="second">
            <el-button type="text" icon="el-icon-refresh" @click="loadCurrentTopicLogSize" />
            {{tab.second.lastUpdateTime}}
            <el-button type="text" icon="el-icon-search" @click="topicData.view.visible = true" />

            <el-table
              :data="tab.second.tableData"
              v-loading="tab.second.view.loading"
              border
              stripe
              size="mini">
              <el-table-column
                prop="topic"
                label="topic"
                width="350"/>
              <el-table-column
                prop="partition"
                label="partition"
                width="70" sortable/>
              <el-table-column
                prop="logSize"
                label="logSize"
                width="80"/>
              <el-table-column
                label="minOffset"
                width="100"
              >
                <template slot-scope="scope">
                  <span>{{showMinOffset(scope.row)}}</span>
                </template>
              </el-table-column>
              <el-table-column
                prop="time"
                label="timestamp" sortable/>
              <el-table-column
                fixed="right"
                label="操作"
                width="80">
                <template slot-scope="scope">
                  <el-button type="text" :disabled="scope.row.minOffset === scope.row.logSize" size="small" @click="showDataDialog(scope.row)">尾部数据</el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-tab-pane>
          <el-tab-pane label="数据模拟(JSON)" name="third">
            <el-button-group>
              <el-button plain size="small" icon="el-icon-ice-cream-round" @click="lastOneData">随机选一条</el-button>
              <el-button type="primary" size="small" icon="el-icon-position" @click="sendData">发送</el-button>
            </el-button-group>

            选择分区
            <el-input-number size="small" v-model="input.partition" :min="0" :max="dialog.configs.max" label="选择分区"></el-input-number>
            <json-editor v-loading="view.onlyOneDataLoading" :json="input.json" @change="changeJson" />
          </el-tab-pane>
          <el-tab-pane label="数据模拟(Object)" name="four">
            <el-button-group>
              <el-button type="primary" size="small" icon="el-icon-send" >发送</el-button>
            </el-button-group>

            选择分区
            <el-input-number v-model="input.partition" :min="0" :max="dialog.configs.max" label="选择分区"></el-input-number>
            <json-editor :json="input.json" @change="changeJson" />
          </el-tab-pane>
        </el-tabs>
      </el-col>
    </el-row>

    <el-dialog  :visible.sync="view.dialog" :title="dialogTips" width="90%">
      选择分区
      <el-input-number v-model="dialog.configs.partition" :min="0" :max="dialog.configs.max" label="选择分区" size="small"></el-input-number>
      <kafka-data-view :datas="dialog.datas" :loading="view.dialogLoading"  @update-data-show="reloadData" @next-partition="loadNextPartition" />
    </el-dialog>

    <!-- 创建主题 -->
    <el-dialog  :visible.sync="view.createTopicDialog" title="创建新主题" width="30%">
      <el-form v-model="input.form" size="small" label-width="100px">
        <el-form-item label="主题名称">
          <el-input v-model="input.form.topic"></el-input>
        </el-form-item>
        <el-form-item label="分区数">
          <el-input-number v-model="input.form.partitions"></el-input-number>
        </el-form-item>
        <el-form-item label="副本数">
          <el-input-number v-model="input.form.replication"></el-input-number>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="createTopic">确 定</el-button>
      </span>
    </el-dialog>

    <!-- broker 信息展示 -->
    <el-dialog :visible.sync="broker.view.visible" :title="broker.view.title" width="50%">
      {{broker.lastUpdateTime}} <el-button type="text" icon="el-icon-refresh" @click="refreshBrokerMonitor" />
      <el-table
        :data="broker.tableData"
        v-loading="broker.view.loading"
        border
        stripe
        size="mini">
        <el-table-column
          prop="mBean"
          label="监控属性"
          width="450"/>
        <el-table-column
          prop="oneMinute"
          label="1分钟"
          width="100"/>
        <el-table-column
          prop="meanRate"
          label="速率"
          width="100"/>
        <el-table-column
          prop="fiveMinute"
          label="5分钟"
          width="100"/>
        <el-table-column
          prop="fifteenMinute"
          label="15分钟"/>
      </el-table>
    </el-dialog>

    <!-- 数据搜索框| 这个和 kafkaDataView功能有部分重复, 看怎么优化好 -->
    <el-dialog width="90%" :visible.sync="topicData.view.visible" :title="topicData.view.title" >
      <el-row>
        <el-button type="plain" size="small" class="margin-right text-forestgreen" @click="createIndex"><i class="fa fa-play"></i></el-button>
        <el-select v-model="topicData.input.serizlizer" filterable clearable placeholder="序列化" size="small">
          <el-option v-for="serizlizer in topicData.serializers" :key="serizlizer" :label="serizlizer" :value="serizlizer" />
        </el-select>
        <el-select v-model="topicData.input.classloader" filterable clearable placeholder="类加载器" size="small">
          <el-option v-for="classloader in topicData.classloaders" :key="classloader" :label="classloader" :value="classloader" />
        </el-select>
        加载数量(每分区) <el-input-number size="small" v-model="topicData.input.loadCount" />
      </el-row>
      <el-row class="margin-top">
        <el-input v-model="topicData.input.keyword" placeholder="输入关键词搜索" size="small" @keyup.enter.native="indexSearch" @chage="indexSearch" />
        <el-table
          v-loading="topicData.view.loading"
          :data="handleDatas"
          border
          stripe
          height="500"
          size="mini">
          <el-table-column
            type="index"
            width="50"/>
          <el-table-column
            prop="partition"
            label="partition"
            width="70"/>
          <el-table-column
            prop="offset"
            label="offset"
            width="100"/>
          <el-table-column
            prop="time"
            label="time"
            width="150"/>
          <el-table-column
            prop="data"
            label="data"/>
          <el-table-column
            fixed="right"
            label="操作"
            width="100">
            <template slot-scope="scope">
              <el-button type="text" size="small" @click="showJsonData(scope.row)">展示为 JSON</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-row>
    </el-dialog>

    <el-drawer
      :visible.sync="topicData.drawer.visible"
      :with-header="false"
      direction="rtl" append-to-body>
      <json-editor :json="topicData.drawer.data" />
    </el-drawer>
  </div>
</template>

<script>
  import core from '../../api/core'
  import kafka from '../../api/kafka'
  import {parseTime} from '@/utils'

  import ListGroup from '../../components/ListGroup'
  import KafkaDataView from '../components/KafkaDataView'
  import JsonEditor from '../../components/JsonEditor'

  export default {
    name: 'kafkaTopic',
    components: { ListGroup, KafkaDataView, JsonEditor },
    data(){
      return {
        input: {
          topicName: null,
          topic: {},
          json: {},
          partition: 0,
          form: {
            topic: null,
            replication: 1,
            partitions: 1
          }
        },
        view: {
          activeTabName:'third',
          dialog: false,
          dialogLoading: false,
          onlyOneDataLoading: false,
          createTopicDialog: false,
          topicsLoading: false
        },
        topics: [],
        connects: [],
        brokers: [],
        dialog: {
          datas: [],
          configs: {
            partition: null,
            max: 0
          }
        },
        tab: {
          zero: {
            view: {
              loading: false
            },
            lastUpdateTime: null,
            tableData: []
          },
          second: {
            view: {
              loading: false
            },
            lastUpdateTime: null,
            tableData: []
          }
        },
        broker: {
          view: {
            visible: false,
            loading: false,
            title: 'broker 监控'
          },
          tableData: [],
          lastUpdateTime: null
        },
        topicData:{
          input: {
            keyword: null,
            serizlizer: 'string',
            classloader: null,
            loadCount : 10
          },
          view: {
            visible: false,
            loading: false,
            title: '数据查询(索引方式)'
          },
          drawer: {
            visible: false,
            data: null
          },
          tableData: [],
          serializers: [],
          classloaders: [],
        }
      }
    },
    mounted() {
      // 加载所有连接
      this.reloadAllConnects();
    },
    methods: {
      createIndex(){
        this.topicData.view.loading = true;
        kafka.createIndex(this.input.connect,this.input.topicName,this.topicData.input.loadCount,this.topicData.input.serizlizer,this.topicData.input.classloader).then(res => {
          this.topicData.view.loading = false;
          this.indexSearch();
        })
      },
      indexSearch(){
        kafka.indexSearch(this.topicData.input.keyword).then(res => {
          this.topicData.tableData = res.data;
        })
      },
      reloadAllConnects(){
        core.moduleConnects('kafka').then(res => {
          this.connects = res.data;
          if (this.connects && this.connects.length > 0){
            this.input.connect = this.connects[0];
            this.loadTopics(this.input.connect)
          }
        });
      },
      refreshBrokerMonitor(){
        this.broker.view.visible = true;
        this.broker.view.loading = true;
        this.broker.view.title = this.input.connect+' 监控';
        kafka.monitorBroker(this.input.connect).then(res => {
          this.broker.view.loading = false;
          this.broker.tableData = res.data;
          this.broker.lastUpdateTime = parseTime(new Date().getTime());
        })
      },
      refreshTopicMonitor(){
        this.tab.zero.view.loading = true;
        kafka.monitorTopic(this.input.connect,this.input.topicName).then(res => {
          this.tab.zero.view.loading = false;
          this.tab.zero.tableData = res.data;
          this.tab.zero.lastUpdateTime = parseTime(new Date().getTime())
        })
      },
      createTopic(){
        kafka.createTopic(Object.assign({clusterName: this.input.connect},this.input.form)).then(res => {
          this.$message('创建 '+this.input.form.topic+' 成功');
          this.view.createTopicDialog = false;
          this.loadTopics(this.input.connect);
        });
      },
      deleteTopic(){
        this.$confirm('确定删除组 '+this.input.topicName+' 此操作不可逆?','警告',{type: 'warning'}).then(() => {
          kafka.deleteTopic(this.input.connect,this.input.topicName).then(res => {
            this.loadTopics(this.input.connect);
          })
        }).catch(()=>{})

      },
      sendData(){
        let params = {
          clusterName: this.input.connect,
          topic: this.input.topicName,
          data: JSON.stringify(this.input.json)
        }
        kafka.topicSendJsonData(params).then(res => {
          this.$message('发送成功');
        })
      },
      lastOneData(){
        this.input.json = {};   // 先清空数据
        this.view.onlyOneDataLoading = true;
        kafka.topicDataOne(this.input.connect,this.input.topicName,'string',null).then(res => {
          this.view.onlyOneDataLoading = false;
          if (res.data){
            this.input.json = JSON.parse(res.data.data);
          }
        }).catch(res => {
          this.view.onlyOneDataLoading = false;
        })
      },
      changeJson(json){
        this.input.json = json;
      },
      reloadData(serializer,classloader,loadCount){
        let dialogConfig = this.dialog.configs;
        this.view.dialogLoading = true;
        kafka.topicDataLast(this.input.connect,this.input.topicName,dialogConfig.partition,loadCount,serializer,classloader).then(res => {
          this.dialog.datas = res.data;
          res.data.forEach(item => !item.partition ? item.partition = this.dialog.configs.partition : item.partition)
          this.view.dialogLoading = false;
        })
      },
      loadNextPartition(serializer,classloader,loadCount){
        this.dialog.configs.partition ++;
        if (this.dialog.configs.partition > this.dialog.configs.max){
          this.dialog.configs.partition = 0;
        }
        this.reloadData(serializer,classloader,loadCount);
      },
      showDataDialog(row){
        this.dialog.configs.partition = row.partition;
        let partitions = this.input.topic.partitions ?  this.input.topic.partitions.length: 0
        if (partitions !== 0){
          this.dialog.configs.max = partitions - 1;
        }
        this.view.dialog = true;
        this.dialog.datas = [];

      },
      switchTab(tab) {
        if (tab.index === '1'){
          this.loadCurrentTopicLogSize();
        }else if (tab.name === 'second'){
          if (!this.topicData.classloaders || this.topicData.classloaders.length === 0){
            // 加载序列化和类加载器, 用于主题数据搜索
            core.serializers().then(res => {
              this.topicData.serializers = res.data;
            })
            core.classloaders().then(res => {
              this.topicData.classloaders = res.data;
            });
          }

        }
      },
      showMinOffset(row){
        return row.minOffset+' ('+(row.logSize - row.minOffset)+')'
      },
      loadTopics(connect) {
        this.view.topicsLoading = true;
        // 加载所有的主题信息
        kafka.topics(connect).then(res => {
          this.view.topicsLoading = false;
          this.topics = res.data;
          if (this.topics && this.topics.length > 0) {
            let topicName = this.topics[0].name;
            this.choseTopic(topicName,0)
          }
        })
        // 加载所有的 brokers
        kafka.brokers(connect).then(res => {
          this.brokers = res.data;
        })
      },
      choseTopic(value, index) {
        this.input.topicName = value;
        this.input.topic = this.topics[index];

        // this.loadCurrentTopicLogSize();
        this.refreshTopicMonitor();
      },
      loadCurrentTopicLogSize(){
        this.tab.second.view.loading = true;
        kafka.logSize(this.input.connect,this.input.topicName).then(res => {
          this.tab.second.lastUpdateTime = parseTime(new Date().getTime());
          this.tab.second.tableData = res.data.map(item => Object.assign({time: parseTime(item.timestamp)},item));
          this.tab.second.view.loading = false;
        }).catch(res => {
          this.tab.second.view.loading = false;
        })
      },
      showJsonData(row){
        this.topicData.drawer.visible = true;
        this.topicData.drawer.data = JSON.parse(row.data)
      }
    },
    computed:{
      topicNames(){
        return this.topics.map(item => item.name)
      },
      dialogTips(){
        return this.input.topicName +'_'+this.dialog.configs.partition+' 尾部数据';
      },
      handleDatas(){
        return this.topicData.tableData.map(item => Object.assign({time: parseTime(item.timestamp)},item));
      }
    },
    watch:{
      'input.topicName':{
        handler(newTopic,oldTopic){
          if (this.view.activeTabName !== 'second'){
            this.currentTopicLogSize = [];
          }else{
            this.loadCurrentTopicLogSize();
          }

        }
      }
    }
  }
</script>

<style scoped>

</style>
