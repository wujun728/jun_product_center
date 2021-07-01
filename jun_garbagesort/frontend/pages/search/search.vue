<template>
	<view class="content">
		<view class="form-view">
			<form @submit="formSubmit" @reset="formReset" class="form-form">
				<view class="input-view">
					<view @click="takePhoto" class="input-view-item input-view-camera">
						<image class="search-img" src="../../static/icos/camera.png"></image>
					</view>
					<view @click="readyRecord" class="input-view-item input-view-speech">
						<image class="search-img" src="../../static/icos/record.png"></image>
					</view>
					<view class="input-view-item input-view-search">
						<input confirm-type="search" :placeholder="defaultKeyword" @search="doSearch(false)" @input="inputChange"
						 @confirm="doSearch(false)" v-model="keyword" class="input-search" name="input" placeholder="输入搜索关键词" />
					</view>
					<view class=" font-search" @click="doSearch(false)">查询</view>
				</view>
			</form>
		</view>
		<view class="search-keyword" @touchstart="blur">
			<scroll-view class="keyword-list-box" v-show="isShowKeywordList" scroll-y>
				<view class="keyword-entry" @tap="showDetail(row)" hover-class="keyword-entry-tap" v-for="row in keywordList" :key="row.keyword">
					<view class="keyword-text" @tap="doSearch(row.keyword)">
						<rich-text :nodes="row.htmlStr"></rich-text>
					</view>
					<view class="">
						<view v-if="row.garbageType==1" class="keyword-type garbage-gan">干垃圾</view>
						<view v-else-if="row.garbageType==2" class="keyword-type garbage-shi">湿垃圾</view>
						<view v-else-if="row.garbageType==3" class="keyword-type garbage-huishou">可回收物</view>
						<view v-else-if="row.garbageType==4" class="keyword-type garbage-youhai">有害垃圾</view>
					</view>
					<view class="keyword-img" @tap="setkeyword(row)">
						<image src="/static/HM-search/back.png"></image>
					</view>
				</view>
				<view>
					<ad unit-id="adunit-060249bea9401e5c"></ad>
				</view>
			</scroll-view>
			<scroll-view class="keyword-box" v-show="!isShowKeywordList" scroll-y>
				<view class="keyword-block" v-if="oldKeywordList.length>0">
					<view class="keyword-list-header">
						<view>历史搜索</view>
						<view>
							<image @tap="oldDelete" src="/static/HM-search/delete.png"></image>
						</view>
					</view>
					<view class="keyword">
						<view v-for="(keyword,index) in oldKeywordList" @tap="doSearch(keyword)" :key="index">{{keyword}}</view>
					</view>
				</view>
				<view class="keyword-block">
					<view class="keyword-list-header">
						<view>热门搜索</view>
						<view>
							<image @tap="hotToggle" :src="'/static/HM-search/attention'+forbid+'.png'"></image>
						</view>
					</view>
					<view class="keyword" v-if="forbid==''">
						<view v-for="(keyword,index) in hotKeywordList" @tap="doSearch(keyword)" :key="index">
							<view class="" v-if="index<3">
								<image class="image-hot" src="../../static/icos/hot.png" mode=""></image>
							</view>
							<view class="">{{keyword}}
							</view>

						</view>
					</view>
					<view class="hide-hot-tis" v-else>
						<view>当前搜热门搜索已隐藏</view>
					</view>
				</view>
				<view>
					<ad unit-id="adunit-060249bea9401e5c"></ad>
				</view>
				<!-- <view  >
					<ad unit-id="adunit-19c9c625b0b4a546" ad-type="video" ad-theme="white"></ad>
				</view> -->

			</scroll-view>
		</view>
		<!-- popup start -->
		<view class="">
			<uni-popup :show="popupShow" position="bottom" @hidePopup="hidePopup">
				<view class="view-popup">
					<view class="recording-title">按住 说话</view>
					<!-- 此处可放置倒计时，可根据需要自行添加 -->
					<!-- <div class="recording-time"> 
			{{seconds}} : {{ms}}
		</div> -->
					<view class="recording-box">
						<canvas id="canvas" canvas-id="canvas">
							<view :animation="animationData" class="recording-button" @touchstart="start" @touchend="end"></view>
						</canvas>
					</view>
					<!-- <button type="primary" @click="playVoice">播放</button> -->
				</view>
			</uni-popup>
		</view>
		<!-- popup end -->
		<view class="">
			<my-popup :show="detailPopupShow" :detail="detailShowObject" @hideMypopup="hideMypopup"></my-popup>
		</view>
		<uni-popup :show="imagesResultShow" position="middle" mode="fixed" @hidePopup="hideUnipPpup">
			<view class="imagePopup">
				<view class="image-left">
					<image class="tempImage" :src="imagePath"></image>
				</view>
				<view class="image-right">
					<view class="image-close">
						<view class="">无法精准识别,请选择最接近的</view>
					</view>
					<view class="">
						<view v-for="(item, index) in imageResults" :key="index" class="list-item" @tap="tapKeyword(item.keyword)">
							<view class="item-keyword"> {{item.keyword}}</view>
							<view class="item-pi">
								<view class="">匹配度</view>
								<view class=""> {{Math.floor(item.score*100)}}% > </view>
							</view>
						</view>
					</view>
				</view>
			</view>
			<view class="">
				<button class="image-button" type="primary" @tap="hideUnipPpup">返回</button>
			</view>

		</uni-popup>
		<share />
	</view>
</template>

<script>
	//引用mSearch组件，如不需要删除即可
	import uniPopup from "@/components/uni-popup/uni-popup.vue"
	import myPopup from "@/components/myPopup.vue"
	import share from "@/components/share.vue"


	const recorderManager = uni.getRecorderManager();
	const innerAudioContext = uni.createInnerAudioContext();

	innerAudioContext.autoplay = true;

	export default {
		components: {
			//引用mSearch组件，如不需要删除即可
			uniPopup,
			myPopup,
			share
		},
		data() {
			return {
				defaultKeyword: "",
				keyword: "",
				oldKeywordList: [],
				hotKeywordList: [],
				keywordList: [],
				forbid: '',
				isShowKeywordList: false,
				imagePath: "",

				// 录音相关的 值	start
				popupShow: false,
				voicePath: '',
				startTiming: false,
				drawTiming: false,
				timeoutTiming: false,
				animaTiming1: false,
				animaTiming2: false,
				animationData: {},
				maxTime: 5000,
				frame: 50,

				// 录音相关的 值	end

				// image 相关的值 start
				possible: [],
				reference: null,
				results: [],
				// imageResults: [],
				imageResults: [{
					"score": 0.944264,
					"root": "商品-箱包",
					"keyword": "手提袋"
				}, {
					"score": 0.799999,
					"root": "Logo",
					"keyword": "85度c"
				}, {
					"score": 0.589144,
					"root": "商品-箱包",
					"keyword": "购物袋"
				}, {
					"score": 0.227161,
					"root": "商品-箱包",
					"keyword": "塑料购物袋"
				}, {
					"score": 0.018175,
					"root": "商品-箱包",
					"keyword": "宣传袋"
				}],

				imagesResultShow: false,
				replyWord: [
					"很抱歉,识别精灵未能匹配到,将会尽快完善 ᕙ(⇀‸↼‵‵)ᕗ",
					"未能匹配到结果,识别精灵会继续学习的, 试试其他的吧(๑°⌓°๑)",
					"识别精灵成长中，正在学习该垃圾的分类",
					"抱歉，识别精灵未能给您提供合适的结果",
					"请尝试换一个搜索关键词试试看吧",
					"很抱歉,识别精灵未能匹配到,将会尽快完善 ᕙ(⇀‸↼‵‵)ᕗ",
				],

				// image 相关的值 end
				detailPopupShow: false,
				detailShowObject: null,

			}
		},
		// onShareAppMessage() {
		// 	return {
		// 		title: "这是搜索页的分享",
		// 		path: '/pages/index/index',
		// 		imageUrl: this.image ? this.image : 'https://img-cdn-qiniu.dcloud.net.cn/uniapp/app/share-logo@3.png'
		// 	}
		// },

		onLoad(option) {
			let me = this;
			if (option.type == 1) {
				me.takePhoto();
			} else if (option.type == 2) {
				me.readyRecord();
			} else if (option.type == 3) {

				console.log(" search-------")
				console.log(option)
				if (option.keyword) {
					me.keyword = option.keyword

					if (option.uni != "null") {
						let uniOne = JSON.parse(option.uni);

						me.detailPopupShow = true;
						me.detailShowObject = {
							keyword: uniOne.garbageName,
							garbageType: uniOne.garbageType,
							remark: uniOne.remark,
						}
						me.saveKeyword(uniOne.garbageName);
					}
					console.log(option.keywordList)
					console.log(option.keywordList == "[]")
					if (option.keywordList != "[]") {
						let list = JSON.parse(option.keywordList);
						me.isShowKeywordList = true;
						me.keywordList = me.drawCorrelativeKeyword(JSON.parse(option.keywordList), me.keyword)
					}
					console.log(me.isShowKeywordList)
					console.log(me.keywordList)

					console.log(me.detailPopupShow)
					if (me.keywordList == 0 && !me.detailPopupShow) {
						me.noTitlemodalTap();
					}

				}
			}

			me.init();
			// 录音过程圆圈动画

			console.log(1);
			// 录音结束的事件监听
			recorderManager.onStop(function(res) {
				uni.showLoading({
					title: '正在努力识别中...'
				});
				console.log('recorder stop' + JSON.stringify(res));
				me.voicePath = res.tempFilePath;
				console.log(me.voicePath)
				me.popupShow = false;

				// if (me.voicePath) {
				// 	innerAudioContext.src = me.voicePath;
				// 	innerAudioContext.play();
				// 	console.log("播放了声音")
				// }

				// canvas 画图
				// let angle = -0.5;
				// let context = uni.createCanvasContext('canvas');
				// me.draw = setTimeout(function() {
				// 	context.beginPath();
				// 	context.setStrokeStyle("#1296db");
				// 	context.setLineWidth(3);
				// 	context.arc(0, 0, 0, -0.5 * Math.PI, (angle += 2 / (me.maxTime / me.frame)) * Math.PI, false);
				// 	context.stroke();
				// 	context.draw();
				// }, 1);
				uni.uploadFile({
					url: me.serverUrl + '/upload/record', //仅为示例，非真实的接口地址
					filePath: me.voicePath,
					name: 'file',
					success: (uploadFileRes) => {
						console.log(uploadFileRes.data)
						let res = JSON.parse(uploadFileRes.data);
						let data = res.data;
						let uniOne = data.uni;
						me.keyword = data.keyword;
						me.isShowKeywordList = true;
						me.keywordList = me.drawCorrelativeKeyword(data.results, me.keyword);
						if (uniOne) {
							me.detailPopupShow = true;
							me.detailShowObject = {
								keyword: uniOne.garbageName,
								garbageType: uniOne.garbageType,
								remark: uniOne.remark,
							}
						}
						if (me.keywordList == 0 && !me.detailPopupShow) {
							me.noTitlemodalTap();
						}
					},
					complete() {
						uni.hideLoading();
					}
				});
			})

		},

		methods: {
			tapKeyword(keyword) {
				this.keyword = keyword;
				this.doSearch();
				this.imagesResultShow = false;
			},
			noTitlemodalTap() {
				uni.showModal({
					title: "未能匹配成功",
					content: this.replyWord[Math.round(Math.random() * 5)],
					confirmText: "知道了",
					showCancel: false,
					success: function(res) {
						if (res.confirm) {
							console.log('用户点击确定');
						} else if (res.cancel) {
							console.log('用户点击取消');
						}
					}
				})
			},
			//  自己写的方法 start
			showDetail(row) {
				this.detailShowObject = row;
				this.detailPopupShow = true;
			},
			hideMypopup() {
				this.detailPopupShow = false;
			},
			hideUnipPpup() {
				this.imagesResultShow = false;
			},
			readyRecord() {
				this.popupShow = true;
			},
			start() {
				// console.log("startTiming");
				this.recording();
				// this.startTiming = setTimeout(this.recording, 150);
			},
			end() {
				// console.log("录音 end")
				recorderManager.stop();
				clearInterval(this.draw);
				clearTimeout(this.startTiming);
				clearTimeout(this.timeoutTiming);
				clearInterval(this.animaTiming1);
				clearInterval(this.animaTiming2);
				this.draw = false;
				this.startTiming = false;
				this.timeoutTiming = false;
				this.animaTiming1 = false;
				this.animaTiming2 = false;
				var animation = uni.createAnimation({
					duration: 500,
					timingFunction: 'ease',
				})
				this.animation = animation
				animation.scale(1, 1).step()
				this.animationData = animation.export()
			},
			recording() {
				let me = this;
				// console.log('录音开始');
				recorderManager.start();
				// 画图
				// let angle = -0.5;
				// let context = uni.createCanvasContext('canvas');
				// me.draw = setInterval(function() {
				// 	context.beginPath();
				// 	context.setStrokeStyle("#1296db");
				// 	context.setLineWidth(3);
				// 	context.arc(75, 75, 50, -0.5 * Math.PI, (angle += 2 / (me.maxTime / me.frame)) * Math.PI, false);
				// 	context.stroke();
				// 	context.draw();
				// }, me.frame);
				me.animaTiming1 = setInterval(function() {
					var animation = uni.createAnimation({
						duration: 500,
						timingFunction: 'ease',
					})

					me.animation = animation

					animation.scale(1.3, 1.3).step()

					me.animationData = animation.export()

				}, 500)
				me.animaTiming2 = setInterval(function() {
					var animation = uni.createAnimation({
						duration: 500,
						timingFunction: 'ease',
					})

					me.animation = animation

					animation.scale(1, 1).step()

					me.animationData = animation.export()

				}, 1000)
				me.timeoutTiming = setTimeout(() => {
					clearInterval(me.draw);
					me.draw = false;
					me.animaTiming1 = false;
					me.animaTiming2 = false;
					// console.log("时间到，录音结束");
					recorderManager.stop();
				}, me.maxTime);
			},
			playVoice() {
				let me = this;
				// console.log(" 点击 播放")
				// console.log(me.voicePath)
				if (me.voicePath) {
					innerAudioContext.src = me.voicePath;
					innerAudioContext.play();
					// console.log("播放了声音")
				}
			},
			hidePopup() {
				this.popupShow = false;
			},
			takePhoto() {
				let me = this;
				uni.chooseImage({
					count: 1,
					success: res => {
						uni.showLoading({
							title: '正在努力识别中...'
						});
						me.imagePath = res.tempFilePaths[0];
						console.log(me.imagePath)
						uni.uploadFile({
							url: me.serverUrl + '/upload/image', //仅为示例，非真实的接口地址
							filePath: res.tempFilePaths[0],
							name: 'file',
							success: (uploadFileRes) => {
								console.log(uploadFileRes.data)
								let res = JSON.parse(uploadFileRes.data);
								let data = res.data;
								let response = JSON.parse(data.response);
								console.log(response.result);
								let uniOne = data.uni;
								me.keyword = data.keyword;

								me.keywordList = me.drawCorrelativeKeyword(data.results, me.keyword);
								if (uniOne) { // 查找到的唯一值不等于空
									me.isShowKeywordList = true;
									me.detailPopupShow = true;

									me.detailShowObject = {
										keyword: uniOne.garbageName,
										garbageType: uniOne.garbageType,
										remark: uniOne.remark,
									}
								} else if (me.keywordList.length > 0) {
									me.isShowKeywordList = true;

								} else {
									me.imageResults = response.result;
									me.imagesResultShow = true;
								}


								// if (me.keywordList == 0 && !me.detailPopupShow) {
								// 	me.noTitlemodalTap();
								// }

							},
							complete() {
								uni.hideLoading();
							}
						});
					}
				});
			},

			// 自己写的方法 end

			init() {
				this.loadDefaultKeyword();
				this.loadOldKeyword();
				this.loadHotKeyword();

			},
			blur() {
				uni.hideKeyboard()
			},
			//加载默认搜索关键字
			loadDefaultKeyword() {
				//定义默认搜索关键字，可以自己实现ajax请求数据再赋值,用户未输入时，以水印方式显示在输入框，直接不输入内容搜索会搜索默认关键字
				this.defaultKeyword = "苹果";
			},
			//加载历史搜索,自动读取本地Storage
			loadOldKeyword() {
				uni.getStorage({
					key: 'OldKeys',
					success: (res) => {
						var OldKeys = JSON.parse(res.data);
						this.oldKeywordList = OldKeys;
					}
				});
			},
			//加载热门搜索
			loadHotKeyword() {
				let me = this;
				me.hotKeywordList = ['键盘', '鼠标', '显示器', '电脑主机', '蓝牙音箱', '笔记本电脑', '鼠标垫', 'USB', 'USB3.0'];
				uni.request({
					url: me.serverUrl + "/qb/top10",
					success(res) {
						me.hotKeywordList = res.data.data.map(item => item.keyword)
					}
				})
				//定义热门搜索关键字，可以自己实现ajax请求数据再赋值
			},
			//监听输入 苹
			inputChange() {
				// var keyword = event.detail ? event.detail.value : event;
				var keyword = this.keyword;

				console.log("keyword:" + keyword)
				//兼容引入组件时传入参数情况


				setTimeout(() => {
					console.log("results:" + keyword)
					if (!keyword) {
						this.keywordList = [];
						this.isShowKeywordList = false;
						return;
					}
					this.isShowKeywordList = true;
					//以下示例截取淘宝的关键字，请替换成你的接口
					uni.request({
						url: this.serverUrl + "/qb/name/" + keyword, //仅为示例
						success: (res) => {
							console.log(res);
							this.keywordList = this.drawCorrelativeKeyword(res.data.data, keyword);
						}
					});
				}, 300)

			},
			//高亮关键字
			drawCorrelativeKeyword(keywords, keyword) {
				var len = keywords.length,
					keywordArr = [];
				for (var i = 0; i < len; i++) {
					var row = keywords[i];
					//定义高亮#9f9f9f
					var html = row.garbageName.replace(keyword, "<span style='color: #72c69c;'>" + keyword + "</span>");
					html = '<div>' + html + '</div>';
					var tmpObj = {
						keyword: row.garbageName,
						htmlStr: html,
						garbageType: row.garbageType,
						remark: row.remark,
					};
					keywordArr.push(tmpObj)
				}
				return keywordArr;
			},
			//顶置关键字
			setkeyword(data) {
				this.keyword = data.keyword;
			},
			//清除历史搜索
			oldDelete() {
				uni.showModal({
					content: '确定清除历史搜索记录？',
					success: (res) => {
						if (res.confirm) {
							console.log('用户点击确定');
							this.oldKeywordList = [];
							uni.removeStorage({
								key: 'OldKeys'
							});
						} else if (res.cancel) {
							console.log('用户点击取消');
						}
					}
				});
			},
			//热门搜索开关
			hotToggle() {
				this.forbid = this.forbid ? '' : '_forbid';
			},
			//执行搜索
			doSearch(key) {
				uni.showLoading({
					title: '正在努力查询中...'
				});
				key = key ? key : this.keyword ? this.keyword : this.defaultKeyword;
				this.keyword = key;
				this.saveKeyword(key); //保存为历史 
				// uni.showToast({
				// 	title: key,
				// 	icon: 'none',
				// 	duration: 2000
				// });
				// this.inputChange();
				uni.request({
					url: this.serverUrl + "/qb/uniname/" + this.keyword, //仅为示例
					success: (res) => {
						console.log(res);
						let data = res.data.data;
						let uniOne = data.uni;
						let results = data.results;
						if (uniOne) {
							this.detailPopupShow = true;
							this.detailShowObject = {
								keyword: uniOne.garbageName,
								garbageType: uniOne.garbageType,
								remark: uniOne.remark,
							}
						}
						if (results.length > 0) {
							this.keywordList = this.drawCorrelativeKeyword(results, this.keyword);
						}
						if (this.keywordList == 0 && !this.detailPopupShow) {
							this.noTitlemodalTap();
						}
						// this.keywordList = this.drawCorrelativeKeyword(res.data.data, keyword);
					},
					complete() {
						uni.hideLoading();
					}

				});

			},
			//保存关键字到历史记录
			saveKeyword(keyword) {
				uni.getStorage({
					key: 'OldKeys',
					success: (res) => {
						console.log(res.data);
						var OldKeys = JSON.parse(res.data);
						var findIndex = OldKeys.indexOf(keyword);
						if (findIndex == -1) {
							OldKeys.unshift(keyword);
						} else {
							OldKeys.splice(findIndex, 1);
							OldKeys.unshift(keyword);
						}
						//最多10个纪录
						OldKeys.length > 10 && OldKeys.pop();
						uni.setStorage({
							key: 'OldKeys',
							data: JSON.stringify(OldKeys)
						});
						this.oldKeywordList = OldKeys; //更新历史搜索
					},
					fail: (e) => {
						var OldKeys = [keyword];
						uni.setStorage({
							key: 'OldKeys',
							data: JSON.stringify(OldKeys)
						});
						this.oldKeywordList = OldKeys; //更新历史搜索
					}
				});
			}
		}
	}
</script>
<style>
	@import url("search.css");
</style>
