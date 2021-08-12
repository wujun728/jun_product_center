<template>
    <page-layout>
      <a-row :gutter="[10, 10]">
        <!-- 查询表单 -->
        <a-col :span="24">
          <a-card>
            <pro-query
              :searchParam="searchParam"
              @on-search ="search"
            ></pro-query>
          </a-card>
        </a-col>
        <!-- 岗位列表 -->
        <a-col :span="24">
          <a-card>
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
      <info :visible="state.visibleInfo" @close="closeInfo" :record="state.recordInfo"></info>
    </page-layout>
</template>

<script>
import save from './modal/save';
import edit from './modal/edit';
import info from './modal/info';
import { message , modal} from 'ant-design-vue';
import { ExclamationCircleOutlined } from '@ant-design/icons-vue';
import { page, remove, removeBatch } from "@/api/module/post";
import { reactive, createVNode, ref } from 'vue';

const removeKey = "remove";
const removeBatchKey = "removeBatch";

export default {
  components: {
    save,
    edit,
    info,
  },
  setup() {

    const tableRef = ref();

    /// 开关
    const switchFormat = { yes: true, no: false };

    /// 列配置
    const columns = [
      { dataIndex: "name", key: "name", title: "名称" },
      { dataIndex: "code", key: "code", title: "标识" },
      { dataIndex: "remark", key: "remark", title: "备注" },
      { dataIndex: "enable", key: "enable", title: "状态", switch: switchFormat},
      { dataIndex: "sort", key: "sort", title: "排序" },
    ];

    /// 数据来源 [模拟]
    const fetch = async (param) => {
      var response = await page(param);
      return {
        total: response.data.total,
        data: response.data.record,
      };
    };

    /// 删除配置
    const removeMethod = (record) => {
      modal.confirm({
        title: '您是否确定要删除此配置?',
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

    const removeBatchMethod = (ids) => {
       modal.confirm({
        title: '您是否确定要删除选择配置?',
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
      { label: "查看", event: function (record) { state.visibleInfo = true, state.recordInfo = record }},
      { label: "修改", event: function (record) { state.visibleEdit = true, state.recordEdit = record }},
      { label: "删除", event: function (record) { removeMethod(record) }},
    ];

    /// 分页参数
    const pagination = {
      pageNum: 1,
      pageSize: 10,
    }

    /// 外置参数 - 当参数改变时, 重新触发 fetch 函数
    const state = reactive({
      selectedRowKeys: [],
      param: {},
      visibleSave: false,
      visibleEdit: false,
      visibleInfo: false,
      recordEdit: {},
      recordInfo: {},
    })
    
    const searchParam = [
        { key: "name", type: "input", label: "岗位名称"},
        { key: "code", type: "input", label: "岗位编码"},
    ]

    const search = function(value) {
      state.param = value
      tableRef.value.reload()
    }

    const closeSave = function(){
      state.visibleSave = false
      tableRef.value.reload()
    }

    const closeEdit = function(){
      state.visibleEdit = false
      tableRef.value.reload()
    }

    const closeInfo = function(){
      state.visibleInfo = false
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
      closeInfo,

      tableRef
    };
  },
};
</script>