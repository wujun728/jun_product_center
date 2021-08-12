<template>
	<view>
		<view class="user-section">
			<image class="bg" src="/static/user-bg.jpg"></image>
			<text class="bg-upload-btn yticon icon-paizhao"></text>
			<view class="portrait-box">
				<image class="portrait" :src="imgMemberPath(unifiedUser.avatar)" @click="uploadImg"></image>
				<text class="pt-upload-btn yticon icon-paizhao"></text>
			</view>
		</view>
		<view class="content">
			<view class="row b-b">
				<text class="tit">昵称</text>
				<input class="input" type="text" v-model="unifiedUser.nickName" placeholder="昵称" placeholder-class="placeholder" />
			</view> 
		</view>
		<button class="add-btn" @click="confirm">提交</button>
	</view>
</template>

<script>
	import {  
	    mapState,  
	    mapMutations  
	} from 'vuex';  
	export default {
		data() {
			return {
				
			};
		},
		computed:{
			...mapState(['unifiedUser']),
		},
		methods: {
			...mapMutations(['login']),
 			imgMemberPath:function(path){
				if(path==''||path==null||path=='null'){
					return "/static/missing-face.png"				 
				}else if (path!=''&&path.indexOf('http')>=0) {
					return path;				 
				}
				return this.siteHost + path;
			},
			//提交
			confirm(){ 
				let _this=this;
				this.sendRequest({
					url : 'save_profile',
					data : this.unifiedUser,
					method : "post",
					success:function (res) {
						if(res.code=='200'){
							_this.$api.msg(res.message);
 							_this.login({unifiedUser:res.data});
							uni.navigateBack();  
						}else{
							_this.$api.msg(res.message);
						}
					}
				}); 
			},
			uploadImg:function(){
				var _this=this;
				uni.chooseImage({
					success: function (res) {
						const tempFilePaths = res.tempFilePaths;
						_this.sendUpload({
							url : 'image_Up',
							filePath: tempFilePaths[0],
							name: 'file',
							success:function (res) {
								if(res.code=='200'){
									_this.$api.msg(res.message);
									_this.unifiedUser.avatar=res.data.fileUrl;
								}else{
									_this.$api.msg(res.message);
								}
							}
						});  
					},fail:function(res){
						console.log("用户取消上传文件")
					}
				});
			},
		}
	}
</script>

<style lang="scss">
	page{
		background: $page-color-base;
	}
	.user-section{
		display:flex;
		align-items:center;
		justify-content: center;
		height: 460upx;
		padding: 40upx 30upx 0;
		position:relative;
		.bg{
			position:absolute;
			left: 0;
			top: 0;
			width: 100%;
			height: 100%;
			filter: blur(1px);
			opacity: .7;
		}
		.portrait-box{
			width: 200upx;
			height: 200upx;
			border:6upx solid #fff;
			border-radius: 50%;
			position:relative;
			z-index: 2;
		}
		.portrait{
			position: relative;
			width: 100%;
			height: 100%;
			border-radius: 50%;
		}
		.yticon{
			position:absolute;
			line-height: 1;
			z-index: 5;
			font-size: 48upx;
			color: #fff;
			padding: 4upx 6upx;
			border-radius: 6upx;
			background: rgba(0,0,0,.4);
		}
		.pt-upload-btn{
			right: 0;
			bottom: 10upx;
		}
		.bg-upload-btn{
			right: 20upx;
			bottom: 16upx;
		}
	}

	.row{
		display: flex;
		align-items: center;
		position: relative;
		padding:0 30upx;
		height: 110upx;
		background: #fff;
		
		.tit{
			flex-shrink: 0;
			width: 120upx;
			font-size: 30upx;
			color: $font-color-dark;
		}
		.input{
			flex: 1;
			font-size: 30upx;
			color: $font-color-dark;
		}
		.icon-shouhuodizhi{
			font-size: 36upx;
			color: $font-color-light;
		}
	}
	.default-row{
		margin-top: 16upx;
		.tit{
			flex: 1;
		}
		switch{
			transform: translateX(16upx) scale(.9);
		}
	}
	.add-btn{
		display: flex;
		align-items: center;
		justify-content: center;
		width: 690upx;
		height: 80upx;
		margin: 60upx auto;
		font-size: $font-lg;
		color: #fff;
		background-color: $base-color;
		border-radius: 10upx;
		box-shadow: 1px 2px 5px rgba(219, 63, 96, 0.4);
	}
</style>
