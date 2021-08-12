<template>
  <Icon
  >
    <template #component="svgProps">
      <svg
        aria-hidden="true"
        v-bind="svgProps"
      >
        <use :xlink:href="iconName"/>
      </svg>
    </template>
  </Icon>
</template>
<script lang="ts">
/**
 description: 自定义svg icon
 props  参数（ 继承ant-design-vue/icons的参数，component参数不继承，因为该组件已使用 ）
 style  设置图标的样式，例如 fontSize 和 color  CSSProperties  -
 spin  是否有旋转动画  boolean  false
 rotate  图标旋转角度（IE9 无效）  number  -
 */
import { computed, defineComponent, reactive, toRefs } from 'vue'
import Icon from '@ant-design/icons-vue'

export default defineComponent({
  name: 'SvgIcon',
  components: {
    Icon
  },
  props: {
    name: { // src/assets/icons/svg下面的图标名称
      type: String,
      required: true
    }
  },
  setup (props) {
    const state = reactive({
      iconName: computed(() => `#icon-${props.name}`)
    })
    return {
      ...toRefs(state)
    }
  }
})
</script>

<style scoped lang="less">
</style>
