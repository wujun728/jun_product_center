import type { BasicTypeProps } from './BasicTable.vue'
import type { TableColumns } from 'naive-ui/es/data-table/src/interface'

export default function useTableColumns(
  props: Pick<BasicTypeProps, 'columns'>
): TableColumns {
  console.log(props.columns)
  // 处理columns
  return [] as TableColumns
}
