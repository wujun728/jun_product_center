// @ts-nocheck
// 先忽略该文件的ts检查，否则 TableTool onRefresh={ctx.refresh}编译不通过。
// 参见：https://github.com/vuejs/vue-next/pull/2164
import { defineComponent, onMounted, PropType, reactive, toRefs, watch } from 'vue'
import { tableProps } from 'ant-design-vue/es/table/interface'
import { getAntdComponentProps } from '@/components/_utils'
import './index.less'
import TableTool from '@/components/PageComponent/StandardTable/TableTool.vue'
const StandardTable = defineComponent({
  name: 'StandardTable',
  components: {
    TableTool
  },
  props: Object.assign({}, tableProps, {
    fetch: {
      type: Function
    },
    pageSize: {
      type: Number as PropType<number>,
      default: 10
    },
    pageNo: {
      type: Number as PropType<number>,
      default: 1
    },
    total: {
      type: Number as PropType<number>,
      default: 0
    },
    showPagination: {
      type: Boolean as PropType<boolean>,
      default: true
    },
    size: {
      type: String as PropType<string>,
      default: 'middle',
      validator: (size: string) => {
        return ['default', 'middle', 'small'].includes(size)
      }
    }
  }),
  setup (props) {
    const state = reactive({
      tableSize: props.size,
      tableData: [],
      tableLoading: false,
      tableColumns: []
    })

    watch(() => props, p => {
      state.tableSize = p.size
      state.tableColumns = p.columns
    }, { immediate: true, deep: true })

    const handleFetch = async () => {
      if (typeof props.fetch === 'function') {
        try {
          state.tableLoading = true
          const data = await props.fetch()
          state.tableData = data
        } catch (e) {
          state.tableData = []
        } finally {
          state.tableLoading = false
        }
      }
    }

    const refresh = async () => {
      await handleFetch()
    }

    onMounted(async () => {
      await handleFetch()
    })

    return {
      ...toRefs(state),
      handleFetch,
      refresh
    }
  },
  render: ctx => {
    // default table props
    const defaultTableProps = getAntdComponentProps(tableProps, ctx)
    // todo: merge user custom props
    // todo: merge user custom operation
    return (
      <div class="app-standard-table-list-toolbar">
        <div class="app-standard-table-list-toolbar-container">
          <div class="app-standard-table-list-toolbar-container-left">
            <slot name="tableTitle">
              <div>标准表格</div>
            </slot>
          </div>
          <div class="app-standard-table-list-toolbar-container-right">
            <a-space>
              <slot name="operation"></slot>
              <TableTool
                v-model={[ctx.tableSize, 'size']}
                v-model={[ctx.tableColumns, 'columns']}
                onRefresh={ctx.refresh}
              ></TableTool>
            </a-space>
          </div>
        </div>
        <a-table
          {
            ...{
              ...defaultTableProps,
              size: ctx.tableSize,
              loading: ctx.tableLoading,
              dataSource: ctx.tableData,
              rowKey: (row) => row.id
            }
          }
        ></a-table>
      </div>
    )
  }
})

export default StandardTable
