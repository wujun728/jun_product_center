<template>
  <loading v-if="loading"></loading>
  <router-view v-else v-slot="{ Component }">
    <!-- todo: 添加动画 -->
    <transition>
      <component :is="Component"></component>
    </transition>
  </router-view>
</template>

<script lang="ts">
import { defineComponent, computed } from 'vue'
import { useStore } from 'vuex'

export default defineComponent({
  name: 'AppContent',
  setup () {
    const store = useStore()
    const loading = computed(() => store.state.app.routerLoading)
    return {
      loading
    }
  }
})
</script>

<style scoped lang="less">

</style>
