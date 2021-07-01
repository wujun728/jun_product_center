<template>
	<view>
		<view :animation="animationData" style="background:red;height:100rpx;width:100rpx"></view>
		<levelPopup :show="popupShow" @hidePopup="hidePopup" :level="1"/>
	</view>
</template>

<script>
	import levelPopup from "@/components/levelPopup.vue"
	import uniPopup from "@/components/uni-popup/uni-popup.vue"
	export default {
		components: {
			levelPopup,
			uniPopup
		},
		data() {
			return {
				animationData: {},
				showPopupMiddleImg: true,
				popupShow:true,
				
			}
		},
		onShow: function() {
			var animation = uni.createAnimation({
				duration: 1000,
				timingFunction: 'ease',
			})

			this.animation = animation

			// 			animation.scale(2, 2).rotate(45).step()
			// 
			// 			this.animationData = animation.export()

			setTimeout(function() {
				animation.translate(300).step()
				this.animationData = animation.export()
				console.log("1000")
			}.bind(this), 1000);

			setTimeout(function() {
				this.rotateAndScale();
				console.log("3000")
			}.bind(this), 3000);

			setTimeout(function() {
				this.rotateThenScale();
				console.log("6000")
			}.bind(this), 6000);

			setTimeout(function() {
				this.rotateAndScaleThenTranslate();
				console.log("9000")
			}.bind(this), 9000);
		},
		methods: {
			hidePopup() {
				this.popupShow = false;
			},
			rotateAndScale: function() {
				// 旋转同时放大
				this.animation.rotate(60).scale(3, 3).step()
				this.animationData = this.animation.export()
			},
			rotateThenScale: function() {
				// 先旋转后放大
				this.animation.rotate(90).step()
				this.animation.scale(2, 2).step()
				this.animationData = this.animation.export()
			},
			rotateAndScaleThenTranslate: function() {
				// 先旋转同时放大，然后平移
				this.animation.rotate(180).scale(2, 2).step()
				this.animation.translate(100, 100).step({
					duration: 1000
				})
				this.animationData = this.animation.export()
			}
		}
	}
</script>

<style>
	.center-box{
		width: 500upx;
		height: 500upx;
	}

	.center-box .image {
		width: 100%;
		height: 100%;
	}

</style>
