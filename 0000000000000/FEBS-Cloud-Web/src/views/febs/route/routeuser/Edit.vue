<template>
  <el-dialog
    :title="title"
    :width="width"
    top="100px"
    :close-on-click-modal="false"
    :close-on-press-escape="false"
    :visible.sync="isVisible"
  >
    <el-form ref="form" :model="user" :rules="rules" label-position="right" label-width="100px">
      <el-form-item :label="$t('table.routeUser.username')" prop="username">
        <el-input v-model="user.username" :readonly="user.id === '' ? false : 'readonly'" />
      </el-form-item>
      <el-form-item v-if="user.id === ''" :label="$t('table.routeUser.password')" prop="password">
        <el-input v-model="user.password" type="password" />
      </el-form-item>
      <el-form-item :label="$t('table.routeUser.perm')" prop="roles">
        <el-select v-model="user.roles" multiple value="" placeholder="" style="width:100%">
          <el-option
            v-for="item in roles"
            :key="item.roleId"
            :label="item.roleName"
            :value="String(item.roleName)"
          />
        </el-select>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="warning" plain :loading="buttonLoading" @click="isVisible = false">
        {{ $t('common.cancel') }}
      </el-button>
      <el-button type="primary" plain :loading="buttonLoading" @click="submitForm">
        {{ $t('common.confirm') }}
      </el-button>
    </div>
  </el-dialog>
</template>
<script>
import '@riophae/vue-treeselect/dist/vue-treeselect.css'
import r from '@/utils/route-request'

export default {
  name: 'RouteUserEdit',
  props: {
    dialogVisible: {
      type: Boolean,
      default: false
    },
    title: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      user: this.initUser(),
      buttonLoading: false,
      screenWidth: 0,
      width: this.initWidth(),
      roles: [
        { roleId: 1, roleName: 'admin' },
        { roleId: 2, roleName: 'user' }
      ],
      rules: {
        username: [
          { required: true, message: this.$t('rules.require'), trigger: 'blur' },
          { min: 4, max: 10, message: this.$t('rules.range4to10'), trigger: 'blur' },
          { validator: (rule, value, callback) => {
            if (!this.user.id) {
              r.get(`route/auth/user/${value}`).then((r) => {
                if (r.data) {
                  callback(this.$t('rules.usernameExist'))
                } else {
                  callback()
                }
              })
            } else {
              callback()
            }
          }, trigger: 'blur' }
        ],
        password: [
          { required: true, message: this.$t('rules.require'), trigger: 'blur' },
          { min: 6, max: 20, message: this.$t('rules.range6to20'), trigger: 'blur' }
        ],
        roles: { required: true, message: this.$t('rules.require'), trigger: 'change' }
      }
    }
  },
  computed: {
    isVisible: {
      get() {
        return this.dialogVisible
      },
      set() {
        this.close()
        this.reset()
      }
    }
  },
  methods: {
    initUser() {
      return {
        id: '',
        username: '',
        password: '',
        roles: []
      }
    },
    initWidth() {
      this.screenWidth = document.body.clientWidth
      if (this.screenWidth < 991) {
        return '90%'
      } else if (this.screenWidth < 1400) {
        return '35%'
      } else {
        return '600px'
      }
    },
    setUser(val) {
      this.user = { ...val }
    },
    close() {
      this.$emit('close')
    },
    submitForm() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          this.buttonLoading = true
          if (!this.user.id) {
            // create
            r.post('route/auth/user', { ...this.user }).then(() => {
              this.buttonLoading = false
              this.isVisible = false
              this.$message({
                message: this.$t('tips.createSuccess'),
                type: 'success'
              })
              this.$emit('success')
            }).catch(r => {
              this.buttonLoading = false
            })
          } else {
            // update
            r.put('route/auth/user', { ...this.user }).then(() => {
              this.buttonLoading = false
              this.isVisible = false
              this.$message({
                message: this.$t('tips.updateSuccess'),
                type: 'success'
              })
              this.$emit('success')
            }).catch(r => {
              this.buttonLoading = false
            })
          }
        } else {
          return false
        }
      })
    },
    reset() {
      // 先清除校验，再清除表单，不然有奇怪的bug
      this.$refs.form.clearValidate()
      this.$refs.form.resetFields()
      this.user = this.initUser()
    }
  }
}
</script>
<style lang="scss" scoped>
</style>
