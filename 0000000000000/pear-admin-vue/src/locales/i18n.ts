import { nextTick } from 'vue'
import { createI18n, I18nOptions } from 'vue-i18n'

// export const SUPPORT_LOCALES = ['zh-CN', 'en-US']

export function setI18nLanguage (i18n, locale) {
  if (i18n.mode === 'legacy') {
    i18n.global.locale = locale
  } else {
    // eslint-disable-next-line @typescript-eslint/ban-ts-ignore
    // @ts-ignore
    i18n.global.locale.value = locale
  }
  /**
   * NOTE:
   * If you need to specify the language setting for headers, such as the `fetch` API, set it here.
   * The following is an example for axios.
   *
   * axios.defaults.headers.common['Accept-Language'] = locale
   */
  // eslint-disable-next-line @typescript-eslint/ban-ts-ignore
  // @ts-ignore
  document.querySelector('html').setAttribute('lang', locale)
}

export async function loadLocaleMessages (i18n, locale) {
  // load locale messages with dynami import
  const messages = await import(/* webpackChunkName: "locale-[request]" */ `./lang/${locale}.ts`)

  // set locale and locale message
  i18n.global.setLocaleMessage(locale, messages.default)
  setI18nLanguage(i18n, locale)
  return nextTick()
}

export function setupI18n (options: I18nOptions) {
  const i18n = createI18n(options)
  setI18nLanguage(i18n, options.locale)
  return i18n
}
