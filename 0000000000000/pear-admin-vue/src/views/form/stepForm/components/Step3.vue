<template>
  <a-result title="操作成功" status="success" sub-title="预计两小时内到账" style="max-width: 560px; margin: 40px auto 0;">
    <template #extra>
      <a-space>
        <a-button type="primary" @click="finish">再转一笔</a-button>
        <a-button @click="toOrderList">查看账单</a-button>
      </a-space>
    </template>
    <a-descriptions :column="1">
      <a-descriptions-item label="付款账户">pearadmin.com</a-descriptions-item>
      <a-descriptions-item label="收款账户">test@example.com</a-descriptions-item>
      <a-descriptions-item label="收款人姓名">落小梅</a-descriptions-item>
      <a-descriptions-item label="转账金额">500元</a-descriptions-item>
    </a-descriptions>
  </a-result>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import { useRouter } from 'vue-router'

export default defineComponent({
  inheritAttrs: false,
  name: 'step3',
  emits: ['finish'],
  setup (_, { emit }) {
    const router = useRouter()
    const finish = () => {
      emit('finish')
    }
    const toOrderList = () => {
      router.push({ name: 'basic-list' })
    }
    return {
      finish,
      toOrderList
    }
  }
})
</script>

<style scoped lang="less">

</style>
