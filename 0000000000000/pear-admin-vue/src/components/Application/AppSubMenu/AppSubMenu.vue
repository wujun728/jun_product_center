<template>
  <a-sub-menu
    v-if="!route?.meta?.hidden"
    :key="route.name"
    v-bind="$attrs"
  >
    <template #title>
        <span>
          <app-icon :icon-name="route?.meta?.icon" />
<!--          <span>{{ route.meta.title }}</span>-->
          <span>{{ t(route.meta.i18nTitle) }}</span>
        </span>
    </template>
    <template v-for="item in route.children" :key="item.name">
      <template v-if="!item.children">
        <a-menu-item :key="item.name">
          <router-link :to="{name: item.name}">
            <app-icon :icon-name="item?.meta?.icon" />
<!--            <span>{{ item?.meta?.title }}</span>-->
            <span>{{ t(item?.meta?.i18nTitle) }}</span>
          </router-link>
        </a-menu-item>
      </template>
      <template v-else>
        <app-sub-menu :route="item" :key="item.name" />
      </template>
    </template>
  </a-sub-menu>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import AppIcon from '@/components/Application/AppIcon/AppIcon.tsx'
import { useI18n } from 'vue-i18n'

export default defineComponent({
  name: 'AppSubMenu',
  components: {
    AppIcon
  },
  props: {
    route: {
      type: Object,
      required: true
    }
  },
  setup () {
    const { t } = useI18n()
    return {
      t
    }
  }
})
</script>

<style scoped lang="less">

</style>
