import { MaybeRef } from '@vueuse/core'
import { Ref } from 'vue'

export interface FetchOptions {
  url: string
  method: 'post' | 'get' | 'put' | 'delete'
  data?: Record<string, any> | null
  params?: Record<string, any> | null
}

export interface FetchResponse {
  loading: MaybeRef<boolean>
  data: MaybeRef<any>
  execute: (throwOnFailed?: boolean) => Promise<any>
  isFinished: Ref<boolean>
}
