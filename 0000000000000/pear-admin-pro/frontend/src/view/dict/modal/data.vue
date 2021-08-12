<template>
  <div>
    <pro-table v-if="visible"
        ref="tableChilrenRef"
        rowKey="id"
        :fetch="fetch"
        :columns="columns"
        :toolbar="toolbar"
        :operate="operate"
        :param="state.param"
        :pagination="pagination"
        :row-selection="{ selectedRowKeys: state.selectedRowKeys, onChange: onSelectChange }"
    >
    </pro-table>
    <a-empty v-else />
    <save :visible="state.visibleSave" @close="closeSave" :record="state.recordSave"></save>
    <edit :visible="state.visibleEdit" @close="closeEdit" :record="state.recordEdit"></edit>
  </div>
</template>
<script>
import save from "./data/save";
import edit from "./data/edit";
import { message , modal} from 'ant-design-vue';
import { ExclamationCircleOutlined } from '@ant-design/icons-vue';
import { page, remove, removeBatch } from "@/api/module/dictData";
import { reactive, createVNode, watch, ref} from 'vue';

const removeKey = "remove";
const removeBatchKey = "removeBatch";

export default {
  props: {
      visible: {
          type: Boolean,
          default: false
      },
      record: {
          type: Object
      }
  },
  components: {
    save,
    edit,
  },
  setup(props) {

    const tableChilrenRef = ref()

    watch(props,(props) => {
        state.param.code = props.record.code;
    })

    const switchFormat = { yes: true, no: false, event: function(value,record){
      record.enable = !record.enable;
      return value;
    }}

    const columns = [
      { dataIndex: "label", key: "label", title: "名称" },
      { dataIndex: "value", key: "value", title: "标识" },
      { dataIndex: "enable", key: "enable", title: "状态", switch: switchFormat },
    ];

    /// 数据来源 [模拟]
    const fetch = async (param) => {
      var response = await page(param);
      return {
        total: response.data.total,
        data: response.data.record,
      };
    };

    /// 删除角色
    const removeMethod = (record) => {
      modal.confirm({
        title: '您是否确定要删除此字典?',
        icon: createVNode(ExclamationCircleOutlined),
        okText: '确定',
        cancelText: '取消',
        onOk() {
          message.loading({ content: "提交中...", key: removeKey });
          remove({"id":record.id}).then((response) => {
            if(response.success){
              message.success({content: "删除成功", key: removeKey, duration: 1}).then(()=> {
                tableChilrenRef.value.reload()
              })
            }else{
              message.error({content: "删除失败", key: removeKey, duration: 1})
            }
          })
        }
      });
    }

    /// 批量删除
    const removeBatchMethod = (ids) => {
       modal.confirm({
        title: '您是否确定要删除选择字典?',
        icon: createVNode(ExclamationCircleOutlined),
        okText: '确定',
        cancelText: '取消',
        onOk() {
          message.loading({ content: "提交中...", key: removeBatchKey });
          removeBatch({"ids":ids}).then((response) => {
            if(response.success){
              message.success({content: "删除成功", key: removeBatchKey, duration: 1}).then(()=> {
                tableChilrenRef.value.reload()
              })
            }else{
              message.error({content: "删除失败", key: removeBatchKey, duration: 1})
            }
          })
        }
      });
    }

    /// 工具栏
    const toolbar = [
      { label: "新增", event: function () { state.visibleSave = true, state.recordSave = props.record }},
      { label: "删除", event: function () { removeBatchMethod(state.selectedRowKeys) }},
    ];

    /// 行操作
    const operate = [
      { label: "修改", event: function (record) { state.visibleEdit = true, state.recordEdit = record }},
      { label: "删除", event: function (record) { removeMethod(record) }},
    ];

    const pagination = { pageNum: 1, pageSize: 10 };

    const state = reactive({
      selectedRowKeys: [],
      param: { name: "", code: "" },
      visibleEdit: false,
      visibleSave: false,
      recordEdit: {},
      recordSave: {},
    });

    const onSelectChange = selectedRowKeys => {
      state.selectedRowKeys = selectedRowKeys;
    };

    const closeSave = function(){
      state.visibleSave = false
      tableChilrenRef.value.reload()
    }

    const closeEdit = function(){
      state.visibleEdit = false
      tableChilrenRef.value.reload()
    }

    return {
      state,
      fetch, 
      toolbar,
      columns,
      operate,
      pagination,
      onSelectChange,

      closeSave,
      closeEdit,

      tableChilrenRef
    };
  },
};
</script>