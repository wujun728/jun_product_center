<!--
    /**
     * 树形下拉选择组件，下拉框展示树形结构，提供选择某节点功能，方便其他模块调用
     * 调用示例：
     * <Upload
              :v-model="addForm.img"//文件对象
              :limit="6"//文件数量现在
              :showFileList="true"// 是否显示文件列表
     *              />
     */
-->
<template>

    <el-upload
      :action="serverUrl"
      :before-upload="handleBeforeUpload"
      :headers='headers'
      v-on:on-progress="handlePreview"
      :on-success="handleUploadSuccess"
      :on-error="handleUploadError"
      :on-change="handleChange"
      :on-remove="handleRemove"
      :before-remove="beforeRemove"
      :show-file-list="showFileList"
      :limit="limit"
      :auto-upload="true"
      :file-list="fileList"
      list-type="picture"
      accept=".jpg,.jpeg,.png,.gif,.bmp,.pdf,.JPG,.JPEG,.PBG,.GIF,.BMP,.PDF,.mp4"
    >
      <el-button v-if="flag" slot="trigger" type="primary">选择</el-button>
      <el-button :type="buttonType" :loading="loading">{{buttonTxt}}<i class="el-icon-upload el-icon--right"></i></el-button>
      <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
    </el-upload>
    <!-- <el-dialog :visible.sync="dialogVisible">
        <img width="100%" :src="dialogImageUrl" alt="">
    </el-dialog> -->
 </template>
<script>
import { setStore,getStore } from '/utils/storage'

export default {
  model: {
    prop: "value",
    event: "handleJSONSuccess",
  },
  props: {
    buttonTxt: {
      type: String,
      default: "上传"
    },
    flag: {
      type:Boolean,
      default:false
    },
    action: {
      type: String,
      required: false
    },
    buttonType: {
      type: String,
      default: "primary"
    },
    value: Array,
    name: null,
    uploadId:null,
    showFileList:false,
    listType:"picture",//text/picture/picture-card 文件列表显示
    onSuccess: Function,
    onProgress: Function,
    beforeUpload: Function,
    limit: "", // 图片数量限制
    fileType: null // 文件类型限制用|分隔 如png|jpg|jpeg|gif
  },
  data() {
    return {
      loading: false,
      dialogVisible: false,
      dialogImageUrl: "",
      serverUrl: "/api/file/image_upload", // 这里写你要上传的图片服务器地址
      fileList: [],
      headers: {
        shuogesha_auth_id: getStore("shuogesha_auth_id") ? getStore("shuogesha_auth_id") : ''
      },
    };
  },
  watch: {
    value: function() {
      this.fileList = this.value;
    }
  },
  mounted: function() {
    this.fileList = this.value;
  },
  methods: {
    handleBeforeUpload(file) {
      const fileName = file.name;
      const ext = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
      if (this.fileType) {
        // 限制文件格式
        if (this.fileType.indexOf(ext) < 0) {
          this.$message.error("文件类型不合法，请上传" + this.fileType);
          return false;
        }
      }
      const is10M = file.size / 1024 / 1024 < 10; // 限制小于10M
      if (!is10M) {
        this.$message.error('大小不可超过10M');
        return false;
      }
      this.loading = true; // 开启上传中状态
      if (this.beforeUpload) {
        const arg = [].slice.call(arguments);
        if (this.uploadId) {
          arg.push(this.uploadId);
        }
        this.beforeUpload.apply(this, arg);
      }
    },
    beforeRemove(file, fileList) {
      // return this.$confirm('确定移除 ${file.name}？');
    },
    handleChange(file, fileList) {
      this.fileList = fileList.slice(-3);
      // this.$emit("handleJSONSuccess", fileList);
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
      this.$emit("handleJSONSuccess", fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    handlePreview(file){
      this.$emit('on-progress');
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
     },
    handleUploadSuccess(res, file,fileList) {
       if (res.code == 200) {//重新绑定数据
         file.url= res.data;
         this.$emit("handleJSONSuccess", this.fileList);
       }
      this.loading = false; // 关闭上传中状态
      if (this.onSuccess) {
        const arg = [].slice.call(arguments);
        if (this.uploadId) {
          arg.push(this.uploadId);
        }
        this.onSuccess.apply(this, arg);
      }
    },
    handleUploadError() {
      this.loading = false; // 关闭上传中状态
      this.$message.error("上传失败");
    },
  }
};
</script>
<style scoped>
</style>
