<template>
  <div>
    {{ route?.meta?.title }}
    <n-divider></n-divider>
    <BasicForm
      ref="formRefEl"
      :model="model"
      :rules="rules"
      :schema="schema"
      label-placement="left"
      :label-width="160"
    ></BasicForm>
    <n-space>
      <n-button type="primary" @click="handleSubmit">提交</n-button>
      <n-button @click="reset">重置</n-button>
    </n-space>
    <n-divider> </n-divider>
    <pre>
      {{ JSON.stringify(model, 0, 2) }}
    </pre>
  </div>
</template>

<script setup lang="tsx">
  import { ref } from 'vue'
  import { useRoute } from 'vue-router'
  import BasicForm, {
    BasicFormSchema
  } from '@/components/BasicComponents/Form/BasicForm'
  import { FlashOutline } from '@vicons/ionicons5'

  const route = useRoute()

  const generalOptions = ['groode', 'veli good', 'emazing', 'lidiculous'].map(
    (v) => ({
      label: v,
      value: v
    })
  )

  const schema = ref<BasicFormSchema>([
    {
      component: 'NInput',
      field: {
        model: 'inputValue',
        slots: {
          prefix: () => {
            return (
              <n-icon>
                <FlashOutline />
              </n-icon>
            )
          }
        }
      },
      property: {
        label: 'Input'
      }
    },
    {
      component: 'NInput',
      field: {
        model: 'textareaValue',
        property: {
          type: 'textarea',
          autoSize: {
            minRows: 3,
            maxRows: 5
          }
        }
      },
      property: {
        label: 'Textarea'
      }
    },
    {
      component: 'NSelect',
      property: {
        label: 'Select'
      },
      field: {
        model: 'multipleSelectValue',
        property: {
          options: generalOptions,
          multiple: true
        }
      }
    },
    {
      component: 'NDatePicker',
      property: {
        label: 'Datetime'
      },
      field: {
        model: 'datetimeValue',
        property: {
          type: 'datetime'
        }
      }
    },
    {
      component: 'NSwitch',
      property: {
        label: 'Switch'
      },
      field: {
        model: 'switchValue'
      }
    },
    {
      component: 'NCheckboxGroup',
      property: {
        label: 'Checkbox Group'
      },
      field: {
        model: 'checkboxGroupValue',
        slots: {
          default: () => {
            return [1, 2, 3].map((it) => {
              return (
                <n-checkbox key={it} value={`Option ${it}`}>
                  Option {it}
                </n-checkbox>
              )
            })
          }
        }
      }
    },
    {
      component: 'NRadioGroup',
      property: {
        label: 'Radio Group'
      },
      field: {
        model: 'radioGroupValue',
        slots: {
          default: () => {
            return [1, 2, 3].map((it) => {
              return (
                <n-radio key={it} value={`Option ${it}`}>
                  Option {it}
                </n-radio>
              )
            })
          }
        }
      }
    },
    {
      component: 'NRadioGroup',
      property: {
        label: 'Radio Button Group'
      },
      field: {
        model: 'radioButtonGroupValue',
        slots: {
          default: () => {
            return [1, 2, 3].map((it) => {
              return (
                <n-radio-button key={it} value={`Option ${it}`}>
                  Option {it}
                </n-radio-button>
              )
            })
          }
        }
      }
    },
    {
      component: 'NInputNumber',
      property: {
        label: 'Input Number'
      },
      field: {
        model: 'inputNumberValue'
      }
    },
    {
      component: 'NTimePicker',
      property: {
        label: 'Time Picker'
      },
      field: {
        model: 'timePickerValue'
      }
    },
    {
      component: 'NSlider',
      property: {
        label: 'Slider'
      },
      field: {
        model: 'sliderValue'
      }
    },
    {
      component: 'NTransfer',
      property: {
        label: 'Transfer'
      },
      field: {
        model: 'transferValue',
        property: {
          options: generalOptions
        }
      }
    },
    {
      customRender: (model: any) => {
        return (
          <n-form-item label="Nested Path" show-feedback={false}>
            <n-grid cols={2} x-gap={24}>
              <n-form-item-gi path="nestedValue.path1">
                <n-input
                  placeholder="Nested Path 1"
                  v-model={[model.nestedValue.path1, 'value']}
                />
              </n-form-item-gi>
              <n-form-item-gi path="nestedValue.path2">
                <n-select
                  v-model={[model.nestedValue.path2, 'value']}
                  placeholder="Nested Path 2"
                  options={generalOptions}
                />
              </n-form-item-gi>
            </n-grid>
          </n-form-item>
        )
      }
    }
  ])

  const model = ref({
    inputValue: null,
    textareaValue: null,
    selectValue: null,
    multipleSelectValue: null,
    datetimeValue: null,
    nestedValue: {
      path1: null,
      path2: null
    },
    switchValue: false,
    checkboxGroupValue: null,
    radioGroupValue: null,
    radioButtonGroupValue: null,
    inputNumberValue: null,
    timePickerValue: null,
    sliderValue: 0,
    transferValue: null
  })

  const rules = ref({
    inputValue: {
      required: true,
      trigger: ['blur', 'input'],
      message: '请输入 inputValue'
    },
    textareaValue: {
      required: true,
      trigger: ['blur', 'input'],
      message: '请输入 textareaValue'
    },
    selectValue: {
      required: true,
      trigger: ['blur', 'change'],
      message: '请选择 selectValue'
    },
    multipleSelectValue: {
      required: true,
      type: 'array',
      trigger: ['blur', 'change'],
      message: '请选择 multipleSelectValue'
    },
    datetimeValue: {
      type: 'number',
      required: true,
      trigger: ['blur', 'change'],
      message: '请输入 datetimeValue'
    },
    nestedValue: {
      path1: {
        required: true,
        trigger: ['blur', 'input'],
        message: '请输入 nestedValue.path1'
      },
      path2: {
        required: true,
        trigger: ['blur', 'change'],
        message: '请输入 nestedValue.path2'
      }
    },
    checkboxGroupValue: {
      type: 'array',
      required: true,
      trigger: 'change',
      message: '请选择 checkboxGroupValue'
    },
    radioGroupValue: {
      required: true,
      trigger: 'change',
      message: '请选择 radioGroupValue'
    },
    radioButtonGroupValue: {
      required: true,
      trigger: 'change',
      message: '请选择 radioButtonGroupValue'
    },
    inputNumberValue: {
      type: 'number',
      required: true,
      trigger: ['blur', 'change'],
      message: '请输入 inputNumberValue'
    },
    timePickerValue: {
      type: 'number',
      required: true,
      trigger: ['blur', 'change'],
      message: '请输入 timePickerValue'
    },
    sliderValue: {
      validator(rule: any, value: number) {
        return value > 50
      },
      trigger: ['blur', 'change'],
      message: 'sliderValue 需要大于 50'
    },
    transferValue: {
      type: 'array',
      required: true,
      trigger: 'change',
      message: '请输入 transferValue'
    }
  })

  const formRefEl = ref<HTMLFormElement | null>(null)

  async function handleSubmit() {
    const error = await formRefEl.value?.validate()
    if (!error) {
      console.log(model)
    }
  }

  function reset() {
    formRefEl.value?.reset()
  }
</script>

<style scoped lang="less"></style>
