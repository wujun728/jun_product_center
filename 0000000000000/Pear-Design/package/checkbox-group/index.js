// 导入组件，组件必须声明 name
import CheckBoxGroup from './src'

// 为组件提供 install 安装方法，供按需引入
CheckBoxGroup.install = function (Vue) {
  Vue.component(CheckBoxGroup.name, CheckBoxGroup)
}

// 导出组件
export default CheckBoxGroup