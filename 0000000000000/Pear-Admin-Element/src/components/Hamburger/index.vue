<template>
  <div style="padding: 0 15px;" @click="toggleClick">
    <svg
      :class="{'is-active':isActive}"
      class="hamburger"
      viewBox="0 0 1024 1024"
      xmlns="http://www.w3.org/2000/svg"
      width="64"
      height="64"
    >
      <path d="M972.8 188.16H51.2a40.32 40.32 0 0 1-40.32-40.32 40.32 40.32 0 0 1 40.32-40.32h921.6a40.32 40.32 0 0 1 40.32 40.32 40.32 40.32 0 0 1-40.32 40.32zM972.8 531.2H358.4a33.28 33.28 0 0 1 0-64h614.4a33.28 33.28 0 1 1 0 64zM972.8 888.32H51.2a40.32 40.32 0 0 1-40.32-40.32 40.32 40.32 0 0 1 40.32-40.32h921.6a40.32 40.32 0 0 1 40.32 40.32 40.32 40.32 0 0 1-40.32 40.32zM51.2 673.28a32.64 32.64 0 0 1-23.68-9.6 32.64 32.64 0 0 1 0-46.08L128 517.12 27.52 417.28a32.64 32.64 0 0 1 0-46.08 32 32 0 0 1 46.72 0l122.88 122.88a33.28 33.28 0 0 1 0 46.72l-122.88 122.88a32.64 32.64 0 0 1-23.04 9.6z" fill="#323333" p-id="2170"></path>
    </svg>
  </div>
</template>

<script>
export default {
  name: 'Hamburger',
  props: {
    isActive: {
      type: Boolean,
      default: false
    }
  },
  methods: {
    toggleClick() {
      this.$emit('toggleClick')
    }
  }
}
</script>

<style scoped>
.hamburger {
  display: inline-block;
  vertical-align: middle;
  width: 17.5px;
  height: 17.5px;
}

.hamburger.is-active {
  transform: rotate(180deg);
}
</style>
