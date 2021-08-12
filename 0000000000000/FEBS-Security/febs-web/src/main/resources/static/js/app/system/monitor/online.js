$(function () {
    var settings = {
        url: ctx + "session/active",
        pageSize: 100,
        columns: [{
            field: 'username',
            title: '用户名'
        }, {
            field: 'loginTime',
            title: '登录时间'
        }, {
            field: 'lastRequested',
            title: '最后访问时间'
        }, {
            field: 'ip',
            title: 'IP地址'
        }, {
            field: 'location',
            title: '登录地点'
        }, {
            field: 'loginType',
            title: '登录方式',
            formatter: function (value, row, index) {
                switch (value) {
                    case 'normal':
                        return '<span class="badge badge-success">账号密码</span>';
                    case 'social':
                        return '<span class="badge badge-warning">社交账号</span>';
                    case 'sms':
                        return '<span class="badge badge-primary">手机验证码</span>';
                    default:
                        return '<span class="badge badge-default">未知</span>';
                }
            }
        }, {
            field: 'sessionId',
            title: 'SessionId',
            formatter: function (value, row, index) {
                return value.substr(0, 8) + '*************' + value.substr(20);
            }
        }, {
            title: '操作',
            formatter: function (value, row, index) {
                return "<a href='#' onclick='kickout(\"" + row.sessionId + "\")'>下线</a>";
            }
        }]
    };

    $MB.initTable('onlineTable', settings);
});

function kickout(sessionId) {
    $.get(ctx + "session/kickout", {"sessionId": sessionId}, function (r) {
        if (r.code === 0) {
            if (r.msg === 'refresh') {
                location.href = ctx + "login";
            }
            $MB.refreshTable('onlineTable');
            $MB.n_success('该用户已强制下线！');
        } else {
            $MB.n_danger(r.msg);
        }
    }, "json");
}