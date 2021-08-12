<template>
  <a-card class="success">
    <a-result
      status="success"
      title="提交成功"
      sub-title="提交结果页用于反馈一系列操作任务的处理结果， 如果仅是简单操作，使用 Message 全局提示反馈即可。 本文字区域可以展示简单的补充说明，如果有类似展示 “单据”的需求，下面这个灰色区域可以呈现比较复杂的内容。"
    >
        <div class="desc">
        <a-steps :current="1">
      <template #progressDot="{ index, status, prefixCls }">
        <a-popover>
          <template #content>
            <span>step {{ index }} status: {{ status }}</span>
          </template>
          <span :class="`${prefixCls}-icon-dot`" />
        </a-popover>
      </template>
      <a-step title="创建项目" description="You can hover on the dot." />
      <a-step title="部门初审" description="You can hover on the dot." />
      <a-step title="财务复核" description="You can hover on the dot." />
      <a-step title="完成" description="You can hover on the dot." />
    </a-steps>
      </div>
      <template #extra>
        <a-button key="console" type="primary">返回列表</a-button>
        <a-button key="buy">查看项目</a-button>
        <a-button key="buy">打印</a-button>
      </template>
    </a-result>
  </a-card>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
export default defineComponent({
  name: 'index'
})
</script>

<style lang="less" scoped>
.success{
  margin:10px
}
</style>
