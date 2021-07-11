<template>
	<view class="container">
		<view class="tab">
			<u-tabs :list="goodTypes" :current="currentIndex" active-color="black" font-size="24" :bold="false" item-width="180"
			 :bar-style="utab.barStyle" @change="changeType">
			</u-tabs>
		</view>
		<view class="goods_content uni-flex uni-row" :class="currentIndex==1?'goods_content_active1':''">
			<view class="good_item flex-item" v-for="(item,index) in getGoods" :key="item.id">
				<view class="good_item_title">
					<image :src="item.titleImg" class="title_img" mode="widthFix"></image>
					<view class="title_detail">
						<text class="ename">{{item.ename!=null?item.ename:''}}</text>
						<text class="name">{{item.name!=null?item.name:''}}</text>
						<text class="detail">{{item.title!=null?item.title:''}}</text>
					</view>
				</view>
				<view class="good_item_bottom">
					<view class="bottom_detail">
						<text class="price">￥{{item.goodsPrice}}</text>
						<text class="spec">
							<text v-if="item.type==2">{{item.goodsSizeG}}g/{{item.goodsSizeB}}磅</text>
							<text v-else-if="item.type==1">{{item.goodsSizeG}}/{{item.goodsSizeB==null?'':item.goodsSizeB}}张</text>
							<text v-else>{{item.goodsSizeG}}g/{{item.goodsSizeB}}份</text>
						</text>
					</view>
					<view class="add">
						<text>+</text>
					</view>
				</view>
			</view>
			<u-empty text="没有更多啦" mode="list" v-if="getGoods[0]==null" class="goods_empty"></u-empty>
			<view class="empty_bar" v-else>没有更多啦</view>
		</view>
	</view>
</template>
<script>
	export default {
		data() {
			return {
				currentIndex: 0,
				utab: {
					barStyle: {
						"height": "1rpx"
					},
				},
				goodTypes: [],
				goods: []
			}
		},
		onLoad(option) {
			this.GET("/goods-type/findAll").then(result => {
				const res = result.data;
				res.unshift({
					id: 0,
					name: "全部"
				});
				this.goodTypes = result.data;
				this.GET("/goods/find/all").then(result => {
					this.goods = result.data;

				})
			})
			if (option.goodId) {

			}
		},
		computed: {
			getGoods() {
				let goods_copy = [];
				if (this.currentIndex == 0) {
					for (let i = 0; i < this.goods.length; i++) {
						const good = this.goods[i];
						// 如果是卡券，则不加入
						if (good.type == 1) {
							continue;
						}
						goods_copy.push(good);
					}
				} else {
					for (let i = 0; i < this.goods.length; i++) {
						const good = this.goods[i];
						if (this.goods[i].type == this.currentIndex) {
							goods_copy.push(good);
						}
					}
				}
				for (let i = 0; i < goods_copy.length; i++) {
					const good = goods_copy[i];
					good["goodsSizeB"] = good.goodsSpecList[0].goodsSizeB;
					good["goodsSizeG"] = good.goodsSpecList[0].goodsSizeG;
					good["goodsPrice"] = good.goodsSpecList[0].goodsPrice;
				}
				return goods_copy;
			}
		},
		methods: {
			changeType(index) {
				this.currentIndex = index;
			}
		}
	}
</script>

<style lang="scss">
	.container {
		margin-bottom: 12rpx;
		position: relative;

		.tab {
			position: fixed;
			top: 0rpx;
			z-index: 2;
			/* #ifdef H5 */
			top: 40px !important;
			/* #endif */
			width: 100%;
		}

		.goods_content {
			margin-top: 56*2rpx;
			flex-direction: row;
			flex-wrap: wrap;

			.good_item {
				width: 50%;
				padding: 0rpx 20*2rpx;

				.good_item_title {

					.title_img {
						width: 100%;
					}

					.title_detail {

						.ename,
						.name,
						.detail {
							display: block;
							overflow: hidden;
							text-overflow: ellipsis;
							white-space: nowrap;
							width: 100%;
							color: black;
						}

						.ename {
							font-weight: bold;
						}

						.name {
							font-size: 12*2rpx;
						}

						.detail {
							font-size: 8*2rpx;
							color: #969696;
						}
					}
				}

				.good_item_bottom {
					margin-top: 12*2rpx;

					.bottom_detail {
						float: left;
						line-height: 30rpx;

						.price {
							display: block;
						}

						.spec {
							color: #969696;
							font-size: 8*2rpx;
						}
					}

					.add {
						float: right;
						width: 20*2rpx;
						height: 20*2rpx;
						line-height: 20*2rpx;
						text-align: center;
						background-color: rgba(150, 150, 150, 0.2);

						::after {
							content: "";
							display: block;
							clear: both;
						}
					}
				}
			}
		}

		.goods_content_active1 {


			.good_item {
				width: 100%;
				position: relative;

				.good_item_title {

					.title_img {
						width: 100%;
						box-shadow: 3px 3px 10px 0px gray;
						border-radius: 8px;
					}

					.title_detail {

						.ename,
						.name {
							line-height: 60rpx;
						}
					}
				}
			}

			.good_item_bottom {
				position: absolute;
				bottom: 0px;
				right: 40rpx;

				.bottom_detail {
					float: none !important;

					.price {
						display: none !important;
					}

					.spec {
						margin-bottom: 12rpx;
						display: block;
					}
				}
			}

		}

		.empty_bar {
			text-align: center;
			line-height: 100rpx;
			line-height: 100rpx;
			width: 100%;
		}

		.goods_empty {
			margin-top: 12*2rpx;
			width: 100%;
		}
	}
</style>
