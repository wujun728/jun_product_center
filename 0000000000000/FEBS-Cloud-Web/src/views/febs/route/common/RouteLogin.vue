<template>
  <div>
    <span class="notify">
      {{ $t('table.routeLogin.needLogin') }}
      <el-link id="route-login" type="primary" @click="dialogFormVisible = true">{{ $t('table.routeLogin.toLogin') }}</el-link>
      &nbsp;( {{ $t('table.routeLogin.tips') }}
      <el-link href="https://www.kancloud.cn/mrbird/spring-cloud/1473117" target="_blank">https://www.kancloud.cn/mrbird/spring-cloud/1473117</el-link>
      &nbsp;)
    </span>
    <el-dialog
      :width="width"
      title=""
      :visible.sync="dialogFormVisible"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      :show-close="false"
    >
      <el-form ref="form" :model="auth" :rules="rules">
        <el-form-item id="title">
          <i class="el-icon-lock" /> <span>{{ $t('table.routeLogin.title') }}</span>
        </el-form-item>
        <el-form-item prop="username">
          <el-input
            ref="username"
            v-model="auth.username"
            :placeholder="$t('login.username')"
            prefix-icon="el-icon-user"
            name="username"
            type="text"
            autocomplete="off"
            @keyup.enter.native="login"
          />
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            ref="password"
            v-model="auth.password"
            prefix-icon="el-icon-key"
            type="password"
            :placeholder="$t('login.password')"
            name="password"
            autocomplete="off"
            :show-password="true"
            @keyup.enter.native="login"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="warning" plain @click="reset">
          {{ $t('common.cancel') }}
        </el-button>
        <el-button type="primary" plain @click="login">{{ $t('table.routeLogin.login') }}</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import r from '@/utils/route-request'
export default {
  name: 'RouteLogin',
  data() {
    return {
      dialogFormVisible: false,
      width: this.initWidth(),
      auth: {
        username: 'Jack',
        password: '123456'
      },
      rules: {
        username: { required: true, message: this.$t('rules.require'), trigger: 'blur' },
        password: { required: true, message: this.$t('rules.require'), trigger: 'blur' }
      }
    }
  },
  mounted() {
    window.onresize = () => {
      return (() => {
        this.width = this.initWidth()
      })()
    }
  },
  methods: {
    login() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          r.get('route/login', { ...this.auth }).then(v => {
            this.$message({
              message: '认证成功',
              type: 'success'
            })
            const routeToken = v.data.data
            this.$store.commit('account/setRouteToken', routeToken)
            this.reset()
          })
        }
      })
    },
    reset() {
      this.dialogFormVisible = false
      this.$refs.form.clearValidate()
      this.$refs.form.resetFields()
    },
    initWidth() {
      this.screenWidth = document.body.clientWidth
      if (this.screenWidth < 991) {
        return '70%'
      } else if (this.screenWidth < 1400) {
        return '35%'
      } else {
        return '400px'
      }
    }
  }
}
</script>
<style lang="scss" scoped>
  span.notify {
    background-color: #fdf6ec;
    color: #e6a23c;
    width: 100%;
    padding: 1rem;
    margin: 0;
    box-sizing: border-box;
    border-radius: 4px;
    position: relative;
    overflow: hidden;
    opacity: 1;
    display: flex;
    align-items: center;
    transition: opacity .2s;
    font-size: .95rem;
    #route-login {
      font-size: .95rem;
    }
  }
  #title{
    text-align: center;
    color: #464159;
    span {
      font-size: 1.2rem;
      margin-left: 0.4rem;
      font-weight: 600;
    }
    i {
      font-size: 1.2rem;
      font-weight: 600;
    }
  }
</style>
