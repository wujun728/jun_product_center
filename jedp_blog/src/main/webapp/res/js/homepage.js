/* 首页js
 * @Author: Leo 
 * @Date: 2019-10-09 11:35:45 
 * @Last Modified by:   Leo 
 * @Last Modified time: 2019-10-09 11:35:45 
 */
layui.config({
    base: '/res/js/'
}).use('unslider', function () {
    var $ = window.jQuery = layui.jquery
        , slider = $('.banner').unslider({
            autoplay: true  //是否自动轮播，默认：false
            , speed: 800               //  播放速度，单位毫秒 默认750
            , delay: 3500              //  每张图片滑动的间隔时间，单位毫秒 默认3000
            , index: 'first'    //起始下标，整数 或者 'first'、'last' 默认first
            , keys: true    //是否开启键盘控制，true，false，或者一个option/keycode对象。 默认ture
            //, keys: {
            //    prev: 37,   //←
            //    next: 39,   //→
            //    stop: 27    //ESC
            //}
            //, arrows: true //箭头 true，false，或者一个option/keycode对象。 默认ture
            , arrows: {
                prev: '<a class="unslider-arrow prev"><i class="fa fa-chevron-left" aria-hidden="true"></i></a>'
                , next: '<a class="unslider-arrow next"><i class="fa fa-chevron-right" aria-hidden="true"></i></a>'
//                ,stop: '<a class="unslider-pause" />'
//                ,start: '<a class="unslider-play">Play</a>'
            }
            , animation: 'horizontal'  //动画方式 'horizontal','vertical','fade' 默认：'horizontal'
            , selectors: {
                container: 'ul:first',
                slides: 'li'
            }
            , animateHegiht: true
            , activeClass: 'unslider-active'
            , infinite: true
            , noloop: false
        })
        , sliderInvoke = slider.data('unslider');
    $('.banner').css('width', '');
    //鼠标悬浮显示指针，停止轮播
    $('.banner,.unslider-arrow').on('mouseover', function (e) {
        layui.stope(e);
        $('.unslider-arrow').show();
        sliderInvoke.stop();
    }).on('mouseout', function (e) {
        layui.stope(e);
        $('.unslider-arrow').hide();
        sliderInvoke.start();
    });

    //鼠标按住轮播手动控制滑动
    //静态页面存在跨域问题，请部署到服务器使用
    var scripts = [
        '/res/lib/jquery.event.move.js',
        '/res/lib/jquery.event.swipe.js'
    ];
    $.getScript(scripts[0]);
    $.getScript(scripts[1], function () {
        sliderInvoke.initSwipe();
    });
});

layui.use(['jquery', 'util'], function (carousel) {
    var $ = layui.jquery
        , util = layui.util
        , device = layui.device();

    var serverTime = new Date();
    var startTime = new Date(2018, 2, 3);  //开始时间  js月份从0开始;

    $(function () {
        playAnnouncement();
        playRunTimeStr(startTime, serverTime, '.bloginfo-runtime');
    });

    function showRunTime(startTime, serverTime, selecter) {
        var date3 = serverTime.getTime() - startTime.getTime()  //时间差的毫秒数

        //计算出相差天数
        var days = Math.floor(date3 / (24 * 3600 * 1000))

        //计算出小时数

        var leave1 = date3 % (24 * 3600 * 1000)    //计算天数后剩余的毫秒数
        var hours = Math.floor(leave1 / (3600 * 1000))
        //计算相差分钟数
        var leave2 = leave1 % (3600 * 1000)        //计算小时数后剩余的毫秒数
        var minutes = Math.floor(leave2 / (60 * 1000))
        //计算相差秒数
        var leave3 = leave2 % (60 * 1000)      //计算分钟数后剩余的毫秒数
        var seconds = Math.round(leave3 / 1000);

        var str = '博客已运行<span style="margin-left:4px;">' + util.digit(days, 2) + '天' + util.digit(hours, 2) + '时' + util.digit(minutes, 2) + '分' + util.digit(seconds, 2) + '秒</span>';
        $(selecter).html(str);
    }

    function playRunTimeStr(startTime, serverTime, selecter) {
        showRunTime(startTime, serverTime, selecter);
        setInterval(function () {
            showRunTime(startTime, serverTime, selecter);
            serverTime = new Date(serverTime.getTime() + 1000 * 1);
        }, 1000);
    }

    //播放公告
    function playAnnouncement() {
        var index = 0;
        var $announcement = $('.home-tips-container>span');
        //自动轮换
        setInterval(function () {
            index++;    //下标更新
            if (index >= $announcement.length) {
                index = 0;
            }
            $announcement.eq(index).stop(true, true).fadeIn().siblings('span').fadeOut();  //下标对应的图片显示，同辈元素隐藏
        }, 3000);
    }
});