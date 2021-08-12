<template>
  <a-form
    class="search-bar"
    v-bind="wrapper"
    layout="inline"
  >
    <div class="search-bar-type">
      <a-form-item
        class="ant-row"
      >
        <template #label>
          所属类目
        </template>
        <div class="search-bar-type-tags">
          <a-checkable-tag
            style="margin-right: 8px; padding: 0 8px; font-size: 14px;"
            v-for="(tag, index) in types" :key="index"
            :checked="selectedTags.includes(tag)"
            @change="checked => handleChange(tag, checked)"
          >
            {{ tag }}
          </a-checkable-tag>
          <a
            class="search-bar-type-tags-action"
          >
            展开
          </a>
        </div>
      </a-form-item>
    </div>
    <a-divider dashed/>
    <div class="search-bar-owner"></div>
    <a-divider dashed/>
    <div class="search-bar-other"></div>
  </a-form>
</template>

<script lang="ts">
import { defineComponent, reactive, toRefs } from 'vue'

const defaultTypes = [
  '全部',
  '类目一',
  '类目二',
  '类目三',
  '类目四',
  '类目五',
  '类目六',
  '类目七',
  '类目八',
  '类目九',
  '类目十',
  '类目十一',
  '类目十二'
]
export default defineComponent({
  name: 'SearchBar',
  props: {
    types: {
      type: Array,
      default: () => {
        return defaultTypes
      }
    }
  },
  setup () {
    const state = reactive({
      checked: [] as string[],
      selectedTags: [] as string[],
      wrapper: {
        labelCol: {
          lg: { span: 7 },
          sm: { span: 7 }
        },
        wrapperCol: {
          lg: { span: 20 },
          sm: { span: 20 }
        }
      }
    })

    const handleChange = (tag: string, checked: boolean) => {
      if (tag === '全部') {
        state.selectedTags = checked ? defaultTypes : []
      } else {
        const { selectedTags } = state
        const nextSelectedTags = checked
          ? [...selectedTags, tag]
          : selectedTags.filter(t => t !== tag)
        state.selectedTags = nextSelectedTags
      }
    }
    return {
      ...toRefs(state),
      handleChange
    }
  }
})
</script>

<style scoped lang="less">
.search-bar {
  &-type {
    &-tags {
      padding-right: 50px;
      position: relative;
      max-height: 32px;
      margin-left: -8px;
      overflow: hidden;
      line-height: 32px;
      transition: all .3s;
      &-action {
        position: absolute;
        top: 0;
        right: 0;
      }
    }
  }

  &-owner {
  }

  &-other {
  }
}
</style>
