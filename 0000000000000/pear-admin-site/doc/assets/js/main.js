/** EasyWeb doc v2.0.0 date:2019-10-01 License By http://easyweb.vip */
$(function () {
    /* 切换主题 */
    var $body = $('body');
    var theme = localStorage.getItem('doc_theme');
    if (theme) $body.addClass(theme);

    /* 解决展开静态的二级菜单不能不能折叠动态的二级菜单的问题 */
    $(document).on('click', '.sidebar-nav li>a', function () {
        var $parent = $(this).parent();
        $parent.parent().children('li').not($parent).addClass('collapse');
    });

    /* 回到顶部 */
    $body.append('<div class="ew-back-top" style="display: none;"></div>');
    $(window).scroll(function () {
        if ($(window).scrollTop() > 100) $('.ew-back-top').show();
        else $('.ew-back-top').hide();
    });
    var $html = $('html,body');
    $('.ew-back-top').click(function () {
        $html.stop();
        $html.animate({scrollTop: 0}, 300);
        var lash = location.hash;
        var index = lash.lastIndexOf('?id=');
        if (index !== -1) location.hash = lash.substring(0, index);
    });

    /** 加载docsify的js */
    function getDocsifyScript() {
        $.getScript('../assets/docsify/plugins/zoom-image.min.js', function () {  // 图片查看
            $.getScript('../assets/docsify/docsify.min.js', function () {
                $.getScript('../assets/docsify/prism-all.min.js');  // 代码高亮
            });
        });
    }

    /** 初始化文档 */
    function initDocsify(openSearch) {
        window.$docsify = {name: '更新日志', loadSidebar: '_sidebar.md', auto2top: true, maxLevel: 3, subMaxLevel: 3};
        if (openSearch) {  // 开启搜索
            window.$docsify.search = {placeholder: '搜索', noData: '没有结果', depth: 5};
            $.getScript('../assets/docsify/plugins/search.min.js', function () {
                getDocsifyScript();
            });
        } else {
            localStorage.clear();
            localStorage.setItem('doc_theme', $body.hasClass('ew-dark') ? 'ew-dark' : '');
            getDocsifyScript();
        }
    }

    initDocsify(true);  // 初始化文档

});

/** 日期格式化 */
function dateFormat(date, fmt) {
    var o = {
        'M+': date.getMonth() + 1,
        'd+': date.getDate(),
        'h+': date.getHours() % 12 === 0 ? 12 : date.getHours() % 12,
        'H+': date.getHours(),
        'm+': date.getMinutes(),
        's+': date.getSeconds(),
        'q+': Math.floor((date.getMonth() + 3) / 3),
        'S': date.getMilliseconds()
    };
    var week = {
        '0': '/u65e5', '1': '/u4e00', '2': '/u4e8c',
        '3': '/u4e09', '4': '/u56db', '5': '/u4e94', '6': '/u516d'
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    if (/(E+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (
        (RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? '/u661f/u671f' : '/u5468') : '') + week[date.getDay() + '']);
    for (var k in o)
        if (new RegExp('(' + k + ')').test(fmt)) fmt = fmt.replace(RegExp.$1,
            RegExp.$1.length === 1 ? o[k] : ('00' + o[k]).substr(("" + o[k]).length));
    return fmt;
}
