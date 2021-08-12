<template>
  {{ route?.meta?.title }}
  <n-p> 你选中了 {{ state.checkedRowKeys.length }} 行。 </n-p>

  <BasicTable
    :columns="columns"
    :data="data"
    :pagination="state.pagination"
    :row-key="(row) => row.address"
    @update:checked-row-keys="handleCheck"
  />
</template>

<script setup lang="ts">
  import { useRoute } from 'vue-router'
  import BasicTable from '@/components/BasicComponents/Table/BasicTable.vue'
  import { ref } from 'vue'

  const route = useRoute()

  const columns = [
    {
      type: 'selection',
      disabled(row: any, index: number) {
        return row.name === 'Edward King 3'
      }
    },
    {
      title: 'Name',
      key: 'name'
    },
    {
      title: 'Age',
      key: 'age'
    },
    {
      title: 'Address',
      key: 'address'
    }
  ]

  const data = Array.apply(null, { length: 46 }).map((_, index) => ({
    name: `Edward King ${index}`,
    age: 32,
    address: `London, Park Lane no. ${index}`
  }))

  const state = ref({
    checkedRowKeys: [],
    pagination: {
      pageSize: 5
    }
  })

  function handleCheck(rowKeys: any) {
    console.log('outer => ', rowKeys)
    state.value.checkedRowKeys = rowKeys
  }
</script>

<style scoped lang="less"></style>
