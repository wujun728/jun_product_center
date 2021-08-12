<template>
  <a-menu
    :openKeys="openKeys"
    :selectedKeys="selectedKeys"
    :inlineIndent="16"
    mode="inline"
    theme="dark"
    @openChange="handleOpen"
    @select="handleSelect"
  >
    <template v-for="route in menuList" :key="route.name">
      <template v-if="!route.meta.hidden">
        <template v-if="!route.children">
          <a-menu-item :key="route.name">
            <router-link :to="{name: route.name}">
              <AppIcon :icon-name="route?.meta?.icon"></AppIcon>
<!--              <span>{{ route.meta.title }}</span>-->
              <span>{{ t(route.meta.i18nTitle) }}</span>
            </router-link>
          </a-menu-item>
        </template>
        <template v-else>
          <app-sub-menu :route="route" :key="route.name"/>
        </template>
      </template>
    </template>
  </a-menu>
</template>

<script lang="ts">
import { computed, defineComponent, reactive, watch, toRefs } from 'vue'
import AppSubMenu from '@/components/Application/AppSubMenu/AppSubMenu.vue'
import AppIcon from '@/components/Application/AppIcon/AppIcon.tsx'
import { useRoute } from 'vue-router'
import { useStore } from 'vuex'
import { useI18n } from 'vue-i18n'

export default defineComponent({
  name: 'AppMenu',
  components: {
    AppSubMenu,
    AppIcon
  },
  emits: ['update:openKeys', 'update:selectedKeys'],
  setup () {
    const store = useStore()
    const route = useRoute()
    const { t } = useI18n()
    const menuState = reactive({
      collapsed: computed(() => store.state.layout.collapsed),
      openKeys: computed(() => store.state.layout.openKeys),
      selectedKeys: computed(() => store.state.layout.selectedKeys),
      preOpenKeys: [],
      menuList: computed(() => store.state.layout.menuList)
    })

    const handleOpen = (openKeys: string[]) => {
      store.dispatch('layout/setOpenKeys', openKeys)
    }

    const handleSelect = ({ selectedKeys }: { selectedKeys: string[] }) => {
      store.dispatch('layout/setSelectedKeys', selectedKeys)
    }

    // route change
    watch(() => route.name, () => {
      const matched = route.matched
      // todo: types
      // eslint-disable-next-line
      const openKeys: any = matched.map(it => it.name)
      store.dispatch('layout/setSelectedKeys', openKeys)
      if (!menuState.collapsed) {
        store.dispatch('layout/setOpenKeys', openKeys)
      }
    }, { immediate: true })

    // openKeys
    watch(
      () => menuState.openKeys,
      (val, oldVal) => {
        menuState.preOpenKeys = oldVal
      }
    )

    watch(
      () => menuState.collapsed,
      collapsed => {
        const openKeys = collapsed ? [] : menuState.preOpenKeys
        store.dispatch('layout/setOpenKeys', openKeys)
      }
    )

    return {
      t,
      ...toRefs(menuState),
      handleOpen,
      handleSelect
    }
  }
})
</script>

<style lang="less">
</style>
