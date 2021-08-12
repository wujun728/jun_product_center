import { h, SetupContext, PropType } from 'vue'
import type { Component } from 'vue'
import { NIcon } from 'naive-ui'

// slots渲染优先级大于props.icon
const VIcons = (props: { icon: Component }, context: SetupContext) => {
  return h(
    NIcon,
    { ...props, ...context.attrs },
    context.slots.default
      ? context.slots.default
      : { default: () => h(props.icon) }
  )
}

VIcons.props = {
  icon: {
    type: Object as PropType<Component>
  }
}

export default VIcons
