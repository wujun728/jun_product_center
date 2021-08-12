<template>
  <div class="app-container">
    <span v-if="isRouteLogged">
      <RouteUser />
    </span>
    <span v-else>
      <RouteLogin />
    </span>
  </div>
</template>
<script>
import RouteUser from './RouteUser'
import RouteLogin from '../common/RouteLogin'

export default {
  name: 'RouteUserIndex',
  components: { RouteLogin, RouteUser },
  computed: {
    isRouteLogged() {
      return !!this.$store.state.account.routeToken
    }
  }
}
</script>
