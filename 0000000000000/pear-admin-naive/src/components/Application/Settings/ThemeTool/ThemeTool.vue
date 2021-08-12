<template>
  <div>
    <n-popselect
      :value="value"
      :options="options"
      trigger="click"
      @update:value="handleToggle"
    >
      <n-button round>主题: {{ currentThemeName }}</n-button>
    </n-popselect>
  </div>
</template>

<script lang="ts" name="ThemeTool" setup>
  import { computed, ref, watch } from 'vue'
  import { useAppStore } from '@/store/modules/app'
  import type { ThemeName } from '@/store/modules/app'
  import useUIConfig from '@/config/useUIConfigs'

  const options = [
    {
      label: '亮色',
      value: 'light'
    },
    {
      label: '暗色',
      value: 'dark'
    },
    {
      label: '跟随系统',
      value: 'auto'
    }
  ]

  const appStore = useAppStore()
  const { toggleTheme } = useUIConfig()

  const value = ref<ThemeName>('auto')

  watch(
    () => appStore.getTheme,
    (theme: ThemeName) => {
      value.value = theme
    },
    {
      immediate: true
    }
  )

  const currentThemeName = computed(() => {
    const item = options.find((item) => item.value === value.value)
    return item?.label
  })

  function handleToggle(key: string) {
    toggleTheme(key as ThemeName)
  }
</script>
