<template>
  <div class="app-container">
    <el-row>
      <el-col>
        <el-button class="margin-right" type="text" icon="el-icon-refresh" @click="reloadAllConnects"/>

        <el-select v-model="connParam.connName" @change="loadConnect" size="small">
          <el-option v-for="connect in connects" :key="connect" :value="connect" :label="connect"/>
        </el-select>
        <el-select v-model="connParam.index"  size="small" :disabled="tabs.dashboard.mode === 'cluster'" style="width: 60px">
          <el-option v-for="db in dbsComputed" :key="db" :value="db" :label="db" />
        </el-select>
      </el-col>
    </el-row>

    <el-tabs :active-name="tabs.activeTabName" @tab-click="switchTab">
      <!-- 监控总览(内存,运行模式) -->
      <el-tab-pane name="dashboard" label="监控总览(实时)">
        <p>运行模式: {{tabs.dashboard.mode}}</p>
        <div class="panel panel-default">
          <div class="panel-heading">内存信息 <el-button style="padding:0" size="mini" type="text" icon="el-icon-refresh" @click="loadMemoryUse"/></div>
          <div class="panel-body">
            <el-table
                    :data="tabs.dashboard.memoryUses"
                    border
                    stripe
                    style="width: 100%"
                    size="mini">
              <el-table-column
                      label="node">
                <template slot-scope="scope">
                  <span>{{scope.row.target.host + ':' + scope.row.target.port}}</span>
                </template>
              </el-table-column>
              <el-table-column
                prop="role"
                label="role"
              />
              <el-table-column
                      prop="rss"
                      label="rss" :formatter="formatSize" />
              <el-table-column
                      prop="max"
                      label="max"
                      width="130" :formatter="formatSize" />
              <el-table-column
                      prop="system"
                      label="system" :formatter="formatSize" />
              <el-table-column
                      prop="lua"
                      label="lua"
                      :formatter="formatSize"
              />
              <el-table-column
                      prop="policy"
                      label="policy"
              />
              <el-table-column
                prop="dbSize"
                label="dbSize"
              />
            </el-table>
          </div>
        </div>

        <div class="panel panel-default">
          <div class="panel-heading">拓扑信息 <el-button style="padding:0" size="mini" type="text" icon="el-icon-refresh" @click="loadNodes"/></div>
          <div class="panel-body">
            <el-table
                    :data="nodesHandle"
                    border
                    stripe
                    style="width: 100%"
                    size="mini" class="margin-top">
              <el-table-column
                      prop="id"
                      label="id"
                      width="300">
              </el-table-column>
              <el-table-column
                      prop="connectString"
                      label="host:port"
                      width="150">
              </el-table-column>
              <el-table-column
                      prop="role"
                      label="role"
                      width="60">
              </el-table-column>
              <el-table-column
                      prop="master"
                      label="master"
                      width="300"
              />
              <el-table-column
                      prop="slotStart"
                      label="slotStart"
              >
              </el-table-column>
              <el-table-column
                      prop="slotEnd"
                      label="slotEnd"
              >
              </el-table-column>
            </el-table>
          </div>
        </div>
      </el-tab-pane>
      <!-- 客户端连接管理 -->
      <el-tab-pane name="clients" label="客户端连接(实时)">
        <p><b>总连接数: </b>{{totalClients}}</p>
        <el-row v-loading="tabs.clients.loading">
          <el-col :span="6">
            <ul class="list-group ">
              <li :class="tabs.clients.chose === index ? 'active list-group-item ':'list-group-item cursor-point'"  v-for="(connect,index ) in tabs.clients.connects" @click="brokerClients(index)">
                <span><b style="width: 70px;display: inline-block">{{connect.role}}</b>{{connect.target.host+':'+connect.target.port}}</span>
                <span class="pull-right cursor-point text-danger" >{{connect.clients.length}}</span>
              </li>
            </ul>

            <el-table
              :data="tabs.clients.clientConnects"
              border
              stripe
              style="width: 100%"
              size="mini"
            >
              <el-table-column
                prop="host"
                label="host"/>
              <el-table-column
                prop="connections"
                label="connections"/>
            </el-table>
          </el-col>
          <el-col :span="17" class="margin-left">
            <el-table
              :data="choseConnects"
              border
              stripe
              style="width: 100%"
              size="mini"
              height="400"
            >
              <el-table-column
                prop="id"
                label="id"/>
              <el-table-column
                label="client" sortable
                width="150"
              >
                <template slot-scope="scope">
                  <span>{{scope.row.connect.host + ':' + scope.row.connect.port}}</span>
                </template>
              </el-table-column>
              <el-table-column
                prop="age"
                label="age" sortable/>
              <el-table-column
                prop="idle"
                label="idle" sortable/>
              <el-table-column
                prop="cmd"
                label="cmd" sortable/>
              <el-table-column
                fixed="right"
                label="操作"
                width="150"
              >
                <template slot-scope="scope">
                  <el-button type="text" class="text-danger" size="small" @click="killClient(scope.row.id)">kill</el-button>
                </template>
              </el-table-column>
            </el-table>
            <!--分页组件-->
            <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="tabs.clients.currentPage"
              :page-size="tabs.clients.pageSize"
              layout="total, sizes, prev, pager, next"
              :total="currentClients">
            </el-pagination>
          </el-col>
        </el-row>
      </el-tab-pane>
      <el-tab-pane name="slowlogs" label="慢查询(实时)">
<!--        <p><b>总慢查询数量:</b></p>-->
        <el-row v-loading="tabs.slowlogs.loading">
          <el-col :span="6">
            <ul class="list-group ">
              <li :class="tabs.slowlogs.chose === index ? 'active list-group-item ':'list-group-item cursor-point'"  v-for="(query,index ) in tabs.slowlogs.querys" @click="choseClientQuery(index)">
                <span><b style="width: 70px;display: inline-block">{{query.role}}</b>{{query.target.host+':'+query.target.port}}</span>
                <span class="pull-right cursor-point text-danger" >{{query.slowlogs.length}}</span>
              </li>
            </ul>
          </el-col>
          <el-col :span="17" class="margin-left">
            <el-table
                    :data="tabs.slowlogs.clientQuerys"
                    border
                    stripe
                    style="width: 100%"
                    size="mini"
                    height="400"
            >
              <el-table-column
                      prop="id"
                      label="id" width="80"/>

              <el-table-column
                      label="timeStamp" sortable width="150">
                <template slot-scope="scope">
                  <span>{{parseTime(scope.row.timeStamp)}}</span>
                </template>
              </el-table-column>

              <el-table-column
                      prop="executionTime"
                      label="executionTime" sortable width="140"/>

              <el-table-column
                      label="args" >
                <template slot-scope="scope">
                  <span>{{scope.row.args ? scope.row.args.join(' '): ''}}</span>
                </template>
              </el-table-column>
              <el-table-column
                      fixed="right"
                      label="操作"
                      width="150"
              >
                <template slot-scope="scope">
                  <el-button type="text" class="text-danger" size="small" @click="killQuery(scope.row.id)">kill</el-button>
                </template>
              </el-table-column>
            </el-table>
            <!--分页组件-->
            <el-pagination
                    @size-change="handleSlowlogSizeChange"
                    @current-change="handleSlowlogCurrentChange"
                    :current-page="tabs.slowlogs.currentPage"
                    :page-size="tabs.slowlogs.pageSize"
                    layout="total, sizes, prev, pager, next"
                    :total="tabs.slowlogs.clientQuerys.length">
            </el-pagination>
          </el-col>
        </el-row>
      </el-tab-pane>
      <!-- 数据查询 -->
      <el-tab-pane name="third" label="数据查询">
        <el-row>
          <el-col>
            <el-form  inline size="mini">
              <el-form-item label="类加载器">
                <el-select v-model="tabs.third.input.serializerParam.classloader" style="width: 150px" >
                  <el-option v-for="classloader in classloaders" :key="classloader" :value="classloader"
                             :label="classloader"/>
                </el-select>
              </el-form-item>
              <el-form-item label="key">
                <el-select v-model="tabs.third.input.serializerParam.keySerializer" style="width: 100px;">
                  <el-option v-for="serializer in serializers" :key="serializer" :value="serializer" :label="serializer"/>
                </el-select>
              </el-form-item>
              <el-form-item label="value">
                <el-select v-model="tabs.third.input.serializerParam.value" style="width: 100px;">
                  <el-option v-for="serializer in serializers" :key="serializer" :value="serializer" :label="serializer"/>
                </el-select>
              </el-form-item>
              <el-form-item label="hashKey">
                <el-select v-model="tabs.third.input.serializerParam.hashKey" style="width: 100px;">
                  <el-option v-for="serializer in serializers" :key="serializer" :value="serializer" :label="serializer"/>
                </el-select>
              </el-form-item>
              <el-form-item label="hashValue">
                <el-select v-model="tabs.third.input.serializerParam.hashValue" style="width: 100px;">
                  <el-option v-for="serializer in serializers" :key="serializer" :value="serializer" :label="serializer"/>
                </el-select>
              </el-form-item>

              <el-form-item>
                  <el-button type="text"   size="mini" @click="localStorageConfig" > <i class="fa fa-floppy-o" aria-hidden="true"></i></el-button>
              </el-form-item>

            </el-form>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="15">
            <el-row>
              <el-col :span="24">
                <el-form :inline="true" @submit.native.prevent>
                  <el-form-item>
                    <el-checkbox v-model="tabs.third.input.search.wildcardLeft">左模糊</el-checkbox>
                    <el-checkbox v-model="tabs.third.input.search.wildcardRight">右模糊</el-checkbox>
                    <el-checkbox v-model="tabs.third.input.search.fast">快速搜索</el-checkbox>
                  </el-form-item>
                  <el-form-item>
                    <el-input v-model="tabs.third.input.keyScanParam.pattern" style="width: 380px" size="small" placeholder="输入 key 发起搜索" clearable
                              suffix-icon="el-icon-search" @keyup.enter.native="scanKey"/>
                  </el-form-item>
                  <el-form-item label="超时(ms)">
                    <el-input :disabled="tabs.third.input.search.fast" v-model="tabs.third.input.search.timeout" size="small"  style="width: 80px" />
                  </el-form-item>
                  <el-form-item>
                    <el-button  size="small" type="danger" @click="dropChoseKeys">删除 keys</el-button>
                  </el-form-item>
                </el-form>
              </el-col>
            </el-row>

            <el-table
              :data="tabs.third.keyScanResult.keys"
              v-loading="tabs.third.loading"
              border
              stripe
              style="width: 100%"
              size="mini"
              height="500"
              ref="keyScanTable"
            >
              <el-table-column
                type="selection"
                width="55" />
              <el-table-column
                type="index"
                width="50"/>
              <el-table-column
                prop="type"
                label="type"
                width="90" />
              <el-table-column
                prop="key"
                label="key"
                width="450" />
              <el-table-column
                prop="ttl"
                label="ttl"
                width="90" />
              <el-table-column
                prop="length"
                label="length" />

              <el-table-column
                prop="pttl"
                label="pttl"
                width="90" />
              <el-table-column
                prop="slot"
                label="slot"
                width="100"/>

              <el-table-column
                fixed="right"
                label="操作"
                width="150"
              >
                <template slot-scope="scope">
                  <el-button type="text" class="text-danger" size="small" @click="dropKeys(scope.row.key)">删除 key</el-button>
                  <el-button type="text" size="small" @click="readData(scope.row)">数据</el-button>
                </template>
              </el-table-column>
            </el-table>
            <el-button-group>
              <el-button plain size="mini" icon="el-icon-coordinate" @click="reScan">重置</el-button>
              <el-button plain size="mini" :disabled="tabs.third.keyScanResult.done" icon="el-icon-arrow-right" @click="nextPage">下一页</el-button>
            </el-button-group>
            cursor: {{tabs.third.input.keyScanParam.cursor}}
          </el-col>
          <el-col :span="9" >
            <template v-if="tabs.third.input.key.type === 'string'">
              <div class="panel panel-default">
                <div class="panel-heading">{{tabs.third.input.key.key}}({{tabs.third.input.key.type}}) 数据展示
                  <el-button class="pull-right" size="small" type="text" style="padding: 0;" @click="showJson">JSON</el-button>
                </div>
                <div class="panel-body" style="min-height: 300px;max-height: 520px;overflow-y: scroll">
                    <div class="text">{{tabs.third.data}}</div>
                </div>
              </div>
            </template>
            <template v-if="tabs.third.input.key.type === 'hash'">
              <p :title="tabs.third.input.key.key" style="overflow-x: hidden;white-space: nowrap;text-overflow: ellipsis;">
                <el-button size="small" icon="el-icon-document-copy" type="text" @click="copyKey(tabs.third.input.key.key,$event)"  class="margin-right"/>
                <b>key: </b>
                <span class="text-more">{{tabs.third.input.key.key}} </span>
              </p>
              <p class="padding-big-left"><b>数据量: </b>{{tabs.third.input.key.length}}</p>
              <el-row>
                <el-col :span="18">
                  <el-input suffix-icon="el-icon-search" placeholder="搜索 hashKey " v-model="tabs.third.input.subScanParam.pattern"
                            @keyup.enter.native="scanHashKeys" size="small"/>
                </el-col>
                <el-col :span="5">
                  <el-button size="small" @click="readHashChoseData">选中数据</el-button>
                </el-col>
              </el-row>
              <el-table
                :data="subKeysData"
                border
                stripe
                style="width: 100%"
                height="300"
                size="mini"
                ref="subKeyTable"
                highlight-current-row
              >
                <el-table-column
                        type="index"
                        width="50"/>
                <el-table-column type="selection" width="55" />
                <el-table-column prop="key" label="key" />
                <el-table-column
                        fixed="right"
                        label="操作"
                        width="150"
                >
                  <template slot-scope="scope">
                    <el-button type="text" size="small" @click="readHashSubKeyData(scope.row.key)">数据</el-button>
                  </template>
                </el-table-column>
              </el-table>
              <el-button-group>
                <el-button plain size="mini" icon="el-icon-coordinate" @click="subKeysReScan">重置</el-button>
                <el-button plain size="mini" :disabled="tabs.third.subKeyResult.finish" icon="el-icon-arrow-right" @click="subKeysNextPage">下一页</el-button>
              </el-button-group>
              cursor: {{tabs.third.subKeyResult.cursor}}

              <div class="panel panel-default">
                <div class="panel-heading">{{tabs.third.input.subKey}} 数据展示
                  <el-button class="pull-right" size="small" type="text" style="padding: 0;" @click="showJson">JSON</el-button>
                </div>
                <div class="panel-body" style="min-height: 300px;max-height: 520px;overflow-y: scroll">
                  <div class="text">{{tabs.third.data}}</div>
                </div>
              </div>
            </template>
            <template v-if="tabs.third.input.key.type === 'list'">
              <p><b>key:</b> {{tabs.third.input.key.key}} <b>数据量: </b>{{tabs.third.input.key.length}}</p>
              <p>
                <el-input-number v-model="tabs.third.input.rangeParam.start" style="width: 120px" size="small" :min="0"
                                 :max="tabs.third.input.key.length"/>
                <el-input-number v-model="tabs.third.input.rangeParam.stop" style="width: 120px" size="small" :min="0"
                                 :max="tabs.third.input.key.length"/>
                <el-button plain size="small" @click="readListData">加载数据</el-button>
              </p>
              <div class="panel panel-default">
                <div class="panel-heading">数据展示
                  <el-button class="pull-right" size="small" type="text" style="padding: 0;" @click="showJson">JSON</el-button>
                </div>
                <div class="panel-body" style="min-height: 300px;max-height: 520px;overflow-y: scroll">
                  <div class="text">{{tabs.third.data}}</div>
                </div>
              </div>
            </template>
            <template v-if="tabs.third.input.key.type === 'set' || tabs.third.input.key.type === 'zset'">
              <p><b>key:</b> {{tabs.third.input.key.key}} <b>数据量: </b>{{tabs.third.input.key.length}}</p>
              <template v-if="tabs.third.input.key.type === 'set'">
                <el-row>
                  <el-col :span="14">
                    <el-input v-model="tabs.third.set.otherKeys" placeholder="另外的 key,多个用逗号分隔 " size="small" class="margin-bottom"/>
                  </el-col>
                  <el-col :span="10">
                    <el-button-group >
                      <el-button :disabled="!!!tabs.third.set.otherKeys" @click="collectionOperation('inter')" type="plain" size="small" >交集</el-button>
                      <el-button :disabled="!!!tabs.third.set.otherKeys" @click="collectionOperation('union')" type="plain" size="small">并集</el-button>
                      <el-button :disabled="!!!tabs.third.set.otherKeys" @click="collectionOperation('diff')" type="plain" size="small">差集</el-button>
                    </el-button-group>
                  </el-col>
                </el-row>
              </template>
              <el-row>
                <el-col :span="14">
                  <el-input suffix-icon="el-icon-search" placeholder="搜索 " v-model="tabs.third.input.subScanParam.pattern"
                            @keyup.enter.native="scanSetData" size="small"/>
                </el-col>
                <el-col :span="10">
                  <el-button-group>
                    <el-button plain size="mini" icon="el-icon-coordinate" @click="typeSetReScan">重置</el-button>
                    <el-button plain size="mini" :disabled="tabs.third.setDataScanResult.finish" icon="el-icon-arrow-right" @click="typeSetNextPage">下一页</el-button>
                  </el-button-group>

                </el-col>
              </el-row>
              <div class="panel panel-default">
                <div class="panel-heading">数据展示
                  <el-button class="pull-right" size="small" type="text" style="padding: 0;" @click="showJson">JSON</el-button>
                </div>
                <div class="panel-body" style="min-height: 300px;max-height: 520px;overflow-y: scroll">
                  <div class="text">{{tabs.third.data}}</div>
                </div>
              </div>
            </template>
          </el-col>
        </el-row>

      </el-tab-pane>
    </el-tabs>

    <!-- json 格式化展示 -->
    <el-drawer
      :with-header="false"
      :visible.sync="drawer.visible"
      direction="rtl"
    >
      <json-editor :json="drawer.json"/>
    </el-drawer>
  </div>
</template>

<script>
  import core from '../../api/core'
  import redis from '../../api/redis'
  import JsonEditor from '@/components/JsonEditor'
  import ListGroup from '@/components/ListGroup'
  import {parseTime,formatSizeHuman} from "../../utils";

  import clip from '@/utils/clipboard' // use clipboard directly

  export default {
    name: 'redis',
    components: { JsonEditor, ListGroup },
    data() {
      return {
        connParam: {
          connName: null,
          index: 0
        },
        connects: [],
        serializers: [],
        classloaders: [],
        dbs: 16,
        tabs: {
          activeTabName:'third',
          dashboard: {
            mode: null,
            memoryUses: []
          },
          third: {
            input: {
              search: {
                wildcardLeft: true,
                wildcardRight: true,
                timeout: -1,
                fast: true
              },
              keyScanParam: {
                pattern: null,
                limit: 20,
                cursor: 0
              },
              subScanParam:{
                pattern: '*',
                limit: 20,
                cursor: 0
              },
              serializerParam: {
                keySerializer: 'string',
                value: 'string',
                hashKey: 'string',
                hashValue: 'string',
                classloaderName: null
              },
              rangeParam:{
                min: null,
                max: null,
                offset: 0,
                count: 10,
                start: null,
                stop: null
              },
              key: {},      // 这里是 key 对象 , 非 string 对象
              subKey: null, // 这里只是用于展示标题
              subKeysParam: {
                key: null,
                all: false,
                subKeys: []
              },
            },
            set: {
              // 专用于 set 的参数和输出
              otherKeys: null
            },
            zset: {
              // 专用于 zset 的输入和输出
            },
            keyScanResult: {},
            subKeyResult: {},
            setDataScanResult: {},
            data: null,
            loading: false
          },
          topology: {
            nodes: []
          },
          clients: {
            loading: false,
            connects: [],
            chose: 0,
            currentPage: 1,
            pageSize: 20,
            clientConnects: []
          },
          slowlogs:{
            loading: false,
            querys: [],
            chose: 0,
            currentPage: 1,
            pageSize: 20,
            clientQuerys: []
          }
        },
        drawer: {
          visible: false,
          json: null
        }
      }
    },
    mounted() {
      this.reloadAllConnects();

      // 加载所有的序列化工具和类加载器工具
      core.classloaders().then(res => {
        this.classloaders = res.data
      })
      core.serializers().then(res => {
        this.serializers = res.data
      })

      // 加载本地保存的配置,如果之前有保存的话
      let config = this.$storeLocal.get('redis.config')
      if (config) {
        Object.assign(this.tabs.third.input.serializerParam, config)
      }
    },
    methods: {
      parseTime,
      copyKey(text, event){
        clip(text, event)
      },
      collectionOperation(op){
        let otherKeys = this.tabs.third.set.otherKeys.split(',');
        let keys = [...otherKeys,this.tabs.third.input.key.key].join(',');
        redis.collectionMethods(this.connParam,keys,op,this.tabs.third.input.serializerParam).then(res => {
          this.tabs.third.data = res.data;
        })
      },
      localStorageConfig() {
        this.$storeLocal.set('redis.config', this.tabs.third.input.serializerParam)
        this.$message({type:'success',message:'个人配置保存成功,下次直接使用此配置'})
      },
      dropChoseKeys(){
        let selection = this.$refs.keyScanTable.selection;
        if (selection.length === 0){
          this.$message('选中 key 来删除');
          return ;
        }
        let keys = selection.map(item => item.key).join(',');
        this.$confirm('确定删除 ' + selection.length + ' 个 key 吗,此操作不可逆?', '警告', { type: 'warning' }).then(() => {
          redis.dropKeys(this.connParam,keys).then(res => {
            this.scanKey();
          })
        }).catch(() => {})
      },
      readHashChoseData(){
        let selection = this.$refs.subKeyTable.selection;
        let subKeys = selection.map(item => item.key).join(',');
        let subKeyParam = {
          key: this.tabs.third.input.key.key,
          subKeys: subKeys
        }
        this.tabs.third.input.subKey = selection.length + '个 key ';
        redis.readData(this.connParam,subKeyParam,null,null,this.tabs.third.input.serializerParam).then(res => {
          this.tabs.third.data = res.data;
        })
      },
      readHashSubKeyData(subKey){
        let subKeyParam = {
          key: this.tabs.third.input.key.key,
          subKeys: subKey
        }
        this.tabs.third.input.subKey = subKey;
        redis.readData(this.connParam,subKeyParam,null,null,this.tabs.third.input.serializerParam).then(res => {
          this.tabs.third.data = res.data;
        })
      },
      readListData(){
        let thirdInput = this.tabs.third.input;
        redis.readData(this.connParam,thirdInput.subKeysParam,thirdInput.rangeParam,null,this.tabs.third.input.serializerParam).then(res => {
          this.tabs.third.data = res.data;
        })
      },
      scanSetData(){
        let thirdInput = this.tabs.third.input;
        redis.readData(this.connParam,thirdInput.subKeysParam,null,thirdInput.subScanParam,thirdInput.serializerParam).then(res => {
          this.tabs.third.setDataScanResult = res.data;
          this.tabs.third.data = res.data.keys;
        })
      },
      typeSetNextPage(){
        this.tabs.third.input.subScanParam.cursor = this.tabs.third.setDataScanResult.cursor;
        this.scanSetData();
      },
      typeSetReScan(){
        this.tabs.third.input.subScanParam.cursor = 0;
        this.scanSetData();
      },
      scanHashKeys(){
        let thirdInput = this.tabs.third.input;
        redis.subKeys(this.connParam,thirdInput.key.key,thirdInput.subScanParam,thirdInput.serializerParam).then(res => {
          this.tabs.third.subKeyResult = res.data;
        })
      },
      subKeysNextPage(){
        this.tabs.third.input.subScanParam.cursor = this.tabs.third.subKeyResult.cursor;
        this.scanHashKeys();
      },
      subKeysReScan(){
        this.tabs.third.input.subScanParam.cursor = 0;
        this.scanHashKeys();
      },
      // 格式化节目大小的显示
      formatSize(row, column) {
        // let value = row[column.property] / 1024
        // if (value < 1024) {
        //   return value.toFixed(2) + ' KB'
        // }
        // if (value >= 1024 && value < (1024 * 1024)) {
        //   return (value / 1024).toFixed(2) + ' MB'
        // }
        // return (value / (1024 * 1024)).toFixed(2) + ' GB'
        return formatSizeHuman(row[column.property]);
      },
      scanKey(){
        let thirdInput = this.tabs.third.input;

        let scanParam = Object.assign({},thirdInput.keyScanParam);
        // 如果需要支持模糊, 在key 两边加上 * 号
        if (scanParam.pattern){
          if (thirdInput.search.wildcardLeft ) {
            scanParam.pattern= '*' + scanParam.pattern
          }
          if (thirdInput.search.wildcardRight ) {
            scanParam.pattern = scanParam.pattern + '*'
          }
        }else{
          scanParam.pattern = '*';
        }
        scanParam.timeout = thirdInput.search.timeout;
        scanParam.fast = thirdInput.search.fast;

        this.tabs.third.loading = true;
        redis.scan(this.connParam,scanParam,thirdInput.serializerParam).then(res => {
          this.tabs.third.loading = false;
          this.tabs.third.keyScanResult = res.data;
        }).catch(res => {
          this.tabs.third.loading = false;
        })
      },
      nextPage(){
        // 取得下一个游标值
        if (this.tabs.dashboard.mode === 'cluster'){
          this.tabs.third.input.keyScanParam.cursor = this.tabs.third.keyScanResult.cursor +'|'+ this.tabs.third.keyScanResult.hostIndex;
        }else{
          this.tabs.third.input.keyScanParam.cursor = this.tabs.third.keyScanResult.cursor;
        }

        this.scanKey();
      },
      reScan(){
        if (this.tabs.dashboard.mode === 'cluster'){
          this.tabs.third.input.keyScanParam.cursor = '0|0';
        }else{
          this.tabs.third.input.keyScanParam.cursor = 0;
        }
        this.scanKey();
      },
      dropKeys(keys){
        if (keys && typeof keys === 'string') {
          this.$confirm('确定删除 ' + keys + ' 此操作不可逆?', '警告', { type: 'warning' }).then(() => {
            redis.dropKeys(this.connParam, keys).then(res => {
              this.scanKey()
            })
          }).catch(() => {
          })
        } else if (keys) {
          this.$confirm('再次确认,将删除' + keys.length + '个 key 此操作不可逆?', '警告', { type: 'warning' }).then(() => {
            redis.dropKeys(this.connParam, keys).then(res => {
              this.scanKey()
            })
          }).catch(() => {
          })
        }
      },
      readData(row){
        let thirdInput = this.tabs.third.input;
        thirdInput.key = row;
        thirdInput.subKeysParam.key = row.key;
        if (row.type === 'string'){
          // string 类型,直接加载数据
          redis.readData(this.connParam,{key:row.key},null,null,thirdInput.serializerParam).then(res => {
            this.tabs.third.data = res.data;
          })
        }else if (row.type === 'hash'){
          thirdInput.subScanParam.cursor = 0;
          thirdInput.subScanParam.pattern = '*';
          thirdInput.subScanParam.limit = 20;
          // hash 类型, 调用 hash scan
          this.scanHashKeys();
        }else if (row.type === 'list'){
          thirdInput.rangeParam.min = null;
          thirdInput.rangeParam.max = null;

          thirdInput.rangeParam.stop = 10;
          if (thirdInput.rangeParam.stop > thirdInput.key.length){
            thirdInput.rangeParam.stop= thirdInput.key.length;
          }
          this.readListData();
        }else if (row.type === 'set' || row.type === 'zset'){
          thirdInput.subScanParam.cursor = 0;
          thirdInput.subScanParam.pattern = '*';
          thirdInput.subScanParam.limit = 20;
          this.scanSetData();
        }

      },
      reloadAllConnects(){
        core.moduleConnects('redis').then(res => {
          this.connects = res.data
          if (this.connects && this.connects.length > 0) {
            this.loadConnect(this.connects[0])
          }
        })
      },
      loadConnect(connect){
        this.connParam.connName = connect;
        // 加载当前运行模式
        redis.mode(this.connParam).then(res => {
          this.tabs.dashboard.mode = res.data;

          // 如果当前是集群模式,则需要设置搜索游标为 cursor|hostIndex 模式
          if (res.data === 'cluster'){
            this.tabs.third.input.keyScanParam.cursor = '0|0';
          }

          this.scanKey();
        });

        // 加载当前数据库数量 , 调这个接口, 后端会 client.getBulkReply 出错
        // redis.dbs(this.connParam).then(res => {
        //   this.dbs = res.data;
        // });

        // 加载拓扑结构,这个加载好像会导致命令冲突,延后执行
        setTimeout(()=> {
          this.loadNodes();
        },500)
      },
      loadNodes(){
        redis.nodes(this.connParam).then(res => {
          this.tabs.topology.nodes = res.data;
        })
      },
      loadMemoryUse(){
        redis.memoryUses(this.connParam).then(res => {
          this.tabs.dashboard.memoryUses = res.data;
        })
      },
      showJson(){
        try{
          if (typeof(this.tabs.third.data) === 'object'){
            this.drawer.json = this.tabs.third.data
          }else{
            this.drawer.json = JSON.parse(this.tabs.third.data);
          }

          this.drawer.visible = true;
        }catch (e) {
          this.$message(e.toString());
          console.log(this.tabs.third.data)
        }

      },
      switchTab(tab, event){
        switch (tab.name) {
          case 'dashboard':
            // 加载内存占用信息
            this.loadMemoryUse();
            break;
          case 'clients':
            this.tabs.clients.loading = true;
            redis.clientList(this.connParam).then(res => {
              this.tabs.clients.connects = res.data;
              this.tabs.clients.loading = false;

              this.brokerClients(0);
            })
            break;
          case 'slowlogs':
            this.tabs.slowlogs.loading = true;
            redis.slowlogs(this.connParam).then(res => {
              this.tabs.slowlogs.loading = false;
              this.tabs.slowlogs.querys = res.data;

              this.choseClientQuery(0);
            })
            break;
        }
      },
      handleSizeChange(val) {
        this.tabs.clients.pageSize = val
      },
      handleSlowlogSizeChange(val){
        this.tabs.slowlogs.pageSize = val;
      },
      handleSlowlogCurrentChange(val){
        this.tabs.slowlogs.currentPage = val;
      },
      handleCurrentChange(val) {
        this.tabs.clients.currentPage = val
      },
      killClient(id){
        redis.killClient(this.connParam,id).then(res => {
          console.log('kill '+id)
        })
      },
      killQuery(id){

      },
      choseClientQuery(index){
        this.tabs.slowlogs.chose = index;
        let querys = this.tabs.slowlogs.querys[index].slowlogs;
        this.tabs.slowlogs.clientQuerys = querys;
      },
      brokerClients(index){
        this.tabs.clients.chose = index;
        // 统计每一个客户端占用连接
        let clients = this.tabs.clients.connects[index].clients;
        let hostConnects = {};
        for (let i = 0; i < clients.length; i++) {
          let host = clients[i].connect.host;
          hostConnects[host] = hostConnects[host] || 0 ;
          hostConnects[host]++;
        }
        let results = []
        for (let host in hostConnects) {
          results.push({ host: host, connections: hostConnects[host] })
        }
        this.tabs.clients.clientConnects = results;
      }
    },
    computed: {
      subKeysData(){
        let keys = this.tabs.third.subKeyResult.keys;
        if(keys){
          return keys.map(key => ({key: key}))
        }
        return [];
      },
      dbsComputed(){
        let array = [];
        for (let i = 0; i < this.dbs; i++) {
          array.push(i);
        }
        return array;
      },
      nodesHandle(){
        if (this.tabs.topology.nodes && this.tabs.topology.nodes.length > 0) {
          this.tabs.topology.nodes.forEach(node => node.connectString = node.hostAndPort.host + ':' + node.hostAndPort.port)
        }
        return this.tabs.topology.nodes;
      },
      choseConnects(){
        let clientsTab = this.tabs.clients;
        if (clientsTab.connects && clientsTab.connects.length > 0){
          let clients = clientsTab.connects[clientsTab.chose].clients;
          let part =  clients.slice((clientsTab.currentPage - 1) * clientsTab.pageSize,clientsTab.currentPage * clientsTab.pageSize)
          return part;
        }
        return [];
      },
      currentClients(){
        if (this.tabs.clients.connects[this.tabs.clients.chose]){
          return this.tabs.clients.connects[this.tabs.clients.chose].clients.length
        }

        return 0 ;
      },
      totalClients(){
        let total = this.tabs.clients.connects.map(item => item.clients.length).reduce((sum,item,index) => sum += item,0)
        return total;
      }
    }
  }
</script>

<style scoped>

</style>
