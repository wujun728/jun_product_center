<template>
  <div >
    <el-row>
      <el-col :span="6" class="padding-right">
        <table-manage ref="tablemanager" />
      </el-col>
      <el-col :span="18">
        <el-button-group>
          <el-button type="primary" size="mini" icon="el-icon-toilet-paper" @click="generate">生成</el-button>
        </el-button-group>

        <el-form size="mini" :inline="true" label-width="100px" class="margin-top">
          <el-row>
            <el-col :span="24">
              <el-form-item label="targetRuntime" >
                <el-select v-model="input.targetRuntime" @change="changeRuntime">
                  <el-option v-for="runtime in targetRuntimes" :key="runtime" :value="runtime" :label="runtime" />
                </el-select>
              </el-form-item>
              <el-form-item label="javaClientType">
                <el-select v-model="input.javaClientType" :disabled="view.javaClientTypeDisabled">
                  <el-option v-for="jct in javaClientTypes" :key="jct" :value="jct" :label="jct" />
                </el-select>
              </el-form-item>
              <el-form-item label="modelType">
                <el-select v-model="input.modelType">
                  <el-option v-for="mt in modelTypes" :key="mt" :value="mt" :label="mt" />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="24">
              <el-form-item label="filesConfig">
                <el-checkbox label="entity" v-model="input.filesConfig.entity" />
                <el-checkbox label="xml" v-model="input.filesConfig.xml"/>
                <el-checkbox label="mapper" v-model="input.filesConfig.mapper" />
              </el-form-item>
            </el-col>
          </el-row>

        </el-form>

        <package-config ref="packageConfig" />

        <div class="panel panel-default">
          <div class="panel-heading"> <el-checkbox v-model="input.pluginConfigs.tkmybatis.enable" /> 插件配置(tk.mybatis.mapper.generator.MapperPlugin)</div>
          <div class="panel-body">
            <el-form size="mini" label-width="120px" :disabled="!input.pluginConfigs.tkmybatis.enable">
              <el-form-item label="mappers">
                <el-input placeholder="输入 mappers " v-model="input.pluginConfigs.tkmybatis.properties.mappers" />
              </el-form-item>
              <el-form-item label="caseSensitive">
                <el-checkbox v-model="input.pluginConfigs.tkmybatis.properties.caseSensitive">caseSensitive</el-checkbox>
              </el-form-item>
              <el-form-item label="forceAnnotation">
                <el-checkbox v-model="input.pluginConfigs.tkmybatis.properties.forceAnnotation">forceAnnotation</el-checkbox>
              </el-form-item>
              <el-form-item label="delimiter">
                <el-col :span="2">
                  <el-input v-model="input.pluginConfigs.tkmybatis.properties.beginningDelimiter" />
                </el-col>
                <el-col :span="2">
                  <el-input v-model="input.pluginConfigs.tkmybatis.properties.endingDelimiter" />
                </el-col>
              </el-form-item>
              <el-form-item label="lombok">
                <el-checkbox-group v-model="input.lombokArray">
                  <el-checkbox label="Getter" />
                  <el-checkbox label="Setter" />
                  <el-checkbox label="ToString" />
                  <el-checkbox label="Accessors" />

                </el-checkbox-group>

              </el-form-item>
            </el-form>
          </div>
        </div>
      </el-col>
    </el-row>

  </div>
</template>

<script>
  import TableManage from "./TableManage";
  import PackageConfig from "./PackageConfig";

  import database from '../../api/database'
  import core from '../../api/core'

  export default {
    name: 'MapperBuild',
    components: { TableManage, PackageConfig },
    data(){
      return {
        input: {
          targetRuntime: 'MyBatis3Simple',
          modelType: 'FLAT',
          javaClientType: 'XMLMAPPER',
          filesConfig: {
            entity: true,
            xml: true,
            mapper: true
          },
          lombokArray: ['Getter','Setter','ToString','Accessors'],
          pluginConfigs: {
            tkmybatis:{
              enable: false,
              type: 'tk.mybatis.mapper.generator.MapperPlugin',
              properties: {
                mappers: 'tk.mybatis.mapper.common.Mapper',
                caseSensitive: true,
                forceAnnotation: true,
                beginningDelimiter: '`',
                endingDelimiter: '`',
                lombok: null
              }
            }
          }
        },
        view: {
          javaClientTypeDisabled: false
        },
        modelTypes:['FLAT',"CONDITIONAL",'HIERARCHICAL'],
        targetRuntimes: ['MyBatis3Simple','MyBatis3DynamicSql','MyBatis3Kotlin','MyBatis3'],
        javaClientTypes: ['XMLMAPPER','ANNOTATEDMAPPER']
      }
    } ,
    methods: {
      generate(){
        let datasource = this.$refs.tablemanager.getDataSource();
        let actualTableNames = datasource.tables.map(item => item.actualTableName);
        datasource.tables = actualTableNames;

        let packageConfig = this.$refs.packageConfig.getPackageConfig();

        let params = {
          projectName: packageConfig.projectName,
          dataSourceConfig: datasource,
          packageConfig: packageConfig,
          pluginConfigs: [],
          filesConfig: this.input.filesConfig,
          targetRunTime: this.input.targetRuntime,
          modelType: this.input.modelType,
          javaClientType: this.input.javaClientType,
        };

        this.input.pluginConfigs.tkmybatis.properties.lombok = this.input.lombokArray.join(',');

        for (let key in this.input.pluginConfigs){
          if (this.input.pluginConfigs[key].enable){
            params.pluginConfigs.push(this.input.pluginConfigs[key]);
          }
        }

        // console.log(params);

        database.buildMapper(params).then(res => {
          core.fileDownload(res.data);
        })
      },
      changeRuntime(value){
        this.input.targetRuntime = value;
        switch (value) {
          case 'MyBatis3DynamicSql':
          case 'MyBatis3Kotlin':
            this.view.javaClientTypeDisabled = true;
            break;
          case 'MyBatis3':
            this.view.javaClientTypeDisabled = false;
            this.javaClientTypes = ['XMLMAPPER','MIXEDMAPPER','ANNOTATEDMAPPER'];
            break;
          case 'MyBatis3Simple':
            this.view.javaClientTypeDisabled = false;
            this.javaClientTypes = ['XMLMAPPER','ANNOTATEDMAPPER'];
            break;
        }
      }
    }
  }
</script>

<style scoped>

</style>
