<template>
  <div></div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'LeftContent',
  components: {
  }
})
</script>

<style scoped lang="less">
.item{
    height: 100%;
    padding-left: 10px;
    padding-right: 10px;
    &:hover{
        background-color: whitesmoke;
    }
}
</style>
