import client from 'webpack-theme-color-replacer/client'
import { generate } from '@ant-design/colors'

export default {
  getAntdSerials (color) {
    // 淡化（即less的tint）
    // eslint-disable-next-line @typescript-eslint/ban-ts-ignore
    // @ts-ignore
    const lightens = new Array(9).fill().map((t, i) => {
      return client.varyColor.lighten(color, i / 10)
    })
    // colorPalette变换得到颜色值
    const colorPalettes = generate(color)
    const rgb = client.varyColor.toNum3(color.replace('#', '')).join(',')
    return lightens.concat(colorPalettes).concat(rgb)
  },
  changeColor (newColor) {
    const options = {
      newColors: this.getAntdSerials(newColor), // new colors array, one-to-one corresponde with `matchColors`
      changeUrl (cssUrl) {
        return `/${cssUrl}` // while router is not `hash` mode, it needs absolute path
      }
    }
    return client.changer.changeColor(options, Promise)
  }
}
