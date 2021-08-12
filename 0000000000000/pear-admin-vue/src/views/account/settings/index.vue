<template>
  <page-container>
    <a-card>
      <a-tabs v-model:activeKey="activeKey" :tab-position="tabPosition">
      <a-tab-pane key="1" tab="基本设置">Content of Tab 1</a-tab-pane>
      <a-tab-pane key="2" tab="安全设置">Content of Tab 2</a-tab-pane>
      <a-tab-pane key="3" tab="账号绑定">Content of Tab 3</a-tab-pane>
      <a-tab-pane key="4" tab="消息通知">Content of Tab 3</a-tab-pane>
      </a-tabs>
    </a-card>
  </page-container>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue'
export default defineComponent({
  name: 'index',
  setup () {
    const tabPosition = ref('left')
    const activeKey = ref('1')
    return {
      tabPosition,
      activeKey
    }
  }
})
</script>

<style scoped lang="less">

</style>
