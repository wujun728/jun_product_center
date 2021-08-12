import store from "@/store/store";
import router from '@/route'
export default function permission(el, binding, VNode, prevNode) {
  // 获取当前页面权限列表
  const { currentRoute } = router
  const userInfo = store.user.state.userInfo
  const { permissions = [] } = userInfo
  let currentPageId = ''
  let currentPageActions = []
  for (let i = 0; i < permissions.length; i++) {
    const { pageId, actions } = permissions[i]
    if (currentRoute.value.name === pageId) {
      currentPageId = pageId
      currentPageActions = actions
      break
    }
  }

  // 指令参数
  const {arg} = binding
  // 逻辑处理
  if (currentPageId) { // 如果当前页面有配置权限
    if (!currentPageActions.includes(arg)) { // 如果当前用户在这个菜单下没有该按钮权限
      el.style.display = 'none'
    }
  }
}
