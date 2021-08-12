<template>
  <div class="table-tool">
    <a-space>
      <div class="table-tool-item">
        <a-tooltip
          title="刷新"
        >
          <ReloadOutlined @click="refresh"/>
        </a-tooltip>
      </div>
      <div class="table-tool-item">
        <a-tooltip
          title="密度"
        >
          <a-dropdown
            :trigger="['click']"
          >
            <ColumnHeightOutlined/>
            <template #overlay>
              <a-menu
                @click="handleTableHeight"
                :selectedKeys="selectedKeys"
                style="width: 80px;"
              >
                <a-menu-item key="default">
                  <a href="javascript:;">默认</a>
                </a-menu-item>
                <a-menu-item key="middle">
                  <a href="javascript:;">中等</a>
                </a-menu-item>
                <a-menu-item key="small">
                  <a href="javascript:;">紧凑</a>
                </a-menu-item>
              </a-menu>
            </template>
          </a-dropdown>
        </a-tooltip>
      </div>
      <div class="table-tool-item">
        <a-tooltip
          title="列设置"
        >
          <a-popover
            trigger="click"
            placement="bottomRight"
          >
            <template #title>
              <div class="table-tool-item-column">
                <a-checkbox>列展示</a-checkbox>
                <a href="javascript:;">重置</a>
              </div>
            </template>
            <template #content>
              <div class="table-tool-item-content">
                <template
                  v-for="(col, index) in toolColumns"
                  :key="index"
                >
                  <div
                    class="table-tool-item-content-row"
                  >
                    <svg-icon name="drag" class="drag-icon"></svg-icon>
                    <a-checkbox>{{col.title}}</a-checkbox>
                  </div>
                </template>
              </div>
            </template>
            <SettingOutlined/>
          </a-popover>
        </a-tooltip>
      </div>
    </a-space>
  </div>
</template>

<script lang="ts">
import { defineComponent, PropType, reactive, toRefs, watch } from 'vue'
import {
  ReloadOutlined,
  ColumnHeightOutlined,
  SettingOutlined
} from '@ant-design/icons-vue'
import { ColumnProps } from 'ant-design-vue/es/table/interface'

export interface TableToolState {
  selectedKeys: string[];
  toolColumns: ColumnProps[];
}

export default defineComponent({
  name: 'TableTool',
  props: {
    size: {
      type: String as PropType<string>
    },
    columns: {
      type: Array as PropType<ColumnProps[]>
    }
  },
  components: {
    ReloadOutlined,
    ColumnHeightOutlined,
    SettingOutlined
  },
  emits: ['update:size', 'refresh'],
  setup (props, { emit }) {
    const state: TableToolState = reactive({
      selectedKeys: [],
      toolColumns: []
    })
    // init in props
    watch(() => props, p => {
      state.selectedKeys = Array.of(p.size) as string[]
      // columns
      const cols = [...p.columns as ColumnProps[]]
      state.toolColumns = cols
    }, { immediate: true })

    const handleTableHeight = ({ key }) => {
      state.selectedKeys = Array.of(key)
      emit('update:size', key)
    }

    const refresh = () => {
      emit('refresh')
    }

    return {
      ...toRefs(state),
      handleTableHeight,
      refresh
    }
  }
})
</script>

<style scoped lang="less">
::v-deep(.ant-popover-title) {
  min-width: 177px;
  min-height: 32px;
  margin: 0;
  padding: 5px 16px 4px;
  color: rgba(0, 0, 0, .85);
  font-weight: 500;
  border-bottom: 1px solid #f0f0f0;
}

::v-global(.ant-popover-inner-content) {
  padding: 0 0 8px !important;
}

.table-tool {
  width: 100%;
  height: auto;

  &-item {
    margin: 0 4px;
    font-size: 16px;
    cursor: pointer;

    &-column {
      display: flex;
      flex-direction: row;
      justify-content: space-between;
      align-content: center;
      padding: 5px 0 4px 0;
    }

    &-content {
      padding: 4px 0;
      &-row {
        padding-top: 8px;
        .drag-icon {
          width: 24px;
          padding-right: 6px;
          cursor: move;
        }
      }
    }
  }
}
</style>
