<template>
    <div class="app-container">
      <el-row>
        <el-button class="margin-right" type="text" icon="el-icon-refresh" @click="refreshConnects"/>

        <el-select v-model="input.connect" @change="choseConnect" size="small">
          <el-option v-for="connect in connects" :key="connect" :value="connect" :label="connect" />
        </el-select>

        <span class="margin-left" :style="'color:'+clusterModel.clusterHealth.status" >{{clusterModel.clusterHealth.cluster_name}}</span>
      </el-row>

      <el-tabs :active-name="tabs.activeTabName" @tab-click="switchTab">
        <el-tab-pane name="dashboard" label="监控总览(实时)">
          <el-row class="margin-top">
            <span class="margin-right">节点数量: <b>{{clusterModel.clusterHealth.number_of_nodes}}</b></span>
            <span class="margin-right">数据节点数量: <b>{{clusterModel.clusterHealth.number_of_data_nodes}}</b></span>
            <span class="margin-right">分片数: <b>{{clusterModel.clusterHealth.active_shards}}</b></span>
            <span class="margin-right">未分配分片数: <b>{{clusterModel.clusterHealth.unassigned_shards}}</b></span>
            <span class="margin-right">主分片数: <b>{{clusterModel.clusterHealth.active_primary_shards}}</b></span>
            <!--        <span class="margin-right">可用分片: <b>{{clusterModel.clusterHealth.active_shards_percent_as_number}}%</b></span>-->
          </el-row>
          <!-- 节点信息展示 -->
          <div class="panel panel-default margin-top">
            <div class="panel-heading">节点信息</div>
            <div class="panel-body">
              <el-row  >
                <el-col :span="14">
                  <el-table
                    :data="nodes"
                    border
                    stripe
                    highlight-current-row
                    size="mini">

                    <el-table-column
                      type="index"
                      width="50"
                    />
                    <el-table-column
                      prop="id"
                      label="id"
                      width="200"
                    />
                    <el-table-column
                      prop="transport_address"
                      label="transport"
                      width="140"
                      sortable
                    />
                    <el-table-column
                      label="roles"
                      width="140"
                    >
                      <template slot-scope="scope" >
                        <span>{{scope.row.roles.join(',')}}</span>
                      </template>
                    </el-table-column>

                    <el-table-column
                      prop="version"
                      label="version"
                      width="80"
                    />

                    <el-table-column
                      label="磁盘"
                      width="80"
                    >
                      <template slot-scope="scope" v-if="clusterModel.nodeStats.nodes && clusterModel.nodeStats.nodes[scope.row.id]">
                        <span>{{calcStorageUsePercent(scope.row)}}%</span>
                      </template>
                    </el-table-column>

                    <el-table-column
                      label="内存"
                      width="80"
                    >
                      <template slot-scope="scope" v-if="clusterModel.nodeStats.nodes && clusterModel.nodeStats.nodes[scope.row.id]">
                        <span>{{calcJvmMemUsePercent(scope.row)}}%</span>
                      </template>
                    </el-table-column>

                    <el-table-column
                      label="http"
                      width="80"
                    >
                      <template slot-scope="scope" v-if="clusterModel.nodeStats.nodes && clusterModel.nodeStats.nodes[scope.row.id]">
                        <span>{{calcHttpOpen(scope.row)}}</span>
                      </template>
                    </el-table-column>

                    <el-table-column
                      fixed="right"
                      label="操作"
                      width="80"
                    >
                      <template slot-scope="scope">
                        <el-button type="text" class="" size="small" @click="closeNode(scope.row)" >关停</el-button>
                      </template>
                    </el-table-column>
                  </el-table>
                </el-col>
              </el-row>
            </div>
          </div>

        </el-tab-pane>
        <el-tab-pane name="index" label="索引查询">
          <el-row>
            <el-col :span="9">
              <el-table
                :data="indices"
                border
                stripe
                style="width: 100%"
                highlight-current-row
                fit
                height="500"
                size="mini">
                <el-table-column
                  type="index"
                  width="50"
                />
                <el-table-column
                  prop="indexName"
                  label="indexName"
                  width="240"
                  sortable
                />
                <el-table-column
                  label="docs"
                  width="100"
                  sortable
                  sort-by="docs.count"
                >
                  <template slot-scope="scope">
                    <span>{{scope.row.docs.count}}({{scope.row.docs.deleted}})</span>
                  </template>
                </el-table-column>
                <el-table-column
                  prop="store"
                  label="store"
                  width="80"
                  :formatter="formatSize"
                  sortable
                />
                <el-table-column
                  fixed="right"
                  label="操作"
                  width="100"
                >
                  <template slot-scope="scope">
                    <el-button type="text" class="" size="small"  @click="searchIndexDataEvent(scope.row)">数据</el-button>
                  </template>
                </el-table-column>
              </el-table>
            </el-col>
            <el-col :span="14" class="margin-left">
              <template v-if="tabs.index.settings.index">
                <el-row>
                  <el-col :span="14">
                    <p >搜索耗时: {{tabs.index.result.took / 1000000}} ms</p>
                    <p >索引信息: {{tabs.index.input.indexName}}</p>
                    <p >创建时间: {{parseTime(tabs.index.settings.index.creation_date)}}</p>
                    <p >分片数: {{tabs.index.settings.index.number_of_shards}} 副本数: {{tabs.index.settings.index.number_of_replicas}}</p>

                    <el-table
                      :data="tabs.index.shardAlloc"
                      border
                      stripe
                      style="width: 100%"
                      highlight-current-row
                      fit
                      size="mini">
                      <el-table-column
                        type="index"
                        width="50"
                      />
                      <el-table-column
                        label="分片主机"
                        prop="host"
                        sortable
                      />

                      <el-table-column
                        label="分片信息"
                      >
                        <template slot-scope="scope">
                          <el-button type="text" v-for="shard in scope.row.shards">
                            <span v-if="shard.primary"> <b>{{shard.shard}}</b> </span>
                            <span v-else> {{shard.shard}}</span>
                          </el-button>
                        </template>

                      </el-table-column>

                    </el-table>
                  </el-col>
                  <el-col :span="10">
                    <!-- :expandedOnStart="true"  -->
                    <vue-json-editor  v-model="tabs.index.mappings" :show-btns="false" lang="zh" />
                  </el-col>
                </el-row>

              </template>
            </el-col>
          </el-row>

          <!-- 数据信息展示   -->
          <el-row v-show="tabs.index.result.hits.hits && tabs.index.result.hits.hits.length > 0">
            <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="tabs.index.input.dsl.from"
              :page-size="tabs.index.input.dsl.size"
              layout="total, sizes, prev, pager, next"
              :total="tabs.index.result.hits.total">
            </el-pagination>

            <el-table
              :data="tabs.index.result.hits.hits"
              border
              stripe
              style="width: 100%"
              highlight-current-row
              fit
              @row-click="showRowDialog"
              @sort-change="changeSort"
              size="mini">
              <el-table-column
                type="index"
                width="50"
              />
              <el-table-column
                prop="_id"
                label="_id"
                :show-overflow-tooltip="true"
              />
              <el-table-column
                prop="_score"
                label="_score"
                :show-overflow-tooltip="true"
              />
              <el-table-column
                prop="_type"
                label="_type"
                :show-overflow-tooltip="true"
              />
              <template v-if="tabs.index.result.hits.hits && tabs.index.result.hits.hits.length > 0">
                <el-table-column
                  v-for="(value,key,index) in tabs.index.result.hits.hits[0]._source"
                  :prop="'_source.'+key"
                  :label="key"
                  sortable
                  :show-overflow-tooltip="true"
                />
              </template>

            </el-table>
          </el-row>
        </el-tab-pane>
        <el-tab-pane name="query" label="DSL 快速查询">
          <el-row>
            <el-col :span="6">
              <el-button-group class="margin-bottom ">
                <el-button type="primary" size="small" @click="dslSearch">搜索</el-button>
              </el-button-group>

              <vue-json-editor mode="code" :expandedOnStart="true" v-model="tabs.query.input.dsl" :show-btns="false" lang="zh" />

            </el-col>
            <el-col :span="18">
              <el-table
                :data="tabs.query.result.hits.hits"
                border
                stripe
                style="width: 100%"
                highlight-current-row
                fit
                @row-click="showRowDialog"
                @sort-change="changeSort"
                size="mini">
                <el-table-column
                  type="index"
                  width="50"
                />
                <el-table-column
                  prop="_id"
                  label="_id"
                  :show-overflow-tooltip="true"
                />
                <el-table-column
                  prop="_score"
                  label="_score"
                  :show-overflow-tooltip="true"
                />
                <el-table-column
                  prop="_type"
                  label="_type"
                  :show-overflow-tooltip="true"
                />
                <template v-if="tabs.query.result.hits.hits && tabs.query.result.hits.hits.length > 0">
                  <el-table-column
                    v-for="(value,key,index) in tabs.query.result.hits.hits[0]._source"
                    :prop="'_source.'+key"
                    :label="key"
                    sortable
                    :show-overflow-tooltip="true"
                  />
                </template>

              </el-table>
            </el-col>
          </el-row>

        </el-tab-pane>
      </el-tabs>

      <el-dialog  :visible.sync="indexDataDialog.show"  title="数据展示">
        <vue-json-editor  :expandedOnStart="true" v-model="indexDataDialog.json" :show-btns="false" lang="zh" />
      </el-dialog>
    </div>
</template>

<script>
  import core from '../../api/core'
  import elasticsearch from '../../api/elasticsearch'
  import vueJsonEditor from 'vue-json-editor'
  import {parseTime,formatSizeHuman} from '@/utils'

  const defaultDsl = {"query":{"bool":{"must":[],"must_not":[],"should":[{"match_all":{}}]}},"from":0,"size":50,"sort":[{"_score":{"order":"desc"}}],"aggs":{},"version":true};

  const searchDsl = {"query":{"match_all":{}}};

  export default {
    name: 'elasticsearch',
    components:{vueJsonEditor},
    data(){
      return {
        input: {
          connect: null
        },
        connects: [],
        clusterModel: {
          clusterHealth: {},
          clusterState: {},
          clusterNodes: {},
          nodeStats: {},
          status: {}
        },
        tabs:{
          activeTabName: 'index',
          index: {
            input: {
              dsl: defaultDsl,
              indexName: null
            },
            shardAlloc: [],
            settings:{},
            mappings: {},
            result: {
              _shards: {},
              hits:{hits:[]},
              took: null,
              timed_out: null
            }
          },
          query: {
            input:{
              dsl: searchDsl
            },
            result: {
              hits: {hits: []}
            }
          }
        },
        indexDataDialog:{
          show : false,
          json: {}
        }
      }
    },
    methods:{
      parseTime,
      closeNode(){

      },
      dslSearch(){
        elasticsearch.dslSearch(this.input.connect,this.tabs.index.input.dsl).then(res => {
          this.tabs.query.result = res.data;
        })
      },
      handleSizeChange(val){
        this.tabs.index.input.dsl.size = val;
        this.searchIndexData();
      },
      handleCurrentChange(val){
        this.tabs.index.input.dsl.from = val;
        this.searchIndexData();
      },
      changeSort({column, prop, order}){
        let newSort = {};
        let columnName = column.property.split('.')[1];
        if (order === 'ascending'){
          newSort[columnName] = {order: 'asc'};
        }else{
          newSort[columnName] = {order: 'desc'};
        }

        let defaultSort = [{"_score":{"order":"desc"}}];
        defaultSort.push(newSort);
        this.tabs.index.input.dsl.sort = defaultSort;
        this.searchIndexData();
      },
      showRowDialog(row,column,event){
        this.indexDataDialog.show = true;
        this.indexDataDialog.json = row;
      },
      formatSizeHuman,
      refreshConnects(){
        core.moduleConnects('elasticsearch').then(res => {
          this.connects = res.data;
          if (res.data && res.data.length > 0){
            this.input.connect = res.data[0];
            this.choseConnect(res.data[0])
          }
        })
      },
      choseConnect(value){
        // 加载集群健康查询
        elasticsearch.clusterHealth(value).then(res => {
          // console.log(res.data,'clusterHealth')
          this.clusterModel.clusterHealth = res.data;
        });

        elasticsearch.clusterNodes(value).then(res => {
          // console.log(res.data,'clusterNodes')
          this.clusterModel.clusterNodes= res.data;
        })

        elasticsearch.nodeStats(value).then(res => {
          // console.log(res.data,'nodeStats')
          this.clusterModel.nodeStats = res.data;
        })

        // 查询所有的索引信息
        elasticsearch.clusterState(value).then(res => {
          // console.log(res.data,'clusterState')
          this.clusterModel.clusterState = res.data;
        })


        elasticsearch.status(value).then(res => {
          // console.log(res.data,'status')
          this.clusterModel.status = res.data;
        })
      },
      switchTab(tab, event){
        switch (tab.name) {
          case 'dashboard':
            break;
          case 'index':

            break;
        }
      },
      formatSize(row,column,cellValue){
        return formatSizeHuman(cellValue);
      },
      searchIndexDataEvent(row){
        this.tabs.index.input.indexName = row.indexName;

        // 查找当前 index 的分片分配情况
        let alloc = this.clusterModel.clusterState.routing_table.indices[row.indexName].shards;
        let hostMap = {};
        for(let i in alloc){
          let el = alloc[i];
          for (let j = 0; j < el.length; j++) {
            let hostNode = this.clusterModel.clusterNodes.nodes[el[j].node];
            hostMap[hostNode.host] = hostMap[hostNode.host] || [];
            hostMap[hostNode.host].push(el[j])
          }
        }

        let arr = [];
        for (let host in hostMap){
          arr.push({host:host,shards:hostMap[host]});
        }
        this.tabs.index.shardAlloc = arr;

        // 获取当前索引的属性信息
        let metadata = this.clusterModel.clusterState.metadata;
        this.tabs.index.settings = metadata.indices[row.indexName].settings;

        this.tabs.index.mappings = metadata.indices[row.indexName].mappings;

        this.searchIndexData();
      },
      searchIndexData(){
        elasticsearch.search(this.input.connect,this.tabs.index.input.indexName,this.tabs.index.input.dsl).then(res => {
          this.tabs.index.result = res.data;
        })
      },
      calcStorageUsePercent(row){
        let fs = this.clusterModel.nodeStats.nodes[row.id].fs.data[0];
        let percent = (fs.total_in_bytes - fs.available_in_bytes) / fs.total_in_bytes;
        return percent.toFixed(2);
      },
      calcJvmMemUsePercent(row){
        let mem = this.clusterModel.nodeStats.nodes[row.id].jvm.mem;
        return mem.heap_used_percent;
      },
      calcHttpOpen(row){
        let http = this.clusterModel.nodeStats.nodes[row.id].http;
        return http.current_open;
      },
    },
    mounted() {
      this.refreshConnects();
    },
    computed: {
      indices(){
        let indices = this.clusterModel.status.indices;
        if (indices){
          let afterIndices = [];
          for(let indexName in indices){
            let primaries = indices[indexName].primaries;
            // console.log(primaries)
            afterIndices.push({indexName:indexName,docs:primaries.docs,store: primaries.store.size_in_bytes})
          }
          return afterIndices;
        }
        return [];
      },
      nodes(){
        if (this.clusterModel.clusterNodes){
          let nodeOrigins = this.clusterModel.clusterNodes.nodes;
          let nodes  = [];
          for (let nodeId in nodeOrigins) {
            nodes.push(Object.assign({id:nodeId},nodeOrigins[nodeId]));
          }
          return nodes;
        }
        return [];
      },
    }
  }
</script>

<style scoped>
  @import "../../assets/bootstrap.css";
</style>
