<template>
  <page-container
    content="高级表单常见于一次性输入和提交大批量数据的场景。"
  >
    <a-space
      direction="vertical"
      style="width: 100%; display: flex;"
      size="large"
    >
      <StoreManage ref="store"/>
      <TaskManage ref="task"/>
      <MemberManage />
    </a-space>
    <footer-tool-bar>
      <div
        class="advanced-validate-tips"
      >
        <a-popover
          trigger="click"
          title="表单校验信息"
          overlayClassName="advanced-validate-popover"
          :getPopupContainer="trigger => trigger.parentNode"
        >
          <template #content>
            <li
              v-for="(item) in errors"
              :key="item.name"
              class="advanced-validate-tips-error-item"
              @click="scrollToErrorFormItem(item.name)"
            >
              <div class="advanced-validate-tips-error-item-icon">
                <CloseCircleOutlined/>
              </div>
              <div>{{ item.label }}</div>
              <div class="advanced-validate-tips-error-item-reason">{{ item.reason }}</div>
            </li>
          </template>
          <span
            class="advanced-validate-tips-icon"
            v-show="errors.length > 0"
          >
            <CloseCircleOutlined/> {{ errors.length }}
          </span>
        </a-popover>
        <a-space>
          <a-button @click="resetFields">重置</a-button>
          <a-button type="primary" :loading="loading" @click="validate">提交</a-button>
        </a-space>
      </div>
    </footer-tool-bar>
  </page-container>
</template>

<script lang="ts">
import { defineComponent, ref, reactive, toRefs, unref, Ref } from 'vue'
import StoreManage from './components/StoreManage.vue'
import TaskManage from './components/TaskManage.vue'
import MemberManage from './components/MemberManage.vue'
import { CloseCircleOutlined } from '@ant-design/icons-vue'
import { message } from 'ant-design-vue'

interface ValidateResult {
  status: 'rejected' | 'fulfilled';
  value?: any;
  reason?: any;
}

interface ValidateError {
  label: string;
  reason: string;
  name: string;
}

interface State {
  loading: boolean;
  errors: ValidateError[];
}

export default defineComponent({
  name: 'index',
  components: {
    StoreManage,
    TaskManage,
    MemberManage,
    CloseCircleOutlined
  },
  setup () {
    const store: Ref = ref<Element | null>(null)
    const task: Ref = ref<Element | null>(null)

    const state = reactive({
      loading: false,
      errors: []
    } as State)

    const validate = async e => {
      e.preventDefault()
      try {
        const fieldLabels = { ...store.value.fieldLabels, ...task.value.fieldLabels }
        const promises = [store.value.validate(), task.value.validate()]
        const result: ValidateResult[] = await Promise.allSettled(promises)
        const errors: Array<ValidateError> = result.reduce((errs, item) => {
          if (item.status === 'rejected') {
            const errorFields = item.reason.errorFields
            const itErr = errorFields.map(it => {
              return {
                name: it.name,
                label: fieldLabels[it.name],
                reason: it.errors[0]
              }
            })
            return [...errs, ...itErr]
          }
          return errs
        }, [] as Array<ValidateError>)
        if (errors.length === 0) {
          const fetch = () => {
            return new Promise((resolve) => {
              setTimeout(() => {
                resolve()
              }, 800)
            })
          }
          try {
            state.loading = true
            await fetch()
            message.success('提交成功。')
          } finally {
            state.loading = false
          }
        }
        state.errors = errors
      } catch (e) {
        console.log(e)
      }
    }

    const scrollToErrorFormItem = (name) => {
      const labelNode = document.querySelector(`label[for="${name}"]`)
      if (labelNode) {
        labelNode.scrollIntoView(true)
      }
    }

    const resetFields = () => {
      const refs = [unref(store), unref(task)]
      refs.forEach(ref => ref.resetFields())
      state.errors.length = 0
    }

    return {
      ...toRefs(state),
      scrollToErrorFormItem,
      validate,
      resetFields,
      store,
      task
    }
  }
})
</script>

<style scoped lang="less">
::v-deep(.ant-popover-inner-content) {
  min-width: 256px;
  max-height: 290px;
  padding: 0;
  overflow: auto;
}

.advanced-validate-tips {
  .advanced-validate-popover {
  }

  &-error-item {
    padding: 8px 16px;
    list-style: none;
    border-bottom: 1px solid #f0f0f0;
    cursor: pointer;
    transition: all .3s;

    &-icon {
      float: left;
      margin-right: 12px;
      padding-bottom: 22px;
      color: #ff4d4f;
    }

    &-reason {
      margin-top: 2px;
      color: rgba(0, 0, 0, .45);
      font-size: 12px;
    }
  }

  &-icon {
    margin-right: 8px;
    color: red;
  }
}
</style>
