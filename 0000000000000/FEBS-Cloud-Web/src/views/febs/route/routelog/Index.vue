<template>
  <div class="app-container">
    <span v-if="isRouteLogged">
      <RouteLog />
    </span>
    <span v-else>
      <RouteLogin />
    </span>
  </div>
</template>
<script>
import RouteLog from './RouteLog'
import RouteLogin from '../common/RouteLogin'

export default {
  name: 'RouteLogIndex',
  components: { RouteLogin, RouteLog },
  computed: {
    isRouteLogged() {
      return !!this.$store.state.account.routeToken
    }
  }
}
</script>
