import Card from './src'

Card.install = function (Vue) {
  Vue.component(Card.name, Card)
}

export default Card