import app from '@/app'
import { loadLocaleMessages, setupI18n } from './i18n'
import config from '@/config/pear.config'
import storage from 'store'

const DEFAULT_LANG = storage.get('pear_language') || config.defaultLanguage
const i18n = setupI18n({
  globalInjection: true,
  legacy: false,
  locale: DEFAULT_LANG,
  fallbackLocale: DEFAULT_LANG,
  messages: {
  }
})

app.use(i18n)
loadLocaleMessages(i18n, DEFAULT_LANG).then(() => {
  console.log('init i18n')
})

export { i18n }
