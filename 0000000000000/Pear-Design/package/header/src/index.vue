<template>
  <header class="p-header" :style="{ height }">
    <slot></slot>
  </header>
</template>
<script>
import "./index.scss";

export default{
  name: 'p-header',
  props: {
    height: {
      type: String,
      default: '60px'
    }
  }
};
</script>