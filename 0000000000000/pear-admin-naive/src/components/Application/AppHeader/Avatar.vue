<template>
  <n-dropdown
    trigger="hover"
    :options="avatarOptions"
    placement="bottom-end"
    @select="avatarSelect"
  >
    <div class="flex flex-row items-center h-12 cursor-pointer px-2">
      <n-avatar
        class="bg-white"
        round
        size="small"
        object-fit="cover"
        src="https://portrait.gitee.com/uploads/avatars/user/1755/5267877_jobin_jia_1608578025.png!avatar200"
      >
      </n-avatar>
      <span class="ml-0.5">落小梅</span>
    </div>
  </n-dropdown>
</template>
<script lang="tsx">
  export default {
    name: 'Avatar'
  }
</script>
<script lang="tsx" setup>
  import { useRouter } from 'vue-router'
  import { useDialog } from 'naive-ui'
  import Icon from '@/components/BasicComponents/Icons/Icon.vue'
  import { usePermission } from '@/store/modules/permission'

  const router = useRouter()
  const permission = usePermission()

  const avatarOptions = [
    {
      label: '退出登录',
      key: 'logout',
      icon: () => <Icon icon="ant-design:logout-outlined" />
    }
  ]
  const avatarSelect = (key: string) => {
    switch (key) {
      case 'logout':
        logout()
        break
    }
  }

  const dialog = useDialog()
  function logout() {
    dialog.warning({
      title: '提示',
      content: '你确定退出登录吗？',
      positiveText: '确定',
      negativeText: '取消',
      onPositiveClick: () => {
        permission.clear()
        router.push('/login')
      }
    })
  }
</script>
