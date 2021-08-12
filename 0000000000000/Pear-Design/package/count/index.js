import Count from './src'

Count.install = function (Vue) { 
  Vue.component(Count.name, Count)
}

export default Count