<template>
  <div class=""  v-loading="loading">
    <div class="fixed-top">
      <el-row class="op">
        <el-col :span="15">
          <el-input size="small" v-model="input.docUrl" @keyup.enter.native="parseDoc" placeholder="请输入你的 swagger 地址">
            <el-button size="small" slot="append" @click="parseDoc" >解析</el-button>
          </el-input>
        </el-col>
        <el-col :span="9">
          <el-button-group >
            <el-button size="small" @click="download" >下载文档</el-button>
          </el-button-group>
        </el-col>
      </el-row>
    </div>

    <el-row class="" style="padding-top: 60px">
      <el-col :span="24">
        <div v-html="htmlDoc" />
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import swagger from "../../api/swagger";

  export default {
    name: "swagger",
    data(){
      return {
        input: {
          docUrl: null
        },
        htmlDoc: null,
        loading: false
      }
    },
    methods: {
      parseDoc(){
        if (this.input.docUrl){
          this.loading = true;
          swagger.doc(this.input.docUrl).then(res => {
            this.loading = false;
            this.htmlDoc = res;
          })
        }
      },
      download(){
        if (this.input.docUrl){
          swagger.download(this.input.docUrl)
        }
      }
    }
  }
</script>

<style scoped>
  .fixed-top{
    position: fixed;
    height: 60px;
    padding: 10px;
    z-index: 2;

    width: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  .fixed-top .op{
    width: 100%;
  }
</style>
