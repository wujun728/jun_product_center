<template>
  <div>
    <page-header
      title="数 据 字 典"
      describe="用 户 Online 列 表，用 于 系 统 在 线 用 户 监 控."
    ></page-header>
    <page-layout>
      <a-row :gutter="[10, 10]">
        <a-col :span="12">
          <a-card>
          <!-- 列表 -->
          <pro-table
            rowKey="id"
            ref="tableRef"
            :fetch="fetch"
            :columns="columns"
            :toolbar="toolbar"
            :operate="operate"
            :param="state.param"
            :pagination="pagination"
            :row-selection="{ selectedRowKeys: state.selectedRowKeys, onChange: onSelectChange }"
          >
          </pro-table>
          </a-card>
        </a-col>
        <a-col :span="12">
          <a-card>
            <dictData :visible="state.visibleData" :record="state.recordData"></dictData>
          </a-card>
        </a-col>
      </a-row>
    </page-layout>
    <save :visible="state.visibleSave" @close="closeSave"></save>
    <edit :visible="state.visibleEdit" @close="closeEdit" :record="state.recordEdit"></edit>
  </div>
</template>
<script>
import save from "./modal/save";
import edit from "./modal/edit";
import data from "./modal/data";
import { message , modal} from 'ant-design-vue';
import { ExclamationCircleOutlined } from '@ant-design/icons-vue';
import { page, remove, removeBatch } from "@/api/module/dict";
import { reactive, createVNode, ref } from 'vue';

const removeKey = "remove";
const removeBatchKey = "removeBatch";

export default {
  components: {
    save,
    edit,
    dictData: data,
  },
  setup() {

    const tableRef = ref()

    const switchFormat = { yes: true, no: false, event: function(value,record){
      record.enable = !record.enable;
      return value;
    }}

    const columns = [
      { dataIndex: "name", key: "name", title: "名称" },
      { dataIndex: "code", key: "code", title: "标识" },
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
              message.success({content: "删除成功", key: removeKey, duration: 1}).then(()=>{
                tableRef.value.reload()
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
              message.success({content: "删除成功", key: removeBatchKey, duration: 1}).then(()=>{
                tableRef.value.reload()
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
      { label: "新增", event: function () { state.visibleSave = true }},
      { label: "删除", event: function () { removeBatchMethod(state.selectedRowKeys) }},
    ];

    /// 行操作
    const operate = [
      { label: "查看", event: function (record) { state.visibleData = true, state.recordData = record }},
      { label: "修改", event: function (record) { state.visibleEdit = true, state.recordEdit = record }},
      { label: "删除", event: function (record) { removeMethod(record) }},
    ];

    const pagination = { pageNum: 1, pageSize: 10 };

    const state = reactive({
      selectedRowKeys: [],
      param: { name: "", code: "" },
      visibleEdit: false,
      visibleSave: false,
      visibleData: false,
      recordData: {},
      recordEdit: {},
    });

    const onSelectChange = selectedRowKeys => {
      state.selectedRowKeys = selectedRowKeys;
    };

    const closeSave = function(){
      state.visibleSave = false
      tableRef.value.reload()
    }

    const closeEdit = function(){
      state.visibleEdit = false
      tableRef.value.reload()
    }

    return {
      state,
      fetch,
      toolbar,
      columns,
      operate,
      closeSave,
      closeEdit,
      pagination,
      onSelectChange,
      tableRef
    };
  },
};
</script>