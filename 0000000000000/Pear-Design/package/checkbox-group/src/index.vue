<template>
  <label class="checkbox-group" name="checkbox-group">
    <slot></slot>
  </label>
</template>
<script>
import "./index.scss";
export default{
  name: "p-checkbox-group",
  data(){
      return {
          type:"group"
      }
  },
  props: {
    modelValue: { type: Array, required: true },
    disabled: { type: Boolean }
  },
  watch: {
    modelValue(newValue) {
      this.$emit("change", newValue);
      this.$emit("update:modelValue", newValue);
    }
  },
  methods: {
    selectItem(item) {
      const { modelValue } = this;
      this.$emit("select", [...modelValue, item]);
      this.$emit("update:modelValue", [...modelValue, item]);
    },
    deleteItem(item) {
      const { modelValue: selectItems } = this;
      this.$emit(
        "select",
        selectItems.filter(selectitem => selectitem !== item)
      );
      this.$emit(
        "update:modelValue",
        selectItems.filter(selectitem => selectitem !== item)
      );
    }
  }
};
</script>