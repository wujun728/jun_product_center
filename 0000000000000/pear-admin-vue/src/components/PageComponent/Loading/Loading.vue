<template>
  <div class="app-route-loading">
    <a-spin
      size="large"
    ></a-spin>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'loading'
})
</script>

<style scoped lang="less">
.app-route-loading {
  width: 100%;
  min-height: 500px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}
</style>
