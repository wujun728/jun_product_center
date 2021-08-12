declare module '*.svg' {

  import { VNode } from 'vue'

  type Svg = VNode;

  const content: Svg
  export default content
}
