import { omit } from 'lodash-es'
import { ref, SetupContext, unref } from 'vue'

type ProxyCache<T> = Record<keyof T, unknown>

export default function useProxyFunc<
  T extends Record<string, unknown>,
  U extends keyof T
>(
  attrs: T,
  attribute: U,
  func: (args: unknown) => unknown
): { attrs: SetupContext['attrs'] } {
  /* eslint-disable @typescript-eslint/no-empty-function */
  const proxyFunc = ref(() => {})
  const proxyCache = ref<ProxyCache<T>>(Object.create({}))
  const defaultFunc = attrs[attribute] ?? null
  if (defaultFunc && typeof defaultFunc === 'function') {
    const proxy = unref(proxyCache)[attribute]
    if (!proxy) {
      proxyCache.value[attribute] = function (args: any) {
        const result = func(args) ?? args
        defaultFunc(result)
      }
    }
    proxyFunc.value = unref(proxyCache)[attribute]
  } else {
    console.warn(`
      the component attribute: ${attribute} type is not function
    `)
    return {
      attrs
    }
  }

  return {
    attrs: Object.assign(omit(attrs, Array.of(attribute)), {
      [attribute]: unref(proxyFunc)
    })
  }
}
