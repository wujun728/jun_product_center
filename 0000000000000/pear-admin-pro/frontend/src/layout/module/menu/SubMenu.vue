<template>
  <template v-if="!item.hidden">
    <a-menu-item
      v-if="
        item.children &&
        item.children.length == 1 &&
        item.meta.alwaysShow != true
      "
      :key="resolvePath(item.path, true)"
      @click="handleFoldSideBar"
    >
      <router-link :to="item.path + item.children[0].path.startsWith('/')?item.children[0].path: '/' + item.children[0].path">
        <component :is="$antIcons[item.meta.icon]" />
        <span>{{ t(item.meta.i18n) }}</span>
      </router-link>
    </a-menu-item>

    <!-- if item.children is not null 渲染 a-sub-menu -->
    <a-sub-menu
      @click="handleFoldSideBar"
      :key="item.path"
      v-else-if="item.children && item.children.length > 0"
    >
      <template v-slot:title>
        <span>
          <component v-if="level === 0" :is="$antIcons[item.meta.icon]" />
          <span v-else><div class="indent"></div></span>
          <span>{{ t(item.meta.i18n) }}</span>
        </span>
      </template>
      <!-- 递归 item.children -->
      <sub-menu
        v-for="child in item.children"
        :key="resolvePath(child.path)"
        :item="child"
        :level="level + 1"
        :base-path="resolvePath(child.path)"
      />
    </a-sub-menu>
    <!-- if item.chilren is null 渲染 a-menu-item -->
    <a-menu-item
      @click="handleFoldSideBar"
      v-bind="$attrs"
      :key="resolvePath(item.path, true)"
      v-else
    >
      <router-link :to="resolvePath(item.path, true)">
        <component v-if="level === 0" :is="$antIcons[item.meta.icon]" />
        <span v-else><div class="indent"></div></span>
        <span>{{ t(item.meta.i18n) }}</span>
      </router-link>
    </a-menu-item>
  </template>
</template>
<script>
import path from "path";
import { computed } from "vue";
import { useStore } from "vuex";
import { useI18n } from "vue-i18n";
export default {
  emits: ["click"],
  name: "SubMenu",
  props: {
    item: {
      type: Object,
      required: true
    },
    basePath: {
      type: String,
      default: ""
    },
    level: {
      type: Number,
      required: true
    }
  },
  setup(props) {
    const { commit, getters } = useStore();
    const resolvePath = (routePath, single) => {
      if (/^(https?:|mailto:|tel:)/.test(routePath)) {
        return routePath;
      }
      if (single) {
        return props.basePath;
      }
      // 当处于 comp 模式下拼接相关路由
      return path.resolve(props.basePath, routePath);
    };
    const handleFoldSideBar = () => {
      const isComputedMobile = computed(() => getters.isMobile);
      if (isComputedMobile.value) {
        commit("layout/UPDATE_COLLAPSED", true);
      }
    };

    // i18n
    const { t } = useI18n()

    return {
      handleFoldSideBar,
      resolvePath,
      t
    };
  }
};
</script>
<style>
.indent {
  width: 15px;
  display: inline-block;
}
</style>