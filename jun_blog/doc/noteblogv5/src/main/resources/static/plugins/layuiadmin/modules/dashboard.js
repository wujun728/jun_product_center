/** Created By Wuwenbin https://wuwenbin.me
 * mail to wuwenbinwork@163.com
 * 欢迎加入我们，QQ群：697053454
 * if you use the code,  please do not delete the comment
 * 如果您使用了此代码，请勿删除此头部注释
 * */
layui.define(function (exports) {

    //区块轮播切换
    layui.use(['admin', 'carousel'], function () {
        var $ = layui.$
            , carousel = layui.carousel
            , device = layui.device();

        //轮播切换
        $('.layadmin-carousel').each(function () {
            var othis = $(this);
            carousel.render({
                elem: this
                , width: '100%'
                , arrow: 'none'
                , interval: othis.data('interval')
                , autoplay: othis.data('autoplay') === true
                , trigger: (device.ios || device.android) ? 'click' : 'hover'
                , anim: othis.data('anim')
            });
        });


    });

    //数据概览
    layui.use(['admin', 'carousel', 'echarts', 'jquery'], function () {
        var $ = layui.$
            , admin = layui.admin
            , $ = layui.jquery
            , carousel = layui.carousel
            , echarts = layui.echarts;

        var txt = [];
        for (var i = 0; i < ipSummary.length; i++) {
            txt.push(ipSummary[i].name);
        }
        var items = [], cnts = [];
        for (var i = 0; i < urlSummary.length; i++) {
            items.push(urlSummary[i].item);
            cnts.push(urlSummary[i].cnt);
        }

        var browsers = [];
        for (var i = 0; i < browserSummary.length; i++) {
            browsers.push(browserSummary[i].name);
        }
        var echartsApp = [], options = [
            //访客浏览器
            {
                title: {
                    text: '访客访问浏览器分布（近30天）',
                    x: 'center',
                    textStyle: {
                        fontSize: 14
                    }
                },
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b}: {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    x: 'left',
                    data: browsers
                },
                series: [
                    {
                        name: '访问来源',
                        type: 'pie',
                        radius: ['50%', '70%'],
                        avoidLabelOverlap: false,
                        label: {
                            normal: {
                                show: false,
                                position: 'center'
                            },
                            emphasis: {
                                show: true,
                                textStyle: {
                                    fontSize: '30',
                                    fontWeight: 'bold'
                                }
                            }
                        },
                        labelLine: {
                            normal: {
                                show: false
                            }
                        },
                        data: browserSummary
                    }
                ]
            },
            //访客浏IP分布
            {
                title: {
                    text: '访客IP访问分布（近30天前10的地域）',
                    x: 'center',
                    textStyle: {
                        fontSize: 14
                    }
                },
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    x: 'right',
                    data: txt
                },
                series: [{
                    name: '访问来源',
                    type: 'pie',
                    radius: '55%',
                    center: ['50%', '50%'],
                    data: ipSummary
                }]
            },
            //新增的用户量
            {
                title: {
                    text: '访问PV统计',
                    x: 'center',
                    textStyle: {
                        fontSize: 14
                    }
                },
                tooltip: { //提示框
                    trigger: 'axis',
                    formatter: "{b}<br>累计访问：{c}次"
                },
                xAxis: [{ //X轴
                    type: 'category',
                    data: items
                }],
                yAxis: [{  //Y轴
                    type: 'value'
                }],
                series: [{ //内容
                    type: 'line',
                    data: cnts
                }]
            }
        ]
            , elemDataView = $('#LAY-index-dataview').children('div')
            , renderDataView = function (index) {
            echartsApp[index] = echarts.init(elemDataView[index], layui.echartsTheme);
            echartsApp[index].setOption(options[index]);
            //window.onresize = echartsApp[index].resize;
            admin.resize(function () {
                echartsApp[index].resize();
            });
        };


        //没找到DOM，终止执行
        if (!elemDataView[0]) return;


        renderDataView(0);

        //监听数据概览轮播
        var carouselIndex = 0;
        carousel.on('change(LAY-index-dataview)', function (obj) {
            renderDataView(carouselIndex = obj.index);
        });

        //监听侧边伸缩
        layui.admin.on('side', function () {
            setTimeout(function () {
                renderDataView(carouselIndex);
            }, 300);
        });

        //监听路由
        layui.admin.on('hash(tab)', function () {
            layui.router().path.join('') || renderDataView(carouselIndex);
        });


        $("#checkUpdate").click(function () {
            $.ajax({
                url: "https://wuwenbin.me/checkUpdate",
                type: "post",
                dataType: 'json',
                data: {
                    clientVersion: ver
                },
                beforeSend: function () {
                    layer.msg("检查中...");
                },
                success: function (resp) {
                    layer.msg(resp.message)
                },
                error: function () {
                    layer.msg("检查更新出错，请稍后再试！");
                }
            });
        })

    });


    exports('dashboard', {})
});