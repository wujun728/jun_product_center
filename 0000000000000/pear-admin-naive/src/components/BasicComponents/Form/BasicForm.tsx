import {
  defineComponent,
  PropType,
  resolveComponent,
  DefineComponent,
  ref,
  toRaw,
  Slots,
  VNode
} from 'vue'
import { ComponentType } from './interface'
import { NForm, NFormItem } from 'naive-ui'

export interface SchemaField {
  model: string
  property?: any
  slots?: Slots | Record<string, () => VNode>
}

export interface FormItemSchema {
  component: ComponentType
  property?: any
  slots?: Slots
  field: SchemaField
}

export type CustomRender = {
  customRender: (args: any) => JSX.Element | VNode
}

type FormSchema = FormItemSchema | CustomRender

export type BasicFormSchema = FormSchema[]

export default defineComponent({
  name: 'BasicForm',
  props: {
    schema: {
      type: [Array, Function] as PropType<BasicFormSchema>,
      required: true,
      default: () => []
    },
    model: {
      type: Object as PropType<Record<string, unknown>>,
      required: true
    }
  },
  setup(props, { attrs, expose, slots }) {
    function renderFormItem() {
      return props.schema.map((item: FormSchema) => {
        if ('component' in item) {
          const ComponentItem = resolveComponent(
            item.component
          ) as DefineComponent
          const itemFieldSlots = toRaw(item?.field?.slots) ?? {}
          return (
            <NFormItem path={item.field.model} {...item.property}>
              <ComponentItem
                v-model={[props.model[item.field.model], 'value']}
                {...item.field.property}
                v-slots={itemFieldSlots}
              />
            </NFormItem>
          )
        } else {
          return item.customRender(props.model)
        }
      })
    }

    const formRefEl = ref<typeof NForm | null>(null)

    async function submit() {
      const err = await formRefEl.value?.validate()
      if (!err) {
        return props.model
      }
    }

    function validate() {
      return formRefEl.value?.validate()
    }

    function reset() {
      return formRefEl.value?.restoreValidation()
    }

    expose({
      submit,
      validate,
      reset
    })

    return () => {
      const bindProps = {
        ...attrs,
        model: props.model
      }
      return (
        <NForm {...bindProps} v-slots={slots} ref={formRefEl}>
          {renderFormItem()}
        </NForm>
      )
    }
  }
})
