import Container from './src/index.vue'

Container.install = function (Vue) { 
  Vue.component(Container.name, Container)
}

export default Container