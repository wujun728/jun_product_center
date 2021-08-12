<template>
  <div class="app-container">
    <span v-if="isRouteLogged">
      <RateLimitRule />
    </span>
    <span v-else>
      <RouteLogin />
    </span>
  </div>
</template>
<script>
import RateLimitRule from './RateLimitRule'
import RouteLogin from '../common/RouteLogin'

export default {
  name: 'RateLimitRuleIndex',
  components: { RouteLogin, RateLimitRule },
  computed: {
    isRouteLogged() {
      return !!this.$store.state.account.routeToken
    }
  }
}
</script>
