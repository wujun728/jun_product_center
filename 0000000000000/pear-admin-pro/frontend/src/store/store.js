const requireStoreContext = require.context('./modules/', false, /([\w+]\.(js))/);

const modules = requireStoreContext.keys().reduce((modules, fileName) => {
  const name = fileName.replace(/(\.\/|\.js)/g, '');
  return {
    ...modules,
    [name]: requireStoreContext(fileName).default
  }
}, {});

export default modules;
