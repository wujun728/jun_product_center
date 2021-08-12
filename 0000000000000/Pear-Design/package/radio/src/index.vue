<template>
  <label
    :class="[
        'p-radio',
        { 'is-checked': value == model },
        { 'is-disabled': isDisabled }
    ]"
    @click.stop="handleClick">
    <span class="p-radio-item"></span>
    <input
       v-if="false"
       type="radio"
      :disabled="isDisabled"
       v-bind="$attrs"
      :value="model"
      @click.stop
    />
    <slot></slot>
  </label>
</template>
<script>
import "./index.scss";
export default{
  name: "p-radio",
  props: {
    value: {
      type: [String, Number],
      required: true,
    },
    disabled: {
      type: [Boolean],
      default: false,
    },
    modelValue: {
      type: [String, Number],
    }
  },
  computed: {
    isGroup() {
      return this.$parent.type === "group";
    },
    isDisabled() {
      return this.$parent.disabled || this.disabled;
    },
    model: {
      get() {
          if(this.isGroup){
              return this.$parent.modelValue;
          }else{
              return this.modelValue;
          }
      },
      set(newValue) {
        if (this.isGroup) {
          this.$parent.$emit("select", newValue);
          this.$parent.$emit("update:modelValue", newValue);
        } else {
          this.$emit("select", newValue);
          this.$emit("update:modelValue", newValue);
        }
      },
    },
  },
  methods: {
    handleClick() {
        console.log(this.model+":"+this.value)
      !this.isDisabled && (this.model = this.value);
    },
  },
};
</script>