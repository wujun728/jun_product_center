<template>
  <div id="app">
    <p-container>
      <p-header>
        <Header></Header>
      </p-header>
      <p-main>
        <router-view></router-view>
      </p-main>
    </p-container>
  </div>
</template>
<script>
import Header from './layout/Header'
export default {
  name: 'App',
  components:{
    Header
  }
}
</script>

<style>
#app {
  height: 100%;
}
body{
  height: 100%;
  margin: 0px;
}
html{
  height: 100%;
}

.p-container{
  height: 100%;
}

.p-container .p-header{
  box-shadow: 0 2px 8px #f0f1f2;
}

</style>
