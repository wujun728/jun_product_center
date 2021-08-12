<template>
  <!-- 框架顶部菜单区域 -->
  <div id="header" :class="[isMobile && 'mobile_header']">
    <template v-if="layout !== 'layout-head'">
      <!-- 左侧菜单功能项 -->
      <div class="prev-menu">
        <!-- 左侧缩进功能键 -->
        <div class="menu-item" @click="trigger()">
          <AlignLeftOutlined v-if="collapsed" />
          <!-- 左侧缩进功能键盘 -->
          <AlignRightOutlined v-else />
        </div>
        <div class="menu-item" @click="refresh">
          <!-- 刷新当前页面路由 -->
          <ReloadOutlined v-if="routerActive" />
          <LoadingOutlined v-else />
        </div>
      </div>
    </template>

    <template v-else>
      <div class="head-logo">
        <Logo></Logo>
      </div>
      <div class="head-menu">
        <Menu></Menu>
      </div>
    </template>

    <!-- 实现综合布局方式 -->
    <div v-if="layout == 'layout-comp'" class="comp-menu">
      <template :key="index" v-for="(route, index) in routes">
        <router-link
          :to="toPath(route)"
          class="menu-item"
          :class="[active === route.path ? 'is-active' : '']"
        >
          <span>{{ route.meta.title }}</span>
        </router-link>
      </template>
    </div>

    <!-- 右侧菜单功能项 基本公用 -->
    <div class="next-menu">
      <div class="menu-item" v-if="!fullscreen" @click="full(1)">
        <ExpandOutlined />
      </div>
      <div class="menu-item" v-else @click="full(2)">
        <CompressOutlined />
      </div>
      <a-dropdown class="notice-item">
        <BellOutlined />
        <template #overlay>
          <a-menu class="notice-dropdown">
            <a-tabs>
              <a-tab-pane key="1" tab="通知">
                <a-empty description="暂无通知" />
              </a-tab-pane>
              <a-tab-pane key="2" tab="公告">
                <a-empty description="暂无公告" />
              </a-tab-pane>
              <a-tab-pane key="3" tab="私信">
                <a-empty description="暂无私信" />
              </a-tab-pane>
              <a-tab-pane key="4" tab="任务">
                <a-empty description="暂无任务" />
              </a-tab-pane>
            </a-tabs>
          </a-menu>
        </template>
      </a-dropdown>
      <a-dropdown class="locale-item">
        <GlobalOutlined />
        <template #overlay>
          <a-menu @click="toggleLang" :selectedKeys="selectedKeys">
            <a-menu-item key="zh-cn"> 简体中文 </a-menu-item>
            <a-menu-item key="en-us"> English </a-menu-item>
          </a-menu>
        </template>
      </a-dropdown>
      <a-dropdown class="avatar-item">
        <a-avatar src="https://portrait.gitee.com/uploads/avatars/user/1611/4835367_Jmysy_1578975358.png"></a-avatar>
        <template #overlay>
          <a-menu class="avatar-dropdown">
            <a-menu-item key="0">
              <a-menu-item @click = "go"> 个人中心 </a-menu-item>
            </a-menu-item>
            <a-menu-divider />
            <a-menu-item key="3">
              <a-menu-item @click="logout"> 注销登录 </a-menu-item>
            </a-menu-item>
          </a-menu>
        </template>
      </a-dropdown>
      <div v-if="!isMobile" class="menu-item" @click="setting()">
        <!-- 主题设置隐显键 -->
        <MoreOutlined />
      </div>
    </div>
  </div>
</template>
<script>
import { computed, watch, ref, unref } from "vue";
import { useStore } from "vuex";
import Menu from "../menu/index.vue";
import Logo from "../logo/index.vue";
import { useRoute, useRouter } from "vue-router";
import _path from "path";
import i18n from "@/locale";
import {
  AlignLeftOutlined,
  AlignRightOutlined,
  MoreOutlined,
  ExpandOutlined,
  CompressOutlined,
  ReloadOutlined,
  GlobalOutlined,
  BellOutlined,
  LoadingOutlined,
} from "@ant-design/icons-vue";
import { loadLocaleMessages } from "@/locale";
export default {
  components: {
    AlignLeftOutlined,
    AlignRightOutlined,
    MoreOutlined,
    ExpandOutlined,
    CompressOutlined,
    ReloadOutlined,
    GlobalOutlined,
    Menu,
    Logo,
    BellOutlined,
    LoadingOutlined,
  },

  methods: {
    full: function (num) {
      num = num || 1;
      num = num * 1;
      var docElm = document.documentElement;
      switch (num) {
        case 1:
          if (docElm.requestFullscreen) {
            docElm.requestFullscreen();
          } else if (docElm.mozRequestFullScreen) {
            docElm.mozRequestFullScreen();
          } else if (docElm.webkitRequestFullScreen) {
            docElm.webkitRequestFullScreen();
          } else if (docElm.msRequestFullscreen) {
            docElm.msRequestFullscreen();
          }
          break;
        case 2:
          if (document.exitFullscreen) {
            document.exitFullscreen();
          } else if (document.mozCancelFullScreen) {
            document.mozCancelFullScreen();
          } else if (document.webkitCancelFullScreen) {
            document.webkitCancelFullScreen();
          } else if (document.msExitFullscreen) {
            document.msExitFullscreen();
          }
          break;
      }
      this.updateFullscreen();
    },
  },
  setup() {
    const { getters, commit, dispatch } = useStore();
    const layout = computed(() => getters.layout);
    const collapsed = computed(() => getters.collapsed);
    const fullscreen = computed(() => getters.fullscreen);
    const menuModel = computed(() => getters.menuModel);
    const theme = computed(() => getters.theme);
    const $route = useRoute();
    const router = useRouter();
    const active = ref($route.matched[0].path);
    const isMobile = computed(() => getters.isMobile);
    const routerActive = computed(() => getters.routerActive);

    watch(
      computed(() => $route.fullPath),
      () => {
        active.value = $route.matched[0].path;
      }
    );
    
    const toPath = (route) => {
      let { redirect, children, path } = route;
      if (redirect) {
        return redirect;
      }
      while (children && children[0]) {
        path = _path.resolve(path, children[0].path);
        children = children[0].children;
      }
      return path;
    };
    
    const routes = computed(() => getters.menu).value.filter((r) => !r.hidden);

    const refresh = async () => {
      commit("layout/UPDATE_ROUTER_ACTIVE");
      setTimeout(() => {
        commit("layout/UPDATE_ROUTER_ACTIVE");
      }, 500);
    };

    const logout = async (e) => {
      await dispatch("user/logout");
    };

    const store = useStore();
    const defaultLang = computed(() => store.state.app.language);
    const selectedKeys = ref([unref(defaultLang)]);
    const toggleLang = async ({ key }) => {
      selectedKeys.value = [key];
      await loadLocaleMessages(i18n, key);
      await store.dispatch("app/setLanguage", key);
    };

    const go = function() {
      router.push("/")
    }

    return {
      go,
      isMobile,
      layout,
      collapsed,
      fullscreen,
      trigger: () => commit("layout/TOGGLE_SIDEBAR"),
      setting: () => commit("layout/TOGGLE_SETTING"),
      updateFullscreen: () => commit("layout/updateFullscreen"),
      menuModel,
      routerActive,
      theme,
      refresh,
      routes,
      active,
      toPath,
      logout,
      toggleLang,
      selectedKeys,
    };
  },
};
</script>
<style lang="less" scoped>
.mobile_header {
  padding-right: 0px !important;
}
</style>