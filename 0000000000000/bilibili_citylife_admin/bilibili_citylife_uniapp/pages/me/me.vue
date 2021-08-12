<template>
	<view class="">
		<u-navbar :is-back="false" :title="title" title-color="white" :background="background"></u-navbar>
		我的
	</view>
</template>

<script>
	export default {
		data() {
			return {
				title:'我的',
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
