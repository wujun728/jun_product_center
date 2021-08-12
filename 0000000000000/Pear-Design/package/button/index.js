import Button from './src'

Button.install = function (Vue) { 
  Vue.component(Button.name, Button)
}

export default Button