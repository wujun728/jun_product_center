<template>
  <a-modal
    :visible="visible"
    title="发送私信"
    cancelText="取消"
    okText="提交"
    @ok="submit"
    @cancel="cancel"
  >
    <a-form
       ref="formRef"
      :model="formState"
      :rules="formRules"
      :label-col="labelCol"
      :wrapper-col="wrapperCol"
    >
      <a-form-item label="目标" name="recipientId">
          <a-select v-model:value="formState.recipientId">
            <a-select-option :value="user.id" v-bind:key="index" v-for="(user,index) in state.users">{{user.nickname}}</a-select-option>
          </a-select>
      </a-form-item>
      <a-form-item ref="content" label="内容" name="content">
        <a-textarea v-model:value="formState.content" />
      </a-form-item>
    </a-form>
  </a-modal>
</template>
<script>
import { message } from 'ant-design-vue';
import { save } from "@/api/module/inbox";
import { list } from "@/api/module/user";
import { defineComponent, reactive, ref, toRaw } from "vue";
export default defineComponent({
  props: {
    visible: {
      type: Boolean,
    },
  },
  emit: ["close"],
  setup(props, context) {

    const formRef = ref();
    
    const state = reactive({});

    const formState = reactive({});

    const formRules = {
      recipientId: [ { required: true, message: '请输入目标用户', trigger: 'blur'} ],
      content: [ { required: true, message: '请输入公告内容', trigger: 'blur'} ]
    };

    const saveKey = "save";

    const loadUser = () => {
      list({}).then((response)=>{
        state.users = response.data;
      }) 
    }

    const submit = (e) => {
      message.loading({ content: '提交中...', key: saveKey });
      formRef.value
        .validate()
        .then(() => {
          save(toRaw(formState)).then((response)=>{
              if(response.success){
                message.success({ content: '保存成功', key: saveKey, duration: 1 }).then(()=>{
                  cancel();
                });
              }else{
                message.success({ content: '保存失败', key: saveKey, duration: 1 }).then(()=>{
                  cancel();
                });
              }
          });
        })
        .catch(error => {
          console.log('error', error);
        });
    };

    const cancel = (e) => {
      formRef.value.resetFields();
      context.emit("close", false);
    };

    loadUser();

    return {
      state,
      submit,
      cancel,
      formRef,
      formState,
      formRules,
      
      labelCol: { span: 6 },
      wrapperCol: { span: 18 },
    };
  },
});
</script>