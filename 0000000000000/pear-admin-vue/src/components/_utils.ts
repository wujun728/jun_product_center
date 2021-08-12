export function getAntdComponentProps<T extends object> (componentProps: T, currentProps: any): T {
  return Object.keys(componentProps).reduce((result, key) => {
    if (currentProps[key]) {
      return {
        ...result,
        [key]: currentProps[key]
      }
    } else {
      return result
    }
  }, {}) as T
}
