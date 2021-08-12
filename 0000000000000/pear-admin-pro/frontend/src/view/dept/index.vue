<template>
    <page-layout>
      <a-row :gutter="[10, 10]">
        <!-- 顶部区域 -->
        <a-col :span="24">
          <a-card>
            <!-- 查询参数 -->
            <pro-query
              :searchParam="searchParam"
              @on-search ="search"
            ></pro-query>
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
      <info :visible="state.visibleInfo" @close="closeInfo" :record="state.recordInfo"></info>
    </page-layout>
</template>

<script>
import save from "./modal/save";
import edit from "./modal/edit";
import info from "./modal/info";
import { message , modal} from 'ant-design-vue';
import { ExclamationCircleOutlined } from '@ant-design/icons-vue';
import { tree, remove, removeBatch } from "@/api/module/dept";
import { reactive, createVNode, ref } from 'vue';

const removeKey = "remove";

export default {
  components: {
    save,
    edit,
    info,
  },
  setup() {

    const tableRef = ref()

    const switchFormat = { yes: true, no: false, event: function(value,record){
      record.enable = !record.enable;
      return value;
    }}

    const columns = [
      { dataIndex: "name", key: "name", title: "部门" },
      { dataIndex: "address", key: "address", title: "地址" },
      { dataIndex: "enable", key: "enable", title: "状态", switch: switchFormat},
      { dataIndex: "sort", key: "sort", title: "排序" },
      { dataIndex: "remark", key: "remark", title: "备注" },
    ];

    /// 数据来源 [模拟]
    const fetch = async (param) => {
      var response = await tree(param);
      return {
        data: response.data,
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

    /// 工具栏
    const toolbar = [
      { label: "新增", event: function () { state.visibleSave = true }},
    ];

    /// 行操作
    const operate = [
      { label: "查看", event: function (record) { state.visibleInfo = true, state.recordInfo = record }},
      { label: "修改", event: function (record) { state.visibleEdit = true, state.recordEdit = record }},
      { label: "删除", event: function (record) { removeMethod(record) }},
    ];

    const pagination = false;
    
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
        { key: "name", type: "input", label: "名称"},
        { key: "code", type: "input", label: "描述"},
    ]

    const search = function(value) {
      state.param = value
      tableRef.value.reload()
    }

    const onSelectChange = selectedRowKeys => {
      state.selectedRowKeys = selectedRowKeys;
    };

    const closeSave = function() {
      state.visibleSave = false
      tableRef.value.reload()
    }

    const closeEdit = function() {
      state.visibleEdit = false
      tableRef.value.reload()
    }

    const closeInfo = function() {
      state.visibleInfo = false
    }

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