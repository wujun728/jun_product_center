<template>
  <a-modal
    :visible="visible"
    title="查看详情"
    cancelText="取消"
    okText="确定"
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
import { defineComponent, reactive, ref, watch } from "vue";

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
      formRef.value.resetFields();
      context.emit("close", false);
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