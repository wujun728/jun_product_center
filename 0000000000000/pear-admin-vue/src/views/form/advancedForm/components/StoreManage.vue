<template>
  <a-card
    title="仓库管理"
  >
    <a-form
      layout="vertical"
      ref="form"
    >
      <a-row class="form-row" :gutter="16">
        <a-col :lg="6" :md="12" :sm="24">
          <a-form-item
            name="name"
            :label="fieldLabels.name"
            v-bind="validateInfos.name"
          >
            <a-input
              placeholder="请输入仓库名称"
              v-model:value="formState.name"
            />
          </a-form-item>
        </a-col>
        <a-col :xl="{span: 6, offset: 2}" :lg="{span: 8}" :md="{span: 12}" :sm="24">
          <a-form-item
            name="url"
            :label="fieldLabels.url"
            v-bind="validateInfos.url"
          >
            <a-input
              addonBefore="http://"
              addonAfter=".com"
              placeholder="请输入"
              v-model:value="formState.url"
            />
          </a-form-item>
        </a-col>
        <a-col :xl="{span: 8, offset: 2}" :lg="{span: 10}" :md="{span: 24}" :sm="24">
          <a-form-item
            label="仓库管理员"
            v-bind="validateInfos.owner"
          >
            <a-select placeholder="请选择管理员" v-model:value="formState.owner">
              <a-select-option value="王同学">王同学</a-select-option>
              <a-select-option value="李同学">李同学</a-select-option>
              <a-select-option value="黄同学">黄同学</a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
      </a-row>
      <a-row class="form-row" :gutter="16">
        <a-col :lg="6" :md="12" :sm="24">
          <a-form-item
            name="approver"
            v-bind="validateInfos.approver"
            :label="fieldLabels.approver"
          >
            <a-select placeholder="请选择审批员" v-model:value="formState.approver">
              <a-select-option value="王晓丽">王晓丽</a-select-option>
              <a-select-option value="李军">李军</a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-col :xl="{span: 6, offset: 2}" :lg="{span: 8}" :md="{span: 12}" :sm="24">
          <a-form-item
            name="dateRange"
            v-bind="validateInfos.dateRange"
            :label="fieldLabels.dateRange"
          >
            <a-range-picker
              style="width: 100%"
              v-model:value="formState.dateRange"
            />
          </a-form-item>
        </a-col>
        <a-col :xl="{span: 8, offset: 2}" :lg="{span: 10}" :md="{span: 24}" :sm="24">
          <a-form-item
            name="type"
            v-bind="validateInfos.type"
            :label="fieldLabels.type"
          >
            <a-select
              placeholder="请选择仓库类型"
              v-model:value="formState.type"
            >
              <a-select-option value="公开">公开</a-select-option>
              <a-select-option value="私密">私密</a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
      </a-row>
    </a-form>
  </a-card>
</template>

<script lang="ts">
import { defineComponent, reactive, ref, Ref } from 'vue'
import { useForm } from '@ant-design-vue/use'

const fieldLabels = {
  name: '仓库名',
  url: '仓库域名',
  owner: '仓库管理员',
  approver: '审批人',
  dateRange: '生效日期',
  type: '仓库类型'
}

export default defineComponent({
  name: 'StoreManage',
  setup () {
    const form: Ref = ref<Element | null>(null)
    const formState = reactive({
      name: '',
      url: '',
      owner: undefined,
      approver: undefined,
      dateRange: [],
      type: undefined
    })
    const validateUrl = (rule, value) => {
      const regex = /^pear-(.*)$/
      if (!regex.test(value)) {
        return Promise.reject(new Error('需要以 pear- 开头'))
      }
      return Promise.resolve()
    }
    const {
      resetFields,
      validate,
      validateInfos
    } = useForm(
      formState,
      reactive({
        name: [{
          required: true,
          message: '请输入仓库名称'
        }],
        url: [{
          required: true,
          message: '请输入仓库域名'
        }, { validator: validateUrl }],
        owner: [{
          required: true,
          message: '请选择管理员'
        }],
        approver: [{
          required: true,
          message: '请选择审批员'
        }],
        dateRange: [{
          required: true,
          type: 'array',
          message: '请选择生效日期'
        }],
        type: [{
          required: true,
          message: '请选择仓库类型'
        }]
      })
    )

    return {
      fieldLabels,
      validateInfos,
      form,
      formState,
      validate,
      resetFields
    }
  }
})
</script>

<style scoped lang="less">

</style>
