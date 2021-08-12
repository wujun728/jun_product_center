<template>
  <footer class="p-footer"  :style="{ height }">
    <slot></slot>
  </footer>
</template>
<script>
import "./index.scss";
export default{
  name: 'p-footer',
  props: {
    height: {
      type: String,
      default: '60px'
    }
  }
};
</script>
