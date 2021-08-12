<template>
  <div class="app-container">
    <el-row>
      <el-col :span="24">
        <el-select v-model="input.connect" filterable clearable placeholder="选择连接" @change="switchConnection" size="small">
          <el-option
            v-for="connection in connections"
            :key="connection"
            :label="connection"
            :value="connection">
          </el-option>
        </el-select>

        <el-select class="margin-left" v-model="input.catalog" filterable clearable placeholder="选择库" @change="switchCatalog" size="small">
          <el-option v-for="catalogMeta in catalogs"
                     :key="catalogMeta.catalog" :label="catalogMeta.catalog" :value="catalogMeta.catalog"
          />
        </el-select>
      </el-col>
    </el-row>
    <el-row v-if="schemas && schemas.length > 0">
      <el-col :span="24">
        <div class=" padding-top text-forestgreen">当前 schema 数量: {{schemas.length}} </div>
        <el-checkbox-group v-model="input.selectedSchemas"  @change="searchTables">
          <el-checkbox-button size="small" v-for="schema in schemas" :value="schema" :label="schema" :key="schema"/>
        </el-checkbox-group>
      </el-col>
    </el-row>
    <el-row>
      <el-input  placeholder="输入表名/字段名/表注释/字段注释/table:表名/column:列名/tag:表标记" class="padding-top padding-bottom" v-model="input.keyword" @keyup.enter.native="searchTables" @keyup.native="keyupSearch">
        <el-button slot="append" icon="el-icon-search" @click="searchTables"></el-button>
      </el-input>
    </el-row>

    <el-row>
      <el-col :span="6">
        <ul class="list-group" style="min-height: 1px; max-height: 600px; overflow-y: scroll;padding: 2px">
          <li :class="index === input.choseIndex ? 'list-group-item text-more cursor-point active' : 'list-group-item text-more cursor-point'"
              v-for="(table,index) in tables" :key="table._hashCode" :title="table.table.remark" @click="choseTable(index)" >
            {{showTableName(table)}}</li>
        </ul>
      </el-col>
      <el-col :span="17">
        <el-button-group class="margin-left">
          <el-button plain size="mini" :class="state.echart === 'hierarchy' ? 'text-danger':'' " @click="hierarchy"><i class="fa fa-sitemap" aria-hidden="true"></i> Hierarchy</el-button>
          <el-button plain size="mini" :class="state.echart === 'hierarchy' ? '':'text-danger' " @click="superTypes" > superTypes</el-button>
        </el-button-group>
        <el-row>
          <el-tree :data="relations" ref="relation" :default-expand-all="true" />
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import database from '../../api/database'
  import core from '../../api/core'

  import ListGroup from '../../components/ListGroup'


  export default {
    name: 'relation',
    data(){
      return {
        input: {
          connect: null,
          catalog: null,
          selectedSchemas: [],
          choseIndex : 0
        },
        state: {
          echart: 'hierarchy'
        },
        view: {
          loading: false,
        },
        schemas: [],
        catalogs: [],
        connections: [],
        tables: [],
        relations: []
      }
    },
    methods: {
      showTableName(table){
        let schema = table.actualTableName.schema
        let realTableName = table.actualTableName.tableName;
        if (schema){
          realTableName = schema+'.'+realTableName;
        }
        if (table.table && table.table.remark){
          return realTableName + '('+table.table.remark+')';
        }
        return realTableName;
      },
      choseTable(index){
        this.input.choseIndex = index;

        // let root = this.$refs.relation.getNode('/');
        // root.loaded = false;
        this[this.state.echart].call(this);
      },
      hierarchy(){
        this.state.echart = 'hierarchy';
        let table = this.tables[this.input.choseIndex];
        database.hierarchy(this.input.connect,table.actualTableName).then(res => {
          if (res.data ){
            this.relations = [res.data]
          }
        })
      },
      superTypes(){
        this.state.echart = 'superTypes';
        let table = this.tables[this.input.choseIndex];
        database.superTypes(this.input.connect,table.actualTableName).then(res => {
          if (res.data ){
            this.relations = [res.data]
          }
        })
      },
      keyupSearch(){
        if (this.input.keyword && this.input.keyword.length > 10){
          this.searchTables();
        }
      },
      searchTables(){
        this.view.loading = true;
        database.searchTables(this.input.connect,this.input.catalog,this.input.selectedSchemas,this.input.keyword).then(res => {
          this.view.loading = false;
          this.tables = res.data;

          if (this.tables && this.tables.length > 0){
            this.choseTable(0)
          }
        });
      },
      refreshConnect(){
        database.connections().then(res => {
          this.connections = res.data;
          if (res.data && res.data.length> 0){
            if (this.connect){
              this.switchConnection(this.connect)
            }else{
              this.switchConnection(res.data[0]);
            }

          }
        })
      },
      switchConnection(value){
        this.input.connect = value;
        // 获取当前连接信息
        if (value){
          database.catalogs(value).then(res => {
            this.catalogs = res.data;
            if (res.data && res.data.length > 0){
              if (this.catalog){
                this.switchCatalog(this.catalog);
              }else{
                this.switchCatalog(res.data[0].catalog)
              }
            }
          });
        }
      },
      switchCatalog(catalog){
        this.input.catalog = catalog;
        // 加载当前 catalog 的 schemas
        if (this.catalogs){
          let currentCatalogFilter = this.catalogs.filter(item => item.catalog === catalog);
          if (currentCatalogFilter && currentCatalogFilter.length > 0){
            this.schemas = currentCatalogFilter[0].schemas;
          }
        }

        this.searchTables();
      },
    },
    computed: {
    },
    mounted() {
      this.refreshConnect();
    },
  }
</script>

<style scoped>

</style>
