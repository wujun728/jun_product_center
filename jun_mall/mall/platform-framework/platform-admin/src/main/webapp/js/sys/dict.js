$(function () {
    $("#sysDictGrid").Grid({
        url: '../sys/dict/list',
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '唯一标识', name: 'groupCode', index: 'group_code', width: 80},
            {label: 'key', name: 'dictKey', index: 'dict_key', width: 80},
            {label: 'value', name: 'dictValue', index: 'dict_value', width: 80},
            {label: '备注', name: 'remark', index: 'remark', width: 80}]
    });
});

let vm = new Vue({
    el: '#sysDict',
    data: {
        showList: true,
        title: null,
        dict: {},
        ruleValidate: {
            groupCode: [
                {required: true, message: '唯一标识不能为空', trigger: 'blur'}
            ]
        },
        q: {
            groupCode: ''
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.dict = {};
        },
        update: function (event) {
            let id = getSelectedRow("#sysDictGrid");
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id)
        },
        saveOrUpdate: function (event) {
            let url = vm.dict.id == null ? "../sys/dict/save" : "../sys/dict/update";
            Ajax.request({
                url: url,
                params: JSON.stringify(vm.dict),
                type: "POST",
                contentType: "application/json",
                successCallback: function (r) {
                    alert('操作成功', function (index) {
                        vm.reload();
                    });
                }
            });
        },
        del: function (event) {
            let ids = getSelectedRows("#sysDictGrid");
            if (ids == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                Ajax.request({
                    url: "../sys/dict/delete",
                    params: JSON.stringify(ids),
                    type: "POST",
                    contentType: "application/json",
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
                url: "../sys/dict/info/" + id,
                async: true,
                successCallback: function (r) {
                    vm.dict = r.dict;
                }
            });
        },
        reload: function (event) {
            vm.showList = true;
            let page = $("#sysDictGrid").jqGrid('getGridParam', 'page');
            $("#sysDictGrid").jqGrid('setGridParam', {
                postData: {'groupCode': vm.q.groupCode},
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
        },
        reloadSearch: function () {
            vm.q = {
                groupCode: ''
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