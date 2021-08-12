<template>
  <div class="px-5 login-wrapper">
    <ThemeTool class="float-right my-4 mr-4" />
    <div
      class="relative w-full py-5 my-40 ml-auto mr-auto login-form sm:w-full"
    >
      <div class="flex flex-row items-center justify-around">
        <img class="w-16" src="~@/assets/logo.png" alt="" />
        <n-element
          tag="h2"
          class="text-4xl"
          style="
            color: var(--primary-color);
            transition: 0.3s var(--cubic-bezier-ease-in-out);
          "
        >
          Pear Admin
        </n-element>
      </div>
      <n-form
        ref="formRefEl"
        class="mt-10"
        :model="model"
        :rules="rules"
        size="large"
      >
        <n-form-item path="username">
          <n-input
            v-model:value="model.username"
            placeholder="请输入账号(admin)"
          ></n-input>
        </n-form-item>
        <n-form-item class="block" path="password">
          <n-input
            v-model:value="model.password"
            type="password"
            placeholder="请输入密码(admin)"
            show-password-toggle
          ></n-input>
        </n-form-item>
        <n-form-item class="block" path="captchaCode">
          <n-grid x-gap="12" :cols="2">
            <n-gi>
              <n-input
                v-model:value="model.captchaCode"
                placeholder="请输入验证码"
                maxlength="5"
              >
              </n-input>
            </n-gi>
            <n-gi class="flex flex-row-reverse">
              <img
                class="cursor-pointer"
                :src="validateCodeState?.image"
                alt="验证码"
                :loading="isFetching"
                @click="reloadCapture"
              />
            </n-gi>
          </n-grid>
        </n-form-item>
        <n-form-item class="block">
          <n-grid :cols="2" :x-gap="24">
            <n-checkbox>记住我</n-checkbox>
            <n-element
              tag="a"
              href="javascript:;"
              class="block text-right"
              style="
                color: var(--primary-color);
                transition: 0.3s var(--cubic-bezier-ease-in-out);
              "
            >
              忘记密码
            </n-element>
          </n-grid>
        </n-form-item>
        <n-form-item class="block">
          <n-button
            :disabled="!btnNotDisable"
            type="primary"
            class="w-full"
            attr-type="submit"
            :loading="isFetching"
            @click="handleLogin"
            >登录
          </n-button>
        </n-form-item>
      </n-form>
    </div>
  </div>
</template>
<script lang="ts" setup>
  import { NForm, useMessage } from 'naive-ui'
  import { ref, unref, markRaw, computed, watch } from 'vue'
  import { USER_API } from '@/api/moduels/user'
  import { useRouter } from 'vue-router'
  import { until } from '@vueuse/core'
  import { stringify } from 'qs'
  import useAppFetch from '@/api/useAppFetch'
  import { usePermission } from '@/store/modules/permission'
  import ThemeTool from '@/components/Application/Settings/ThemeTool/ThemeTool.vue'

  // composable
  const router = useRouter()
  const message = useMessage()
  const permissionStore = usePermission()

  // formRef
  const formRefEl = ref<null | typeof NForm>(null)
  // form model
  const model = ref({
    username: 'admin',
    password: 'admin',
    captchaCode: ''
  })

  const rules = markRaw({
    username: [
      {
        required: true,
        message: '请输入账号'
      }
    ],
    password: [
      {
        required: true,
        message: '请输入密码'
      }
    ],
    captchaCode: [
      {
        required: true,
        message: '请输入验证码'
      }
    ]
  })

  // 验证码
  const {
    data: validateCodeState,
    isFinished: hasCode,
    execute: refreshValidateCode
  } = useAppFetch(
    USER_API.validateCode,
    {
      method: 'get'
    },
    { immediate: true }
  ).json()

  // 自动填写验证码
  watch(hasCode, (code) => {
    if (code) {
      model.value.captchaCode = validateCodeState.value?.code
    }
  })

  // 重新加载验证码
  const reloadCapture = () => {
    refreshValidateCode()
  }

  // login button disabled
  const btnNotDisable = computed(() =>
    Object.values(model.value).every((val) => val && val.trim() !== '')
  )

  // login params
  const validateParams = computed(() => {
    return {
      captchaKey: validateCodeState.value?.key,
      captchaImage: validateCodeState.value?.image
    }
  })

  const loginUrl = ref<string>(USER_API.login)
  const {
    data: loginData,
    post,
    isFetching,
    isFinished
  } = useAppFetch(
    loginUrl,
    {
      method: 'post'
    },
    { immediate: false }
  ).json()

  const handleLogin = async (e: Event): Promise<void> => {
    e && e.preventDefault()
    const error = await formRefEl.value?.validate()
    if (!error) {
      const urlParams = stringify({
        ...unref(model),
        ...unref(validateParams)
      })
      loginUrl.value = `${USER_API.login}?${urlParams}`
      post().execute()
      await until(isFinished).toBe(true)
      if (!loginData.value) {
        refreshValidateCode()
        return
      }
      const { success = false, msg, token, tokenKey } = unref(loginData)
      if (success) {
        message.success(msg)
        permissionStore.setToken('token', { tokenKey, token })
        console.log(router)
        console.log(router.getRoutes())
        router.push('/')
      }
    }
  }
</script>

<style scoped lang="less">
  @screen md {
    .login-form {
      width: 340px;
    }
  }

  .login-wrapper {
    @apply w-full h-screen absolute;

    background: url('@/assets/svg/background.svg') no-repeat;
    background-size: cover;
  }
</style>
