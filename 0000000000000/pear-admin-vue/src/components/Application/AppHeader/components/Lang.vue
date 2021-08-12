<template>
  <a-dropdown
    placement="bottomRight"
  >
    <template #overlay>
      <a-menu
        @click="toggleLang"
        :selectedKeys="selectedKeys"
      >
        <a-menu-item key="zh-CN">
          🇨🇳 简体中文
        </a-menu-item>
<!--        <a-menu-item key="zh-TW">-->
<!--          🇭🇰 繁体中文-->
<!--        </a-menu-item>-->
        <a-menu-item key="en-US">
          🇺🇸 English
        </a-menu-item>
      </a-menu>
    </template>
    <span class="action">
      <SvgIcon name="lang" style="font-size: 16px;"></SvgIcon>
    </span>
  </a-dropdown>
</template>

<script lang="ts">
import { computed, defineComponent, ref, unref } from 'vue'
import { loadLocaleMessages } from '@/locales/i18n'
import { i18n } from '@/locales'
import { useStore } from 'vuex'
export default defineComponent({
  name: 'Lang',
  setup () {
    const store = useStore()
    const defaultLang = computed(() => store.getters['app/language'])
    const selectedKeys = ref([unref(defaultLang)])
    const toggleLang = async ({ key }) => {
      selectedKeys.value = [key]
      await loadLocaleMessages(i18n, key)
      await store.dispatch('app/setLanguage', key)
    }
    return {
      selectedKeys,
      toggleLang
    }
  }
})
</script>

<style scoped lang="less">
.action {
  display: flex;
  align-items: center;
  height: 100%;
  padding: 0 12px;
  cursor: pointer;
  transition: all 0.3s;

  .avatar {
    margin: 20px 8px 20px 0;
  }

  > span {
    vertical-align: middle;
  }

  &:hover {
    background: rgba(0, 0, 0, 0.025);
  }

  .name {
    color: black;
  }
}
</style>
