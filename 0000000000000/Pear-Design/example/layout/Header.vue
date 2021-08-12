<template>
  <div class="header">
    <div class="menu">
      <a
        class="menu-item"
        v-on:click="addClass(index,$event)"
        v-bind:class="{ show:index==current}"
        :key="menu.path"
        v-for="(menu,index) in menus"
      >
        <router-link :to="menu.path">{{menu.name}}</router-link>
      </a>
    </div>
  </div>
</template>
<script>
import { defineComponent,ref } from "vue";

export default defineComponent({
  name: "header",
  setup() {
    const menus = [{id: "1", name: "首页", path: "/" },
                   {id: "2", name: "指南", path: "/guide" },
                   {id: "3", name: "组件", path: "/document" },
                   {id: "4", name: "物料", path: "/material" }];
    const current = ref(0);
    const addClass = function (index) {
      this.current = index; 
    };
    return {
      menus,
      current,
      addClass,
    };
  },
});
</script>
<style>
.header {
  padding-left: 20px;
  padding-right: 20px;
}
.header .menu {
  display: inline-block;
  margin-left: 200px;
  height: 60px;
  line-height: 60px;
  position: absolute;
  right: 100px;
}
.header .menu .show {
  border-bottom: 3px solid #00c58e;
}
.header .menu .menu-item {
  width: 60px;
  margin-left: 10px;
  text-align: center;
  height: 57px;
  line-height: 57px;
  display: inline-block;
}
.header .menu .menu-item a {
  color: rgba(0, 0, 0, 0.65);
  text-decoration: none;
  font-size: 15px;
}
</style>