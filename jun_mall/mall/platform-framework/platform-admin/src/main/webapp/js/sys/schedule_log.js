$(function () {
    $("#sysScheduleLogGrid").Grid({
        url: '../sys/scheduleLog/list',
        colModel: [
            {label: '日志ID', name: 'logId', index: 'log_id', key: true, hidden: true},
            {label: '任务ID', name: 'jobId', index: 'job_id', width: 50},
            {label: 'bean名称', name: 'beanName', index: 'bean_name', width: 60},
            {label: '方法名称', name: 'methodName', index: 'method_name', width: 60},
            {label: '参数', name: 'params', index: 'params', width: 60},
            {
                label: '状态', name: 'status', index: 'status', width: 50, formatter: function (value, options, row) {
                return value === 0 ?
                    '<span class="label label-success">成功</span>' :
                    '<span class="label label-danger pointer" onclick="vm.showError(' + row.logId + ')">失败</span>';
            }
            },
            {label: '耗时(单位：毫秒)', name: 'times', index: 'times', width: 70},
            {
                label: '执行时间', name: 'createTime', index: 'create_time', width: 80, formatter: function (value) {
                return transDate(value);
            }
            }
        ],
        footerrow: true,
        gridComplete: function () {
            var rowNum = parseInt($(this).getGridParam('records'), 10);
            if (rowNum > 0) {
                $(".ui-jqgrid-sdiv").show();
                var times = jQuery(this).getCol('times', false, 'sum');
                $(this).footerData("set", {
                    "jobId": "<font color='red'>合计<font>",
                    "times": "<font color='red'>" + times + "<font>"
                });
            } else {
                $(".ui-jqgrid-sdiv").hide();
            }
        }
    });
});

var vm = new Vue({
    el: '#sysScheduleLog',
    data: {
        q: {
            jobId: null
        }
    },
    methods: {
        query: function () {
            $("#sysScheduleLogGrid").jqGrid('setGridParam', {
                postData: {'jobId': vm.q.jobId},
                page: 1
            }).trigger("reloadGrid");
        },
        reloadSearch: function () {
            vm.q = {
                jobId: ''
            }
            vm.query();
        },
        showError: function (logId) {
            Ajax.request({
                url: "../sys/scheduleLog/info/" + logId,
                successCallback: function (r) {
                    openWindow({
                        title: '失败信息',
                        area: ['600px', '400px'],
                        shadeClose: true,
                        content: r.log.error
                    });
                }
            });
        },
        back: function (event) {
            history.go(-1);
        }
    }
});

