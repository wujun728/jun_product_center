import RadioGroup from './src/index.vue'

RadioGroup.install = function (Vue) { 
  Vue.component(RadioGroup.name, RadioGroup)
}

export default RadioGroup