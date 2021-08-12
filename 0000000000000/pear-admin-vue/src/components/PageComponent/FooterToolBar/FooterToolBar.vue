<template>
  <div class="app-footer-tool-bar" :style="style">
    <div class="app-footer-tool-bar-left">
      <slot name="left">{{ left }}</slot>
    </div>
    <div class="app-footer-tool-bar-right">
      <slot></slot>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, computed } from 'vue'
import { useStore } from 'vuex'

export default defineComponent({
  name: 'FooterToolBar',
  props: {
    left: {
      type: [String, Object]
    }
  },
  setup () {
    const store = useStore()
    const collapsed = computed(() => store.state.layout.collapsed)
    const style = computed(() => {
      return {
        width: !collapsed.value ? 'calc(100% - 208px)' : 'calc(100% - 48px)'
      }
    })
    return {
      style
    }
  }
})
</script>

<style scoped lang="less">
.app-footer-tool-bar {
  position: fixed;
  right: 0;
  bottom: 0;
  z-index: 99;
  display: flex;
  align-items: center;
  width: 100%;
  padding: 0 24px;
  line-height: 44px;
  background: #fff;
  border-top: 1px solid hsv(0, 0, 94%);
  box-shadow: 0 -2px 8px rgba(0, 0, 0, 0.15);
  transition: width 0.3s cubic-bezier(0.645, 0.045, 0.355, 1);

  &-left {
    flex: 1
  }

  &-right {
    > * {
      margin-right: 8px;
      &:last-child {
        margin: 0;
      }
    }
  }
}
</style>
