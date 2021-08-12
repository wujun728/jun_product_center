<template>
  <div>
    <div class="panel panel-default">
      <div class="panel-heading"><i class="el-icon-setting"></i> 数据源设置</div>
      <div class="panel-body">
        <el-button-group class="padding-bottom">
          <el-button plain circle size="small" icon="el-icon-plus" @click="dialog.visible = true" />
          <el-button plain circle size="small" icon="el-icon-folder-delete" @click="cleanAllTable"/>
        </el-button-group>
        <ul class="list-group ">
          <li class="list-group-item" v-for="(tableMetaData,index ) in selectedTables">
            <span>{{tableMetaData.actualTableName.tableName}}</span>
            <a class="pull-right cursor-point text-danger" @click="selectedTables.splice(index,1)" ><i class="fa fa-trash "></i> 删除</a>
          </li>
        </ul>
      </div>
    </div>

    <el-dialog :visible.sync="dialog.visible" :title="dialog.title">
      <el-row>
        <el-col :span="24">
          <table-search ref="tableSearch"/>
        </el-col>
      </el-row>
      <span slot="footer" class="dialog-footer">
        <el-button  type="primary"  @click="selectTables">确 认</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import TableSearch from "../components/TableSearch";

  export default {
    name: "TableManage",
    components: {TableSearch},
    data(){
      return {
        selectedTables:[],
        dialog: {
          visible: false,
          title: '选择表'
        }
      }
    },
    methods: {
      selectTables(){
        let datasource = this.$refs.tableSearch.getDatasource();
        this.selectedTables = datasource.tables;
        this.dialog.visible = false
      },
      getDataSource(){
        if (Object.keys(this.$refs).length === 0){
          this.$message('请先选择数据表');
          return null;
        }
        return this.$refs.tableSearch.getDatasource();
      },
      cleanAllTable(){
        this.selectedTables = [];
      }
    },
    mounted() {

    }
  }
</script>

<style scoped>
  @import "../../assets/bootstrap.css";
</style>
