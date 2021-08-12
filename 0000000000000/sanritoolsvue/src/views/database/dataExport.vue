<template>
    <div class="app-container">
      <el-row>
        <chose-datasource @change="changeDatasource"/>
      </el-row>

      <el-row class="margin-top">
        <el-col :span="22">
          <el-input
            type="textarea"
            :autosize="{ minRows: 5, maxRows: 15}"
            placeholder="写入你的 sql 语句"
            v-model="input.sql"
          />
        </el-col>
        <el-button class="margin-left" type="info" size="small" @click="preview" >预览</el-button>
      </el-row>

      <el-row class="margin-top ">
        <div style="display: flex;justify-content: flex-end; padding: 10px">
          <el-button-group class="">
            <el-button type="primary" size="small" @click="exportData" >数据导出</el-button>
          </el-button-group>
        </div>

        <el-table
          :data="dynamicTableData.rows"
          border
          stripe
          style="width: 100%"
          highlight-current-row
          fit
          height="500"
          size="mini">
          <el-table-column v-for="header in dynamicTableData.headers" :key="header.columnName"
                           :prop="header.columnName"
                           :label="header.columnName" sortable />

        </el-table>
      </el-row>
    </div>
</template>

<script>
  import ChoseDatasource from '../components/ChoseDatasource'

  import core from '../../api/core'
  import database from '../../api/database'

  export default {
    name: 'dataExport',
    components: { ChoseDatasource },
    data(){
      return {
        input: {
          connName: null,
          sql: 'select * from aa.broker'
        },
        dynamicTableData: {},
      }
    },
    methods: {
      changeDatasource(datasource){
        this.input.connName = datasource.connect;
      },
      preview(){
        database.exportPreview(this.input.connName,this.input.sql).then(res => {
          this.dynamicTableData = res.data.body;
        })
      },
      exportData(){
        database.exportData(this.input.connName,this.input.sql).then(res => {
          core.fileDownload(res.data.relativePath);
        })
      }
    },
    mounted() {

    },
    created() {

    }
  }
</script>

<style scoped>

</style>
