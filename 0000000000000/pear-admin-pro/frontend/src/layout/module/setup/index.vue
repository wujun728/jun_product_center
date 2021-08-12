<template>
  <a-drawer
    placement="right"
    :closable="false"
    :visible="visible"
    width="340"
    :after-visible-change="afterVisibleChange"
    @close="onChangeVisible()"
    :class="[color]"
  >
    <div>
      <div class="pearone-color">
        <div class="color-title">菜单风格</div>
        <div class="color-content">
          <ul>
            <li
              @click="updateTheme('theme-dark')"
              :class="[theme === 'theme-dark' ? 'layui-this' : '']"
            >
              <a
                href="javascript:;"
                style="border-radius: 4px"
                class="clearfix full-opacity-hover"
                ><div>
                  <span
                    style="
                      display: block;
                      width: 20%;
                      float: left;
                      height: 12px;
                      background: #2e3549;
                      border-top-left-radius: 4px;
                    "
                  ></span
                  ><span
                    style="
                      display: block;
                      width: 80%;
                      float: left;
                      height: 12px;
                      background: white;
                      border-top-right-radius: 4px;
                    "
                  ></span>
                </div>
                <div>
                  <span
                    style="
                      display: block;
                      width: 20%;
                      float: left;
                      height: 34px;
                      background: #2e3549;
                      border-bottom-left-radius: 4px;
                    "
                  ></span
                  ><span
                    style="
                      display: block;
                      width: 80%;
                      float: left;
                      height: 34px;
                      background: #f4f5f7;
                      border-bottom-right-radius: 4px;
                    "
                  ></span></div
              ></a>
            </li>
            <li
              @click="updateTheme('theme-light')"
              :class="[theme === 'theme-light' ? 'layui-this' : '']"
            >
              <a
                href="javascript:;"
                class="clearfix full-opacity-hover"
                style="border-radius: 4px"
                ><div>
                  <span
                    style="
                      display: block;
                      width: 20%;
                      float: left;
                      height: 12px;
                      border-top-left-radius: 4px;
                      background: white;
                    "
                  ></span
                  ><span
                    style="
                      display: block;
                      width: 80%;
                      float: left;
                      height: 12px;
                      border-top-right-radius: 4px;
                      background: white;
                    "
                  ></span>
                </div>
                <div>
                  <span
                    style="
                      display: block;
                      width: 20%;
                      float: left;
                      height: 34px;
                      border-bottom-left-radius: 4px;
                      background: white;
                    "
                  ></span
                  ><span
                    style="
                      display: block;
                      width: 80%;
                      float: left;
                      height: 34px;
                      background: #f4f5f7;
                      border-bottom-right-radius: 4px;
                    "
                  ></span></div
              ></a>
            </li>
            <li
              @click="updateTheme('theme-night')"
              :class="[theme === 'theme-night' ? 'layui-this' : '']"
            >
              <a
                href="javascript:;"
                data-skin="skin-blue"
                style="border-radius: 4px"
                class="clearfix full-opacity-hover"
                ><div>
                  <span
                    style="
                      display: block;
                      width: 20%;
                      float: left;
                      height: 12px;
                      background: #2e3549;
                      border-top-left-radius: 4px;
                    "
                  ></span
                  ><span
                    style="
                      display: block;
                      width: 80%;
                      float: left;
                      height: 12px;
                      background: #2e3549;
                      border-top-right-radius: 4px;
                    "
                  ></span>
                </div>
                <div>
                  <span
                    style="
                      display: block;
                      width: 20%;
                      float: left;
                      height: 34px;
                      background: #2e3549;
                      border-bottom-left-radius: 4px;
                    "
                  ></span
                  ><span
                    style="
                      display: block;
                      width: 80%;
                      float: left;
                      height: 34px;
                      background: #2e3549;
                      border-bottom-right-radius: 4px;
                    "
                  ></span></div
              ></a>
            </li>
          </ul>
        </div>
      </div>
      <div class="pearone-color">
        <div class="color-title">布局方式</div>
        <div class="color-content">
          <ul>
            <li
              @click="updateLayout('layout-side')"
              :class="[layout === 'layout-side' ? 'layui-this' : '']"
            >
              <a
                href="javascript:;"
                data-skin="skin-blue"
                style="border-radius: 4px"
                class="clearfix full-opacity-hover"
                ><div>
                  <span
                    style="
                      display: block;
                      width: 20%;
                      float: left;
                      height: 12px;
                      background: #2e3549;
                      border-top-left-radius: 4px;
                    "
                  ></span
                  ><span
                    style="
                      display: block;
                      width: 80%;
                      float: left;
                      height: 12px;
                      background: white;
                      border-top-right-radius: 4px;
                    "
                  ></span>
                </div>
                <div>
                  <span
                    style="
                      display: block;
                      width: 20%;
                      float: left;
                      height: 34px;
                      background: #2e3549;
                      border-bottom-left-radius: 4px;
                    "
                  ></span
                  ><span
                    style="
                      display: block;
                      width: 80%;
                      float: left;
                      height: 34px;
                      background: #f4f5f7;
                      border-bottom-right-radius: 4px;
                    "
                  ></span></div
              ></a>
            </li>
            <li
              @click="updateLayout('layout-head')"
              :class="[layout === 'layout-head' ? 'layui-this' : '']"
            >
              <a
                href="javascript:;"
                style="border-radius: 4px"
                class="clearfix full-opacity-hover"
                ><div>
                  <span
                    style="
                      display: block;
                      width: 100%;
                      float: left;
                      height: 12px;
                      background: #2e3549;
                      border-top-left-radius: 4px;
                      border-top-right-radius: 4px;
                    "
                  ></span>
                </div>
                <div>
                  <span
                    style="
                      display: block;
                      width: 100%;
                      float: left;
                      height: 34px;
                      background: #f4f5f7;
                      border-bottom-left-radius: 4px;
                      border-bottom-right-radius: 4px;
                    "
                  ></span></div
              ></a>
            </li>
            <li
              @click="updateLayout('layout-comp')"
              :class="[layout === 'layout-comp' ? 'layui-this' : '']"
            >
              <a
                href="javascript:;"
                data-skin="skin-blue"
                style="border-radius: 4px"
                class="clearfix full-opacity-hover"
                ><div>
                  <span
                    style="
                      display: block;
                      width: 20%;
                      float: left;
                      height: 12px;
                      background: #2e3549;
                      border-top-left-radius: 4px;
                    "
                  ></span
                  ><span
                    style="
                      display: block;
                      width: 80%;
                      float: left;
                      height: 12px;
                      background: #2e3549;
                      border-top-right-radius: 4px;
                    "
                  ></span>
                </div>
                <div>
                  <span
                    style="
                      display: block;
                      width: 20%;
                      float: left;
                      height: 34px;
                      background: #2e3549;
                      border-bottom-left-radius: 4px;
                    "
                  ></span
                  ><span
                    style="
                      display: block;
                      width: 80%;
                      float: left;
                      height: 34px;
                      background: #f4f5f7;
                      border-bottom-right-radius: 4px;
                    "
                  ></span></div
              ></a>
            </li>
          </ul>
        </div>
      </div>
      <div class="select-color">
        <div class="select-color-title">主题配色</div>
        <div class="select-color-content">
          <span
            v-for="(colorItem, index) in colorList"
            class="select-color-item"
            :key="index"
            @click="updateColor(colorItem)"
            :style="{ 'background-color': colorItem }"
            ><CheckOutlined v-if="color === colorItem"
          /></span>
        </div>
      </div>
      <br />
      <div class="pearone-color">
        <div class="color-title">更多设置</div>
        <div class="color-content">
          <br />
          <a-switch v-model:checked="logo" @change="onChangeLogo" />
          <div class="setting-title-right">菜单头部</div>
          <br />
          <br />
          <a-switch v-model:checked="tab" @change="onChangeTab" />
          <div class="setting-title-right">多选项卡</div>
          <br />
          <br />
          <a-switch v-model:checked="keepAlive" @change="toggleKeepAlive" />
          <div class="setting-title-right">视图缓存</div>
          <br />
          <br />
          <a-switch
            v-model:checked="fixedHeader"
            @change="onChangeFixedHeader"
          />
          <div class="setting-title-right">固定头部</div>
          <br />
          <br />
          <a-switch v-model:checked="fixedSide" @change="onChangeFixedSide" />
          <div class="setting-title-right">固定侧边</div>
          <br />
          <br />
          <a-select label-in-value v-model:value="tabType" style="width: 100px" @change="onChangeTabType">
            <a-select-option value="pear-dot-tab"> 样式一 </a-select-option>
            <a-select-option value="pear-card-tab"> 样式二 </a-select-option>
            <a-select-option value="pear-label-tab"> 样式三 </a-select-option>
          </a-select>
          <div class="setting-title-right">卡片样式</div>
          <br />
          <br />
          <a-select
            label-in-value
            v-model:value="routerAnimate"
            style="width: 100px"
            @change="updateRouterAnimate"
          >
            <a-select-option value="null"> 无 </a-select-option>
            <a-select-option value="fade-right"> 渐入 </a-select-option>
            <a-select-option value="fade-top"> 上滑 </a-select-option>
          </a-select>
          <div class="setting-title-right">路由动画</div>
        </div>
      </div>
    </div>
    <br />
    <br />
  </a-drawer>
</template>
<script>
import { CheckOutlined } from "@ant-design/icons-vue";
import { computed } from "vue";
import { useStore } from "vuex";
export default {
  components: {
    CheckOutlined,
  },
  setup() {
    const { getters, commit } = useStore();
    const visible = computed(() => getters.settingVisible);
    const logo = computed(() => getters.logo);
    const keepAlive = computed(() => getters.keepAlive);
    const tab = computed(() => getters.tab);
    const theme = computed(() => getters.theme);
    const fixedSide = computed(() => getters.fixedSide);
    const fixedHeader = computed(() => getters.fixedHeader);
    const layout = computed(() => getters.layout);
    const color = computed(() => getters.color);
    const colorList = computed(() => getters.colorList);
    const routerAnimate = { key: computed(() => getters.routerAnimate) };
    const tabType = { key: computed(() => getters.tabType).value };

    const updateLayout = function (layout) {
      commit("layout/UPDATE_LAYOUT", layout);
    };
    const updateTheme = function (theme) {
      commit("layout/UPDATE_THEME", theme);
    };
    const updateRouterAnimate = function (animate) {
      commit("layout/UPDATE_ROUTER_ANIMATE", animate.key);
    };
    const toggleKeepAlive = function(){
      commit("layout/TOGGLE_KEEP_ALIVE");
    };
    const updateColor = function (color) {
      window.less.modifyVars({
          "primary-color": color,
          "btn-primary-bg": color,
      })
      commit("layout/UPDATE_COLOR", color);
    };
    const onChangeTabType = function (value) {
      commit("layout/UPDATE_TAB_TYPE", value.key);
    };
    
    return {
      tabType,
      toggleKeepAlive,
      routerAnimate,
      updateRouterAnimate,
      onChangeTabType,
      updateLayout,
      updateTheme,
      updateColor,
      colorList,
      fixedSide,
      fixedHeader,
      visible,
      logo,
      tab,
      layout,
      keepAlive,
      theme,
      onChangeVisible: () => commit("layout/TOGGLE_SETTING"),
      onChangeLogo: () => commit("layout/TOGGLE_LOGO"),
      onChangeTab: () => commit("layout/updateTab"),
      onChangeTheme: () => commit("layout/TOGGLE_THEME"),
      onChangeFixedSide: () => commit("layout/TOGGLE_FIXEDSIDE"),
      onChangeFixedHeader: () => commit("layout/TOGGLE_FIXEDHEADER"),
      onChangeLayout: () => commit("layout/TOGGLE_LAYOUT"),
      afterVisibleChange: () => {},
      changeLanguage: (e) => {
        commit("layout/setLanguage", e.target.value);
      },
      color,
    };
  },
};
</script>
<style>
.setting-title-right {
  float: right;
  margin-right: 15px;
}
.ant-drawer-body{
  padding: 10px!important;
}
</style>