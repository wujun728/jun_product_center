const requireStoreContext = require.context('./modules/', false, /([\w+]\.(ts))/)

const modules = requireStoreContext.keys().reduce((modules, fileName) => {
  const name = fileName.replace(/(\.\/|\.ts)/g, '')
  return {
    ...modules,
    [name]: requireStoreContext(fileName).default
  }
}, {})

export default modules
