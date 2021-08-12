<template>
	<view>
		<view class="notice-item" v-for="(item, index) in noticeList" :key="index">
			<text class="time">{{item.dateline}}</text>
			<view class="content">
				<text class="title">{{item.name}}</text>
				<view class="img-wrapper" v-if="item.img!=''">
					<image class="pic" :src="imgPath(item.img)"></image>
				</view>
				<text class="introduce">
					{{item.remark}}
				</text>
				<view class="bot b-t" v-if="item.url!=''" @click="detail(item)">
					<text>查看详情</text>
					<text class="more-icon yticon icon-you"></text>
				</view>
			</view>
		</view>
		<!-- <view class="notice-item">
			<text class="time">昨天 12:30</text>
			<view class="content">
				<text class="title">新品上市，全场满199减50</text>
				<view class="img-wrapper">
					<image class="pic" src="https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3761064275,227090144&fm=26&gp=0.jpg"></image>
					<view class="cover">
						活动结束
					</view>
				</view>
				<view class="bot b-t">
					<text>查看详情</text>
					<text class="more-icon yticon icon-you"></text>
				</view>
			</view>
		</view> -->
		<!-- <view class="notice-item">
			<text class="time">2019-07-26 12:30</text>
			<view class="content">
				<text class="title">新品上市，全场满199减50</text>
				<view class="img-wrapper">
					<image class="pic" src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1556465765776&di=57bb5ff70dc4f67dcdb856e5d123c9e7&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01fd015aa4d95fa801206d96069229.jpg%401280w_1l_2o_100sh.jpg"></image>
					<view class="cover">
						活动结束
					</view>
				</view>
				<text class="introduce">新品上市全场2折起，新品上市全场2折起，新品上市全场2折起，新品上市全场2折起，新品上市全场2折起</text>
				<view class="bot b-t">
					<text>查看详情</text>
					<text class="more-icon yticon icon-you"></text>
				</view>
			</view>
		</view> -->
	</view>
</template>

<script>
	import uniLoadMore from '@/components/uni-load-more/uni-load-more.vue';
	export default {
		components: {
			uniLoadMore	
		},
		data() {
			return {
				noticeList: [],
				listQuery: { //查询分页
					name:"", 
					pageNo: 1,
					pageSize: 10
				},
			}
		},
		onLoad(options){ 
			this.loadData();
		},
		//下拉刷新
		onPullDownRefresh(){
			this.listQuery.pageNo=1;
			this.loadData('refresh');
		},
		//加载更多
		onReachBottom(){
			this.listQuery.pageNo++;
			this.loadData();
		},
		methods: {
			imgPath:function(path){
				if(path==''||path==null||path=='null'){
					return "/static/errorImage.jpg"				 
				}else if (path!=''&&path.indexOf('http')>=0) {
					return path;				 
				}
				return this.siteHost + path;
			},
			//加载商品 ，带下拉刷新和上滑加载
			async loadData(type='add', loading) {
				//没有更多直接返回
				if(type === 'add'){
					if(this.loadingType === 'nomore'){
						return;
					}
					this.loadingType = 'loading';
				}else{
					this.loadingType = 'more'
				}
				//加载第一页
				if(type === 'refresh'){
					if(loading == 1){
						this.listQuery.pageNo=1;
					}
				}
 				let _this=this;
				this.sendRequest({
					url : "get_notice_list",
					data : this.listQuery,
					method : "get",
					header: {'content-type' : "application/x-www-form-urlencoded"},
					success:function (res) {
						let pageData= res.data;  
						let noticeList = pageData.datas;
						if(type === 'refresh'){
							_this.noticeList = [];
						}
						_this.noticeList = _this.noticeList.concat(noticeList); 
						//判断是否还有下一页，有是more  没有是nomore(测试数据判断大于20就没有了)
						_this.loadingType  = pageData.pageNo<=pageData.totalPage ? 'nomore' : 'more';
						if(type === 'refresh'){
							if(loading == 1){
								uni.hideLoading()
							}else{
								uni.stopPullDownRefresh();
							}
						}
					}
				}); 
			},
			detail(item){
				if (item.url!=''&&item.url.indexOf('http')>=0) {
					window.location.href =item.url;
				}else{
					uni.navigateTo({
						url: item.url
					})
				} 
			}
		}
	}
</script>

<style lang='scss'>
	page {
		background-color: #f7f7f7;
		padding-bottom: 30upx;
	}

	.notice-item {
		display: flex;
		flex-direction: column;
		align-items: center;
	}

	.time {
		display: flex;
		align-items: center;
		justify-content: center;
		height: 80upx;
		padding-top: 10upx;
		font-size: 26upx;
		color: #7d7d7d;
	}

	.content {
		width: 710upx;
		padding: 0 24upx;
		background-color: #fff;
		border-radius: 4upx;
	}

	.title {
		display: flex;
		align-items: center;
		height: 90upx;
		font-size: 32upx;
		color: #303133;
	}

	.img-wrapper {
		width: 100%;
		height: 260upx;
		position: relative;
	}

	.pic {
		display: block;
		width: 100%;
		height: 100%;
		border-radius: 6upx;
	}

	.cover {
		display: flex;
		justify-content: center;
		align-items: center;
		position: absolute;
		left: 0;
		top: 0;
		width: 100%;
		height: 100%;
		background-color: rgba(0, 0, 0, .5);
		font-size: 36upx;
		color: #fff;
	}

	.introduce {
		display: inline-block;
		padding: 16upx 0;
		font-size: 28upx;
		color: #606266;
		line-height: 38upx;
	}

	.bot {
		display: flex;
		align-items: center;
		justify-content: space-between;
		height: 80upx;
		font-size: 24upx;
		color: #707070;
		position: relative;
	}

	.more-icon {
		font-size: 32upx;
	}
</style>
