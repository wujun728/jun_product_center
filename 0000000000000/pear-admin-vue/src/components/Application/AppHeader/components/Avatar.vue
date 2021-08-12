<template>
  <a-dropdown
    placement="bottomLeft"
  >
    <template #overlay>
      <a-menu
      >
        <a-menu-item key="center">
          <router-link :to="{name: 'center'}">
            <UserOutlined/>
            个人中心
          </router-link>
        </a-menu-item>
        <a-menu-item key="settings">
          <router-link :to="{name: 'settings'}">
            <SettingOutlined/>
            个人设置
          </router-link>
        </a-menu-item>
        <a-menu-divider/>
        <a-menu-item key="logout">
          <a>
            <LogoutOutlined/>
            退出登录
          </a>
        </a-menu-item>
      </a-menu>
    </template>
    <span class="action">
      <a-avatar class="avatar"
                src="https://portrait.gitee.com/uploads/avatars/user/1755/5267877_jobin_jia_1608578025.png" size="small"
                alt="avatar"/>
      <span class="name">UserName</span>
    </span>
  </a-dropdown>
</template>

<script>
import { defineComponent } from 'vue'
import {
  UserOutlined,
  SettingOutlined,
  LogoutOutlined
} from '@ant-design/icons-vue'

export default defineComponent({
  name: 'Avatar',
  components: {
    UserOutlined,
    SettingOutlined,
    LogoutOutlined
  }
})
</script>

<style scoped lang="less">
.action {
  display: flex;
  align-items: center;
  height: 100%;
  padding: 0 12px;
  cursor: pointer;
  transition: all 0.3s;

  .avatar {
    margin: 20px 8px 20px 0;
  }

  > span {
    vertical-align: middle;
  }

  &:hover {
    background: rgba(0, 0, 0, 0.025);
  }

  .name {
    color: black;
  }
}
</style>
