<template>
  <div class="app-container">
    <span v-if="isRouteLogged">
      <RateLimitLog />
    </span>
    <span v-else>
      <RouteLogin />
    </span>
  </div>
</template>
<script>
import RateLimitLog from './RateLimitLog'
import RouteLogin from '../common/RouteLogin'

export default {
  name: 'RateLimitLogIndex',
  components: { RouteLogin, RateLimitLog },
  computed: {
    isRouteLogged() {
      return !!this.$store.state.account.routeToken
    }
  }
}
</script>
