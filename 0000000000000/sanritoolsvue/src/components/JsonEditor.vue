<template>
    <div class="json-container">
      <el-input
        type="textarea"
        :rows="10"
        placeholder="JSON 字符串内容"
        v-model="jsonText">
      </el-input>
      <small class="text-forestgreen margin-top" style="display: block" >如果修改了上方的数据,请重新验证 JSON; 修改下方的数据会自动同步</small>
      <el-button-group  class="padding-top padding-bottom">
        <el-button plain size="small" icon="el-icon-bottom" @click="checkJSON">验证 JSON </el-button>
        <el-button plain size="small" icon="el-icon-bottom" @click="innerJSON">innerJSON</el-button>
      </el-button-group>
      <vue-json-editor :expandedOnStart="true" class="editor-wrapper" :style="'height:'+editorHeight+'px'" ref="editor" v-model="jsonObj" :show-btns="false" lang="zh" @json-change="changeJson"  />
    </div>
</template>

<script>
  import vueJsonEditor from 'vue-json-editor'

  export default {
    name: 'index',
    components:{ vueJsonEditor },
    props: {
      json: {
        type: [Object,Array]
      }
    },
    data(){
      return {
        jsonText: null,
        jsonObj: null
      }
    },
    mounted(){
      this.jsonText = JSON.stringify(this.json);
      this.jsonObj = this.json;

      // this.$refs.editor.expandAll();
    },
    methods:{
      innerJSON(){
        if (this.jsonObj.constructor === Array){
          let newArray = [];
          for (let i = 0; i < this.jsonObj.length; i++) {
            newArray.push(JSON.parse(this.jsonObj[i]));
          }
          this.jsonObj = newArray;
          this.jsonText = JSON.stringify(this.jsonObj);
        }
      },
      changeJson(json){
        this.jsonText = JSON.stringify(json);
        this.jsonObj = json;
        this.$emit('change',this.jsonObj)
      },
      checkJSON(){
        try{
          this.jsonObj = JSON.parse(this.jsonText)
          this.$emit('change',this.jsonObj)
        }catch (e) {
          this.$message({type:'error',message:'JSON 验证失败 '+ e})
        }

      }
    },
    watch:{
      json(json){
        this.jsonText = JSON.stringify(json);
        this.jsonObj = json;
      }
    },
    computed:{
      editorHeight(){
        return document.body.clientHeight - 280;
      }
    }
  }
</script>

<style scoped>
  .json-container{

  }
  .buttons{
    padding: 10px 0 ;
  }

  .editor-wrapper{
    position: relative;
  }

  /deep/ .jsoneditor-vue{
    position: absolute;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
  }
</style>


