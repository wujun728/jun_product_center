import { ref, computed } from 'vue'
import themeConfig from './theme.config'
import { zhCN, dateZhCN, useOsTheme, darkTheme } from 'naive-ui'
import { ThemeName, useAppStore } from '@/store/modules/app'
// import {usePreferredLanguages} from "@vueuse/core";
// import {upperFirst} from "lodash-es";

// const defaultLang = 'enUS'

export default function useUIConfig() {
  // const languages = usePreferredLanguages()
  // const [localLang] = unref(languages)
  // const standardLang = localLang.replace(/-/g, '')
  const appStore = useAppStore()
  const osThemeRef = useOsTheme()

  const cfg = ref({
    dateLocale: ref(dateZhCN),
    locale: ref(zhCN),
    theme: computed(() => {
      if (appStore.getTheme === 'auto') {
        return osThemeRef.value === 'dark' ? darkTheme : null
      }
      return appStore.getTheme === 'dark' ? darkTheme : null
    }),
    themeOverrides: themeConfig
  })

  function toggleTheme(themeName: ThemeName) {
    return appStore.toggleTheme(themeName)
  }

  return {
    cfg,
    toggleTheme
  }
}
