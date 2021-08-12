/**
 * 将ant-design-vue的icon转成 <app-icon name='xxx'></app-icon> 模式使用
 */
import { defineComponent } from 'vue'
import * as AllIcons from '@ant-design/icons-vue'
const allIcon: any = AllIcons

export default defineComponent({
  name: 'AppIcon',
  props: {
    iconName: {
      type: String
    }
  },
  setup (props) {
    const IconItem = props.iconName ? allIcon[props.iconName] : {}
    return () => {
      return (
        props.iconName ? <IconItem/> : null
      )
    }
  }
})
