<template>
  <div class="app-container">
    <span v-if="isRouteLogged">
      <BlackList />
    </span>
    <span v-else>
      <RouteLogin />
    </span>
  </div>
</template>
<script>
import BlackList from './BlackList'
import RouteLogin from '../common/RouteLogin'

export default {
  name: 'BlackListIndex',
  components: { RouteLogin, BlackList },
  computed: {
    isRouteLogged() {
      return !!this.$store.state.account.routeToken
    }
  }
}
</script>
