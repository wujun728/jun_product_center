<template>
	<view class="">
		<u-navbar :is-back="false" :title="title" title-color="white" :background="background"></u-navbar>
		热门
	</view>
</template>

<script>
	export default {
		data() {
			return {
				title:'热门',
				background:{
					backgroundImage: 'linear-gradient(to right, #fa709a 0%, #fee140 100%)'
				}
			}
		},
		onLoad() {
	
		},
		methods: {
	
		}
	}
</script>

<style>
</style>
