<template>
  <a-config-provider :locale="antdLocal">
    <router-view />
  </a-config-provider>
</template>
<script lang="ts">
import { computed, defineComponent, ref } from 'vue'
import { useI18n } from 'vue-i18n'
import { useStore } from 'vuex'

export default defineComponent({
  name: 'App',
  setup () {
    const store = useStore()
    const defaultLang = computed(() => store.getters['app/language'])

    const antdLocal = ref(
      computed(() => {
        const { getLocaleMessage } = useI18n({ useScope: 'global' })
        const locale = getLocaleMessage(defaultLang.value).antdLocal
        return locale
      })
    )
    return {
      antdLocal
    }
  }
})
</script>
