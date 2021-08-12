<template>
  <div>
    <div class="panel panel-default">
      <div class="panel-heading">方案管理</div>
      <div class="panel-body">
        <el-row>
          <el-col>
            <el-select v-model="input.schema" @change="switchSchema" size="small">
              <el-option v-for="schema in schemas" :key="schema" :value="schema" :label="schema" />
            </el-select>

            <el-button size="small"  type="text" icon="el-icon-plus" class="margin-left" @click="openSchemaDialog" >编辑方案</el-button>

            <el-upload style="display: inline-block" class="margin-left" action="#" :show-file-list="false" :http-request="uploadTemplate" >
              <el-button size="small" slot="trigger" icon="el-icon-plus"  type="text">新模板</el-button>
            </el-upload>
          </el-col>
        </el-row>
        <el-row class="margin-top">
          <el-col :span="6">
            <list-group :list="templates" @click-item="choseTemplate" />
          </el-col>
          <el-col :span="17" class="margin-left">
            <el-button size="small" type="plain" @click="saveTemplate"><i class="fa fa-floppy-o" aria-hidden="true"></i> 保存模板</el-button>
            <el-input  type="textarea"
                       class="padding-top"
                       :autosize="{ minRows: 10, maxRows: 20}"
                       placeholder="模板数据"
                       v-model="content"/>
          </el-col>
        </el-row>
      </div>
    </div>

    <el-dialog :visible.sync="dialog.visible" :title="dialog.title">
      <el-input placeholder="输入方案名" v-model="dialog.schemaName" size="small" />
      <el-table
              :data="templateSelectTable"
              border
              stripe
              style="width: 100%"
              size="mini"
              highlight-current-row
              @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55" />
        <el-table-column prop="name" label="模板名" />
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button  type="primary"  @click="createSchema">确 认</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import database from "../../api/database";

  import ListGroup from "../../components/ListGroup";

  export default {
    name: "SchemaManage",
    components: {ListGroup},
    data(){
      return {
        input: {
          schema: null,
          template: null
        },
        schemas: [],
        templates: [],
        content: null,
        dialog: {
          schemaName: null,
          visible: false,
          title: '创建新方案',
          templates:[],
          selectedTemplate: []
        }
      }
    },
    methods: {
      saveTemplate(){
        database.override({name:this.input.template,content:this.content}).then(res => {
          this.$message('模板覆盖成功');
        })
      },
      openSchemaDialog(){
        this.dialog.visible = true;
        this.dialog.schemaName = this.input.schema;
      },
      refreshTemplates(){
        database.templates().then(res => {
          this.dialog.templates = res.data;
        })
      },
      refreshSchemas(){
        database.codeSchemas().then(res => {
          this.schemas = res.data;
          if (res.data && res.data.length > 0){
            this.switchSchema(res.data[0]);
          }
        });
      },
      handleSelectionChange(rows){
        if (rows){
          this.dialog.selectedTemplate = rows.map(item => item.name);
        }
      },
      switchSchema(schema){
        this.input.schema = schema;
        database.codeSchemaTemplates(schema).then(res => {
          this.templates = res.data;
          if (res.data && res.data.length > 0){
            this.choseTemplate(res.data[0])
          }
        })
      },
      choseTemplate(template){
        this.input.template = template;
        database.templateContent(template).then(res => {
          this.content = res.data;
        })
      },
      dropSchemaTemplate(){

      },
      addSchemaTemplate(){

      },
      dropSchema(){

      },
      createSchema(){
        let schema = this.dialog.schemaName + '.schema';
        let templates = this.dialog.selectedTemplate.join('\n');
        database.override({name:schema,content: templates}).then(res => {
          this.dialog.visible = false;
          this.refreshSchemas();
        })
      },
      uploadTemplate(data){
        let file = data.file;
        let formData = new FormData();
        formData.append('file',file,file.name)
        database.uploadTemplate(formData).then(res => {
          this.refreshTemplates();
          this.$message('模板上传成功')
        })
      },
      dropTemplate(){

      },
      modifyTemplate(){

      },
      getSchema(){
        return this.input.schema;
      }
    },
    mounted() {
     this.refreshSchemas();
     this.refreshTemplates();
    },
    computed: {
      templateSelectTable(){
        if (this.dialog.templates){
          return this.dialog.templates.map(item => ({name:item}));
        }
        return [];
      }
    }
  }
</script>

<style scoped>

</style>
