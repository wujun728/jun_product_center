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
        <!-- 多库列表 -->
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
      <!-- 新增页面 -->
      <save :visible="state.visibleSave" @close="closeSave"></save>
      <!-- 修改页面 -->
      <edit :visible="state.visibleEdit" @close="closeEdit" :record="state.recordEdit"></edit>
    </page-layout>
</template>

<script>
import save from './modal/save';
import edit from './modal/edit';
import { message , modal} from 'ant-design-vue';
import { ExclamationCircleOutlined } from '@ant-design/icons-vue';
import { page, remove, removeBatch } from "@/api/module/dataSource";
import { reactive, createVNode, ref } from 'vue';

const removeKey = "remove";
const removeBatchKey = "removeBatch";

export default {
  components: {
    save,
    edit,
  },
  setup() {

    const tableRef = ref()

    /// 列配置
    const columns = [
      { dataIndex: "name", key: "name", title: "名称" },
      { dataIndex: "username", key: "username", title: "账户" },
      { dataIndex: "password", key: "password", title: "密码"},
      { dataIndex: "url", key: "url", title: "链接"},
      { dataIndex: "driver", key: "driver", title: "驱动"},
      { dataIndex: "remark", key: "remark", title: "备注"},
      { dataIndex: "createTime", key: "createTime", title: "创建日期" },
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
      { label: "查看", event: function (record) { alert("查看详情:" + JSON.stringify(record))}},
      { label: "修改", event: function (record) { state.recordEdit = record, state.visibleEdit = true }},
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
      recordEdit: {},
    })

    /// 查询参数
    const searchParam = [
        { key: "name", type: "input", label: "名称"},
        { key: "code", type: "input", label: "描述"},
    ]

    /// 查询操作
    const search = function(value) {
      state.param = value
      tableRef.value.reload
    }

    const onSelectChange = selectedRowKeys => {
      state.selectedRowKeys = selectedRowKeys;
    };

    const closeSave = function(){
      state.visibleSave = false
      tableRef.value.reload
    }

    const closeEdit = function(){
      state.visibleEdit = false
      tableRef.value.reload
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
    
      tableRef
    };
  },
};
</script>