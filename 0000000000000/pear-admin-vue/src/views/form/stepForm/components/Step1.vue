<template>
  <a-form
    v-bind="wrapper"
    class="step-form"
  >
    <a-form-item
      label="付款账户"
      v-bind="validateInfos.paymentUser"
    >
      <a-select
        placeholder="pearadmin@com"
        v-model:value="paymentUser"
        allowClear
      >
        <a-select-option value="1">pearadmin.com</a-select-option>
      </a-select>
    </a-form-item>
    <a-form-item
      label="收款账户"
      v-bind="validateInfos.payType"
    >
      <a-input-group
        style="display: inline-block; vertical-align: middle"
        :compact="true"
      >
        <a-select defaultValue="alipay" style="width: 100px">
          <a-select-option value="alipay">支付宝</a-select-option>
          <a-select-option value="wexinpay">微信</a-select-option>
        </a-select>
        <a-input
          v-model:value="payType"
          :style="{width: 'calc(100% - 100px)'}"
        />
      </a-input-group>
    </a-form-item>
    <a-form-item
      label="收款人姓名"
      v-bind="validateInfos.name"
    >
      <a-input v-model:value="name"/>
    </a-form-item>
    <a-form-item
      label="转账金额"
      v-bind="validateInfos.money"
    >
      <a-input v-model:value="money" prefix="￥"/>
    </a-form-item>
    <a-form-item :wrapperCol="{span: 19, offset: 5}">
      <a-button type="primary" @click="nextStep">下一步</a-button>
    </a-form-item>
  </a-form>
  <a-divider/>
  <div class="step-form-style-desc">
    <h3>说明</h3>
    <h4>转账到支付宝账户</h4>
    <p>如果需要，这里可以放一些关于产品的常见问题说明。如果需要，这里可以放一些关于产品的常见问题说明。如果需要，这里可以放一些关于产品的常见问题说明。</p>
  </div>
</template>

<script lang="ts">
import { defineComponent, markRaw, reactive, toRefs } from 'vue'
import { useForm } from '@ant-design-vue/use'

export default defineComponent({
  name: 'Step1',
  inheritAttrs: false,
  emits: ['next'],
  setup (props, { emit }) {
    const wrapper = markRaw({
      labelCol: {
        lg: { span: 5 },
        sm: { span: 5 }
      },
      wrapperCol: {
        lg: { span: 19 },
        sm: { span: 19 }
      }
    })
    const formState = reactive({
      paymentUser: '1',
      payType: 'test@example.com',
      name: 'Alex',
      money: '5000'
    })
    const {
      validate,
      validateInfos
    } = useForm(formState, reactive({
      paymentUser: [{
        required: true,
        message: '付款账户必须填写'
      }],
      payType: [{
        required: true,
        message: '收款账户必须填写'
      }],
      name: [{
        required: true,
        message: '收款人名称必须核对'
      }],
      money: [{
        required: true,
        message: '转账金额必须填写'
      }]
    }))
    const nextStep = async () => {
      try {
        const values = await validate()
        emit('next', values)
      } catch (e) {
        console.log(e)
      }
    }
    return {
      ...toRefs(formState),
      wrapper,
      validateInfos,
      nextStep
    }
  }
})
</script>

<style scoped lang="less">
@import "step-form";
</style>
