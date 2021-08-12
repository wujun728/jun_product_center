<template>
  <div class="document">
    <p-container>
      <p-aside>
        <div class="menu menu-tree">
          <div class="menu">
            <div
              class="menu-item"
              v-on:click="addClass(index,$event)"
              v-bind:class="{ show:index==current}"
             :key="menu.path"
              v-for="(menu,index) in menus">
              <router-link :to="menu.path">{{menu.name}}</router-link>
            </div>
          </div>
        </div>
      </p-aside>
      <p-main>
        <router-view></router-view>
      </p-main>
    </p-container>
  </div>
</template>
<script>
import { defineComponent,ref } from "vue";
export default defineComponent({
  name: "header",
  setup() {
    const menus = [{id: "1", name: "起飞", path: "/document-start" },
                   {id: "2", name: "布局", path: "/document-layout" },
                   {id: "3", name: "栅格", path: "/document-grid" },
                   {id: "4", name: "按钮", path: "/document-button" },
                   {id: "5", name: "开关", path: "/document-switch" },
                   {id: "6", name: "卡片", path: "/document-card" },
                   {id: "7", name: "单选", path: "/document-radio" },
                   {id: "8", name: "多选", path: "/document-checkbox" },
                   {id: "9", name: "滑块", path: "/document-slider" },
                   {id: "10", name: "输入框", path: "/document-input" },
                   {id: "11", name: "计数器", path: "/document-input-number" }]
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
.menu-tree .menu-item{
    height: 42px;
    margin-top: 2px;
    margin-bottom: 2px;
    line-height: 42px;
    padding-left: 30px;
    margin-right: 10px;
}
.menu-tree .menu-item a{
    color: rgba(0, 0, 0, 0.65);
    width: 100%;
    display: inline-block;
    text-decoration: none;
}
.menu-tree .menu-item a{
    font-size: 14px;
    color: rgba(0, 0, 0, 0.65);
    text-decoration: none;
}
.menu-tree .menu-item.show{
    box-shadow: 0 2px 4px rgba(0,0,0,.15);
    background: #00c58e;
    border-radius: 2px;
}
.menu-tree .menu-item.show a{
    background: #00c58e;
    color: white;
}
.document{
  height: 100%;
}
.document .p-container{
  height: 100%;
}
.document .p-container .p-main{
  padding: 30px;
  border-left: 1px solid whitesmoke;
  height: 100%;
}
</style>