<template>
	<view class="app">
		<view class="price-box">
			<text>支付金额</text>
			<text class="price">{{orderInfo.price}}</text>
		</view>

		<view class="pay-type-list"> 
			<view class="type-item b-b" @click="changePayType(index)" v-for="(item, index) in payments" :key="item.type">
				<text class="icon yticon icon-weixinzhifu" v-if="item.type=='wx'||item.type=='wechat'"></text>
				<text class="icon yticon icon-alipay" v-if="item.type=='alipay'"></text>
				<text class="icon yticon icon-erjiye-yucunkuan" v-if="item.type=='yue'"></text>
				<view class="con">
					<text class="tit">{{item.name}}</text>
					<text>{{item.remark}}</text>
					<text v-if="item.type=='yue'">可用余额 ¥{{account.account}}</text>
				</view>
				<label class="radio" v-if="!(item.type=='yue'&&account.account<orderInfo.price)">
					<radio value="" color="#fa436a" :checked='payType == item' />
					</radio>
				</label>
				<label class="radio" v-if="item.type=='yue'&&account.account<orderInfo.price">
					余额不足
				</label>
			</view>
			<!-- <view class="type-item b-b" @click="changePayType(2)">
				<text class="icon yticon icon-alipay"></text>
				<view class="con">
					<text class="tit">支付宝支付</text>
				</view>
				<label class="radio">
					<radio value="" color="#fa436a" :checked='payType == 2' />
					</radio>
				</label>
			</view>
			<view class="type-item" @click="changePayType(3)">
				<text class="icon yticon icon-erjiye-yucunkuan"></text>
				<view class="con">
					<text class="tit">预存款支付</text>
					<text>可用余额 ¥198.5</text>
				</view>
				<label class="radio">
					<radio value="" color="#fa436a" :checked='payType == 3' />
					</radio>
				</label>
			</view> -->
		</view>
		
		<text class="mix-btn" @click="confirm">确认支付</text>
	</view>
</template>

<script>

	export default {
		data() {
			return {
				payType: {},
				orderInfo: {},
				payments: [],
				account:{}
			};
		},
		computed: {
		
		},
		onLoad(option) {
 			if(option.orderNo){
				let platform='';
				// #ifdef  H5
					platform='pc';
				// #endif 
				// #ifdef  APP-PLUS
					platform='app';
				// #endif 
				// #ifdef  MP-WEIXIN
					platform='wx';
				// #endif 
				let _this=this;
				this.sendRequest({
					url : "get_payments",
					data : {orderNo:option.orderNo,platform:platform},
					method : "get",
					success:function (res) {
						if(res.code=='200'){
							_this.orderInfo=res.data.order;
							_this.payments=res.data.payments;
							_this.account=res.data.account;
							if(_this.account.account>=_this.orderInfo.price){
								this.payType='yue';//余额支付
							}
						}else{
							_this.$api.msg(res.message);
						}
					}
				});
			}
		},

		methods: {
			//选择支付方式
			changePayType(index) {
				this.payType = this.payments[index];
			},
			//确认支付
			confirm: async function() {
				if(!this.payType||!this.payType.type){
					_this.$api.msg('请选择支付方式');
				}
				this.orderInfo.payType=this.payType.type;
				this.orderInfo.platform=this.payType.platform;
				let _this=this;
				_this.sendRequest({
					url : 'pay_order',
					data : this.orderInfo,
					method : "post",
					success:function (res) {
						if(res.code=='200'){
 							if(res.data.paybill.pay_type=='yue'){
								_this.$api.msg(res.message);
							}else if(res.data.paybill.pay_type=='wx'){
								uni.requestPayment({
								    provider: 'wxpay', // wxpay、alipay
								    orderInfo: res.data.data, //微信、支付宝订单数据
								    success: function (res) {
								        console.log('success:' + JSON.stringify(res));
										uni.redirectTo({
											url: '/pages/money/paySuccess'
										})
								    },
								    fail: function (err) {
								        console.log('fail:' + JSON.stringify(err));
								    }
								});
							}else if(res.data.paybill.pay_type=='alipay'){
								// #ifdef  H5
 								   const div=document.createElement('divform');
								   div.innerHTML=res.data.data;
								   document.body.appendChild(div);
								   // document.forms[0].acceptCharset='GBK';//保持与支付宝默认编码格式一致，如果不一致将会出现：调试错误，请回到请求来源地，重新发起请求，错误代码 invalid-signature 错误原因: 验签出错，建议检查签名字符串或签名私钥与应用公钥是否匹配
				
								   document.forms[0].submit();
								// #endif 
								// #ifdef  APP-PLUS
									uni.requestPayment({
										provider: 'alipay',
										orderInfo: res.data.data, 
										success: function (res) { 
											console.log('success:' + JSON.stringify(res));
											uni.redirectTo({
												url: '/pages/money/paySuccess'
											})
										},
										fail: function (err) {
											console.log('fail:' + JSON.stringify(err));
										}
									});
								// #endif 
								// #ifdef  MP-WEIXIN
									 locahost.href=res.data.data;
								// #endif 
							} 
						}else{
							_this.$api.msg(res.message);
						}
					}
				});  
			},
		}
	}
</script>

<style lang='scss'>
	.app {
		width: 100%;
	}

	.price-box {
		background-color: #fff;
		height: 265upx;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		font-size: 28upx;
		color: #909399;

		.price{
			font-size: 50upx;
			color: #303133;
			margin-top: 12upx;
			&:before{
				content: '￥';
				font-size: 40upx;
			}
		}
	}

	.pay-type-list {
		margin-top: 20upx;
		background-color: #fff;
		padding-left: 60upx;
		
		.type-item{
			height: 120upx;
			padding: 20upx 0;
			display: flex;
			justify-content: space-between;
			align-items: center;
			padding-right: 60upx;
			font-size: 30upx;
			position:relative;
		}
		
		.icon{
			width: 100upx;
			font-size: 52upx;
		}
		.icon-erjiye-yucunkuan {
			color: #fe8e2e;
		}
		.icon-weixinzhifu {
			color: #36cb59;
		}
		.icon-alipay {
			color: #01aaef;
		}
		.tit{
			font-size: $font-lg;
			color: $font-color-dark;
			margin-bottom: 4upx;
		}
		.con{
			flex: 1;
			display: flex;
			flex-direction: column;
			font-size: $font-sm;
			color: $font-color-light;
		}
	}
	.mix-btn {
		display: flex;
		align-items: center;
		justify-content: center;
		width: 630upx;
		height: 80upx;
		margin: 80upx auto 30upx;
		font-size: $font-lg;
		color: #fff;
		background-color: $base-color;
		border-radius: 10upx;
		box-shadow: 1px 2px 5px rgba(219, 63, 96, 0.4);
	}

</style>
