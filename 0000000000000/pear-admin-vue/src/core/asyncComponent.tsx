import { defineAsyncComponent, ComponentPublicInstance, defineComponent } from 'vue'

const loading = defineComponent({
  name: 'componentLoading',
  setup () {
    return () => {
      return (
        <div style="width: 100%;height: 100%">
          <a-spin></a-spin>
        </div>
      )
    }
  }
})

/**
 * 异步组件加载
 * @param loader
 */
function loadAsyncComponent (loader): ComponentPublicInstance {
  const asyncComponent: ComponentPublicInstance = defineAsyncComponent({
    loader: loader,
    loadingComponent: loading,
    errorComponent: loading,
    delay: 400,
    // 如果提供了 timeout ，并且加载组件的时间超过了设定值，将显示错误组件
    // 默认值：Infinity（即永不超时，单位 ms）
    // timeout: 3000,
    // 定义组件是否可挂起 | 默认值：true
    suspensible: false,
    /**
     *
     * @param {*} error 错误信息对象
     * @param {*} retry 一个函数，用于指示当 promise 加载器 reject 时，加载器是否应该重试
     * @param {*} fail  一个函数，指示加载程序结束退出
     * @param {*} attempts 允许的最大重试次数
     */
    onError (error, retry, fail, attempts) {
      if (error.message.match(/fetch/) && attempts <= 3) {
        // 请求发生错误时重试，最多可尝试 3 次
        retry()
      } else {
        // 注意，retry/fail 就像 promise 的 resolve/reject 一样：
        // 必须调用其中一个才能继续错误处理。
        fail()
      }
    }
  })
  return asyncComponent
}

export { loadAsyncComponent as default }
