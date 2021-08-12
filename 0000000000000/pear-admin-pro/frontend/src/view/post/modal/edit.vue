<template>
  <a-modal
    :visible="visible"
    title="新增岗位"
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
      <a-form-item ref="code" label="标识" name="code">
        <a-input v-model:value="formState.code" />
      </a-form-item>
      <a-form-item ref="sort" label="标识" name="sort">
        <a-input-number v-model:value="formState.sort" />
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
import { message } from 'ant-design-vue';
import { edit } from "@/api/module/post";
import { defineComponent, reactive, ref, toRaw, watch } from "vue";
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
        formState.id = props.record.id
        formState.name = props.record.name
        formState.code = props.record.code
        formState.sort = props.record.sort
        formState.remark = props.record.remark
        formState.enable = props.record.enable
    })

    const formRules = {
      name: [ { required: true, message: '请输入岗位名称', trigger: 'blur'} ],
      code: [ { required: true, message: '请输入岗位编号', trigger: 'blur'} ]
    };

    const editKey = "add";

    const submit = (e) => {
      message.loading({ content: '提交中...', key: editKey });
      formRef.value
        .validate()
        .then(() => {
          edit(toRaw(formState)).then((response)=>{
              if(response.success){
                message.success({ content: '保存成功', key: editKey, duration: 1 }).then(()=>{
                  cancel();
                });
              }else{
                message.success({ content: '保存失败', key: editKey, duration: 1 }).then(()=>{
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