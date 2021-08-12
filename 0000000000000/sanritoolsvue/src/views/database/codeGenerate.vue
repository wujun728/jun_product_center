<template>
    <div class="app-container">
      <el-tabs  v-model="tabs.activeTabName" >
        <el-tab-pane label="代码生成(模板)" name="first">
          <el-row>
            <el-col :span="6">
              <table-manage  ref="firstTableManage"/>
            </el-col>
            <el-col :span="17" class="margin-left">
              <el-row>
                <el-col :span="24">
                  <el-select v-model="input.renameStrategy" size="small" style="width: 240px">
                    <el-option v-for="renameStrategy in renameStrategies" :key="renameStrategy" :value="renameStrategy" :label="renameStrategy" />
                  </el-select>
                  <el-checkbox v-model="input.single" class="margin-left">单文件</el-checkbox>
                  <el-button type="primary"  size="small" class="margin-left" @click="generateCodeConfirm" >预览后生成</el-button>
                </el-col>
              </el-row>
              <el-row class="margin-top">
                <schema-manage ref="schemaManage" />
              </el-row>
              <el-row class="margin-top">
                <package-config ref="firstPackageConfig" />
              </el-row>
            </el-col>
          </el-row>
        </el-tab-pane>
        <el-tab-pane label="代码生成(mapper)" name="second">
          <el-row>
            <el-col :span="24">
              <mapper-build />
            </el-col>
          </el-row>
        </el-tab-pane>
        <el-tab-pane label="代码生成(project)" name="third">
          <el-row>
            <el-col :span="24">
              <project-build />
            </el-col>
          </el-row>
        </el-tab-pane>
      </el-tabs>

      <el-dialog :visible.sync="dialog.templateConfirm.visible" :title="dialog.templateConfirm.title">
        <vue-json-editor :expandedOnStart="true" v-model="dialog.templateConfirm.params" :show-btns="false" lang="zh" />
        <span slot="footer" class="dialog-footer">
          <el-button  type="primary"  @click="generateCode">生 成</el-button>
        </span>
      </el-dialog>
    </div>
</template>

<script>
  import database from '../../api/database'
  import core from "../../api/core";

  import SchemaManage from "./SchemaManage";
  import TableManage from "./TableManage";
  import MapperBuild from "./MapperBuild";
  import PackageConfig from "./PackageConfig";
  import vueJsonEditor from 'vue-json-editor'
  import ProjectBuild from './ProjectBuild'

  export default {
    name: 'codeGenerate',
    components: {SchemaManage, TableManage, MapperBuild, PackageConfig, vueJsonEditor, ProjectBuild},
    data(){
      return {
        input: {
          renameStrategy: null,
          single: true
        },
        tabs:{
          activeTabName: 'first'
        },
        renameStrategies: [],
        dialog:{
          templateConfirm: {
            visible: false,
            title: '确认配置是否正确',
            params: {}
          }
        }
      }
    },
    methods: {
      generateCodeConfirm(){
        let dataSource = this.$refs.firstTableManage.getDataSource();
        if (!dataSource){
          return ;
        }
        let actualTableNames = dataSource.tables.map(item => item.actualTableName);
        dataSource.tables = actualTableNames;

        let schema = this.$refs.schemaManage.getSchema();
        let packageConfig = this.$refs.firstPackageConfig.getPackageConfig();

        // 获取方案的模板列表
        database.codeSchemaTemplates(schema).then(res => {
          let templates = res.data;
          this.dialog.templateConfirm.params = {templates:templates,dataSourceConfig:dataSource,packageConfig:packageConfig,renameStrategyName:this.input.renameStrategy,single:this.input.single}
          this.dialog.templateConfirm.visible = true;
        })

      },
      generateCode(){
        database.codeGenerate(this.dialog.templateConfirm.params).then(res => {
          core.fileDownload(res.data);
          this.dialog.templateConfirm.visible = false;
        })
      }
    },

    mounted() {
      // 加载所有的重命名策略
      database.renameStrategies().then(res => {
        this.renameStrategies = res.data;
        if (res.data && res.data.length > 0){
          this.input.renameStrategy = res.data[0];
        }
      })
    },
    computed: {

    }
  }
</script>

<style scoped>
  .b{}
</style>
