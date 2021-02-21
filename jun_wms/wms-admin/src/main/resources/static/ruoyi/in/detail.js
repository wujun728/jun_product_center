var billId =parseInt(localStorage.getItem(prefix+'/detail'));





var vue = new Vue({
    el: '.container-div',
    data: {
        isTop: 0,
        boxInfoDto:{

        },
        billDetailId:0,
        dialogVisible: false,
        dialogVisibleForGetBox:false,
        billInDetail: [],
        boxItems : [],
        boxItem:{
            itemCode:'',
            quantity:'',
            boxNo:'',
            billInDetailId:'',
        },
        billInMaster:{

        },
        billDetails: [],

        taskInfos:[],

        groupBox:{
            boxNo:'',
        },


    },
    methods:{
        inWare:function(boxItem){
            var boxItemId = boxItem.boxItemId;
            var boxCode = boxItem.boxCode;
            var itemCode = boxItem.itemCode;
            var batch = boxItem.batch;
            var quantity = boxItem.quantity;



        },

        saveTaskinfo:function(){

            var that = this;
            var data = {
                    boxInfoDto :that.boxInfoDto,
                    billDetailId:that.billDetailId,
                    isTop:that.isTop
                };
            $.ajax({
                cache : true,
                type : "POST",
                url :  ctx + "system/cellInfo/saveTaskinfo",
                data : JSON.stringify(data),
                dataType:"json",
                contentType: "application/json;charset-UTF-8",
                async : false,
                error : function(request) {
                    $.modal.alertError("系统错误");
                },
                success : function(data) {
                    debugger
                    alert("已成功下发任务,可在任务列表查询")

                    that.dialogVisibleForGetBox = false;
                    that.selectTaskInfoByBillInMasterId();
                }

            //that.dialogVisibleForGetBox = false;
        });
        },


        getBox:function(index){
            var that = this;

            $.ajax({
                cache : true,
                type : "POST",
                url :  ctx + "system/cellInfo/findBoxInfoForBillIn",
                data : {

                    'itemCode':that.billDetails[index].itemCode,
                    'batch':that.billDetails[index].supplierBatch,
                    'quantity':that.billDetails[index].quantity,
                },
                async : false,
                error : function(request) {
                    $.modal.alertError("系统错误");
                },
                success : function(data) {
                   // console.log(data);
                    if(data == ""){

                        alert("未找到合适的托盘")
                        return;
                    }
                    that.boxInfoDto = data;
                   // console.log(that.boxInfoDto)
                    that.billDetailId = that.billDetails[index].billInDetailId
                    that.dialogVisibleForGetBox = true;
                }
            });

        },



        selectTaskInfoByBillInMasterId:function(){

            var that = this;

            $.ajax({
                cache : true,
                type : "POST",
                url :  ctx + "in/taskInfo/getTaskinfoByBillInMasterId",
                data : {

                    billId:billId

                },
                async : false,
                error : function(request) {
                    $.modal.alertError("系统错误");
                },
                success : function(data) {
                    console.log(data);

                    debugger
                    that.taskInfos = data.data;

                }

            });

        },







        closeModal:function() {
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        },

        showBox:function(){
            var that = this;
            $.ajax({

                type : "POST",
                url : prefix + '/showBox',
                data : {

                    billId:billId
                },
                async : false,
                error : function() {
                    alert("系统繁忙！");
                },
                success : function(data) {

                    that.boxItems = data.rows;

                }

            });

        },



        saveBox:function(){

            this.dialogVisible = false;

            $.ajax({

                type : "POST",
                url : prefix + '/saveBox',
                data : this.boxItem,
                async : false,
                error : function() {
                    alert("保存afsasf！");
                },
                success : function() {
                    alert("保存成功！");

                }

            });

        },




        putBox:function(row){
            //alert(billInDetailId);
            var that = this;
            that.boxItem.itemCode = row.itemCode;
            that.boxItem.billInDetailId = row.billInDetailId;
            that.dialogVisible = true;

        },


        deleteBillDetail:function(billInDetailId){
            this.$confirm('此操作将永久删除该条信息, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                var result = $.getServerData(detailPrefix+'/delete','GET',{id:billInDetailId},);
                if(result.code==200){
                    this.$message({
                        message: '删除成功',
                        type: 'success'
                    });
                    this.getBillDetails();
                }
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });
            });

        },
        getBillMaster:function(){
            var that = this;
            $.ajax({
                cache : true,
                type : "POST",
                url :  prefix+'/findList',
                data : {
                    billId:billId
                },
                async : false,
                error : function(request) {
                    $.modal.alertError("系统错误");
                },
                success : function(data) {
                    debugger
                    that.billInMaster = data.rows[0];
                  //  that.showBox();
                }
            });
        },

        getBillDetails:function(){
            var that = this;
            $.ajax({
                cache : true,
                type : "POST",
                url :  detailPrefix+'/findList',
                data : {
                    billId:billId
                },
                async : false,
                error : function(request) {
                    $.modal.alertError("系统错误");
                },
                success : function(data) {
                    that.billDetails = data.rows;




                }
            });
        },

        closeModal:function(){
            debugger
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        },


        // putBillOnBoxByItemCode:function(billInDetailId){
        //
        //    $.ajax({
        //
        //        type : "POST",
        //        utl : detailPrefix+'/getItemCode',
        //        data : {
        //            ItemCode : billInDetailId
        //        },
        //        async : false,
        //        error : function(request) {
        //            $.modal.alertError("系统错误");
        //        },
        //        success : function(data) {
        //            $("#itemCode").html = data.itemCode;
        //        }
        //
        //
        //    });
        //
        // },



        //
        // saveBillDetail:function(){
        //     dialogVisible = false;
        //
        //
        // }



    },
    created:function(){
        var that = this;
        that.getBillMaster();
        that.getBillDetails();
        that.selectTaskInfoByBillInMasterId();
        that.showBox();

    },





    mounted: function () {

            /*var options = {
                url: detailPrefix + "/findList",
                createUrl: detailPrefix + "/add",
                updateUrl: detailPrefix + "/edit/{id}",
                removeUrl: detailPrefix + "/remove",
                exportUrl: detailPrefix + "/export",
                detailUrl:detailPrefix + "/detail",
                modalName: "入库单",
                showExport: true,
                queryParams:{
                    billId:billId,
                },
                columns: [{
                    checkbox: true
                },

                    {
                        field : 'itemCode',
                        title : '料号 ',
                        sortable: true
                    },




                    {
                        title: '操作',
                        align: 'center',
                        events: {
                            'click .detail': function (e, value, rowData, index) {

                            },
                            'click .edit': function (e, value, rowData, index) {


                            },
                            'click .delete': function (e, value, rowData, index) {



                            }
                        },
                        formatter: function(value, row, index) {
                            var actions = [];
                            actions.push('<a class="btn   btn-xs  detail"><i class="fa fa-remove"></i>查看</a>');
                            actions.push('<a class="btn btn-success btn-xs edit "><i class="fa fa-edit"></i>编辑</a> ');
                            actions.push('<a class="btn btn-danger btn-xs  delete"><i class="fa fa-remove"></i>删除</a>');
                            return actions.join('');
                        }
                    }]
            };
            $.table.init(options);*/

    }

})