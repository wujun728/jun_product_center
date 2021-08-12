<template>
  <a-modal
    :visible="visible"
    title="权限配置"
    cancelText="取消"
    okText="保存"
    @ok="submit"
    @cancel="cancel"
  >
       <a-tree
          checkable
          checkStrictly
          show-line="true"
          :tree-data="state.powers"
          :replace-fields="state.powerReplaceFields"
          v-model:checkedKeys="state.checkedPowerIds"
        />
  </a-modal>
</template>
<script>
import { message } from "ant-design-vue";
import { tree as powerTree } from "@/api/module/power";
import { power, give } from "@/api/module/tenant";
import { defineComponent, reactive, ref, watch } from "vue";
export default defineComponent({
  props: {
    visible: {
      type: Boolean,
    },
    record: {
      type: Object,
    },
  },
  emit: ["close"],
  setup(props, context) {

    const state = reactive({
      powers: [],
      checkedPowerIds: {checked:[]},
      powerReplaceFields: { key: "id" },
    });

    const loadPower = async () => {
      var response = await powerTree();
      state.powers = response.data;
    };

    /// 监听数据
    watch(props, (props) => {
      let powerIds = [];
      power({ tenantId: props.record.id }).then((response) => {
        response.data.forEach((element) => {
          powerIds.push(element.id);
        });
        state.checkedPowerIds = powerIds;
      });
    });

    loadPower();

    const submit = (e) => {
      give({ 
        tenantId: props.record.id,
        powerIds: state.checkedPowerIds.checked?state.checkedPowerIds.checked:state.checkedPowerIds}).then(
        (response) => {
          if (response.success) {
            message.success({ content: "保存成功", duration: 1 }).then(() => {
              cancel();
            });
          } else {
            message.error({ content: "保存失败", duration: 1 }).then(() => {
              cancel();
            });
          }
        }
      );
    };

    const cancel = (e) => {
      context.emit("close", false);
    };

    return {
      state,
      submit,
      cancel,
    };
  },
});
</script>