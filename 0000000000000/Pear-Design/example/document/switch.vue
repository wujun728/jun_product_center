<template>
  <div>
    <h3>基础</h3>
    <p-switch v-model="on"></p-switch>
    <h3>禁用</h3>
    <p-switch v-model="off" disabled></p-switch>
    <h3>联动</h3>
    <p-switch v-model="offs"></p-switch>
    <p-switch v-model="offs"></p-switch>
    <h3>事件</h3>
    <p-switch v-model="onss" @change="change"></p-switch>
  </div>
</template>
<script>
export default {
  data() {
    return {
      off: false,
      on: true,
      ons: true,
      offs: false,
      onss: true,
    };
  },
  methods: {
    change(value) {
      alert("当前状态:" + value);
    },
  },
};
</script>
<style>
h3 {
  color: rgba(0, 0, 0, 0.65);
}
</style>