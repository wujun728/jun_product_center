import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueJsx from '@vitejs/plugin-vue-jsx'
import viteSvgIcons from 'vite-plugin-svg-icons'
// eslint-disable-next-line @typescript-eslint/ban-ts-comment
// @ts-ignore
import PurgeIcons from 'vite-plugin-purge-icons'

import path from 'path'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    vueJsx(),
    viteSvgIcons({
      // 指定需要缓存的图标文件夹
      iconDirs: [path.resolve(process.cwd(), 'src/assets/icons')],
      // 指定symbolId格式
      symbolId: 'icon-[dir]-[name]'
    }),
    PurgeIcons()
  ],
  resolve: {
    alias: [
      {
        find: '@',
        replacement: path.resolve(__dirname, './src/')
      }
    ]
  },
  server: {
    host: true,
    port: 5000,
    open: true
  },
  build: {
    sourcemap: true
  }
})
