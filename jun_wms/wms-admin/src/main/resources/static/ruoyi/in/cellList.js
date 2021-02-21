var billId =parseInt(localStorage.getItem(prefix+'/detail'));





var vue = new Vue({
    el: '.container-div',

    data: {
        cellList: [
            { name: 'hw', row: 1, col: 1, have: 2, select: 1 },
            { name: 'hw', row: 1, col: 2, have: 1, select: 1 },
            { name: 'hw', row: 1, col: 3, have: 1, select: 1 },
            { name: 'hw', row: 1, col: 4, have: 1, select: 1 },
            { name: 'hw', row: 1, col: 5, have: 1, select: 1 },

        ],
        shelfStyle: {
            width: '',
            paddingBottom: ''
        },
        shelfStyle2: {
            paddingBottom: ''
        },
        seen: false,
        current: 0,

        nowShowConten: {
            row: null,
            column: null,
            state: null,
        },

        bigSeen: false,
        bigCurrent: 0,

        tabsFlg: 1,
        shelfIndex: '',
        cadFlg: false,

        areas:[],
        doors:[],
        cellLists:[],
        cellInfoDtos:[],
        column:0,
        style:[],
        shelfs:[]
    },
    methods: {


        findCellInfoDto:function(){
            var that = this;
            $.ajax({
                cache : true,
                type : "POST",
                url :  "/system/cellInfo/findCellInfoDto",
                data : {
                    itemName:$("input[name='itemName']").val(),
                    itemCode:$("input[name='itemCode']").val(),
                    batch:$("input[name='batch']").val()

                },
                async : false,
                error : function(request) {
                    $.modal.alertError("系统错误");
                },
                success : function(data) {
                    that.cellInfoDtos = data;
                }
            });

        },



        changeShelfBoxBackGround:function(type,index){

            var shelfBoxs = [];

            if(type == "bottom"){

                shelfBoxs = document.querySelectorAll(".shelfBox.bottom");
            }else if(type == "top"){

                shelfBoxs = document.querySelectorAll(".shelfBox.top");
            }
            var shelfBoxxChilds =  shelfBoxs[index].children[0];

            shelfBoxxChilds.style.background = "red";

        },



        getCellListByAreaId:function(areaId){

            var that = this;
            $.ajax({
                cache : true,
                type : "POST",
                url :  "/system/cellInfo/findcellList",
                data : {
                    areaId : areaId
                },
                async : false,
                error : function(request) {
                    $.modal.alertError("系统错误");
                },
                success : function(data) {
                    debugger
                    that.cellLists = data;
                    var style = [];
                    var style2 = [];
                    for(var i=0;i<data.length;i++){

                        var column = data[i][data[i].length-1].scolumn;

                        var row = data[i][data[i].length-1].srow;

                        var width = 100/column + "%";
                        var paddingBottom = 600/row + 'px';
                        var paddingBottom2 = (600/row)-10 + 'px';

                        style.push({

                            width : width,
                            paddingBottom:paddingBottom
                        });
                        style2.push({
                            paddingBottom:paddingBottom2
                        });
                    }
                    that.style = style;
                    that.style2 = style2;

                }
            });

        },

        // getCellListByShelfId:function(shelfId){
        //
        //     var that = this;
        //     $.ajax({
        //         cache : true,
        //         type : "POST",
        //         url :  "/system/cellInfo/findcellList",
        //         data : {
        //             shelfId : shelfId
        //         },
        //         async : false,
        //         error : function(request) {
        //             $.modal.alertError("系统错误");
        //         },
        //         success : function(data) {
        //             that.cellLists = data;
        //
        //             var style = [];
        //             var style2 = [];
        //             for(var i=0;i<data.length;i++){
        //
        //                 var column = data[i][data[i].length-1].scolumn;
        //
        //                 var row = data[i][data[i].length-1].srow;
        //
        //                 var width = 100/column + "%";
        //                 var paddingBottom = 600/row + 'px';
        //                 var paddingBottom2 = (600/row)-10 + 'px';
        //
        //                 style.push({
        //
        //                     width : width,
        //                     paddingBottom:paddingBottom
        //                 });
        //                 style2.push({
        //                     paddingBottom:paddingBottom2
        //                 });
        //             }
        //             that.style = style;
        //             that.style2 = style2;
        //
        //         }
        //     });
        //
        // },
        //
        //
        // getShelfs:function(){
        //
        //     var that = this;
        //     $.ajax({
        //         cache : true,
        //         type : "POST",
        //         url :  "/system/shelfInfo/list",
        //         data : {
        //
        //         },
        //         async : false,
        //         error : function(request) {
        //             $.modal.alertError("系统错误");
        //         },
        //         success : function(data) {
        //
        //             that.shelfs = data.rows;
        //         }
        //     });
        // },




        getAreas:function(){

            var that = this;
            $.ajax({
                cache : true,
                type : "POST",
                url :  "/system/areaInfo/list",
                data : {

                },
                async : false,
                error : function(request) {
                    $.modal.alertError("系统错误");
                },
                success : function(data) {

                    that.areas = data.rows;
                }
            });

        },

        getDoors:function(){

            var that = this;
            $.ajax({
                cache : true,
                type : "POST",
                url :  "/system/door/findList",
                data : {

                },
                async : false,
                error : function(request) {
                    $.modal.alertError("系统错误");
                },
                success : function(data) {

                    that.doors = data.rows;
                }
            });

        },


        bigEnter(index) {
            this.bigSeen = true;
            this.bigCurrent = index;
        },
        bigLeave() {
            this.bigSeen = false;
            this.bigCurrent = null;
        },




        enter(index) {
            let cell = this.cellLists[0][index];
            this.nowShowConten.row = this.cellLists[0][this.cellLists[0].length-1].srow+1-cell.srow;
            this.nowShowConten.column = cell.scolumn;
            if(cell.state === 0){
                this.nowShowConten.state = '无货';
            }else if(cell.state === 0){
                this.nowShowConten.state = '有货';
            }else if(cell.state === 0){
                this.nowShowConten.state = '锁定';
            }else if(cell.state === 0){
                this.nowShowConten.state = '故障';
            }

            this.seen = true;
            this.current = index;
        },
        leave() {
            this.seen = false;
            this.current = null;
        },
        selectShelf(type) {
            let index = this.shelfIndex
            for (let i = 0; i < this.cellList.length; i++) {
                this.cellList[i].select = 1;
            }
            if (type == 'position') {
                this.cellList[index].select = 2;
            } else if (type == 'clear') {
                this.shelfIndex = '';
            }
        },
        tranCad(){

            let cad = document.querySelector('.smallCad');

            let left = cad.style.left;
            if(this.cadFlg){
                cad.style.left = '0%';
                this.cadFlg = false
            }else{
                cad.style.left = '96%';
                this.cadFlg = true
            }
            // cad.style.transition = 'left 20s cubic-bezier(1, 1.01, 0.02, 0.01) 0s';

        }

    },
    created: function () {
        this.getAreas();
        this.getDoors();
    },

    updated:function(){
        this.changeShelfBoxBackGround("top",0);
        this.changeShelfBoxBackGround("bottom",1);
    },
    mounted: function () {
        setTimeout(this.tranCad,1000);
        setInterval(this.tranCad,20000);


    },

})