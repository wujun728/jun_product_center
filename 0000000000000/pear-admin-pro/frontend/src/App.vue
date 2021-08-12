<template>
  <a-config-provider :locale="antdLocal">
    <router-view></router-view>
  </a-config-provider>
</template>
<script>
import {computed, defineComponent, ref} from "vue";
import {useStore} from "vuex";
import {useI18n} from 'vue-i18n';

export default defineComponent({
  name: 'App',
  setup() {
    
    const store = useStore()
    const defaultLang = computed(() => store.getters['app/language'])
    const color = computed(() => store.getters.color);

    const antdLocal = ref(
      computed(() => {
        const { getLocaleMessage } = useI18n({ useScope: 'global' })
        const locale = getLocaleMessage(defaultLang.value).antdLocal
        return locale
      })
    )

    setTimeout(function(){
      window.less.modifyVars({
        "primary-color": color.value,
      })
    },10)
    
    return {
      antdLocal
    }
  }
})
</script>