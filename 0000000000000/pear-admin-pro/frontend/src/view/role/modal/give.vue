<template>
  <a-modal
    :visible="visible"
    title="权限配置"
    cancelText="取消"
    okText="保存"
    @ok="submit"
    @cancel="cancel"
  >
    <a-tabs v-model:activeKey="state.active">
      <a-tab-pane key="1" tab="菜单权限">
        <a-tree
          checkable
          checkStrictly
          show-line="true"
          :tree-data="state.powers"
          :replace-fields="state.powerReplaceFields"
          v-model:checkedKeys="state.checkedPowerIds"
        />
      </a-tab-pane>
      <a-tab-pane key="2" tab="数据权限" force-render>
        <a-select v-model:value="scope" style="width: 100%">
          <a-select-option value="ALL">全部</a-select-option>
          <a-select-option value="SELF">仅自己</a-select-option>
          <a-select-option value="DEPT">所在部门</a-select-option>
          <a-select-option value="DEPT_CHILD">所在部门及下级部门</a-select-option>
          <a-select-option value="CUSTOM">自定义数据</a-select-option>
        </a-select>
        <br/>
        <br/>
        <a-tree
          checkable
          show-line="true"
          v-if="state.showDept"
          :tree-data="state.depts"
          :replace-fields="state.deptReplaceFields"
          v-model:checkedKeys="state.checkedDeptIds"
        />
      </a-tab-pane>
    </a-tabs>
  </a-modal>
</template>
<script>
import { message } from "ant-design-vue";
import { tree as deptTree } from "@/api/module/dept";
import { assign as powerTree } from "@/api/module/power";
import { power, give, dept } from "@/api/module/role";
import { defineComponent, reactive, ref, watch } from "vue";
import TreeSelect from "ant-design-vue/lib/vc-tree-select";
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

    const scope = ref("ALL");

    const state = reactive({
      depts: [],
      powers: [],
      active: "1",
      showDept: false,
      checkedPowerIds: {checked:[]},
      checkedDeptIds: {checked:[]},
      powerReplaceFields: { key: "id" },
      deptReplaceFields: { key: "id", title: "name" },
    });

    const SHOW_PARENT = TreeSelect.SHOW_PARENT;

    const loadPower = async () => {
      var response = await powerTree();
      state.powers = response.data;
    };

    const loadDept = async () => {
      var response = await deptTree();
      state.depts = response.data;
    };

    /// 监听数据
    watch(scope, (scope) => {
      if(scope === "CUSTOM") {
        state.showDept = true;
      } else {
        state.checkedDeptIds = [];
        state.showDept = false;
      }
    })

    /// 监听数据
    watch(props, (props) => {
      let powerIds = [];
      power({ roleId: props.record.id }).then((response) => {
        response.data.forEach((element) => {
          powerIds.push(element.id);
        });
        state.checkedPowerIds = powerIds;
      });
      let deptIds = [];
      dept({ roleId: props.record.id}).then((response) => {
        response.data.forEach((element) => {
          deptIds.push(element.id);
        })
        state.checkedDeptIds = deptIds;
      })
      scope.value = props.record.scope;
    });

    loadPower();
    loadDept();

    const submit = (e) => {
      give({ 
        scope: scope.value,
        roleId: props.record.id,
        powerIds: state.checkedPowerIds.checked?state.checkedPowerIds.checked:state.checkedPowerIds,
        deptIds: state.checkedDeptIds.checked?state.checkedDeptIds.checked:state.checkedDeptIds }).then(
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
      scope,
      state,
      submit,
      cancel,
      SHOW_PARENT
    };
  },
});
</script>