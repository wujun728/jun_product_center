const getModules = context => {
  return context.keys().reduce((modules, key) => {
    return {
      ...modules,
      ...context(key).default
    }
  }, {})
}

export {
  getModules
}
