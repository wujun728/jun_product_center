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
import { page, remove, removeBatch, resume, pause } from "@/api/module/job";
import { reactive, createVNode } from 'vue';

const removeKey = "remove";
const removeBatchKey = "removeBatch";

export default {
  components: {
    save,
    edit,
    info,
  },
  setup() {

    /// 开关
    const switchFormat = { yes: true, no: false, event: function(value, record){

        if(value) {

           resume({"id": record.id}).then((response) => {
               if(response.success){
                   record.enable = value;
               }
           })
        } else {
           pause({"id": record.id}).then((response) => {
               if(response.success){
                   record.enable = value;
               }
           })
        }

    }};

    /// 列配置
    const columns = [
      { dataIndex: "name", key: "name", title: "名称" },
      { dataIndex: "beanName", key: "beanName", title: "运行类" },
      { dataIndex: "cronExpression", key: "cronExpression", title: "任务表达式" },
      { dataIndex: "enable", key: "enable", title: "状态", switch: switchFormat},
      { dataIndex: "remark", key: "remark", title: "备注" },
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
        title: '您是否确定要删除此任务?',
        icon: createVNode(ExclamationCircleOutlined),
        okText: '确定',
        cancelText: '取消',
        onOk() {
          message.loading({ content: "提交中...", key: removeKey });
          remove({"id":record.id}).then((response) => {
            if(response.success){
              message.success({content: "删除成功", key: removeKey, duration: 1})
            }else{
              message.error({content: "删除失败", key: removeKey, duration: 1})
            }
          })
        }
      });
    }

    const removeBatchMethod = (ids) => {
       modal.confirm({
        title: '您是否确定要删除选择任务?',
        icon: createVNode(ExclamationCircleOutlined),
        okText: '确定',
        cancelText: '取消',
        onOk() {
          message.loading({ content: "提交中...", key: removeBatchKey });
          removeBatch({"ids":ids}).then((response) => {
            if(response.success){
              message.success({content: "删除成功", key: removeBatchKey, duration: 1})
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
        { key: "name", type: "input", label: "任务名称"},
        { key: "code", type: "input", label: "运行目标"},
    ]

    const search = function(value) {
      state.param = value
    }

    const closeSave = function(){
      state.visibleSave = false
    }

    const closeEdit = function(){
      state.visibleEdit = false
    }

    const closeInfo = function(){
      state.visibleInfo = false
    }

    const onSelectChange = selectedRowKeys => {
      state.selectedRowKeys = selectedRowKeys;
    };

    return {
      state, // 状态共享
      fetch, // 数据回调
      toolbar, // 工具栏
      columns, // 列配置
      operate, // 行操作
      pagination, // 分页配置

      search,
      searchParam, // 查询参数
    
      closeSave,
      closeEdit,
      closeInfo,
      
      onSelectChange
    };
  },
};
</script>