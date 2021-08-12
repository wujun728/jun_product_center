<template>
  <a-modal
    :visible="visible"
    title="新增权限"
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
      <a-form-item label="上级" name="parent">
        <a-tree-select
          v-model:value="formState.parent"
          style="width: 100%;"
          :dropdown-style="{ maxHeight: '360px', overflow: 'auto' }"
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
      <a-form-item v-if="state.showComponent" ref="component" label="组件" name="component">
        <a-input v-model:value="formState.component" />
      </a-form-item>
      <a-form-item v-if="state.showPath" ref="path" label="路径" name="path">
        <a-input v-model:value="formState.path" />
      </a-form-item>
      <a-form-item v-if="state.showCode" ref="code" label="标识" name="code">
        <a-input v-model:value="formState.code" />
      </a-form-item>
      <a-form-item ref="i18n" label="i18n" name="i18n">
        <a-input v-model:value="formState.i18n" />
      </a-form-item>
      <a-form-item ref="type" label="类型" name="type">
        <a-select v-model:value="formState.type" @change="change">
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
import { message } from "ant-design-vue";
import { save, tree } from "@/api/module/power";
import { defineComponent, reactive, ref, toRaw, watch } from "vue";
export default defineComponent({
  props: {
    visible: {
      type: Boolean,
    },
  },
  emit: ["close"],
  setup(props, context) {

    const state = reactive({
      depts: [],
      showComponent: true,
      showPath: true,
      showCode: false
    });

    const formRef = ref();

    const formState = reactive({
      type: "0",
      sort: 0,
      parent: "0",
      enable: true,
    });

    const formRules = {
      title: [{ required: true, message: "请输入权限名称", trigger: "blur" }],
      i18n: [{ required: true, message: "请输入 i18n", trigger: "blur" }],
      type: [{ required: true, message: "请输入权限类型", trigger: "blur" }],
      parent: [{ required: true, message: "请选择上级权限", trigger: "change" }],
    };

    const submit = (e) => {
      formRef.value
        .validate()
        .then(() => {
          save(toRaw(formState)).then((response) => {
            if (response.success) {
              message.success({ content: "保存成功", duration: 1 }).then(() => {
                cancel();
              });
            } else {
              message.success({ content: "保存失败", duration: 1 }).then(() => {
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

    const loadPower = () => {
      tree({}).then((response) => {
        response.data = [{
          id:"0",
          title: "顶级菜单",
          children: response.data
        }]
        state.powers = response.data;
      });
    };

    const change = function(val) {
      if(val === '2') {
        state.showComponent = false;
        state.showPath = false;
        state.showCode = true;
      } else {
        state.showComponent = true;
        state.showPath = true;
        state.showCode = false;
      }
    }

    loadPower();

    return {
      state,
      submit,
      change,
      cancel,
      formRef,
      formState,
      formRules,

      labelCol: { span: 6 },
      wrapperCol: { span: 18 },

      replaceFields: {
        children: "children",
        title: "title",
        key: "id",
        value: "id",
      },
    };
  },
});
</script>