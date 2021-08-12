<template>
  <div class="app-container">
    <span v-if="isRouteLogged">
      <BlockLog />
    </span>
    <span v-else>
      <RouteLogin />
    </span>
  </div>
</template>
<script>
import BlockLog from './BlockLog'
import RouteLogin from '../common/RouteLogin'

export default {
  name: 'BlockLogIndex',
  components: { RouteLogin, BlockLog },
  computed: {
    isRouteLogged() {
      return !!this.$store.state.account.routeToken
    }
  }
}
</script>
