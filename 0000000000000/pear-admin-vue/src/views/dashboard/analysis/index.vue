<template>
  <page-container>
    <a-card>
      <a-form>
        <a-form-item
          label="手机验证码"
          v-bind="validateInfos.mobileCode"
        >
          <ValidateMobile
            size="large"
            v-model="mobileCode"
            :validateCodeLength="validateCodeLength"
          ></ValidateMobile>
        </a-form-item>
      </a-form>
      <a-button type="primary" @click="handleValidate">Validate</a-button>
      <a-button @click="handleReset" style="margin-left: 10px;">Reset</a-button>
    </a-card>
  </page-container>
</template>

<script lang="ts">
import { defineComponent, reactive, toRefs } from 'vue'
import { useForm } from '@ant-design-vue/use'

export default defineComponent({
  name: 'index',
  setup () {
    const state = reactive({
      validateCodeLength: 4
    })
    const formState = reactive({
      mobileCode: ''
    })

    const {
      validate,
      validateInfos,
      resetFields
    } = useForm(formState, reactive({
      mobileCode: [
        {
          required: true,
          message: 'is required'
        }, {
          validator: (rule, value) => {
            if (value.toString().length < state.validateCodeLength) {
              return Promise.reject(new Error('验证码格式不正确!'))
            }
            return Promise.resolve()
          }
        }
      ]
    }))

    const handleValidate = async () => {
      try {
        const v = await validate()
        console.log(v)
      } catch (e) {
        console.log(e)
      }
    }

    const handleReset = () => {
      resetFields()
    }

    return {
      ...toRefs(formState),
      ...toRefs(state),
      validateInfos,
      handleValidate,
      handleReset
    }
  }
})
</script>

<style scoped lang="less">

</style>
