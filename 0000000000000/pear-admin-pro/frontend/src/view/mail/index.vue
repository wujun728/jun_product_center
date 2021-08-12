<template>
  <div>
    <page-header
      title="邮箱发送"
      describe="表单页用于向用户收集或验证信息，基础表单常见于数据项较少的表单场景。表单域标签也可支持响应式."
    ></page-header>
    <page-layout>
      <a-card>
        <a-row type="flex" justify="center">
          <a-col :xs="24" :sm="24" :md="24" :lg="24" :xl="22" :xxl="19">
            <a-form
              ref="ruleForm"
              :model="form"
              :rules="rules"
              :label-col="labelCol"
              :wrapper-col="wrapperCol"
            >
              <a-form-item name="to" label="收件邮箱">
                <a-input v-model:value="form.to" />
              </a-form-item>
              <a-form-item name="subject" label="邮箱主题">
                <a-input v-model:value="form.subject" />
              </a-form-item>
              <a-form-item name="content" label="邮箱内容">
                <a-textarea v-model:value="form.content" :rows="8" />
              </a-form-item>
              <a-form-item :wrapper-col="{ span: 14, offset: 4 }">
                <a-button type="primary" @click="onSubmit"> 发送 </a-button>
                <a-button style="margin-left: 10px" @click="resetForm">
                  重置
                </a-button>
              </a-form-item>
            </a-form>
          </a-col>
        </a-row>
      </a-card>
    </page-layout>
    <page-footer></page-footer>
  </div>
</template>
<script>
import { message } from 'ant-design-vue';
import { send } from '@/api/module/mail'
import { toRaw } from 'vue';

const sendKey = "sendKey";

export default {
  data() {
    return {
      labelCol: { xs: 4, sm: 3, md: 3, lg: 3, xl: 2, xxl: 3 },
      wrapperCol: { xs: 20, sm: 21, md: 21, lg: 21, xl: 20, xxl: 17 },
      other: "",
      form: {
        to: "",
        title: undefined,
        content: undefined
      },
      rules: {
        to: [{required: true,message: "请输入收件邮箱",trigger: "blur"}],
        subject: [{required: true,message: "请输入邮件主题",trigger: "blur"}],
        content: [{required: true, message: "请输入邮件内容", trigger: "blur"}]
      }
    };
  },
  methods: {
    onSubmit() {
      this.$refs.ruleForm
        .validate()
        .then(() => {

          message.loading({
            content: "提交中...",
            key: sendKey,
          });

          send(toRaw(this.form)).then((response) => {
              if(response.success){
                message.success({ content: '发送成功',key: sendKey, duration: 1 }).then(()=>{
                  this.resetForm();
                });
              }else{
                message.success({ content: '发送失败',key: sendKey, duration: 1 }).then(()=>{
                  this.resetForm();
                });
              }
          })

          console.log("values", this.form);
        })
        .catch(error => {
          console.log("error", error);
        });
    },
    resetForm() {
      this.$refs.ruleForm.resetFields();
    }
  }
};
</script>
