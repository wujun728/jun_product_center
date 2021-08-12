<template>
  <a-drawer
    title="修改城市"
    :maskClosable="false"
    width=650
    placement="right"
    :closable="false"
    @close="onClose"
    :visible="cityEditVisiable"
    style="height: calc(100% - 55px);overflow: auto;padding-bottom: 53px;">
    <a-form :form="form">
      <a-form-item label='城市名称' v-bind="formItemLayout">
        <a-input style="width: 100%"
                        v-decorator="['cityName',
                   {rules: [
                    { required: true, message: '不能为空'},
                    { max: 20, message: '长度不能超过20个字符'}
                  ]}]"/>
      </a-form-item>
      <a-form-item label='简介' v-bind="formItemLayout">
        <a-input v-decorator="['introduce',
                   {rules: [
                    { max: 200, message: '长度不能超过200个字符'}
                  ]}]"/>
      </a-form-item>
      <a-form-item label='经度' v-bind="formItemLayout">
        <a-input v-decorator="['longitude',
                   {rules: [
                    { max: 50, message: '长度不能超过50个字符'}
                  ]}]"/>
      </a-form-item>
      <a-form-item label='纬度' v-bind="formItemLayout">
        <a-input v-decorator="['latitude',
                   {rules: [
                    { max: 50, message: '长度不能超过50个字符'}
                  ]}]"/>
      </a-form-item>
    </a-form>
    <div class="drawer-bootom-button">
      <a-popconfirm title="确定放弃编辑？" @confirm="onClose" okText="确定" cancelText="取消">
        <a-button style="margin-right: .8rem">取消</a-button>
      </a-popconfirm>
      <a-button @click="handleSubmit" type="primary" :loading="loading">提交</a-button>
    </div>
  </a-drawer>
</template>

<script>
const formItemLayout = {
  labelCol: { span: 3 },
  wrapperCol: { span: 18 }
}
export default {
  name: 'CityEdit',
  props: {
    cityEditVisiable: {
      default: false
    }
  },
  data () {
    return {
      loading: false,
      formItemLayout,
      form: this.$form.createForm(this),
      city: {}
    }
  },
  methods: {
    reset () {
      this.loading = false
      this.form.resetFields()
    },
    onClose () {
      this.reset()
      this.$emit('close')
    },
    setFormValues ({...city}) {
      let fields = ['cityName', 'introduce', 'longitude', 'latitude']
      Object.keys(city).forEach((key) => {
        if (fields.indexOf(key) !== -1) {
          this.form.getFieldDecorator(key)
          let obj = {}
          obj[key] = city[key]
          this.form.setFieldsValue(obj)
        }
      })
      this.city.cityId = city.cityId
    },
    handleSubmit () {
      this.form.validateFields((err, values) => {
        if (!err) {
          let city = this.form.getFieldsValue()
          city.cityId = this.city.cityId
          this.$put('city', {
            ...city
          }).then(() => {
            this.reset()
            this.$emit('success')
          }).catch(() => {
            this.loading = false
          })
        }
      })
    }
  }
}
</script>
