<template>
  <page-container>
    <a-list
      class="card-list"
      :grid="{gutter: 24, lg: 3, md: 2, sm: 1, xs: 1, column: 4}"
      :dataSource="dataSource"
    >
      <template #renderItem>
        <div
          class="card-list-item"
        >
          <a-card hoverable>
            <template #cover>
              <img
                alt="example"
                src="https://gw.alipayobjects.com/zos/rmsportal/JiqGstEfoWAOHiTxclqi.png"
              />
            </template>
            <template class="ant-card-actions" #actions>
              <setting-outlined key="setting"/>
              <edit-outlined key="edit"/>
              <ellipsis-outlined key="ellipsis"/>
            </template>
            <a-card-meta title="Card title" description="This is the description">
              <template #avatar>
                <a-avatar src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png"/>
              </template>
            </a-card-meta>
          </a-card>
        </div>
      </template>
    </a-list>
  </page-container>
</template>

<script lang="ts">
import { SettingOutlined, EditOutlined, EllipsisOutlined } from '@ant-design/icons-vue'
import { defineComponent } from 'vue'

const dataSource = new Array(12).fill('').map((it, id) => {
  return {
    rowKey: id
  }
})

export default defineComponent({
  name: 'index',
  components: {
    SettingOutlined,
    EditOutlined,
    EllipsisOutlined
  },
  setup () {
    return {
      dataSource
    }
  }
})
</script>

<style scoped lang="less">
::v-deep(.ant-row) {
  margin: 0 !important;
}
.card-list {
  .card-list-item {
    box-sizing: border-box;
    padding: 0 8px 8px 0;
  }
}
</style>
