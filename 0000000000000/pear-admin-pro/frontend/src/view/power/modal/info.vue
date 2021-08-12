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
      <a-form-item label="上级" name="parent">
          <a-tree-select
            v-model:value="formState.parent"
            style="width: 100%"
            :tree-data="state.powers"
            placeholder="上级权限"
            replace
            tree-default-expand-all
            :replaceFields="replaceFields"
          >
          </a-tree-select>
      </a-form-item>
      <a-form-item ref="title" label="名称" name="title">
        <a-input v-model:value="formState.title" />
      </a-form-item>
      <a-form-item ref="component" label="组件" name="component">
        <a-input v-model:value="formState.component" />
      </a-form-item>
      <a-form-item ref="path" label="路径" name="path">
        <a-input v-model:value="formState.path" />
      </a-form-item>
      <a-form-item ref="i18n" label="i18n" name="i18n">
        <a-input v-model:value="formState.i18n" />
      </a-form-item>
      <a-form-item ref="type" label="类型" name="type">
        <a-select v-model:value="formState.type">
          <a-select-option value="0"> 目录 </a-select-option>
          <a-select-option value="1"> 菜单 </a-select-option>
          <a-select-option value="2"> 按钮 </a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item ref="sort" label="排序" name="sort">
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
import { save, tree } from "@/api/module/power";
import { defineComponent, reactive, ref, toRaw, watch } from "vue";
export default defineComponent({
  props: {
    visible: {
      type: Boolean,
    },
    record: {
      type: Object
    }
  },
  emit: ["close"],
  setup(props, context) {

    const state = reactive({ 
      depts: [], 
    })
    
    const formRef = ref();
    
    const formState = reactive({
      sort: 0,
      enable: true,
    });

    watch(props,(props) => {
      formState.id = props.record.id;
      formState.title = props.record.title;
      formState.component = props.record.component;
      formState.path = props.record.path;
      formState.type = props.record.type;
      formState.enable = props.record.enable;
      formState.i18n = props.record.i18n;
      formState.remark = props.record.remark;
      formState.parent = props.record.parent;
    })

    const formRules = {
      title: [{ required: true, message: '请输入权限名称', trigger: 'blur'}],
      component: [{ required: true, message: '请输入路由组件', trigger: 'blur'}],
      path: [{ required: true, message: '请输入路由地址', trigger: 'blur'}],
      i18n: [{ required: true, message: '请输入 i18n', trigger: 'blur'}],
      type: [{ required: true, message: '请输入权限类型', trigger: 'blur'}],
      parent: [{ required: true, message: '请选择上级权限', trigger: 'change'}]
    };

    const submit = (e) => {
      formRef.value.resetFields();
      context.emit("close", false);
    };

    const cancel = (e) => {
      formRef.value.resetFields();
      context.emit("close", false);
    };

    const loadPower = () => {
      tree({}).then((response)=>{
        state.powers = response.data;
      })
    }

    loadPower();

    return {
      state,
      submit,
      cancel,
      formRef,
      formState,
      formRules,
      
      labelCol: { span: 6 },
      wrapperCol: { span: 18 },

      replaceFields: {children:'children', title:'title', key:'id', value: 'id' }
    };
  },
});
</script>