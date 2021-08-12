<template>
  <div class="app-container">
    <p class="tips">说明: 此工具是默认为阿里的那种配置保存方式来获取数据的,像 nacos , diamond ,
      你只需要指定数据库连接和 schema , 我将为你获取对应 group 和 dataId , 并获取配置数据 </p>
    <el-row>
      <el-col>
        <el-select v-model="input.connect" filterable clearable placeholder="选择连接" @change="switchConnect" size="small">
          <el-option
                  v-for="connection in connects"
                  :key="connection"
                  :label="connection"
                  :value="connection">
          </el-option>
        </el-select>

        <span class="append-button-group">
          <el-button type="text" icon="el-icon-refresh" class="text-danger" @click="refreshConnect"/>
        </span>

        <el-select class="margin-left" v-model="input.catalog" filterable clearable placeholder="选择库" @change="switchCatalog" size="small">
          <el-option v-for="catalogMeta in catalogs"
                     :key="catalogMeta.catalog" :label="catalogMeta.catalog" :value="catalogMeta.catalog"
          />
        </el-select>
        <span class="append-button-group">
          <el-button type="text" icon="el-icon-refresh" class="text-danger" />
        </span>

        <el-button class="margin-left" size="small" type="primary" @click="loadGroups">加载配置组</el-button>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24">
        <el-radio-group v-model="input.logicSchema" v-if="schemas && schemas.length > 0" >
          <el-radio-button size="small" v-for="schema in schemas" :value="schema" :label="schema" :key="schema" />
        </el-radio-group>
      </el-col>
    </el-row>
    <el-row class="margin-top">
      <el-col :span="6">
        <list-group :list="groups" @click-item="choseGroup"/>
      </el-col>
      <el-col :span="6">
        <list-group :list="dataIds" @click-item="choseDataId"/>
      </el-col>
      <el-col :span="10" class="margin-left">
        <el-input  type="textarea"
                   :autosize="{ minRows: 10, maxRows: 20}"
                   placeholder="配置信息"
                   v-model="content"/>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import database from "../../api/database";
  import core from "../../api/core";
  import ListGroup from "../../components/ListGroup";

  export default {
    name: 'configData',
    components: { ListGroup},
    data(){
      return {
        input: {
          connect: null,
          catalog: null,
          group: null,
          dataId: null,
          logicSchema: null
        },
        view: {
          loading: false
        },
        connects: [],
        catalogs: [],
        schemas: [],
        groups: [],
        dataIds: [],
        content: null
      }
    },
    mounted() {
      database.connections().then(res => {
        this.connects = res.data;
        if (this.connects && this.connects.length > 0){
          this.input.connect = this.connects[0];
          this.switchConnect(this.connects[0]);
        }
      });
    },
    methods: {
      loadGroups(){
        if (this.input.logicSchema){
          database.groups(this.input.connect,this.input.logicSchema).then(res => {
            this.groups = res.data;
            if (this.groups && this.groups.length > 0){
              this.choseGroup({value:this.groups[0]});
            }
          })
        }
      },
      choseGroup(value){
        this.input.group = value;
        database.dataIds(this.input.connect,this.input.logicSchema,value).then(res => {
          this.dataIds = res.data;
          if (this.dataIds && this.dataIds.length > 0){
            this.choseDataId(this.dataIds[0]);
          }
        })
      },
      choseDataId(value){
        this.input.dataId = value;
        database.content(this.input.connect,this.input.logicSchema,this.input.group,value).then(res => {
          this.content = res.data;
        })
      },
      switchConnect(connect){
        this.input.connect = connect;
        this.input.logicSchema = null;
        // 获取当前连接信息
        if (connect) {
          database.catalogs(connect).then(res => {
            this.catalogs = res.data;
            if (res.data && res.data.length > 0) {
              this.input.catalog = res.data[0].catalog;
              this.switchCatalog(res.data[0].catalog)
            }
          });
        }
      },
      choseSchema(schema){
        this.input.logicSchema = schema;
      },
      refreshConnect(){
        this.switchConnect(this.input.connName);
      },
      switchCatalog(catalog){
        this.view.loading = true;
        // 加载当前 catalog 的 schemas
        if (this.catalogs){
          let currentCatalogFilter = this.catalogs.filter(item => item.catalog === catalog);
          if (currentCatalogFilter && currentCatalogFilter.length > 0){
            this.schemas = currentCatalogFilter[0].schemas;
            if (!this.schemas){
              this.choseSchema(catalog)
            }
          }
        }
      }
    }
  }
</script>

<style scoped>
  .tips{
    line-height: 1.5;
  }
</style>
