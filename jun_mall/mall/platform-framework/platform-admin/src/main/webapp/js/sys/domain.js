$(function () {
    $("#sysDomainGrid").Grid({
        url: '../sys/domain/list',
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '域编码', name: 'domainCode', index: 'domain_code', width: 80},
            {label: '域名称', name: 'domainName', index: 'domain_name', width: 80},
            {label: '域地址', name: 'domainUrl', index: 'domain_url', width: 80},
            {
                label: '状态', name: 'domainStatus', index: 'domain_status', width: 80, formatter: function (value) {
                return transStatus(value);
            }
            },
            {
                label: '创建时间', name: 'createTime', index: 'create_time', width: 80, formatter: function (value) {
                return transDate(value);
            }
            },
            {label: '备注', name: 'remark', index: 'remark', width: 80},
            {
                label: '图标', name: 'icon', index: 'icon', width: 80, formatter: function (value) {
                if (!value) {
                    return '';
                }
                return '<i class="' + value + ' fa-lg">';
            }
            }]
    });
});

let vm = new Vue({
    el: '#sysDomain',
    data: {
        showList: true,
        title: null,
        domain: {},
        ruleValidate: {
            name: [
                {required: true, message: '名称不能为空', trigger: 'blur'}
            ]
        },
        q: {
            domainName: ''
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.domain = {};
        },
        update: function (event) {
            let id = getSelectedRow("#sysDomainGrid");
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id)
        },
        saveOrUpdate: function (event) {
            let url = vm.domain.id == null ? "../sys/domain/save" : "../sys/domain/update";
            Ajax.request({
                url: url,
                params: JSON.stringify(vm.domain),
                contentType: "application/json",
                type: 'POST',
                successCallback: function () {
                    alert('操作成功', function (index) {
                        vm.reload();
                    });
                }
            });
        },
        del: function (event) {
            let ids = getSelectedRows("#sysDomainGrid");
            if (ids == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                Ajax.request({
                    url: "../sys/domain/delete",
                    params: JSON.stringify(ids),
                    contentType: "application/json",
                    type: 'POST',
                    successCallback: function () {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    }
                });
            });
        },
        getInfo: function (id) {
            Ajax.request({
                url: "../sys/domain/info/" + id,
                async: true,
                successCallback: function (r) {
                    vm.domain = r.domain;
                }
            });
        },
        reload: function (event) {
            vm.showList = true;
            let page = $("#sysDomainGrid").jqGrid('getGridParam', 'page');
            $("#sysDomainGrid").jqGrid('setGridParam', {
                postData: {'domainName': vm.q.domainName},
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
        },
        reloadSearch: function () {
            vm.q = {
                domainName: ''
            }
            vm.reload();
        },
        handleSubmit: function (name) {
            handleSubmitValidate(this, name, function () {
                vm.saveOrUpdate()
            });
        },
        handleReset: function (name) {
            handleResetForm(this, name);
        }
    }
});