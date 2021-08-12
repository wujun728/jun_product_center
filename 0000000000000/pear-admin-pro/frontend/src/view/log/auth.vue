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
    </page-layout>
</template>

<script>
import { message , modal} from 'ant-design-vue';
import { ExclamationCircleOutlined } from '@ant-design/icons-vue';
import { page, clean } from "@/api/module/log";
import { reactive, createVNode } from 'vue';

const cleanKey = "clean";

export default {
  setup() {

    /// 文本
    const converFormat = [{label:"成功", value:true},{label:"失败", value:false}];

    /// 列配置
    const columns = [
      { dataIndex: "title", key: "title", title: "标题" },
      { dataIndex: "describe", key: "describe", title: "描述" },
      { dataIndex: "action", key: "action", title: "动作"},
      { dataIndex: "type", key: "type", title: "方式"},
      { dataIndex: "browser", key: "browser", title: "浏览器" },
      { dataIndex: "system", key: "system", title: "系统" },
      { dataIndex: "address", key: "address", title: "操作地" },
      { dataIndex: "operator", key: "operator", title: "操作人" }, 
      { dataIndex: "createTime", key: "createTime", title: "操作时间" },
      { dataIndex: "state", key: "state", title: "状态", conver: converFormat },
    ];

    const operate = [
      { label: "查看", event: function (record) { alert("查看详情:" + JSON.stringify(record))}},
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
    const cleanMethod = (record) => {
      modal.confirm({
        title: '您是否确定要清空日志?',
        icon: createVNode(ExclamationCircleOutlined),
        okText: '确定',
        cancelText: '取消',
        onOk() {
          message.loading({ content: "提交中...", key: cleanKey });
          clean({"isAuth":true}).then((response) => {
            if(response.success){
              message.success({content: "清空成功", key: cleanKey, duration: 1})
            }else{
              message.error({content: "清空失败", key: cleanKey, duration: 1})
            }
          })
        }
      });
    }

    /// 工具栏
    const toolbar = [
      { label: "备份", event: function () { }},
      { label: "清空", event: function () { cleanMethod(); }},
    ];

    /// 分页参数
    const pagination = {
      pageNum: 1,
      pageSize: 10,
    }

    /// 外置参数 - 当参数改变时, 重新触发 fetch 函数
    const state = reactive({
      selectedRowKeys: [],
      param: { name: "", code: "", isAuth: true }
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

    return {
      state,
      fetch,
      toolbar,
      columns,
      operate,
      pagination,

      search,
      searchParam,

      onSelectChange,
    };
  },
};
</script>