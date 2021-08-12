<template>
  <a-form
    class="step-form"
    v-bind="wrapper"
  >
    <a-alert
      :closable="true"
      message="确认转账后，资金将直接打入对方账户，无法退回。"
      style="margin-bottom: 24px;"
    />
    <a-descriptions
      :column="1"
    >
      <a-descriptions-item label="付款账户">pearadmin.com</a-descriptions-item>
      <a-descriptions-item label="收款账户">test@example.com</a-descriptions-item>
      <a-descriptions-item label="收款人姓名">落小梅</a-descriptions-item>
      <a-descriptions-item label="转账金额">￥ 5,000.00</a-descriptions-item>
    </a-descriptions>
    <a-divider/>
    <a-form-item
      label="支付密码"
      class="stepFormText"
      v-bind="validateInfos.paymentPassword"
    >
      <a-input
        type="password"
        style="width: 80%;"
        v-model:value="formState.paymentPassword"
      />
    </a-form-item>
    <a-form-item :wrapperCol="{span: 19, offset: 5}">
      <a-button :loading="loading" type="primary" @click="nextStep">提交</a-button>
      <a-button style="margin-left: 8px" @click="prevStep">上一步</a-button>
    </a-form-item>
  </a-form>
</template>

<script lang="tsx">
import { defineComponent, markRaw, reactive, toRefs, onUnmounted } from 'vue'
import { useForm } from '@ant-design-vue/use'
// import { Modal } from 'ant-design-vue'

interface FormState {
  loading: boolean;
  timer: unknown;
}

export default defineComponent({
  name: 'Step2',
  inheritAttrs: false,
  emits: ['prev', 'next'],
  setup (p, { emit }) {
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

    const state = reactive({
      loading: false,
      timer: undefined
    } as FormState)

    const formState = reactive({
      paymentPassword: '123456'
    })

    const {
      validate,
      validateInfos
    } = useForm(formState, reactive({
      paymentPassword: [{
        required: true,
        message: '请输入支付密码'
      }]
    }))

    const nextStep = async () => {
      state.loading = true
      try {
        const values = await validate()
        state.timer = setTimeout(() => {
          state.loading = false
          emit('next', values)
        }, 1500)
      } catch (e) {
        state.loading = false
      }
    }

    onUnmounted(() => {
      if (state.timer) {
        state.timer = undefined
      }
    })

    const prevStep = () => {
      emit('prev')
    }

    return {
      ...toRefs(state),
      formState,
      validateInfos,
      wrapper,
      nextStep,
      prevStep
    }
  }
})
</script>

<style scoped lang="less">
@import "step-form";
</style>
