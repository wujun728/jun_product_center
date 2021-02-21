var billId =parseInt(localStorage.getItem(prefix+'/detail'));

console.log(billId);

var vue = new Vue({
    el: '.container-div',
    data: {
        isTop: 0,
        boxInfoDto:{

        },
        billDetailId:0,
        dialogVisible: false,
        dialogVisibleForGetBox:false,
        billDetail: [],
        boxItems : [],
        boxItem:{
            itemCode:'',
            quantity:'',
            boxNo:'',
            billOutDetailId:'',
        },
        billOutMaster:{

        },


        billOutDetails: [],

        taskInfos:[],

        groupBox:{
            boxNo:'',
        },

        pickTasks:[],
        itemCode:'',
        itemName:'',
        quantity:0,
        selectedQuantity:0,
        message:"输入有误",
    },
    methods:{


        changeHowToBillOut:function(value){
            debugger
            var that = this;
            //先进先出
            if(value == 0){
                //将已选择数量和总数清零
                that.setAllPickQuantityTo0();
                for(var i=0;i<that.pickTasks.length;i++){

                    that.pickTasks[i].pickQuantity = that.pickTasks[i].quantity;
                    that.selectedQuantity += that.pickTasks[i].pickQuantity;

                    if(that.selectedQuantity > that.quantity){

                        that.pickTasks[i].pickQuantity -= (that.selectedQuantity - that.quantity);
                        that.selectedQuantity -= (that.selectedQuantity - that.quantity);
                        return
                    }

                }
            }



        },

        //将所有的选择数量和总选择数量归零
        setAllPickQuantityTo0:function(){
            var that = this;
            for(var i=0;i<that.pickTasks.length;i++){

                that.pickTasks[i].pickQuantity = 0;
            }
            that.selectedQuantity= 0;
        },


        changeSelectedQuantity:function(index,value){
            var that = this;
            //数量不得大于托盘中的数量
            if(parseInt(value) > that.pickTasks[index].quantity){
                $.modal.alertError("已选择数量超过托盘中的数量, 请重新选择！！");
                that.pickTasks[index].pickQuantity = 0;
                that.selectedQuantity = that.sumOfPickQuantity(that.pickTasks);
            }
            that.selectedQuantity = that.sumOfPickQuantity(that.pickTasks);
            if(that.selectedQuantity > that.quantity){
                $.modal.alertWarning("已达到预约出库数量！")
                //设置不能再加了  出货数量已经大于需要的数量了 ， 没必要继续出货了
                that.pickTasks[index].pickQuantity--;
                that.selectedQuantity--;
            }
        },
        //计算数组中 pickQuantity字段的和
        sumOfPickQuantity:function(arr){
            var sum = 0;
            for(var i=0;i<arr.length;i++){

                sum += parseInt(arr[i].pickQuantity);
            }
            return sum;
        },


        saveTaskinfoForBillOut:function(){
            var that = this;
            if(that.selectedQuantity != that.quantity){

                $.modal.alertError("已选择出库数量不等于预约出库数量，请修改选择数量！!");
                return;
            }
            var pickTasks = that.pickTasks;
            var istop = that.isTop;
            var data = {
                pickTasks : pickTasks,
                istop : istop,
            }
            $.ajax({
                cache : true,
                type : "POST",
                url :  ctx + "pickTask/saveTaskinfoForBillOut",
                data : JSON.stringify(data),
                dataType:"json",
                contentType: "application/json;charset-UTF-8",
                async : false,
                error : function(request) {
                    $.modal.alertError("系统错误");
                },
                success : function(data) {
                    $.modal.alertSuccess("已成功下发任务,可在任务列表查询");

                    that.dialogVisibleForGetBox = false;
                    that.selectTaskInfoByBillOutMasterId();
                }
            });
        },


        getBoxItemInCellInfoByItemCode:function(index){
            var that = this;
            that.itemCode = that.billOutDetails[index].itemCode;
            that.itemName = that.billOutDetails[index].itemName;
            that.quantity = that.billOutDetails[index].quantity;
            var billOutDetailId = that.billOutDetails[index].billOutDetailId;
            that.pickTasks=[];
            that.pickTaskInserts=[];
            that.selectedQuantity = 0;
                $.ajax({
                cache : true,
                type : "POST",
                url :  ctx + "system/cellInfo/getBoxItemInCellInfoByItemCode",
                data : {

                    'itemCode':that.billOutDetails[index].itemCode,
                },
                async : false,
                error : function(request) {
                    $.modal.alertError("系统错误");
                },
                success : function(data) {
                    if(data.data.length == 0){

                        $.modal.alertError("未找到合适的托盘");
                        return
                    }

                    var boxItems = data.data;
                    for(var i = 0;i<boxItems.length;i++){
                        var boxItem = boxItems[i];
                        var itemCode = boxItem.itemCode;
                        var boxCode = boxItem.boxCode;
                        var boxItemId = boxItem.id;
                        var quantity = boxItem.quantity;
                        var batch = boxItem.batch;
                        var pickQuantity = 0;
                        var pickTask = {
                            itemCode:itemCode,
                            boxCode:boxCode,
                            boxItemId:boxItemId,
                            quantity:quantity,
                            batch:batch,
                            pickQuantity:pickQuantity,
                            billOutDetailId:billOutDetailId
                        };
                        that.pickTasks.push(pickTask);
                    }
                    that.changeHowToBillOut(0);
                    that.dialogVisibleForGetBox = true;
                }
            });

        },



        selectTaskInfoByBillOutMasterId:function(){
            var that = this;
            $.ajax({
                cache : true,
                type : "POST",
                url :  ctx + "in/taskInfo/getTaskinfoByBillOutMasterId",
                data : {

                    billId:billId
                },
                async : false,
                error : function(request) {
                    $.modal.alertError("系统错误");
                },
                success : function(data) {

                    that.taskInfos = data.data;
                }
            });
        },

        closeModal:function() {
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        },

        deleteBillDetail:function(billOutDetailId){
            var id = billOutDetailId;
            this.$confirm('此操作将永久删除该条信息, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                var result = $.getServerData(detailPrefix+'/deleteBillOutDetailByBillOutDetailId','GET',{billOutDetailId:billOutDetailId},);
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
                url :  prefix+'/findBillOutMasterDtoByBillId',
                data : {
                    billId:billId
                },
                async : false,
                error : function(request) {
                    $.modal.alertError("系统错误");
                },
                success : function(data) {
                    that.billOutMaster = data;
                }
            });
        },

        getBillDetails:function(){
            var ip = detailPrefix+'/findListByBillId';

            var that = this;
            $.ajax({
                cache : true,
                type : "POST",
                url :  detailPrefix+'/findListByBillId',
                data : {
                    billId:billId
                },
                async : false,
                error : function(request) {
                    $.modal.alertError("系统错误");
                },
                success : function(data) {
                    console.log(data);
                    that.billOutDetails = data.rows;
                }
            });
        },

        closeModal:function(){
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        },



    },
    created:function(){
        var that = this;
        that.getBillMaster();
        that.getBillDetails();
        that.selectTaskInfoByBillOutMasterId();

    },

    mounted: function () {

    }

})