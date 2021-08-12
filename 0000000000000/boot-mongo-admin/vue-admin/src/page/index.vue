<template>
  <el-row class="container">
        <el-col :span="24" class="header">
            <el-col :span="5" class="logo" :class="'logo-width'">
                <a href="/" style="text-decoration:none;color:#FFFFFF;">
                  BMA后台
                </a>
            </el-col>
            <el-col :span="2">
                <div class="tools" @click.prevent="collapse">
                    <i class="fa fa-align-justify"></i>
                </div>
            </el-col>
            <el-col :span="10" class="userinfo">
                <el-dropdown trigger="hover">
                    <span class="el-dropdown-link userinfo-inner">
                        你好：{{unifieduser.username}}
                    </span>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item>
                            <a href="#/">首页</a>
                        </el-dropdown-item>
                        <el-dropdown-item>
                            <a >修改密码</a>
                        </el-dropdown-item>
                        <el-dropdown-item @click.native="logout()">
                            注销登录
                        </el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </el-col>
        </el-col>
        <el-col :span="24" class="main">
            <aside :class="collapsed?'menu-collapsed':'menu-expanded'">
              <!-- 导航菜单 -->
              <el-menu ref="navmenu" :class="collapsed?'menu-bar-collapse-width':'menu-bar-width'"
                :collapse="collapsed" :collapse-transition="false" :unique-opened="true"
                :default-active="navselected" :active="navselected"
                @open="handleopen" @close="handleclose" @select="handleselect">
                <!-- 导航菜单树组件，动态加载菜单 -->
                <menu-tree v-for="item in navTree" :key="item.id" :menu="item"></menu-tree>
              </el-menu>
            </aside>
            <section class="content-container">
                <div class="grid-content bg-purple-light">
                    <el-col :span="24" class="breadcrumb-container">
                      <!-- 标签页 -->
                      <div class="tab-container">
                        <el-tabs class="tabs" :class="collapsed?'position-collapse-left':'position-left'"
                          v-model="mainTabsActiveName" :closable="true" type="card"
                          @tab-click="selectedTabHandle" @tab-remove="removeTabHandle">
                          <el-dropdown class="tabs-tools" :show-timeout="0" trigger="hover">
                            <div style="font-size:20px;width:50px;text-align:center"><i class="el-icon-arrow-down"></i></div>
                            <el-dropdown-menu slot="dropdown">
                              <el-dropdown-item @click.native="tabsCloseCurrentHandle">关闭当前标签</el-dropdown-item>
                              <el-dropdown-item @click.native="tabsCloseOtherHandle">关闭其它标签</el-dropdown-item>
                              <el-dropdown-item @click.native="tabsCloseAllHandle">关闭全部标签</el-dropdown-item>
                              <el-dropdown-item @click.native="tabsRefreshCurrentHandle">刷新当前标签</el-dropdown-item>
                            </el-dropdown-menu>
                          </el-dropdown>
                          <el-tab-pane v-for="item in mainTabs"
                            :key="item.name" :label="item.title" :name="item.name">
                            <span slot="label"><i :class="item.icon"></i> {{item.title}} </span>
                          </el-tab-pane>
                        </el-tabs>
                      </div>
                    </el-col>
                    <el-col :span="24" class="breadcrumb-container" style="padding-top:40px">
                        <strong class="title">{{$route.name}}</strong>
                        <el-breadcrumb separator="/" class="breadcrumb-inner">
                            <el-breadcrumb-item v-for="item in $route.matched" :key="item.path">
                                {{ item.name }}
                            </el-breadcrumb-item>
                        </el-breadcrumb>
                    </el-col>
                    <!-- <tabNav></tabNav> -->
                    <el-col :span="24" class="content-wrapper">
                      <keep-alive>
                        <transition name="fade" mode="out-in">
                            <router-view></router-view>
                        </transition>
                      </keep-alive>
                    </el-col>
                </div>
            </section>
        </el-col>

    </el-row>
</template>

<script>
// 在单独构建的版本中辅助函数为 Vuex.mapState
import { mapState } from 'vuex'
import { setStore,getStore } from '/utils/storage'
const Index = () => import('/page/index.vue')
import MenuTree from "@/components/MenuTree"
// import tabNav from '@/components/tabNav/tabNav.vue'

  export default {
    data() {
      return {
          collapsed: false,
          unifieduser:{username:"管理员"},
          navselected:['1']//默认menu展开
        }
      },
      components:{
          MenuTree
      },
      methods: {
          //折叠导航栏
        collapse: function() {
            this.collapsed = !this.collapsed;
        },
        handleopen() {//当前打开
          console.log('handleopen')
        },
        handleclose() {//
          console.log('handleclose')
        },
        handleselect(a, b) {
          console.log('handleselect')
         },
        // 路由操作处理
        handleRoute (route) {
          //设置当前选中
          this.navselected=route.meta.index+""
          // tab标签页选中, 如果不存在则先添加
          var tab = this.mainTabs.filter(item => item.name === route.name)[0];
           if (!tab) {
            tab = {
              name: route.name,
              title: route.name,
              query: this.$route.query,
              icon: route.meta.icon
            }
            this.mainTabs = this.mainTabs.concat(tab)
          }
          this.mainTabsActiveName = tab.name
          },
        //跳转到用户登录页面
        logout: function() {
           this.$store.dispatch('logout').then(() => {
            location.reload()// 为了重新实例化vue-router对象 避免bug
          })
        },
        // tabs, 选中tab
        selectedTabHandle (tab) {
          tab = this.mainTabs.filter(item => item.name === tab.name)
          if (tab.length >= 1) {
            this.$router.push({ name: tab[0].name,query:tab[0].query})
          }
        },
        // tabs, 删除tab
        removeTabHandle (tabName) {
          this.mainTabs = this.mainTabs.filter(item => item.name !== tabName)
          if (this.mainTabs.length >= 1) {
            // 当前选中tab被删除
            if (tabName === this.mainTabsActiveName) {
              this.$router.push({ name: this.mainTabs[this.mainTabs.length - 1].name }, () => {
                this.mainTabsActiveName = this.$route.name
              })
            }
          } else {
            this.$router.push("/")
          }
        },
        // tabs, 关闭当前
        tabsCloseCurrentHandle () {
          this.removeTabHandle(this.mainTabsActiveName)
        },
        // tabs, 关闭其它
        tabsCloseOtherHandle () {
          this.mainTabs = this.mainTabs.filter(item => item.name === this.mainTabsActiveName)
        },
        // tabs, 关闭全部
        tabsCloseAllHandle () {
          this.mainTabs = []
          this.$router.push("/")
        },
        // tabs, 刷新当前
        tabsRefreshCurrentHandle () {
          var tempTabName = this.mainTabsActiveName
          //找到之前的tab
          let tab = this.mainTabs.filter(item => item.name === tempTabName)
          this.removeTabHandle(tempTabName)
          if (tab.length >= 1) {
            this.$nextTick(() => {
              this.$router.push({name: tab[0].name,query:tab[0].query})
            })
          }
        },
        //显示消息
        chat: function (a, b) {
          if (a!=b&&a) {
              const h = this.$createElement;
                this.$notify({
                  title: '系统消息',
                  message: h('i', { style: 'color: teal'}, a.content)
                });
          }
      },

      },
      computed: {
        // mapState({
        //   navTree: state=>state.menu.navTree,
        // // unifieduser: state=>state.app.unifieduser,
        // }),
        navTree: {
          get () { return this.$store.state.menu.navTree},
        },
        mainTabs: {
          get () { return this.$store.state.app.mainTabs },
          set (val) { this.$store.commit('updateMainTabs', val) }
        },
        mainTabsActiveName: {
          get () { return this.$store.state.app.mainTabsActiveName },
          set (val) { this.$store.commit('updateMainTabsActiveName', val) }
        },
      },
      watch: {
       $route: 'handleRoute'
      },
      created () {
        this.handleRoute(this.$route);
        let userinfo=this.$store.state.app.unifieduser;
        if(!userinfo){
          let user=getStore("unifieduser");
          if(user){
            userinfo=JSON.parse(user);
          }
        }
        if(userinfo){
          this.unifieduser=userinfo;//设置用户信息
           //开始链接webscoket
          // this.$store.commit('WEBSOCKET_INIT',process.env.WEBSOCKET_API+"/im/"+userinfo.id);
          // this.$store.getters.onEvent('chat');
        }
      },

  }
</script>


<style scoped lang="scss">

$color-primary: #20a0ff;//#18c79c
.container {
		position: absolute;
		top: 0px;
		bottom: 0px;
		width: 100%;
		.header {
			height: 60px;
			line-height: 60px;
			background: $color-primary;
			color:#fff;
			.userinfo {
				text-align: right;
				padding-right: 35px;
				float: right;
				.userinfo-inner {
					cursor: pointer;
					color:#fff;
					img {
						width: 40px;
						height: 40px;
						border-radius: 20px;
						margin: 10px 0px 10px 10px;
						float: right;
					}
				}
			}
			.logo {
				//width:230px;
				height:60px;
				font-size: 22px;
				padding-left:20px;
				padding-right:20px;
				border-color: rgba(238,241,146,0.3);
				border-right-width: 1px;
				border-right-style: solid;
				img {
					width: 40px;
					float: left;
					margin: 10px 10px 10px 18px;
				}
				.txt {
					color:#fff;
				}
			}
			.logo-width{
				width:230px;
			}
			.logo-collapse-width{
				width:60px
			}
			.tools{
				padding: 0px 23px;
				width:14px;
				height: 60px;
				line-height: 60px;
				cursor: pointer;
			}
		}
		.main {
			display: flex;
			// background: #324057;
			position: absolute;
			top: 60px;
			bottom: 0px;
			overflow: hidden;
			aside {
				flex:0 0 230px;
				width: 230px;
				// position: absolute;
				// top: 0px;
				// bottom: 0px;
				.el-menu{
					height: 100%;
				}
				.collapsed{
					width:60px;
					.item{
						position: relative;
					}
					.submenu{
						position:absolute;
						top:0px;
						left:60px;
						z-index:99999;
						height:auto;
						display:none;
					}

				}
                .el-menu--collapse{
                    width: 59px;
                }
			}
			.menu-collapsed{
				flex:0 0 60px;
				width: 60px;
        overflow-y: scroll;
			}
			.menu-expanded{
				flex:0 0 230px;
				width: 230px;
        overflow-y: scroll;
			}
			.content-container {
				// background: #f1f2f7;
				flex:1;
				// position: absolute;
				// right: 0px;
				// top: 0px;
				// bottom: 0px;
				// left: 230px;
				overflow-y: scroll;
				padding: 20px;
				.breadcrumb-container {
					// margin-bottom: 10px;
					.title {
						width: 200px;
						float: left;
						color: #475669;
					}
					.breadcrumb-inner {
						float: right;
					}
				}
				.content-wrapper {
					background-color: #fff;
					box-sizing: border-box;
				}
			}
		}
  }

.tabs {
    position: fixed;
    top: 60px;
    right: 50px;
    padding-left: 0px;
    padding-right: 2px;
    z-index: 1020;
    height: 40px;
    line-height: 40px;
    font-size: 14px;
    background: rgb(255, 253, 255);
    border-color: rgba(200, 206, 206, 0.5);
    // border-left-width: 1px;
    // border-left-style: solid;
    border-bottom-width: 1px;
    border-bottom-style: solid;
  }
 .tabs-tools {
    position: fixed;
    top: 60px;
    right: 0;
    z-index: 1020;
    height: 40px;
    // padding: 0 10px;
    font-size: 14px;
    line-height: 40px;
    cursor: pointer;
    border-color: rgba(200, 206, 206, 0.5);
    border-left-width: 1px;
    border-left-style: solid;
    border-bottom-width: 1px;
    border-bottom-style: solid;
    background: rgba(255, 255, 255, 1);
  }
  .tabs-tools:hover {
    background: rgba(200, 206, 206, 1);
  }
.position-left {
  left: 230px;
}
.position-collapse-left {
  left: 60px;
}
.el-dropdown-link {
  cursor: pointer;
  color: #409EFF;
}
</style>
