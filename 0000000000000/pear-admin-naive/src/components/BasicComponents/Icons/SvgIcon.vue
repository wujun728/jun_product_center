<template>
  <svg
    :class="[$attrs.class, spin && 'animate-spin']"
    :style="getStyle"
    aria-hidden="true"
  >
    <use :xlink:href="symbolId" />
  </svg>
</template>

<script setup name="SvgIcon" lang="ts">
  import { computed } from 'vue'
  import type { CSSProperties } from 'vue'
  import { NotNullable } from '@/types'

  interface SvgIconProps {
    name: NotNullable<string>
    prefix?: string
    color?: string
    spin?: boolean
    size: NotNullable<string | number>
  }

  /* eslint-disable no-undef */
  // @see https://github.com/vuejs/rfcs/pull/227#issuecomment-870105222
  // defineExpose
  const props = withDefaults(defineProps<SvgIconProps>(), {
    prefix: 'icon',
    color: '#333',
    spin: false,
    size: 16
  })

  const symbolId = computed(() => `#${props.prefix}-${props.name}`)

  const getStyle = computed((): CSSProperties => {
    const { size } = props
    let s = `${size}`
    s = `${s.replace('px', '')}px`
    return {
      width: s,
      height: s
    }
  })
</script>

<style scoped lang="less">
  .svg-icon-spin {
    animation: loadingCircle 1s infinite linear;
  }
</style>
