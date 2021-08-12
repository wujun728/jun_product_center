<template>
  <label class="radio-group" name="radio-group">
    <slot></slot>
  </label>
</template>
<script>
import "./index.scss"
export default{
  name: "p-radio-group",
  data(){
      return {
          type: "group"
      }
  },
  props: {
    disabled: { type: Boolean },
    modelValue:{ type: [String,Number]},
  },
  watch: {
    value(newValue) {
      this.$emit("select", newValue);
      this.$emit("update:modelValue", newValue);
    }
  }
};
</script>