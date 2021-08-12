<template>
  <div :class="['slider', { 'is-disabled': disabled }]">
    <div v-if="!isIE" class="slider__progress" :style="progressStyle"></div>
    <input
      :class="['slider__inner', { 'is-disabled': disabled }]"
      :disabled="disabled"
      :min="min"
      :max="max"
      type="range"
      v-model="rate"
      v-bind="$attrs"
    >
    <span v-if="!isIE && showTooltip" class="tooltip" :style="toolTipPosition">{{ rate }}</span>
  </div>
</template>

<script>
import "./index.scss";
export default {
  name: "p-slider",
  props: {
    showTooltip: { type: Boolean, default: true },
    disabled: { type: Boolean, default: false },
    min: { type: Number, default: 0 },
    max: { type: Number, default: 100 },
    modelValue: { type: [Number, String] }
  },
  data() {
    return {
      rate: 0
    };
  },
  computed: {
    isIE() {
      return (
        !!window.ActiveXObject ||
        "ActiveXObject" in window ||
        navigator.userAgent.indexOf("Edge") > -1
      );
    },
    progressStyle() {
      const { rate, max, min } = this;
      return {
        width: `${((rate - min) * 100) / (max - min)}%`
      };
    },
    toolTipPosition() {
      const { rate, max, min } = this;
      const xOffset = 9 - 18 * ((rate - min) / (max - min));
      return {
        left: `${((rate - min) * 100) / (max - min)}%`,
        marginLeft: `${xOffset}px`,
        transform: `translateX(-50%)`
      };
    }
  },
  watch: {
    rate(value) {
      this.$emit("sliding", Number(value));
    },
    modelValue: {
      handler(value) {
        this.rate = value || this.min;
      },
      immediate: true
    }
  }
};
</script>