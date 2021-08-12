import { createRouter, createWebHashHistory } from 'vue-router'
import Home from '../components/HelloWorld'
import Document from "../components/Document"
import Material from "../components/Material"
import Button from "../document/button"
import Switch from "../document/switch"
import Start from "../document/start"
import Card from "../document/card"
import Radio from "../document/radio"
import CheckBox from "../document/checkbox"
import Slider from "../document/slider"
import Input from "../document/input"
import InputNumber from "../document/input-number"

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },{
    path: '/document',
    name: 'Document',
    component: Document,
    children: [
      {
        path: '/document-start',
        name: 'start',
        component: Start
      },
      {
        path: '/document-button',
        name: 'button',
        component: Button
      },
      {
        path: '/document-switch',
        name: 'switch',
        component: Switch
      },
      {
        path: '/document-card',
        name: 'card',
        component: Card
      },
      {
        path: '/document-radio',
        name: 'radio',
        component: Radio
      },
      {
        path: '/document-checkbox',
        name: 'checkbox',
        component: CheckBox
      },
      {
        path: '/document-slider',
        name: 'slider',
        component: Slider
      },
      {
        path: '/document-input',
        name: 'input',
        component: Input
      },
      {
        path: '/document-input-number',
        name: 'inputNumber',
        component: InputNumber
      }
    ]
  },{
    path: '/material',
    name: 'material',
    component: Material
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router