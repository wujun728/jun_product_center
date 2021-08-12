<template>
  <div>
    <editor v-model="data" :init="init"></editor>
  </div>
</template>
<script>
import Editor from "@tinymce/tinymce-vue";
require("./imp.js");
import init from "./init";
import initMini from "./init_mini";

export default {
  name:"tinymce",
  components: {
    Editor
  },
  model: {
    prop: "value"
  },
  props: {
    value: {
      type: String,
      default: ""
    },
    mini: {
      type: Boolean,
      default: false
    },
    minHeight: {
      type: Number,
      default: 0
    }
  },
  computed: {
    init() {
      let resinit = {};
      if (this.mini) {
        resinit = initMini;
      } else {
        resinit = init;
      }
      if (this.minHeight) {
        resinit.min_height = this.minHeight;
      }
      return resinit;
    }
  },
  data() {
    return {
      data: this.value
    };
  },

  watch: {
    //最后赋值给表单的数据
    data: {
      handler(val, oldValue) {
        //找到图片前缀 and去掉，
        // let data = val;
        // data = data.replace(new RegExp(this.img_url, "g"), "");
        // this.$emit("update:value", data);
        this.$emit("update:value", val);
      },
      deep: true
    },
    value: {
      handler(val, oldValue) {
        this.data = val;
      },
      deep: true
    }
  }
};
</script>
