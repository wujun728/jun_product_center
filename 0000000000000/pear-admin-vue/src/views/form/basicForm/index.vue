<template>
  <page-container
    content="表单页用于向用户收集或验证信息，基础表单常见于数据项较少的表单场景。"
  >
    <a-card
    >
      <a-form
        layout="horizontal"
        v-bind="formItemWrapper"
      >
        <a-form-item
          label="标题"
          v-bind="validateInfos.title"
        >
          <a-input
            v-model:value="formState.title"
            placeholder="给目标起个名字"
          />
        </a-form-item>
        <a-form-item
          label="起止日期"
          v-bind="validateInfos.dateRange"
        >
          <a-range-picker
            v-model:value="formState.dateRange"
            style="width: 100%"
          />
        </a-form-item>
        <a-form-item
          label="目标描述"
          v-bind="validateInfos.targetDescription"
        >
          <a-textarea
            v-model:value="formState.targetDescription"
            :auto-size="{minRows: 3}"
            placeholder="请输入你的阶段性目标名称"
          />
        </a-form-item>
        <a-form-item
          label="衡量标准"
          v-bind="validateInfos.reference"
        >
          <a-textarea
            v-model:value="formState.reference"
            :auto-size="{minRows: 3}"
            placeholder="请输入衡量标准"
          />
        </a-form-item>
        <a-form-item
          v-bind="validateInfos.client"
        >
          <template #label>
                <span>
                  客户
                  <em class="custom-form-label">
                  <span>(选填)
                    <a-tooltip>
                       <template #title>目标的服务对象</template>
                       <InfoCircleOutlined/>
                    </a-tooltip>
                  </span>
                </em>
                </span>
          </template>
          <a-input
            v-model:value="formState.client"
            placeholder="请描述你服务的客户，内部客户直接 @姓名／工号"
          />
        </a-form-item>
        <a-form-item
          v-bind="validateInfos.user"
        >
          <template #label>
                <span>
                  邀评人
                  <em class="custom-form-label">
                    <span>(选填)</span>
                  </em>
                </span>
          </template>
          <a-input
            v-model:value="formState.user"
            placeholder="请直接 @姓名／工号，最多可邀请 5 人"
          />
        </a-form-item>
        <a-form-item
          v-bind="validateInfos.weight"
        >
          <template #label>
                <span>
                  权重
                  <em class="custom-form-label">
                    <span>(选填)</span>
                  </em>
                </span>
          </template>
          <a-input-number
            v-model:value="formState.weight"
            placeholder="请输入"
          />
          <span class="ant-form-text">%</span>
        </a-form-item>
        <a-form-item
          label="目标公开"
          v-bind="validateInfos.targetOpen"
          style="margin-bottom:0"
        >
          <a-radio-group
            v-model:value="formState.targetOpen"
            :options="targetOptions"
          />
        </a-form-item>
        <a-form-item
          v-if="formState.targetOpen === '02'"
          :wrapperCol="{span: 10, offset: 7}"
          style="margin-bottom:0"
        >
          <a-select
            v-model:value="formState.target"
            placeholder="公开给"
            mode="multiple"
          >
            <a-select-option key="01">同事甲</a-select-option>
            <a-select-option key="02">同事乙</a-select-option>
            <a-select-option key="03">同事丙</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item
          :wrapperCol="{span: 10, offset: 7}"
        >
          <div style="color: rgba(0,0,0,.45);">客户、邀评人默认被分享</div>
        </a-form-item>
        <a-form-item
          :wrapperCol="{span: 10, offset: 7}"
        >
          <a-button type="primary" @click.prevent="onSubmit">提交</a-button>
          <a-button style="margin-left: 10px" @click="onSave">保存</a-button>
          <a-button style="margin-left: 10px" @click="handleReset">重置</a-button>
        </a-form-item>
      </a-form>
    </a-card>
  </page-container>
</template>

<script>
import { defineComponent, reactive } from 'vue'
import { InfoCircleOutlined } from '@ant-design/icons-vue'
import { useForm } from '@ant-design-vue/use'

const formItemWrapper = {
  labelCol: {
    xs: { span: 24 },
    sm: { span: 7 }
  },
  wrapperCol: {
    xs: { span: 24 },
    sm: { span: 12 },
    md: { span: 10 }
  }
}

const targetOptions = [
  {
    label: '公开',
    value: '01'
  },
  {
    label: '部分公开',
    value: '02'
  },
  {
    label: '不公开',
    value: '03'
  }
]

export default defineComponent({
  name: 'basicForm',
  components: {
    InfoCircleOutlined
  },
  setup () {
    const formState = reactive({
      title: '',
      dateRange: [],
      targetDescription: '',
      reference: '',
      client: '',
      user: '',
      weight: undefined,
      targetOpen: '01',
      target: []
    })
    const {
      validateInfos,
      validate,
      resetFields
    } = useForm(formState, reactive({
      title: [
        {
          required: true,
          message: '标题必填哈'
        }
      ]
    }))
    const onSubmit = async () => {
      try {
        await validate()
        console.log(formState)
      } catch (e) {
        console.log(e)
      }
    }
    const onSave = async () => {
      try {
        await validate()
        console.log(formState)
      } catch (e) {
        console.log(e)
      }
    }
    const handleReset = () => {
      resetFields()
    }
    return {
      formItemWrapper,
      targetOptions,
      validateInfos,
      formState,
      onSubmit,
      onSave,
      handleReset
    }
  }
})
</script>

<style scoped lang="less">
.custom-form-label {
  color: rgba(0, 0, 0, .45);
  font-style: normal;
}
</style>
