<template>
	<view class="content" v-if="controlShow">
		<!-- <view class="title">环保守卫者挑战赛</view> -->

		<view :animation="animationData" class="main-panel">
			<view class="quesion-panel">
				<view class="quesion-panel-item1">
					<view class="quesion-panel-text">{{current+1}}. </view>
					<view class="quesion-panel-text"> {{questionBanks.length>0?questionBanks[current].garbageName:"干电池"}}</view>
				</view>
				<view class="quesion-panel-item2">
					<view class="">属于哪种垃圾分类？</view>
				</view>
				<view class="quesion-panel-item3">
					<view class="gary">（点击下方图标选择）</view>
				</view>
				<!-- <view class="quesion-panel-item2">
					<view class="">{{current+1}}/10</view>
				</view> -->
			</view>
			<view class="main-show-classify">
				<view class="main-classify">
					<view @click="switchTabToClassify(1)">
						<image class="main-img" src="../../static/icos/ico-1.jpg"></image>
					</view>
					<view @click="switchTabToClassify(2)">
						<image class="main-img" src="../../static/icos/ico-2.jpg"></image>
					</view>
				</view>
				<view class="main-classify">
					<view @click="switchTabToClassify(3)">
						<image class="main-img" src="../../static/icos/ico-3.jpg"></image>
					</view>
					<view @click="switchTabToClassify(4)">
						<image class="main-img" src="../../static/icos/ico-4.jpg"></image>
					</view>
				</view>
			</view>
			<view class="top-text">
				<view class="">
					{{current+1}}
				</view>
				<view class="gary">
					/ 10
				</view>
			</view>
			<view  >
				<ad unit-id="adunit-19c9c625b0b4a546" ad-type="video" ad-theme="white"></ad>
			</view>
		</view>

		<!-- <share /> -->
	</view>
</template>

<script>
	import share from "@/components/share.vue"

	export default {
		components: {
			share,
		},
		data() {
			return {
				questionBanks: [],
				score: 0,
				current: 0,
				controlShow: true,
				animationData: {},
			}
		},
		onShow() {
			console.log("size:"+this.questionBanks.length)
			// let map = new Map();
			// for (let i = 0; i < 10000; i++) {
			// 	let temp = Math.round(Math.random() * 10);
			// 	if (map.has(temp)) {
			// 		map.set(temp, (map.get(temp)) + 1);
			// 	} else {
			// 		map.set(temp, 1);
			// 	}
			// }
			// console.log("size:" + map.size)
			// for (let [key, value] of map) {
			// 	console.log(`${key}==${value}`)
			// }
			uni.login({
				provider: 'weixin',
				success: function(loginRes) {
					console.log("loginRes")
					console.log(loginRes);
					// 获取用户信息
					uni.getUserInfo({
						provider: 'weixin',
						success: function(infoRes) {
							console.log(infoRes);
							console.log('用户昵称为：' + infoRes.userInfo.nickName);
						}
					});
				}
			});
			this.randTen();
		},
		methods: {
			donghua() {
				var animation = uni.createAnimation({
					duration: 1000,
					timingFunction: 'ease',
				})
				this.animation = animation
				animation.opacity(0).translate(-300).step()
				this.animationData = animation.export()
				console.log("1000")
				setTimeout(function() {
					animation.translate(10).opacity(0.5).step({
						duration: 0
					})
					this.animationData = animation.export()
				}.bind(this), 400);
				setTimeout(function() {
					animation.translate(0).opacity(1).step({
						duration: 800
					})
					this.animationData = animation.export()
					console.log("1000")
				}.bind(this), 500);
				console.log("动画呢")
			},

			randTen() {
				console.log("初始化")
				if (this.questionBanks.length > 0 && this.current < 9) {
					return false;
				}
				this.current = 0;
				this.score = 0;
				this.controlShow = true;
				this.questionBanks = [];
				uni.request({
					url: this.serverUrl + '/qb/randOne?num=10', //仅为示例，并非真实接口地址。
					success: (res) => {
						console.log(res)
						console.log(res.data.data);
						this.questionBanks = res.data.data;
					}
				});
			},
			switchTabToClassify(index) {
				if (this.current == 9) this.controlShow = false;
				console.log(index)
				console.log(this.questionBanks[this.current])
				this.questionBanks[this.current]['selectedType'] = index;

				console.log("当前的需要：" + this.current)
				if (this.questionBanks[this.current].garbageType == index) {
					this.donghua();
					let me = this;
					setTimeout(function() {
						me.score++;
						me.current++;
						me.gotoNav();
					}, 400);

				} else {
					let obj = this.questionBanks[this.current];
					let type = obj.garbageType == 1 ? '干垃圾' : obj.garbageType == 2 ? '湿垃圾' : obj.garbageType == 3 ? '可回收物' : '有害垃圾';
					let temp = obj.garbageName + " 属于 " + type;
					let me = this;
					uni.showModal({
						title: "选错啦",
						content: temp,
						confirmText: "知道了",
						showCancel: false,
						success: function(res) {
							console.log(res);
							console.log("点击了确认:me.current:" + me.current);
							me.donghua();
							setTimeout(function() {
								me.current++;
								me.gotoNav();
							}, 400);
						}
					})
				}
			},
			gotoNav() {
				if (this.current >= 10) {
					console.log("要进行跳转拉")
					let list = JSON.stringify(this.questionBanks);
					let score = this.score;
					uni.request({
						url: this.serverUrl + '/challenge', //仅为示例，并非真实接口地址。
						data: {
							"score": score,
							"userName": "张三",
							"list": this.questionBanks
						},
						header: {
							'content-type': "application/json"
						},
						method: "POST",
						success: (res) => {
							console.log(res)
							console.log(res.data.data);
						}
					});
					uni.navigateTo({
						url: `/pages/challenge/challengeResult?score=${score}&list=${list}`
					});
				}
			}
		}
	}
</script>

<style>
	@import url("challenge.css");
</style>
