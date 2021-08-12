import { MaybeRef } from '@vueuse/core'
import { stringify } from 'qs'
import { isRef, unref, ref } from 'vue'
export default function useGetRequestParams(
  url: MaybeRef<string>,
  params: MaybeRef<Record<string, any>>
): MaybeRef<string> {
  const unWrapUrl = isRef(url) ? unref(url) : url
  const result: MaybeRef<string> = ref(unWrapUrl)
  const unWrapParams = isRef(params) ? unref(params) : params
  const paramsStr = stringify(unWrapParams)
  result.value = `${unWrapUrl}?${paramsStr}`
  return result
}
