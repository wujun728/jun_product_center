<template>
  <div class="app-container">
    <el-row>
      <el-select filterable clearable v-model="input.project" @change="loadStatemenetIds" size="small">
        <el-option v-for="project in projects" :key="project.project" :value="project.project" :label="project.project"/>
      </el-select>
      <el-button  size="small" plain @click="view.dialog = true" icon="el-icon-upload">上传 Mapper</el-button>
      <el-button  size="small" plain @click="reloadMappers" icon="el-icon-connection">重新加载</el-button>

    </el-row>
    <el-row class="margin-top">
      <el-col :span="15">
        <el-row>
          <el-col :span="14">
            <list-group :list="namespaces" @click-item="loadNamespaceIds" />
          </el-col>
          <el-col :span="10">
            <list-group :list="ids" @click-item="showStatementParam" />
          </el-col>
        </el-row>

      </el-col>
      <el-col :span="9" >
        <el-select filterable clearable v-model="input.connName"  size="small">
          <el-option v-for="connName in connNames" :key="connName" :value="connName" :label="connName"/>
        </el-select>
        <el-button-group>
          <el-button  size="small" plain icon="el-icon-video-play" @click="execute">执行并获取 SQL </el-button>
        </el-button-group>
        <json-editor :json="input.params" @change="changeParams"/>
      </el-col>
    </el-row>

    <el-dialog  :visible.sync="view.dialog" title="上传 Mapper ">
      <el-form label-width="120px" size="small">
        <el-form-item label="项目名">
          <el-input v-model="input.project" placeholder="项目名"></el-input>
        </el-form-item>
        <el-form-item label="类加载器">
          <el-select filterable  v-model="input.classloader">
            <el-option v-for="classloader in classloaders" :key="classloader" :label="classloader" :value="classloader" />
          </el-select>
          <el-button plain icon="el-icon-refresh" @click="loadClassloaders" />
        </el-form-item>
        <el-form-item label="Mapper 文件">
          <el-upload action="#" :show-file-list="false" :http-request="cacheMapperFile" :before-upload="checkUploadFile">
            <el-button slot="trigger" size="small" type="primary">上传 Mapper</el-button>
          </el-upload>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="postMapper">确 定</el-button>
      </span>
    </el-dialog>

    <el-dialog
      title="执行 SQL 和数据"
      :visible.sync="view.drawer"
      width="90%">
      <el-button size="small" plain icon="el-icon-video-play" @click="executeSQL">执行</el-button>

      <el-input
        type="textarea"
        :autosize="{ minRows: 5, maxRows: 10}"
        placeholder="输入 SQL 来执行"
        v-model="dynamicTableData.sql">
      </el-input>

      <el-table
        :data="dynamicTableData.rows"
        style="width: 100%"
        height="500"
        size="mini">
        <el-table-column v-for="header in dynamicTableData.headers" :key="header.columnName"
          :prop="header.columnName"
          :label="header.columnName" sortable>
        </el-table-column>
      </el-table>
    </el-dialog>
  </div>
</template>

<script>
  import core from '../../api/core'
  import mybatis from '../../api/mybatis'
  import database from '../../api/database'

  import ListGroup from '@/components/ListGroup'
  import JsonEditor from '@/components/JsonEditor'

  export default {
    name: 'mybatis',
    components: { ListGroup , JsonEditor},
    data(){
      return {
        view: {
          dialog: false,
          drawer: false
        },
        input: {
          classloader: null,
          project: null,
          file: null,
          params: null,
          statementId: null,
          connName: null,
          namespace: null
        },
        classloaders:[],
        projects: [],
        statementIds: {},
        connNames: [],
        dynamicTableData: {},
        ids: []
      }
    },
    mounted(){
      // 加载当前已经上传好了的项目
      this.loadProjects();

      this.loadClassloaders();

      // 加载所有的数据库连接
      database.connections().then(res => {
        this.connNames = res.data;
        if (this.connNames && this.connNames.length > 0){
          this.input.connName = this.connNames[0];
        }
      })
    },
    methods: {
      loadNamespaceIds(namespace){
        this.input.namespace = namespace;
        this.ids = this.statementIds[namespace];
      },
      loadClassloaders(){
        // 加载所有的类加载器
        core.classloaders().then(res => {
          this.classloaders = res.data;
        })
      },
      loadProjects(){
        mybatis.projects().then(res => {
          this.projects = res.data;
          if (this.projects && this.projects.length > 0){
            this.loadStatemenetIds(this.projects[0].project);
          }
        })
      },
      reloadMappers(){
        mybatis.reload().then(res => {
          this.loadProjects();
        });
      },
      loadStatemenetIds(project){
        this.input.project = project;
        mybatis.statementIds(project).then(res => {
          if (res.data){
            let namespaces = {};
            for (let i = 0; i < res.data.length; i++) {
              let statementId = res.data[i];

              let pos = statementId.lastIndexOf('.');
              let namespace = statementId.substring(0,pos);
              let id = statementId.substring(pos + 1);

              namespaces[namespace] = namespaces[namespace] || [];
              namespaces[namespace].push(id);
            }
            this.statementIds = namespaces;

            this.loadNamespaceIds(Object.keys(namespaces)[0]);
          }

        })
      },
      checkUploadFile(file){
        let filename = file.name;
        let pos = filename.lastIndexOf('.');
        if (pos === -1){
          alert('需上传 xml 文件');
        }
        let extension = filename.substring(pos+1,filename.length)
        if (extension !== 'xml' ){
          alert('需上传 xml 文件');
        }

        return true;
      },
      cacheMapperFile(data){
        console.log(data)
        this.input.file = data.file;
      },
      postMapper(){
        let formData = new FormData();
        formData.append('file',this.input.file,this.input.file.name)
        formData.append('project',this.input.project)
        formData.append('classloaderName',this.input.classloader)

        mybatis.uploadMapperFile(formData).then(res => {
          this.view.dialog = false;
        })
      },
      showStatementParam(value){
        this.input.statementId = this.input.namespace+'.'+value;
        mybatis.statementParams(this.input.project,value).then(res => {
          this.input.params = {};
          if (res.data){
            for (let i = 0; i < res.data.length; i++) {
              this.input.params[res.data[i].property] = null;
            }
          }
        })
      },
      changeParams(params){
        this.input.params = params;
      },
      execute(){
        mybatis.boundSql(this.input.project,this.input.statementId,this.input.connName,'java.util.HashMap',this.input.params).then(res => {
          this.dynamicTableData = res.data.dynamicQueryDto;
          this.view.drawer = true;
        })
      },
      executeSQL(){
        database.executeQuery(this.input.connName,this.dynamicTableData.sql).then(res => {
          this.dynamicTableData = res.data[0];
        })
      }
    },
    computed: {
      namespaces(){
       return Object.keys(this.statementIds);
      }
    }
  }
</script>

<style scoped>

</style>
