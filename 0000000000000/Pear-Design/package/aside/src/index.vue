<template>
  <aside class="p-aside"  :style="{ width }">
    <slot></slot>
  </aside>
</template>
<script>
import "./index.scss";
export default{
  name: 'p-aside',
  props: {
    width: {
      type: String,
      default: '220px'
    }
  }
};
</script>