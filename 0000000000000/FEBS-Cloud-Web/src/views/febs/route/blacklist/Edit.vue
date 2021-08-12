<template>
  <el-dialog
    :title="title"
    :width="width"
    top="100px"
    :close-on-click-modal="false"
    :close-on-press-escape="false"
    :visible.sync="isVisible"
  >
    <el-form ref="form" :model="blackList" :rules="rules" label-position="right" label-width="129px">
      <el-form-item :label="$t('table.blackList.ip')" prop="ip">
        <el-input v-model="blackList.ip" :readonly="!blackList.id ? false : 'readonly'" />
      </el-form-item>
      <el-form-item :label="$t('table.blackList.requestUri')" prop="requestUri">
        <el-input v-model="blackList.requestUri" :readonly="!blackList.id ? false : 'readonly'" :placeholder="$t('table.blackList.st')" />
      </el-form-item>
      <el-form-item :label="$t('table.blackList.requestMethod')" prop="requestMethod">
        <el-select v-model="blackList.requestMethod" :disabled="!blackList.id ? false : 'disabled'" value="" placeholder="" style="width:100%">
          <el-option
            v-for="item in requestMethods"
            :key="item.id"
            :label="item.name"
            :value="String(item.name)"
          />
        </el-select>
      </el-form-item>
      <el-form-item :label="$t('table.blackList.status')" prop="status">
        <el-switch
          v-model="blackList.status"
          :active-text="$t('common.status.valid')"
          :inactive-text="$t('common.status.invalid')"
          active-value="1"
          inactive-value="0"
        />
      </el-form-item>
      <el-form-item :label="$t('table.blackList.timeLimit')">
        <el-switch
          v-model="blackList.timeLimit"
          :active-text="$t('common.open')"
          :inactive-text="$t('common.close')"
          active-value="1"
          inactive-value="0"
        />
      </el-form-item>
      <el-form-item v-if="blackList.timeLimit === '1'" :label="$t('table.blackList.timeRange')" prop="limitTime">
        <el-time-picker
          v-model="blackList.limitFrom"
          :placeholder="$t('table.blackList.limitFrom')"
          value-format="HH:mm:ss"
        />
        <el-time-picker
          v-model="blackList.limitTo"
          :placeholder="$t('table.blackList.limitTo')"
          value-format="HH:mm:ss"
        />
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
  name: 'BlackListEdit',
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
      blackList: this.initBlackList(),
      buttonLoading: false,
      screenWidth: 0,
      width: this.initWidth(),
      requestMethods: [
        { id: 1, name: 'GET' },
        { id: 2, name: 'POST' },
        { id: 3, name: 'PUT' },
        { id: 4, name: 'DELETE' },
        { id: 5, name: 'ALL' }
      ],
      rules: {
        requestUri: { required: true, message: this.$t('rules.require'), trigger: 'blur' },
        requestMethod: [
          { required: true, message: this.$t('rules.require'), trigger: '[change, blur]' },
          { validator: (rule, value, callback) => {
            if (!this.blackList.id && this.blackList.requestMethod && this.blackList.requestUri) {
              r.get('route/auth/blackList/exist', {
                ip: this.blackList.ip,
                requestUri: this.blackList.requestUri,
                requestMethod: this.blackList.requestMethod
              }).then((r) => {
                if (r.data && r.data.length) {
                  callback(this.$t('tips.sameRule'))
                } else {
                  callback()
                }
              })
            } else {
              callback()
            }
          }, trigger: 'change' }
        ],
        limitTime: { validator: (rule, value, callback) => {
          if (this.blackList.timeLimit === '1' && (!this.blackList.limitFrom || !this.blackList.limitTo)) {
            callback(new Error(this.$t('rules.require')))
          } else {
            callback()
          }
        }, trigger: 'blur' }
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
    initBlackList() {
      return {
        id: null,
        ip: '',
        requestUri: '',
        requestMethod: '',
        limitFrom: '',
        limitTo: '',
        timeLimit: '0',
        status: '1'
      }
    },
    initWidth() {
      this.screenWidth = document.body.clientWidth
      if (this.screenWidth < 991) {
        return '90%'
      } else if (this.screenWidth < 1400) {
        return '45%'
      } else {
        return '700px'
      }
    },
    setblackList(val) {
      this.blackList = { ...val }
    },
    close() {
      this.$emit('close')
    },
    submitForm() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          this.buttonLoading = true
          if (!this.blackList.id) {
            // create
            r.post('route/auth/blackList', { ...this.blackList }).then(() => {
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
            r.put('route/auth/blackList', { ...this.blackList }).then(() => {
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
      this.blackList = this.initBlackList()
    }
  }
}
</script>
<style lang="scss" scoped>
</style>
