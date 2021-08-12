<template>
  <page-container>
    <a-row :gutter="15">
      <a-col :span="6">
        <a-card></a-card>
      </a-col>
      <a-col :span="18">
        <a-card></a-card>
      </a-col>
    </a-row>
  </page-container>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
export default defineComponent({
  name: 'index'
})
</script>

<style scoped lang="less">

</style>
