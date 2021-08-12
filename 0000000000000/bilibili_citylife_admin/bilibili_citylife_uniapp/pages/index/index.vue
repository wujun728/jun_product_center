<template>
	<view class="content">
		<u-navbar :is-back="false" :title="title" title-color="white" :background="background"></u-navbar>

		<view class="search">
			<u-search border-color="#f4f5f9" search-icon-color="#FF6666" placeholder="关键词" v-model="keyword" @search="search"
			 @custom="search"></u-search>
		</view>

		<view class="wrap">
			<u-swiper :list="list" height="350"></u-swiper>
		</view>

		<view class="item_wrap" v-for="(item,index) in activityList" :key="index">
			<view class="card">
				<view class="top">
					<text>{{item.title}}</text>
				</view>
				<view class="middle">
					<view class="left">
						<image :src="item.img" mode="aspectFill"></image>
					</view>
					<view class="right">
						<view class="content">
							<text>{{item.detail}}</text>
						</view>
						<view class="tags" >
							<view v-for="(it,ind) in item.label" :key="ind">
								<view class="item">
									{{it}}
								</view>
							</view>
						</view>
					</view>
				</view>
				
				<view class="bottom">
					<view class="left">
						{{item.createTime}}
					</view>
					<view class="right">
						<view class="view">
							<u-icon name="eye" color="#99CCFF"></u-icon>
							<text>{{item.views}}</text>
						</view>
						<view class="zan">
							<u-icon name="thumb-up" color="#FF6666"></u-icon>
							<text>{{item.likes}}</text>
						</view>
						<view class="comments" >
							<u-icon name="more-circle" color="#99CC66"></u-icon>
							<text>{{item.comments}}</text>
						</view>
					</view>
				</view>
			</view>
		</view>

	</view>
</template>

<script>
	import {
		mapGetters,
		mapActions
	} from 'vuex'
	export default {
		data() {
			return {
				title: '首页',
				background: {
					backgroundImage: 'linear-gradient(to right, #fa709a 0%, #fee140 100%)'
				},
				keyword: '',
				list: [],
				queryParams:{
					pageNum:1,
					pageSize:10
				},
				activityList:[]
			}
		},
		onLoad() {
			if (!this.getIsLogin) {
				this.init();
			}
		},
		computed: {
			...mapGetters(['getUserinfo', 'getNeedAuth', 'getIsLogin'])
		},

		methods: {
			...mapActions(['login', 'authUserInfo']),
			init() {
				this.$u.api.getSwiperActivity().then(res=>{
					this.list=res.data.map(item=>{
						return {
							id:item.id,
							image:item.img
						}
					})
				});
				this.$u.api.getActivityList(this.queryParams).then(res=>{
					console.log(JSON.stringify(res));
					this.activityList=res.data.map(item=>{
						item.label=item.label.split(",");
						return item;
					});
				})
				this.login();
			},
			search() {
				console.log(this.keyword);
			}


		}
	}
</script>

<style scoped lang="scss">
	.content {
		.search {
			margin: 10rpx 20rpx;
		}

		.wrap {
			height: 300rpx;
			margin-bottom: 80rpx;
		}

		.card {
			width: 700rpx;
			height: 250rpx;
			display: flex;
			flex-direction: column;
			margin: 20rpx auto;
			// background-color: rgba($color: #000000, $alpha: 0.2);

			.top {
				text {
					font-size: 30rpx;
					font-weight: bold;
					display: -webkit-box;
					-webkit-line-clamp: 1;
					-webkit-box-orient: vertical;
					overflow: hidden;
					margin: 5rpx 10rpx;
				}
			}

			.middle {
				display: flex;
				height: 200rpx;

				.left {
					// background-color: rgba($color: #000000, $alpha: 0.3);
					image {
						width: 250rpx;
						height: 150rpx;
						margin: 0 20rpx 10rpx 10rpx;
						border-radius: 10rpx;

					}
				}

				.right {
					flex: 1;
					.content {
							text{
								display: -webkit-box;
								-webkit-line-clamp: 3;
								-webkit-box-orient: vertical;
								overflow: hidden;
							}
					}

					.tags {
						display: flex;
						width: 400rpx;
						white-space: nowrap;
						overflow: hidden;
						.item{
							margin-right: 20rpx;
							background-color: rgba($color: #000000, $alpha: 0.08);
							padding: 5rpx 10rpx;
							border-radius: 5rpx;
							font-size: 13rpx;
							
						}
					}
				}
			}
			
			.bottom{
				// flex: 1;
				display: flex;
				// background-color: rgba($color: #000000, $alpha: 0.5);
				height: 70rpx;
				align-items: center;
				justify-content: space-between;
				.left{
					font-size: 13rpx;
					margin-left: 10rpx;
				}
				.right{
					flex: 1;
					display: flex;
					justify-content: space-around;
					margin: 0 40rpx;
					.view{
						text{margin-left: 8rpx;}
					}
					.zan{
						text{margin-left: 8rpx;}
					}
					.comments{
						text{margin-left: 8rpx;}
					}
				}
			}
		}
	}
</style>
