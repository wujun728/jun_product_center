<script>
	export default {
		onLaunch: function() {},
		onShow: function() {},
		onHide: function() {}
	};
</script>

<style lang="scss">
	@import "uview-ui/index.scss";
	@import './common/uni.css';

	// @font-face {
	// 	font-family: siyuan;
	// 	src: url('~@/static/_思源黑体SourceHanSansCN-Heavy.otf');
	// }

	/* 解决头条小程序组件内引入字体不生效的问题 */
	/* #ifdef MP-TOUTIAO */
	@font-face {
		font-family: uniicons;
		src: url('/static/uni.ttf');
	}

	/* #endif */
	// .container{
	// 	font-family: siyuan;
	// }
</style>
