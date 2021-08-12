import Main from './src'

Main.install = function (Vue) {
  Vue.component(Main.name, Main)
}

export default Main