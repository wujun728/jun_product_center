<template>
  <label
    :class="[
      'checkbox',
      { 'is-checked': isChecked },
      { 'is-disabled': isDisabled }
    ]"
    @click.stop="handleClick"
  >
    <span class="checkbox__label">
      <fat-icon name="check" />
    </span>
    <input v-if="false" type="checkbox" v-bind="$attrs" :value="model" @click.stop />
    <slot></slot>
  </label>
</template>

<script>
import "./index.scss";
export default{
  name: "p-checkbox",
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
      type: [String, Number, Boolean],
    },
  },
  computed: {
    isGroup() {
      return this.$parent.type === "group";
    },
    isDisabled() {
      return this.$parent.disabled || this.disabled;
    },
    isChecked() {
      const { isGroup, model } = this;
      if (!isGroup) return model;
      const {
        value,
        $parent: { modelValue: selectItems },
      } = this;
      return selectItems.some((item) => item === value);
    },
    model: {
      get() {
        return this.isGroup ? this.$parent.modelValue : this.modelValue;
      },
      set(newValue) {
        const { isGroup, isChecked } = this;
        if (isGroup) {
          isChecked
            ? this.$parent.deleteItem(newValue)
            : this.$parent.selectItem(newValue);
        } else {
          this.$emit("select", newValue);
          this.$emit("update:modelValue", newValue);
        }
      },
    },
  },
  methods: {
    handleClick() {
      const { isDisabled, isGroup, model, value } = this;
      if (!isDisabled) {
        this.$emit("update:modelValue", !model);
        this.model = isGroup ? value : !model;
      }
    },
  },
};
</script>