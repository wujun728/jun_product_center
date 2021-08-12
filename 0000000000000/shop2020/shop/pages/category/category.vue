<template>
	<view class="content">
		<scroll-view scroll-y class="left-aside">
			<view v-for="item in flist" :key="item.id" class="f-item b-b" :class="{active: item.id === currentId}" @click="tabtap(item)">
				{{item.name}}
			</view>
		</scroll-view>
		<scroll-view scroll-with-animation scroll-y class="right-aside" @scroll="asideScroll" :scroll-top="tabScrollTop">
			<view v-for="item in slist" :key="item.id" class="s-list" :id="'main-'+item.id">
				<text class="s-item">{{item.name}}</text>
				<view class="t-list">
					<view @click="navToList(item.id, titem.id)" v-if="titem.parent.id === item.id" class="t-item" v-for="titem in tlist" :key="titem.id">
						<image :src="imgPath(titem.img)"></image>
						<text>{{titem.name}}</text>
					</view>
				</view>
			</view>
		</scroll-view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				sizeCalcState: false,
				tabScrollTop: 0,
				currentId: 1,
 				flist: [],
				slist: [],
				tlist: [],
			}
		},
		onLoad(){
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
			async loadData(){
				var _this = this;
				//获取数据
				this.sendRequest({
					url : "get_product_cat",
					data : {pid : ''},
					method : "get",
					header: {'content-type' : "application/x-www-form-urlencoded"},
					success:function (res) {
						let cateList= res.data;  
						cateList.forEach(item=>{ 
							_this.flist.push(item);  //pid为父级id, 没有pid或者pid=0是一级分类
							item.children.forEach(it=>{
								_this.slist.push(it); //没有图的是2级分类
								it.children.forEach(i=>{
									_this.tlist.push(i); //3级分类
								}); 
							}) 
						}) 
						if(cateList&&cateList.length>0){
							_this.currentId=cateList[0].id;
						} 
					}
				});  
			},
			//一级分类点击
			tabtap(item){
				if(!this.sizeCalcState){
					this.calcSize();
				}
				this.currentId = item.id;
				let index = this.slist.findIndex(sitem=>sitem.parent.id === item.id);
				this.tabScrollTop = this.slist[index].top;
			},
			//右侧栏滚动
			asideScroll(e){
				if(!this.sizeCalcState){
					this.calcSize();
				}
				let scrollTop = e.detail.scrollTop;
				let tabs = this.slist.filter(item=>item.top <= scrollTop).reverse();
				if(tabs.length > 0){
					this.currentId = tabs[0].parent.id;
				}
			},
			//计算右侧栏每个tab的高度等信息
			calcSize(){
				let h = 0;
				this.slist.forEach(item=>{
					let view = uni.createSelectorQuery().select("#main-" + item.id);
					view.fields({
						size: true
					}, data => {
						item.top = h;
						h += data.height;
						item.bottom = h;
					}).exec();
				})
				this.sizeCalcState = true;
			},
			navToList(sid, tid){
				uni.navigateTo({
					url: `/pages/product/list?cateId=${tid}&fid=${this.currentId}`
				})
			}
		}
	}
</script>

<style lang='scss'>
	page,
	.content {
		height: 100%;
		background-color: #f8f8f8;
	}

	.content {
		display: flex;
	}
	.left-aside {
		flex-shrink: 0;
		width: 200upx;
		height: 100%;
		background-color: #fff;
	}
	.f-item {
		display: flex;
		align-items: center;
		justify-content: center;
		width: 100%;
		height: 100upx;
		font-size: 28upx;
		color: $font-color-base;
		position: relative;
		&.active{
			color: $base-color;
			background: #f8f8f8;
			&:before{
				content: '';
				position: absolute;
				left: 0;
				top: 50%;
				transform: translateY(-50%);
				height: 36upx;
				width: 8upx;
				background-color: $base-color;
				border-radius: 0 4px 4px 0;
				opacity: .8;
			}
		}
	}

	.right-aside{
		flex: 1;
		overflow: hidden;
		padding-left: 20upx;
	}
	.s-item{
		display: flex;
		align-items: center;
		height: 70upx;
		padding-top: 8upx;
		font-size: 28upx;
		color: $font-color-dark;
	}
	.t-list{
		display: flex;
		flex-wrap: wrap;
		width: 100%;
		background: #fff;
		padding-top: 12upx;
		&:after{
			content: '';
			flex: 99;
			height: 0;
		}
	}
	.t-item{
		flex-shrink: 0;
		display: flex;
		justify-content: center;
		align-items: center;
		flex-direction: column;
		width: 176upx;
		font-size: 26upx;
		color: #666;
		padding-bottom: 20upx;
		
		image{
			width: 140upx;
			height: 140upx;
		}
	}
</style>
