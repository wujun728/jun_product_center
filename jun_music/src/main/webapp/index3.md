# zplayer

#### 项目介绍
史上最精简的音乐播放器！300多行js！你想要的功能zplayer都有！请加Q群130512958交流技术。zplayer还支持初始化多个播放器。如果使用有建议或者其他问题，可以加群交流。

喜欢的留步帮忙star一下谢谢~

#### 版权说明
本产品最终解释权归智博客所有，任何人不得引任何形式进行盗窃并公开传播等侵权行为，盗版必究。

#### 使用说明
1. down下来本项目后，在页面内引入 css/zplayer.min.css和js/zplayer.min.js
2. 使用方法以及参数说明<br/>（autoplay，showlrc，fixed，listFolded，listMaxHeight五个参数可以不传，则走默认值,具体见以下代码示例）。

```
<div id="player"></div>
<script>
    var zp = new zplayer({
        element: document.getElementById("player"),
	autoPlay: false,/*是否开启自动播放,默认false*/
	lrcStart: true,/*是否开启歌词功能 ，默认false（为true时musics集合中需要传入lrc字段。）*/
	showLrc: true,/*开启歌词功能后是否立即展示歌词内容 ，默认false*/
	fixed: true, /*是否固定在底部，默认false*/
	listFolded: true, /*列表默认折叠，默认false*/
	listMaxHeight: 300, /*列表最大高度，默认240*/
	musics: [{
	    title: "可能否",
	    author: "木小雅",
	    url: "http://music.163.com/song/media/outer/url?id=569214126.mp3",
	    pic: "http://p1.music.126.net/SJYnDay7wgewU3O7tPfmOQ==/109951163322541581.jpg?param=300x300",
	    lrc:"[00:00] 作曲 : 木小雅\n[00:01] 作词 : 木小雅\n[00:17]春天的风 能否吹来夏天的雨"
	},{
	    title: "讲真的",
	    author: "曾惜",
	    url: "http://music.163.com/song/media/outer/url?id=30987293.mp3",
	    pic: "http://p1.music.126.net/cd9tDyVMq7zzYFbkr0gZcw==/2885118513459477.jpg?param=300x300",
	    lrc:"[by:却连一句我爱你都不能说出口]\n[ti:讲真的]\n[ar:曾惜]\n[al:不要你为难]\n[by:冰城离殇]\n[00:00] 作曲 : 何诗蒙\n[00:01] 作词 : 黄然\n[00:18]今夜特别漫长"        

```



#### 其他开源项目
客官留步：<br>
[zb-shiro](https://gitee.com/supperzh/zb-shiro)：https://gitee.com/supperzh/zb-shiro<br/>
非常精简的springboot+mybatis+redis+shiro+thymeleaf权限后台项目。花一分钟时间进来了解一下~
<br/>
<br/>
<br/>


#### 播放器预览图
1. 默认效果<br/>
![默认效果](https://gitee.com/supperzh/zplayer/raw/master/img/default.png?v=1.0)

2.歌词+不固定效果<br/>
![歌词不固定效果](https://gitee.com/supperzh/zplayer/raw/master/img/lrc.png?v=1.0)

3. 固定底部（折叠）效果<br/>
![固定效果](https://gitee.com/supperzh/zplayer/raw/master/img/fixed.png?v=1.0)

<br/>
<br/>
<br/>

		        
#### 参与贡献

#### 参与贡献

1. Fork 本项目
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request


