<template>
  <a-modal
    :visible="visible"
    title="新增配置"
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
      <a-form-item ref="name" label="名称" name="name">
        <a-input v-model:value="formState.name" />
      </a-form-item>
      <a-form-item ref="key" label="键" name="key">
        <a-input v-model:value="formState.key" />
      </a-form-item>
      <a-form-item ref="value" label="值" name="value">
        <a-input v-model:value="formState.value" />
      </a-form-item>
      <a-form-item label="状态" name="enable">
        <a-switch v-model:checked="formState.enable" />
      </a-form-item>
      <a-form-item label="备注" name="remark">
        <a-textarea v-model:value="formState.remark" />
      </a-form-item>
    </a-form>
  </a-modal>
</template>
<script>
import { message } from "ant-design-vue";
import { save } from "@/api/module/config";
import { defineComponent, reactive, ref, toRaw } from "vue";

const key = "save";
export default defineComponent({
  props: {
    visible: {
      type: Boolean,
    },
  },
  emit: ["close"],
  setup(props, context) {
    const formRef = ref();

    const formState = reactive({
      enable: true,
    });

    const formRules = {
      name: [{ required: true, message: "请输入配置名", trigger: "blur" }],
      key: [{ required: true, message: "请输入配置键", trigger: "blur" }],
      value: [{ required: true, message: "请输入配置值", trigger: "blur" }],
    };

    const submit = (e) => {
      message.loading({
        content: "提交中...",
        key,
      });
      formRef.value
        .validate()
        .then(() => {
          save(toRaw(formState)).then((response) => {
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