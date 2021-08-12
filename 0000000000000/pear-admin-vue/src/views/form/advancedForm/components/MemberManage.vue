<template>
  <a-card
    title="成员管理"
  >
    <a-table
      :columns="columns"
      :dataSource="data"
      :pagination="false"
      :loading="loading"
    >
      <template v-for="(col, i) in ['name', 'workId', 'department']" v-slot:[col]="{text, record}">
        <a-input
          :key="col"
          v-if="record.editable"
          style="margin: -5px 0"
          :value="text"
          :placeholder="columns[i].title"
          @change="e => handleChange(e.target.value, record.key, col)"
        />
        <template v-else>{{ text }}</template>
      </template>
      <template #operation="{record}">
        <template v-if="record.editable">
            <span v-if="record.isNew">
              <a @click="saveRow(record)">添加</a>
              <a-divider type="vertical"/>
              <a-popconfirm title="是否要删除此行？" @confirm="remove(record.key)">
                <a>删除</a>
              </a-popconfirm>
            </span>
          <span v-else>
              <a @click="saveRow(record)">保存</a>
              <a-divider type="vertical"/>
              <a @click="cancel(record.key)">取消</a>
            </span>
        </template>
        <span v-else>
            <a @click="toggle(record.key)">编辑</a>
            <a-divider type="vertical"/>
            <a-popconfirm title="是否要删除此行？" @confirm="remove(record.key)">
              <a>删除</a>
            </a-popconfirm>
          </span>
      </template>
    </a-table>
    <a-button style="width: 100%; margin-top: 16px; margin-bottom: 24px" type="dashed" @click="newMember">
      <template #icon>
        <PlusOutlined/>
      </template>
      新增成员
    </a-button>
  </a-card>
</template>

<script lang="ts">
import { defineComponent, reactive, toRefs } from 'vue'
import { PlusOutlined } from '@ant-design/icons-vue'
import { message } from 'ant-design-vue'

const columns = [
  {
    title: '成员姓名',
    dataIndex: 'name',
    key: 'name',
    width: '20%',
    slots: { customRender: 'name' }
  },
  {
    title: '工号',
    dataIndex: 'workId',
    key: 'workId',
    width: '20%',
    slots: { customRender: 'workId' }
  },
  {
    title: '所属部门',
    dataIndex: 'department',
    key: 'department',
    width: '40%',
    slots: { customRender: 'department' }
  },
  {
    title: '操作',
    key: 'action',
    slots: { customRender: 'operation' }
  }
]

interface DataItem {
  key: string;
  name: string;
  workId: string;
  department: string;
  editable: boolean;
  isNew?: boolean;
  _originalData?: any;
}

interface MemberState {
  loading: boolean;
  data: Array<DataItem>;
}

export default defineComponent({
  name: 'MemberManage',
  components: {
    PlusOutlined
  },
  setup () {
    const state: MemberState = reactive({
      loading: false,
      data: [
        {
          key: '1',
          name: '小明',
          workId: '001',
          editable: false,
          department: '行政部'
        },
        {
          key: '2',
          name: '李莉',
          workId: '002',
          editable: false,
          department: 'IT部'
        },
        {
          key: '3',
          name: '王小帅',
          workId: '003',
          editable: false,
          department: '财务部'
        }
      ]
    })
    const newMember = () => {
      const length = state.data.length
      state.data.push({
        key: length === 0 ? '1' : (parseInt(state.data[length - 1].key) + 1).toString(),
        name: '',
        workId: '',
        department: '',
        editable: true,
        isNew: true,
        _originalData: undefined
      })
    }
    const remove = (key) => {
      const newData = state.data.filter(item => item.key !== key)
      state.data = newData
    }

    const saveRow = (record) => {
      state.loading = true
      const {
        key,
        name,
        workId,
        department
      } = record
      if (!name || !workId || !department) {
        state.loading = false
        message.error('请填写完整成员信息。')
        return
      }
      // 模拟网络请求、卡顿 800ms
      new Promise((resolve) => {
        setTimeout(() => {
          resolve({ loop: false })
        }, 800)
      }).then(() => {
        const target = state.data.find(item => item.key === key) as DataItem
        target.editable = false
        target.isNew = false
        state.loading = false
      })
    }
    const toggle = (key) => {
      const target = state.data.find(item => item.key === key) as DataItem
      target._originalData = { ...target }
      target.editable = !target.editable
    }

    const getRowByKey = (key, newData) => {
      const data = state.data
      return (newData || data).find(item => item.key === key)
    }
    const cancel = (key) => {
      const target = state.data.find(item => item.key === key) as DataItem
      Object.keys(target).forEach(key => {
        target[key] = target._originalData[key]
      })
      target._originalData = undefined
    }

    const handleChange = (value, key, column) => {
      const newData = [...state.data]
      const target = newData.find(item => key === item.key)
      if (target) {
        target[column] = value
        state.data = newData
      }
    }
    return {
      columns,
      ...toRefs(state),
      newMember,
      remove,
      saveRow,
      toggle,
      getRowByKey,
      cancel,
      handleChange
    }
  }
})
</script>

<style scoped lang="less">

</style>
