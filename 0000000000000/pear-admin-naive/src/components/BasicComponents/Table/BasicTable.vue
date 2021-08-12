<template>
  <n-grid :cols="1">
    <n-gi class="flex flex-row items-center justify-between">
      <div>
        <n-space>
          <slot name="leftContent"> </slot>
        </n-space>
      </div>
      <div>
        <n-space>
          <slot name="rightContent">
            <n-button type="primary">Left Slot Button</n-button>
            <n-button type="primary">Left Slot Button</n-button>
            <n-button type="primary">Left Slot Button</n-button>
          </slot>
        </n-space>
      </div>
    </n-gi>
    <n-gi>
      <n-data-table class="mt-2" v-bind="modifyAttrs" />
    </n-gi>
  </n-grid>
</template>
<script lang="ts">
  export default {
    name: 'BasicTable'
  }
</script>
<script lang="ts" setup>
  import { useAttrs } from 'vue'
  import useProxyFunc from './proxyFunction'

  export interface BasicTableProps {
    api?: string
  }
  /* eslint-disable no-undef */
  const props = withDefaults(defineProps<BasicTableProps>(), {
    api: 'api fetch'
  })

  const attrs = useAttrs()

  // example：代理某个事件
  // 使用前需要确定外层使用该组件传递了该事件
  // 开启 onUpdate:checkedRowkeys的事件代理
  const { attrs: modifyAttrs } = useProxyFunc(
    attrs,
    'onUpdate:checkedRowKeys',
    (args) => {
      // 需要插桩的函数 => 返回的 数据 为暴露给父组件的数据
      console.log(args)
      return args
    }
  )

  // todo: 跟据api来加载表格数据
</script>

<style lang="less" scoped></style>
