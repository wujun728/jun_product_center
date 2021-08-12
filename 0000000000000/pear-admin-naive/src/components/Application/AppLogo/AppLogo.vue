<template>
  <div class="flex flex-row items-center justify-center w-full h-12">
    <img v-if="collapsed" class="object-cover w-8" src="~@/assets/logo.png" />
    <n-element
      v-else
      tag="h2"
      class="
        flex flex-row
        items-center
        justify-center
        w-full
        h-full
        overflow-hidden
        text-2xl
        animate__animated animate__slideInLeft
      "
      >Pear Admin</n-element
    >
  </div>
</template>
<script setup name="AppLogo" lang="ts">
  import { toRef } from 'vue'
  interface AppLogoProps {
    collapsed: boolean
  }

  /* eslint-disable no-undef */
  const props = defineProps<AppLogoProps>()

  const collapsed = toRef(props, 'collapsed')
</script>

<style scoped lang="less"></style>
