<template>
  <el-submenu v-if="menu.type=='0'&&menu.status=='0'&&menu.children && menu.children.length >= 1" :index="'' + menu.id">
    <template slot="title">
      <i :class="menu.icon" ></i>
      <!-- <i class="fa fa-address-card"></i> -->
      <span slot="title">{{menu.name}}</span>
    </template>
    <MenuTree v-for="item in menu.children" :key="item.id" :menu="item" v-show="menu.type=='0'&&menu.status=='0'"></MenuTree>
  </el-submenu>
  <el-menu-item v-else-if="menu.type=='0'&&menu.status=='0'" :index="'' + menu.id" @click="handleRoute(menu)">
    <i :class="menu.icon"></i>
    <span slot="title">{{menu.name}}</span>
  </el-menu-item>
</template>

<script>
 export default {
  name: 'MenuTree',
  props: {
    menu: {
      type: Object,
      required: true
    }
  },
  methods: {
    handleRoute (menu) {
      // 通过菜单URL跳转至指定路由
      this.$router.push({path: "/" + menu.path});
    }
  }
}
</script>

<style scoped lang="scss">
// .el-submenu, .el-menu-item {
//   background-color: #3c4b5a44;
// }
</style>
