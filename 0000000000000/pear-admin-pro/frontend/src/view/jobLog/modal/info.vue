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
      :wrapper-col="wrapperCol"
    >
      <a-form-item ref="jobName" label="名称" name="jobName">
        <a-input v-model:value="formState.jobName" />
      </a-form-item>
      <a-form-item ref="beanName" label="目标" name="beanName">
        <a-input v-model:value="formState.beanName" />
      </a-form-item>
      <a-form-item ref="time" label="耗时" name="time">
        <a-input v-model:value="formState.time" />
      </a-form-item>
      <a-form-item ref="createTime" label="时间" name="createTime">
        <a-input v-model:value="formState.createTime" />
      </a-form-item>
      <a-form-item label="状态" name="state">
        <a-switch v-model:checked="formState.state" />
      </a-form-item>
    </a-form>
  </a-modal>
</template>
<script>
import { defineComponent, reactive, ref, watch } from "vue";
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
    
    const formState = reactive({});

    watch(props, (props) => {
        formState.jobName = props.record.jobName
        formState.beanName = props.record.beanName
        formState.time = props.record.time
        formState.createTime = props.record.createTime
        formState.state = props.record.state
    })

    const formRules = { };

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