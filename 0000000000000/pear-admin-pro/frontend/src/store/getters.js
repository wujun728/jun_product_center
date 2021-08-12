const getters = {
  layout: state => state.layout.layout,
  theme: state => state.layout.theme,
  color: state => state.layout.color,
  collapsed: state => state.layout.collapsed,
  settingVisible: state => state.layout.setting.opened,
  logo: state => state.layout.logo,
  tab: state => state.layout.tab,
  tabType: state => state.layout.tabType,
  routerActive: state => state.layout.routerActive,
  openKey: state => state.layout.openKey,
  activeKey: state => state.layout.activeKey,
  panes: state => state.layout.panes,
  fullscreen: state => state.layout.fullscreen,
  sideWitch: state => state.layout.sideWitch,
  collapsedWidth: state => state.layout.collapsedWidth,
  fixedHeader: state => state.layout.fixedHeader,
  fixedSide: state => state.layout.fixedSide,
  routes: state => state.layout.routes,
  colorList: state => state.layout.colorList,
  routerAnimate: state => state.layout.routerAnimate,
  language: state => state.layout.language,
  isMobile: state => state.layout.isMobile,
  keepAlive: state=> state.layout.keepAlive,
  token: state =>
    state.user.token
      ? state.user.token
      : localStorage.getItem("token")
      ? localStorage.getItem("token")
      : "",
  menu: state =>
    state.user.userRoutes.length !== 0
      ? state.user.userRoutes
      : localStorage.getItem("user_routes")
      ? JSON.parse(localStorage.getItem("user_routes"))
      : "",
  power: state => state.user.userPowers.length !== 0
      ? state.user.userPowers
      : localStorage.getItem("user_powers")
      ? JSON.parse(localStorage.getItem("user_powers"))
      : ""
};
export default getters;