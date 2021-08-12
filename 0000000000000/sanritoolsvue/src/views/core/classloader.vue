<template>
  <div class="app-container">
    <el-row>
      <el-col :span="3">
        <el-input size="small" v-model="input.classloaderName" />
      </el-col>
        <el-upload style="display: inline-block" action="#" :show-file-list="false" :http-request="commonUploadClassFile" :before-upload="checkUploadFile">
          <el-button slot="trigger" size="small" type="primary">上传 DTO</el-button>
        </el-upload>
        <el-upload style="display: inline-block" class="margin-left" action="#" :show-file-list="false" :http-request="uploadWholeClassFile" :before-upload="checkUploadFile">
          <el-button slot="trigger" size="small" type="primary">上传标准 DTO</el-button>
        </el-upload>
    </el-row>

    <el-row class="margin-top">
      <el-col :span="6">
        <list-group :list="classloaders" :props="{enableDelete:true}" @click-item="clickClassloader" ></list-group>
      </el-col>
      <el-col :span="12" class="margin-left">
        <list-group :list="loadedClasses" @click-item="loadClassStruct" ></list-group>
      </el-col>
    </el-row>

    <el-drawer
            title="类结构"
            :visible.sync="drawer.visiable"
            :with-header="false"
            size="60%">
      <p>只是一个简单示例,并不是真实的类情况</p>
      <mavon-editor :editable="false"
                    :subfield="false"
                    :toolbarsFlag="false"
                    defaultOpen="preview"
                    style="height: 100%;width: 100%;"
                    v-model="drawer.classStructJava" />
    </el-drawer>
  </div>
</template>

<script>
  import core from '../../api/core'
  import ListGroup from '@/components/ListGroup'
  import { mavonEditor } from 'mavon-editor'
  import 'mavon-editor/dist/css/index.css'

  export default {
    name: 'classloader',
    components:{ ListGroup,mavonEditor },
    data(){
      return {
        input: {
          classloaderName: null,
          className : null
        },
        classloaders:[],
        loadedClasses:[],
        drawer: {
          visiable: false,
          input: {},
          classStruct: null,
          classStructJava: null
        }
      }
    },
    created() {
      this.loadClassloaders();
    },
    methods:{
      loadClassStruct(value){
        this.input.className = value;
        core.classStruct(this.input.classloaderName,this.input.className).then(res => {
          this.drawer.visiable = true;
          this.drawer.classStruct = res.data;
        })
      },
      loadClassloaders(){
        core.classloaders().then(res => {
          this.classloaders = res.data;
          if (this.classloaders && this.classloaders.length > 0){
            this.clickClassloader(this.classloaders[0]);
          }
        })
      },
      clickClassloader(value,index){
        this.input.classloaderName = value;

        core.listLoadedClasses(value).then(res => {
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

        let formData = new FormData()
        formData.append('file',file,filename);
        formData.append('classloaderName',this.input.classloaderName);
        core.uploadClassesZip(formData).then(this.loadClassloaders)
      },
      // 通用上传,支持 zip 平行结构 ，单个 class , 单个 java 文件
      commonUploadClassFile(data){
        let file = data.file;

        let filename = file.name;
        let pos = filename.lastIndexOf('.');
        let extension = filename.substring(pos+1,filename.length)
        let baseName = filename.substring(0,pos);

        let formData = new FormData()
        formData.append('file',file,filename);
        formData.append('classloaderName',this.input.classloaderName);

        switch (extension){
          case 'zip':
             core.uploadClassesZipSimple(formData).then(this.loadClassloaders);
            break;
          case 'class':
             core.uploadSingleClass(formData).then(this.loadClassloaders);
          case 'java':
             core.uploadSingleJavaFile(formData).then(this.loadClassloaders);
        }
      }
    },
    watch:{
      'drawer.classStruct':{
        handler(){
          let javaCodes = [];
          let struct = this.drawer.classStruct;
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
          this.drawer.classStructJava = '```java\n'+javaCodes.join('\n\n')+'\n```';
        }
      }
    }
  }
</script>

<style scoped>
  .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

</style>
