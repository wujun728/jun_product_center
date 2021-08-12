import useInteraction from '@/composables/global/useInteraction'
import { defineComponent } from 'vue'
export default defineComponent({
  name: 'Interaction',
  setup() {
    useInteraction()
    return () => null
  }
})
