import { Component } from 'vue'
import app from '@/app'
// const requireContext = require.context('@/components/PageComponent/', true, /([\w+]\.(vue|tsx|ts))/)
const requireContext = require.context('@/components/PageComponent/', true, /([index]\.(ts))/)

interface Modules {
  [key: string]: Component;
}
const components: Modules = requireContext.keys().reduce((module: Modules, key) => {
  const com: Component = requireContext(key).default
  const componentName = com.name as string
  return {
    ...module,
    [componentName]: com
  }
}, {} as Modules)

Object.keys(components).forEach(key => {
  app.component(key, components[key])
})
