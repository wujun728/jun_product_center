<template>
  <a-dropdown
    :trigger="['click']"
  >
    <template #overlay>
      <a-menu>
        <a-tabs>
          <a-tab-pane key="1" tab="通知" force-render>
            1
          </a-tab-pane>
          <a-tab-pane key="2" tab="消息">
            2
          </a-tab-pane>
          <a-tab-pane key="3" tab="代办">
            3
          </a-tab-pane>
        </a-tabs>
      </a-menu>
    </template>
    <div
      class="ant-admin-notice-bar"
    >
      <div
        class="ant-admin-notice-bar-badge"
        @click="handleClickBadge"
      >
        <a-badge
          count="4"
        >
          <BellOutlined style="font-size: 16px"/>
        </a-badge>
      </div>
    </div>
  </a-dropdown>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import { BellOutlined } from '@ant-design/icons-vue'
export default defineComponent({
  name: 'NoticeTab',
  components: {
    BellOutlined
  },
  setup () {
    const handleClickBadge = () => {
      console.log()
    }
    return {
      handleClickBadge
    }
  }
})
</script>

<style scoped>
::deep .ant-dropdown-menu-item-disabled {
  cursor: pointer;
}
</style>
<style scoped lang="less">
.ant-admin-notice-bar {
  display: flex;
  align-items: center;
  height: 100%;
  padding: 0 12px;
  cursor: pointer;
  transition: all .3s;

  &:hover {
    background: rgba(0, 0, 0, 0.025);
  }

  &-badge {
    vertical-align: middle;
    font-size: 16px;
    display: inline-block;
    color: black;
    font-style: normal;
    line-height: 0;
    text-align: center;
    text-transform: none;
    padding: 4px;
  }
}
</style>
