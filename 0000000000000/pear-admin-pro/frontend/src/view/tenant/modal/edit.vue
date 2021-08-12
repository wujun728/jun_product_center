<template>
  <a-modal
    :visible="visible"
    title="修改租户"
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
      :wrapper-col="wrapperCol">
      <a-form-item ref="name" label="名称" name="name">
        <a-input v-model:value="formState.name" />
      </a-form-item>
      <a-form-item label="备注" name="describe">
        <a-textarea v-model:value="formState.describe" />
      </a-form-item>
    </a-form>
  </a-modal>
</template>
<script>
import { message } from 'ant-design-vue';
import { edit } from "@/api/module/tenant";
import { defineComponent, reactive, ref, toRaw, watch } from "vue";

const key = "edit";
export default defineComponent({
  props: {
    visible: {
      type: Boolean,
    },
    record: {
      type: Object,
    }
  },
  emit: ["close"],
  setup(props, context) {

    const formRef = ref();
    
    let formState = reactive({});

    const formRules = {
      name: [{ required: true, message: "请输入租户名称", trigger: "blur" }],
      describe: [{ required: true, message: "请输入租户描述", trigger: "blur" }],
    };

    watch(props,(props) => {
        formState.id = props.record.id
        formState.name = props.record.name
        formState.describe = props.record.describe
    })

    const submit = (e) => {
      message.loading({
        content: "提交中...",
        key,
      });
      formRef.value
        .validate()
        .then(() => {
          edit(toRaw(formState)).then((response) => {
            if (response.success) {
              message.success({
                content: "保存成功",
                key,
                duration: 1,
              }).then(()=>{
                cancel();
              });
            } else {
              message.error({
                content: "保存失败",
                key,
                duration: 1,
              }).then(()=>{
                cancel();
              });
            }
          });
        })
        .catch((error) => {
          console.log("error", error);
        });
    };

    const cancel = (e) => {
      formRef.value.resetFields();
      context.emit("close", false);
    };

    return {
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