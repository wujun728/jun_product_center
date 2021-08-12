<template>
    <div class="app-container">
      <el-row>
        <el-col>
          <el-button class="margin-right" type="text" icon="el-icon-refresh" @click="refreshConnects"/>

          <el-select v-model="input.connect" @change="choseConnect" size="small">
            <el-option v-for="connect in connects" :key="connect" :value="connect" :label="connect" />
          </el-select>

          <el-select class="margin-left" v-model="input.databaseName" @change="choseDatabase" size="small">
            <el-option v-for="db in databaseNames" :key="db" :value="db" :label="db" />
          </el-select>

          <el-select  class="margin-left" v-model="input.collectionName" @change="choseCollection" size="small">
            <el-option v-for="coll in collectionNames" :key="coll._hashCode" :value="coll.collectionName" :label="coll.collectionName" />
          </el-select>
        </el-col>
      </el-row>
      <el-row>
        <!-- 分页 -->
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="input.page.pageNo"
          :page-size="input.page.pageSize"
          layout="total, sizes, prev, pager, next"
          :total="result.total">
        </el-pagination>
          <el-table
            :data="dataHandle"
            border
            stripe
            style="width: 100%"
            fit
            size="mini">
            <el-table-column type="index" width="55" />
            <el-table-column
              prop="_id"
              label="_id" width="80">
            </el-table-column>
            <el-table-column
              prop="_class"
              label="_class" width="120">
            </el-table-column>
            <el-table-column
              prop="values"
              label="values">
            </el-table-column>

            <el-table-column
              fixed="right"
              label="操作"
              width="150"
            >
              <template slot-scope="scope">
                <el-button type="text" class="" size="small" @click="showJSON(scope.row)">JSON</el-button>
              </template>
            </el-table-column>
          </el-table>
      </el-row>

      <el-drawer
        title="JSON 数据展示"
        :visible.sync="drawer.show"
        :with-header="false"
        size="30%">
        <json-editor :json="drawer.json" />
      </el-drawer>
    </div>
</template>

<script>
  import core from '../../api/core'
  import mongo from '../../api/mongo'

  import ListGroup from '../../components/ListGroup'
  import JsonEditor from '../../components/JsonEditor'

  export default {
    name: 'mongo',
    components: { ListGroup ,JsonEditor},
    data(){
      return {
        input: {
          connect: null,
          databaseName: null,
          collectionName: null,
          page: {
            pageNo: 1,
            pageSize: 10
          }
        },
        connects: [],
        databaseNames: [],
        collectionNames: [],
        result: {},
        drawer: {
          show: false,
          json: null
        }
      }
    },
    methods: {
      showJSON(row){
        this.drawer.json = JSON.parse(row.values);
        this.drawer.show = true;
      },
      choseConnect(connect){
        this.input.connect = connect;
        this.loadDatabases();
      },
      choseDatabase(databaseName){
        this.input.databaseName = databaseName;
        this.loadCollectionNames();
      },
      choseCollection(collectionName){
        this.input.collectionName = collectionName;
        this.search()
      },
      refreshConnects(){
        core.moduleConnects('mongo').then(res => {
          this.connects = res.data;
          if (res.data && res.data.length > 0 ){
            this.input.connect = res.data[0];
            this.loadDatabases();
          }
        })
      },
      loadDatabases(){
        mongo.databaseNames(this.input.connect).then(res => {
          this.databaseNames = res.data;
          if (res.data && res.data.length > 0){
            this.input.databaseName = res.data[0];
            this.loadCollectionNames();
          }
        })
      },
      loadCollectionNames(){
        mongo.collectionNames(this.input.connect,this.input.databaseName).then(res => {
          this.collectionNames = res.data;
          if (res.data && res.data.length > 0){
            this.input.collectionName = res.data[0].collectionName;

            this.search();
          }
        })
      },
      search(){
        mongo.queryPage(this.input.connect,this.input.databaseName,this.input.collectionName,this.input.page).then(res => {
          this.result = res.data;
        })
      },
      handleCurrentChange(val){
        this.input.page.pageNo = val;
        this.search();
      },
      handleSizeChange(val){
        this.input.page.pageSize = val;
        this.search();
      }
    },
    mounted() {
      this.refreshConnects();
    },
    computed: {
      dataHandle(){
        if (this.result.obj){
          let arr = [];
          for (let i = 0; i < this.result.obj.length; i++) {
            let item = JSON.parse(this.result.obj[i]);
            let map = {_id: item._id,_class:item._class};
            delete item._id;
            delete item._class;
            map.values = JSON.stringify(item);
            arr.push(map);
          }
          return arr;
        }
        return [];
      }
    }
  }
</script>

<style scoped>

</style>
