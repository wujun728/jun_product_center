<template>
  <div id="app">
    <router-view />
    <theme-picker />
  </div>
</template>

<script>
import ThemePicker from "@/components/ThemePicker";

export default {
  name: "App",
  components: { ThemePicker },
  mounted() {
    this.$nextTick(() => {
      this.$store.dispatch("initWebSocket", { component: this });
      this.$store.dispatch("registerModule");
    });
  },
};
</script>
<style scoped>
#app .theme-picker {
  display: none;
}
</style>
