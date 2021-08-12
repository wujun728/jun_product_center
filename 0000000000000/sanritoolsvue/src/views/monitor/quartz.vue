<template>
  <div class="app-container">
    <el-row class="margin-bottom">
      <div class="fit-items">
        <chose-datasource :connect="input.connName" :catalog="input.catalog" @change="changeDatasource"/>
      </div>

    </el-row>
    <el-row class="margin-bottom">
      <div class="" style="display: flex;justify-content: flex-end">
        <el-select size="small" filterable  v-model="input.classloader">
          <el-option v-for="classloader in classloaders" :key="classloader" :label="classloader" :value="classloader" />
        </el-select>
        <el-button size="small" class="margin-left" type="text" plain icon="el-icon-refresh" @click="loadClassloaders" />

        <el-button type="primary"  class="margin-left" size="small" icon="el-icon-setting" @click="settings.show = true">设置</el-button>
        <el-button type="warning" size="small"  icon="el-icon-plus" @click="dialog.show = true ">新建任务</el-button>
      </div>

      <el-form :inline="true" @submit.native.prevent size="small">
<!--        <el-form-item>-->
<!--          <el-input v-model="input.keyword" placeholder="输入任务名称搜索" @keyup.enter.native="search" >-->
<!--    -->
<!--            <el-button slot="append" icon="el-icon-search" @click="search" />-->
<!--          </el-input>-->
<!--        </el-form-item>-->

      </el-form>
    </el-row>
    <el-row>
      <el-table
              :data="triggers"
              border
              stripe
              style="width: 100%"
              size="mini"
              highlight-current-row
              fit
      >
        <el-table-column type="index" width="55" />
        <el-table-column  label="trigger" >
          <template slot-scope="scope">
            <span>{{scope.row.triggerKey.group + '.' + scope.row.triggerKey.name}}</span>
          </template>
        </el-table-column>

        <el-table-column  label="job" >
          <template slot-scope="scope">
            <span>{{scope.row.jobKey.group + '.' + scope.row.jobKey.name}}</span>
          </template>
        </el-table-column>

        <el-table-column label="startTime" width="140">
          <template slot-scope="scope">
            <span>{{parseTime(scope.row.startTime)}}</span>
          </template>
        </el-table-column>
        <el-table-column label="prevFireTime" width="140">
          <template slot-scope="scope">
            <span>{{parseTime(scope.row.prevFireTime)}}</span>
          </template>
        </el-table-column>
        <el-table-column label="nextFireTime" width="140">
          <template slot-scope="scope">
            <span>{{parseTime(scope.row.nextFireTime)}}</span>
          </template>
        </el-table-column>

        <el-table-column label="cron" prop="cron" />

        <el-table-column
                fixed="right"
                label="操作"
                width="300"
        >
          <template slot-scope="scope">
            <el-button type="text" class="text-danger" size="small" @click="trigger(scope.row)" >trigger</el-button>
            <el-button type="text" size="small" @click="pause(scope.row)">pause</el-button>
            <el-button type="text" size="small" @click="resume(scope.row)">resume</el-button>
            <el-button type="text" size="small" @click="remove(scope.row)">remove</el-button>
            <el-button type="text" size="small"  @click="showNextTimes(scope.row)">times</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-row>

    <el-dialog  :visible.sync="settings.show" title="quartz 绑定参数设置">
      <el-form size="small" inline>
        <el-form-item :label="'配置项'+index" v-for="(prop,index) in settings.props" :key="prop.key">
          <div class="" style="display: flex">
            <el-input v-model="prop.key"  placeholder="key"/>
            <el-input v-model="prop.value" placeholder="value" />
            <el-button icon="el-icon-plus" type="text" @click="settings.props.push({key:'',value:''})">添加</el-button>
            <el-button icon="el-icon-delete" v-if="settings.props.length !== 1" @click="settings.props.splice(index,1)" type="text" >删除</el-button>
          </div>

        </el-form-item>
      </el-form>
       <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="bindQuartz">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 添加任务对话框 -->
    <el-dialog  :visible.sync="dialog.show" title="添加一个任务">
      <el-form size="small"  label-width="120px">
        <el-form-item label="jobKey">
          <div class="" style="display: flex">
            <el-input v-model="dialog.form.name" placeholder="任务名称" />
            <el-input v-model="dialog.form.group" placeholder="任务分组" />
          </div>
        </el-form-item>

        <el-form-item label="类加载器">
          <el-row>
            <el-select filterable  v-model="dialog.form.classloaderName" @change="choseClassloaderName" >
              <el-option v-for="classloader in classloaders" :key="classloader" :label="classloader" :value="classloader" />
            </el-select>
          </el-row>
          <el-row>
            <el-col :span="15">
              <list-group :list="dialog.loadClasses" @click-item="choseClass" />
            </el-col>
            <el-col :span="8">
              <list-group :list="dialog.methodNames" @click-item="choseMethodName" />
            </el-col>
          </el-row>

        </el-form-item>

        <el-form-item label="cron">
          <el-input v-model="dialog.form.cron" size="small" />
        </el-form-item>

        <el-form-item label="任务描述">
          <el-input type="textarea"
                    :autosize="{ minRows: 5, maxRows: 10}"
                    placeholder="任务描述"
                    v-model="dialog.form.description"/>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="editJob">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import {parseTime} from "../../utils";
  import ChoseDatasource from "../components/ChoseDatasource";
  import quartz from "../../api/quartz";
  import core from '../../api/core'
  import ListGroup from '../../components/ListGroup'

  export default {
    name: "quartz",
    components: {  ChoseDatasource,ListGroup},
    data(){
      return {
        input: {
          keyword: null,
          connName: null,
          catalog: null,
          schema: null,
          classloader: null
        },
        triggers: [],
        classloaders: [],
        dialog: {
          show: false,
          form: {
            name:null,
            group:null,
            description: null,
            className: null,
            classloaderName:null,
            jobMethodName: null,
            cron: null
          },
          loadClasses: [],
          methodNames: []
        },
        settings: {
          show: false,
          props: [{key:'org.quartz.jobStore.tablePrefix',value:'qrtz_'},
            {key:'org.quartz.scheduler.instanceName',value:'SchedulerFactory'}]
        }
      }
    },
    methods: {
      parseTime,
      editJob(){
        quartz.editJob(this.namespace(),this.dialog.form).then(res => {
          this.search();

          this.dialog.show = false;
        })
      },
      choseClassloaderName(value){
        this.dialog.form.classloaderName = value;

        // 查询类加载器加载的类
        core.listLoadedClasses(value).then(res => {
          this.dialog.loadClasses = res.data;
        })
      },
      choseClass(value,index){
        this.dialog.form.className = value;

        // 查询这个类的所有方法
        core.methodNames(this.dialog.form.classloaderName,value).then(res => {
          this.dialog.methodNames = res.data;
        })
      },
      choseMethodName(value,index){
        this.dialog.form.jobMethodName = value;
      },
      search(){
        quartz.triggers(this.input.connName,this.input.catalog,this.input.schema).then(res => {
          this.triggers = res.data;
        })
      },
      changeDatasource(datasource){
        this.input = {...this.input,...datasource};
        this.input.connName = datasource.connect;
        this.search();
      },
      showNextTimes(row){
        let nextTimes = row.nextTimes || [];
        this.$alert(nextTimes.join('<br/>'),'执行时间段',{dangerouslyUseHTMLString:true})
      },
      bindQuartz(){
        let settings = {};
        for (let i = 0;i< this.settings.props.length ; i++) {
          let prop = this.settings.props[i];
          settings[prop.key] = prop.value;
        }
        quartz.bindQuartz(this.namespace(),settings).then(res => {
          this.$message('绑定成功');
          this.settings.show = false;
        })
      },
      loadClassloaders(){
        core.classloaders().then(res => {
          this.classloaders = res.data;
        });
      },
      trigger(row){
        quartz.trigger(this.namespace(),row.jobKey,this.input.classloader).then(res => {
          this.$message('触发任务成功')
        })
      },
      pause(row){
        quartz.pause(this.namespace(),row.jobKey,this.input.classloader).then(res => {
          this.$message('暂停任务成功')
        })
      },
      resume(row){
        quartz.resume(this.namespace(),row.jobKey,this.input.classloader).then(res => {
          this.$message('恢复任务成功')
        })
      },
      remove(row){
        quartz.remove(this.namespace(),row.triggerKey.name,row.triggerKey.group,row.jobKey.name,row.jobKey.group,this.input.classloader).then(res => {
          this.$message('删除任务成功')
          this.search();
        })
      },
      namespace(){
        if (this.input.catalog){
          return this.input.connName + '@' + this.input.catalog;
        }
        if (this.input.schema){
          return this.input.connName + '@' + this.input.schema;
        }
        return this.input.connName;
      }
    },
    created() {
      quartz.loadCache().then(res => {
        Object.assign(this.input,res.data)
      });

      this.loadClassloaders();

    }
  }
</script>

<style scoped>
  .fit-items{
    display: flex;
  }
</style>
