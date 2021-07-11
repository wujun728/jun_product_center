<template>
	<view class="container" v-if="good!=null">
		<swiper class="swiper" indicator-dots="true" :autoplay="play1" interval="3000" duration="700">
			<swiper-item style="background-color: black;" class="swiper-item" v-for="(item,index) in good.goodsSlideList" :key="index"
			 @change="stopVideo()">
				<!-- #ifdef H5 -->
				<video style="width: 100%;" :src="item.slideImg" :poster="good.titleImg" ref="video" x5-video-player-type="h5" id="video"
				 v-if="item.slideImg.indexOf('.mp4')>0" :enable-progress-gesture='false' @play="play1=false" @pause="play1=true"
				 controls class="slide_video"></video>
				<!--  #endif -->
				<video v-if="item.slideImg.indexOf('.mp4')>0" style="width: 100%;" :src="item.slideImg" :enable-progress-gesture='false'
				 @play="play1=false" @pause="play1=true" controls class="slide_video"></video>
				<image v-else :src="item.slideImg" class="slide_img" mode="widthFix"></image>
			</swiper-item>
		</swiper>

		<view class="content_detail">
			<view class="title">
				<text class="ename" v-if="good.ename!=null">{{good.ename}}</text>
				<view></view>
				<text class="name">{{good.name}}</text>
			</view>
			<view class="title2">
				<text class="price">￥&nbsp;{{getDefaultSpec.goodsPrice}}</text>
				<view class="tags" v-if="good.tag!=''">
					<u-tag :text="item" v-for="(item,index) in good.tag.split(',')" :key="index" mode="plain" shape="circle" size="mini"
					 type="info" class="tag">
					</u-tag>
				</view>
			</view>
			<u-parse :html="good.detail" class="detail"></u-parse>
			<view class="content_imgs">
				<image :src="item.img" v-for="(item,index) in good.goodsImgList" :key="index" class="content_img" mode="widthFix"></image>
			</view>
			<view class="info">
				<text class="info_title">产品信息</text>
				<view></view>
				<text class="info_title_specs">规格</text>
				<view class="specs">
					<view v-for="(item,index) in good.goodsSpecList" :key="index" class="spec">
						<text class="spec_title">{{item.goodsSizeB}}磅({{item.goodsSizeG}}g)</text>
						<view></view>
						<text class="spec_parts" v-if="item.goodsParts!=null">配件：{{item.goodsParts}}</text>
					</view>
				</view>
				<view class="specs">
					<view v-for="(item,index) in good.goodsRemarkList" :key="index" class="spec">
						<text class="spec_title">{{item.remarkKey}}</text>
						<view></view>
						<text class="spec_parts">{{item.remarkVal}}</text>
					</view>
				</view>
			</view>
		</view>
		<view class="choose_buy" @click="toChoose">
			前往加购
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				play1: true,
				good: null
			}
		},
		onLoad(option) {
			if (option.goodId == null) {
				return;
			}
			this.GET("/goods/find/one/toDetail/" + option.goodId).then(result => {
				this.good = result.data;
				console.log(this.good)
				let atitle = "";
				if (this.good.ename != null) {
					atitle = this.good.ename + "[" + this.good.name + "]";
				} else {
					atitle = this.good.name;
				}
				uni.setNavigationBarTitle({
					title: atitle
				})

				if (this.good.goodsSlideList[0] == null) {
					this.good.goodsSlideList = new Array();
					this.good.goodsSlideList.push({
						id: 0,
						slideImg: this.good.titleImg
					})
				}
				console.log(this.$refs.child)

			})
		},
		computed: {
			getDefaultSpec() {
				if (this.good != null) {
					for (var i = 0; i < this.good.goodsSpecList.length; i++) {
						const el = this.good.goodsSpecList[i];
						if (this.good.defaultSize == el.id) {
							return el;
						}
					}
				}
			}
		},
		methods: {
			toChoose() {
				uni.reLaunch({
					url: '/pages/choose/choose?goodId=' + this.good.id
				});
			}
		}
	}
</script>

<style lang="scss">
	.container {
		.swiper {
			min-height: 240*2rpx;
			height: 320*2rpx;

			.swiper-item {
				.slide_video {
					height: 100%;
					line-height: 100%;
				}

				.slide_img {
					width: 100%;
				}
			}
		}

		.content_detail {
			padding: 12*2rpx;

			.title {
				margin: 10*2rpx 0px;
				font-weight: bold;
				line-height: 20*2rpx;
			}

			.title2 {

				.tags {
					margin: 10*2rpx 0px;

					.tag {
						margin-right: 8rpx;
						padding: 8rpx;
						border-color: black;
						color: black;
					}
				}
			}

			.detail {
				font-size: 20rpx;
				line-height: 18*2rpx;
				margin: 20*2rpx 0rpx;
			}

			.content_imgs {
				margin: 20*2rpx 0rpx;

				.content_img {
					width: 100%;
					max-width: 100%;
					max-height: 100%;
				}
			}

			.info {
				margin-bottom: 80*2rpx;

				.info_title {
					line-height: 60*2rpx;
					font-weight: bold;
					font-size: 12*2rpx;
				}

				.info_title_specs {
					color: #a1a1a1;
					font-size: 12*2rpx;
				}

				.specs {
					font-size: 12*2rpx;
					border-bottom: 1px #e1e1e1 solid;

					.spec {
						padding: 12*2rpx 0rpx;

						.spec_title {
							color: #a1a1a1;
							line-height: 40rpx;
						}
					}
				}
			}
		}

		.choose_buy {
			position: fixed;
			bottom: 0rpx;
			width: 100%;
			text-align: center;
			height: 48*2rpx;
			line-height: 48*2rpx;
			background-color: #442818;
			color: white;
		}
	}
</style>
