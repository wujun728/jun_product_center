<template>
  <section class="p-container" :class="{ 'is-vertical': isVertical }">
    <slot></slot>
  </section>
</template>

<script>
import "./index.scss";
import { computed } from 'vue';
export default{
  name: "p-container",
  props: {
    direction: {
      type:String,
      default:''
    }
  },
  setup(props, { slots }) {
    const isVertical = computed(() => {
      if (props.direction === 'vertical') {
        return true
      } else if (props.direction === 'horizontal') {
        return false
      }
      if (slots && slots.default) {
        const  vNodes= slots.default()
        return vNodes.some(vNode => {
          const tag = vNode.type.name
          return tag === 'p-header' || tag === 'p-footer'
        })
      } else {
        return false
      }
    })
    return {
      isVertical,
    }
  },
};
</script>