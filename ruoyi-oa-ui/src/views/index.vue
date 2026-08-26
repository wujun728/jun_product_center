<template>
  <div>
    <Home />
  </div>
</template>

<script>
import Home from "./home/index.vue";
import eventType from "@/utils/socket/eventType.js";

export default {
  name: "Index",
  components: { Home },
  mounted() {
    // 监听日程提醒消息
    this.$eventBus.$on(eventType[2], (payload) => {
      console.log("收到待办消息:", payload.text);
      this.handleMsg("待办消息", payload.text);
    });
    this.$eventBus.$on(eventType[3], (payload) => {
      console.log("收到日程消息:", payload.text);
      this.handleMsg("日程提醒", payload.text);
    });
  },
  beforeDestroy() {
    this.$eventBus.$off(eventType[3]);
  },
  methods: {
    handleMsg(title, msg) {
      this.$notify({
        title: title,
        message: `<div style="min-height:60px;padding: 12px 0;">${msg}</div>`,
        position: "bottom-right",
        type: "success", // 可选值：success / warning / error / info
        duration: 20000, // 单位毫秒，0-不自动关闭
        dangerouslyUseHTMLString: true,
      });
    },
  },
};
</script> 
