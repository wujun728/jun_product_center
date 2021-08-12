<template>
  <n-menu
    v-model:value="curretMenu"
    :options="menus"
    :expanded-keys="expandKeys"
    @update:value="handleUpdateValue"
    @update:expanded-keys="handleExpandKeys"
  ></n-menu>
</template>

<script lang="ts" setup>
  import { usePermission } from '@/store/modules/permission'
  import { ref, watch, unref, computed } from 'vue'
  import { useRouter, useRoute } from 'vue-router'
  import type { RouteLocationNormalized } from 'vue-router'
  import { set } from '@vueuse/core'

  const permissionStore = usePermission()

  const menus = computed(() => permissionStore.getMenus)

  const router = useRouter()

  const route = useRoute()

  // menu selectedKeys
  const curretMenu = ref<string>('')

  // menu expandKeys
  const expandKeys = ref<string[]>([''])

  function setMenuKeys(r: RouteLocationNormalized) {
    set(curretMenu, unref(r.fullPath))
    set(expandKeys, Array.of(route.matched[0].path))
  }

  watch(() => route, setMenuKeys, {
    immediate: true,
    deep: true
  })

  function handleUpdateValue(key: string) {
    router.push(key)
  }

  function handleExpandKeys(keys: string[]) {
    set(expandKeys, keys)
  }
</script>
