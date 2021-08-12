import Switch from './src/index.vue'

Switch.install = function (Vue) { 
  Vue.component(Switch.name, Switch)
}

export default Switch