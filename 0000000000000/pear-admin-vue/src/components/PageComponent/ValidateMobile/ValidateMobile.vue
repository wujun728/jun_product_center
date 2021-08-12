<template>
  <div class="validate-mobile-code-wrapper">
    <template
      v-for="n in validateCodeLength"
      :key="n"
    >
      <a-input
        :size="size"
        class="validate-mobile-code-wrapper-input"
        :class="classes"
        :value="validateCodes[n - 1]"
        :ref="(el) => {if (el) domRefs[n - 1] = el }"
        @change="(e) => handleChange(e, n - 1)"
        @keydown.delete="handleDelete($event, n - 1)"
      ></a-input>
    </template>
  </div>
</template>
<script lang="ts">
import { defineComponent, PropType, reactive, ref, toRefs, Ref, onBeforeUpdate, watch, computed } from 'vue'
import { isEmpty } from 'lodash-es'

type HTMLRef = HTMLElement | null

export default defineComponent({
  name: 'ValidateMobile',
  props: {
    size: {
      type: String as PropType<string>,
      default: 'default',
      validator: (value: string) => {
        return ['default', 'small', 'large'].includes(value)
      }
    },
    modelValue: {
      type: String as PropType<string>,
      default: ''
    },
    pattern: {
      type: RegExp as PropType<RegExp>,
      default: /^\d{1}$/
    },
    validateCodeLength: {
      type: Number as PropType<number>,
      default: 4
    }
  },
  emits: ['update:modelValue'],
  setup (props, { emit }) {
    // classes
    const classes = computed(() => {
      return {
        'validate-mobile-lg': props.size as string === 'large',
        'validate-mobile-sm': props.size === 'small',
        'validate-mobile': props.size === 'default'
      }
    })

    const state = reactive({
      validateCodes: new Array(props.validateCodeLength).fill('')
    })
    const domRefs: Ref = ref<HTMLRef[]>([])

    onBeforeUpdate(() => {
      domRefs.value = []
    })

    // add value and next input focused
    const handleChange = (e: any, n: number): void => {
      if (props.pattern.test(e.data)) {
        state.validateCodes.splice(n, 1, e.data)
        // next input focus
        if (n < props.validateCodeLength - 1) {
          const dom: HTMLRef = domRefs.value[n + 1]
          dom && dom.focus()
        } else {
          domRefs.value[n].blur()
        }
      }
      emit('update:modelValue', state.validateCodes.join(''))
    }

    // delete Event
    const handleDelete = (e: any, n: number): void => {
      console.log(n)
      if (n >= 0) {
        state.validateCodes.splice(n, 1, e.data)
        if (n > 0) {
          const dom: HTMLRef = domRefs.value[n - 1]
          dom && dom.focus()
        }
      }
      emit('update:modelValue', state.validateCodes.join(''))
      e.preventDefault()
    }

    watch(() => props.modelValue, (value: string) => {
      if (isEmpty(value)) {
        state.validateCodes = new Array(props.validateCodeLength)
      } else {
        /**
         * 传过来的值跟当前数数不一样时，重新赋值
           如果一样就不赋值：
           受form控制时，值若为1234,若删除掉中间3，数组为[1,2,'',4], 此时如果不判断值是否相同，重新赋值则会变为[1,2,4,''] 会打乱顺序
         */
        if (state.validateCodes.join('') !== value) {
          state.validateCodes = value.toString().substr(0, props.validateCodeLength).split('')
        }
      }
    }, { immediate: true })

    return {
      ...toRefs(state),
      domRefs,
      classes,
      handleChange,
      handleDelete
    }
  }
})
</script>

<style scoped lang="less">
.validate-mobile-code-wrapper {
  //width: 100%;
  //height: auto;
  //display: flex;
  //flex-direction: row;
  //justify-content: flex-start;

  &-input {
    //width: 40px;
    //height: 40px;
    margin-right: 8px;
    text-align: center;
  }

  .validate-mobile {
    width: 32px;
    height: 32px;

    &-lg {
      width: 40px;
      height: 40px;
    }

    &-sm {
      width: 24px;
      height: 24px;
    }
  }
}
</style>
