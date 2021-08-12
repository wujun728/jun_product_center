<template>
  <a-layout id="layout" :class="[theme, layout]">
    <!-- 侧边菜单 -->
    <div
      v-if="isMobile && !collapsed"
      class="layout_mobile_mask"
      @click="closeSideBar"
    />
    <a-layout-sider
      v-if="layout != 'layout-head'"
      :width="sideWitch"
      :collapsed="collapsed"
      :trigger="null"
      :collapsedWidth="collapsedWidth"
      collapsible
      :class="[
        fixedSide ? 'fixed-side' : '',
        isMobile && 'layout_mobile',
        collapsed && 'layout_collapse'
      ]"
    >
      <div class="pear-layout-left-sider">
        <!-- 菜单图标 -->
        <Logo v-if="logo"></Logo>
        <!-- 垂直菜单 -->
        <Menu></Menu>
      </div>
    </a-layout-sider>
    <!-- 右边区域 -->
    <a-layout>
      <!-- 顶部区域 -->
      <a-layout-header>
        <Header></Header>
      </a-layout-header>
      <!-- 中心区域 -->
      <a-layout-content :class="[fixedHeader ? 'fixedHeader' : '', tab ? 'muiltTab' : '']">
        <!-- 选项卡页面 -->
        <Tab v-if="tab"></Tab>
        <!-- 内容区域 -->
        <Content :style="{ overflow: fixedHeader ? 'auto' : '' }"></Content>
        <!-- 设置页面 -->
        <Setup></Setup>
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>
<script>
import { computed } from "vue";
import { useStore } from "vuex";
import Content from "./module/content/index.vue";
import Header from "./module/header/index.vue";
import Logo from "./module/logo/index.vue";
import Tab from "./module/tab/index.vue";
import Setup from "./module/setup/index.vue";
import Menu from "./module/menu/index.vue";
export default {
  components: {
    Menu,
    Content,
    Header,
    Logo,
    Tab,
    Setup,
  },
  setup() {
    const { getters, commit } = useStore();
    const layout = computed(() => getters.layout);
    const collapsed = computed(() => getters.collapsed);
    const logo = computed(() => getters.logo);
    const tab = computed(() => getters.tab);
    const theme = computed(() => getters.theme);
    const sideWitch = computed(() => getters.sideWitch);
    const fixedHeader = computed(() => getters.fixedHeader);
    const fixedSide = computed(() => getters.fixedSide);
    const isMobile = computed(() => getters.isMobile);
    const collapsedWidth = computed(() => getters.collapsedWidth);
    const closeSideBar = () => {
      const isComputedMobile = computed(() => getters.isMobile);
      if (isComputedMobile.value) {
        commit("layout/TOGGLE_SIDEBAR", true);
      }
    };
    const handleFoldSideBar = () => {
      const isComputedMobile = computed(() => getters.isMobile);
      const isCollapsed = computed(() => getters.collapsed);
      if (isComputedMobile.value && !isCollapsed.value) {
        commit("layout/TOGGLE_SIDEBAR");
      }
    };
    const handleLayouts = () => {
      const domWidth = document.body.getBoundingClientRect().width;
      const isLayoutMobile = domWidth !== 0 && domWidth - 1 < 992;
      commit("layout/UPDATE_ISMOBILE", isLayoutMobile);
      if (isLayoutMobile) {
        setTimeout(() => {
          handleFoldSideBar();
        }, 1000);
      }
    };
    handleLayouts();
    window.addEventListener("resize", handleLayouts);

    return {
      closeSideBar,
      isMobile,
      collapsed,
      fixedHeader,
      fixedSide,
      sideWitch,
      layout,
      theme,
      logo,
      tab,
      collapsedWidth
    };
  }
};
</script>
<style lang="less">

#layout {
  height: 100%;
  .ant-menu-inline-collapsed {
    width: auto;
  }
  .ant-menu-inline-collapsed > .ant-menu-submenu > .ant-menu-submenu-title {
    text-align: center;
  }
  @ant-menu-hieht: 48px;
  .ant-menu-vertical > .ant-menu-item,
  .ant-menu-vertical-left > .ant-menu-item,
  .ant-menu-vertical-right > .ant-menu-item,
  .ant-menu-inline > .ant-menu-item,
  .ant-menu-vertical > .ant-menu-submenu > .ant-menu-submenu-title,
  .ant-menu-vertical-left > .ant-menu-submenu > .ant-menu-submenu-title,
  .ant-menu-vertical-right > .ant-menu-submenu > .ant-menu-submenu-title,
  .ant-menu-inline > .ant-menu-submenu > .ant-menu-submenu-title {
    height: @ant-menu-hieht !important;
    line-height: @ant-menu-hieht !important;
  }

  .ant-menu-submenu-title>span{
    font-size: 13.8px!important;
  }

  .ant-menu-inline-collapsed > .ant-menu-item {
    padding: 0px !important;
    text-align: center;
  }
}
</style>
<style lang="less" scoped>
//移动端侧边栏遮罩层
.layout_mobile_mask {
  position: fixed;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  z-index: 998;
  width: 100%;
  height: 100vh;
  overflow: hidden;
  background: #000;
  opacity: 0.5;
}
//移动端导航栏布局
.layout_mobile {
  position: fixed !important;
  z-index: 999;
  &.layout_collapse {
    width: 0 !important;
    min-width: 0 !important;
    max-width: 0 !important;
    * {
      display: none !important;
      width: 0 !important;
      min-width: 0 !important;
      max-width: 0 !important;
    }
    .ant-menu-item,
    .ant-menu-submenu {
      display: none !important;
      width: 0 !important;
      min-width: 0 !important;
      max-width: 0 !important;
    }
  }
}
</style>