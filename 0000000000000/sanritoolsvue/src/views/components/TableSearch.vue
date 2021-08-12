<template>
    <div>
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
        <el-col :span="24">
          <el-table
            :data="tables"
            v-loading="view.loading"
            border
            stripe
            highlight-current-row
            size="mini"
            style="width: 100%"
            height="500"
            @selection-change="handleSelectionChange"
          >
            <el-table-column type="selection" width="55" />
            <el-table-column type="index"  width="50"/>
            <el-table-column  label="schema" sortable >
              <template slot-scope="scope">
                <span>{{scope.row.actualTableName.schema}}</span>
              </template>
            </el-table-column>
            <el-table-column  label="tableName" >
              <template slot-scope="scope">
                <span>{{scope.row.actualTableName.tableName}}</span>
              </template>
            </el-table-column>
            <el-table-column  label="注释" >
              <template slot-scope="scope">
                <span>{{scope.row.table.remark}}</span>
              </template>
            </el-table-column>
          </el-table>
        </el-col>
      </el-row>
    </div>
</template>

<script>
  import database from '../../api/database'

  export default {
    name: 'TableSearch',
    data(){
      return {
        input: {
          keyword: '',
          selectedSchemas: [],
          selectedTables:[],
          connect: null,
          catalog: null,
        },
        view: {
          loading: false
        },
        tables: [],
        schemas: [],
        catalogs: [],
        connections: []
      }
    },
    methods: {
      // 外部调用,获取当前数据源
      getDatasource(){
        return {
          connName: this.input.connect,
          catalog: this.input.catalog,
          tables: this.input.selectedTables,
          schemas: this.input.selectedSchemas
        }
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
        });
      },
      handleSelectionChange(selectedTables){
        this.input.selectedTables = selectedTables;
        this.$emit('selection-change', this.input.selectedTables)
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

        database.tables(this.input.connect,this.input.catalog).then(res => {
          this.tables = res.data;
        })
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
      }
    },
    mounted() {
      this.refreshConnect();
    },
    props:{
      connect: String,
      catalog: String
    }
  }
</script>

<style scoped>

</style>
