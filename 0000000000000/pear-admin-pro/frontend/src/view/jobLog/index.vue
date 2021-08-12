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
              :fetch="fetch"
              :columns="columns"
              :toolbar="toolbar"
              :param="state.param"
              :pagination="pagination"
              :operate="operate"
              :row-selection="{ selectedRowKeys: state.selectedRowKeys, onChange: onSelectChange }"
            >
              <!-- 继承至 a-table 的默认插槽 -->
            </pro-table>
          </a-card>
        </a-col>
      </a-row>
      <info :visible="state.visibleInfo" @close="closeInfo" :record="state.recordInfo"></info>
    </page-layout>
</template>

<script>
import info from './modal/info';
import { page } from "@/api/module/jobLog";
import { reactive } from 'vue';

export default {
  components:  {
    info
  },
  setup() {

    /// 文本
    const converFormat = [{label:"成功", value:true},{label:"失败", value:false}];

    /// 列配置
    const columns = [
      { dataIndex: "jobName", key: "jobName", title: "任务" },
      { dataIndex: "beanName", key: "beanName", title: "目标" },     
      { dataIndex: "time", key: "time", title: "耗时"},
      { dataIndex: "createTime", key: "createTime", title: "运行时间" },
      { dataIndex: "state", key: "state", title: "状态", conver: converFormat },
    ];

    const operate = [
      { label: "查看", event: function (record) { state.visibleInfo = true, state.recordInfo = record }},
    ];

    /// 数据来源 [模拟]
    const fetch = async (param) => {
      var response = await page(param);
      return {
        total: response.data.total,
        data: response.data.record,
      };
    };

    /// 工具栏
    const toolbar = [
      { label: "备份", event: function () { }},
    ];

    /// 分页参数
    const pagination = {
      pageNum: 1,
      pageSize: 10,
    }

    /// 外置参数 - 当参数改变时, 重新触发 fetch 函数
    const state = reactive({
      selectedRowKeys: [],
      param: { name: "", code: "", isAuth: true },
      visibleInfo: false,
      recordInfo: {},
    })

    /// 查询参数
    const searchParam = [
        { key: "title", type: "input", label: "标题"},
        { key: "state", type: "select", label: "状态", value: "",
          options: [
            { text: "全部", value: ""},
            { text: "成功", value: true},
            { text: "失败", value: false}
          ]
        }
    ]
    /// 查询操作
    const search = function(value) {
      state.param.title = value.title
      state.param.state = value.state
    }
    
    const onSelectChange = selectedRowKeys => {
      state.selectedRowKeys = selectedRowKeys;
    };

    const closeInfo = function(){
      state.visibleInfo = false
    }

    return {
      state,
      fetch,
      toolbar,
      columns,
      operate,
      pagination,

      search,
      searchParam,
      
      closeInfo,
      onSelectChange,
    };
  },
};
</script>