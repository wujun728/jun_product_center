<template>
  <div id="main-tab">
    <el-tabs v-model="activeName2" type="card" @tab-click="handleClick">
      <el-tab-pane label="用户管理" name="first"></el-tab-pane>
      <el-tab-pane label="配置管理" name="second"></el-tab-pane>
      <el-tab-pane label="角色管理" name="third"></el-tab-pane>
      <el-tab-pane label="定时任务补偿" name="fourth"></el-tab-pane>
      <el-tab-pane label="配置管理" name="second"></el-tab-pane>
      <el-tab-pane label="角色管理" name="third"></el-tab-pane>
      <el-tab-pane label="定时任务补偿" name="fourth"></el-tab-pane>
    </el-tabs>
  </div>
</template>
<script>
export default {
  data() {
    return {
      editableTabsValue2: "2",
      editableTabs2: [
        {
          title: "Tab 1",
          name: "1",
          content: "Tab 1 content"
        },
        {
          title: "Tab 2",
          name: "2",
          content: "Tab 2 content"
        }
      ],
      tabIndex: 2
    };
  },
  methods: {
    addTab(targetName) {
      let newTabName = ++this.tabIndex + "";
      this.editableTabs2.push({
        title: "New Tab",
        name: newTabName,
        content: "New Tab content"
      });
      this.editableTabsValue2 = newTabName;
    },
    removeTab(targetName) {
      let tabs = this.editableTabs2;
      let activeName = this.editableTabsValue2;
      if (activeName === targetName) {
        tabs.forEach((tab, index) => {
          if (tab.name === targetName) {
            let nextTab = tabs[index + 1] || tabs[index - 1];
            if (nextTab) {
              activeName = nextTab.name;
            }
          }
        });
      }

      this.editableTabsValue2 = activeName;
      this.editableTabs2 = tabs.filter(tab => tab.name !== targetName);
    }
  }
};
</script>
<style>
#main-tab {
  height: 45px;
  padding-left: 0px;
  display: none !important;
}
.muilt-tab #main-tab {
  display: block !important;
}
.el-tabs__item {
  border: none;
}
.el-tabs__nav-scroll {
  border: none !important;
}
.el-tabs__nav {
  border: none !important;
  border-radius: 0px !important;
}
.el-tabs__nav .is-active {
  border: whitesmoke 1px solid;
}
.el-tabs__header {
  border: none !important;
  border-bottom: whitesmoke solid 1px !important;
  background-color: white;
}
.el-tabs--card > .el-tabs__header .el-tabs__item {
  border-left: whitesmoke 1px solid !important;
}

.el-tabs--card > .el-tabs__header .el-tabs__item:last-child {
  border-right: whitesmoke 1px solid !important;
}
.el-tabs__nav-prev{
  width: 40px;
  text-align: center;
}
.el-tabs__nav-next{
    width: 40px;
  text-align: center;
}
.el-tabs__nav-wrap.is-scrollable{
  padding-left: 40px;
  padding-right: 40px;
}

.el-tabs__nav-next{
  border-left: 1px solid whitesmoke;
}
.el-tabs__nav-prev{
  border-right: 1px solid whitesmoke;
}
</style>