<template>
  <div id="login">
    <div class="login-form">
      <a-form
        ref="formRef"
        :rules="formRules"
        :model="formState"
        :label-col="labelCol"
        :wrapper-col="wrapperCol"
      >
        <a-form-item>
          <img class="logo" src="@/assets/image/logo.png" />
          <div class="head">Pear Admin</div>
          <div class="desc">明 湖 区 最 具 影 响 力 的 设 计 规 范 之 一</div>
        </a-form-item>
        <a-form-item>
          <a-input
            placeholder="账 户 : admin"
            v-model:value="formState.username"
          />
        </a-form-item>
        <a-form-item>
          <a-input
            placeholder="密 码 : admin"
            type="password"
            v-model:value="formState.password"
          />
        </a-form-item>
        <a-form-item class="captchaKey">
          <a-input v-model:value="formState.captchaKey" />
        </a-form-item>
        <a-form-item>
          <a-row :gutter="10">
            <a-col :span="13">
              <a-input v-model:value="formState.captchaCode" />
            </a-col>
            <a-col :span="11">
              <img
                class="captchaImage"
                @click="refreshCaptcha"
                style="margin-top: -3px"
                :src="formState.captchaImage"
                alt="连接失败"
              />
            </a-col>
          </a-row>
        </a-form-item>
        <a-form-item>
          <a-checkbox :checked="true"> 记住我 </a-checkbox>
          <a class="forgot" href=""> 忘记密码 </a>
        </a-form-item>
        <a-form-item :wrapper-col="{ span: 24 }">
          <a-button :loading="load" type="primary" @click="onSubmit">
            登录
          </a-button>
        </a-form-item>
      </a-form>
    </div>
  </div>
</template>
<script>
import { reactive, ref } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";
import { create } from "@/api/module/captcha";
import { notification } from "ant-design-vue";
export default {
  setup() {
    const router = useRouter();
    const store = useStore();
    const load = ref(false);

    const formRef = ref();

    // 登录参数
    const formState = reactive({
      username: "admin",
      password: "admin",
      captchaKey: "key",
      captchaCode: "code",
      captchaImage: "image",
    });

    const formRules = {
      username: [{ required: true, message: "请输入账户", trigger: "blur" }],
      password: [{ required: true, message: "请输入密码", trigger: "blur" }],
    };

    // 验证码 初始化
    const refreshCaptcha = async function () {
      const result = await create();
      formState.captchaKey = result.data.key;
      formState.captchaCode = result.data.code;
      formState.captchaImage = result.data.image;
    };

    refreshCaptcha();

    // 登录验证
    const onSubmit = () => {
      formRef.value
        .validate()
        .then(async () => {
          load.value = true;
          await store.dispatch("user/login", formState);
          load.value = false;
          notification['success']({
            message: "登录成功",
            description: "就 眠 仪 式, 欢 迎 回 来.",
          });
          await router.push("/");
        })
        .catch((error) => {
          notification['error']({
            message: "登录失败",
            description: error,
          });
          load.value = false;
          refreshCaptcha();
        });
    };

    return {
      labelCol: { span: 6 },
      wrapperCol: { span: 24 },
      refreshCaptcha,
      formRules,
      formState,
      onSubmit,
      formRef,
      load,
    };
  },
};
</script>
<style lang="less">
#login {
  width: 100%;
  height: 100%;
  background: url(../../assets/image/background.svg);
  background-size: cover;
  .login-form {
    margin: auto;
    width: 330px;
    min-height: 20px;
    padding-top: 150px;
    .captchaKey {
      display: none;
    }
    .captchaImage {
      border-radius: 4px;
      border: 1px solid #d9d9d9;
    }
    .ant-input {
      border-radius: 4px;
      line-height: 40px;
      height: 40px;
    }
    .ant-btn {
      width: 100%;
      height: 40px;
      line-height: 40px;
    }
  }
  .logo {
    width: 60px !important;
    margin-top: 10px !important;
    margin-bottom: 10px !important;
    margin-left: 20px !important;
    border: none;
    background-color: transparent;
  }
  .head {
    width: 300px;
    font-size: 30px !important;
    font-weight: 700 !important;
    margin-left: 20px !important;
    line-height: 60px !important;
    margin-top: 10px !important;
    position: absolute !important;
    display: inline-block !important;
    height: 60px !important;
    color: #36b368;
  }
  .desc {
    width: 100% !important;
    text-align: center !important;
    color: gray !important;
    height: 60px !important;
    line-height: 60px !important;
  }
  .forgot {
    float: right;
  }
}
</style>
