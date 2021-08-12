import { defineStore } from 'pinia'
import type { GlobalTheme } from 'naive-ui'

export type ThemeName = 'dark' | 'light' | 'auto'
export interface AppConfiguration {
  theme: ThemeName
}

export type GetTheme = GlobalTheme | null

export const useAppStore = defineStore({
  id: 'app',
  state: (): AppConfiguration => ({
    theme: <ThemeName>localStorage.getItem('theme') ?? 'auto'
  }),
  getters: {
    getTheme(state): ThemeName {
      if (state.theme === null) {
        const localTheme = localStorage.getItem('theme')
        return localTheme as ThemeName
      } else {
        return state.theme
      }
    }
  },
  actions: {
    toggleTheme(theme: ThemeName) {
      this.theme = theme
      localStorage.setItem('theme', theme)
    }
  }
})
