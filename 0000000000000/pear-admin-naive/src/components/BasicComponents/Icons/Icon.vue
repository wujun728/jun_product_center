<template>
  <SvgIcon
    v-if="isSvgIcon"
    :size="size"
    :name="getSvgIcon"
    :class="[$attrs.class]"
    :spin="spin"
  />
  <span
    v-else
    ref="elRef"
    :class="[$attrs.class, spin && 'animate-spin']"
    :style="getWrapStyle"
  >
  </span>
</template>

<script setup name="Icon" lang="ts">
  import SvgIcon from '@/components/BasicComponents/Icons/SvgIcon.vue'
  import Iconify from '@purge-icons/generated'

  import { ref, computed, unref, nextTick, onMounted, watch } from 'vue'
  import type { CSSProperties } from 'vue'
  import { NotNullable } from '@/types'

  const SVG_END_WITH_FLAG = '.svg'

  interface IconProps {
    icon: NotNullable<string>
    color?: string
    size?: NotNullable<string | number>
    spin?: boolean
    prefix?: string
  }

  /* eslint-disable no-undef */
  const props = withDefaults(defineProps<IconProps>(), {
    size: 16,
    spin: false,
    prefix: ''
  })

  const elRef = ref<HTMLElement | null>(null)

  const isSvgIcon = computed(() => props.icon.endsWith(SVG_END_WITH_FLAG))
  const getSvgIcon = computed(() => props.icon.replace(SVG_END_WITH_FLAG, ''))
  const getIconRef = computed(
    () => `${props.prefix ? props.prefix + ':' : ''}${props.icon}`
  )

  const update = async () => {
    if (unref(isSvgIcon)) return

    const el = unref(elRef)
    if (!el) return

    await nextTick()
    const icon = unref(getIconRef)
    if (!icon) return

    const svg = Iconify.renderSVG(icon, {})
    if (svg) {
      el.textContent = ''
      el.appendChild(svg)
    } else {
      const span = document.createElement('span')
      span.className = 'iconify'
      span.dataset.icon = icon
      el.textContent = ''
      el.appendChild(span)
    }
  }

  const getWrapStyle = computed((): CSSProperties => {
    const { size, color } = props
    let fs = size
    if (typeof size === 'string') {
      fs = parseInt(size, 10)
    }

    return {
      fontSize: `${fs}px`,
      color: color,
      display: 'inline-flex'
    }
  })

  watch(() => props.icon, update, { flush: 'post' })

  onMounted(update)
</script>

<style lang="less"></style>
