<template>
  <div>
    <el-row>
      <el-col :span="6" class="padding-right">
        <table-manage ref="tablemanager" />
      </el-col>
      <el-col :span="18">
        <el-button-group class="margin-bottom">
          <el-button type="primary" size="mini" icon="el-icon-toilet-paper" @click="generate">生成</el-button>
        </el-button-group>

        <div class="panel panel-default">
          <div class="panel-heading">全局设置</div>
          <div class="panel-body">
            <el-form inline size="mini">
              <el-form-item label="">

              </el-form-item>
            </el-form>
          </div>
        </div>

        <package-config ref="packageConfig" />

        <div class="panel panel-default">
          <div class="panel-heading">Maven 版本配置</div>
          <div class="panel-body">
            <el-form size="mini" inline>
              <el-form-item label="groupId" >
                <el-input v-model="input.mavenConfig.groupId" />
              </el-form-item>
              <el-form-item label="artifactId" >
                <el-input v-model="input.mavenConfig.artifactId" />
              </el-form-item>
              <el-form-item label="version" >
                <el-input v-model="input.mavenConfig.version" />
              </el-form-item>
              <el-form-item label="springBootVersion" >
                <el-input v-model="input.mavenConfig.springBootVersion" />
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
    name: 'ProjectBuild',
    components: {TableManage, PackageConfig},
    data() {
      return {
        input: {
          mavenConfig: {
            groupId: null,
            artifactId: null,
            version: '1.0-SNAPSHOT',
            springBootVersion: '2.0.5.RELEASE'
          },
          globalConfig: {

          }
        }
      }
    },
    methods: {
      generate(){

      }
    }
  }
</script>

<style scoped>

</style>
