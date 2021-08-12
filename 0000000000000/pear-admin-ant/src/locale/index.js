import { loadLocaleMessages, setupI18n } from './i18n'
import config from '@/pear.js'

const DEFAULT_LANG = localStorage.getItem('pear_lang') ? localStorage.getItem('pear_lang') : config.defaultLanguage
const i18n = setupI18n({
  globalInjection: true,
  legacy: false,
  locale: DEFAULT_LANG,
  fallbackLocale: DEFAULT_LANG,
  messages: {
  }
})

loadLocaleMessages(i18n, DEFAULT_LANG).then(() => {
  console.log('init i18n')
})

export default i18n
