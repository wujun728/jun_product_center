<template>
  <div id="button">
    <h3>基础</h3>
    <p-button-container>
      <p-button type="default">默认按钮</p-button>
      <p-button type="primary">主题按钮</p-button>
    </p-button-container>
    <h3>风格</h3>
    <p-button-container>
      <p-button type="default">默认按钮</p-button>
      <p-button type="primary">主题按钮</p-button>
      <p-button type="warming">警告按钮</p-button>
      <p-button type="failure">失败按钮</p-button>
    </p-button-container>
    <h3>基础</h3>
    <p-button-container>
      <p-button type="primary" size="md">中等按钮</p-button>
      <p-button type="primary" size="sm">小型按钮</p-button>
      <p-button type="primary" size="xs">迷你按钮</p-button>
      <p-button type="primary" size="lg">大型按钮</p-button>
    </p-button-container>
    <h3>禁用</h3>
    <p-button-container>
      <p-button type="default" disabled>禁用按钮</p-button>
      <p-button type="primary" disabled>禁用按钮</p-button>
    </p-button-container>
    <h3>圆角</h3>
    <p-button-container>
      <p-button type="default" round>禁用按钮</p-button>
      <p-button type="primary" round>禁用按钮</p-button>
    </p-button-container>
    <h3>图标</h3>
    <p-button-container>
      <p-button type="default" icon="scanning">禁用按钮</p-button>
      <p-button type="primary" icon="check-item">禁用按钮</p-button>
      <p-button type="default" icon="scanning"></p-button>
      <p-button type="primary" icon="check-item"></p-button>
    </p-button-container>
    <h3>加载</h3>
    <p-button-container>
      <p-button type="default" load>加载按钮</p-button>
      <p-button type="primary" load>加载按钮</p-button>
    </p-button-container>
    <h3>自适应</h3>
    <p-button type="default" full>自适应按钮</p-button>
    <br />
    <br />
    <p-button type="primary" full>自适应按钮</p-button>
    <br />
    <br />
    <p-button type="failure" full>自适应按钮</p-button>
    <h3>按钮组</h3>
    <p-button-group>
        <p-button type="default" round>default button</p-button>
        <p-button type="default" round>default button</p-button>
        <p-button type="default" round>default button</p-button>
    </p-button-group>
    <br/>
    <p-button-group>
        <p-button type="primary">default button</p-button>
        <p-button type="primary">default button</p-button>
        <p-button type="primary">default button</p-button>
    </p-button-group>
  </div>
</template>
<style>
h3{
  color: rgba(0, 0, 0, 0.65);
}
</style>