<template>
  <div class="app-container">
    <el-row>
      <el-col>
        <el-select v-model="input.connName" filterable clearable placeholder="选择连接" @change="switchConnection" size="small">
          <el-option
            v-for="connection in connections"
            :key="connection"
            :label="connection"
            :value="connection">
          </el-option>
        </el-select>

        <span class="append-button-group">
          <el-button type="text" icon="el-icon-refresh" class="text-danger" @click="refreshConnect"/>
        </span>

        <el-select class="margin-left" v-model="input.catalog" filterable clearable placeholder="选择库" @change="switchCatalog" size="small">
          <el-option v-for="catalogMeta in meta.catalogs"
            :key="catalogMeta.catalog" :label="catalogMeta.catalog" :value="catalogMeta.catalog"
          />
        </el-select>
        <span class="append-button-group">
          <el-button type="text" icon="el-icon-refresh" class="text-danger" @click="refreshCatalog"/>
        </span>

      </el-col>
    </el-row>
    <el-row v-if="meta.schemas && meta.schemas.length > 0">
      <el-col :span="24">
        <div class=" padding-top text-forestgreen">当前 schema 数量: {{meta.schemas.length}} </div>
        <el-checkbox-group v-model="input.selectSchemas"  @change="searchTables">
          <el-checkbox-button size="small" v-for="schema in meta.schemas" :value="schema" :label="schema" :key="schema"/>
        </el-checkbox-group>
      </el-col>
    </el-row>
    <el-row>
      <el-input v-model="input.keyword" placeholder="输入表名/字段名/表注释/列注释/table:表名/column:列名/tag:表标记" suffix-icon="el-icon-search" @keyup.enter.native="searchTables" @keyup.native="keyupSearch" />
      <div class="panel panel-default">
        <div class="panel-heading "><span class="text-forestgreen">当前表数量: {{meta.tables.length}} ,当前选中表 {{table.actualTableName ? table.actualTableName.fullName : '未选定'}}</span></div>
        <div class="panel-body">
          <div class="table-items">
            <ul class="list-group list-inline">
              <li :index="index " v-contextmenu:contextmenu
                  :class="'list-group-item list-inline-item ' + (currentTable.actualTableName.fullName === table.actualTableName.fullName ? ' active' : '') "
                  v-for="(currentTable,index) in meta.tables" :key="currentTable._hashCode">
                <span  class="cursor-point"  @click="choseTable(currentTable)" ><i class="el-icon-chicken"></i>
                  <template v-if="currentTable.tableMark &&  currentTable.tableMark.deprecated">
                    <s>{{showTableName(currentTable)}}</s>
                  </template>
                  <template v-else>
                    {{showTableName(currentTable)}}
                  </template>
                </span>
                <del class="padding-small-right cursor-point"><i class="el-icon-refresh" @click="refreshTable(currentTable,index)"></i></del>
                <el-button class="padding-left padding-right  " type="text" :disabled="dataCount.loading" @click="loadTableDataTotal(currentTable,index)" >
                  {{dataCount[index+''] ? dataCount[index+''] : -1 }}
                </el-button>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </el-row>

    <el-row>
      <el-col :span="15">
<!--        <div>-->
<!--          <el-button type="text" :loading="view.dataTotalLoading" @click="loadTableDataTotal">数据量:{{view.dataTotal ? view.dataTotal.rows[0].count : '点击获取'}}</el-button>-->
<!--        </div>-->
        <el-table
          v-loading="view.loading"
          :data="table.columns"
          border
          stripe
          style="width: 100%"
          size="mini">
          <el-table-column
            type="index"
            width="50"/>
          <el-table-column
            prop="columnName"
            label="列名"
            width="200"/>
          <el-table-column
            label="必填"
            width="50">
            <template slot-scope="scope">
              <span >{{ scope.row.nullable ? '':'必填' }}</span>
            </template>
          </el-table-column>
          <el-table-column
            label="类型"
            width="170">
            <template slot-scope="scope">
              <span >{{ showTableColumnType(scope.row) }}</span>
            </template>
          </el-table-column>
          <el-table-column
            prop="remark"
            label="注释"/>
        </el-table>

      </el-col>
      <el-col :span="9">
        <template v-if="table && table.indexs">
          <el-table
            :data="table.indexs"
            border
            stripe
            style="width: 100%"
            size="mini">
            <el-table-column
              type="index"
              width="50"/>
            <el-table-column
              prop="indexName"
              label="索引名称"
              width="200"/>
            <el-table-column
              prop="columnName"
              label="列名"
              width="100"/>
            <el-table-column
              label="索引类型">
              <template slot-scope="scope">
                <span >{{ showIndexType(scope.row) }}</span>
              </template>
            </el-table-column>
            <el-table-column
              prop="ordinalPosition"
              label="顺序"/>
            <el-table-column
              label="unique">
              <template slot-scope="scope">
                <span >{{ scope.row.unique }}</span>
              </template>
            </el-table-column>
          </el-table>
        </template>

        <el-button-group class="margin-top" >
          <el-button plain size="mini" :class="state.echart === 'hierarchy' ? 'text-danger':'' " @click="hierarchy"><i class="fa fa-sitemap" aria-hidden="true"></i> Hierarchy</el-button>
          <el-button plain size="mini" :class="state.echart === 'hierarchy' ? '':'text-danger' " @click="superTypes" > superTypes</el-button>
          <el-button plain size="mini" icon="el-icon-plus" />
        </el-button-group>
        <el-row>
          <el-tree :data="relations" ref="relation" :default-expand-all="true" />
        </el-row>
      </el-col>
    </el-row>

    <!-- 单表上的右键菜单 -->
    <v-contextmenu ref="contextmenu" @contextmenu="showContextMenu" >
      <v-contextmenu-item @click="tableMarkTag"><i class="fa fa-file-text"></i> 表标记</v-contextmenu-item>
      <v-contextmenu-item @click="emptyTable"><i class="fa fa-columns"></i> 清空表</v-contextmenu-item>
      <v-contextmenu-item divider></v-contextmenu-item>
      <v-contextmenu-item><i class="fa fa-taxi"></i> 表属性</v-contextmenu-item>
    </v-contextmenu>

    <el-dialog :visible.sync="tableMarkDialog.visible" :title="tableMarkDialog.title">
      <el-row>
        <el-col :span="24">
          <el-transfer v-model="tableMarkDialog.tableTags"
                       :data="tagsComputed"
                       :titles="['标签库','选中的标签']"/>
        </el-col>
      </el-row>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="storeTableTags">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import database from '../../api/database'
import contextmenu from 'v-contextmenu'
import 'v-contextmenu/dist/index.css'
import Vue from 'vue'

import ListGroup from '../../components/ListGroup'

Vue.use(contextmenu)

export default {
  name: 'metadata',
  components: {ListGroup},
  data() {
    return {
      connections: [],
      meta:{
        catalogs: [],
        tables: [],
        columns: [],
        schemas: []
      },
      input:{
        connName: null,
        catalog: null,
        keyword: null,
        selectSchemas: []
      },
      table: {},
      state: {
        echart: 'hierarchy'
      },
      view: {
        loading: false,
        dataTotalLoading: false
      },
      tableMarkDialog: {
        visible: false,
        title: '表标记',
        tags: [],
        tableTags: []
      },
      dataCount: {
        loading: false
      },
      relations: []
    }
  },
  mounted(){
    // 加载所有连接
    database.connections().then(res => {
      this.connections = res.data;
      if (this.connections && this.connections.length > 0){
        this.input.connName = this.connections[0];
        this.switchConnection(this.connections[0]);
      }
    });

    // 加载可以选择的表标记
    database.tags().then(res => {
      this.tableMarkDialog.tags = res.data;
    })
  },
  computed: {
    tagsComputed(){
      if (this.tableMarkDialog.tags){
        return this.tableMarkDialog.tags.map(item => ({key:item,label:item}));
      }
      return []
    }
  },
  methods:{
    hierarchy(){
      this.state.echart = 'hierarchy';
      database.hierarchy(this.input.connName,this.table.actualTableName).then(res => {
        if (res.data ){
          this.relations = [res.data]
        }
      })
    },
    superTypes(){
      this.state.echart = 'superTypes';
      database.superTypes(this.input.connName,this.table.actualTableName).then(res => {
        if (res.data ){
          this.relations = [res.data]
        }
      })
    },
    loadTableDataTotal(table,index){
      this.dataCount.loading = true;
      let schema = table.actualTableName.schema
      let catalog = table.actualTableName.catalog
      let realTableName = table.actualTableName.tableName;
      if (schema){
        realTableName = schema+'.'+realTableName;
      }
      if (catalog){
        realTableName = catalog + '.' + realTableName;
      }
      database.executeQuery(this.input.connName,'select count(*) from '+realTableName).then(res => {
        this.dataCount.loading = false;
        this.dataCount[index+''] = 0;
        if (res.data && res.data.length > 0){
          // this.view.dataTotal = res.data[0];
          let data0 = res.data[0];
          let col = data0.headers[0].columnName;
          this.dataCount[index+''] = data0.rows[0][col] + '';
          this.$forceUpdate(this.dataCount)
        }
      })
    },
    // 显示右键菜单的前置操作
    showContextMenu(vnode){
      let tableIndex = vnode.elm.attributes['index'].value;
      this.choseTable(this.meta.tables[tableIndex]);
    },
    tableMarkTag(vnode,event){
      this.tableMarkDialog.visible = true;
      // 获取当前数据表的标记列表
      let actualTableName = this.table.actualTableName;
      database.tableTags(this.input.connName,actualTableName.catalog,actualTableName.schema,actualTableName.tableName).then(res => {
        this.tableMarkDialog.tableTags = res.data;
      })
    },
    emptyTable(vnode,event){
      let actualTableName = this.table.actualTableName;
      this.$confirm('确定清空数据表 '+actualTableName.tableName+' 此操作不可逆?','警告',{type: 'warning'}).then(() => {
        database.emptyTable(this.input.connName,actualTableName).then(res => {
          this.$message('清除成功')
        })
      }).catch(()=>{})
    },
    handleTagSelection(selected){
      this.tableMarkDialog.tableTags = selected.map(item => item.tag);
    },
    storeTableTags(){
      let actualTableName = this.table.actualTableName;
      let params = {connName:this.input.connName,actualTableName:actualTableName,tags:this.tableMarkDialog.tableTags};
      database.markTag([params]).then(res => {
        this.tableMarkDialog.visible = false;
      })
    },
    keyupSearch(){
      if (this.input.keyword && this.input.keyword.length > 10){
        this.searchTables();
      }
    },
    searchTables(){
      this.meta.tables.length = 0;
      this.table = {};
      this.dataCount = {loading: false}

      if (this.input.keyword){
        this.input.keyword = this.input.keyword.trim();
      }

      database.searchTables(this.input.connName,this.input.catalog,this.input.selectSchemas,this.input.keyword).then(res => {
        this.meta.tables = res.data;
        if (this.meta.tables.length > 0){
          this.table = this.meta.tables[0];
        }
      })
    },
    showTableColumnType(row){
      let columnSize = '('+row.columnSize+')';
      if (row.decimalDigits){
        columnSize = '(' + row.columnSize + ',' + row.decimalDigits + ')';
      }
      return row.typeName + columnSize;
    },
    showTableName(table){
      let schema = table.actualTableName.schema
      let realTableName = table.actualTableName.tableName;
      if (schema){
        realTableName = schema+'.'+realTableName;
      }
      if (table.table.remark){
        return realTableName + '('+table.table.remark+')';
      }
      return realTableName;
    },
    switchCatalog(catalog){
      this.meta.tables.length = 0 ;
      this.table = {};
      this.dataCount = {loading : false};
      this.view.loading = true;
      // 加载当前 catalog 的 schemas
      if (this.meta.catalogs){
        let currentCatalogFilter = this.meta.catalogs.filter(item => item.catalog === catalog);
        if (currentCatalogFilter && currentCatalogFilter.length > 0){
          this.meta.schemas = currentCatalogFilter[0].schemas;
        }
      }

      database.tables(this.input.connName,this.input.catalog).then(res => {
        this.view.loading = false;

        this.meta.tables = res.data;
        if (this.meta.tables && this.meta.tables.length > 0){
          this.table = this.meta.tables[0];
        }
      })
    },
    switchConnection(value){
      this.meta.catalogs.length = 0 ;   // 清空数组
      this.meta.tables.length = 0 ;
      this.input.catalog = null;
      this.table = {};

      // 获取当前连接信息
      if (value){
        database.catalogs(value).then(res => {
          this.meta.catalogs = res.data;
          if (res.data && res.data.length > 0){
            this.input.catalog = res.data[0].catalog;
            this.switchCatalog(res.data[0].catalog)
          }
        });
      }
    },
    choseTable(table){
      this.table = table;
      this.view.dataTotal = null;

      this.hierarchy();
    },
    refreshTable(table,index){
      database.refreshTable(this.input.connName,table.actualTableName).then(res => {
        this.table = res.data;
        this.meta.tables[index] = res.data;
      })
    },
    refreshCatalog(){
      database.refreshCatalogOrSchema(this.input.connName,this.input.catalog,null).then(res => {
        this.meta.tables = res.data;
        if (this.meta.tables.length > 0){
          this.table = this.meta.tables[0];
        }
      })
    },
    refreshSchema(schema){
      database.refreshCatalogOrSchema(this.input.connName,this.input.catalog,schema).then(res => {
       this.searchTables();
      });
    },
    refreshConnect(){
      this.switchConnection(this.input.connName);
    },
    showIndexType(row){
      switch (row.indexType) {
        case 0:
          return 'tableIndexStatistic';
        case 1:
          return 'tableIndexClustered';
        case 2:
          return 'tableIndexHashed'
        case 3:
          return '类型 3 '
      }
      return 'unknow';
    }
  }
}
</script>

<style scoped>
  @import "../../assets/bootstrap.css";

  .list-inline>.list-inline-item{
    margin-top: 8px;
    margin-left: 5px;
    padding: 10px 15px;
    border-radius: 5px;
  }

  .list-group-item:hover{
    background-color: #E8E7E6;
  }
  .list-group-item.active:hover{
    z-index: 2;
    color: #fff;
    background-color: #337ab7;
    border-color: #337ab7;
  }
  .cursor-point{
    cursor: pointer;
  }
  .table-items{
    min-height: 100px;
    max-height: 260px;
    overflow-y: scroll;
  }

  .append-button-group>.el-button{
    margin-left: 10px;
    font-size: 18px;
    font-weight: bold;
  }
  .list-group-item{
    padding: 0 !important;
  }
  .list-group-item>span{
    display: inline-block;
    padding: 10px 7px 10px 15px;
  }
  .list-group-item>i{
    display: inline-block;
    /*padding-right: 15px;*/
    padding-left: 7px;
    cursor: pointer;
  }
</style>
