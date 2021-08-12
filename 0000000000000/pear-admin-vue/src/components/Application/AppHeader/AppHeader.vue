<template>
  <a-layout-header
    class="app-layout-header"
    :style="{width: !collapsed ? 'calc(100% - 208px)': 'calc(100% - 48px)'}"
  >
    <div class="app-layout-header-content">
      <div style="flex: 1 1 0%;height: 100%;">
        <LeftContent/>
      </div>
      <div class="app-layout-header-content-right">
        <RightContent/>
      </div>
    </div>
  </a-layout-header>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import RightContent from './components/RightContent.vue'
import LeftContent from './components/LeftContent.vue'

export default defineComponent({
  name: 'AppHeader',
  props: {
    collapsed: {
      type: Boolean
    }
  },
  components: {
    RightContent,
    LeftContent
  }
})
</script>

<style scoped lang="less">
.app-layout-header {
  padding: 0px;
  height: 48px;
  line-height: 48px;
  width: 100%;
  z-index: 19;
  position: fixed;
  top: 0;
  right: 0;
  &-content {
    position: relative;
    display: flex;
    align-items: center;
    height: 100%;
    padding: 0 16px;
    background: #fff;
    box-shadow: 0 1px 4px rgba(0, 21, 41, .08);
    &-right {
      display: flex;
      float: right;
      margin-left: auto;
      overflow: hidden;
      height: 100%;
    }
    &-left {
      display: flex;
      float: left;
      overflow: hidden;
      height: 100%;
    }
  }
}
</style>
