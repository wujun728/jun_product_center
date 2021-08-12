import Header from './src'

Header.install = function (Vue) {
  Vue.component(Header.name, Header)
}

export default Header