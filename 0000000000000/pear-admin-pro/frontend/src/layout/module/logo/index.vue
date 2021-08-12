<template>
  <div id="logo">
    <!-- 根据侧边收缩状态决定隐显内容 -->
    <div v-if="!collapsed">
       <span class="title">{{title}}</span>
    </div>
    <!-- 根据侧边收缩状态决定隐显内容 -->
    <span v-else><img class="image" :src="image" /></span>
  </div>
</template>
<script>
import { computed } from "vue";
import { useStore } from "vuex";
import config from '@/pear';
export default {
  setup() {
    
    const { getters } = useStore();
    const collapsed = computed(() => getters.collapsed);

    const image = config.image;
    const title = config.title;

    return {
      collapsed,
      image,
      title
    };
  }
};
</script>