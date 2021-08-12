// 导入组件，组件必须声明 name
import CheckBox from './src'

// 为组件提供 install 安装方法，供按需引入
CheckBox.install = function (Vue) {
  Vue.component(CheckBox.name, CheckBox)
}

// 导出组件
export default CheckBox