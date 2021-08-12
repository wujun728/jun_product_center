<template>
  <n-tabs
    v-model:value="active"
    type="card"
    :closable="list.length > 1"
    @close="handleClose"
  >
    <n-tab-pane
      v-for="(panel, index) in list"
      :key="index"
      :name="panel.path"
      :tab="panel.title"
      @click="go(panel)"
    ></n-tab-pane>
  </n-tabs>
</template>

<script setup name="AppTab" lang="ts">
  import { useTabs } from '../../../composables'

  const { active, list, to, close } = useTabs()

  const go = function (panel: any) {
    to(panel)
  }

  const handleClose = function (path: string) {
    close(path)
  }
</script>
