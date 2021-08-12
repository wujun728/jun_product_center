<template>
  <a-card
    title="任务管理"
  >
    <a-form
      layout="vertical"
    >
      <a-row class="form-row" :gutter="16">
        <a-col :lg="6" :md="12" :sm="24">
          <a-form-item
            name="name2"
            v-bind="validateInfos.name2"
            :label="fieldLabels.name2"
          >
            <a-input placeholder="请输入任务名称" v-model:value="formState.name2"/>
          </a-form-item>
        </a-col>
        <a-col :xl="{span: 6, offset: 2}" :lg="{span: 8}" :md="{span: 12}" :sm="24">
          <a-form-item
            name="url2"
            v-bind="validateInfos.url2"
            :label="fieldLabels.url2"
          >
            <a-input placeholder="请输入任务描述" v-model:value="formState.url2"/>
          </a-form-item>
        </a-col>
        <a-col :xl="{span: 8, offset: 2}" :lg="{span: 10}" :md="{span: 24}" :sm="24">
          <a-form-item
            name="owner2"
            v-bind="validateInfos.owner2"
            :label="fieldLabels.owner2"
          >
            <a-select
              placeholder="请选择执行人"
              v-model:value="formState.owner2"
            >
              <a-select-option value="黄丽丽">黄丽丽</a-select-option>
              <a-select-option value="李大刀">李大刀</a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
      </a-row>
      <a-row class="form-row" :gutter="16">
        <a-col :lg="6" :md="12" :sm="24">
          <a-form-item
            name="approver2"
            v-bind="validateInfos.approver2"
            :label="fieldLabels.approver2"
          >
            <a-select
              placeholder="请选择责任人"
              v-model:value="formState.approver2"
            >
              <a-select-option value="王伟">王伟</a-select-option>
              <a-select-option value="李红军">李红军</a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-col :xl="{span: 6, offset: 2}" :lg="{span: 8}" :md="{span: 12}" :sm="24">
          <a-form-item
            name="dateRange2"
            v-bind="validateInfos.dateRange2"
            :label="fieldLabels.dateRange2"
          >
            <a-time-picker
              style="width: 100%"
              v-model:value="formState.dateRange2"
            />
          </a-form-item>
        </a-col>
        <a-col :xl="{span: 8, offset: 2}" :lg="{span: 10}" :md="{span: 24}" :sm="24">
          <a-form-item
            name="type2"
            v-bind="validateInfos.type2"
            :label="fieldLabels.type2"
          >
            <a-select
              placeholder="请选择任务类型"
              v-model:value="formState.type2">
              <a-select-option value="定时执行">定时执行</a-select-option>
              <a-select-option value="周期执行">周期执行</a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
      </a-row>
    </a-form>
  </a-card>
</template>

<script lang="ts">
import { defineComponent, reactive } from 'vue'
import { useForm } from '@ant-design-vue/use'

const fieldLabels = {
  name2: '任务名',
  url2: '任务描述',
  owner2: '执行人',
  approver2: '责任人',
  dateRange2: '生效日期',
  type2: '任务类型'
}

export default defineComponent({
  name: 'TaskManage',
  setup () {
    const formState = reactive({
      name2: '',
      url2: '',
      owner2: undefined,
      approver2: undefined,
      dateRange2: undefined,
      type2: undefined
    })
    const {
      resetFields,
      validate,
      validateInfos
    } = useForm(
      formState,
      reactive({
        name2: [{
          required: true,
          message: '请输入任务名称'
        }],
        url2: [{
          required: true,
          message: '请输入任务描述'
        }],
        owner2: [{
          required: true,
          message: '请选择执行人'
        }],
        approver2: [{
          required: true,
          message: '请选择责任人'
        }],
        dateRange2: [{
          required: true,
          type: 'date',
          message: '请选择提醒时间'
        }],
        type2: [{
          required: true,
          message: '请选择任务类型'
        }]
      })
    )

    return {
      validateInfos,
      // onSubmit,
      formState,
      validate,
      resetFields,
      fieldLabels
    }
  }
})
</script>

<style scoped lang="less">

</style>
