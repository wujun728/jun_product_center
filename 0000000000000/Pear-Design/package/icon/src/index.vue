<template>
  <i class="pear-icon" 
  :class="[type ? 'pear-icon-' + type : '']"
  :style="{'font-size': size + 'px'}">
    <slot></slot>
  </i>
</template>

<script lang="ts">
import "./index.scss";
export default{
  name: "p-icon",
  props: {
    type: String,
    size: String
  }
};
</script>