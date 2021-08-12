<template>
    <div class="app-container">
      <el-row class="margin-bottom">
        <el-select size="small" clearable filterable v-model="input.classloader" @change="handleChangeClassloader">
          <el-option v-for="cl in classloaders" :label="cl" :value="cl" :key="cl" />
        </el-select>
        <el-button class="margin-left" size="small" type="text" @click="beginUpload">上传</el-button>
      </el-row>
      <el-row>
        <el-col :span="13">
          <el-table
            :data="loadedClasses"
            border
            stripe
            style="width: 100%"
            height="500"
            highlight-current-row
            @row-click="choseClass"
            size="mini">
            <el-table-column type="index" width="55" />
<!--            <el-table-column type="selection" width="55" />-->
            <el-table-column
              prop="packageName"
              label="包名"
              sortable
            />
            <el-table-column
              prop="name"
              label="类名" sortable />
            <el-table-column
              label="方法数"
              width="90"
              sortable
            >
              <template slot-scope="scope">
                <span>{{scope.row.methods.length}}</span>
              </template>
            </el-table-column>

            <el-table-column
              label="字段数"
              width="90"
              sortable
            >
              <template slot-scope="scope">
                <span>{{scope.row.fields.length}}</span>
              </template>
            </el-table-column>

          </el-table>
        </el-col>
        <el-col :span="11">
          <div style="height: 600px; overflow-y: scroll">
            <mavon-editor :editable="false"
                          :subfield="false"
                          :toolbarsFlag="false"
                          defaultOpen="preview"
                          style="height: 100%;width: 100%;"
                          v-model="currentClassStruct" />
          </div>

        </el-col>
      </el-row>

      <el-dialog  :visible.sync="dialog.show" title="上传或覆盖类">
        <el-input class="margin-bottom" size="small" clearable v-model="dialog.input.classloaderName" placeholder="类加载器名" />
        <div>
          <el-upload style="display: inline-block" action="#" :show-file-list="false" :http-request="commonUploadClassFile" :before-upload="checkUploadFile">
            <el-button slot="trigger" size="small" type="primary">上传 DTO</el-button>
          </el-upload>
          <el-upload style="display: inline-block" class="margin-left" action="#" :show-file-list="false" :http-request="uploadWholeClassFile" :before-upload="checkUploadFile">
            <el-button slot="trigger" size="small" type="primary">上传标准 DTO</el-button>
          </el-upload>
        </div>
      </el-dialog>
    </div>
</template>

<script>
  import core from '../../api/core'

  import { mavonEditor } from 'mavon-editor'
  import 'mavon-editor/dist/css/index.css'

  export default {
    name: 'classloaderNew',
    components: {mavonEditor},
    data(){
      return {
        input: {
          classloader: null
        },
        classloaders: [],
        loadedClasses: [],
        dialog: {
          show: false,
          input: {
            classloaderName: null,
          }
        },
        currentClassStruct: '暂无代码'
      }
    },
    methods: {
      choseClass(row,column,event){
        let javaCodes = [];
        let struct = row;
        javaCodes.push("package "+struct.packageName+';');
        javaCodes.push('public class '+struct.name +'{');
        for (let i = 0; i < struct.fields.length; i++) {
          let field = struct.fields[i];
          javaCodes.push('\t private '+field.type +' '+field.name+';');
        }
        for (let i = 0; i < struct.methods.length; i++) {
          let method = struct.methods[i];
          let args = [];
          if (method.args ){
            for (let j = 0; j < method.args.length; j++) {
              let arg = method.args[j];
              args.push(arg.type+' '+arg.name);
            }
          }
          javaCodes.push('\t public '+method.returnType +' '+method.name +'('+args.join(',')+'){}');
        }
        javaCodes.push('\n}')
        this.currentClassStruct= '```java\n'+javaCodes.join('\n\n')+'\n```';
      },
      beginUpload(){
        this.dialog.show = true
        this.dialog.input.classloaderName = this.input.classloader;
      },
      handleChangeClassloader(cl){
        this.input.classloader = cl;
        core.classloaderLoadedClasses(cl).then(res => {
          this.loadedClasses = res.data;
        })
      },
      checkUploadFile(file){
        let filename = file.name;
        let pos = filename.lastIndexOf('.');
        if (pos === -1){
          alert('必须传 class , zip , java 类的文件')
        }
        let extension = filename.substring(pos+1,filename.length)
        if (extension !== 'class' && extension !== 'zip' && extension !== 'java'){
          alert('必须传 class , zip , java 类的文件')
        }

        return true;
      },
      // 上传标准的类结构 class file
      uploadWholeClassFile(data){
        let file = data.file;

        let filename = file.name;
        let pos = filename.lastIndexOf('.');
        let baseName = filename.substring(0,pos);

        let classloaderName = this.dialog.input.classloaderName;
        if (!classloaderName){
          this.$message('请输入类加载器名称');
          return ;
        }

        let formData = new FormData()
        formData.append('file',file,filename);
        formData.append('classloaderName',classloaderName);
        core.uploadClassesZip(formData).then(this.loadClassloaders)
      },
      // 通用上传,支持 zip 平行结构 ，单个 class , 单个 java 文件
      commonUploadClassFile(data){
        let file = data.file;

        let filename = file.name;
        let pos = filename.lastIndexOf('.');
        let extension = filename.substring(pos+1,filename.length)
        let baseName = filename.substring(0,pos);
        let classloaderName = this.dialog.input.classloaderName;
        if (!classloaderName){
          this.$message('请输入类加载器名称');
          return ;
        }

        let formData = new FormData()
        formData.append('file',file,filename);
        formData.append('classloaderName',classloaderName);

        switch (extension){
          case 'zip':
            core.uploadClassesZipSimple(formData).then(this.loadClassloaders);
            break;
          case 'class':
            core.uploadSingleClass(formData).then(this.loadClassloaders);
          case 'java':
            core.uploadSingleJavaFile(formData).then(this.loadClassloaders);
        }
      },
      loadClassloaders(){
        this.dialog.show = false;

        core.classloaders().then(res => {
          this.classloaders = res.data;
          if (res.data && res.data.length > 0){
            this.handleChangeClassloader(res.data[0]);
          }
        })
      }
    },
    mounted() {
      this.loadClassloaders();
    }
  }
</script>

<style scoped>

</style>
