<template>
  <div class="w-screen h-full">
    <n-layout
      has-sider
      class="h-screen"
      :native-scrollbar="false"
      position="absolute"
    >
      <n-layout-sider
        position="absolute"
        bordered
        class="h-screen"
        show-trigger="arrow-circle"
        collapse-mode="width"
        :width="220"
        :native-scrollbar="false"
        :collapsed="collapsed"
        @update:collapsed="toggleCollapsed"
      >
        <AppLogo :collapsed="collapsed"></AppLogo>
        <Menu></Menu>
      </n-layout-sider>
      <n-layout
        position="absolute"
        :style="{ marginLeft: collapsed ? '48px' : '220px' }"
      >
        <AppHeader />
        <AppTab class="mt-14 ml-2 mr-2" />
        <n-layout-content
          bordered
          class="mt-24 pb-28"
          content-style="padding: 14px"
          position="absolute"
        >
          <router-view></router-view>
        </n-layout-content>
        <n-layout-footer
          bordered
          class="py-4 h-auto box-border text-gray-500"
          position="absolute"
        >
          <div class="w-full h-10 flex felx-row justify-center items-center">
            <n-space>
              <a href="http://www.pearadmin.com">官网</a>
              <a href="http://www.pearadmin.com/">社区</a>
              <a href="http://forum.pearadmin.com/">论坛</a>
            </n-space>
          </div>
          <div class="w-full h-10 flex felx-row justify-center items-center">
            Copyright © {{ new Date().getFullYear() }} &nbsp;&nbsp; Pear Admin
            开源社区
          </div>
        </n-layout-footer>
      </n-layout>
    </n-layout>
  </div>
</template>
<script lang="ts" setup>
  // import Icon from '@/components/BasicComponents/Icons/Icon.vue'
  import Menu from '@/components/Application/Menu/Menu.vue'
  import AppLogo from '@/components/Application/AppLogo/AppLogo.vue'
  import AppHeader from '@/components/Application/AppHeader/AppHeader.vue'
  import AppTab from '@/components/Application/AppTab/AppTab.vue'
  import { ref } from 'vue'
  import { set } from '@vueuse/core'

  const collapsed = ref<boolean>(false)
  function toggleCollapsed(value: boolean) {
    set(collapsed, value)
  }
</script>
