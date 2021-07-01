<template>
	<view>
		<view v-show="show" class="outter" @tap="hide"></view>
		<view v-show="show" class="inner">
			<view class="show-main">
				<view class="title">
					{{level==1?'破坏者':level==2?'青铜守卫':level==3?'黄金守卫':level==4?'钻石守卫':'最强王者'}}
				</view>
				<view class="level-image">
					<view class="">
						<image v-if="level==1" class="popup-image" src="../static/level/pohuaizhe.png" mode=""></image>
						<image v-else-if="level==2" class="popup-image" src="../static/level/qingtong.png" mode=""></image>
						<image v-else-if="level==3" class="popup-image" src="../static/level/huangjin.png" mode=""></image>
						<image v-else-if="level==4" class="popup-image" src="../static/level/zuanshi.png" mode=""></image>
						<image v-else-if="level==5" class="popup-image" src="../static/level/wangzhe.png" mode=""></image>
					</view>
				</view>

				<view class="bottom">
					<view class="">
						<button open-type="share" class="share" size="mini" type="warn">去分享</button>
					</view>
					<view class="">
						<button class="i-know" size="mini" type="primary" @tap="hide">知道了</button>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		props: {
			show: {
				type: Boolean,
				default: true
			},
			level: {
				type: Number,
				default: null,
			}
		},
		data() {
			return {

			};
		},
		methods: {
			hide() {
				this.$emit('hidePopup');
			}
		}
	}
</script>

<style>
	.outter {
		position: fixed;
		z-index: 998;
		top: 0;
		right: 0;
		bottom: 0;
		left: 0;
		background-color: rgba(0, 0, 0, 0.9);
	}

	.inner {
		position: fixed;
		z-index: 999;
		display: flex;
		justify-content: center;

		flex-direction: column;
		align-items: center;
		top: 50%;
		left: 50%;
		min-width: 500upx;
		min-height: 380upx;
		max-width: 100%;
		max-height: 80%;
		transform: translate(-50%, -65%);
		background: none;
		box-shadow: none;

	}

	.show-main {
		/* width: 400upx; */
		/* height: 400upx; */
		/* background-color: #fff; */
	}

	.popup-image {
		width: 360upx;
		height: 360upx;
	}
	.level-image{
		display: flex;
		justify-content: center;
		align-items: center;
	}

	.title {
		text-align: center;
		color: yellow;
		font-size: 50upx;
		font-weight: bold;
		border-bottom: 4upx solid #FF6600;
		border-right: 3upx solid #FF6600;
		/* box-shadow: 10upx 10upx 20upx #c5c5c5; */
		box-shadow: 6upx 8upx 20upx #fff;
	}

	.bottom {
		display: flex;
		flex-direction: row;
		justify-content: space-between;
	}
	.bottom button{
		width:200upx ;
	}

	.i-know {
		margin-left: 20upx;
	}
</style>
