<template>
  <div class="app-setting-drawer">
    <a-drawer
      title="应用设置"
      placement="right"
      v-model:visible="visible"
      :closable="false"
    >
      <div class="app-setting-drawer-content">
        <h3>主题色</h3>
        <div class="app-setting-drawer-content-colors">
          <div
            class="app-setting-drawer-content-colors-item"
            v-for="(color, key) in themeColors"
            :key="key"
            :style="{background: color.color}"
            @click="changePrimaryColor(color.color)"
            :title="color.colorName"
          >
            <CheckOutlined v-if="primaryColor === color.color" style="color: #ffffff;"/>
          </div>
        </div>
      </div>
      <template #handle>
        <div class="app-setting-drawer-btn" @click="toggle">
          <SettingOutlined v-if="!visible"/>
          <CloseOutlined v-else/>
        </div>
      </template>
    </a-drawer>
  </div>
</template>

<script lang="ts">
import { defineComponent, Ref, ref, computed, unref } from 'vue'
import { SettingOutlined, CloseOutlined, CheckOutlined } from '@ant-design/icons-vue'
import { presetPrimaryColors } from '@ant-design/colors'
import themeColor from '@/themes/colorChange'
import { message } from 'ant-design-vue'
import config from '@/config/pear.config'
import { useStore } from 'vuex'

export default defineComponent({
  name: 'SettingDrawer',
  components: {
    SettingOutlined,
    CloseOutlined,
    CheckOutlined
  },
  setup () {
    const store = useStore()
    const visible: Ref = ref<boolean>(false)
    const primaryColor: Ref = ref<string>(unref(computed(() => store.getters['app/primaryColor'])))

    const themeColors = Object.keys(presetPrimaryColors).map(it => {
      return {
        colorName: it,
        color: presetPrimaryColors[it]
      }
    })
    themeColors.unshift({
      colorName: 'pear-admin',
      color: config.primaryColor
    })

    const toggle = () => {
      visible.value = !visible.value
    }

    const changePrimaryColor = (color: string) => {
      const togglePrimary = message.loading('正在切换主题...', 1)
      themeColor.changeColor(color).then(() => {
        togglePrimary()
        primaryColor.value = color
        store.dispatch('app/changePrimaryColor', color)
      })
    }

    return {
      visible,
      toggle,
      themeColors,
      primaryColor,
      changePrimaryColor
    }
  }
})
</script>

<style scoped lang="less">
@import '~@/themes/pear-theme-vars.less';

.app-setting-drawer {
  &-content {
    width: 100%;
    height: auto;

    &-colors {
      width: 100%;
      height: auto;
      display: flex;
      flex-direction: row;
      justify-content: flex-start;
      align-content: flex-start;
      flex-wrap: wrap;

      &-item {
        width: 20px;
        height: 20px;
        border-radius: 2px;
        text-align: center;
        line-height: 20px;
        margin: 8px 8px 0 0;
        cursor: pointer;
      }
    }
  }

  &-btn {
    position: absolute;
    top: 25%;
    background: @primary-color;
    color: @white;
    border-radius: @border-radius-base 0 0 @border-radius-base;
    width: 48px;
    height: 48px;
    right: 256px;
    display: flex;
    justify-content: center;
    align-items: center;
    cursor: pointer;
    pointer-events: auto;
    z-index: 1001;
    text-align: center;
    font-size: 16px;
  }
}
</style>
