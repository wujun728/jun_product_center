<template>
  <div>
     <el-card class="login-form-layout">
      <el-form label-position="left">
        <div style="text-align: center">
          <span style="width: 56px;height: 56px;color: #409EFF"></span>
        </div>
        <h2 class="login-title color-main">后台管理系统</h2>
        <el-form-item prop="username">
          <el-input v-model="ajaxData.username"
                    placeholder="请输入用户名">
          <span slot="prefix">
            <span class="color-main fa fa-user"></span>
          </span>
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="ajaxData.password" type="password"
                    placeholder="请输入密码">
          <span slot="prefix">
            <span class="color-main fa fa-lock" ></span>
          </span>
          <span slot="suffix">
            <span class="color-main"></span>
          </span>
          </el-input>
        </el-form-item>
        <el-form-item style="margin-bottom: 60px;text-align: center">
          <el-button style="width: 45%" type="primary" @click.native.prevent="login">
            登录
          </el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>



<script>
  import { authLogin} from '@/api/common'
  import { setStore } from '/utils/storage'
  export default {
    name: 'login',
    data() {
      return {
        ajaxData:{
          username: '',
          password: ''
        }
      }
    },
    methods: {
      login:function(){
         let para = Object.assign({}, this.ajaxData);
            authLogin(para).then((res) => {
              if(res.code==200){
                setStore('shuogesha_auth_id',res.data.authId);
                setStore('unifieduser',JSON.stringify(res.data.unifieduser));
                // this.$store.commit('setToken', res.data.authId)
                // this.$store.commit('setUnifieduser', res.data.unifieduser)
                // this.$store.commit('setUser', res.data.user)
                // this.$store.commit('menuRouteLoaded', false)
                // this.$store.commit('updateMainTabs', [])
                // this.$store.commit('updateMainTabsActiveName', '')
                this.$router.push({path: '/home'});
              }else{
                this.$message({
                  message:res.message,
                  type: 'error'
                });
              }
            });
        //存
        // setStore('token','111');
        // this.$router.push({path: '/home'});
      }
    }
  }

</script>
 <style scoped>
  .login-form-layout {
    position: absolute;
    left: 0;
    right: 0;
    width: 360px;
    margin: 140px auto;
    border-top: 10px solid #409EFF;
  }

  .login-title {
    text-align: center;
  }

  .login-center-layout {
    background: #409EFF;
    width: auto;
    height: auto;
    max-width: 100%;
    max-height: 100%;
    margin-top: 200px;
  }
</style>
