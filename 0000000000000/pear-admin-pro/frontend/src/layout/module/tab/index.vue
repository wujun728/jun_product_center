<template>
  <div id="tab" :class="[tabType]">
    <a-tabs
      hide-add
      v-model:activeKey="activeKey"
      type="editable-card"
      @edit="onEdit"
      @change="callback"
      class="tab"
    >
      <a-tab-pane
        v-for="pane in panes"
        :key="pane.path"
        :tab="i18nTitle(pane.i18n)"
        :closable="pane.closable"
      >
      </a-tab-pane>
    </a-tabs>
    <a-dropdown class="tab-tool" :placement="placement">
      <a-button>
        <template v-slot:icon>
          <DownOutlined />
        </template>
      </a-button>
      <template v-slot:overlay>
        <a-menu>
          <a-menu-item @click="closeAll()">关 闭 所 有</a-menu-item>
          <a-menu-item @click="closeOther()">关 闭 其 他</a-menu-item>
          <a-menu-item @click="closeCurrent()">关 闭 当 前</a-menu-item>
        </a-menu>
      </template>
    </a-dropdown>
  </div>
</template>
<script>
import _path from "path";
import { computed, reactive, ref, watch } from "vue";
import { useStore } from "vuex";
import { DownOutlined } from "@ant-design/icons-vue";
import { useRouter, useRoute } from "vue-router";
import { useI18n } from "vue-i18n";
import config from '@/pear';
export default {
  components: {
    DownOutlined
  },
  methods: {
    callback(key) {
      this.selectTab(key);
    },
    onEdit(targetKey, action) {
      this[action](targetKey);
    },
    closeAll() {
      this.closeAllTab();
    },
    closeOther() {
      this.closeOtherTab();
    },
    closeCurrent() {
      this.closeCurrentTab();
    },
    remove(targetKey) {
      this.removeTab(targetKey);
    }
  },

  setup() {
    const { getters, commit } = useStore();
    const defaultPanes = computed(() => getters.panes);
    const panes = ref(initPanes);
    const initPanes = [];
    const route = useRoute();
    const router = useRouter();
    const storeKey = computed(() => getters.activeKey);
    const activeKey = ref(storeKey.value);
    const tabType = computed(() => getters.tabType);

    const state = reactive({
      menu: computed(() => getters.menu)
    });
    // store中不允许修改，这里转一次
    const menu = ref(state.menu);

    // 初 始 化 选 项 卡 选 中 项
    const findFixedPane = (list, prefix, panes) => {
      panes.forEach(pane => {
        const { path, meta, hidden, children = [] } = pane;
        if (children && children.length > 0) {
          findFixedPane(list, _path.resolve(prefix, path), children);
        } else {
          if (!hidden && meta && config.defaultTab === pane.component) {
            list.push({
              title: meta.title,
              path: _path.resolve(prefix, path),
              i18n: meta.i18n,
              closable: false
            });
          }
        }
      });
    };

    findFixedPane(initPanes, "", menu.value);

    // 新 增 或 添 加 选 项 卡 操 作
    const dynamicMenu = () => {
      const title = route.meta.title;
      const path = route.path;
      const i18n = route.meta.i18n;
      commit("layout/addTab", { title,path,i18n });
      const { fullPath } = route;
      const startIndex = fullPath.indexOf("/");
      const endIndex = fullPath.lastIndexOf("/");
      const openKey = [fullPath.substring(startIndex, endIndex)];
      localStorage.setItem("openKey", JSON.stringify(openKey));
    };

    // 路 由 变 更 监 听
    watch(computed(() => route.fullPath),
      dynamicMenu
    );

    // 选 项 卡 变 化 监 听
    watch(computed(() => getters.panes),
      n => (panes.value = n),
      { deep: true, immediate: true }
    );

    // 选 项 卡 选 中 监 听
    watch(storeKey, targetKey => {
      activeKey.value = targetKey;
      router.push(targetKey);
    });

    // 初 始 化 操 作
    dynamicMenu(route);
    const allTabs = [...initPanes, ...defaultPanes.value];
    const tabs = allTabs.reduce((result, current) => {
      const resultTitles = result.map(it => it.title);
      if (!resultTitles.includes(current.title)) {
        return [...result, current];
      } else {
        return result;
      }
    }, []);

    commit("layout/initPanes", tabs);

    const { t } = useI18n()

    const i18nTitle = function(key) {
      if(undefined != key) {
        return t(key);
      }
      return "404";
    }

    return {
      t,
      i18nTitle,
      placement: ref("bottomRight"),
      panes,
      activeKey,
      tabType,
      selectTab: key => commit("layout/selectTab", key),
      removeTab: key => commit("layout/removeTab", key),
      closeAllTab: () => commit("layout/closeAllTab"),
      closeOtherTab: () => commit("layout/closeOtherTab"),
      closeCurrentTab: () => commit("layout/closeCurrentTab")
    };
  }
};
</script>
