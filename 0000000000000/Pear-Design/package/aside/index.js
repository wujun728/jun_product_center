import Aside from './src'

Aside.install = function (Vue) {
  Vue.component(Aside.name, Aside)
}

export default Aside