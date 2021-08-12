<template>
  <page-layout>
    <a-row :gutter="[10, 10]">
      <!-- 顶部区域 -->
      <a-col :span="24">
        <a-card>
          <!-- 查询参数 -->
          <pro-query :searchParam="searchParam" @on-search="search"></pro-query>
        </a-card>
      </a-col>
      <!-- 中心区域 -->
      <a-col :span="24">
        <a-card>
          <!-- 列表 -->
          <pro-table
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
    </a-row>
    <save :visible="state.visibleSave" @close="closeSave"></save>
    <edit :visible="state.visibleEdit" @close="closeEdit" :record="state.recordEdit"></edit>
    <give :visible="state.visibleGive" @close="closeGive" :record="state.recordGive"></give>
    <info :visible="state.visibleInfo" @close="closeInfo" :record="state.recordInfo"></info>
  </page-layout>
</template>
<script>
import save from './modal/save';
import edit from './modal/edit';
import give from './modal/give';
import info from './modal/info';
import { message , modal} from 'ant-design-vue';
import { ExclamationCircleOutlined } from '@ant-design/icons-vue';
import { page, remove, removeBatch } from "@/api/module/role";
import { reactive, createVNode, ref } from 'vue';

const removeKey = "remove";
const removeBatchKey = "removeBatch";

export default {
  components: {
    save,
    edit,
    give,
    info,
  },
  setup() {

    const tableRef = ref();

    const switchFormat = { yes: true, no: false, event: function(value,record){
      record.enable = !record.enable;
      return value;
    }}

    const columns = [
      { dataIndex: "name", key: "name", title: "名称" },
      { dataIndex: "code", key: "code", title: "标识" },
      { dataIndex: "remark", key: "remark", title: "描述" },
      { dataIndex: "enable", key: "enable", title: "状态", switch: switchFormat },
      { dataIndex: "sort", key: "sort", title: "排序" },
    ];

    /// 数据来源
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
        title: '您是否确定要删除此角色?',
        icon: createVNode(ExclamationCircleOutlined),
        okText: '确定',
        cancelText: '取消',
        onOk() {
          message.loading({ content: "提交中...", key: removeKey });
          remove({"id":record.id}).then((response) => {
            if(response.success){
              message.success({content: "删除成功", key: removeKey, duration: 1}).then(() => 
                tableRef.value.reload()
              )
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
        title: '您是否确定要删除选择角色?',
        icon: createVNode(ExclamationCircleOutlined),
        okText: '确定',
        cancelText: '取消',
        onOk() {
          message.loading({ content: "提交中...", key: removeBatchKey });
          removeBatch({"ids":ids}).then((response) => {
            if(response.success){
              message.success({content: "删除成功", key: removeBatchKey, duration: 1}).then(() => 
                tableRef.value.reload()
              )
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
      { label: "查看", event: function (record) { state.visibleInfo = true, state.recordInfo = record }},
      { label: "修改", event: function (record) { state.visibleEdit = true, state.recordEdit = record }},
      { label: "分配", event: function (record) { state.visibleGive = true, state.recordGive = record }},
      { label: "删除", event: function (record) { removeMethod(record) }},
    ];

    /// 分页参数
    const pagination = { pageNum: 1, pageSize: 10 };

    /// 外置参数
    const state = reactive({
      selectedRowKeys: [],
      param: { name: "", code: "" },
      visibleSave: false,
      visibleEdit: false,
      visibleGive: false,
      visibleInfo: false,
      recordEdit: {},
      recordGive: {},
      recordInfo: {},
    });

    /// 查询参数
    const searchParam = [
      { key: "name", type: "input", label: "名称" },
    ];

    /// 查询操作
    const search = function (value) {
      state.param = value;
      tableRef.value.reload();
    };

    const closeSave = function(){
        state.visibleSave = false;
        tableRef.value.reload();
    }

    const closeEdit = function(){
        state.visibleEdit = false;
        tableRef.value.reload(); 
    }

    const closeGive = function(){
        state.visibleGive = false;
        tableRef.value.reload();
    }

    const closeInfo = function(){
        state.visibleInfo = false;
    }

    const onSelectChange = selectedRowKeys => {
      state.selectedRowKeys = selectedRowKeys;
    };

    return {
      state,
      fetch,
      search,
      toolbar,
      columns, 
      operate,
      pagination, 
      searchParam, 
      onSelectChange,

      closeSave,
      closeEdit,
      closeGive,
      closeInfo,

      tableRef
    };
  },
};
</script>