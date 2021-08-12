<template>
  <page-container>
    <a-card>
      <standard-table
        size="small"
        :columns="columns"
        :fetch="fetchData"
      >
      </standard-table>
    </a-card>
  </page-container>
</template>

<script lang="ts">
import { defineComponent, reactive, toRefs } from 'vue'
import request from '@/api/request'

export default defineComponent({
  name: 'index',
  setup () {
    const state = reactive({
      columns: [
        {
          title: 'Name',
          dataIndex: 'name'
        },
        {
          title: 'UseName',
          dataIndex: 'username'
        },
        {
          title: 'Email',
          dataIndex: 'email'
        },
        {
          title: 'Phone',
          dataIndex: 'phone'
        },
        {
          title: 'WebSite',
          dataIndex: 'website'
        }
      ]
    })
    const fetchList = async () => {
      const { data } = await request('https://jsonplaceholder.typicode.com/users')
      return data
    }
    return {
      ...toRefs(state),
      fetchData: fetchList
    }
  }
})
</script>

<style scoped lang="less">
::v-deep(.ant-card-body) {
  padding-top: 0;
  padding-bottom: 0;
}
</style>
