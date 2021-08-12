<template>
  <div id="doc">
    <page-layout>
      <a-card bodyStyle="padding: 5px">
        <iframe id="show-iframe" frameborder="0" name="showHere" width="100%" scrolling="auto" src="http://localhost:9191/swagger-ui.html"></iframe>
      </a-card>
    </page-layout>
  </div>
</template>
<script>
import { onMounted } from "vue";
export default {
  setup() {
    // TODO iframe 动态高度
    onMounted(() => {
      const oIframe = document.getElementById("show-iframe");
      const deviceHeight = document.documentElement.clientHeight - 140;
      oIframe.style.height = deviceHeight  + "px";
      oIframe.src =  process.env.VUE_APP_API_BASE_URL + "/swagger-ui.html";
    });
  },
};
</script>
<style scoped>
iframe {
  border-width: 0px !important;
}
</style>
