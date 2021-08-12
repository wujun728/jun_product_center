<template>
  <div class="app-container">
    <el-button class="margin-bottom" plain size="small" icon="el-icon-plus" @click="createModule">新模块</el-button>
    <span class="margin-left">只支持 kafka,redis,mongo,zookeeper,database 固定的模块名</span>
    <div class="connect-container">
      <el-card  class="box-card" v-for="(value,module,index) in connectCards" :key="module"  >
        <div slot="header" class="card-header">
          <span>{{module}}</span>
          <div>
            <el-button icon="el-icon-plus" type="text" @click="createConnect(module)" size="small">新建连接</el-button>
            <el-button icon="el-icon-delete" type="text" @click="dropModule(module)" class="text-danger" size="small">删除模块</el-button>
          </div>

        </div>
        <ul class="connect">
          <li v-for="connect in connectCards[module]" :key="connect.name" >
            <div @click="showConnectInfo(connect)">
              <label>{{connect.name}}</label>
              <span>{{connect.lastModified}}</span>
            </div>
            <i class="el-icon-close cursor-point" @click="dropConnect(module,connect)"></i>
          </li>
        </ul>
      </el-card>
    </div>

    <el-drawer
      :visible.sync="view.drawer"
      :with-header="false"
      direction="rtl">
      <el-button-group style="padding-bottom: 10px; ">
        <el-button plain icon="el-icon-box" size="small" @click="saveConnect">保存</el-button>
      </el-button-group>
     <json-editor :json="connectParam" @change="changeJson" />
    </el-drawer>

    <el-dialog
      :title="'创建 '+input.module+' 连接'"
      width="80%"
      :visible.sync="view.dialog">
      <el-row v-if="input.module !== 'kafka'">
        <el-col :span="24" >
          <monaco-editor @mounted="editorMounted" @codeChange="onCodeChange" height="400" language="json" :code="input.connectCode" />
        </el-col>
      </el-row>
      <el-row v-if="input.module === 'kafka'">
        <el-col :span="24" >
          <monaco-editor2 @mounted="kafkaEditorMounted" @codeChange="onCodeChange" height="400" language="yaml" :code="input.connectCode" />
        </el-col>
      </el-row>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="commitConfig">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import core from '../../api/core'

  import JsonEditor from '@/components/JsonEditor'
  import ListGroup from '@/components/ListGroup'
  import MonacoEditor from 'vue-monaco-editor'
  import MonacoEditor2 from 'vue-monaco-editor'

  export default {
    name: 'connect',
    components:{ JsonEditor, ListGroup, MonacoEditor, MonacoEditor2 },
    data(){
      return {
        connects: [],
        modules: [],
        examples: ['redis','zookeeper','database'],
        connectParam: null,
        view:{
            drawer: false,
            dialog: false
        },
        input: {
          module: null,
          connectCode: null
        },
        dialog: {
          input: {
            editor: null,
            kafkaEditor: null
          }
        }
      }
    },
    mounted() {
      // 加载所有连接
      this.reloadModuleAndConnect();
    },
    methods:{
      editorMounted(editor){
        this.dialog.input.editor = editor;
      },
      kafkaEditorMounted(editor){
        this.dialog.input.kafkaEditor = editor;
      },
      onCodeChange(editor){
        // this.input.connectCode = code;
        this.input.connectCode = editor.getValue();
      },
      commitConfig(){
        if (this.input.module === 'kafka'){
          core.createKafkaConnect(this.input.connectCode).then(res => {
            this.reloadModuleAndConnect();
            this.view.dialog = false;
          });
        }else{
          core.createConnect(this.input.module,this.input.connectCode).then(res => {
            this.reloadModuleAndConnect();
            this.view.dialog = false;
          })
        }
      },
      reloadModuleAndConnect(){
        core.modules().then(res => {
          this.modules = res.data;
        });

        core.connects().then(res => {
          this.connects = res.data;
        });
      },
      dropModule(module){
        this.$confirm('确定删除 '+module+' 模块 此操作不可逆?','警告',{type: 'warning'}).then(() => {
          core.deleteModule(module).then(res => {
            this.reloadModuleAndConnect();
          })
        }).catch(()=>{})

      },
      dropConnect(module,connect){
        this.$confirm('确定删除 '+connect.name+' 连接 此操作不可逆?','警告',{type: 'warning'}).then(() => {
          core.dropConnect(module,connect.name).then(res => {
            let dropIndex = this.connects.findIndex(item => item.name === connect.name);
            this.connects.splice(dropIndex,1);
          })
        }).catch(()=>{})

      },
      createModule(){
        this.$prompt('输入模块名', '新模块', {}).then(({ value }) => {
          core.createModule(value).then(res => {
            this.reloadModuleAndConnect()
          })
        }).catch(() => {});
      },
      showConnectInfo(connect){
        this.input.module = connect.module;
        core.content(connect.module,connect.name).then(res => {
            this.connectParam = JSON.parse(res.data);
            this.view.drawer = true;
        })
      },
      createConnect(module){
        this.input.module = module;
        this.view.dialog = true;
        let format = 'json';
        if (module === 'kafka'){
          format = 'yaml';
        }
        core.example(module,format).then(res => {
          this.input.connectCode = res.data;
          if (this.dialog.input.editor){
            this.dialog.input.editor.setValue(this.input.connectCode);
          }
          if (this.dialog.input.kafkaEditor){
            this.dialog.input.kafkaEditor.setValue(this.input.connectCode);
          }
        })
      },
      changeJson(json){
        this.connectParam= json;
      },
      saveConnect(){
        core.createConnect(this.input.module,this.connectParam).then(res => {
            this.$message({
                message: '连接更新成功',
                type: 'success'
            });
        })
      }

    },
    computed:{
      connectCards(){
        let moduleConnects = {};
        if (this.connects && this.connects.length > 0){
          for (let i = 0; i < this.connects.length; i++) {
            let connect = this.connects[i];
            if (!moduleConnects[connect.module]){
              moduleConnects[connect.module] = [];
            }
            moduleConnects[connect.module].push(connect);
          }
        }

        if (this.modules && this.modules.length > 0){
          for (let i = 0; i < this.modules.length; i++) {
            let module = this.modules[i];
            if (!(module in moduleConnects)){
              moduleConnects[module] = [];
            }
          }
        }
        return moduleConnects;
      }
    }
  }
</script>

<style scoped>

  .box-card{
    width: 400px;
    margin-right: 10px;
    display: inline-block;
    height: 300px;
  }
  .box-card .connect{
    list-style-type: none;
    margin: 0;
    padding: 0;
    font-size: 13px;
    line-height: 18px;
  }
  .box-card .connect>li{
    padding: 12px 10px;
    color: #67c23a;
    background-color: #f0f9eb;
    margin-bottom: 7px;

    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  .box-card .connect>li label{
    content: '-';
    padding-right: 10px;

    cursor: pointer
  }

  .card-header{
    display: flex;
    justify-content: space-between;
    align-items: center;
  }

  /*::-webkit-scrollbar {*/
  /*  width: 2px;*/
  /*  height: 2px;*/
  /*  background-color: #F5F5F5;*/
  /*}*/
</style>
<style>
  .el-card .el-card__header{
    padding: 10px 15px;
  }
  .el-card .el-card__body{
    padding: 0;
    height: calc(100% - 53px);
    overflow-y: scroll;
  }
</style>
