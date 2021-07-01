<template>
	<view class="content">
		<!-- <view class="title">挑战赛-结果</view> -->
		<view class="" style="margin-bottom: 20upx;">
			<view class="">
				<uni-steps :mydata="[
					{title: '破坏者'}, 
					{title: '青铜守卫'}, 
					{title: '黄金守卫'}, 
					{title: '钻石守卫'},
					{title: '王者'}]"
				 :active="level-1">
				</uni-steps>
			</view>
			<view class="level">
				<view class="text-left">
					<view v-if="level==1" class="level1">
						破坏者
					</view>
					<view v-else-if="level==2" class="level2">
						青铜守卫
					</view>
					<view v-else-if="level==3" class="level3">
						黄金守卫
					</view>
					<view v-else-if="level==4" class="level4">
						钻石守卫
					</view>
					<view v-else-if="level==5" class="level5">
						最强王者
					</view>
				</view>
				<view class="text-right">
					{{score!=0?score:''}}0分
				</view>
			</view>
			<view class="summary">
				<view v-if="level==1" class="level1">
					{{multiTips[Math.round(Math.random()*10)]}}
				</view>
				<view v-else-if="level==2" class="level2">
					{{multiTips[Math.round(Math.random()*10)]}}
				</view>
				<view v-else-if="level==3" class="level3">
					{{multiTips[Math.round(Math.random()*10)]}}
				</view>
				<view v-else-if="level==4" class="level4">
					{{multiTips[Math.round(Math.random()*10)]}}
				</view>
				<view v-else-if="level==5" class="level5">
					{{multiTips[Math.round(Math.random()*10)]}}
				</view>

			</view>
			<view class="">
				<view class="table">
					<view class="table-item" v-for="(item,index) in list" :key="index" :class="index%2==1?'gray':''">
						<view class="garbage-name">{{item.garbageName}}</view>
						<view class="selected" :class="item.garbageType!=item.selectedType?'through':''">
							<view v-if="item.selectedType==1" class="">干垃圾</view>
							<view v-else-if="item.selectedType==2" class="">湿垃圾</view>
							<view v-else-if="item.selectedType==3" class="">可回收物</view>
							<view v-else-if="item.selectedType==4" class="">有害垃圾</view>
						</view>
						<view class="result">
							<view v-if="item.garbageType==item.selectedType" class="">
								<image class="icon-img" src="../../static/icos/right-full.png" mode=""></image>
							</view>
							<view v-else class="">
								<view v-if="item.garbageType==1" class="garbage-gan-item">干垃圾</view>
								<view v-else-if="item.garbageType==2" class="garbage-shi-item">湿垃圾</view>
								<view v-else-if="item.garbageType==3" class="garbage-huishou-item">可回收物</view>
								<view v-else-if="item.garbageType==4" class="garbage-youhai-item">有害垃圾</view>
							</view>
						</view>
					</view>
				</view>
			</view>
			<view class="button-show">
				<view class="">
					<button class="goonButton button-left" type="warn" @tap="goOnChallenge">继续挑战</button>
				</view>
				<view class="">
					<button class="goonButton" type="primary" open-type="share">考考别人</button>
				</view>
			</view>
		</view>
		<!-- <view  >
			<ad unit-id="adunit-19c9c625b0b4a546" ad-type="video" ad-theme="white"></ad>
		</view> -->
		<view >
			<ad unit-id="adunit-060249bea9401e5c"></ad>
		</view>
		<levelPopup :show="popupShow" @hidePopup="hidePopup" :level="level" />
		<share />
	</view>
</template>

<script>
	import uniSteps from "@/components/uni-steps/uni-steps.vue"
	import levelPopup from "@/components/levelPopup.vue"
	import share from "@/components/share.vue"
	export default {
		data() {
			return {
				popupShow: false,
				level: null,
				score: null,
				list: [],

				multiTips: [
					"让垃圾找到自己的归属,请给垃圾找个合适的家",
					"世界上没有垃圾，只有放错地方的宝藏",
					"1吨废物=700公斤的错误条件+200公斤的懒惰思想+100公斤真正的废物",
					"从垃圾中淘宝不如变垃圾为宝",
					"垃圾应该成为当今时代的一首诗",
					"继农业革命、工业革命、计算机革命之后，影响人类生存发展的又一次浪潮，将是在世纪之交时要出现的垃圾革命",
					"地球所提供的足以满足每个人的需要，但不足以填满每个人的欲壑",
					"参与垃圾分类，保护地球家园，共创美好世界",
					"大地给予所有的人是物质的精华，而最后，它从人们那里得到的回赠却是这些物质的垃圾",
					"我们违背大自然的结果是：我们破坏了自然景观的美，自然动态的美和天籁的美",
					"让垃圾找到自己的归属,请给垃圾找个合适的家",
				],
				// level: 4,
				// score: 6,
				// list: [{
				// 	"questionId": 8,
				// 	"garbageType": 1,
				// 	"garbageName": "猫砂",
				// 	"analysis": null,
				// 	"remark": null,
				// 	"selectedType": 4
				// }, {
				// 	"questionId": 15,
				// 	"garbageType": 1,
				// 	"garbageName": "编织袋",
				// 	"analysis": null,
				// 	"remark": null,
				// 	"selectedType": 4
				// }, {
				// 	"questionId": 28,
				// 	"garbageType": 1,
				// 	"garbageName": "毛发",
				// 	"analysis": null,
				// 	"remark": null,
				// 	"selectedType": 4
				// }, {
				// 	"questionId": 31,
				// 	"garbageType": 1,
				// 	"garbageName": "橡皮泥",
				// 	"analysis": null,
				// 	"remark": null,
				// 	"selectedType": 4
				// }, {
				// 	"questionId": 42,
				// 	"garbageType": 1,
				// 	"garbageName": "竹筷",
				// 	"analysis": null,
				// 	"remark": null,
				// 	"selectedType": 4
				// }, {
				// 	"questionId": 45,
				// 	"garbageType": 1,
				// 	"garbageName": "伞",
				// 	"analysis": null,
				// 	"remark": null,
				// 	"selectedType": 4
				// }, {
				// 	"questionId": 50,
				// 	"garbageType": 1,
				// 	"garbageName": "毛笔",
				// 	"analysis": null,
				// 	"remark": null,
				// 	"selectedType": 4
				// }, {
				// 	"questionId": 51,
				// 	"garbageType": 1,
				// 	"garbageName": "眼镜",
				// 	"analysis": null,
				// 	"remark": null,
				// 	"selectedType": 4
				// }, {
				// 	"questionId": 52,
				// 	"garbageType": 1,
				// 	"garbageName": "打火机",
				// 	"analysis": null,
				// 	"remark": "干垃圾哟",
				// 	"selectedType": 4
				// }, {
				// 	"questionId": 54,
				// 	"garbageType": 1,
				// 	"garbageName": "烟盒",
				// 	"analysis": null,
				// 	"remark": null,
				// 	"selectedType": 4
				// }],

			}
		},
		components: {
			uniSteps,
			levelPopup,
			share
		},
		onLoad: function(option) { //option为object类型，会序列化上个页面传递的参数
			console.log(option); //打印出上个页面传递的参数。
			console.log(option.score); //打印出上个页面传递的参数。
			console.log(option.list); //打印出上个页面传递的参数。
			this.insertAd();
			this.score = option.score
			this.list = JSON.parse(option.list);
			if (this.score <= 1) {
				this.level = 1
			} else if (this.score <= 4) {
				this.level = 2
			} else if (this.score <= 7) {
				this.level = 3
			} else if (this.score <= 9) {
				this.level = 4
			} else {
				this.level = 5;
			}
			console.log(this.level)
			this.popupShow = true;
		},
		methods: {
			insertAd() {
				// 在页面中定义插屏广告
				let interstitialAd = null
			
				// 在页面onLoad回调事件中创建插屏广告实例
				if (wx.createInterstitialAd) {
					interstitialAd = wx.createInterstitialAd({
						adUnitId: 'adunit-c9a12748b3ed91fa'
					})
					interstitialAd.onLoad(() => {})
					interstitialAd.onError((err) => {})
					interstitialAd.onClose(() => {})
				}
			
				// 在适合的场景显示插屏广告
				if (interstitialAd) {
					interstitialAd.show().catch((err) => {
						console.error(err)
					})
				}
			},
			hidePopup() {
				this.popupShow = false;
			},
			goOnChallenge() {
				uni.reLaunch({
					url: '/pages/challenge/challenge'
				});
			}
		}
	}
</script>

<style>
	@import url("challengeResult.css");
</style>
