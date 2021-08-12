<template>
  <n-layout-header
    class="h-12 flex flex-row justify-between items-center px-4"
    bordered
    position="absolute"
  >
    <div class="flex flex-auto">
      <slot name="headerLeft"></slot>
    </div>
    <div class="flex">
      <RightContent></RightContent>
    </div>
  </n-layout-header>
</template>

<script setup lang="ts">
  import RightContent from './RightContent.vue'
</script>

<style scoped lang="less"></style>
