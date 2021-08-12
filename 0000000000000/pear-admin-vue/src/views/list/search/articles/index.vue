<template>
  <page-container
  >
    <template #description>
      <div class="articles-search-bar">
        <a-input-search
          style="max-width: 522px;"
          v-model:value="searchBar.search"
          placeholder="请输入"
          enter-button="搜索"
          size="large"
          @search="onSearch"
        />
      </div>
    </template>
    <template #footer>
      <a-tabs default-active-key="1">
        <a-tab-pane key="1" tab="文章" />
        <a-tab-pane key="2" tab="项目" />
        <a-tab-pane key="3" tab="应用" />
      </a-tabs>
    </template>
    <div>
      <a-card>
        <SearchBar />
      </a-card>
    </div>
  </page-container>
</template>

<script>
import { defineComponent, reactive } from 'vue'
import SearchBar from '../components/SearchBar.vue'

export default defineComponent({
  name: 'articles',
  components: {
    SearchBar
  },
  setup () {
    const searchBar = reactive({
      search: ''
    })

    const onSearch = (v) => {
      console.log(v)
    }
    return {
      searchBar,
      onSearch
    }
  }
})
</script>

<style scoped lang="less">
.articles-search-bar {
  text-align: center;
}
</style>
