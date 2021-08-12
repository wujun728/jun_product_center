<template>
  <div class="p-input-number">
    <p-button icon="minus-bold" ripple @click="minus"></p-button>
    <input type="text" @change="changeValue"  :value="current_num" disabled />
    <p-button icon="add-bold" ripple @click="add"></p-button>
  </div>
</template>

<script>
import "./index.scss";
import {ref} from "vue";
export default{
  name: "p-input-number",
  props: {
    min: {
      type: [Number]
    },
    max: {
      type: [Number]
    },
    modelValue: {
      type: [Number],
      default: 0,
    },
    step: {
      type: [Number],
      default: 1,
    }
  },
  setup(props,context) {
    const current_num = ref(Number(props.modelValue));

    const minus = function() {
      if (Number(current_num.value) <= Number(props.min)) {
        current_num.value = Number(props.min);
      } else {
        current_num.value = current_num.value - Number(props.step);
      }
      context.emit("update:modelValue",current_num.value);
    };

    const add = function() {
      if (Number(current_num.value) >= Number(props.max)) {
        current_num.value = Number(props.max);
      } else {
        current_num.value = current_num.value + Number(props.step);
      }
      context.emit("update:modelValue",current_num.value);
    };

    return {
      current_num,
      minus,
      add,
    };
  },
};
</script>