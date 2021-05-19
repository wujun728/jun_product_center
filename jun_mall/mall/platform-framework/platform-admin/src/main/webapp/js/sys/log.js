$(function () {
    $("#sysLogGrid").Grid({
        url: '../sys/log/list',
        colModel: [
            {label: 'id', name: 'id', key: true, hidden: true},
            {label: '用户名', name: 'userName', index: 'user_name', width: 50},
            {label: '用户操作', name: 'operation', index: 'operation', width: 70},
            {label: '方法', name: 'title', index: 'title', width: 150},
            {label: '请求参数', name: 'params', index: 'params', width: 80},
            {label: 'IP地址', name: 'ip', index: 'ip', width: 70},
            {
                label: '创建时间', name: 'createDate', index: 'create_date', width: 90, formatter: function (value) {
                return transDate(value);
            }
            }
        ]
    });
});

var vm = new Vue({
    el: '#sysLog',
    data: {
        q: {
            key: null
        },
        isLogin: []
    },
    methods: {
        query: function () {
            vm.reload();
        },
        reload: function (event) {
            let page = $("#sysLogGrid").jqGrid('getGridParam', 'page');

            let operation = '';
            if (vm.isLogin && vm.isLogin.length > 0) {
                operation = vm.isLogin[0];
            }
            $("#sysLogGrid").jqGrid('setGridParam', {
                postData: {'key': vm.q.key, 'operation': operation},
                page: page
            }).trigger("reloadGrid");
        },
        reloadSearch: function () {
            vm.q = {
                key: ''
            }
            vm.isLogin = [];
            vm.query();
        }
    }
});