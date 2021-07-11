<template>
	<view class="container">
		<text class="new">新品</text>
		<view class="new_content">
			<navigator v-for="(item,index) in newItems" :key="index" :url="'/pages/detail/detail?goodId='+item.id" class="new_item"
			 hover-class="ahover">
				<view class="cover">
					<image :src="item.newImg" class="bgImg"></image>
				</view>
				<view class="texts">
					<text class="name">{{item.name}}</text>
					<view></view>
					<text class="title" v-if="item.title!=null">{{item.title}}</text>
				</view>
			</navigator>
		</view>
	</view>
</template>
<script>
	export default {
		data() {
			return {
				newItems: []
			}
		},
		onShow() {
			this.GET("/goods/find/all/toNew").then(result => {
				this.newItems = result.data;
			})
		},
		methods: {

		}
	}
</script>

<style lang="scss">
	$text_left_pd:10*2rpx;

	.container {
		letter-spacing: 6*2rpx;

		.new {
			line-height: 24*2rpx;
			padding-left: $text_left_pd;
		}

		.new_content {
			width: 100%;

			.new_item.ahover {
				opacity: 0.8;
			}

			.new_item {
				margin-bottom: 30*2rpx;

				.cover {
					position: relative;

					.bgImg {
						width: 100%;
						height: 200*2rpx;
					}

					.video {
						position: absolute;
						top: 40%;
						left: 45%;
						width: 48*2rpx;
						height: 48*2rpx;
					}
				}

				.texts {
					padding-left: $text_left_pd;

					.name {
						line-height: 25*2rpx;
						font-weight: bold;
						color: #323232;
					}

					.title {
						font-size: 12*2rpx;
						letter-spacing: 1rpx;
						color: #939393;
					}
				}
			}
		}
	}
</style>
