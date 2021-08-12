import Col from './src'

Col.install = function (Vue) {
  Vue.component(Col.name, Col)
}

export default Col