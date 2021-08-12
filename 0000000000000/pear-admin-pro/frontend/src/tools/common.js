/** 生产环境 */
export const isNotProduction = () => {
  return process.env.NODE_ENV !== 'production'
}

/** 聚合导入 */
export const getModule = context => {
  return context.keys().reduce((modules, key) => {
    return {
      ...modules,
      ...context(key).default
    }
  }, {})
}