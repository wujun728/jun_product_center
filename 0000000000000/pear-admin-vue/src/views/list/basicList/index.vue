<template>
  <page-container>
    <a-row :gutter="[10, 10]">
      <a-col :span="24">
        <a-card></a-card>
      </a-col>
      <a-col :span="24">
        <a-card>
          <a-list item-layout="horizontal" :data-source="data">
            <template #renderItem="{ item }">
              <a-list-item>
                <a-list-item-meta
                  description="Ant Design, a design language for background applications, is refined by Ant UED Team"
                >
                  <template #title>
                    <a href="https://www.antdv.com/">{{ item.title }}</a>
                  </template>
                  <template #avatar>
                    <a-avatar
                      src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png"
                    />
                  </template>
                </a-list-item-meta>
              </a-list-item>
            </template>
          </a-list>
        </a-card>
      </a-col>
    </a-row>
  </page-container>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
interface DataItem {
  title: string;
}
const data: DataItem[] = [
  {
    title: 'Ant Design Title 1'
  },
  {
    title: 'Ant Design Title 2'
  },
  {
    title: 'Ant Design Title 3'
  },
  {
    title: 'Ant Design Title 4'
  }
]
export default defineComponent({
  setup () {
    return {
      data
    }
  }
})
</script>

<style scoped lang="less">
</style>
