<template>
  <NoticeTab/>
  <Avatar/>
  <Lang/>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import Avatar from './Avatar.vue'
import Lang from './Lang.vue'
import NoticeTab from './NoticeTab.vue'

export default defineComponent({
  name: 'RightContent',
  components: {
    Avatar,
    Lang,
    NoticeTab
  }
})
</script>

<style scoped lang="less">

</style>
