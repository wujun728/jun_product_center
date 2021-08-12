<template>
  <button
    @click="clickHandle"
    class="p-button"
    :class="
      [ type ? 'p-button-' + type : '',
        size ? 'p-button-' + size : '',
        load ? 'p-button-loading' : '',
        round ? 'p-button-round' : '',
        disabled ? 'p-button-disabled' : '']"
    :disabled="disabled"
  >
    <p-icon v-if="load" class="load" type="loading"></p-icon>
    <p-icon
      v-if="iconAlign == 'left'"
      :type="icon"
    ></p-icon>
    <span class="text">
      <slot></slot>
    </span>
    <p-icon
      v-if="iconAlign == 'right'"
      :type="icon"
    ></p-icon>
  </button>
</template>
<script lang="ts">
import "./index.scss";
export default {
  name: "p-button",
  props: {
    type: {
      type: String,
      default: "default",
    },
    size: {
      type: String,
      default: "md",
    },
    icon: {
      type: String,
      default: "",
    },
    load: {
      type: Boolean,
      default: false,
    },
    round: {
      type: Boolean,
      default: false,
    },
    disabled: {
      type: Boolean,
      default: false,
    },
    iconAlign: {
      type: String,
      default: "left"
    },
  },
  emits: ["click"],
  setup(props, context) {
    const clickHandle = function () {
      context.emit("click");
    };
    return {
      clickHandle,
    };
  },
};
</script>