<template>
  <a-modal
    :visible="visible"
    title="新增租户"
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
      <a-tabs v-model:activeKey="state.active">
        <a-tab-pane key="0" tab="租户信息">
          <a-form-item ref="name" label="名称" name="name">
            <a-input v-model:value="formState.tenant.name" />
          </a-form-item>
          <a-form-item label="备注" name="describe">
            <a-textarea v-model:value="formState.tenant.describe" />
          </a-form-item>
        </a-tab-pane>
        <a-tab-pane key="1" tab="账户信息">
          <a-form-item ref="username" label="账户" name="username">
            <a-input v-model:value="formState.user.username" />
          </a-form-item>
          <a-form-item ref="password" label="密码" name="password">
            <a-input v-model:value="formState.user.password" />
          </a-form-item>
        </a-tab-pane>
        <a-tab-pane key="2" tab="默认权限">
          <a-tree
            checkable
            checkStrictly
            show-line="true"
            :tree-data="state.powers"
            :replace-fields="state.powerReplaceFields"
            v-model:checkedKeys="state.powerIds"
          />
        </a-tab-pane>
      </a-tabs>
    </a-form>
  </a-modal>
</template>
<script>
import { message } from "ant-design-vue";
import { save } from "@/api/module/tenant";
import { tree } from "@/api/module/power";
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
      tenant: {},
      user: {},
    });

    const state = reactive({
      powers: [],
      powerIds: { checked: [] },
      powerReplaceFields: { key: "id" },
      deptReplaceFields: { key: "id", title: "name" },
    });

    const formRules = {};

    const submit = (e) => {
      message.loading({
        content: "提交中...",
        key,
      });
      formRef.value
        .validate()
        .then(() => {
          save({user:formState.user,tenant:formState.tenant,powerIds:state.powerIds.checked}).then((response) => {
            if (response.success) {
              message
                .success({
                  content: "保存成功",
                  key,
                  duration: 1,
                })
                .then(() => {
                  cancel();
                });
            } else {
              message
                .error({
                  content: "保存失败",
                  key,
                  duration: 1,
                })
                .then(() => {
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

    const loadPower = async () => {
      var response = await tree();
      state.powers = response.data;
    };

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
    };
  },
});
</script>