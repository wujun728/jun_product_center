<template>
  <div :class="classObj" class="app-wrapper">
    <div v-if="device==='mobile'&&sidebar.opened" class="drawer-bg" @click="handleClickOutside" />
    <sidebar class="sidebar-container" />
    <div :class="{hasTagsView:needTagsView}" class="main-container">
      <div :class="{'fixed-header':fixedHeader}">
        <navbar />
        <tags-view v-if="needTagsView" />
      </div>
      <app-main />
      <div class="footer">© 2020 <a target="_blank" href="https://mrbird.cc">MrBird</a> - FEBS</div>
      <right-panel v-if="showSettings">
        <settings />
      </right-panel>
    </div>
  </div>
</template>

<script>
import RightPanel from '@/components/RightPanel'
import { AppMain, Navbar, Settings, Sidebar, TagsView } from './components'
import ResizeMixin from './mixin/ResizeHandler'

export default {
  name: 'Layout',
  components: {
    AppMain,
    Navbar,
    RightPanel,
    Settings,
    Sidebar,
    TagsView
  },
  mixins: [ResizeMixin],
  computed: {
    sidebar() {
      return this.$store.state.setting.sidebar
    },
    device() {
      return this.$store.state.setting.device
    },
    showSettings() {
      return this.$store.state.setting.settingBar.opened
    },
    needTagsView() {
      return this.$store.state.setting.multipage
    },
    fixedHeader() {
      return this.$store.state.setting.fixHeader
    },
    classObj() {
      return {
        hideSidebar: !this.sidebar.opened,
        openSidebar: this.sidebar.opened,
        withoutAnimation: this.sidebar.withoutAnimation,
        mobile: this.device === 'mobile'
      }
    }
  },
  methods: {
    handleClickOutside() {
      this.$store.commit('setting/closeSidebar', { withoutAnimation: false })
    }
  }
}
</script>

<style lang="scss" scoped>
  @import "~@/styles/mixin.scss";
  @import "~@/styles/variables.scss";

  .app-wrapper {
    @include clearfix;
    position: relative;
    height: 100%;
    width: 100%;

    &.mobile.openSidebar {
      position: fixed;
      top: 0;
    }
  }

  .drawer-bg {
    background: #000;
    opacity: 0.3;
    width: 100%;
    top: 0;
    height: 100%;
    position: absolute;
    z-index: 999;
  }

  .fixed-header {
    position: fixed;
    top: 0;
    right: 0;
    z-index: 9;
    width: calc(100% - #{$sideBarWidth});
    transition: width 0.28s;
  }

  .hideSidebar .fixed-header {
    width: calc(100% - 54px)
  }

  .mobile .fixed-header {
    width: 100%;
  }
  .footer {
    position: fixed;
    bottom: 0;
    right: 0;
    text-align: center;
    height: 2.4rem;
    box-shadow: 0 2px 10px 0 rgba(0, 0, 0, 0.1);
    font-size: 13px;
    background: #fff;
    width: calc(100% - #{$sideBarWidth});
    display: block;
    z-index: 999;
    color: #606266;
    line-height: 2.4rem;
  }
  .hideSidebar .footer  {
    width: calc(100% - 54px)
  }

  .mobile .footer  {
    width: 100%;
  }
</style>
